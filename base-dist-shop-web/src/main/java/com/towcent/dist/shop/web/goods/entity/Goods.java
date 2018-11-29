package com.towcent.dist.shop.web.goods.entity;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 商品Entity
 *
 * @author yxp
 * @version 2018-07-02
 */
public class Goods extends DataEntity<Goods> {

	private static final long serialVersionUID = 1L;
	private GoodsCategory goodsCategory; // 商品分类id
	private String structureNo; // 分类结构编码
	private String structureName; // 分类结构名称
	private String goodsNo; // 商品编码
	private String goodsName; // 商品名称
	private String goodsType; // 商品类型
	private String goodsBarcode; // 商品条形码
	private String goodsStatus; // 商品状态
	private String brand; // 商品品牌
	private BigDecimal price; // 商品价格
	private Long integral; // 兑换积分
	private BigDecimal minPrice; // 最小商品价格
	private BigDecimal maxPrice; // 最大商品价格
	private BigDecimal costPrice; // 商品成本价
	private String mainUrls; // 商品图片
	private String description; // 商品简介
	private String descPic; // 商品图片描述
	private Integer descPicV; // 图片版本
	private BigDecimal weight; // 重量(kg)
	private Integer sales; // 销量
	private String qrCode; // 二维码地址
	private Integer evaNum; // 评价数量
	private BigDecimal goodEvalRate; // 好评数
	private Integer merchantId; // 商户id
	private String picUrl; // 商品图片

	private List<GoodsSpec> goodsSpecs;

	private List<String> goodsPicList;

	private List<String> goodsDescPicList;// 商品详情图片

	private Integer stock;

	private String unit;

	public Goods() {
		super();
	}

	public Goods(String id) {
		super(id);
	}

	public GoodsCategory getGoodsCategory() {
		return goodsCategory;
	}

	public void setGoodsCategory(GoodsCategory goodsCategory) {
		this.goodsCategory = goodsCategory;
	}

	@Length(min = 0, max = 100, message = "分类结构编码长度必须介于 0 和 100 之间")
	public String getStructureNo() {
		return structureNo;
	}

	public void setStructureNo(String structureNo) {
		this.structureNo = structureNo;
	}

	@Length(min = 0, max = 500, message = "分类结构名称长度必须介于 0 和 500 之间")
	public String getStructureName() {
		return structureName;
	}

	public void setStructureName(String structureName) {
		this.structureName = structureName;
	}

	@Length(min = 0, max = 50, message = "商品编码长度必须介于 0 和 50 之间")
	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	@Length(min = 0, max = 100, message = "商品名称长度必须介于 0 和 100 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	@Length(min = 0, max = 2, message = "商品类型长度必须介于 0 和 2 之间")
	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Length(min = 0, max = 50, message = "商品条形码长度必须介于 0 和 50 之间")
	public String getGoodsBarcode() {
		return goodsBarcode;
	}

	public void setGoodsBarcode(String goodsBarcode) {
		this.goodsBarcode = goodsBarcode;
	}

	@Length(min = 0, max = 1, message = "商品状态长度必须介于 0 和 1 之间")
	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	@Length(min = 0, max = 100, message = "商品品牌长度必须介于 0 和 100 之间")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	@Length(min = 0, max = 1000, message = "商品图片长度必须介于 0 和 1000 之间")
	public String getMainUrls() {
		return mainUrls;
	}

	public void setMainUrls(String mainUrls) {
		this.mainUrls = mainUrls;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescPic() {
		return descPic;
	}

	public void setDescPic(String descPic) {
		this.descPic = descPic;
	}

	public Integer getDescPicV() {
		return descPicV;
	}

	public void setDescPicV(Integer descPicV) {
		this.descPicV = descPicV;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	@Length(min = 0, max = 200, message = "二维码地址长度必须介于 0 和 200 之间")
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public Integer getEvaNum() {
		return evaNum;
	}

	public void setEvaNum(Integer evaNum) {
		this.evaNum = evaNum;
	}

	public BigDecimal getGoodEvalRate() {
		return goodEvalRate;
	}

	public void setGoodEvalRate(BigDecimal goodEvalRate) {
		this.goodEvalRate = goodEvalRate;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public List<GoodsSpec> getGoodsSpecs() {
		return goodsSpecs;
	}

	public void setGoodsSpecs(List<GoodsSpec> goodsSpecs) {
		this.goodsSpecs = goodsSpecs;
	}

	public List<String> getGoodsPicList() {
		return goodsPicList;
	}

	public void setGoodsPicList(List<String> goodsPicList) {
		this.goodsPicList = goodsPicList;
	}

	public List<String> getGoodsDescPicList() {
		return goodsDescPicList;
	}

	public void setGoodsDescPicList(List<String> goodsDescPicList) {
		this.goodsDescPicList = goodsDescPicList;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
}
