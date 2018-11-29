package com.towcent.dist.shop.portal.share.vo.input;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * 4.0.4 客户管理列表
 * @author huangtao
 * @version 0.0.1
 */
@Setter @Getter
public class DistMemberCustomerListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "tabFlag不能为空.")
	private String tabFlag;		// Tab(1:代理商 2:普通用户) 默认1全部
	
	// @NotBlank(message = "searchStr不能为空.")
	private String searchStr;		// 查询条件(工号或手机)
		
}