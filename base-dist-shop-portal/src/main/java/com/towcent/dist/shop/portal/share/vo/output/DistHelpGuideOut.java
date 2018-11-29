package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.1.3 操作指南
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistHelpGuideOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String helplist;		// 帮助图片(多张图分号；分割)	
	
}