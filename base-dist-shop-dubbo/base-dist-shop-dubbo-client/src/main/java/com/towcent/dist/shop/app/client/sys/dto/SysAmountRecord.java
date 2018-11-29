package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-25 15:56:30
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysAmountRecord implements Serializable {

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
     * 交易去向(作废).
     */
	private String direction;
	
	/**
     * 类型(0:支出，1:收入).
     */
	private String type;
	
	/**
     * 此次交易金额.
     */
	private BigDecimal amount;
	
	/**
     * 此次流水之后的余额（暂时不适用）.
     */
	private BigDecimal amountAfter;
	
	/**
     * 订单金额.
     */
	private BigDecimal orderAmount;
	
	/**
     * 订单title(默认商品名称).
     */
	private String orderTitle;
	
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