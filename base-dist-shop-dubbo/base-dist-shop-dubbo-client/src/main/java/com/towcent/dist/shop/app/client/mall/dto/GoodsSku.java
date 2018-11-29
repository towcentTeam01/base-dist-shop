package com.towcent.dist.shop.app.client.mall.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 17:42:23
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class GoodsSku implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 商品规格id.
     */
	private Integer goodsSpecId;
	
	/**
     * 规格价格.
     */
	private BigDecimal price;
	
	/**
     * 最小数量 批发商品专有.
     */
	private Integer minNum;
	
	/**
     * 最大数量 批发商品专有.
     */
	private Integer maxNum;
	
	/**
     * 备注.
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