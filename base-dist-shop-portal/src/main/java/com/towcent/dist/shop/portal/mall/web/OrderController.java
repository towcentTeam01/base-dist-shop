package com.towcent.dist.shop.portal.mall.web;

import com.towcent.dist.shop.portal.mall.vo.input.*;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;
import com.towcent.dist.shop.portal.mall.biz.OrderService;

/**
 * OrderController
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "mall/order", method = RequestMethod.POST)
public class OrderController extends BaseController {

	@Resource
	private OrderService orderService;

	// 2.3.1 创建订单接口
	@RequestMapping(value = "create") @Loggable
	public ResultVo create(@RequestBody OrderCreateIn paramIn) {
		return orderService.create(paramIn);
	}

	// 2.3.2 订单列表
	@RequestMapping(value = "list") @Loggable
	public ResultVo list(@RequestBody OrderListIn paramIn) {
		return orderService.list(paramIn);
	}

	// 2.3.3 订单商品评价列表
	@RequestMapping(value = "evalList") @Loggable
	public ResultVo evalList(@RequestBody OrderEvalListIn paramIn) {
		return orderService.evalList(paramIn);
	}

	// 2.3.4 订单删除
	@RequestMapping(value = "del") @Loggable
	public ResultVo del(@RequestBody OrderDelIn paramIn) {
		return orderService.del(paramIn);
	}

	// 2.3.5 订单商品评价
	@RequestMapping(value = "eval") @Loggable
	public ResultVo eval(@RequestBody OrderEvalIn paramIn) {
		return orderService.eval(paramIn);
	}

	// 2.3.6 订单取消
	@RequestMapping(value = "cancel") @Loggable
	public ResultVo cancel(@RequestBody OrderCancelIn paramIn) {
		return orderService.cancel(paramIn);
	}

	// 2.3.7 订单确认收货
	@RequestMapping(value = "receipt") @Loggable
	public ResultVo receipt(@RequestBody OrderReceiptIn paramIn) {
		return orderService.receipt(paramIn);
	}

	// 2.3.8 订单详情
	@RequestMapping(value = "detail") @Loggable
	public ResultVo detail(@RequestBody OrderDetailIn paramIn) {
		return orderService.detail(paramIn);
	}
	
	// 2.3.9 物流跟踪
	@RequestMapping(value = "logisTrace") @Loggable
	public ResultVo trace(@RequestBody LogisTraceIn paramIn) {
		return orderService.trace(paramIn);
	}
}