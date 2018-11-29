package com.towcent.dist.shop.portal.mall.vo.input;


import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.1.3 修改购物车接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ShoppingCartEditIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotNull(message = "goodsId不能为空.")
	private Integer goodsId;		// 商品id
	
	@NotNull(message = "spec不能为空.")
	private Integer spec;		// 规格id
	
	@NotNull(message = "qty不能为空.")
	private Integer qty;		// 数量(大于0)
	
}