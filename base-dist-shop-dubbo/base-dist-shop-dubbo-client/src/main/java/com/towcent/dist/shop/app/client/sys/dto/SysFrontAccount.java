package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2017-12-28 10:33:12
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysFrontAccount implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键Id.
     */
	private Integer id;
	
	/**
     * 帐号.
     */
	private String account;
	
	/**
     * 手机号.
     */
	private String mobile;
	
	/**
     * 邮箱.
     */
	private String email;
	
	/**
     * 密码.
     */
	private String password;
	
	/**
     * 交易密码.
     */
	private String tradePassword;
	
	/**
	 * 账户余额(可结算).
	 */
	private BigDecimal amount;
	
	/**
	 * 冻结余额.
	 */
	private BigDecimal freezeAmount;
	
	/**
	 * 收入总额.
	 */
	private BigDecimal marginAmount;
	
	/**
	 * 已结算.
	 */
	private BigDecimal settledAmount;
	
	/**
	 * 积分.
	 */
	private Integer inter;
	
	/**
	 * 工号.
	 */
	private String jobNo;
	
	/**
	 * 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员).
	 */
	private String levelVip;
	
	// 等级别名
	private String levelVipDesc;  
	
	/**
	 * 上级用户.
	 */
	private Integer parentId;
	
	/**
	 * 性别(1:男 2:女).
	 */
	private String sex;
	
	/**
     * 昵称.
     */
	private String nickName;
	
	/**
     * 头像地址.
     */
	private String portrait;
	
	/**
     * 关注openid.
     */
	private String openId;
	
	/**
	 * 微信小程序openid
	 */
	private String miniOpenId;
	
	/**
	 * 微信unionid.
	 */
	private String unionId;
	
	/**
     * 绑定的微信号.
     */
	private String bindWx;
	
	/**
	 * 微信二维码地址.
	 */
	private String wxQrCode;
	
	/**
	 * 专属海报地址  现改成推广二维码地址.
	 */
	private String posterUrl;
	
	/**
	 * 小程序二维码.
	 */
	private String miniQrCode;
	
	/**
     * 绑定的微博号.
     */
	private String bindWeibo;
	
	/**
     * 绑定的QQ号.
     */
	private String bindQq;
	
	/**
     * 上次登录时间.
     */
	private Date lastLogintime;
	
	/**
     * 登录次数.
     */
	private Integer loginTimes;
	
	/**
     * 账号状态(0:正常 1:锁定).
     */
	private String status;
	
	/**
     * 用户类型(0:客户).
     */
	private String userType;
	
	/**
     * 备用字段.
     */
	private String remark;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 删除标记(0:正常 1:删除).
     */
	private String delFlag;

	/**
	 * 商户Id.
	 */
	private Integer merchantId;
	
	private String sessionKey;
	// 客户数
	private Integer customerNum;
	// 官方客服微信地址（二维码）
	private String serviceWxQrCode;
	private String customerServiceDesc;  // 专属客服职能介绍
	
	private String shareInviteLink;   // 分享邀请链接
	private String inviteIntros;      // 邀请介绍图片地址（多张图片;分割）
}