/**
 * 
 */
package com.towcent.dist.shop.app.client.share.service;

import com.towcent.base.common.exception.RpcException;
import com.towcent.dist.shop.app.client.share.vo.ShareVo;

/**
 * @author shiwei
 *
 */
public interface ShareApi {

	/**
	 * 分享商品
	 * @param userId
	 * @param productId
	 * @return
	 * @throws RpcException
	 */
	public ShareVo shareProduct(Integer userId, Integer productId) throws RpcException;
	
	
	/**
	 * 分享商城
	 * @param userId
	 * @return
	 * @throws RpcException
	 */
	public ShareVo shareShop(Integer userId) throws RpcException;
	
	/**
	 * 邀请好友加入分享
	 * @param userId
	 * @return
	 * @throws RpcException
	 */
	public ShareVo shareFriend(Integer userId) throws RpcException;
	
}
