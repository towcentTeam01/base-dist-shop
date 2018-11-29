package com.towcent.dist.shop.portal.mall.vo.output;

import java.math.BigDecimal;
import java.util.List;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.1.4 购物车列表接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ShoppingCartListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 商品id	
	private String goodsName;		// 商品名称	
	private Integer goodsType;		// 商品类型 0:普通商品 1:批发商品	
	private Integer integral;		// 兑换积分	
	private BigDecimal price;		// 价格	
	private String spec;		// 规格	
	private Integer qty;		// 数量	
	private String picUrl;		// 商品图片	
	private List specList;		// 规格列表	
	
}