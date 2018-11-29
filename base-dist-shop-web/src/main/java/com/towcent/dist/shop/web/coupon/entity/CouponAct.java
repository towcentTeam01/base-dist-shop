package com.towcent.dist.shop.web.coupon.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 优惠券Entity
 *
 * @author yxp
 * @version 2018-07-06
 */
public class CouponAct extends DataEntity<CouponAct> {

  private static final long serialVersionUID = 1L;
  private String actName; // 活动名称
  private Date startTime; // 开始时间
  private Date endTime; // 结束时间
  private String actType; // 类型
  private BigDecimal limitAmount; // 满减金额
  private BigDecimal amount; // 优惠券金额/折扣率（0-10）
  private Integer totalQty; // 总发行数量
  private Integer residQty; // 剩余数量
  private String openFlag; // 是否开启
  private String actUrl; // 活动页地址
  private String actQrCode; // 活动页地址二维码
  private Integer merchantId; // 商户id

  public CouponAct() {
    super();
  }

  public CouponAct(String id) {
    super(id);
  }

  @Length(min = 0, max = 200, message = "活动名称长度必须介于 0 和 200 之间")
  public String getActName() {
    return actName;
  }

  public void setActName(String actName) {
    this.actName = actName;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  @Length(min = 0, max = 2, message = "类型长度必须介于 0 和 2 之间")
  public String getActType() {
    return actType;
  }

  public void setActType(String actType) {
    this.actType = actType;
  }

  @Length(min = 0, max = 1, message = "是否开启长度必须介于 0 和 1 之间")
  public String getOpenFlag() {
    return openFlag;
  }

  public void setOpenFlag(String openFlag) {
    this.openFlag = openFlag;
  }

  @Length(min = 0, max = 200, message = "活动页地址长度必须介于 0 和 200 之间")
  public String getActUrl() {
    return actUrl;
  }

  public void setActUrl(String actUrl) {
    this.actUrl = actUrl;
  }

  @Length(min = 0, max = 200, message = "活动页地址二维码长度必须介于 0 和 200 之间")
  public String getActQrCode() {
    return actQrCode;
  }

  public void setActQrCode(String actQrCode) {
    this.actQrCode = actQrCode;
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

  public Integer getTotalQty() {
    return totalQty;
  }

  public void setTotalQty(Integer totalQty) {
    this.totalQty = totalQty;
  }

  public Integer getResidQty() {
    return residQty;
  }

  public void setResidQty(Integer residQty) {
    this.residQty = residQty;
  }

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }
}
