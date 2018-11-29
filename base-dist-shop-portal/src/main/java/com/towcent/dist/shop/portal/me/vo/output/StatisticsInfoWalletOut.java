package com.towcent.dist.shop.portal.me.vo.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 3.0.1 我的信息汇总接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class StatisticsInfoWalletOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal marginAmount;		// 收入总额
	private BigDecimal settledAmount;		// 已结算	
	private BigDecimal amount;		// 账户余额(可结算)	
	private Integer integral;		// 积分	
	
}