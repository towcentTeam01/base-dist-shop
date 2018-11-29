package com.towcent.dist.shop.web.sys.entity;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 海报模板Entity
 * @author HuangTao
 * @version 2018-07-31
 */
public class SysPosterTemplate extends DataEntity<SysPosterTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 模板名称
	private String picUrl;		// 模板图片URL
	private Integer sort;		// 排序号
	private Integer merchantId;		// 商户Id
	
	public SysPosterTemplate() {
		super();
	}

	public SysPosterTemplate(String id){
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}