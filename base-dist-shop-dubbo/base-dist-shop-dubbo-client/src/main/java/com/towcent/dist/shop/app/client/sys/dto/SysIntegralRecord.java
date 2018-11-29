package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-25 16:00:12
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysIntegralRecord implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 用户id.
     */
	private Integer userId;
	
	/**
     * 交易号.
     */
	private String dealNo;
	
	/**
     * 类型(0:支出，1:收入).
     */
	private String type;
	
	/**
     * 此次交易积分.
     */
	private Integer integral;
	
	/**
     * 此次流水之后的积分余额（暂时不适用）.
     */
	private Integer integralAfter;
	
	/**
     * 备注.
     */
	private String remarks;
	
	/**
     * 发生的时间.
     */
	private Date createDate;
	
	/**
     * 商户id.
     */
	private Integer merchantId;
	
	/**
     * 删除标记(0:正常1:删除).
     */
	private String delFlag;
	
	
}