package com.towcent.dist.shop.app.client.share.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author shiwei
 * @date 2018-07-31 11:36:43
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class ShareCategory implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * id.
     */
	private Integer id;
	
	/**
     * 分类编码.
     */
	private String categoryNo;
	
	/**
     * 分享信息.
     */
	private String shareDesc;
	
	/**
     * 分享图片.
     */
	private String shareImage;
	
	/**
     * share_title.
     */
	private String shareTitle;
	
	/**
     * 商户id 为0时为平台 .
     */
	private Integer merchantId;
	
	/**
     * 商品分类id.
     */
	private Integer categoryId;
	
	
}