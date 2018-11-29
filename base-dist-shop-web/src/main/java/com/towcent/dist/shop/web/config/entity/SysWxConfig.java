package com.towcent.dist.shop.web.config.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 公众号配置Entity
 * @author huangtao
 * @version 2018-07-02
 */
public class SysWxConfig extends DataEntity<SysWxConfig> {
	
	private static final long serialVersionUID = 1L;
	private String appid;		// 应用id
	private String wxAppsecret;		// 微信公众号秘钥
	private String wxToken;		// 微信公众号Token
	private String wxAeskey;		// 微信消息加解密秘钥
	private String wxRemark;		// 公众号备注
	private String isService;		// 是否是服务号(字典yes_no  1:是 0:否)
	private Integer merchantId;		// 商户Id
	
	public SysWxConfig() {
		super();
	}

	public SysWxConfig(String id){
		super(id);
	}

	@Length(min=0, max=64, message="应用id长度必须介于 0 和 64 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=0, max=64, message="微信公众号秘钥长度必须介于 0 和 64 之间")
	public String getWxAppsecret() {
		return wxAppsecret;
	}

	public void setWxAppsecret(String wxAppsecret) {
		this.wxAppsecret = wxAppsecret;
	}
	
	@Length(min=0, max=64, message="微信公众号Token长度必须介于 0 和 64 之间")
	public String getWxToken() {
		return wxToken;
	}

	public void setWxToken(String wxToken) {
		this.wxToken = wxToken;
	}
	
	@Length(min=0, max=64, message="微信消息加解密秘钥长度必须介于 0 和 64 之间")
	public String getWxAeskey() {
		return wxAeskey;
	}

	public void setWxAeskey(String wxAeskey) {
		this.wxAeskey = wxAeskey;
	}
	
	@Length(min=0, max=64, message="公众号备注长度必须介于 0 和 64 之间")
	public String getWxRemark() {
		return wxRemark;
	}

	public void setWxRemark(String wxRemark) {
		this.wxRemark = wxRemark;
	}
	
	@Length(min=0, max=2, message="是否是服务号(字典yes_no  1:是 0:否)长度必须介于 0 和 2 之间")
	public String getIsService() {
		return isService;
	}

	public void setIsService(String isService) {
		this.isService = isService;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}