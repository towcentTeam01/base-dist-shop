package com.towcent.dist.shop.app.server.mall.manager;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.redis.RedisTemplateExt;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.service.BaseService;
import com.towcent.dist.shop.app.client.mall.sevice.GoodsApi;
import com.towcent.dist.shop.app.client.mall.sevice.ShoppingCartApi;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSkuVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartParamVo;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartVo;
import com.towcent.dist.shop.common.CacheKeyUtils;

@Service
public class ShoppingCartApiImpl extends BaseService implements ShoppingCartApi {

	@Resource
	private RedisTemplateExt<String, Object> redisTemplateExt;
	@Resource
	private GoodsApi goodsApi;

	@Override
	public GoodsSkuVo add(ShoppingCartParamVo paramVo) throws RpcException {
		try {
			Integer qty = paramVo.getQty();
			String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
			String hashKey = paramVo.getGoodsId() + "_" + paramVo.getSpec();
			String tpStr = (String) redisTemplateExt.hGet(key, hashKey);
			String[] tpStrs = null;
			if (StringUtils.isNotBlank(tpStr)) {
				tpStrs = StringUtils.split(tpStr, ":");
				qty += Integer.valueOf(tpStrs[0]);
			}
			
			paramVo.setQty(qty);
			GoodsSkuVo skuVo = extractGoodsSku(paramVo);
			
			redisTemplateExt.hSet(key, hashKey, qty + ":" + paramVo.getMerchantId());
			return skuVo;
		} catch (Exception e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public boolean del(ShoppingCartParamVo paramVo) throws RpcException {
		try {
			String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
			String hashKey = paramVo.getGoodsId() + "_" + paramVo.getSpec();
			redisTemplateExt.hDel(key, hashKey);
			return true;
		} catch (Exception e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public boolean batchDel(ShoppingCartParamVo paramVo) throws RpcException {
		try {
			String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
			String[] hashKey = StringUtils.split(paramVo.getKeys(), ";");
			redisTemplateExt.hDel(key, hashKey);
			return true;
		} catch (Exception e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public GoodsSkuVo edit(ShoppingCartParamVo paramVo) throws RpcException {
		try {
			Integer qty = paramVo.getQty();
			String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
			String hashKey = paramVo.getGoodsId() + "_" + paramVo.getSpec();
			// String tpStr = (String) redisTemplateExt.hGet(key, hashKey);
			// String[] tpStrs = StringUtils.split(tpStr, ":");
			
			paramVo.setQty(qty);
			GoodsSkuVo skuVo = extractGoodsSku(paramVo);
			
			redisTemplateExt.hSet(key, hashKey, qty + ":" + paramVo.getMerchantId());
			return skuVo;
		} catch (Exception e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public List<ShoppingCartVo> list(ShoppingCartParamVo paramVo) throws RpcException {

		List<ShoppingCartVo> resultList = Lists.newArrayList();

		String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
		Map<Object, Object> resultMap = redisTemplateExt.hGetAll(key);
		if (!MapUtils.isEmpty(resultMap)) {

			ShoppingCartVo shoppingCartVo = null;
			for (Object rkey : resultMap.keySet()) {

				String zKey = (String) rkey;
				String[] rs = StringUtils.split(zKey, "_"); // 商品Id_规格Id
															// goodsId_spec
				Integer goodsId = Integer.valueOf(rs[0]);
				Integer spec = Integer.valueOf(rs[1]);
				String tpStr = (String) resultMap.get(rkey); // (qty:shareId)
				String[] tpStrs = StringUtils.split(tpStr, ":"); // 数量
				Integer qty = Integer.valueOf(tpStrs[0]);

				paramVo.setQty(qty);
				paramVo.setSpec(spec);
				paramVo.setGoodsId(goodsId);
				shoppingCartVo = getShoppingCartVo(paramVo);
				Integer stock = shoppingCartVo.getStock();

				// 数量大于库存时，则自动变为库存量 没有库存时。忽略该商品
				if (stock == null || stock == 0)
					continue;

				resultList.add(shoppingCartVo);
			}
		}
		return resultList;
	}

	/**
	 * 获取商品库存和规格名称
	 *
	 * @param paramVo
	 * @return
	 * @throws RpcException 
	 */
	private ShoppingCartVo getShoppingCartVo(ShoppingCartParamVo paramVo) throws RpcException {
		ShoppingCartVo shoppingCartVo = new ShoppingCartVo();

		GoodsSkuVo skuVo = extractGoodsSku(paramVo);
		GoodsVo goodsVo = skuVo.getGoods();
		shoppingCartVo.setUserId(paramVo.getUserId());
		shoppingCartVo.setMerchantId(goodsVo.getMerchantId());
		
		shoppingCartVo.setGoodsId(skuVo.getGoods().getId());
		shoppingCartVo.setGoodsType(goodsVo.getGoodsType());
		shoppingCartVo.setGoodsName(goodsVo.getGoodsName());
		shoppingCartVo.setSpec(skuVo.getSpec());
		shoppingCartVo.setSpecName(skuVo.getSpecName());
		shoppingCartVo.setQty(skuVo.getQty());
		shoppingCartVo.setStock(skuVo.getStock());
		shoppingCartVo.setPrice(skuVo.getPrice());
		shoppingCartVo.setPicUrl(goodsVo.getPicUrl());
		shoppingCartVo.setIntegral(goodsVo.getIntegral());
		
		return shoppingCartVo;
	}

	/**
	 * 提取商品库存和规格名称
	 * @param paramVo
	 * @return
	 * @throws RpcException 
	 */
	private GoodsSkuVo extractGoodsSku(ShoppingCartParamVo paramVo) throws RpcException {
		if (null == paramVo.getGoodsId()) return null;
		
		GoodsSkuVo skuVo = new GoodsSkuVo();
		
		// 1. 查询商品详情
		GoodsVo goodsVo = goodsApi.queryGoodsById(paramVo.getGoodsId());
		skuVo.setGoods(goodsVo);
		skuVo.setSpec(paramVo.getSpec());
		skuVo.setQty(paramVo.getQty());
		GoodsUtils.assemblySku(skuVo);
		return skuVo;
	}

	@Override
	public ShoppingCartVo get(ShoppingCartParamVo paramVo) throws RpcException {
		try {
			String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
			String hashKey = paramVo.getGoodsId() + "_" + paramVo.getSpec();
			String tpStr = (String) redisTemplateExt.hGet(key, hashKey);
			Integer qty = 0;
			if (null == paramVo.getQty()) { // 如果购物车存在该规格的商品，则以购物车中的数量为准
				qty = Integer.valueOf(StringUtils.substringBefore(tpStr, ":"));
			} else { // 以前端传递过来的数量
				qty = paramVo.getQty();
			}

			paramVo.setQty(qty);
			ShoppingCartVo shoppingCartVo = getShoppingCartVo(paramVo);
			Integer stock = shoppingCartVo.getStock();
			// 数量大于库存时，则自动变为库存量 没有库存时。忽略该商品
			if (null == stock || stock == 0) {
				throw new ServiceException(
						shoppingCartVo.getGoodsName() + " [" + shoppingCartVo.getSpecName() + "] " + "库存不足");
			}

			return shoppingCartVo;
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", e.getMessage(), e);
		}
	}

	@Override
	public int getCartQty(ShoppingCartParamVo paramVo) throws RpcException {
		String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
		Map<Object, Object> resultMap = redisTemplateExt.hGetAll(key);
		int qty = resultMap.size();
		return qty;
	}

	@Override
	public int getGoodsQty(ShoppingCartParamVo paramVo) throws RpcException {
		String key = CacheKeyUtils.getShoppingCartKey(paramVo.getUserId());
		Map<Object, Object> resultMap = redisTemplateExt.hGetAll(key);
		int qty = 0;
		if (MapUtils.isNotEmpty(resultMap)) {
			for (Object okey : resultMap.keySet()) {
				String tpStr = (String) resultMap.get(okey);
				String[] tpStrs = StringUtils.split(tpStr, ":");
				qty += Integer.valueOf(tpStrs[0]);
			}
		}

		return qty;
	}
}
