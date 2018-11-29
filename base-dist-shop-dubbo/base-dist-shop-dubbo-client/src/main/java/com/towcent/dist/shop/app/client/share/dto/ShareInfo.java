package com.towcent.dist.shop.app.client.share.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author shiwei
 * @date 2018-07-31 20:14:05
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class ShareInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * id.
     */
	private Long id;
	
	/**
     * 工号.
     */
	private String jobNo;
	
	/**
     * 用户ID.
     */
	private Integer createBy;
	
	/**
     * 商户id.
     */
	private Integer merchantId;
	
	/**
     * 商品ID.
     */
	private Integer productId;
	
	/**
     * 分享信息.
     */
	private String shareDesc;
	
	/**
     * 分享图片.
     */
	private String shareImage;
	
	/**
     * 分享链接.
     */
	private String shareUrl;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 浏览量.
     */
	private Integer viewNum;
	
	/**
     * 成交量.
     */
	private Integer tradeNum;
	
	/**
     * 分享标题.
     */
	private String shareTitle;
	
	
}