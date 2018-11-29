package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.2.2 领取优惠券
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class CouponDrawIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "actId不能为空.")
	private Integer actId;		// 活动Id
	
}