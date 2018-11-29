package com.towcent.dist.shop.app.client.me.service;

import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.dist.shop.app.client.me.dto.ConcernGoods;
import com.towcent.dist.shop.app.client.me.dto.ConsigneeAddr;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

/**
 * 会员中心 接口
 *
 * @author yxp
 */
public interface MeApi {

  /**
   * 删除收藏商品信息
   *
   * @param account
   * @return
   * @throws RpcException
   */
  Map<String, Object> memberCenter(SysFrontAccount account) throws RpcException;

  /**
   * 查询收获地址分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<ConsigneeAddr> queryConsigneeAddrPage(
      Map<String, Object> params, SimplePageDto pageDto) throws RpcException;

  /**
   * 保存收获地址信息
   *
   * @param entity
   * @return
   * @throws RpcException
   */
  boolean saveConsigneeAddr(ConsigneeAddr entity) throws RpcException;

  /**
   * 删除收获地址信息
   *
   * @param id
   * @return
   * @throws RpcException
   */
  boolean delConsigneeAddr(Integer id) throws RpcException;

  /**
   * 获取收获地址信息
   *
   * @param id
   * @return
   * @throws RpcException
   */
  ConsigneeAddr getConsigneeAddr(Integer id) throws RpcException;

  /**
   * 查询收藏商品分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<ConcernGoods> queryCollectGoodsPage(
      Map<String, Object> params, SimplePageDto pageDto) throws RpcException;

  /**
   * 删除收藏商品信息
   *
   * @param id
   * @return
   * @throws RpcException
   */
  boolean delConcernGoods(Integer id) throws RpcException;

}
