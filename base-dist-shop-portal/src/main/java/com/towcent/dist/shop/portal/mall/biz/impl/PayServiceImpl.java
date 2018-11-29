package com.towcent.dist.shop.portal.mall.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.wx.service.WxInstanceFactory;
import com.towcent.dist.shop.app.client.mall.sevice.PayApi;
import com.towcent.dist.shop.app.client.mall.vo.PayBalanceVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.mall.biz.PayService;
import com.towcent.dist.shop.portal.mall.vo.input.PayBalanceIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayBuyMemberIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayOrderIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletCheckPayStatusIn;
import com.towcent.dist.shop.portal.mall.vo.input.PayWalletIn;
import com.towcent.dist.shop.portal.mall.vo.output.PayBalanceOut;
import com.towcent.dist.shop.portal.mall.vo.output.PayBuyMemberCheckPayStatusOut;
import com.towcent.dist.shop.portal.mall.vo.output.PayOrderCheckPayStatusOut;
import com.towcent.dist.shop.portal.mall.vo.output.PayWalletCheckPayStatusOut;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * PayServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class PayServiceImpl extends BasePortalService implements PayService {

    @Resource
    private PayApi payApi;
    @Resource
    private WxInstanceFactory wxInstanceFactory;

    @Override
    public ResultVo balance(PayBalanceIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            PayBalanceOut outParam = new PayBalanceOut();
            PayBalanceVo vo = payApi.balancePay(account.getId(), paramIn.getOrderId(), paramIn.getTradePassword());
            outParam.setPayStatus(vo.isResult());

            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }


    @Override
    public ResultVo order(PayOrderIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            // 根据微信code换取openId
            String openId = paramIn.getOpenId();
            if (StringUtils.isNotEmpty(paramIn.getCode())) {
                WxMpOAuth2AccessToken authToken = wxInstanceFactory.getInstance(paramIn.getMerchantId()).oauth2getAccessToken(paramIn.getCode());
                openId = authToken.getOpenId();
                logger.info("user openId ： " + openId);
            }

            // PayOrderOut outParam = new PayOrderOut();
            Map<String, Object> resultMap = payApi.createPayParam(account.getId(), paramIn.getOrderId(), paramIn.getPayType(), openId);
            resultVo.setData(resultMap);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        } catch (WxErrorException e) {
            assemblyVo(resultVo, E_001, "换取openId失败");
            logger.error("", e);
        }
        return resultVo;
    }


    @Override
    public ResultVo orderCheckPayStatus(PayOrderCheckPayStatusIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            PayOrderCheckPayStatusOut outParam = new PayOrderCheckPayStatusOut();
            boolean result = payApi.queryOrderPayStatus(account.getId(), paramIn.getOrderId());
            outParam.setPayStatus(result);
            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }


    @Override
    public ResultVo wallet(PayWalletIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            // 根据微信code换取openId
            String openId = paramIn.getOpenId();
            if (StringUtils.isNotEmpty(paramIn.getCode())) {
                WxMpOAuth2AccessToken authToken = wxInstanceFactory.getInstance(paramIn.getMerchantId()).oauth2getAccessToken(paramIn.getCode());
                openId = authToken.getOpenId();
                logger.info("user openId ： " + openId);
            }

            // PayWalletOut outParam = new PayWalletOut();
            Map<String, Object> resultMap = payApi.createPayBalParam(account.getId(), paramIn.getRechargeAmount(),
                    paramIn.getPayType(), paramIn.getOpenId());
            resultVo.setData(resultMap);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        } catch (WxErrorException e) {
            assemblyVo(resultVo, E_001, "换取openId失败");
            logger.error("", e);
        }
        return resultVo;
    }


    @Override
    public ResultVo walletCheckPayStatus(PayWalletCheckPayStatusIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            PayWalletCheckPayStatusOut outParam = new PayWalletCheckPayStatusOut();
            boolean result = payApi.queryPayBalanceStatus(account.getId(), paramIn.getPayRecordNo());
            outParam.setPayStatus(result);
            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public String payCall(String payType, Map<String, Object> params) {
        try {
            return payApi.payCall(payType, params);
        } catch (RpcException e) {
            logger.error("", e);
        }
        return null;
    }


    @Override
    public ResultVo buyMember(PayBuyMemberIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            // 根据微信code换取openId
            String openId = paramIn.getOpenId();
            if (StringUtils.isNotEmpty(paramIn.getCode())) {
                WxMpOAuth2AccessToken authToken = wxInstanceFactory.getInstance(paramIn.getMerchantId()).oauth2getAccessToken(paramIn.getCode());
                openId = authToken.getOpenId();
                logger.info("user openId ： " + openId);
            }

            // PayBuyMemberOut outParam = new PayBuyMemberOut();
            Map<String, Object> resultMap = payApi.createPayBuyMemberParam(account.getId(), paramIn.getBuyAmount(),
                    paramIn.getPayType(), paramIn.getOpenId(), paramIn.getBizType());
            resultVo.setData(resultMap);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        } catch (WxErrorException e) {
            assemblyVo(resultVo, E_001, "换取openId失败");
            logger.error("", e);
        }
        return resultVo;
    }


    @Override
    public ResultVo buyMemberCheckPayStatus(PayBuyMemberCheckPayStatusIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            PayBuyMemberCheckPayStatusOut outParam = new PayBuyMemberCheckPayStatusOut();
            boolean result = payApi.queryPayBuyMemberStatus(account.getId(), paramIn.getPayRecordNo());
            outParam.setPayStatus(result);
            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }
}