package com.towcent.dist.shop.portal.mall.web;

import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartConfirmIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartGoodsQtyIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartListIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartEditIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartDelIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartAddIn;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.mall.biz.ShoppingCartService;

/**
 * ShoppingCartController
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "mall/shoppingCart", method = RequestMethod.POST)
public class ShoppingCartController extends BaseController {

	@Resource
	private ShoppingCartService shoppingCartService;

	// 2.1.1 添加商品到购物车接口
	@RequestMapping(value = "add") @Loggable
	public ResultVo add(@RequestBody ShoppingCartAddIn paramIn) {
		return shoppingCartService.add(paramIn);
	}

	// 2.1.2 删除购物车商品接口
	@RequestMapping(value = "del") @Loggable
	public ResultVo del(@RequestBody ShoppingCartDelIn paramIn) {
		return shoppingCartService.del(paramIn);
	}

	// 2.1.3 修改购物车接口
	@RequestMapping(value = "edit") @Loggable
	public ResultVo edit(@RequestBody ShoppingCartEditIn paramIn) {
		return shoppingCartService.edit(paramIn);
	}

	// 2.1.4 购物车列表接口
	@RequestMapping(value = "list") @Loggable
	public ResultVo list(@RequestBody ShoppingCartListIn paramIn) {
		return shoppingCartService.list(paramIn);
	}

	// 2.1.5 购物车商品数量
	@RequestMapping(value = "goodsQty") @Loggable
	public ResultVo goodsQty(@RequestBody ShoppingCartGoodsQtyIn paramIn) {
		return shoppingCartService.goodsQty(paramIn);
	}

	// 2.1.6 去结算页面
	@RequestMapping(value = "confirm") @Loggable
	public ResultVo confirm(@RequestBody ShoppingCartConfirmIn paramIn) {
		return shoppingCartService.confirm(paramIn);
	}
}