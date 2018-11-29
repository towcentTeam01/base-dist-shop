package com.towcent.dist.shop.portal.mall.vo.input;


import com.towcent.dist.shop.portal.common.vo.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 2.3.4 订单删除
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderDetailIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "orderId不能为空.")
	private Integer orderId;		// 订单Id
	
}