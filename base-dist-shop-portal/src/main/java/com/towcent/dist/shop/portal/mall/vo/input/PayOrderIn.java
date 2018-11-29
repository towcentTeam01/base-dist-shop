package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.4.2 订单支付（公众号/小程序）
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayOrderIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "orderId不能为空.")
	private Integer orderId;		// 订单Id
	
	@NotBlank(message = "payType不能为空.")
	private String payType;		// 1:微信支付 2:支付宝支付
	
	private String openId;		// 微信openId
	
	private String code;		// 微信code
	
}