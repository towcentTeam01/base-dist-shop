package com.towcent.dist.shop.web.config.entity;

import com.towcent.base.common.model.SysLogisticsCompany;
import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 商家物流配置Entity
 * @author HuangTao
 * @version 2018-07-11
 */
public class SysLogisticsCompanyMerchant extends DataEntity<SysLogisticsCompanyMerchant> {
	
	private static final long serialVersionUID = 1L;
	private SysLogisticsCompany company;		// 物流公司
	private Integer merchantId;		// 商家Id
	
	public SysLogisticsCompanyMerchant() {
		super();
	}

	public SysLogisticsCompanyMerchant(String id){
		super(id);
	}

	public SysLogisticsCompany getCompany() {
		return company;
	}

	public void setCompany(SysLogisticsCompany company) {
		this.company = company;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}