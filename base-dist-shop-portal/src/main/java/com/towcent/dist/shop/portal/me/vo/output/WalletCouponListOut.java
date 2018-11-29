package com.towcent.dist.shop.portal.me.vo.output;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * 3.3.2 我的优惠券列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WalletCouponListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 优惠券Id	
	private String actName;		// 活动名称	
	private String type;		// 类型(1:现金券 2:折扣券 3:满减券 )
	private BigDecimal limitAmount;		// 满减金额	
	private BigDecimal amount;		// 优惠券金额/折扣金额	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;		// 有效期开始时间	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;		// 有效期结束时间	
	private Integer actId;		// 活动id	
	private String status;		// 状态（0：未使用 1：已使用 2：已过期）	
	
}