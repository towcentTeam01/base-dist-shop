package com.towcent.dist.shop.portal.mall.vo.input;


import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

/**
 * 2.4.9 物流跟踪
 * @author shiwei
 * @version 0.0.1
 */
@Data
public class LogisTraceIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotBlank(message = "orderId不能为空.")
	private String orderId;		// 订单ID
}