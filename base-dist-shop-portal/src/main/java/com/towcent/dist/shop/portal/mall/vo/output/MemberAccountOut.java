package com.towcent.dist.shop.portal.mall.vo.output;

import java.math.BigDecimal;

import java.io.Serializable;

import lombok.Data;

/**
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class MemberAccountOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer integral;		// 积分
	private BigDecimal balance;		// 余额

}