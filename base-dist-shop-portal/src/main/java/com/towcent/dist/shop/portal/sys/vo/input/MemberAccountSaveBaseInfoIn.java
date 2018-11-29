package com.towcent.dist.shop.portal.sys.vo.input;


import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 1.0.8 账号基本信息保存
 * @author licheng
 * @version 0.0.1
 */
@Data
public class MemberAccountSaveBaseInfoIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	private String account;		    // 账号
	
	private String sex;		        // 性别(1:男 2:女)
	
	private String nickName;		// 昵称
	
	private String portrait;		// 头像地址
	
	private String mobile;          // 联系方式
	
	private String bindWx;          // 绑定微信
	
	private String email;           // 邮箱
	
	private String remark;          // 备注
	
	
	
}