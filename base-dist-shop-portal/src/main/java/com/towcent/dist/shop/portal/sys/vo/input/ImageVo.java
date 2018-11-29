package com.towcent.dist.shop.portal.sys.vo.input;

import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * 图片参数Vo
 * 
 * @author huangtao
 * @date 2015年7月6日 下午2:35:03
 * @version 0.1.0 
 * @copyright zuojian.com
 */
@Setter @Getter
public class ImageVo extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 图片类型 (0:头像).
	 */
	@NotNull(message="图片类型不能为空")
	private Integer imageType;
	
	/**
	 * base64.
	 */
	private String imageData;
	
	/**
	 * 是否微信上传 (0:否 1:是).
	 */
	private Integer isWeixin = 0;
	
}