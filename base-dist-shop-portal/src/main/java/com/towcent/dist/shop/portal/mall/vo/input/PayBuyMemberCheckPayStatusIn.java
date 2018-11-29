package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.4.8 购买会员支付状态
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class PayBuyMemberCheckPayStatusIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotBlank(message = "payRecordNo不能为空.")
	private String payRecordNo;		// 支付交易号
	
}