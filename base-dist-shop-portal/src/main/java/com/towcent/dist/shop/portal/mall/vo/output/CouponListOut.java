package com.towcent.dist.shop.portal.mall.vo.output;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.2.1 优惠券领取列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class CouponListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 活动id
	private String actName;		// 活动名称	
	private String actType;		// 类型(1:现金券 2:折扣券 3:满减券 )
	private BigDecimal limitAmount;		// 满减金额	
	private BigDecimal amount;		// 优惠券金额/折扣金额	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date startTime;		// 有效期开始时间	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm")
	private Date endTime;		// 有效期结束时间	
	private String status;		// 状态（0：未开始 1：进行中）
	
}