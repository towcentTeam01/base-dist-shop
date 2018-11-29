package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 商品分类id.
     */
	private Integer categoryId;
	
	/**
     * 分类结构编码.
     */
	private String structureNo;
	
	/**
     * 分类结构名称.
     */
	private String structureName;
	
	/**
     * 商品编码.
     */
	private String goodsNo;
	
	/**
     * 商品名称.
     */
	private String goodsName;
	
	/**
     * 商品类型(0:普通商品 1:批发商品).
     */
	private String goodsType;
	
	/**
     * 商品条形码.
     */
	private String goodsBarcode;
	
	/**
     * 商品状态(1:未发布 2:上架 3:下架 4:图片处理中).
     */
	private String goodsStatus;
	
	/**
     * 商品品牌.
     */
	private String brand;
	
	/**
     * 商品价格.
     */
	private BigDecimal price;
	
	/**
     * 兑换积分.
     */
	private Integer integral;
	
	/**
     * 最小商品价格,当商品类型为批发商品时独有.
     */
	private BigDecimal minPrice;
	
	/**
     * 最大商品价格,当商品类型为批发商品时独有.
     */
	private BigDecimal maxPrice;
	
	/**
     * 商品成本价.
     */
	private BigDecimal costPrice;
	
	/**
     * 商品图片url(多个;分割).
     */
	private String mainUrls;
	
	/**
     * 商品简介.
     */
	private String description;
	
	/**
     * 商品图片描述.
     */
	private String descPic;
	
	/**
     * 图片版本.
     */
	private Integer descPicV;
	
	/**
     * 重量(kg).
     */
	private BigDecimal weight;
	
	/**
     * 销量.
     */
	private Integer sales;
	
	/**
     * 二维码地址.
     */
	private String qrCode;
	
	/**
     * 评价数量.
     */
	private Integer evaNum;

	/**
     * 好评数.
     */
	private BigDecimal goodEvalRate;
	
	/**
     * 注备.
     */
	private String remarks;
	
	/**
     * 创建者.
     */
	private Integer createBy;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 更新者.
     */
	private Integer updateBy;
	
	/**
     * 更新时间.
     */
	private Date updateDate;
	
	/**
     * 删除标记(0:正常1:删除).
     */
	private String delFlag;
	
	/**
     * 商户id.
     */
	private Integer merchantId;
	
	
}