/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-client
 * @Title : DistHelpApi.java
 * @Package : com.towcent.dist.shop.app.client.me.service
 * @date : 2018年6月30日下午8:11:06
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.me.service;

import java.util.List;
import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysPosterTemplate;

/**
 * @ClassName: DistHelpApi 
 * @Description: 系统帮助相关接口 
 *
 * @author huangtao
 * @date 2018年6月30日 下午8:11:06
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DistHelpApi {
	
	/**
	 * 专属客服.
	 * @Title customerService
	 * @param merchantId 商家Id
	 * @param parentId   上级代理商Id
	 * @return
	 * @throws RpcException
	 */
	SysFrontAccount customerService(Integer merchantId, Integer parentId) throws RpcException;
	
	/**
	 * 专属海报.
	 * @Title poster
	 * @param id   会员Id
	 * @param templateId 海报模板Id
	 * @return  posterUrl：海报图片地址
	 * @throws RpcException
	 */
	Map<String, String> poster(Integer id, Integer templateId) throws RpcException;
	
	/**
	 * 操作指南.
	 * @Title guide
	 * @param merchantId  商户Id
	 * @return 多个url使用;分割
	 * @throws RpcException
	 */
	String guide(Integer merchantId) throws RpcException;
	
	/**
	 * 等级介绍.
	 * @Title levelDesc
	 * @param merchantId  商户Id
	 * @return 多个url使用;分割
	 * @throws RpcException
	 */
	String levelDesc(Integer merchantId) throws RpcException;
	
	/**
	 * 邀请好友().
	 * @Title customerService
	 * @param id   会员Id
	 * @return
	 * @throws RpcException
	 */
	SysFrontAccount invite(Integer id) throws RpcException;
	
	/**
	 * 获取海报列表.
	 * @Title listForParam
	 * @param merchantId  商户Id
	 * @return
	 * @throws RpcException
	 */
	List<SysPosterTemplate> listForMerchant(Integer merchantId) throws RpcException;
}

	