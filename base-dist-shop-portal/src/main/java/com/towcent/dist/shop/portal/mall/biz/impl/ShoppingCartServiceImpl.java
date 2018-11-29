package com.towcent.dist.shop.portal.mall.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;
import static com.towcent.base.common.constants.BaseConstant.E_003;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.towcent.dist.shop.portal.mall.vo.output.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.service.BasePortalService;

import static com.towcent.base.common.utils.PreciseCompute.*;

import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.mall.sevice.CouponApi;
import com.towcent.dist.shop.app.client.mall.sevice.ShoppingCartApi;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSkuVo;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartParamVo;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartVo;
import com.towcent.dist.shop.app.client.me.service.MeApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.mall.biz.ShoppingCartService;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartAddIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartConfirmIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartDelIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartEditIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartGoodsQtyIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartListIn;
import com.towcent.dist.shop.portal.me.biz.ConsigneeAddrService;

/**
 * ShoppingCartServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class ShoppingCartServiceImpl extends BasePortalService implements ShoppingCartService {

    @Resource
    private ShoppingCartApi shoppingCartApi;
    @Resource
    private SysFrontAccountApi sysFrontAccountApi;
    @Resource
    private MeApi meApi;
    @Resource
    private ConsigneeAddrService consigneeAddrService;
    @Resource
    private CouponApi couponApi;

    @Override
    public ResultVo add(ShoppingCartAddIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        if (paramIn.getQty() <= 0) {
            return assemblyVo(resultVo, E_001, "商品数量必须大于0");
        }
        try {

            ShoppingCartPriceOut out = new ShoppingCartPriceOut();

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            ShoppingCartParamVo paramVo = new ShoppingCartParamVo();
            BeanUtils.copyProperties(paramIn, paramVo);
            paramVo.setUserId(account.getId());
            paramVo.setMerchantId(account.getMerchantId());

            GoodsSkuVo skuVo = shoppingCartApi.add(paramVo);

            out.setPrice(skuVo.getPrice());
            resultVo.setData(out);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo del(ShoppingCartDelIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            ShoppingCartParamVo paramVo = new ShoppingCartParamVo();
            paramVo.setUserId(account.getId());
            paramVo.setMerchantId(account.getMerchantId());
            paramVo.setKeys(paramIn.getKeys());

            shoppingCartApi.batchDel(paramVo);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo edit(ShoppingCartEditIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        if (paramIn.getQty() <= 0) {
            return assemblyVo(resultVo, E_001, "商品数量必须大于0");
        }
        try {
            ShoppingCartPriceOut out = new ShoppingCartPriceOut();

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            ShoppingCartParamVo paramVo = new ShoppingCartParamVo();
            BeanUtils.copyProperties(paramIn, paramVo);
            paramVo.setUserId(account.getId());
            paramVo.setMerchantId(account.getMerchantId());

            GoodsSkuVo skuVo = shoppingCartApi.edit(paramVo);

            out.setPrice(skuVo.getPrice());
            resultVo.setData(out);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo list(ShoppingCartListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            ShoppingCartParamVo paramVo = new ShoppingCartParamVo();
            paramVo.setUserId(account.getId());
            paramVo.setMerchantId(account.getMerchantId());

            List<ShoppingCartVo> list = shoppingCartApi.list(paramVo);

            resultVo.setData(list);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo goodsQty(ShoppingCartGoodsQtyIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            ShoppingCartGoodsQtyOut outParam = new ShoppingCartGoodsQtyOut();

            if (!UserUtils.isExistSession(paramIn.getTokenId())) {
                outParam.setShopCartNum(0);
            } else {
                SysFrontAccount account = UserUtils.getUserAccount(paramIn);
                ShoppingCartParamVo paramVo = new ShoppingCartParamVo();
                paramVo.setUserId(account.getId());
                paramVo.setMerchantId(account.getMerchantId());

                int num = shoppingCartApi.getCartQty(paramVo);
                outParam.setShopCartNum(num);
            }

            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo confirm(ShoppingCartConfirmIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            String[] goodsIdStrs = StringUtils.split(paramIn.getGoodsStr(), ";");
            Integer[] goodsIds = new Integer[goodsIdStrs.length];
            Integer[] qtys = new Integer[goodsIdStrs.length];
            Integer[] specs = new Integer[goodsIdStrs.length];

            // 填充商品规格数组
            this.splitGoodsString(goodsIdStrs, goodsIds, qtys, specs);

            ShoppingCartConfirmOut vo = new ShoppingCartConfirmOut();
            Integer merchantId = account.getMerchantId();

            BigDecimal totalPrice = BigDecimal.ZERO;
            BigDecimal payAmount = BigDecimal.ZERO;
            Integer totalQty = 0;
            BigDecimal freightFee = BigDecimal.ZERO;
            Integer integral = 0;

            ShoppingCartParamVo paramVo = null;
            for (int i = 0; i < goodsIds.length; i++) {
                ShoppingCartVo shoppingCartVo = null;
                paramVo = new ShoppingCartParamVo();
                paramVo.setUserId(account.getId());
                paramVo.setMerchantId(account.getMerchantId());
                paramVo.setGoodsId(goodsIds[i]);
                paramVo.setSpec(specs[i]);
                paramVo.setQty(qtys[i]);

                shoppingCartVo = shoppingCartApi.get(paramVo);
                totalPrice = totalPrice.add(multiply(shoppingCartVo.getPrice(), shoppingCartVo.getQty()));
                integral += shoppingCartVo.getIntegral() * shoppingCartVo.getQty();
                totalQty += shoppingCartVo.getQty();

                if (!merchantId.equals(shoppingCartVo.getMerchantId())) {
                    return assemblyVo(resultVo, E_003, "商品Id字符串格式(同一订单只能为统一商家的商品)有误");
                }
                vo.getGoodsList().add(shoppingCartVo);
            }

            payAmount = totalPrice.add(freightFee);

            // 计算优惠券金额
            BigDecimal couponAmount = couponApi.calcCoupon(totalPrice, paramIn.getCouponId());
            payAmount = payAmount.subtract(couponAmount);

            vo.setTotalPrice(totalPrice);
            vo.setTotalQty(totalQty);
            vo.setIntegral(integral);
            vo.setFreightFee(freightFee);
            vo.setPayAmount(payAmount);

            // 会员账户信息
            this.memberAccount(account, vo);
            // 获取可用优惠券列表信息
            this.couponList(account, vo);

            // 默认地址
            ResultVo addrVo = consigneeAddrService.getDefault(paramIn);
            vo.setConsigneeAddr(addrVo.getData());

            resultVo.setData(vo);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }

    /**
     * 填充数据.
     *
     * @param goodsIdStrs 商品Id:数量:规格
     * @param goodsIds
     * @param qtys
     * @param specs
     * @throws RpcException
     */
    private void splitGoodsString(String[] goodsIdStrs, Integer[] goodsIds, Integer[] qtys, Integer[] specs) throws RpcException {
        for (int i = 0; i < goodsIdStrs.length; i++) {
            try {
                String[] idStrs = StringUtils.split(goodsIdStrs[i], ":");
                if (idStrs.length == 2) {
                    goodsIds[i] = Integer.parseInt(idStrs[0]);
                    specs[i] = Integer.valueOf(idStrs[1]);
                } else {
                    goodsIds[i] = Integer.parseInt(idStrs[0]);
                    qtys[i] = Integer.parseInt(idStrs[1]);
                    specs[i] = Integer.valueOf(idStrs[2]);
                }
            } catch (Exception e) {
                throw new RpcException("", "商品字符串格式不正确", e);
            }
        }
    }

    private void memberAccount(SysFrontAccount account, ShoppingCartConfirmOut vo) {
        try {
            account = sysFrontAccountApi.getAccountById(account.getId());
            MemberAccountOut accountOut = new MemberAccountOut();
            accountOut.setBalance(account.getAmount());
            accountOut.setIntegral(account.getInter());
            vo.setMemberAccount(accountOut);
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }

    /**
     * 优惠券列表信息
     *
     * @param account
     * @param vo
     */
    private void couponList(SysFrontAccount account, ShoppingCartConfirmOut vo) {
        try {

            List<CouponClaimOut> listOut = Lists.newArrayList();

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("userId", account.getId());
            params.put("useFlag", Constant.COUPON_STATUS_UNUSED);

            List<CouponClaim> list = couponApi.queryCouponList(params);

            int num = 0;
            if (!CollectionUtils.isEmpty(list)) {
                CouponClaimOut out = null;
                Date currDate = new Date();
                for (CouponClaim claim : list) {
                    // 判断活动是否开始或已经结束
                    if (!currDate.after(claim.getCouponAct().getStartTime())
                            || !currDate.before(claim.getCouponAct().getEndTime())) {
                        continue;
                    }
                    if (Constant.COUPON_TYPE_FULLSUB.equals(claim.getCouponAct().getActType())) {
                        int sub = claim.getLimitAmount().compareTo(vo.getTotalPrice());
                        if (sub > 0) {
                            continue;
                        }
                    }
                    out = new CouponClaimOut();
                    out.setActId(claim.getCouponAct().getId());
                    out.setActName(claim.getCouponAct().getActName());
                    out.setId(claim.getId());
                    out.setAmount(claim.getAmount());
                    out.setLimitAmount(claim.getLimitAmount());
                    out.setType(claim.getCouponAct().getActType());
                    listOut.add(out);
                    num++;
                }
            }
            vo.setCouponCount(num);
            vo.setCouponList(listOut);
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }
}
