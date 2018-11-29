package com.towcent.dist.shop.portal.mall.vo.output;

import java.math.BigDecimal;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.3.2 订单列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderListOutSec implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 订单详情id	
	private Integer goodsId;		// 商品Id	
	private String goodsName;		// 商品名称	
	private String goodsPicUrl;		// 商品图	
	private String spec;		// 规格	
	private Integer integral;		// 兑换积分	
	private BigDecimal price;		// 单价	
	private Integer qty;		// 数量
	/**
	 * 评价标识(0:未评价 1:已评价) yes_no.
	 */
	private String evalFlag;
	
}