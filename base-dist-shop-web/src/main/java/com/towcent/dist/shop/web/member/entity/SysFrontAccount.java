package com.towcent.dist.shop.web.member.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 会员Entity
 * @author huangtao
 * @version 2018-07-07
 */
public class SysFrontAccount extends DataEntity<SysFrontAccount> {
	
	private static final long serialVersionUID = 1L;
	private String account;		// 帐号
	private String mobile;		// 手机号
	private String email;		// 邮箱
	private String password;		// 密码
	private String tradePassword;		// 交易密码
	private String amount;		// 账户余额(可结算)
	private String freezeAmount;		// 冻结余额
	private String marginAmount;		// 收入总额
	private String settledAmount;		// 已结算
	private String inter;		// 积分
	private String jobNo;		// 工号
	private String levelVip;		// 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)
	private SysFrontAccount parent;		// 上级用户
	private String sex;		// 性别(1:男 2:女)
	private String nickName;		// 昵称
	private String portrait;		// 头像地址
	private String openId;		// 关注openid
	private String miniOpenId;		// 微信小程序openid
	private String unionId;		// 微信unionid
	private String bindWx;		// 绑定的微信号
	private String wxQrCode;		// 微信二维码地址
	private String posterUrl;		// 专属海报地址
	private String miniQrCode;		// 小程序二维码
	private String bindWeibo;		// 绑定的微博号
	private String bindQq;		// 绑定的QQ号
	private Date lastLogintime;		// 上次登录时间
	private String loginTimes;		// 登录次数
	private String status;		// 账号状态(0:正常 1:锁定)
	private String userType;		// 用户类型(0:客户)
	private String remark;		// 备用字段
	private Integer merchantId;		// 商户id
	private Date beginCreateDate;		// 开始 创建时间
	private Date endCreateDate;		// 结束 创建时间
	
	private String tabFlag;       // (1:代理商 2:普通用户)
	
	public SysFrontAccount() {
		super();
	}

	public SysFrontAccount(String id){
		super(id);
	}

	@Length(min=1, max=20, message="帐号长度必须介于 1 和 20 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=20, message="手机号长度必须介于 0 和 20 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=1, max=32, message="密码长度必须介于 1 和 32 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=32, message="交易密码长度必须介于 0 和 32 之间")
	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(String freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	
	public String getMarginAmount() {
		return marginAmount;
	}

	public void setMarginAmount(String marginAmount) {
		this.marginAmount = marginAmount;
	}
	
	public String getSettledAmount() {
		return settledAmount;
	}

	public void setSettledAmount(String settledAmount) {
		this.settledAmount = settledAmount;
	}
	
	@Length(min=0, max=11, message="积分长度必须介于 0 和 11 之间")
	public String getInter() {
		return inter;
	}

	public void setInter(String inter) {
		this.inter = inter;
	}
	
	@Length(min=0, max=20, message="工号长度必须介于 0 和 20 之间")
	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}
	
	@Length(min=1, max=2, message="用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)长度必须介于 1 和 2 之间")
	public String getLevelVip() {
		return levelVip;
	}

	public void setLevelVip(String levelVip) {
		this.levelVip = levelVip;
	}
	
	@JsonBackReference
	public SysFrontAccount getParent() {
		return parent;
	}

	public void setParent(SysFrontAccount parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=1, message="性别(1:男 2:女)长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=64, message="昵称长度必须介于 0 和 64 之间")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Length(min=0, max=500, message="头像地址长度必须介于 0 和 500 之间")
	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	
	@Length(min=0, max=60, message="关注openid长度必须介于 0 和 60 之间")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Length(min=0, max=60, message="微信小程序openid长度必须介于 0 和 60 之间")
	public String getMiniOpenId() {
		return miniOpenId;
	}

	public void setMiniOpenId(String miniOpenId) {
		this.miniOpenId = miniOpenId;
	}
	
	@Length(min=0, max=60, message="微信unionid长度必须介于 0 和 60 之间")
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	
	@Length(min=0, max=32, message="绑定的微信号长度必须介于 0 和 32 之间")
	public String getBindWx() {
		return bindWx;
	}

	public void setBindWx(String bindWx) {
		this.bindWx = bindWx;
	}
	
	@Length(min=0, max=200, message="微信二维码地址长度必须介于 0 和 200 之间")
	public String getWxQrCode() {
		return wxQrCode;
	}

	public void setWxQrCode(String wxQrCode) {
		this.wxQrCode = wxQrCode;
	}
	
	@Length(min=0, max=200, message="专属海报地址长度必须介于 0 和 200 之间")
	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	
	@Length(min=0, max=200, message="小程序二维码长度必须介于 0 和 200 之间")
	public String getMiniQrCode() {
		return miniQrCode;
	}

	public void setMiniQrCode(String miniQrCode) {
		this.miniQrCode = miniQrCode;
	}
	
	@Length(min=0, max=32, message="绑定的微博号长度必须介于 0 和 32 之间")
	public String getBindWeibo() {
		return bindWeibo;
	}

	public void setBindWeibo(String bindWeibo) {
		this.bindWeibo = bindWeibo;
	}
	
	@Length(min=0, max=12, message="绑定的QQ号长度必须介于 0 和 12 之间")
	public String getBindQq() {
		return bindQq;
	}

	public void setBindQq(String bindQq) {
		this.bindQq = bindQq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLogintime() {
		return lastLogintime;
	}

	public void setLastLogintime(Date lastLogintime) {
		this.lastLogintime = lastLogintime;
	}
	
	@Length(min=0, max=11, message="登录次数长度必须介于 0 和 11 之间")
	public String getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}
	
	@Length(min=0, max=2, message="账号状态(0:正常 1:锁定)长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=2, message="用户类型(0:客户)长度必须介于 0 和 2 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=500, message="备用字段长度必须介于 0 和 500 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	
	public Date getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(Date beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public String getTabFlag() {
		return tabFlag;
	}

	public void setTabFlag(String tabFlag) {
		this.tabFlag = tabFlag;
	}
		
}