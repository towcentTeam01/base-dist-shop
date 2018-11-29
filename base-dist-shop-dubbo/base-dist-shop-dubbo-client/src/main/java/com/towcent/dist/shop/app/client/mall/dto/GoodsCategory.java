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
public class GoodsCategory implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 分类编码.
     */
	private String categoryNo;
	
	/**
     * 分类名称.
     */
	private String categoryName;
	
	/**
     * 级别.
     */
	private Integer level;
	
	/**
     * 父id.
     */
	private Integer parentId;
	
	/**
     * 结构体编码.
     */
	private String structureNo;
	
	/**
     * 结构体名称.
     */
	private String structureName;
	
	/**
     * 排序.
     */
	private Integer sort;
	
	/**
     * 所有父id.
     */
	private String parentIds;
	
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
	
	/**
     * 分类图片.
     */
	private String categoryIcon;
	
	
}