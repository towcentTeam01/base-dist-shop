package com.towcent.dist.shop.portal.share.vo.output;


import java.io.Serializable;

import lombok.Data;

/**
 * 4.1.8 商城分享
 * @author shiwei
 * @version 0.0.1
 */
@Data
public class DistHelpShopOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String shareUrl;		// 分享链接(商城首页后面加&shareCode=工号#merchantId#)	
	private String shareImage;		// 分享图片	
	private String shareDesc;		// 分享描述	
	private String shareTitle;		// 分享标题	
	
}