package com.towcent.dist.shop.portal.sys.vo.input;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 1.0.1 登录接口
 * @author huangtao
 * @version 0.0.3
 */
@Data
public class LoginIn extends BaseParam {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "code不能为空.")
	private String code;

	@NotBlank(message = "encryptedData不能为空.")
	private String encryptedData;

	@NotBlank(message = "iv不能为空.")
	private String iv;
	
	private String jobNo; // 邀请工号

}