package com.towcent.dist.shop.web.member.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 提现申请Entity
 * @author HuangTao
 * @version 2018-07-08
 */
public class SysWithdrawApply extends DataEntity<SysWithdrawApply> {
	
	private static final long serialVersionUID = 1L;
	private String amount;		// 提现金额
	private String status;		// 提现状态(0:已申请 1:处理中 2:已处理)
	private Integer merchantId;		// 商户Id
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	protected SysFrontAccount createBy1; // 创建者
	
	public SysWithdrawApply() {
		super();
	}

	public SysWithdrawApply(String id){
		super(id);
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=2, message="提现状态(0:已申请 1:处理中 2:已处理)长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public SysFrontAccount getCreateBy1() {
		return createBy1;
	}

	public void setCreateBy1(SysFrontAccount createBy1) {
		this.createBy1 = createBy1;
	}
		
}