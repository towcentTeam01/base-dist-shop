package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-27 15:44:01
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class GoodsEva implements Serializable {

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
     * 订单id.
     */
	private Integer orderId;
	
	/**
     * 订单详情id.
     */
	private Integer orderDtlId;
	
	/**
     * 评价内容.
     */
	private String evaContent;
	
	/**
     * 会员id.
     */
	private Integer userId;
	
	/**
     * 图片eva_urls(多个以;分割).
     */
	private String evaUrls;
	
	/**
     * 评价星级(1-5星).
     */
	private String evaStar;
	
	/**
     * 商家回复.
     */
	private String replyContent;
	
	/**
     * 商家回复时间.
     */
	private Date replyDate;
	
	/**
     * 是否匿名 (0:否 1:是)  yes_no.
     */
	private String isHideName;
	
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