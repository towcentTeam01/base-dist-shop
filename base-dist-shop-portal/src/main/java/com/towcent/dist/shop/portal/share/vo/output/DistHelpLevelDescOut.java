package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.1.4 分类等级介绍
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistHelpLevelDescOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String desc;		// 会员等级介绍图片地址(多张图分号；分割)	
	
}