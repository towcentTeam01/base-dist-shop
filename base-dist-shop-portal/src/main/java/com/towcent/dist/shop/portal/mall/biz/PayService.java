package com.towcent.dist.shop.portal.mall.biz;

import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberIn;

import java.util.Map;

import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.portal.mall.vo.input.PayBalanceIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletIn;

/**
 * PayService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface PayService {

    ResultVo balance(PayBalanceIn paramIn);

    ResultVo order(PayOrderIn paramIn);

    ResultVo orderCheckPayStatus(PayOrderCheckPayStatusIn paramIn);

    ResultVo wallet(PayWalletIn paramIn);

    ResultVo walletCheckPayStatus(PayWalletCheckPayStatusIn paramIn);

    String payCall(String payType, Map<String, Object> params);

    ResultVo buyMember(PayBuyMemberIn paramIn);

    ResultVo buyMemberCheckPayStatus(PayBuyMemberCheckPayStatusIn paramIn);
}