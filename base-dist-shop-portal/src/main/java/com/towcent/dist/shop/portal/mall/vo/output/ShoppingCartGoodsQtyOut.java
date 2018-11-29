package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.1.5 购物车商品数量
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ShoppingCartGoodsQtyOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer shopCartNum;		// 购物车商品数量	
	
}