package com.towcent.dist.shop.portal.mall.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_000;
import static com.towcent.base.common.constants.BaseConstant.E_001;
import static com.towcent.base.common.constants.BaseConstant.E_002;
import static com.towcent.base.common.constants.BaseConstant.E_003;
import static com.towcent.base.common.constants.BaseConstant.NO;
import static com.towcent.base.common.constants.BaseConstant.YES;
import static com.towcent.dist.shop.common.Constant.ORDER_STATUS_4;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.model.LogisticsTrace;
import com.towcent.base.common.model.SysDictDtl;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.dist.shop.app.client.mall.dto.GoodsEva;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.sevice.GoodsApi;
import com.towcent.dist.shop.app.client.mall.sevice.OrderApi;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSkuVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderCreateVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderDtlItemVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderInfoVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderParamVo;
import com.towcent.dist.shop.app.client.me.dto.ConsigneeAddr;
import com.towcent.dist.shop.app.client.me.service.MeApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.mall.biz.OrderService;
import com.towcent.dist.shop.portal.mall.vo.input.LogisTraceIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderCancelIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderCreateIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderDelIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderDetailIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderEvalIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderEvalListIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderListIn;
import com.towcent.dist.shop.portal.mall.vo.input.OrderReceiptIn;
import com.towcent.dist.shop.portal.mall.vo.output.LogisTraceDetailOut;
import com.towcent.dist.shop.portal.mall.vo.output.LogisTraceOut;
import com.towcent.dist.shop.portal.mall.vo.output.OrderEvalListOut;
import com.towcent.dist.shop.portal.mall.vo.output.OrderListOut;

