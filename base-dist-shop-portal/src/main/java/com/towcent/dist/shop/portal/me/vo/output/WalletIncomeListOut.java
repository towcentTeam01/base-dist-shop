package com.towcent.dist.shop.portal.me.vo.output;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * 3.3.1 钱包/积分明细
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WalletIncomeListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String dealNo;		// 交易号	
	private String type;		// 类型(0:支出，1:收入)	
	private BigDecimal amount;		// 此次交易金额	
	private BigDecimal orderAmount;		// 订单金额	
	private String orderTitle;		// 订单title(默认商品名称)	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;		// 操作时间	
	private String remarks;		// 操作类型	
	
}