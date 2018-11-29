package com.towcent.dist.shop.web.config.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 支付配置Entity
 * @author huangtao
 * @version 2018-07-02
 */
public class PayAccount extends DataEntity<PayAccount> {
	
	private static final long serialVersionUID = 1L;
	private String payId;		// 支付账号id
	private String partner;		// 支付合作id,商户id，差不多是支付平台的账号或id
	private String appid;		// 应用id
	private String publicKey;		// 支付公钥，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况
	private String privateKey;		// 支付私钥
	private String notifyUrl;		// 异步回调地址
	private String returnUrl;		// 同步回调地址
	private String seller;		// 收款账号, 针对支付宝
	private String signType;		// 签名类型
	private String inputCharset;		// 枚举值，字符编码 utf-8,gbk等等
	private String payType;		// 支付类型,aliPay：支付宝，wxPay：微信, youdianPay: 友店微信,此处开发者自定义对应com.egzosn.pay.demo.entity.PayType枚举值
	private String msgType;		// 消息类型，text,xml,json
	private String isTest;		// 是否为测试环境
	private Date createTime;		// 创建时间
	private Integer merchantId;		// 商户id
	private String wxRemark;		// 公众号备注
	
	public PayAccount() {
		super();
	}

	public PayAccount(String id){
		super(id);
	}

	@Length(min=1, max=11, message="支付账号id长度必须介于 1 和 11 之间")
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Length(min=0, max=32, message="支付合作id,商户id，差不多是支付平台的账号或id长度必须介于 0 和 32 之间")
	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	@Length(min=0, max=32, message="应用id长度必须介于 0 和 32 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=0, max=1204, message="支付公钥，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5的情况长度必须介于 0 和 1204 之间")
	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}
	
	@Length(min=0, max=2048, message="支付私钥长度必须介于 0 和 2048 之间")
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	@Length(min=0, max=1024, message="异步回调地址长度必须介于 0 和 1024 之间")
	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	@Length(min=0, max=1024, message="同步回调地址长度必须介于 0 和 1024 之间")
	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	@Length(min=0, max=256, message="收款账号, 针对支付宝长度必须介于 0 和 256 之间")
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}
	
	@Length(min=0, max=16, message="签名类型长度必须介于 0 和 16 之间")
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	@Length(min=0, max=16, message="枚举值，字符编码 utf-8,gbk等等长度必须介于 0 和 16 之间")
	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	
	// @PayType枚举值长度必须介于 0 和 16 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=8, message="消息类型，text,xml,json长度必须介于 0 和 8 之间")
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	@Length(min=1, max=1, message="是否为测试环境长度必须介于 1 和 1 之间")
	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=50, message="公众号备注长度必须介于 0 和 50 之间")
	public String getWxRemark() {
		return wxRemark;
	}

	public void setWxRemark(String wxRemark) {
		this.wxRemark = wxRemark;
	}
	
}