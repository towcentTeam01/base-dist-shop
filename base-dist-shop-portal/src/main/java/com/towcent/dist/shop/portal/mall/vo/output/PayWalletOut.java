package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 2.4.4 钱包充值支付（公众号/APP）
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayWalletOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String payRecordNo;		// 支付交易号	
	
}