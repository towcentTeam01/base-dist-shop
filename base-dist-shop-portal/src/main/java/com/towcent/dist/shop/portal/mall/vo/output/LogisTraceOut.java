package com.towcent.dist.shop.portal.mall.vo.output;


import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 2.4.9 物流跟踪
 * @author shiwei
 * @version 0.0.1
 */
@Data
public class LogisTraceOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String logisticsName;		// 物流公司中文名称
	
	private String freightNumber;		// 物流单号.
	
	private List<LogisTraceDetailOut> logisTraceList; //物流详情
}