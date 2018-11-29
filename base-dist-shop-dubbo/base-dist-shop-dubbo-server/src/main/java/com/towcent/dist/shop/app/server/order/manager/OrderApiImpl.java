package com.towcent.dist.shop.app.server.order.manager;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_1;
import static com.towcent.base.common.constants.BaseConstant.NO;
import static com.towcent.base.common.constants.BaseConstant.YES;
import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_0;
import static com.towcent.dist.shop.common.Constant.COUPON_TYPE_CASH;
import static com.towcent.dist.shop.common.Constant.COUPON_TYPE_DISCOUNT;
import static com.towcent.dist.shop.common.Constant.COUPON_TYPE_FULLSUB;
import static com.towcent.dist.shop.common.Constant.DIST_SWITCH_1;
import static com.towcent.dist.shop.common.Constant.INTEGER_NUMBER_10;
import static com.towcent.dist.shop.common.Constant.ORDER_STATUS_1;
import static com.towcent.dist.shop.common.Constant.ORDER_STATUS_2;
import static com.towcent.dist.shop.common.Constant.ORDER_STATUS_4;
import static com.towcent.dist.shop.common.Constant.ORDER_STATUS_5;
import static com.towcent.dist.shop.common.Constant.ORDER_STATUS_7;
import static com.towcent.dist.shop.common.Constant.PAY_STATUS_0;
import static com.towcent.dist.shop.common.Constant.PAY_STATUS_1;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.model.LogisticsTrace;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.redis.RedisTemplateExt;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.manager.LogisticsTraceApi;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.client.mall.dto.GoodsEva;
import com.towcent.dist.shop.app.client.mall.dto.GoodsSpec;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.sevice.CouponApi;
import com.towcent.dist.shop.app.client.mall.sevice.OrderApi;
import com.towcent.dist.shop.app.client.mall.sevice.PayApi;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSkuVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderCreateVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderParamVo;
import com.towcent.dist.shop.app.client.me.dto.ConsigneeAddr;
import com.towcent.dist.shop.app.client.me.service.BrokerageApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.server.mall.service.CouponClaimService;
import com.towcent.dist.shop.app.server.mall.service.GoodsEvaService;
import com.towcent.dist.shop.app.server.mall.service.GoodsService;
import com.towcent.dist.shop.app.server.mall.service.GoodsSpecService;
import com.towcent.dist.shop.app.server.mall.service.OrderDtlService;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.common.CacheKeyUtils;
import com.towcent.dist.shop.common.ConfigUtil;
import com.towcent.dist.shop.common.Constant;

@Service
public class OrderApiImpl extends BaseService implements OrderApi {
	
