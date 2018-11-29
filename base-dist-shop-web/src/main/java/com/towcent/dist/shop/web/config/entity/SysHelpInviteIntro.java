package com.towcent.dist.shop.web.config.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 邀请介绍Entity
 * @author HuangTao
 * @version 2018-07-08
 */
public class SysHelpInviteIntro extends DataEntity<SysHelpInviteIntro> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 正文标题
	private String picUrl;		// 描述图片地址
	private String sort;		// 排序号
	private Integer merchantId;		// 商户Id
	
	public SysHelpInviteIntro() {
		super();
	}

	public SysHelpInviteIntro(String id){
		super(id);
	}

	@Length(min=0, max=50, message="正文标题长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=200, message="描述图片地址长度必须介于 0 和 200 之间")
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
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}