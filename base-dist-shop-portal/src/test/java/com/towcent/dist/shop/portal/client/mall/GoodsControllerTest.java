package com.towcent.dist.shop.portal.client.mall;

import com.towcent.dist.shop.portal.mall.vo.input.GoodsCollectIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsEvalListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsDetailsIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsCategoryListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsChannelListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsSreachListIn;
import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class GoodsControllerTest extends BaseAppTest {

	static {
		descMap.put("mall/goods/collect", "商品收藏");
		descMap.put("mall/goods/evalList", "商品评价列表");
		descMap.put("mall/goods/details", "商品详情");
		descMap.put("mall/goods/list", "按分类获取商品列表接口");
		descMap.put("mall/goods/categoryList", "商品分类接口");
		descMap.put("mall/goods/channelList", "首页商品推荐接口");
		descMap.put("mall/goods/sreachList", "首页搜索商品接口");
		
	}

	@Test
	public void sreachList() throws IOException {
		String path = "mall/goods/sreachList";
		GoodsSreachListIn paramVo = new GoodsSreachListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setSearchStr("海南");
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void channelList() throws IOException {
		String path = "mall/goods/channelList";
		GoodsChannelListIn paramVo = new GoodsChannelListIn();
		this.setCommonParam(paramVo);
        this.setLoginFlag(paramVo);
		paramVo.setPageNo(1);
		paramVo.setPageSize(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void categoryList() throws IOException {
		String path = "mall/goods/categoryList";
		GoodsCategoryListIn paramVo = new GoodsCategoryListIn();
		this.setCommonParam(paramVo);
        this.setLoginFlag(paramVo);
		paramVo.setParentId(0);
		paramVo.setLevel(2);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void list() throws IOException {
		String path = "mall/goods/list";
		GoodsListIn paramVo = new GoodsListIn();
		this.setCommonParam(paramVo);
        this.setLoginFlag(paramVo);
		paramVo.setCategoryId(null);
		paramVo.setStructureNo("");//100051,100064
		paramVo.setOrderByField("");
		paramVo.setOrderBy("");
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void details() throws IOException {
		String path = "mall/goods/details";
		GoodsDetailsIn paramVo = new GoodsDetailsIn();
		this.setCommonParam(paramVo);
        this.setLoginFlag(paramVo);
		paramVo.setGoodsId(2);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void evalList() throws IOException {
		String path = "mall/goods/evalList";
		GoodsEvalListIn paramVo = new GoodsEvalListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setGoodsId(1);
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void collect() throws IOException {
		String path = "mall/goods/collect";
		GoodsCollectIn paramVo = new GoodsCollectIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setGoodsId(2);
		paramVo.setFlag(0);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}