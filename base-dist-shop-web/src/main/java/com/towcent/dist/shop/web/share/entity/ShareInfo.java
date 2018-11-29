package com.towcent.dist.shop.web.share.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;
import com.towcent.dist.shop.web.member.entity.SysFrontAccount;

/**
 * 分享记录Entity
 * @author shiwei
 * @version 2018-08-25
 */
public class ShareInfo extends DataEntity<ShareInfo> {
	
	private static final long serialVersionUID = 1L;
	private String jobNo;		// 工号
	private Integer merchantId;		// 商户id
	private Integer productId;		// 商品ID
	private String shareDesc;		// 分享信息
	private String shareImage;		// 分享图片
	private String shareUrl;		// 分享链接
	private String viewNum;		// 浏览量
	private String tradeNum;		// 成交量
	private String shareTitle;		// 分享标题
	protected SysFrontAccount createBy1; // 创建者
	private String goodsNo;

	public SysFrontAccount getCreateBy1() {
		return createBy1;
	}

	public void setCreateBy1(SysFrontAccount createBy) {
		this.createBy1 = createBy;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public ShareInfo() {
		super();
	}

	public ShareInfo(String id){
		super(id);
	}

	@Length(min=1, max=20, message="工号长度必须介于 1 和 20 之间")
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	
	@Length(min=1, max=11, message="商户id长度必须介于 1 和 11 之间")
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=11, message="商品ID长度必须介于 0 和 11 之间")
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=512, message="分享信息长度必须介于 1 和 512 之间")
	public String getShareDesc() {
		return shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}
	
	@Length(min=1, max=256, message="分享图片长度必须介于 1 和 256 之间")
	public String getShareImage() {
		return shareImage;
	}

	public void setShareImage(String shareImage) {
		this.shareImage = shareImage;
	}
	
	@Length(min=1, max=256, message="分享链接长度必须介于 1 和 256 之间")
	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	
	@Length(min=1, max=11, message="浏览量长度必须介于 1 和 11 之间")
	public String getViewNum() {
		return viewNum;
	}

	public void setViewNum(String viewNum) {
		this.viewNum = viewNum;
	}
	
	@Length(min=1, max=11, message="成交量长度必须介于 1 和 11 之间")
	public String getTradeNum() {
		return tradeNum;
	}

	public void setTradeNum(String tradeNum) {
		this.tradeNum = tradeNum;
	}
	
	@Length(min=1, max=128, message="分享标题长度必须介于 1 和 128 之间")
	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	
}