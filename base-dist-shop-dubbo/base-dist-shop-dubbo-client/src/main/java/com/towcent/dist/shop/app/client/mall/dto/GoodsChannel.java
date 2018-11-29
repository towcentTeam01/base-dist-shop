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
public class GoodsChannel implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 频道名称.
     */
	private String channelName;
	
	/**
     * 频道类型(1:频道商品 2:商品 3:广告) channel_type.
     */
	private String channelType;
	
	/**
     * 频道别名(eg. 热门(hot)).
     */
	private String channelAlias;
	
	/**
     * 频道商品数.
     */
	private Integer goodsQty;
	
	/**
     * 是否开启(0:否 1:是) yes_no.
     */
	private String channelStatus;
	
	/**
     * 排序.
     */
	private Short sort;
	
	/**
     * 开始时间.
     */
	private Date startTime;
	
	/**
     * 结束时间.
     */
	private Date endTime;
	
	/**
     * 频道图片.
     */
	private String channelImg;
	
	/**
     * 链接.
     */
	private String channelUrl;
	
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
	
	/**
     * 商户id.
     */
	private Integer merchantId;
	
	
}