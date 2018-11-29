package com.towcent.dist.shop.portal.mall.biz.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.mall.dto.CouponAct;
import com.towcent.dist.shop.app.client.mall.sevice.CouponApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.mall.biz.CouponService;
import com.towcent.dist.shop.portal.mall.vo.input.CouponDrawIn;
import com.towcent.dist.shop.portal.mall.vo.input.CouponListIn;
import com.towcent.dist.shop.portal.mall.vo.output.CouponListOut;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.E_001;

/**
 * CouponServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class CouponServiceImpl extends BasePortalService implements CouponService {

    @Resource
    private CouponApi couponApi;

    @Override
    public ResultVo list(CouponListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            // SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", paramIn.getMerchantId());

            List<CouponAct> list = couponApi.queryList(params);

            List<CouponListOut> outList = Lists.newArrayList();
            if (null != list && !CollectionUtils.isEmpty(list)) {
                CouponListOut out = null;
                Date currDate = new Date();
                for (CouponAct entity : list) {
                    if (!currDate.before(entity.getEndTime())) {
                        continue;
                    }

                    out = new CouponListOut();
                    BeanUtils.copyProperties(entity, out);
                    if (!currDate.after(entity.getStartTime())) {
                        out.setStatus(Constant.ACTIVITY_STATUS_UNSTARTED);
                    }

                    if (!currDate.before(entity.getStartTime()) && !currDate.after(entity.getEndTime())) {
                        out.setStatus(Constant.ACTIVITY_STATUS_STARTING);
                    }

                    outList.add(out);
                }
            }

            resultVo.setData(outList);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo draw(CouponDrawIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            couponApi.couponDraw(paramIn.getActId(), account);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }
}
