package com.towcent.dist.shop.portal.mall.vo.input;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.4.7 购买会员支付（公众号/APP）
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayBuyMemberIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "buyAmount不能为空.")
	private BigDecimal buyAmount;		// 会员购买金额
	
	@NotBlank(message = "bizType不能为空.")
	private String bizType;     // 业务类型(2:升级铂金 3:升级钻石)
	
	private String code;		// 微信code
	
	private String openId;		// 微信openId
	
	@NotBlank(message = "payType不能为空.")
	private String payType;		// 1:微信支付 2:支付宝支付
	
}