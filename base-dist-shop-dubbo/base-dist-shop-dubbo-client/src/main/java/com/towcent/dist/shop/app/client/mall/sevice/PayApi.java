/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-client
 * @Title : PayApi.java
 * @Package : com.towcent.dist.shop.app.client.mall.sevice
 * @date : 2018年6月24日下午6:42:49
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.mall.sevice;

import java.math.BigDecimal;
import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.dist.shop.app.client.mall.vo.PayBalanceVo;

/**
 * @ClassName: PayApi 
 * @Description: 支付相关接口
 *
 * @author huangtao
 * @date 2018年6月24日 下午6:42:49
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface PayApi {
	
	/**
	 * 创建支付参数（充值业务）. <br>
	 * 使用场景：APP支付、公众号支付
	 * @Title createPayBalParam
	 * @param userId 账户Id
	 * @param amount 充值金额
	 * @param payType 支付方式 (1:微信支付 2:支付宝支付)
	 * @param openId 微信公众号支付(独有)
	 * @return
	 * @throws RpcException
	 */
	Map<String, Object> createPayBalParam(Integer userId, BigDecimal amount, String payType, String openId) throws RpcException;
	
	/**
	 * 查询交易(充值余额)状态.
	 * @Title queryPayBalanceStatus
	 * @param userId
	 * @param payRecordNo 交易号
	 * @return
	 * @throws RpcException
	 */
	boolean queryPayBalanceStatus(Integer userId, String payRecordNo) throws RpcException;
	
	/**
	 * 余额支付.
	 * @Title: balancePay
	 * @param userId
	 * @param orderId
	 * @return
	 * @throws RpcException
	 * @return: PayBalanceVo
	 */
	PayBalanceVo balancePay(Integer userId, Integer orderId, String tradePassword) throws RpcException;
	
	/**
	 * 创建支付参数(订单业务). <br>
	 * 使用场景：APP支付、公众号支付
	 * @Title createPayParam
	 * @param userId  账户Id
	 * @param orderId 订单Id
	 * @param payType 支付方式 (1:微信支付 2:支付宝支付)
	 * @param openId 微信公众号支付(独有)
	 * @return 
	 * @throws RpcException
	 */
	Map<String, Object> createPayParam(Integer userId, Integer orderId, String payType, String openId) throws RpcException;
	
	/**
	 * 查询支付状态.
	 * @Title: queryOrderPayStatus
	 * @param userId
	 * @param orderId
	 * @return
	 * @throws RpcException
	 * @return: boolean  true:已支付 false:未支付
	 */
	boolean queryOrderPayStatus(Integer userId, Integer orderId) throws RpcException;
	
	/**
	 * 支付回调方法.
	 * @Title: payCall
	 * @param payType
	 * @return
	 * @throws RpcException
	 * @return: String
	 */
	String payCall(String payType, Map<String, Object> params) throws RpcException;
	
	/**
	 * 关闭交易.
	 * @Title closeTrade
	 * @param orderId      订单Id
	 * @param isCloseOrder 是否关闭订单
	 * @throws RpcException
	 */
	boolean closeTrade(Integer orderId, boolean isCloseOrder) throws RpcException;
	
	/**
	 * 创建支付参数（购买会员业务）. <br>
	 * 使用场景：APP支付、公众号支付
	 * @Title createPayBalParam
	 * @param userId 账户Id
	 * @param amount 购买需支付的金额
	 * @param payType 支付方式 (1:微信支付 2:支付宝支付)
	 * @param openId 微信公众号支付(独有)
	 * @param bizType 业务类型(2:升级铂金 3:升级钻石)
	 * @return
	 * @throws RpcException
	 */
	Map<String, Object> createPayBuyMemberParam(Integer userId, BigDecimal amount, String payType, String openId, String bizType) throws RpcException;
	
	/**
	 * 查询交易(购买会员支付)状态.
	 * @Title queryPayBalanceStatus
	 * @param userId
	 * @param payRecordNo 交易号
	 * @return
	 * @throws RpcException
	 */
	boolean queryPayBuyMemberStatus(Integer userId, String payRecordNo) throws RpcException;
}

	