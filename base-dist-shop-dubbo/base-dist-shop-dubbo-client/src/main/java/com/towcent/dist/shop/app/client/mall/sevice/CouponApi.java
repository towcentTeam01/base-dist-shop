package com.towcent.dist.shop.app.client.mall.sevice;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.dist.shop.app.client.mall.dto.CouponAct;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Coupon 接口
 *
 * @author yxp
 */
public interface CouponApi {

  /**
   * 查询优惠券分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<CouponAct> listForPage(Map<String, Object> params, SimplePageDto pageDto)
      throws RpcException;

  /**
   * 查询优惠券列表信息
   *
   * @param params
   * @return
   * @throws RpcException
   */
  List<CouponAct> queryList(Map<String, Object> params) throws RpcException;

  /**
   * 领取优惠券
   *
   * @param actId
   * @param account
   * @return
   * @throws RpcException
   */
  boolean couponDraw(Integer actId, SysFrontAccount account) throws RpcException;

  /**
   * 获取优惠券信息
   *
   * @param couponId
   * @return
   * @throws RpcException
   */
  CouponClaim get(Integer couponId) throws RpcException;

  /**
   * 获取优惠券信息
   *
   * @param couponId
   * @param useFlag 使用状态(0:未使用 1:已使用)
   * @return
   * @throws RpcException
   */
  CouponClaim get(Integer couponId, String useFlag) throws RpcException;

  /**
   * 处理优惠券优惠金额
   *
   * @param totalGoodsAmount
   * @param couponId
   * @throws RpcException
   */
  BigDecimal calcCoupon(BigDecimal totalGoodsAmount, Integer couponId) throws RpcException;

  /**
   * 获取优惠券列表
   *
   * @param params
   * @return
   * @throws RpcException
   */
  List<CouponClaim> queryCouponList(Map<String, Object> params) throws RpcException;
}
