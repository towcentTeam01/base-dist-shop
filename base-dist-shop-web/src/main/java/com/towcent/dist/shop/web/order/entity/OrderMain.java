package com.towcent.dist.shop.web.order.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.towcent.base.sc.web.common.persistence.DataEntity;
import com.towcent.dist.shop.web.member.entity.SysFrontAccount;

/**
 * 订单列表Entity
 * @author huangtao
 * @version 2018-07-02
 */
public class OrderMain extends DataEntity<OrderMain> {
	
	private static final long serialVersionUID = 1L;
	private String orderType;		// 订单类型(0:普通订单)
	private String orderNo;		// 订单号
	private String orderStatus;		// 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
	private String payStatus;		// 支付状态(0:未支付 1:已支付)
	private String payWay;		// 付款方式(1:在线支付 2:线下付款)
	private String totalAmount;		// 总金额(商品总金额-优惠金额+运费)
	private String amount;		// 余额支付金额
	private String payAmount;		// 线上实付金额(总)
	private String payInter;		// 积分支付数额
	private String couponAmount;		// 优惠金额
	private String consigneeName;		// 收货人姓名
	private String mobilePhone;		// 收货人联系方式(手机或者电话至少填一项)
	private String consigneeAddr;		// 收货详细地址(收货地址)
	private String totalQty;		// 商品总数量
	private String freightFee;		// 运费
	private String freightNumber;		// 运单号
	private String logisticsNo;		// 物流公司编码
	private String logisticsName;		// 物流公司名称
	private String isEval;		// 是否评论(1:是0:否)
	private String saleAfterRemarks;		// 售后备注
	private Date saleAfterDate;		// 售后申请时间
	private Date payDate;		// 支付时间
	private Date deliveryTime;		// 发货时间
	private Integer merchantId;		// 商户id
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	private Date beginPayDate;		// 开始 支付时间
	private Date endPayDate;		// 结束 支付时间
	
	protected SysFrontAccount createBy1; // 创建者
	protected SysFrontAccount updateBy1; // 更新者
	
	public OrderMain() {
		super();
	}

	public OrderMain(String id){
		super(id);
	}

	@Length(min=1, max=2, message="订单类型(0:普通订单)长度必须介于 1 和 2 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=20, message="订单号长度必须介于 0 和 20 之间")
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Length(min=1, max=2, message="订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)长度必须介于 1 和 2 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Length(min=1, max=1, message="支付状态(0:未支付 1:已支付)长度必须介于 1 和 1 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	@Length(min=0, max=1, message="付款方式(1:在线支付 2:线下付款)长度必须介于 0 和 1 之间")
	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	
	public String getPayInter() {
		return payInter;
	}

	public void setPayInter(String payInter) {
		this.payInter = payInter;
	}
	
	public String getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(String couponAmount) {
		this.couponAmount = couponAmount;
	}
	
	@Length(min=0, max=20, message="收货人姓名长度必须介于 0 和 20 之间")
	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	
	@Length(min=0, max=20, message="收货人联系方式(手机或者电话至少填一项)长度必须介于 0 和 20 之间")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	@Length(min=0, max=200, message="收货详细地址(收货地址)长度必须介于 0 和 200 之间")
	public String getConsigneeAddr() {
		return consigneeAddr;
	}

	public void setConsigneeAddr(String consigneeAddr) {
		this.consigneeAddr = consigneeAddr;
	}
	
	@Length(min=0, max=11, message="商品总数量长度必须介于 0 和 11 之间")
	public String getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}
	
	public String getFreightFee() {
		return freightFee;
	}

	public void setFreightFee(String freightFee) {
		this.freightFee = freightFee;
	}
	
	@Length(min=0, max=50, message="运单号长度必须介于 0 和 50 之间")
	public String getFreightNumber() {
		return freightNumber;
	}

	public void setFreightNumber(String freightNumber) {
		this.freightNumber = freightNumber;
	}
	
	@Length(min=0, max=20, message="物流公司id长度必须介于 0 和 20 之间")
	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	
	@Length(min=0, max=200, message="物流公司名称长度必须介于 0 和 200 之间")
	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	
	@Length(min=0, max=1, message="是否评论(1:是0:否)长度必须介于 0 和 1 之间")
	public String getIsEval() {
		return isEval;
	}

	public void setIsEval(String isEval) {
		this.isEval = isEval;
	}
	
	@Length(min=0, max=2000, message="售后备注长度必须介于 0 和 2000 之间")
	public String getSaleAfterRemarks() {
		return saleAfterRemarks;
	}

	public void setSaleAfterRemarks(String saleAfterRemarks) {
		this.saleAfterRemarks = saleAfterRemarks;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSaleAfterDate() {
		return saleAfterDate;
	}

	public void setSaleAfterDate(Date saleAfterDate) {
		this.saleAfterDate = saleAfterDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
		
	public Date getBeginPayDate() {
		return beginPayDate;
	}

	public void setBeginPayDate(Date beginPayDate) {
		this.beginPayDate = beginPayDate;
	}
	
	public Date getEndPayDate() {
		return endPayDate;
	}

	public void setEndPayDate(Date endPayDate) {
		this.endPayDate = endPayDate;
	}

	public SysFrontAccount getCreateBy1() {
		return createBy1;
	}

	public void setCreateBy1(SysFrontAccount createBy1) {
		this.createBy1 = createBy1;
	}

	public SysFrontAccount getUpdateBy1() {
		return updateBy1;
	}

	public void setUpdateBy1(SysFrontAccount updateBy1) {
		this.updateBy1 = updateBy1;
	}
		
}