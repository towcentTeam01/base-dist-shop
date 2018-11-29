package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 12:16:53
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class GoodsSpec implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 商品id.
     */
	private Integer goodsId;
	
	/**
     * 商品类型( 0:普通商品 1:批发商品 ).
     */
	private String goodsType;
	
	/**
     * 规格名称.
     */
	private String name;
	
	/**
     * 规格价格.
     */
	private BigDecimal price;
	
	/**
     * 规格库存.
     */
	private Integer stock;
	
	/**
     * 单位(件/套/...).
     */
	private String unit;
	
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