package com.towcent.dist.shop.app.client.mall.sevice;

import java.util.List;
import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.model.LogisticsTrace;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.dist.shop.app.client.mall.dto.GoodsEva;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.vo.OrderCreateVo;
import com.towcent.dist.shop.app.client.mall.vo.OrderParamVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

/**
 * goods 接口
 *
 * @author yxp
 */
public interface OrderApi {

  /**
   * 创建订单接口
   *
   * @param paramVo
   * @return
   * @throws RpcException
   */
  OrderCreateVo createOrder(OrderParamVo paramVo) throws RpcException;

  /**
   * 查询商品分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<OrderMain> listForPage(Map<String, Object> params, SimplePageDto pageDto)
      throws RpcException;

  /**
   * 订单删除
   *
   * @param orderId
   * @param account
   * @return
   * @throws RpcException
   */
  boolean orderDel(Integer orderId, SysFrontAccount account) throws RpcException;

  /**
   * 订单取消
   *
   * @param orderId
   * @param account
   * @return
   * @throws RpcException
   */
  boolean orderCancel(Integer orderId, SysFrontAccount account) throws RpcException;

  /**
   * 订单确认收货
   *
   * @param orderId
   * @param account
   * @return
   */
  boolean orderReceipt(Integer orderId, SysFrontAccount account) throws RpcException;

  /**
   * 查询订单详情分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<OrderDtl> queryOrderDtlPage(Map<String, Object> params, SimplePageDto pageDto)
      throws RpcException;

  /**
   * 查询订单详情分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<OrderDtl> queryEvalPage(Map<String, Object> params, SimplePageDto pageDto)
      throws RpcException;

  /**
   * 订单商品评价
   *
   * @param entity
   * @return
   * @throws RpcException
   */
  boolean orderEval(GoodsEva entity) throws RpcException;

  /**
   * 通过Id查询订单详情(不包含明细)
   *
   * @param id
   * @return
   * @throws RpcException
   */
  OrderMain getOrderDetailById(Integer id) throws RpcException;

  /**
   * 通过Id查询订单详情
   *
   * @param id
   * @param containDtl (是否包含订单明细)
   * @return
   * @throws RpcException
   */
  OrderMain getOrderDetailById(Integer id, boolean containDtl) throws RpcException;

  /**
   * 修改优惠券状态
   *
   * @param orderId
   * @param useFlag 使用状态(0:未使用 1:已使用)
   * @return
   * @throws RpcException
   */
  boolean changeCouponUseFlag(Integer orderId, String useFlag) throws RpcException;
  
  /**
   * 查询物流跟踪信息
   * @param logisticsNo
   * @param freightNumber
   * @return
   * @throws RpcException
   */
  List<LogisticsTrace> getLogisticsTrace(String logisticsNo, String freightNumber) throws RpcException;
  
  /**
   * 通过用户账号更新物流信息
   * @param userId
   * @throws RpcException
   */
  void updateLogisticsTrace(Integer userId) throws RpcException;
}
