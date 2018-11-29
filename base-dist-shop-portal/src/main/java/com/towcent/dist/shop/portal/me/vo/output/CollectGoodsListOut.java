package com.towcent.dist.shop.portal.me.vo.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 3.2.1 我收藏的商品
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class CollectGoodsListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;        // 收藏id
	private Integer goodsId;        // 商品id
	private String goodsName;		// 商品名称	
	private String goodsType;        // 商品类型 0:普通商品 1:批发商品
	private Integer integral;		// 兑换积分	
	private BigDecimal price;		// 价格	
	private BigDecimal minPrice;		// 最小价格	
	private BigDecimal maxPrice;		// 最大价格	
	private String picUrl;		// 商品图片	
	
}