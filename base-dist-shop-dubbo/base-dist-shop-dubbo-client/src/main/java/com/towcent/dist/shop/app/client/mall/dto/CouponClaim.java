package com.towcent.dist.shop.app.client.mall.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class CouponClaim implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 活动id.
     */
	private Integer actId;
	
	/**
     * 会员id.
     */
	private Integer userId;
	
	/**
     * 会员昵称.
     */
	private String nickName;
	
	/**
     * 满减金额.
     */
	private BigDecimal limitAmount;
	
	/**
     * 优惠券金额/折扣金额.
     */
	private BigDecimal amount;
	
	/**
     * 使用状态(0:未使用 1:已使用) use_flag.
     */
	private String useFlag;
	
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

	/**
	 * 订单id.
	 */
	private Integer orderId;

	private CouponAct couponAct;
	
	
}