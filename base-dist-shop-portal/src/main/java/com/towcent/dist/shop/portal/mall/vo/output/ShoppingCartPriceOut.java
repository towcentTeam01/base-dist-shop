package com.towcent.dist.shop.portal.mall.vo.output;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 2.1.5 购物车商品介个
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ShoppingCartPriceOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal price;		// 购物车商品价格
	
}