package com.towcent.dist.shop.app.client.mall.utils;

import com.google.common.collect.Lists;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.dist.shop.app.client.mall.dto.GoodsSku;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSkuVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSpecVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品工具类
 *
 * @author huangtao
 * @date 2017年3月28日 下午12:21:15
 * @version 0.1.0
 * @copyright towcent.com
 */
public class GoodsUtils {

	private static final String ICON = "logo.jpg";
	/** 商品类型 普通商品 */
	public static final String GOODS_TYPE_0 = "0";
	/** 商品类型 批发商品 */
	public static final String GOODS_TYPE_1 = "1";

	/**
	 * 获取商品列表图片(120X120)
	 *
	 * @param mainUrls
	 * @return
	 */
	public static String getGoodsListPicUrl(String headerUrl, String mainUrls, Integer picv) {
		String[] urls = StringUtils.split(mainUrls, ";");
		if (ArrayUtils.isNotEmpty(urls)) {
			return urls[0].replaceAll("_0.jpg", "_3.jpg") + joinPicVersion(picv);
		} else {
			return headerUrl + ICON;
		}
	}

	public static String getGoodsMidListPicUrl(String mainUrls, Integer picv) {
		String[] urls = StringUtils.split(mainUrls, ";");
		if (ArrayUtils.isNotEmpty(urls)) {
			return urls[0].replaceAll("_0.jpg", "_2.jpg") + joinPicVersion(picv);
		}
		return null;
	}

	public static List<String> getGoodsMidPicUrlList(String mainUrls, Integer picv) {
		List<String> list = Lists.newArrayList();
		String[] urls = StringUtils.split(mainUrls, ";");
		if (ArrayUtils.isNotEmpty(urls)) {
			for (String url : urls) {
				list.add(url.replaceAll("_0.jpg", "_2.jpg") + joinPicVersion(picv));
			}
		}
		return list;
	}

	public static List<String> getGoodsPigPicUrlList(String mainUrls, Integer picv) {
		List<String> list = Lists.newArrayList();
		String[] urls = StringUtils.split(mainUrls, ";");
		if (ArrayUtils.isNotEmpty(urls)) {
			for (String url : urls) {
				list.add(url.replaceAll("_0.jpg", "_1.jpg") + joinPicVersion(picv));
			}
		}
		return list;
	}
	
	public static List<String> getGoodsDescPicUrlList(String descPic, Integer picv) {
		List<String> list = Lists.newArrayList();
		String[] urls = StringUtils.split(descPic, ";");
		if (ArrayUtils.isNotEmpty(urls)) {
			for (String url : urls) {
				list.add(url + joinPicVersion(picv));
			}
		}
		return list;
	}

	private static String joinPicVersion(Integer picv) {
		if (null == picv)
			return "";
		return "?v=" + picv;
	}

	/**
	 * 填充sku规格名称<br>
	 * 填充sku库存<br>
	 * 填充sku价格<br>
	 * @param skuVo
	 * @return
	 */
	public static GoodsSkuVo assemblySku(GoodsSkuVo skuVo) {
		GoodsVo goodsVo = skuVo.getGoods();
		if (null == goodsVo) return skuVo;
		
		List<GoodsSpecVo> specList = goodsVo.getSpecList();
		Integer spec = skuVo.getSpec();
		Integer qty = skuVo.getQty();
		if (null != spec && CollectionUtils.isNotEmpty(specList)) {
			for (GoodsSpecVo vo : specList) {
				if (spec == vo.getId().intValue()) {
					skuVo.setSpecName(vo.getName());
					skuVo.setStock(vo.getStock());
					if (GOODS_TYPE_0.equals(skuVo.getGoods().getGoodsType())) {
						skuVo.setPrice(vo.getPrice());
					}
					qty = qty > skuVo.getStock() ? skuVo.getStock() : qty;
					skuVo.setQty(qty);
					List<GoodsSku> skuList = vo.getSkuList();
					if (null != qty && GOODS_TYPE_1.equals(skuVo.getGoods().getGoodsType()) && CollectionUtils.isNotEmpty(skuList)) {
						for (GoodsSku sku : skuList) {
							if (qty >= sku.getMinNum() && qty <= sku.getMaxNum()) {
								if (sku.getPrice().compareTo(BigDecimal.ZERO) > 0) {
									skuVo.setPrice(sku.getPrice());
								}
							}
						}
					}
				}
			}
		}
		return skuVo;
	}
}
