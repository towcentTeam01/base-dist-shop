package com.towcent.dist.shop.web.coupon.entity;

import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;

import java.math.BigDecimal;

/**
 * 优惠券Entity
 *
 * @author yxp
 * @version 2018-07-06
 */
public class CouponClaim extends DataEntity<CouponClaim> {

  private static final long serialVersionUID = 1L;
  private CouponAct couponAct; // 活动
  private SysFrontAccount user; // 会员id
  private String nickName; // 会员昵称
  private BigDecimal limitAmount; // 满减金额
  private BigDecimal amount; // 优惠券金额/折扣金额
  private String useFlag; // 使用状态
  private Integer merchantId; // 商户id
  private String orderId; // 订单id

  public CouponClaim() {
    super();
  }

  public CouponClaim(String id) {
    super(id);
  }

  public CouponAct getCouponAct() {
    return couponAct;
  }

  public void setCouponAct(CouponAct couponAct) {
    this.couponAct = couponAct;
  }

  @Length(min = 0, max = 64, message = "会员昵称长度必须介于 0 和 64 之间")
  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  @Length(min = 1, max = 2, message = "使用状态长度必须介于 1 和 2 之间")
  public String getUseFlag() {
    return useFlag;
  }

  public void setUseFlag(String useFlag) {
    this.useFlag = useFlag;
  }

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  @Length(min = 0, max = 11, message = "订单id长度必须介于 0 和 11 之间")
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public SysFrontAccount getUser() {
    return user;
  }

  public void setUser(SysFrontAccount user) {
    this.user = user;
  }

  public BigDecimal getLimitAmount() {
    return limitAmount;
  }

  public void setLimitAmount(BigDecimal limitAmount) {
    this.limitAmount = limitAmount;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
