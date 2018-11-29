package com.towcent.dist.shop.portal.me.web;

import com.towcent.dist.shop.portal.common.vo.BaseParam;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawMarkIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletWithdrawApplyIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletCouponListIn;
import com.towcent.dist.shop.portal.me.vo.input.WalletIncomeListIn;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.me.biz.WalletService;

/**
 * WalletController
 *
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "me/wallet", method = RequestMethod.POST)
public class WalletController extends BaseController {

    @Resource
    private WalletService walletService;

    // 3.3.1 钱包/积分明细
    @RequestMapping(value = "incomeList")
    @Loggable
    public ResultVo incomeList(@RequestBody WalletIncomeListIn paramIn) {
        return walletService.incomeList(paramIn);
    }

    // 3.3.2 我的优惠券列表
    @RequestMapping(value = "couponList")
    @Loggable
    public ResultVo couponList(@RequestBody WalletCouponListIn paramIn) {
        return walletService.couponList(paramIn);
    }

    // 3.3.3 提现申请
    @RequestMapping(value = "withdrawApply")
    @Loggable
    public ResultVo withdrawApply(@RequestBody WalletWithdrawApplyIn paramIn) {
        return walletService.withdrawApply(paramIn);
    }

    // 3.3.4 提现记录列表
    @RequestMapping(value = "withdrawList")
    @Loggable
    public ResultVo withdrawList(@RequestBody WalletWithdrawListIn paramIn) {
        return walletService.withdrawList(paramIn);
    }

    // 3.3.5 手动标记提现已处理
    @RequestMapping(value = "withdrawMark")
    @Loggable
    public ResultVo withdrawMark(@RequestBody WalletWithdrawMarkIn paramIn) {
        return walletService.withdrawMark(paramIn);
    }

    // 3.3.6 判断是否设置交易密码
    @RequestMapping(value = "isSetTradePwd")
    @Loggable
    public ResultVo isSetTradePwd(@RequestBody BaseParam paramIn) {
        return walletService.isSetTradePwd(paramIn);
    }
}