/**
 * OrderServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class OrderServiceImpl extends BasePortalService implements OrderService {

    @Resource
    private OrderApi orderApi;

    @Resource
    private MeApi meApi;

    @Resource
    private GoodsApi goodsApi;

    @Resource
    private SysFrontAccountApi sysFrontAccountApi;

    @Resource
    private BaseCommonApi baseCommonApi;

    @Override
    public ResultVo create(OrderCreateIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        SysFrontAccount account = UserUtils.getUserAccount(paramIn);

        List<GoodsSkuVo> skuList;
        try {
            skuList = convert2GoodsSku(paramIn.getGoodsStr());
        } catch (Exception e) {
            return assemblyVo(resultVo, E_002, "商品字符串(商品Id:数量:规格)格式错误");
        }

        try {

            OrderParamVo paramVo = new OrderParamVo();
            BeanUtils.copyProperties(paramIn, paramVo);
            paramVo.setSkuList(skuList);

            paramVo.setAccount(account);

            ConsigneeAddr consign = null;
            if (paramIn.getConsigneeAddrId().intValue() > 0) {
                consign = meApi.getConsigneeAddr(paramIn.getConsigneeAddrId());
                if (consign == null || !consign.getUserId().equals(account.getId()))
                    return assemblyVo(resultVo, E_002, "用户收获地址有误");
            }

            paramVo.setConsigneeAddr(consign);

            for (GoodsSkuVo sku : skuList) {
                if (sku.getQty() <= 0)
                    return assemblyVo(resultVo, E_002, "商品ID为[" + sku.getId() + "]的商品数量设置有误，必须为大于0的整数");
                GoodsVo goods = goodsApi.queryGoodsById(sku.getId());
                if (goods == null) return assemblyVo(resultVo, E_002, "无法找到商品ID[" + sku.getId() + "]的商品");
                if (goods.getId() != -1 && !account.getMerchantId().equals(goods.getMerchantId()))
                    return assemblyVo(resultVo, E_002, "一个订单的商品必须为同一商家的商品！");

                sku.setGoods(goods);
            }

            OrderCreateVo vo = orderApi.createOrder(paramVo);

            memberAccount(account, vo);

            resultVo.setData(vo);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "创建订单失败");
            logger.error("创建订单失败", e);
        }

        return resultVo;
    }

    /**
     * 会员账户信息
     *
     * @param account
     * @param vo
     */
    private void memberAccount(SysFrontAccount account, OrderCreateVo vo) {
        try {
            account = sysFrontAccountApi.getAccountById(account.getId());
            vo.setBalance(account.getAmount());
            vo.setIntegral(account.getInter());
        } catch (RpcException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拆分商品明细结构
     *
     * @param goodsStr
     * @return
     * @throws Exception
     */
    private List<GoodsSkuVo> convert2GoodsSku(String goodsStr) throws Exception {
        String[] arrays = StringUtils.split(goodsStr, ";");
        List<GoodsSkuVo> list = Lists.newArrayList();
        for (String array : arrays) {
            if (StringUtils.isNotBlank(array)) {
                list.add(new GoodsSkuVo(array));
            }
        }
        return list;
    }

    @Override
    public ResultVo list(OrderListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SimplePageDto page = this.buildPage(paramIn);

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("tabFlag", paramIn.getTabFlag());
            params.put("createBy", account.getId());
            params.put("containDtl", YES);

            PaginationDto<OrderMain> PageDto = orderApi.listForPage(params, page);

            List<OrderListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                OrderListOut out = null;
                Map<String, SysDictDtl> sysDictDtlMap = baseCommonApi.getDictMapByKey(account.getMerchantId(), "order_status");
                Map<String, SysDictDtl> payMap = baseCommonApi.getDictMapByKey(account.getMerchantId(), "pay_status");
                for (OrderMain entity : PageDto.getList()) {
                    out = new OrderListOut();
                    BeanUtils.copyProperties(entity, out);
                    out.setOrderId(entity.getId());
                    out.setOrderDtl(convertOrderDtlItemList(entity.getOrderDtlList()));
                    out.setOrderStatuDesc(sysDictDtlMap.get(entity.getOrderStatus()).getName());
                    out.setPayStatusDesc(payMap.get(entity.getPayStatus()).getName());
                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<OrderListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo evalList(OrderEvalListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }

        try {
            SimplePageDto page = this.buildPage(paramIn);

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("orderStatus", ORDER_STATUS_4);
            if (null != paramIn.getOrderId()) {
                params.put("orderId", paramIn.getOrderId());
            } else {
                params.put("evalFlag", NO);
                params.put("createBy", account.getId());
            }

            PaginationDto<OrderDtl> PageDto = orderApi.queryEvalPage(params, page);

            List<OrderEvalListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                OrderEvalListOut out = null;
                for (OrderDtl entity : PageDto.getList()) {
                    out = new OrderEvalListOut();
                    BeanUtils.copyProperties(entity, out);
                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<OrderEvalListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }

        return resultVo;
    }

    @Override
    public ResultVo del(OrderDelIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            orderApi.orderDel(paramIn.getOrderId(), account);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo eval(OrderEvalIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            GoodsEva entity = new GoodsEva();
            BeanUtils.copyProperties(paramIn, entity);
            entity.setCreateBy(account.getId());
            entity.setUserId(account.getId());
            entity.setMerchantId(account.getMerchantId());

            orderApi.orderEval(entity);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo cancel(OrderCancelIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            orderApi.orderCancel(paramIn.getOrderId(), account);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo receipt(OrderReceiptIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            orderApi.orderReceipt(paramIn.getOrderId(), account);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo detail(OrderDetailIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }

        try {

            OrderInfoVo orderVo = new OrderInfoVo();

            OrderMain order = orderApi.getOrderDetailById(paramIn.getOrderId(), true);
            if (null == order) {
                return assemblyVo(resultVo, E_003, "订单不存在");
            }

            // 查询订单明细
            List<OrderDtl> odlist = order.getOrderDtlList();
            BeanUtils.copyProperties(order, orderVo);

            orderVo.setOrderDtlList(convertOrderDtlItemList(odlist));

            Map<String, SysDictDtl> sysDictDtlMap = baseCommonApi.getDictMapByKey(order.getMerchantId(), "order_status");
            orderVo.setOrderStatusDesc(sysDictDtlMap.get(orderVo.getOrderStatus()).getName());

            orderVo.setPayStatusDesc(baseCommonApi.getDictByKeyVal(order.getMerchantId(), "pay_status", orderVo.getPayStatus()).getName());
            orderVo.setPayWayDesc(baseCommonApi.getDictByKeyVal(order.getMerchantId(), "pay_way", orderVo.getPayWay()).getName());

            resultVo.setData(orderVo);
            assemblyVo(resultVo, E_000, "查询订单成功");

        } catch (Exception e) {
            assemblyVo(resultVo, E_001, "查询订单失败");
            logger.error("查询订单失败", e);
        }
        return resultVo;
    }

    private List<OrderDtlItemVo> convertOrderDtlItemList(List<OrderDtl> odlist) {
        List<OrderDtlItemVo> list = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(odlist)) {
            for (OrderDtl od : odlist) {
                OrderDtlItemVo item = new OrderDtlItemVo();
                BeanUtils.copyProperties(od, item);
                list.add(item);
            }
        }
        return list;
    }
    
    @Override
	public ResultVo trace(LogisTraceIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			LogisTraceOut outParam = new LogisTraceOut();
			OrderMain orderMain = orderApi.getOrderDetailById(Integer
					.parseInt(paramIn.getOrderId()));
			if (account.getId().equals(orderMain.getCreateBy())) {
				if (StringUtils.isNotEmpty(orderMain.getFreightNumber())) {
					List<LogisTraceDetailOut> outList = Lists.newArrayList();
					List<LogisticsTrace> list = orderApi.getLogisticsTrace(orderMain.getLogisticsNo(), orderMain.getFreightNumber());
					if(null != list && list.size() > 0){
						LogisTraceDetailOut logisTraceDetailOut = null;
						for (LogisticsTrace logisticsTrace : list) {
							if(null != logisticsTrace){
								logisTraceDetailOut = new LogisTraceDetailOut();
								
								BeanUtils.copyProperties(logisticsTrace, logisTraceDetailOut);
								outList.add(logisTraceDetailOut);
							}
						}
					}
					outParam.setFreightNumber(orderMain.getFreightNumber());
					outParam.setLogisticsName(orderMain.getLogisticsName());
					outParam.setLogisTraceList(outList);
					resultVo.setData(outParam);
				} else {
					assemblyVo(resultVo, E_001, "此订单还未发货暂时无法查询快递信息");
				}
			} else {
				assemblyVo(resultVo, E_001, "您无权限访问此订单");
			}
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}
}
