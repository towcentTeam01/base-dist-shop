package com.towcent.dist.shop.portal.client.mall;

import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartConfirmIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartGoodsQtyIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartListIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartEditIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartDelIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartAddIn;
import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class ShoppingCartControllerTest extends BaseAppTest {

	static {
		descMap.put("mall/shoppingCart/confirm", "去结算页面");
		descMap.put("mall/shoppingCart/goodsQty", "购物车商品数量");
		descMap.put("mall/shoppingCart/list", "购物车列表接口");
		descMap.put("mall/shoppingCart/edit", "修改购物车接口");
		descMap.put("mall/shoppingCart/del", "删除购物车商品接口");
		descMap.put("mall/shoppingCart/add", "添加商品到购物车接口");
		
	}

	@Test
	public void add() throws IOException {
		String path = "mall/shoppingCart/add";
		ShoppingCartAddIn paramVo = new ShoppingCartAddIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setGoodsId(2);
		paramVo.setSpec(3);
		paramVo.setQty(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void del() throws IOException {
		String path = "mall/shoppingCart/del";
		ShoppingCartDelIn paramVo = new ShoppingCartDelIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setKeys("1_1");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void edit() throws IOException {
		String path = "mall/shoppingCart/edit";
		ShoppingCartEditIn paramVo = new ShoppingCartEditIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setGoodsId(1);
		paramVo.setSpec(1);
		paramVo.setQty(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void list() throws IOException {
		String path = "mall/shoppingCart/list";
		ShoppingCartListIn paramVo = new ShoppingCartListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void goodsQty() throws IOException {
		String path = "mall/shoppingCart/goodsQty";
		ShoppingCartGoodsQtyIn paramVo = new ShoppingCartGoodsQtyIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void confirm() throws IOException {
		String path = "mall/shoppingCart/confirm";
		ShoppingCartConfirmIn paramVo = new ShoppingCartConfirmIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setGoodsStr("4:1:8");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}