    @Resource
    private GoodsEvaService goodsEvaService;
    @Resource
    private OrderMainService orderMainService;
    @Resource
    private OrderDtlService orderDtlService;
    @Resource
    private PayApi payApi;
    @Resource
    private BaseCommonApi commonApi;
    @Resource
    private RedisTemplateExt<String, Object> redisTemplateExt;
    @Resource
    private CouponClaimService couponClaimService;
    @Resource
    private GoodsSpecService goodsSpecService;
    @Resource
    private BrokerageApi brokerageApi;
    @Resource
    private SysPropertyService propertyService;
    @Resource
    private CouponApi couponApi;
    @Resource
    private GoodsService goodsService;
    @Resource
    private LogisticsTraceApi logisticsTraceApi;
    @Override
    @Transactional
    public OrderCreateVo createOrder(OrderParamVo paramVo) throws RpcException {
        try {
            OrderCreateVo vo = new OrderCreateVo();

            SysFrontAccount account = paramVo.getAccount();
            // 构造订单结构体
            OrderMain orderMain = this.generateOrderMain(paramVo);

            Integer totalQty = 0; // 商品总数量
            Integer payInter = 0; // 积分支付数额支付
            BigDecimal totalGoodsAmount = BigDecimal.ZERO; // 所有商品总金额

            List<OrderDtl> dtlList = Lists.newArrayList();
            Set<String> hashKeys = Sets.newHashSet();
            for (GoodsSkuVo sku : paramVo.getSkuList()) {
                GoodsVo goods = sku.getGoods();
                // 构造订单明细
                OrderDtl dtl = this.generateOrderDtl(goods, sku);
                dtlList.add(dtl);

                String hashKey = goods.getId() + "_" + sku.getSpec();
                hashKeys.add(hashKey);

                totalQty += sku.getQty();
                if (null != goods.getIntegral() && goods.getIntegral() > 0) {
                    payInter += sku.getQty() * goods.getIntegral();
                }
                totalGoodsAmount = totalGoodsAmount.add(dtl.getAmount());
            }

            orderMain.setTotalQty(totalQty);
            orderMain.setPayInter(BigDecimal.valueOf(payInter));
            orderMain.setTotalAmount(totalGoodsAmount); // 临时设置商品总金额(用于红包计算)

            // 计算运费
            BigDecimal freightFee = calcFreightFee();
            orderMain.setFreightFee(freightFee);
            // 红包优惠
            CouponClaim claim = this.disposeCoupon(orderMain, paramVo.getCouponId());
            // 总额需要加运费(商品数量*单价-优惠金额)
            orderMain.setTotalAmount(totalGoodsAmount.subtract(orderMain.getCouponAmount()));
            // 线上实付金额(总) (商品数量*单价+运费-优惠金额)
            orderMain.setPayAmount(orderMain.getTotalAmount().add(freightFee));

            // 创建订单
            Integer orderId = creatOrder(orderMain, dtlList);
            orderMain.setId(orderId);

            // 回写红包使用记录
            this.markCouponUse(claim);

            // 扣减商品库存
            this.deductionGoodsStock(dtlList);

            // 删除购物车记录
            this.delShopCartKey(account.getId(), hashKeys);

            BeanUtils.copyProperties(orderMain, vo);

            return vo;
        } catch (ServiceException e) {
            logger.error("创建订单失败", e);
            throw new RpcException("创建订单失败", e);
        }
    }

    /**
     * 分销佣金计算
     *
     * @param account
     * @param orderMain
     * @throws RpcException
     */
    private void calculateBkge(SysFrontAccount account, OrderMain orderMain) throws RpcException {
        try {
            String switchFlag =
                    propertyService.getSysPropertyToString(account.getMerchantId(), "dist_switch");
            if (DIST_SWITCH_1.equals(switchFlag)) {
                brokerageApi.calculateBkge(account.getId(), orderMain.getId());
            }
        } catch (ServiceException e) {
            logger.error("分销佣金计算失败", e);
            throw new RpcException("分销佣金计算失败", e);
        }
    }

    /**
     * 创建订单
     *
     * @param orderMain
     * @param orderDtllist
     * @return
     * @throws ServiceException
     */
    public Integer creatOrder(OrderMain orderMain, List<OrderDtl> orderDtllist)
            throws ServiceException {
        try {
            orderMain.setOrderNo(
                    commonApi.getSerialNo(orderMain.getMerchantId(), Constant.RULE_ORDER_NO));
            orderMainService.add(orderMain);
            for (OrderDtl orderDtl : orderDtllist) {
                orderDtl.setOrderId(orderMain.getId());
                orderDtlService.add(orderDtl);
            }
            return orderMain.getId();
        } catch (RpcException e) {
            throw new ServiceException("创建订单失败", e);
        }
    }

    /**
     * 扣减商品库存
     *
     * @param orderDtllist
     * @return
     * @throws ServiceException
     */
    public void deductionGoodsStock(List<OrderDtl> orderDtllist) throws ServiceException {
        GoodsSpec spec = null;
        for (OrderDtl entity : orderDtllist) {
            spec = new GoodsSpec();
            spec.setId(entity.getSpecId());
            spec = goodsSpecService.findById(spec);
            if (null != spec) {
                if (spec.getStock() < entity.getQty()) {
                    spec.setStock(0);
                } else {
                    spec.setStock(spec.getStock() - entity.getQty());
                }
                goodsSpecService.modifyById(spec);
            }
        }
    }

    /**
     * 标记优惠券使用状态
     *
     * @param claim
     */
    private void markCouponUse(CouponClaim claim) throws ServiceException {
        if (null != claim) {
            claim.setUseFlag(YES);
            claim.setUpdateDate(new Date());
            couponClaimService.modifyById(claim);
        }
    }

