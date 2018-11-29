package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Generator
 * @date 2015-06-24 17:45:26
 * @version 1.0.0
 * @copyright facegarden.com
 */
public class GoodsPicDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID.
	 */
	private Long id;

	
	/**
	 * picSize.
	 */
	private Long picSize;
	
	/**
	 * 商品编码.
	 */
	private String goodsNo;

	/**
	 * 图片名称.
	 */
	private String picName;

	/**
	 * 图片相对路径.
	 */
	private String picUrl;

	/**
	 * 图片类型（0窗口图、1客观图、2、参考图）.
	 */
	private String picType;

	/**
	 * 删除标记 (0：正常；1：删除).
	 */
	private String delFlag;

	/**
	 * 创建人.
	 */
	private String createBy;

	/**
	 * 创建日期.
	 */
	private Date createDate;

	/**
	 * 修改人.
	 */
	private String updateBy;

	/**
	 * 修改日期.
	 */
	private Date updateDate;

	/**
	 * 排序号.
	 */
	private int sort;
	
	/**
	 * 审核状态. (0：待审核 1：审核通过 2：审核不通过)
	 */	
	private String picStatus;
	
	/**
	 * 大图链接
	 */
	private String picMax;
	
	public String getPicStatus() {
		return picStatus;
	}

	public void setPicStatus(String picStatus) {
		this.picStatus = picStatus;
	}

	/**
	 * 
	 * {@linkplain #id}
	 *
	 * @return the value of goods_pic.id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * {@linkplain #id}
	 * 
	 * @param id
	 *            the value for goods_pic.id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * {@linkplain #goodsNo}
	 *
	 * @return the value of goods_pic.goods_no
	 */
	public String getGoodsNo() {
		return goodsNo;
	}

	/**
	 * {@linkplain #goodsNo}
	 * 
	 * @param goodsNo
	 *            the value for goods_pic.goods_no
	 */
	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo == null ? null : goodsNo.trim();
	}

	/**
	 * 
	 * {@linkplain #picName}
	 *
	 * @return the value of goods_pic.pic_name
	 */
	public String getPicName() {
		return picName;
	}

	/**
	 * {@linkplain #picName}
	 * 
	 * @param picName
	 *            the value for goods_pic.pic_name
	 */
	public void setPicName(String picName) {
		this.picName = picName == null ? null : picName.trim();
	}

	/**
	 * 
	 * {@linkplain #picUrl}
	 *
	 * @return the value of goods_pic.pic_url
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * {@linkplain #picUrl}
	 * 
	 * @param picUrl
	 *            the value for goods_pic.pic_url
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl == null ? null : picUrl.trim();
	}

	/**
	 * 
	 * {@linkplain #picType}
	 *
	 * @return the value of goods_pic.pic_type
	 */
	public String getPicType() {
		return picType;
	}

	/**
	 * {@linkplain #picType}
	 * 
	 * @param picType
	 *            the value for goods_pic.pic_type
	 */
	public void setPicType(String picType) {
		this.picType = picType == null ? null : picType.trim();
	}

	/**
	 * 
	 * {@linkplain #delFlag}
	 *
	 * @return the value of goods_pic.del_flag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * {@linkplain #delFlag}
	 * 
	 * @param delFlag
	 *            the value for goods_pic.del_flag
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}

	/**
	 * 
	 * {@linkplain #createBy}
	 *
	 * @return the value of goods_pic.create_by
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * {@linkplain #createBy}
	 * 
	 * @param createBy
	 *            the value for goods_pic.create_by
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	/**
	 * 
	 * {@linkplain #createDate}
	 *
	 * @return the value of goods_pic.create_date
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * {@linkplain #createDate}
	 * 
	 * @param createDate
	 *            the value for goods_pic.create_date
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 
	 * {@linkplain #updateBy}
	 *
	 * @return the value of goods_pic.update_by
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/**
	 * {@linkplain #updateBy}
	 * 
	 * @param updateBy
	 *            the value for goods_pic.update_by
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}

	/**
	 * 
	 * {@linkplain #updateDate}
	 *
	 * @return the value of goods_pic.update_date
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * {@linkplain #updateDate}
	 * 
	 * @param updateDate
	 *            the value for goods_pic.update_date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 
	 * {@linkplain #sort}
	 *
	 * @return the value of goods_pic.sort
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * {@linkplain #sort}
	 * 
	 * @param sort
	 *            the value for goods_pic.sort
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	public Long getPicSize() {
		return picSize;
	}

	public void setPicSize(Long picSize) {
		this.picSize = picSize;
	}

	public String getPicMax() {
		return picMax;
	}

	public void setPicMax(String picMax) {
		this.picMax = picMax;
	}
	
}