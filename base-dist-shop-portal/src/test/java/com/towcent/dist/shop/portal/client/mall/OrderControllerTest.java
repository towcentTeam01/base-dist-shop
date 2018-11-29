package com.towcent.dist.shop.portal.client.mall;

import com.towcent.dist.shop.portal.mall.vo.input.*;

import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class OrderControllerTest extends BaseAppTest {

	static {
		descMap.put("mall/order/receipt", "订单确认收货");
		descMap.put("mall/order/cancel", "订单取消");
		descMap.put("mall/order/eval", "订单商品评价");
		descMap.put("mall/order/del", "订单删除");
		descMap.put("mall/order/evalList", "订单商品评价列表");
		descMap.put("mall/order/list", "订单列表");
		descMap.put("mall/order/create", "创建订单接口");
		descMap.put("mall/order/detail", "订单详情接口");
		descMap.put("mall/order/logisTrace", "物流跟踪");
	}

	@Test
	public void create() throws IOException {
		String path = "mall/order/create";
		OrderCreateIn paramVo = new OrderCreateIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setGoodsStr("1:1:1");
		paramVo.setPayWay("1");
		paramVo.setRemark("");
		paramVo.setConsigneeAddrId(1);
		paramVo.setCouponId(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void list() throws IOException {
		String path = "mall/order/list";
		OrderListIn paramVo = new OrderListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setTabFlag("1");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void evalList() throws IOException {
		String path = "mall/order/evalList";
		OrderEvalListIn paramVo = new OrderEvalListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(8);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void del() throws IOException {
		String path = "mall/order/del";
		OrderDelIn paramVo = new OrderDelIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(5);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void eval() throws IOException {
		String path = "mall/order/eval";
		OrderEvalIn paramVo = new OrderEvalIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderDtlId(8);
		paramVo.setEvaContent("不错哦，下次继续购买");
		paramVo.setEvaStar("5");
		paramVo.setEvaUrls("http://img.yyxlvyou.com//tempPic/0/20171010170701819.jpg?v=1");
		paramVo.setIsHideName("1");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void cancel() throws IOException {
		String path = "mall/order/cancel";
		OrderCancelIn paramVo = new OrderCancelIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(7);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void receipt() throws IOException {
		String path = "mall/order/receipt";
		OrderReceiptIn paramVo = new OrderReceiptIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(8);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void detail() throws IOException {
		String path = "mall/order/detail";
		OrderDetailIn paramVo = new OrderDetailIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(8);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
	
	@Test
	public void trace() throws IOException {
		String path = "mall/order/logisTrace";
		LogisTraceIn paramVo = new LogisTraceIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		paramVo.setOrderId("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}