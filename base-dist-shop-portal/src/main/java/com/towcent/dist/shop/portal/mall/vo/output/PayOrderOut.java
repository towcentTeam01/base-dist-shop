package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.4.2 订单支付（公众号/小程序）
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayOrderOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;		// 订单Id	
	
}