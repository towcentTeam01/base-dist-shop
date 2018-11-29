package com.towcent.dist.shop.portal.client.mall;

import com.towcent.dist.shop.portal.client.BaseAppTest;
import com.towcent.dist.shop.portal.mall.vo.input.CouponDrawIn;
import com.towcent.dist.shop.portal.mall.vo.input.CouponListIn;
import org.junit.Test;

import java.io.IOException;

public class CouponControllerTest extends BaseAppTest {

	static {
		descMap.put("mall/coupon/draw", "领取优惠券");
		descMap.put("mall/coupon/list", "优惠券领取列表");
		
	}

	@Test
	public void list() throws IOException {
		String path = "mall/coupon/list";
		CouponListIn paramVo = new CouponListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void draw() throws IOException {
		String path = "mall/coupon/draw";
		CouponDrawIn paramVo = new CouponDrawIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setActId(2);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}