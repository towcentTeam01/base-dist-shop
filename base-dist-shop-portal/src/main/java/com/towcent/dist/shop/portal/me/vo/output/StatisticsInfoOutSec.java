package com.towcent.dist.shop.portal.me.vo.output;

import java.math.BigDecimal;

import java.io.Serializable;

import lombok.Data;

/**
 * 3.0.1 我的信息汇总接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class StatisticsInfoOutSec implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nickName;		// 昵称	
	private Integer levelVip;		// 等级	
	private String levelDesc;		// 等级	
	private String headimgurl;		// 图像	
	private Integer totalOrderNum;		// 全部订单数	
	private Integer orderToPayNum;		// 待付款订单数	
	private Integer orderToReceNum;		// 待收货订单数	
	private Integer orderToEvalNum;		// 待评价订单数	
	private Integer orderToFinishNum;		// 已完成订单数	
	private BigDecimal marginAmount;		// 收入总额	
	private BigDecimal settledAmount;		// 已结算	
	private BigDecimal amount;		// 账户余额(可结算)	
	private Integer integral;		// 积分	
	
}