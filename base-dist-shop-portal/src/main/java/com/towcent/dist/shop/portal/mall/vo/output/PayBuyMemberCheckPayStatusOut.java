package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.4.8 购买会员支付状态
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayBuyMemberCheckPayStatusOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Boolean payStatus;		// 支付状态	
	
}