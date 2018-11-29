package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.3.5 订单商品评价
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderEvalIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "orderDtlId不能为空.")
	private Integer orderDtlId;		// 订单详情Id
	
	@NotBlank(message = "evaContent不能为空.")
	private String evaContent;		// 评价内容
	
	@NotBlank(message = "evaStar不能为空.")
	private String evaStar;		// 评价星级
	
	private String evaUrls;		// 评价图片 多个以;分割
	
	@NotBlank(message = "isHideName不能为空.")
	private String isHideName;		// 是否匿名 0:否 1:是
	
}