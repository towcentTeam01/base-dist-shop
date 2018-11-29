package com.towcent.dist.shop.app.client.mall.sevice;

import com.towcent.base.common.exception.RpcException;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSkuVo;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartParamVo;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartVo;

import java.util.List;

/**
 * 购物车 接口
 *
 * @author yxp
 */
public interface ShoppingCartApi {

	/**
	 * 添加商品到购车
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	GoodsSkuVo add(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 删除购车里的商品
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	boolean del(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 批量删除购车里的商品
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	boolean batchDel(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 修改购车里商品的数量
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	GoodsSkuVo edit(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 购车列表数据
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	List<ShoppingCartVo> list(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 购车数据
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	ShoppingCartVo get(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 获取会员购物车商品种类
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	int getCartQty(ShoppingCartParamVo paramVo) throws RpcException;

	/**
	 * 获取会员购物车商品数量
	 * @param paramVo
	 * @return
	 * @throws RpcException
	 */
	int getGoodsQty(ShoppingCartParamVo paramVo) throws RpcException;
}
