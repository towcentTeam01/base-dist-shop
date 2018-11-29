package com.towcent.dist.shop.portal.client.me;

import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsDelIn;
import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsListIn;
import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class CollectControllerTest extends BaseAppTest {

	static {
		descMap.put("me/collect/goodsDel", "删除收藏商品");
		descMap.put("me/collect/goodsList", "我收藏的商品");
		
	}

	@Test
	public void goodsList() throws IOException {
		String path = "me/collect/goodsList";
		CollectGoodsListIn paramVo = new CollectGoodsListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void goodsDel() throws IOException {
		String path = "me/collect/goodsDel";
		CollectGoodsDelIn paramVo = new CollectGoodsDelIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setCollectId(2);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}