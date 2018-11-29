package com.towcent.dist.shop.portal.sys.vo.output;

import java.io.Serializable;

import lombok.Data;

/**
 * 1.0.9 用户基本信息
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class MemberAccountBaseInfoOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;        // 账号Id
	private String account;        // 账号
	private String sex;            // 性别(1:男 2:女)
	private String nickName;		// 昵称	
	private String portrait;		// 头像	
	private String appType;         // 账号类型(0:客户)
	private String miniQrCode;	    // 小程序二维码
	private String wxQrCode;	    // 微信二维码
	private String levelVip;	    // 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)
	private String levelVipDesc;    // 等级别名  
	private String jobNo;	        // 工号
	private String mobile;
	private String bindWx;
	private String email;
	private String status;          // 账号状态(0:正常 1:锁定)
	private String remark;
	
}