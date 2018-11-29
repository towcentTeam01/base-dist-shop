package com.towcent.dist.shop.portal.share.vo.output;

import java.math.BigDecimal;

import java.io.Serializable;

import lombok.Data;

/**
 * 4.0.1 分销用户信息
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistMemberInfoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nickName;		// 昵称	
	private String post;		// 职务	
	private String jobNo;		// 工号	
	private BigDecimal marginAmount;		// 收入总额	
	private BigDecimal settledAmount;		// 已结算	
	private BigDecimal amount;		// 账户余额(可结算)	
	private String portrait;    // 头像
	
}