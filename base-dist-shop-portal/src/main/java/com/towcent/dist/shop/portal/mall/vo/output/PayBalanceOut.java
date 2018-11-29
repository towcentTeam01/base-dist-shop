package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.4.1 余额充值
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayBalanceOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean payStatus;		// 支付状态	
	
}