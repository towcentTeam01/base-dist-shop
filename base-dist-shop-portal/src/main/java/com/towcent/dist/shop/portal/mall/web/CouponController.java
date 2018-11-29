package com.towcent.dist.shop.portal.mall.web;

import com.towcent.dist.shop.portal.mall.vo.input.CouponDrawIn;
import com.towcent.dist.shop.portal.mall.vo.input.CouponListIn;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.mall.biz.CouponService;

/**
 * CouponController
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "mall/coupon", method = RequestMethod.POST)
public class CouponController extends BaseController {

	@Resource
	private CouponService couponService;

	// 2.2.1 优惠券领取列表
	@RequestMapping(value = "list") @Loggable
	public ResultVo list(@RequestBody CouponListIn paramIn) {
		return couponService.list(paramIn);
	}

	// 2.2.2 领取优惠券
	@RequestMapping(value = "draw") @Loggable
	public ResultVo draw(@RequestBody CouponDrawIn paramIn) {
		return couponService.draw(paramIn);
	}
}