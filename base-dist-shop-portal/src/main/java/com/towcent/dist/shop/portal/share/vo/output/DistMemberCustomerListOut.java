package com.towcent.dist.shop.portal.share.vo.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import java.io.Serializable;

import lombok.Data;

/**
 * 4.0.4 客户管理列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistMemberCustomerListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String type;		// 1:代理商 2:普通用户	
	private String portrait;		// 头像地址	
	private String nickName;		// 昵称	
	private String jobNo;		// 工号	
	private Integer levelVip;		// 等级	
	private Integer customerNum;		// 客户数	
	private String mobilePhone;		// 电话	
	private String wxQrCode;		// 微信二维码地址	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;		// 加入时间	
	
}