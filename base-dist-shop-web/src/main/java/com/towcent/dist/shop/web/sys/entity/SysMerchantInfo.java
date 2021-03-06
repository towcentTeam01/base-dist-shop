package com.towcent.dist.shop.web.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.towcent.base.sc.web.common.persistence.DataEntity;
import com.towcent.base.sc.web.modules.sys.entity.Area;

/**
 * 商户信息Entity
 *
 * @author yxp
 * @version 2018-07-19
 */
public class SysMerchantInfo extends DataEntity<SysMerchantInfo> {

    private static final long serialVersionUID = 1L;
    private String sysUserId;        // 系统账号
    private String houseNumber;        // 店铺编码
    private String shopName;        // 店铺名称
    private String shopType;        // 店铺类别
    private String applyStatus;        // 状态
    private String phone;        // 联系电话
    private String logo;        // 商家logo
    private Area province;        // 省(开店地址)
    private Area city;        // 市(开店地址)
    private Area district;        // 区(开店地址)
    private String street;        // 街道
    private String address;        // 详细地址
    private String longtitude;        // 经度
    private String latitude;        // 纬度
    private String email;        // 邮箱（联系人）
    private String qq;        // qq号码（联系人）
    private String wxCode;        // 微信号码（联系人）
    private String wxQrCode;        // 微信二维码地址（联系人）
    private String servicePhone;        // 客服电话
    private String qrCode;        // 二维码地址(小店的二维码)
    private String selfEmployedName;        // 法人姓名
    private String idCardFrontUrl;        // 身份证正面
    private String applyIdCard;        // 身份证号码
    private String bizLicUrl;        // 营业执照照片地址
    private String coverUrl;        // 封面图
    private String idCardBackUrl;        // 身份证反面
    private String handIdCardUrl;        // 手持身份证照片地址
    private String alias;        // 标签(多个;分割)
    private String enabledFlag;        // 启用标识

    public SysMerchantInfo() {
        super();
    }

    public SysMerchantInfo(String id) {
        super(id);
    }

    @Length(min = 1, max = 11, message = "系统账号长度必须介于 1 和 11 之间")
    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Length(min = 0, max = 64, message = "店铺编码长度必须介于 0 和 64 之间")
    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Length(min = 0, max = 200, message = "店铺名称长度必须介于 0 和 200 之间")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Length(min = 0, max = 1, message = "店铺类别长度必须介于 0 和 1 之间")
    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    @Length(min = 0, max = 1, message = "状态长度必须介于 0 和 1 之间")
    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus;
    }

    @Length(min = 0, max = 20, message = "联系电话长度必须介于 0 和 20 之间")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Length(min = 0, max = 200, message = "商家logo长度必须介于 0 和 200 之间")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public Area getCity() {
        return city;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public Area getDistrict() {
        return district;
    }

    public void setDistrict(Area district) {
        this.district = district;
    }

    @Length(min = 0, max = 200, message = "街道长度必须介于 0 和 200 之间")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Length(min = 0, max = 200, message = "详细地址长度必须介于 0 和 200 之间")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Length(min = 0, max = 30, message = "经度长度必须介于 0 和 30 之间")
    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    @Length(min = 0, max = 30, message = "纬度长度必须介于 0 和 30 之间")
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Length(min = 0, max = 100, message = "邮箱（联系人）长度必须介于 0 和 100 之间")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 0, max = 100, message = "qq号码（联系人）长度必须介于 0 和 100 之间")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Length(min = 0, max = 200, message = "微信号码（联系人）长度必须介于 0 和 200 之间")
    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    @Length(min = 0, max = 200, message = "微信二维码地址（联系人）长度必须介于 0 和 200 之间")
    public String getWxQrCode() {
        return wxQrCode;
    }

    public void setWxQrCode(String wxQrCode) {
        this.wxQrCode = wxQrCode;
    }

    @Length(min = 0, max = 20, message = "客服电话长度必须介于 0 和 20 之间")
    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    @Length(min = 0, max = 200, message = "二维码地址(小店的二维码)长度必须介于 0 和 200 之间")
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Length(min = 0, max = 20, message = "法人姓名长度必须介于 0 和 20 之间")
    public String getSelfEmployedName() {
        return selfEmployedName;
    }

    public void setSelfEmployedName(String selfEmployedName) {
        this.selfEmployedName = selfEmployedName;
    }

    @Length(min = 0, max = 200, message = "身份证正面长度必须介于 0 和 200 之间")
    public String getIdCardFrontUrl() {
        return idCardFrontUrl;
    }

    public void setIdCardFrontUrl(String idCardFrontUrl) {
        this.idCardFrontUrl = idCardFrontUrl;
    }

    @Length(min = 0, max = 200, message = "身份证号码长度必须介于 0 和 200 之间")
    public String getApplyIdCard() {
        return applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard;
    }

    @Length(min = 0, max = 200, message = "营业执照照片地址长度必须介于 0 和 200 之间")
    public String getBizLicUrl() {
        return bizLicUrl;
    }

    public void setBizLicUrl(String bizLicUrl) {
        this.bizLicUrl = bizLicUrl;
    }

    @Length(min = 0, max = 2000, message = "封面图长度必须介于 0 和 2000 之间")
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Length(min = 0, max = 200, message = "身份证反面长度必须介于 0 和 200 之间")
    public String getIdCardBackUrl() {
        return idCardBackUrl;
    }

    public void setIdCardBackUrl(String idCardBackUrl) {
        this.idCardBackUrl = idCardBackUrl;
    }

    @Length(min = 0, max = 200, message = "手持身份证照片地址长度必须介于 0 和 200 之间")
    public String getHandIdCardUrl() {
        return handIdCardUrl;
    }

    public void setHandIdCardUrl(String handIdCardUrl) {
        this.handIdCardUrl = handIdCardUrl;
    }

    @Length(min = 0, max = 200, message = "标签(多个;分割)长度必须介于 0 和 200 之间")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Length(min = 0, max = 1, message = "启用标识长度必须介于 0 和 1 之间")
    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

}