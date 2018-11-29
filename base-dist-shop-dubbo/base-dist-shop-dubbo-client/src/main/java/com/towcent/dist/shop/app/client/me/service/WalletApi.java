/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-client
 * @Title : WalletApi.java
 * @Package : com.towcent.dist.shop.app.client.me.service
 * @date : 2018年6月30日下午12:02:23
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.me.service;

import java.math.BigDecimal;
import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.me.vo.AccountRecordVo;
import com.towcent.dist.shop.app.client.sys.dto.SysWithdrawApply;

/**
 * @ClassName: WalletApi 
 * @Description: 钱包相关接口 
 *
 * @author huangtao
 * @date 2018年6月30日 下午12:02:23
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface WalletApi {

  /**
   * 查询交易记录分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<AccountRecordVo> queryIncomePage(Map<String, Object> params, SimplePageDto pageDto)
      throws RpcException;

  /**
   * 查询优惠券领取分页列表信息
   *
   * @param params
   * @param pageDto
   * @return
   * @throws RpcException
   */
  PaginationDto<CouponClaim> queryCouponPage(Map<String, Object> params, SimplePageDto pageDto)
      throws RpcException;

  /**
   * 提现申请.
   * @Title withdrawApply
   * @param userId 会员Id
   * @param amount 提现金额
   * @return
   * @throws RpcException
   */
  boolean withdrawApply(Integer userId, BigDecimal amount) throws RpcException;
  
  /**
   * 提现记录列表.
   * @Title withdrawList
   * @param userId  会员Id
   * @param pageDto 分页对象
   * @return
   * @throws RpcException
   */
  PaginationDto<SysWithdrawApply> withdrawList(Integer userId, SimplePageDto pageDto) throws RpcException;
  
  /**
   * <b>商户角色</b>能处理<br>
   * 标记提现申请为已处理(手工处理).
   * @Title withdrawMark
   * @param userId   用户Id
   * @param applyId  提现申请Id
   * @return
   * @throws RpcException
   */
  boolean withdrawMark(Integer userId, Integer applyId) throws RpcException;
	
}

	