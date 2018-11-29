package com.towcent.dist.shop.portal.share.vo.input;


import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 4.1.2 专属海报
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class DistHelpPosterIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 模板Id
	 */
	@NotNull(message="模板Id不能为空.")
	private Integer templateId;
	
}