package com.towcent.dist.shop.portal.me.vo.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;

import lombok.Data;

/**
 * 3.3.4 提现记录列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WalletWithdrawListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 提现记录Id	
	private BigDecimal amount;		// 提现金额	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;		// 申请时间	
	private String status;		// 提现状态（0：已申请 1：处理中 2：已处理）	
	private String statusDesc;	// 提现状态描述

}