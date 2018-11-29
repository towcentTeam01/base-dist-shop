package com.towcent.dist.shop.common;

import com.towcent.base.common.utils.BaseCacheKey;

/** 
 * 模块级缓存key定义 
 */
public class CacheKeyUtils extends BaseCacheKey {

	/** 购物车缓存key */
	public static final String SHOP_CART_KEY = "itg:shopcart:";
	
	/**
	 * 购物车缓存key
	 * @param userId 会员Id
	 * @return
	 */
	public static String getShoppingCartKey(Integer userId) {
		StringBuilder sb = new StringBuilder(SHOP_CART_KEY);
		return sb.append(userId).toString();
	}
}
