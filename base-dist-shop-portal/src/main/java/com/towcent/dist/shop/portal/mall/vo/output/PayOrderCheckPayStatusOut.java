package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.4.3 检查订单状态
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayOrderCheckPayStatusOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean payStatus;		// 订单支付状态	
	
}