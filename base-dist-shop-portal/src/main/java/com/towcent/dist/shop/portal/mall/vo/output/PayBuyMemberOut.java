package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.4.7 购买会员支付（公众号/APP）
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayBuyMemberOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String payRecordNo;		// 支付交易号	
	
}