package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-07-29 23:32:43
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysPosterTemplate implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 海报模板Id.
     */
	private Integer id;
	
	/**
     * 模板名称.
     */
	private String title;
	
	/**
     * 模板图片URL.
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