package com.towcent.dist.shop.portal.mall.web;

import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBalanceIn;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.mall.biz.PayService;

/**
 * PayController
 * @author huangtao
 * @version 0.0.1
 */
@RestController(value="orderPayController")
@RequestMapping(value = "mall/pay", method = RequestMethod.POST)
public class PayController extends BaseController {

	@Resource
	private PayService payService;

	// 2.4.1 余额支付
	@RequestMapping(value = "balance") @Loggable
	public ResultVo balance(@RequestBody PayBalanceIn paramIn) {
		return payService.balance(paramIn);
	}

	// 2.4.2 订单支付（公众号/小程序）
	@RequestMapping(value = "order") @Loggable
	public ResultVo order(@RequestBody PayOrderIn paramIn) {
		return payService.order(paramIn);
	}

	// 2.4.3 检查订单状态
	@RequestMapping(value = "orderCheckPayStatus") @Loggable
	public ResultVo orderCheckPayStatus(@RequestBody PayOrderCheckPayStatusIn paramIn) {
		return payService.orderCheckPayStatus(paramIn);
	}

	// 2.4.4 钱包充值支付（公众号/APP）
	@RequestMapping(value = "wallet") @Loggable
	public ResultVo wallet(@RequestBody PayWalletIn paramIn) {
		return payService.wallet(paramIn);
	}

	// 2.4.5 钱包充值状态
	@RequestMapping(value = "walletCheckPayStatus") @Loggable
	public ResultVo walletCheckPayStatus(@RequestBody PayWalletCheckPayStatusIn paramIn) {
		return payService.walletCheckPayStatus(paramIn);
	}
	
	// 2.4.6 微信/支付宝异步回调接口.
	@RequestMapping(value = "payBack{payType}.json")
	public String payBack(HttpServletRequest request, @PathVariable Integer payType) throws IOException, RpcException {			
		Map<String, Object> params = null;
		if (payType == 1 || payType == 3) { // 微信
			params = this.getParameter2Map(request.getInputStream());
		} else { // 支付宝
			params = this.getParameter2Map(request.getParameterMap());
		}
		logger.debug("支付回调：" + params);
		String result = payService.payCall(payType+"", params);
		logger.debug("支付回调：" + params + " , 处理状态：" + result);
		return result;
	}

	// 2.4.7 购买会员支付（公众号/APP）
	@RequestMapping(value = "buyMember") @Loggable
	public ResultVo buyMember(@RequestBody PayBuyMemberIn paramIn) {
		return payService.buyMember(paramIn);
	}

	// 2.4.8 购买会员支付状态
	@RequestMapping(value = "buyMemberCheckPayStatus") @Loggable
	public ResultVo buyMemberCheckPayStatus(@RequestBody PayBuyMemberCheckPayStatusIn paramIn) {
		return payService.buyMemberCheckPayStatus(paramIn);
	}
}