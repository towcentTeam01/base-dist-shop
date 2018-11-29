package com.towcent.dist.shop.web.share.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 商品分享分类Entity
 * @author shiwei
 * @version 2018-08-25
 */
public class ShareCategory extends DataEntity<ShareCategory> {
	
	private static final long serialVersionUID = 1L;
	private String categoryNo;		// 分类编码
	private String shareDesc;		// 分享信息
	private String shareImage;		// 分享图片
	private String shareTitle;		// 分享标题
	private Integer merchantId;		// 商户id 为0时为平台
	private Integer categoryId;		// 商品分类id
	
	public ShareCategory() {
		super();
	}

	public ShareCategory(String id){
		super(id);
	}

	@Length(min=0, max=20, message="分类编码长度必须介于 0 和 20 之间")
	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo;
	}
	
	@Length(min=0, max=512, message="分享信息长度必须介于 0 和 512 之间")
	public String getShareDesc() {
		return shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
	}
	
	@Length(min=0, max=256, message="分享图片长度必须介于 0 和 256 之间")
	public String getShareImage() {
		return shareImage;
	}

	public void setShareImage(String shareImage) {
		this.shareImage = shareImage;
	}
	
	@Length(min=0, max=128, message="分享标题长度必须介于 0 和 128 之间")
	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	
	@Length(min=0, max=11, message="商户id 为0时为平台长度必须介于 0 和 11 之间")
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=11, message="商品分类id长度必须介于 0 和 11 之间")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}