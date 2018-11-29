package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

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
public class CouponAct implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 活动名称.
     */
	private String actName;
	
	/**
     * 活动开始时间.
     */
	private Date startTime;
	
	/**
     * 活动结束时间.
     */
	private Date endTime;
	
	/**
     * 类型(1:现金券 2:折扣券 3:满减券 ).
     */
	private String actType;
	
	/**
     * 满减金额.
     */
	private BigDecimal limitAmount;
	
	/**
     * 优惠券金额/折扣金额.
     */
	private BigDecimal amount;
	
	/**
     * 总发行数量.
     */
	private Integer totalQty;
	
	/**
     * 剩余数量.
     */
	private Integer residQty;
	
	/**
     * 是否开启(0:否 1:是) yes_no.
     */
	private String openFlag;
	
	/**
     * 活动页地址.
     */
	private String actUrl;
	
	/**
     * 活动页地址二维码.
     */
	private String actQrCode;
	
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