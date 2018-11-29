package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.3.7 订单确认收货
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderReceiptIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "orderId不能为空.")
	private Integer orderId;		// 订单Id
	
}