package com.towcent.dist.shop.portal.me.vo.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 3.3.3 提现申请
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WalletWithdrawApplyIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "amount不能为空.")
	private BigDecimal amount;		// 提现金额
	
}