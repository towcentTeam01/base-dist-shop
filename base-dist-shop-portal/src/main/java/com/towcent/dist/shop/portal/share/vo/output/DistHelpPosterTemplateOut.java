package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.1.7 获取海报模板
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistHelpPosterTemplateOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer templateId;		// 模板Id	
	private String tempateTitle;		// 模板标题	
	private String tempateUrl;		// 模板图片URL	
	
}