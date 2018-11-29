package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.1.2 专属海报
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistHelpPosterOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String posterUrl;		// 海报图片链接	
	private String shareUrl;	    // 分享链接（推广链接）
	
}