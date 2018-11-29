package com.towcent.dist.shop.portal.share.vo.input;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 4.0.2 升级分销
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistMemberUpgradeIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "nickName不能为空.")
	private String nickName;		// 昵称
	
	@NotBlank(message = "mobile不能为空.")
	private String mobile;		// 手机号码
	
	@NotBlank(message = "bindWx不能为空.")
	private String bindWx;		// 微信号（需要和真实微信一致）
	
	@NotBlank(message = "wxQrCode不能为空.")
	private String wxQrCode;		// 微信二维码
	
}