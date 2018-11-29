package com.towcent.dist.shop.app.client.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderPaySuccessVo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;//订单ID
	
	private String outBizNo;//交易流水号 收单平台的流水号
	
	private BigDecimal payAmount;//支付金额
	
	private String remark;//备注
	
	private String payType;//支付方式 0:余额支付 1:微信支付 2:支付宝支付 3:微信支付(APP)
}
