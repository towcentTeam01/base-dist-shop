package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author huangtao
 * @date 2018-07-11 18:40:58
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class SysLogisticsCompanyMerchant implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键Id.
     */
	private Integer id;
	
	/**
     * 物流公司Id.
     */
	private Integer companyId;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 删除标记(0:正常 1:删除).
     */
	private String delFlag;
	
	/**
     * 商家Id.
     */
	private Integer merchantId;
	
	
}