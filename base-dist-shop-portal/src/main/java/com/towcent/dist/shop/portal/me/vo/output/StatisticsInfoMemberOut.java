package com.towcent.dist.shop.portal.me.vo.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 3.0.1 我的信息汇总接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class StatisticsInfoMemberOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nickName;		// 昵称	
	private Integer levelVip;		// 等级	
	private String levelDesc;		// 等级	
	private String headimgurl;		// 图像	

}