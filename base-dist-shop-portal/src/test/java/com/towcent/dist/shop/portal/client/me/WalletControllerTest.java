package com.towcent.dist.shop.portal.client.me;

import java.io.IOException;
import java.math.BigDecimal;

import com.towcent.dist.shop.portal.common.vo.BaseParam;
import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;
import com.towcent.dist.shop.portal.me.vo.input.WalletCouponListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletIncomeListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawApplyIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawMarkIn;

public class WalletControllerTest extends BaseAppTest {

	static {
		descMap.put("me/wallet/withdrawMark", "手动标记提现已处理");
		descMap.put("me/wallet/withdrawList", "提现记录列表");
		descMap.put("me/wallet/withdrawApply", "提现申请");
		descMap.put("me/wallet/couponList", "我的优惠券列表");
		descMap.put("me/wallet/incomeList", "钱包/积分明细");
		
	}

	@Test
	public void incomeList() throws IOException {
		String path = "me/wallet/incomeList";
		WalletIncomeListIn paramVo = new WalletIncomeListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setTabFlag("1");
		paramVo.setPageNo(1);
		paramVo.setPageSize(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void couponList() throws IOException {
		String path = "me/wallet/couponList";
		WalletCouponListIn paramVo = new WalletCouponListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void withdrawApply() throws IOException {
		String path = "me/wallet/withdrawApply";
		WalletWithdrawApplyIn paramVo = new WalletWithdrawApplyIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);	
		paramVo.setAmount(BigDecimal.valueOf(10.0));
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void withdrawList() throws IOException {
		String path = "me/wallet/withdrawList";
		WalletWithdrawListIn paramVo = new WalletWithdrawListIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void withdrawMark() throws IOException {
		String path = "me/wallet/withdrawMark";
		WalletWithdrawMarkIn paramVo = new WalletWithdrawMarkIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		paramVo.setApplyId(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void isSetTradePwd() throws IOException {
		String path = "me/wallet/isSetTradePwd";
		BaseParam paramVo = new BaseParam();
		this.setCommonParam(paramVo);

		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}