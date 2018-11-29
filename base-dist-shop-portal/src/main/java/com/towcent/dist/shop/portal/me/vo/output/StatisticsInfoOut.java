package com.towcent.dist.shop.portal.me.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 3.0.1 我的信息汇总接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class StatisticsInfoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private StatisticsInfoMemberOut member;		// 用户信息
	private StatisticsInfoOrderOut order;		// 订单信息
	private StatisticsInfoWalletOut wallet;		// 钱包信息
	
}