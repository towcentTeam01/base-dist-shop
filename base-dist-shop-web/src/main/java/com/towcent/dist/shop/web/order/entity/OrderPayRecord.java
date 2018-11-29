package com.towcent.dist.shop.web.order.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.towcent.base.sc.web.common.persistence.DataEntity;
import com.towcent.dist.shop.web.member.entity.SysFrontAccount;

/**
 * 交易记录Entity
 * @author HuangTao
 * @version 2018-07-08
 */
public class OrderPayRecord extends DataEntity<OrderPayRecord> {
	
	private static final long serialVersionUID = 1L;
	private String payRecordNo;		// 支付交易号(唯一)
	private String bizType;		// 业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员)
	private OrderMain order;		// 订单
	private String payType;		// 支付方式(0:余额支付 1:微信支付 2:支付宝)
	private Date payDate;		// 支付时间
	private String payAmount;		// 支付金额
	private String payStatus;		// 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
	private String balanceAmount;		// 余额金额
	private String interAmount;		// 支付积分
	private String gatewayAmount;		// 网关支付金额
	private String thirdPaySn;		// 第三方支付流水号
	private Integer merchantId;		// 商户id
	private Date beginPayDate;		// 开始 支付时间
	private Date endPayDate;		// 结束 支付时间
	
	protected SysFrontAccount createBy1; // 创建者
	protected SysFrontAccount updateBy1; // 更新者
	
	public OrderPayRecord() {
		super();
	}

	public OrderPayRecord(String id){
		super(id);
	}

	@Length(min=1, max=64, message="支付交易号(唯一)长度必须介于 1 和 64 之间")
	public String getPayRecordNo() {
		return payRecordNo;
	}

	public void setPayRecordNo(String payRecordNo) {
		this.payRecordNo = payRecordNo;
	}
	
	@Length(min=0, max=2, message="业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员)长度必须介于 0 和 2 之间")
	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
	public OrderMain getOrder() {
		return order;
	}
	
	public void setOrder(OrderMain order) {
		this.order = order;
	}

	@Length(min=0, max=2, message="支付方式(0:余额支付 1:微信支付 2:支付宝)长度必须介于 0 和 2 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	@Length(min=0, max=1, message="支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)长度必须介于 0 和 1 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	public String getInterAmount() {
		return interAmount;
	}

	public void setInterAmount(String interAmount) {
		this.interAmount = interAmount;
	}
	
	public String getGatewayAmount() {
		return gatewayAmount;
	}

	public void setGatewayAmount(String gatewayAmount) {
		this.gatewayAmount = gatewayAmount;
	}
	
	@Length(min=0, max=64, message="第三方支付流水号长度必须介于 0 和 64 之间")
	public String getThirdPaySn() {
		return thirdPaySn;
	}

	public void setThirdPaySn(String thirdPaySn) {
		this.thirdPaySn = thirdPaySn;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	public Date getBeginPayDate() {
		return beginPayDate;
	}

	public void setBeginPayDate(Date beginPayDate) {
		this.beginPayDate = beginPayDate;
	}
	
	public Date getEndPayDate() {
		return endPayDate;
	}

	public void setEndPayDate(Date endPayDate) {
		this.endPayDate = endPayDate;
	}

	public SysFrontAccount getCreateBy1() {
		return createBy1;
	}

	public void setCreateBy1(SysFrontAccount createBy) {
		this.createBy1 = createBy;
	}

	public SysFrontAccount getUpdateBy1() {
		return updateBy1;
	}

	public void setUpdateBy1(SysFrontAccount updateBy) {
		this.updateBy1 = updateBy;
	}
		
}