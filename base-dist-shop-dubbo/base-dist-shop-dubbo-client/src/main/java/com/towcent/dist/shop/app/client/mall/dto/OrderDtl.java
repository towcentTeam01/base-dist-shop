package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 18:16:12
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class OrderDtl implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 订单id.
     */
	private Integer orderId;
	
	/**
     * 商品id.
     */
	private Integer goodsId;
	
	/**
     * 商品名称.
     */
	private String goodsName;
	
	/**
     * 商品图片.
     */
	private String goodsPicUrl;
	
	/**
     * 规格.
     */
	private String spec;
	
	/**
     * 数量.
     */
	private Integer qty;
	
	/**
     * 单价(元).
     */
	private BigDecimal price;
	
	/**
     * 金额=数量*单价(元).
     */
	private BigDecimal amount;
	
	/**
     * 兑换积分.
     */
	private BigDecimal integral;
	
	/**
     * 评价标识(0:未评价 1:已评价) yes_no.
     */
	private String evalFlag;
	
	/**
     * 商户id.
     */
	private Integer merchantId;
	
	/**
     * 规格id.
     */
	private Integer specId;
	
	
}