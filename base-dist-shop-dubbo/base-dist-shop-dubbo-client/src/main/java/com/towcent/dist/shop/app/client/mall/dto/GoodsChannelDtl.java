package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class GoodsChannelDtl implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 商品id.
     */
	private Integer goodsId;
	
	/**
     * 频道id.
     */
	private Integer channelId;
	
	/**
     * 排序号.
     */
	private Integer sort;
	
	/**
     * 频道商品图片.
     */
	private String img;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 商户id.
     */
	private Integer merchantId;
	
	
}