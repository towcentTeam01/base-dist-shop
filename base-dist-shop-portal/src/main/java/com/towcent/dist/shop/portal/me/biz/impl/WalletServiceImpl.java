package com.towcent.dist.shop.portal.me.biz.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.model.SysDictDtl;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.me.service.WalletApi;
import com.towcent.dist.shop.app.client.me.vo.AccountRecordVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysWithdrawApply;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.common.vo.BaseParam;
import com.towcent.dist.shop.portal.me.biz.WalletService;
import com.towcent.dist.shop.portal.me.vo.input.*;
import com.towcent.dist.shop.portal.me.vo.output.WalletCouponListOut;
import com.towcent.dist.shop.portal.me.vo.output.WalletIncomeListOut;
import com.towcent.dist.shop.portal.me.vo.output.WalletWithdrawListOut;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.E_001;
import static com.towcent.dist.shop.common.Constant.NO;
import static com.towcent.dist.shop.common.Constant.YES;

/**
 * WalletServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class WalletServiceImpl extends BasePortalService implements WalletService {

    @Resource
    private WalletApi walletApi;
    @Resource
    private BaseCommonApi baseCommonApi;
    @Resource
    private SysFrontAccountApi sysFrontAccountApi;

    @Override
    public ResultVo incomeList(WalletIncomeListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SimplePageDto page = this.buildPage(paramIn);

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("userId", account.getId());
            params.put("tabFlag", paramIn.getTabFlag());

            PaginationDto<AccountRecordVo> PageDto = walletApi.queryIncomePage(params, page);

            List<WalletIncomeListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                WalletIncomeListOut out = null;
                for (AccountRecordVo entity : PageDto.getList()) {
                    out = new WalletIncomeListOut();
                    BeanUtils.copyProperties(entity, out);
                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<WalletIncomeListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo couponList(WalletCouponListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SimplePageDto page = this.buildPage(paramIn);

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("userId", account.getId());

            PaginationDto<CouponClaim> PageDto = walletApi.queryCouponPage(params, page);

            List<WalletCouponListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                WalletCouponListOut out = null;
                Date currDate = new Date();
                for (CouponClaim entity : PageDto.getList()) {

                    out = new WalletCouponListOut();
                    BeanUtils.copyProperties(entity, out);
                    out.setActName(entity.getCouponAct().getActName());
                    out.setStartTime(entity.getCouponAct().getStartTime());
                    out.setEndTime(entity.getCouponAct().getEndTime());
                    out.setType(entity.getCouponAct().getActType());

                    out.setStatus(entity.getUseFlag());
                    if (Constant.COUPON_STATUS_UNUSED.equals(entity.getUseFlag())) {
                        if (!currDate.before(entity.getCouponAct().getEndTime())) {
                            out.setStatus(Constant.COUPON_STATUS_OVER);
                        }
                    }

                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<WalletCouponListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo withdrawApply(WalletWithdrawApplyIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            walletApi.withdrawApply(account.getId(), paramIn.getAmount());
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo withdrawList(WalletWithdrawListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            SimplePageDto page = this.buildPage(paramIn);
            PaginationDto<SysWithdrawApply> pagins = walletApi.withdrawList(account.getId(), page);
            List<WalletWithdrawListOut> list = Lists.newArrayList();
            int totalCount = 0;
            if (null != pagins && !CollectionUtils.isEmpty(pagins.getList())) {
                WalletWithdrawListOut out = null;
                Map<String, SysDictDtl> dictMap =
                        baseCommonApi.getDictMapByKey(account.getMerchantId(), "withdraw_apply_status");
                for (SysWithdrawApply withdraw : pagins.getList()) {
                    out = new WalletWithdrawListOut();
                    out.setAmount(withdraw.getAmount());
                    out.setCreateDate(withdraw.getCreateDate());
                    out.setId(withdraw.getId());
                    out.setStatus(withdraw.getStatus());
                    SysDictDtl dict = dictMap.get(out.getStatus());
                    out.setStatusDesc(dict.getName()); // 状态描述
                    list.add(out);
                }
                totalCount = pagins.getTotalCount();
            }
            PaginationDto<WalletWithdrawListOut> outPage =
                    new PaginationDto<WalletWithdrawListOut>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());
            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo withdrawMark(WalletWithdrawMarkIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            walletApi.withdrawMark(account.getId(), paramIn.getApplyId());
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo isSetTradePwd(BaseParam paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            Map map = Maps.newHashMap();
            String flag = NO;
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            account = sysFrontAccountApi.getAccountById(account.getId());
            if (null != account && StringUtils.isNotBlank(account.getTradePassword())) {
                flag = YES;
            }
            map.put("flag", flag);
            resultVo.setData(map);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }
}
