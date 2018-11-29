package com.towcent.dist.shop.portal.me.biz.impl;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.me.service.MeApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.me.biz.StatisticsService;
import com.towcent.dist.shop.portal.me.vo.input.StatisticsInfoIn;
import com.towcent.dist.shop.portal.me.vo.output.StatisticsInfoMemberOut;
import com.towcent.dist.shop.portal.me.vo.output.StatisticsInfoOrderOut;
import com.towcent.dist.shop.portal.me.vo.output.StatisticsInfoOut;
import com.towcent.dist.shop.portal.me.vo.output.StatisticsInfoWalletOut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.E_001;

/**
 * StatisticsServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class StatisticsServiceImpl extends BasePortalService implements StatisticsService {

    @Resource
    private MeApi meApi;

    @Override
    public ResultVo info(StatisticsInfoIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            StatisticsInfoOut out = new StatisticsInfoOut();
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> map = meApi.memberCenter(account);

            StatisticsInfoMemberOut memberOut = new StatisticsInfoMemberOut();

            StatisticsInfoWalletOut walletOut = new StatisticsInfoWalletOut();

            StatisticsInfoOrderOut orderOut = new StatisticsInfoOrderOut();

            Map<String, Object> memberMap = (Map) map.get("member");
            memberOut.setHeadimgurl(String.valueOf(memberMap.get("headimgurl")));
            memberOut.setLevelDesc(String.valueOf(memberMap.get("levelVipDesc")));
            memberOut.setLevelVip(Integer.parseInt(String.valueOf(memberMap.get("levelVip"))));
            memberOut.setNickName(String.valueOf(memberMap.get("nickName")));

            Map<String, Object> walltMap = (Map) map.get("wallet");
            walletOut.setAmount(
                    BigDecimal.valueOf(Double.parseDouble(String.valueOf(walltMap.get("amount")))));
            walletOut.setIntegral(Integer.parseInt(String.valueOf(walltMap.get("integral"))));
            walletOut.setMarginAmount(
                    BigDecimal.valueOf(Double.parseDouble(String.valueOf(walltMap.get("marginAmount")))));
            walletOut.setSettledAmount(
                    BigDecimal.valueOf(Double.parseDouble(String.valueOf(walltMap.get("settledAmount")))));

            Map<String, Object> orderMap = (Map) map.get("order");
            orderOut.setOrderToEvalNum(Integer.parseInt(String.valueOf(orderMap.get("evlNum"))));
            orderOut.setOrderToFinishNum(Integer.parseInt(String.valueOf(orderMap.get("overNum"))));
            orderOut.setOrderToPayNum(Integer.parseInt(String.valueOf(orderMap.get("payNum"))));
            orderOut.setOrderToReceNum(Integer.parseInt(String.valueOf(orderMap.get("receNum"))));
            orderOut.setTotalOrderNum(Integer.parseInt(String.valueOf(orderMap.get("totalNum"))));

            out.setMember(memberOut);
            out.setOrder(orderOut);
            out.setWallet(walletOut);

            resultVo.setData(out);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }
}
