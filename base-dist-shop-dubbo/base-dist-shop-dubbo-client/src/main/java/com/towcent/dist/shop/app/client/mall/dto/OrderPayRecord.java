package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class OrderPayRecord implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 支付交易号.
     */
	private String payRecordNo;
	
	/**
     * 业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员).
     */
	private String bizType;
	
	/**
     * 订单id.
     */
	private Integer orderId;
	
	/**
     * 支付方式(0:余额支付 1:微信支付 2:支付宝).
     */
	private String payType;
	
	/**
     * 支付时间.
     */
	private Date payDate;
	
	/**
     * 支付金额.
     */
	private BigDecimal payAmount;
	
	/**
     * 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败).
     */
	private String payStatus;
	
	/**
     * 余额金额.
     */
	private BigDecimal balanceAmount;
	
	/**
     * 支付积分.
     */
	private BigDecimal interAmount;
	
	/**
     * 网关支付金额.
     */
	private BigDecimal gatewayAmount;
	
	/**
     * 第三方支付流水号.
     */
	private String thirdPaySn;
	
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