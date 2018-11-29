/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-client
 * @Title : DistMemberApi.java
 * @Package : com.towcent.dist.shop.app.client.me.service
 * @date : 2018年6月28日上午10:25:51
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.me.service;

import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

/**
 * @ClassName: DistMemberApi 
 * @Description: 分销会员相关接口 
 *
 * @author huangtao
 * @date 2018年6月28日 上午10:25:51
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface DistMemberApi {
	
	/**
	 * 获取分销会员资料接口.
	 * @Title getDistMemberById
	 * @param id   会员Id
	 * @return
	 * @throws RpcException
	 */
	SysFrontAccount getDistMemberById(Integer id) throws RpcException;
	
	/**
	 * 成为分销商（补充资料）.
	 * @Title resellerDistMember
	 * @param account  更新的会员对象
	 * @return
	 * @throws RpcException
	 */
	boolean resellerDistMember(SysFrontAccount account) throws RpcException;
	
	/**
	 * 统计分销会员的团队人员情况.
	 * @Title customerCount
	 * @param id   会员Id
	 * @return  [agentNum->0, nomalNum->0](代理商、普通用户)
	 * @throws RpcException
	 */
	Map<String, Integer> customerCount(Integer id) throws RpcException;
	
	/**
	 * 查询代理商的下级客户列表.
	 * @Title customerListByPage
	 * @param params
	 * @param pageDto
	 * @return
	 * @throws RpcException
	 */
	PaginationDto<SysFrontAccount> customerListByPage(Map<String, Object> params, SimplePageDto pageDto) throws RpcException;
	
	
}

	