package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-30 00:10:57
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysWithdrawApply implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键Id.
     */
	private Integer id;
	
	/**
     * 提现金额.
     */
	private BigDecimal amount;
	
	/**
     * 提现状态(0:已申请 1:处理中 2:已处理).
     */
	private String status;
	
	/**
     * 备注.
     */
	private String remarks;
	
	/**
     * 创建人(申请人).
     */
	private Integer createBy;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 更新人.
     */
	private Integer updateBy;
	
	/**
     * 更新时间.
     */
	private Date updateDate;
	
	/**
     * 删除标记(0:正常 1:删除).
     */
	private String delFlag;
	
	/**
     * 商户Id.
     */
	private Integer merchantId;
	
	
}