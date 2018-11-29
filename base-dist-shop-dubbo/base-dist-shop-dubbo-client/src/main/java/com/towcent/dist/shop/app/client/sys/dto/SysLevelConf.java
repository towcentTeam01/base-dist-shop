package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 09:35:14
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysLevelConf implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 等级id.
     */
	private Integer id;
	
	/**
     * 会员等级别名.
     */
	private String levelName;
	
	/**
     * 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员).
     */
	private String level;
	
	/**
     * 升级所需锁粉数.
     */
	private Integer lockFans;
	
	/**
     * 升级所需直推订单数.
     */
	private Integer recOrder;
	
	/**
     * 升级所需费用.
     */
	private BigDecimal payFee;
	
	/**
     * 是否默认等级(0:否 1:是) yes_no.
     */
	private String defaultFlag;
	
	/**
     * 直推佣金比例().
     */
	private BigDecimal directBkge;
	
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