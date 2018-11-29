package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-07-01 17:34:29
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysHelpRule implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键Id.
     */
	private Integer id;
	
	/**
     * 正文标题.
     */
	private String title;
	
	/**
     * 描述图片地址.
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