package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.1.1 专属客服
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistHelpCustomerServiceOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String portrait;		// 头像地址	
	private String nickName;		// 昵称	
	private String jobNo;		// 工号	
	private Integer levelVip;		// 等级	
	// private Integer customerNum;		// 客户数	
	private String wxQrCode;		// 微信二维码地址	
	private String mobilePhone;		// 电话	
	private String customerServiceDesc;  // 专属客服职能介绍
	private String serviceWxQrCode;		// 官方客服微信	
	
	
}