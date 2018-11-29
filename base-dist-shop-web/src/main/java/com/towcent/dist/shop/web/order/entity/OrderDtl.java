package com.towcent.dist.shop.web.order.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 订单明细Entity
 * @author huangtao
 * @version 2018-07-02
 */
public class OrderDtl extends DataEntity<OrderDtl> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单id
	private String goodsId;		// 商品id
	private String goodsName;		// 商品名称
	private String goodsPicUrl;		// 商品图片
	private String spec;		// 规格
	private String qty;		// 数量
	private String price;		// 单价(元)
	private String amount;		// 金额=数量*单价(元)
	private String integral;		// 兑换积分
	private String evalFlag;		// 评价标识(0:未评价 1:已评价) yes_no
	private Integer merchantId;		// 商户id
	private String specId;		// 规格id
	
	public OrderDtl() {
		super();
	}

	public OrderDtl(String id){
		super(id);
	}

	@Length(min=0, max=11, message="订单id长度必须介于 0 和 11 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=11, message="商品id长度必须介于 0 和 11 之间")
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	@Length(min=0, max=100, message="商品名称长度必须介于 0 和 100 之间")
	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	@Length(min=0, max=100, message="商品图片长度必须介于 0 和 100 之间")
	public String getGoodsPicUrl() {
		return goodsPicUrl;
	}

	public void setGoodsPicUrl(String goodsPicUrl) {
		this.goodsPicUrl = goodsPicUrl;
	}
	
	@Length(min=0, max=100, message="规格长度必须介于 0 和 100 之间")
	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	@Length(min=0, max=11, message="数量长度必须介于 0 和 11 之间")
	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}
	
	@Length(min=0, max=1, message="评价标识(0:未评价 1:已评价) yes_no长度必须介于 0 和 1 之间")
	public String getEvalFlag() {
		return evalFlag;
	}

	public void setEvalFlag(String evalFlag) {
		this.evalFlag = evalFlag;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=11, message="规格id长度必须介于 0 和 11 之间")
	public String getSpecId() {
		return specId;
	}

	public void setSpecId(String specId) {
		this.specId = specId;
	}
	
}