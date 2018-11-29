package com.towcent.dist.shop.web.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 系统教程Entity
 * @author HuangTao
 * @version 2018-07-22
 */
public class SysHelpGuide extends DataEntity<SysHelpGuide> {
	
	private static final long serialVersionUID = 1L;
	private String guideType;		// 教程类别(1:系统界面)
	private String title;		// 指南标题
	private String picUrl;		// 指南图片地址
	private String sort;		// 排序号
	private Integer merchantId;		// 商户Id
	
	public SysHelpGuide() {
		super();
	}

	public SysHelpGuide(String id){
		super(id);
	}

	@Length(min=0, max=2, message="教程类别(1:系统界面)长度必须介于 0 和 2 之间")
	public String getGuideType() {
		return guideType;
	}

	public void setGuideType(String guideType) {
		this.guideType = guideType;
	}
	
	@Length(min=0, max=50, message="指南标题长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=200, message="指南图片地址长度必须介于 0 和 200 之间")
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	@Length(min=0, max=11, message="排序号长度必须介于 0 和 11 之间")
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	// @Length(min=1, max=11, message="商户Id长度必须介于 1 和 11 之间")
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}