    /**
     * 修改优惠券状态
     * @param orderId
     * @param useFlag 使用状态(0:未使用 1:已使用)
     */
    @Override
    public boolean changeCouponUseFlag(Integer orderId, String useFlag) throws RpcException {
        Assert.notNull(orderId, "orderId不能为空");
        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("orderId", orderId);
            map.put("delFlag", DEL_FLAG_0);
            List<CouponClaim> list = couponClaimService.findByBiz(map);
            if (!CollectionUtils.isEmpty(list)) {
                CouponClaim claim = list.get(0);
                if (YES.equals(useFlag)) {
                    claim.setOrderId(orderId);
                } else {
                    claim.setOrderId(-1);
                }
                claim.setUseFlag(useFlag);
                claim.setUpdateDate(new Date());
                return couponClaimService.modifyById(claim) > 0;
            }
        } catch (ServiceException e) {
            logger.error("修改优惠券状态失败", e);
            throw new RpcException("修改优惠券状态失败", e);
        }
        return false;
    }

    /**
     * 构建订单对象
     *
     * @param paramVo 商品对象
     * @return
     */
    private OrderMain generateOrderMain(OrderParamVo paramVo) throws RpcException {
        ConsigneeAddr consigneeAddr = paramVo.getConsigneeAddr();

        OrderMain orderMain = new OrderMain();
        orderMain.setOrderType(BIZ_TYPE_0);
        orderMain.setOrderStatus(ORDER_STATUS_1);
        orderMain.setPayStatus(PAY_STATUS_0);
        orderMain.setPayWay(paramVo.getPayWay());

        orderMain.setConsigneeName(consigneeAddr.getConsigneeName());
        orderMain.setConsigneeAddr(consigneeAddr.getAddress());
        orderMain.setMobilePhone(consigneeAddr.getMobilePhone());

        orderMain.setIsEval(NO);

        orderMain.setCreateDate(new Date());
        orderMain.setCreateBy(paramVo.getAccount().getId());
        orderMain.setUpdateDate(new Date());
        orderMain.setUpdateBy(paramVo.getAccount().getId());
        orderMain.setMerchantId(paramVo.getAccount().getMerchantId());

        return orderMain;
    }

    /**
     * 构建订单明细对象
     *
     * @param goods 商品对象
     * @param sku
     * @return
     */
    private OrderDtl generateOrderDtl(GoodsVo goods, GoodsSkuVo sku) {
        // 获取规格名称 批发商品根据数量获取价格
        GoodsUtils.assemblySku(sku);

        OrderDtl dtl = new OrderDtl();
        dtl.setGoodsId(goods.getId());
        dtl.setGoodsName(goods.getGoodsName());
        dtl.setQty(sku.getQty());
        dtl.setSpecId(sku.getSpec());
        dtl.setSpec(sku.getSpecName());
        dtl.setPrice(sku.getPrice());
        dtl.setAmount(sku.getPrice().multiply(new BigDecimal(sku.getQty())));
        dtl.setIntegral(BigDecimal.valueOf(goods.getIntegral()));
        dtl.setGoodsPicUrl(
                GoodsUtils.getGoodsListPicUrl(
                        ConfigUtil.getUrlHeader(), goods.getMainUrls(), goods.getDescPicV())); // 商品列表图(180X180)
        dtl.setMerchantId(goods.getMerchantId());
        dtl.setEvalFlag(NO);
        return dtl;
    }

    /**
     * 处理优惠券优惠金额
     *
     * @param orderMain
     * @param couponId
     * @throws RpcException
     */
    private CouponClaim disposeCoupon(OrderMain orderMain, Integer couponId) throws RpcException {
        BigDecimal amount = BigDecimal.ZERO;
        try {
            if (couponId != null) {

                // 商品总金额
                BigDecimal totalGoodsAmount = orderMain.getTotalAmount();

                CouponClaim claim = couponApi.get(couponId, NO);
                if (null != claim) {
                    Date currDate = new Date();
                    // 判断活动是否开始或已经结束
                    if (!currDate.after(claim.getCouponAct().getStartTime())
                            || !currDate.before(claim.getCouponAct().getEndTime())) {
                        orderMain.setCouponAmount(amount);
                        return null;
                    }
                    if (COUPON_TYPE_CASH.equals(claim.getCouponAct().getActType())) { // 现金券
                        amount = claim.getAmount();
                    } else if (COUPON_TYPE_DISCOUNT.equals(claim.getCouponAct().getActType())) { // 折扣券
                        // 优惠金额 = 商品总价 * (10 - 折扣率) / 10
                        amount =
                                totalGoodsAmount
                                        .multiply(BigDecimal.valueOf(INTEGER_NUMBER_10).subtract(claim.getAmount()))
                                        .divide(BigDecimal.valueOf(INTEGER_NUMBER_10));
                    } else if (COUPON_TYPE_FULLSUB.equals(claim.getCouponAct().getActType())) { // 满减券
                        // 判断订单是否满足满减条件
                        if (totalGoodsAmount.compareTo(claim.getLimitAmount()) > 0) {
                            amount = claim.getAmount();
                        }
                    }

                    if (amount.compareTo(BigDecimal.ZERO) > 0) {
                        orderMain.setCouponAmount(amount);
                        claim.setOrderId(orderMain.getId());
                        return claim;
                    }
                }
            }
        } catch (RpcException e) {
            e.printStackTrace();
        }
        orderMain.setCouponAmount(amount);
        return null;
    }

    /**
     * 计算运费
     *
     * @return
     */
    private BigDecimal calcFreightFee() {
        return BigDecimal.ZERO;
    }

    private void delShopCartKey(Integer userId, Set<String> keys) {
        String cacheKey = CacheKeyUtils.getShoppingCartKey(userId);
        for (String hashKey : keys) {
            redisTemplateExt.hDel(cacheKey, hashKey);
        }
    }

    @Override
    public PaginationDto<OrderMain> listForPage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException {
        try {
            params.put("delFlag", DEL_FLAG_0);
            int totalCount = orderMainService.findCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<OrderMain> list = orderMainService.findByPage(page, "create_date", "DESC", params);
            if (YES.equals(params.get("containDtl")) && null != list && !CollectionUtils.isEmpty(list)) {
                for (OrderMain orderMain : list) {
                    Map<String, Object> map = Maps.newHashMap();
                    map.put("orderId", orderMain.getId());
                    List<OrderDtl> sublist = orderDtlService.findByBiz(map);
                    orderMain.setOrderDtlList(sublist);
                }
            }
            return new PaginationDto<>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询订单分页列表失败", e);
            throw new RpcException("", "查询订单分页列表", e);
        }
    }

    @Override
    public boolean orderDel(Integer orderId, SysFrontAccount account) throws RpcException {
        Assert.notNull(orderId, "orderId不能为空");
        try {
            OrderMain orderMain = getOrderDetailById(orderId);
            if (null == orderMain || (null != orderMain && DEL_FLAG_1.equals(orderMain.getDelFlag()))) {
                throw new ServiceException("订单已不存在");
            }

            if (!(ORDER_STATUS_4.equals(orderMain.getOrderStatus())
                    || ORDER_STATUS_5.equals(orderMain.getOrderStatus())
                    || ORDER_STATUS_7.equals(orderMain.getOrderStatus()))) {
                throw new ServiceException("订单状态不正确");
            }

            orderMain.setDelFlag(DEL_FLAG_1);
            return orderMainService.modifyById(orderMain) > 0;
        } catch (ServiceException e) {
            logger.error("删除订单失败", e);
            throw new RpcException("", "删除订单失败", e);
        }
    }

    @Override
    @Transactional
    public boolean orderCancel(Integer orderId, SysFrontAccount account) throws RpcException {
        Assert.notNull(orderId, "orderId不能为空");
        try {
            OrderMain orderMain = getOrderDetailById(orderId, true);
            if (null == orderMain || (null != orderMain && DEL_FLAG_1.equals(orderMain.getDelFlag()))) {
                throw new ServiceException("订单已不存在");
            }

            if (!ORDER_STATUS_1.equals(orderMain.getOrderStatus())) {
                throw new ServiceException("订单状态不正确");
            }

            if (PAY_STATUS_1.equals(orderMain.getPayStatus())) { // 已付款取消订单
                // 关闭交易
                payApi.closeTrade(orderId, false);
            }
            
            // 修改优惠券使用状态
            changeCouponUseFlag(orderMain.getId(), NO);
            
            // 退还商品库存
            revertGoodsStock(orderMain.getOrderDtlList());
            
            orderMain.setOrderStatus(ORDER_STATUS_5);
            return orderMainService.modifyById(orderMain) > 0;
        } catch (ServiceException e) {
            logger.error("取消订单失败", e);
            throw new RpcException("", "取消订单失败", e);
        }
    }

    /**
     * 退还商品库存
     *
     * @param orderDtllist
     * @return
     * @throws ServiceException
     */
    public void revertGoodsStock(List<OrderDtl> orderDtllist) throws ServiceException {
        try {
        	if (CollectionUtils.isEmpty(orderDtllist)) {
        		return;
        	}
            GoodsSpec spec = null;
            for (OrderDtl entity : orderDtllist) {
                spec = new GoodsSpec();
                spec.setId(entity.getSpecId());
                spec = goodsSpecService.findById(spec);
                if (null != spec) {
                    spec.setStock(spec.getStock() + entity.getQty());
                    goodsSpecService.modifyById(spec);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("退还商品库存失败", e);
        }
    }

    @Override
    public boolean orderReceipt(Integer orderId, SysFrontAccount account) throws RpcException {
        Assert.notNull(orderId, "orderId不能为空");
        try {
            OrderMain orderMain = getOrderDetailById(orderId);
            if (null == orderMain || (null != orderMain && DEL_FLAG_1.equals(orderMain.getDelFlag()))) {
                throw new ServiceException("订单已不存在");
            }

            if (!(ORDER_STATUS_2.equals(orderMain.getOrderStatus())
                    && PAY_STATUS_1.equals(orderMain.getPayStatus()))) {
                throw new ServiceException("订单状态不正确");
            }

            orderMain.setOrderStatus(ORDER_STATUS_4);
            boolean rs = orderMainService.modifyById(orderMain) > 0;
            if (rs) {
                // 分销佣金计算(分配佣金需要等到订单完结才能进行)
                this.calculateBkge(account, orderMain);
            }
            return rs;
        } catch (ServiceException e) {
            logger.error("确认收货失败", e);
            throw new RpcException("", "确认收货失败", e);
        }
    }

    @Override
    public PaginationDto<OrderDtl> queryOrderDtlPage(
            Map<String, Object> params, SimplePageDto pageDto) throws RpcException {
        try {
            int totalCount = orderDtlService.findCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<OrderDtl> list = orderDtlService.findByPage(page, "id", "DESC", params);
            return new PaginationDto<>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询订单详情列表失败", e);
            throw new RpcException("", "查询订单详情列表失败", e);
        }
    }

    @Override
    public PaginationDto<OrderDtl> queryEvalPage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException {
        try {
            int totalCount = orderDtlService.selectEvalCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<OrderDtl> list = orderDtlService.selectEvalByPage(page, params);
            return new PaginationDto<>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询订单详情列表失败", e);
            throw new RpcException("", "查询订单详情列表失败", e);
        }
    }

    @Override
    @Transactional
    public boolean orderEval(GoodsEva entity) throws RpcException {
        Assert.notNull(entity.getOrderDtlId(), "orderDtlId不能为空");
        Assert.notNull(entity.getUserId(), "userId不能为空");
        try {
            OrderDtl dtl = new OrderDtl();
            dtl.setId(entity.getOrderDtlId());
            dtl = orderDtlService.findById(dtl);
            if (null == dtl) {
                throw new ServiceException("订单已不存在");
            }

            if (YES.equals(dtl.getEvalFlag())) {
                throw new ServiceException("订单商品已评价");
            }

            OrderMain orderMain = getOrderDetailById(dtl.getOrderId(), true);
            if (null == orderMain || (null != orderMain && DEL_FLAG_1.equals(orderMain.getDelFlag()))) {
                throw new ServiceException("订单已不存在");
            }

            if (!ORDER_STATUS_4.equals(orderMain.getOrderStatus())) {
                throw new ServiceException("订单状态不正确");
            }

            List<OrderDtl> dtlList = orderMain.getOrderDtlList();
            if (!CollectionUtils.isEmpty(dtlList)) {
                int num = 0;
                for (OrderDtl orderDtl : dtlList) {
                    if (YES.equals(orderDtl.getEvalFlag())) {
                        num++;
                    }
                }

                if (num == dtlList.size()) {
                    // 修改订单评价状态
                    orderMain.setIsEval(YES);
                    orderMainService.modifyById(orderMain);
                }
            }

            entity.setGoodsId(dtl.getGoodsId());
            entity.setOrderId(orderMain.getId());
            entity.setCreateBy(entity.getUserId());
            entity.setUpdateBy(entity.getUserId());
            entity.setCreateDate(new Date());
            entity.setUpdateDate(new Date());
            int rs = goodsEvaService.add(entity);
            if (rs > 0) {
                // 修改订单详情评价状态
                dtl.setEvalFlag(YES);
                orderDtlService.modifyById(dtl);

                //修改商品评价信息
                modifGoodsEvalRate(entity.getGoodsId(), Integer.parseInt(entity.getEvaStar()));
                return true;
            }

            return false;
        } catch (ServiceException e) {
            logger.error("订单商品评价失败", e);
            throw new RpcException("", "订单商品评价失败", e);
        }
    }

    /**
     * 修改商品评价信息
     *
     * @param goodsId
     * @param evalStar
     * @throws ServiceException
     */
    private void modifGoodsEvalRate(Integer goodsId, Integer evalStar) throws ServiceException {
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods = goodsService.findById(goods);
        if (null != goods) {
            Integer evalNum = goods.getEvaNum();
            evalNum = null == evalNum ? 0 : evalNum;
            goods.setEvaNum(evalNum + 1);

            if (evalStar > 3) {
                goods.setGoodEvalRate(goods.getGoodEvalRate().add(BigDecimal.ONE));

            }
            goodsService.modifyById(goods);
        }
    }

    @Override
    public OrderMain getOrderDetailById(Integer id) throws RpcException {
        return getOrderDetailById(id, false);
    }

    @Override
    public OrderMain getOrderDetailById(Integer id, boolean containDtl) throws RpcException {
        Assert.notNull(id, "订单ID不能为空");
        try {
            OrderMain entity = new OrderMain();
            entity.setId(id);
            entity = orderMainService.findById(entity);
            if (containDtl && null != entity) {
                Map<String, Object> params = Maps.newHashMap();
                params.put("orderId", id);
                List<OrderDtl> list = orderDtlService.findByBiz(params);
                entity.setOrderDtlList(list);
            }
            return entity;
        } catch (ServiceException e) {
            logger.error("获取订单详情失败", e);
            throw new RpcException("获取订单详情失败", e);
        }
    }

	@Override
	public List<LogisticsTrace> getLogisticsTrace(String logisticsNo,
			String freightNumber) throws RpcException {
		List<LogisticsTrace> list = Lists.newArrayList();
	    try {
	    	list = logisticsTraceApi.getLogisticsTrace(logisticsNo, freightNumber);
        } catch (Exception e) {
            logger.error("获取物流跟踪详情失败", e);
            throw new RpcException("获取物流跟踪详情失败", e);
        }
		return list;
	}

	@Override
	public void updateLogisticsTrace(Integer userId) throws RpcException {
		try {
			// 查询订单
			Map<String, Object> params = Maps.newHashMap();
			params.put("createBy", userId);
			params.put("delFlag", DEL_FLAG_0);
			params.put("orderStatus", Constant.ORDER_STATUS_2);
			List<OrderMain> list = orderMainService.findByBiz(params);
			for (final OrderMain orderMain : list) {
				if(null != orderMain){
					logisticsTraceApi.updateLogisticsTrace(orderMain.getMerchantId(), orderMain.getLogisticsNo(), orderMain.getFreightNumber());
				}
			}
		} catch (ServiceException e) {
			 logger.error("更新物流信息失败", e);
	         throw new RpcException("更新物流信息失败", e);
		}
	}
}
