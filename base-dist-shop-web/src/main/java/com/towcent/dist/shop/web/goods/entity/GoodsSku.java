package com.towcent.dist.shop.web.goods.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 商品SKUEntity
 * @author yxp
 * @version 2018-07-03
 */
public class GoodsSku extends DataEntity<GoodsSku> {
	
	private static final long serialVersionUID = 1L;
	private String goodsSpecId;		// 商品规格id
	private BigDecimal price;		// 规格价格
	private Integer minNum;		// 最小数量 批发商品专有
	private Integer maxNum;		// 最大数量 批发商品专有
	private Integer merchantId;		// 商户id
	
	public GoodsSku() {
		super();
	}

	public GoodsSku(String id){
		super(id);
	}

	@Length(min=0, max=11, message="商品规格id长度必须介于 0 和 11 之间")
	public String getGoodsSpecId() {
		return goodsSpecId;
	}

	public void setGoodsSpecId(String goodsSpecId) {
		this.goodsSpecId = goodsSpecId;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Length(min=0, max=11, message="最小数量 批发商品专有长度必须介于 0 和 11 之间")
	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}
	
	@Length(min=0, max=11, message="最大数量 批发商品专有长度必须介于 0 和 11 之间")
	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
}