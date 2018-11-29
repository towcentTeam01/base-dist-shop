package com.towcent.dist.shop.portal.me.biz;

import com.towcent.dist.shop.portal.common.vo.BaseParam;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawMarkIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawApplyIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletCouponListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletIncomeListIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * WalletService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface WalletService {

    ResultVo incomeList(WalletIncomeListIn paramIn);

    ResultVo couponList(WalletCouponListIn paramIn);

    ResultVo withdrawApply(WalletWithdrawApplyIn paramIn);

    ResultVo withdrawList(WalletWithdrawListIn paramIn);

    ResultVo withdrawMark(WalletWithdrawMarkIn paramIn);

    ResultVo isSetTradePwd(BaseParam paramIn);
}