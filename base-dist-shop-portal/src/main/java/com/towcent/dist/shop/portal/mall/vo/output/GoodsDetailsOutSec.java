package com.towcent.dist.shop.portal.mall.vo.output;

import java.math.BigDecimal;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.0.5 商品详情
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsDetailsOutSec implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 规格id	
	private String name;		// 规格名称	
	private BigDecimal price;		// 价格	
	private Integer minNum;		// 最小数量 批发商品专有	
	private Integer maxNum;		// 最大数量 批发商品专有	
	private String unit;		// 单位 批发商品专有	
	private Integer goodsType;		// 商品类型 0:普通商品 1:批发商品	
	private Integer stock;		// 库存	
	
}