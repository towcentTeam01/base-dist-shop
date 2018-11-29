package com.towcent.dist.shop.portal.mall.vo.output;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.3.2 订单列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;		// 订单Id	
	private String orderNo;		// 订单号	
	private String consigneeName;		// 收货人	
	private String consigneeAddr;		// 收货人地址	
	private String mobilePhone;		// 手机号码	
	private Integer payInter;		// 支付积分	
	private BigDecimal totalAmount;		// 总额	
	private String orderStatus;		// 订单状态(1:已下单 2:已发货3:已签收 4:已完成 5:已取消)	
	private String orderStatuDesc;		// 订单状态描述	
	private String payStatus;		// 支付状态(0:未支付 1:已支付)	
	private String payStatusDesc;		// 支付状态(0:未支付 1:已支付)
	private String isEval;		// 是否评论(1:是0:否)	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;		// 下单时间(yyyy-MM-dd hh:mm:ss)	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date payDate;		// 支付时间(yyyy-MM-dd  hh:mm:ss)	
	private Integer totalQty;		// 商品总数量	
	private BigDecimal freightFee;		// 运费	
	private BigDecimal couponAmount;		// 优惠金额(优惠券金额)	
	private List orderDtl;		// 订单详情	
	
}