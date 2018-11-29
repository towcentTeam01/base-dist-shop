package com.towcent.dist.shop.portal.me.vo.input;


import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 3.3.5 手动标记提现已处理
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WalletWithdrawMarkIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "applyId不能为空.")
	private Integer applyId;		// 提现申请记录Id
	
}