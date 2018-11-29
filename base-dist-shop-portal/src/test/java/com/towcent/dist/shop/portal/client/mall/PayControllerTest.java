package com.towcent.dist.shop.portal.client.mall;

import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBalanceIn;
import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Test;

import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.portal.client.BaseAppTest;

public class PayControllerTest extends BaseAppTest {

	static {
		descMap.put("mall/Pay/buyMemberCheckPayStatus", "购买会员支付状态");
		descMap.put("mall/Pay/buyMember", "购买会员支付（公众号/APP）");
		descMap.put("mall/pay/walletCheckPayStatus", "钱包充值状态");
		descMap.put("mall/pay/wallet", "钱包充值支付（公众号/APP）");
		descMap.put("mall/pay/orderCheckPayStatus", "检查订单状态");
		descMap.put("mall/pay/order", "订单支付（公众号/小程序）");
		descMap.put("mall/pay/balance", "余额充值");
		
	}

	@Test
	public void balance() throws IOException {
		String path = "mall/pay/balance";
		PayBalanceIn paramVo = new PayBalanceIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void order() throws IOException {
		String path = "mall/pay/order";
		PayOrderIn paramVo = new PayOrderIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(1);
		paramVo.setPayType("");
		paramVo.setOpenId("");
		paramVo.setCode("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void orderCheckPayStatus() throws IOException {
		String path = "mall/pay/orderCheckPayStatus";
		PayOrderCheckPayStatusIn paramVo = new PayOrderCheckPayStatusIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setOrderId(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void wallet() throws IOException {
		String path = "mall/pay/wallet";
		PayWalletIn paramVo = new PayWalletIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
//		paramVo.setRechargeAmount("");
		paramVo.setCode("");
		paramVo.setOpenId("");
		paramVo.setPayType("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void walletCheckPayStatus() throws IOException {
		String path = "mall/pay/walletCheckPayStatus";
		PayWalletCheckPayStatusIn paramVo = new PayWalletCheckPayStatusIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setPayRecordNo("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void buyMember() throws IOException {
		String path = "mall/Pay/buyMember";
		PayBuyMemberIn paramVo = new PayBuyMemberIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		paramVo.setBuyAmount(BigDecimal.ONE);
		paramVo.setBizType(Constant.BIZ_TYPE_2);
		paramVo.setCode("");
		paramVo.setOpenId("");
		paramVo.setPayType("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void buyMemberCheckPayStatus() throws IOException {
		String path = "mall/Pay/buyMemberCheckPayStatus";
		PayBuyMemberCheckPayStatusIn paramVo = new PayBuyMemberCheckPayStatusIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		paramVo.setPayRecordNo("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}