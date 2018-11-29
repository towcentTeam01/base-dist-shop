/**
 * 
 */
package com.towcent.dist.shop.app.client.share.vo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author shiwei
 *
 */
@Setter
@Getter
public class ShareVo implements Serializable {

	  private static final long serialVersionUID = 1L;

	  private String shareUrl;//分享链接
	  
	  private String shareImage;//分享图片
	  
	  private String shareDesc;//分享描述
	  
	  private String shareTitle;//分享标题
}
