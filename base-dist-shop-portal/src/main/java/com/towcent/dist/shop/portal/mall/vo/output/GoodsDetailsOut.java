package com.towcent.dist.shop.portal.mall.vo.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 2.0.5 商品详情
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsDetailsOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 商品id	
	private String goodsName;		// 商品名称	
	private Integer goodsType;		// 商品类型 0:普通商品 1:批发商品	
	private Integer integral;		// 兑换积分	
	private BigDecimal price;		// 商品价格	
	private BigDecimal minPrice;		// 最小价格	
	private BigDecimal maxPrice;		// 最大价格	
	private List picUrlList;        // 商品图片列表
	private Integer evalNum;		// 评价数	
	private String goodEvalRate;		// 好评率
	private String description;		// 商品描述	
	private String servicePhone;		// 客服电话	
	private Integer isConcern;		// 收藏标识 是否已经收藏 0:否 1:是	
	private List descPicList;        // 商品描述图片列表
	private List specList;		// 规格列表	
	
}