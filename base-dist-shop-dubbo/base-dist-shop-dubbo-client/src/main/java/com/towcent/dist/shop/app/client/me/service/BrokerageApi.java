/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-client
 * @Title : BrokerageApi.java
 * @Package : com.towcent.dist.shop.app.client.me.service
 * @date : 2018年6月28日下午3:11:29
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.me.service;

import java.math.BigDecimal;

import com.towcent.base.common.exception.RpcException;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.dto.OrderPayRecord;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

/**
 * @ClassName: BrokerageApi 
 * @Description: 分销佣金计算 
 *
 * @author huangtao
 * @date 2018年6月28日 下午3:11:29
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface BrokerageApi {
	
	/**
	 * 计算佣金(普通订单).
	 * @Title calculateBkge
	 * @param userId   会员Id
	 * @param orderId  订单Id
	 * @throws RpcException
	 */
	void calculateBkge(Integer userId, Integer orderId) throws RpcException;
	
	/**
	 * 保存佣金(普通订单).
	 * @Title saveBkge
	 * @param account     用户对象(代理商)
	 * @param order       订单对象
	 * @param bkge        计算所得佣金
	 * @param orderTitle  订单标题(默认取商品名称)
	 * @param remarks     佣金类型(业绩提成、下级提成、平级奖励等)
	 * @param tier        层级(1:业绩提成 2:下级提成 3:平级奖励)
	 * @throws RpcException
	 */
	void saveBkge(SysFrontAccount account, OrderMain order, BigDecimal bkge, String orderTitle, String remarks, int tier) throws RpcException;
	
	/**
	 * 计算佣金(购买会员).
	 * @Title calculateBkgeBuyMember
	 * @param payRecord     交易对象
	 * @throws RpcException
	 */
	void calculateBkgeBuyMember(OrderPayRecord payRecord) throws RpcException;
	
	/**
	 * 保存佣金(购买会员).
	 * @Title saveBkgeBuyMember
	 * @param account      用户对象(代理商)
	 * @param payRecord    交易对象
	 * @param bkge         计算所得佣金
	 * @param title        标题(购买会员类型)
	 * @param remarks      佣金类型(业绩提成、下级提成、平级奖励等)
	 * @param tier         层级(1:业绩提成 2:下级提成 3:平级奖励)
	 * @throws RpcException
	 */
	void saveBkgeBuyMember(SysFrontAccount account, OrderPayRecord payRecord, BigDecimal bkge, String title, String remarks, int tier) throws RpcException;
}

	