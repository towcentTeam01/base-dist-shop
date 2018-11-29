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
public class StatisticsInfoOrderOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer totalOrderNum;		// 全部订单数
	private Integer orderToPayNum;		// 待付款订单数	
	private Integer orderToReceNum;		// 待收货订单数	
	private Integer orderToEvalNum;		// 待评价订单数	
	private Integer orderToFinishNum;		// 已完成订单数	

}