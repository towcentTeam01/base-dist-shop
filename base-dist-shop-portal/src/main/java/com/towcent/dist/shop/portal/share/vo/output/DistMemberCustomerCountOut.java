package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.0.3 客户管理汇总信息
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistMemberCustomerCountOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer agentNum;		// 代理商	
	private Integer nomalNum;		// 普通用户	
	
}