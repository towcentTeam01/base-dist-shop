package com.towcent.dist.shop.web.config.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 等级配置Entity
 * @author huangtao
 * @version 2018-07-02
 */
public class SysLevelConf extends DataEntity<SysLevelConf> {
	
	private static final long serialVersionUID = 1L;
	private String levelName;		// 会员等级别名
	private String level;		// 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)
	private String lockFans;		// 升级所需锁粉数
	private String recOrder;		// 升级所需直推订单数
	private String payFee;		// 升级所需费用
	private String defaultFlag;		// 是否默认等级(0:否 1:是) yes_no
	private String directBkge;		// 直推佣金比例()
	private Integer merchantId;		// 商户id
	
	public SysLevelConf() {
		super();
	}

	public SysLevelConf(String id){
		super(id);
	}

	@Length(min=0, max=200, message="会员等级别名长度必须介于 0 和 200 之间")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Length(min=0, max=2, message="用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)长度必须介于 0 和 2 之间")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	@Length(min=0, max=11, message="升级所需锁粉数长度必须介于 0 和 11 之间")
	public String getLockFans() {
		return lockFans;
	}

	public void setLockFans(String lockFans) {
		this.lockFans = lockFans;
	}
	
	@Length(min=0, max=11, message="升级所需直推订单数长度必须介于 0 和 11 之间")
	public String getRecOrder() {
		return recOrder;
	}

	public void setRecOrder(String recOrder) {
		this.recOrder = recOrder;
	}
	
	public String getPayFee() {
		return payFee;
	}

	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}
	
	@Length(min=0, max=1, message="是否默认等级(0:否 1:是) yes_no长度必须介于 0 和 1 之间")
	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	
	public String getDirectBkge() {
		return directBkge;
	}

	public void setDirectBkge(String directBkge) {
		this.directBkge = directBkge;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}