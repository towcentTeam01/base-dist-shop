package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-07-02 11:30:33
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysHelpGuide implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键Id.
     */
	private Integer id;
	
	/**
     * 教程类别(1:系统界面).
     */
	private String guideType;
	
	/**
     * 指南标题.
     */
	private String title;
	
	/**
     * 指南图片地址.
     */
	private String picUrl;
	
	/**
     * 排序号.
     */
	private Integer sort;
	
	/**
     * 创建人.
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
     * 删除标识(0:正常 1:删除).
     */
	private String delFlag;
	
	/**
     * 商户Id.
     */
	private Integer merchantId;
	
	
}