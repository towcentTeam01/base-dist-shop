package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-07-05 18:00:37
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysMerchantInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 系统账号.
     */
	private Integer sysUserId;
	
	/**
     * 店铺编码.
     */
	private String houseNumber;
	
	/**
     * 店铺名称.
     */
	private String shopName;
	
	/**
     * 店铺类别(1:实体店 2:店铺).
     */
	private String shopType;
	
	/**
     * 状态(1:待审核 2:审核通过 3:审核拒绝).
     */
	private String applyStatus;
	
	/**
     * 联系电话.
     */
	private String phone;
	
	/**
     * 商家logo.
     */
	private String logo;
	
	/**
     * 省(开店地址).
     */
	private Integer province;
	
	/**
     * 市(开店地址).
     */
	private Integer city;
	
	/**
     * 区(开店地址).
     */
	private Integer district;
	
	/**
     * 街道.
     */
	private String street;
	
	/**
     * 详细地址.
     */
	private String address;
	
	/**
     * 经度.
     */
	private String longtitude;
	
	/**
     * 纬度.
     */
	private String latitude;
	
	/**
     * 邮箱（联系人）.
     */
	private String email;
	
	/**
     * qq号码（联系人）.
     */
	private String qq;
	
	/**
     * 微信号码（联系人）.
     */
	private String wxCode;
	
	/**
     * 微信二维码地址（联系人）.
     */
	private String wxQrCode;
	
	/**
     * 客服电话.
     */
	private String servicePhone;
	
	/**
     * 二维码地址(小店的二维码).
     */
	private String qrCode;
	
	/**
     * 法人姓名.
     */
	private String selfEmployedName;
	
	/**
     * 身份证正面.
     */
	private String idCardFrontUrl;
	
	/**
     * 身份证号码.
     */
	private String applyIdCard;
	
	/**
     * 营业执照照片地址.
     */
	private String bizLicUrl;
	
	/**
     * 封面图.
     */
	private String coverUrl;
	
	/**
     * 身份证反面.
     */
	private String idCardBackUrl;
	
	/**
     * 手持身份证照片地址.
     */
	private String handIdCardUrl;
	
	/**
     * 标签(多个;分割).
     */
	private String alias;
	
	/**
     * 启用标识(1:启用 0:禁用).
     */
	private String enabledFlag;
	
	/**
     * 注备.
     */
	private String remarks;
	
	/**
     * 创建者.
     */
	private Integer createBy;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 更新者.
     */
	private Integer updateBy;
	
	/**
     * 更新时间.
     */
	private Date updateDate;
	
	/**
     * 删除标记(0:正常1:删除).
     */
	private String delFlag;
	
	
}