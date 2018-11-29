package com.towcent.dist.shop.portal.share.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 4.1.6 商品分享
 * @author shiwei
 * @version 0.0.1
 */
@Data
public class DistProductIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotBlank(message = "productId不能为空.")
	private String productId;		// 商品ID
	
}