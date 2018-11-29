package com.towcent.dist.shop.portal.mall.vo.input;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.4.4 钱包充值支付（公众号/APP）
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayWalletIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "rechargeAmount不能为空.")
	private BigDecimal rechargeAmount;		// 充值金额
	
	private String code;		// 微信code
	
	private String openId;		// 微信openId
	
	@NotBlank(message = "payType不能为空.")
	private String payType;		// 1:微信支付 2:支付宝支付
	
}