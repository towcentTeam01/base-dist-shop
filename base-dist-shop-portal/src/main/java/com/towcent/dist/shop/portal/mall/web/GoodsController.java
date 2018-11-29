package com.towcent.dist.shop.portal.mall.web;

import com.towcent.dist.shop.portal.mall.vo.input.GoodsCollectIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsEvalListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsDetailsIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsCategoryListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsChannelListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsSreachListIn;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.mall.biz.GoodsService;

/**
 * GoodsController
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "mall/goods", method = RequestMethod.POST)
public class GoodsController extends BaseController {

	@Resource
	private GoodsService goodsService;

	// 2.0.1 首页搜索商品接口
	@RequestMapping(value = "sreachList") @Loggable
	public ResultVo sreachList(@RequestBody GoodsSreachListIn paramIn) {
		return goodsService.sreachList(paramIn);
	}

	// 2.0.2 首页商品推荐接口
	@RequestMapping(value = "channelList") @Loggable
	public ResultVo channelList(@RequestBody GoodsChannelListIn paramIn) {
		return goodsService.channelList(paramIn);
	}

	// 2.0.3 商品分类接口
	@RequestMapping(value = "categoryList") @Loggable
	public ResultVo categoryList(@RequestBody GoodsCategoryListIn paramIn) {
		return goodsService.categoryList(paramIn);
	}

	// 2.0.4 按分类获取商品列表接口
	@RequestMapping(value = "list") @Loggable
	public ResultVo list(@RequestBody GoodsListIn paramIn) {
		return goodsService.list(paramIn);
	}

	// 2.0.5 商品详情
	@RequestMapping(value = "details") @Loggable
	public ResultVo details(@RequestBody GoodsDetailsIn paramIn) {
		return goodsService.details(paramIn);
	}

	// 2.0.6 商品评价列表
	@RequestMapping(value = "evalList") @Loggable
	public ResultVo evalList(@RequestBody GoodsEvalListIn paramIn) {
		return goodsService.evalList(paramIn);
	}

	// 2.0.7 商品收藏
	@RequestMapping(value = "collect") @Loggable
	public ResultVo collect(@RequestBody GoodsCollectIn paramIn) {
		return goodsService.collect(paramIn);
	}
}