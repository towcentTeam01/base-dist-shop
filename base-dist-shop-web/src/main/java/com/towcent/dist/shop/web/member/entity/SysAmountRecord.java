package com.towcent.dist.shop.web.member.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 钱包明细Entity
 * @author huangtao
 * @version 2018-07-08
 */
public class SysAmountRecord extends DataEntity<SysAmountRecord> {
	
	private static final long serialVersionUID = 1L;
	private SysFrontAccount user;		// 用户id
	private String dealNo;		// 交易号
	private String direction;		// 交易去向(作废)
	private String type;		// 类型(0:支出，1:收入)
	private String amount;		// 此次交易金额
	private String amountAfter;		// 此次流水之后的余额（暂时不适用）
	private String orderAmount;		// 订单金额
	private String orderTitle;		// 订单title(默认商品名称)
	private Integer merchantId;		// 商户id
	private Date beginCreateDate;		// 开始 发生的时间
	private Date endCreateDate;		// 结束 发生的时间
	
	public SysAmountRecord() {
		super();
	}

	public SysAmountRecord(String id){
		super(id);
	}

	public SysFrontAccount getUser() {
		return user;
	}
	
	public void setUser(SysFrontAccount user) {
		this.user = user;
	}

	@Length(min=0, max=100, message="交易号长度必须介于 0 和 100 之间")
	public String getDealNo() {
		return dealNo;
	}

	public void setDealNo(String dealNo) {
		this.dealNo = dealNo;
	}
	
	@Length(min=0, max=100, message="交易去向(作废)长度必须介于 0 和 100 之间")
	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	@Length(min=1, max=1, message="类型(0:支出，1:收入)长度必须介于 1 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getAmountAfter() {
		return amountAfter;
	}

	public void setAmountAfter(String amountAfter) {
		this.amountAfter = amountAfter;
	}
	
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	@Length(min=0, max=200, message="订单title(默认商品名称)长度必须介于 0 和 200 之间")
	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
}