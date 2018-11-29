package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.0.6 商品评价列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsEvalListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "goodsId不能为空.")
	private Integer goodsId;		// 商品id
	
	
	
}