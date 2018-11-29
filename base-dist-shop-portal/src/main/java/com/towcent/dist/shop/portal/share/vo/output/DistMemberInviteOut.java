package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.0.5 邀请好友
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistMemberInviteOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String inviteIntros;		// 介绍图片(多张图分号；分割)
	private String portrait;		// 头像地址	
	private String nickName;		// 昵称	
	private String jobNo;		// 工号	
	private Integer levelVip;		// 等级	
	private Integer customerNum;		// 客户数	
	private String wxQrCode;		// 微信二维码地址	
	private String mobilePhone;		// 电话	
	private String shareInviteLink;	// 分享链接（邀请链接）	
	
}