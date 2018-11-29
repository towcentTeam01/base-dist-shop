/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : DistMemberApiImpl.java
 * @Package : com.towcent.dist.shop.app.server.me.manager
 * @date : 2018年6月28日上午10:44:09
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.me.manager;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.service.BaseService;
import com.towcent.dist.shop.app.client.me.service.DistMemberApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.app.server.sys.service.SysLevelConfService;
import com.towcent.dist.shop.common.Constant;

/**
 * @ClassName: DistMemberApiImpl 
 * @Description: 分销相关接口实现类 
 *
 * @author huangtao
 * @date 2018年6月28日 上午10:44:09
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class DistMemberApiImpl extends BaseService implements DistMemberApi {

	@Resource
	private SysFrontAccountService accountService;
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private SysLevelConfService levelService;
	
	@Override
	public SysFrontAccount getDistMemberById(Integer id) throws RpcException {
		Assert.notNull(id, "会员Id不能为空");
		try {
			return accountService.getAccountById(id);
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public boolean resellerDistMember(SysFrontAccount account) throws RpcException {
		Assert.notNull(account, "对象不能为空");
		try {
			account.setLevelVip((Integer.valueOf(account.getLevelVip()) + 1) + "");
			return accountService.modifyById(account) > 0;
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public Map<String, Integer> customerCount(Integer id) throws RpcException {
		Assert.notNull(id, "会员Id不能为空");
		try {
			Map<String, Integer> resultMap = Maps.newHashMap();
			
			Map<String, Object> params = Maps.newHashMap();
			params.put("parentId", id);
			params.put("delFlag", Constant.DEL_FLAG_0);
			params.put("levelVip", 1);
			resultMap.put("nomalNum", accountService.findCount(params));
			params.remove("levelVip");
			params.put("notLevelVip", "1");
			resultMap.put("agentNum", accountService.findCount(params));
			
			return resultMap;
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}

	@Override
	public PaginationDto<SysFrontAccount> customerListByPage(Map<String, Object> params, SimplePageDto pageDto)
			throws RpcException {
		try {
			params.put("delFlag", DEL_FLAG_0);
			params.put("isQty", true);  // 查询客户的客户数（开关）
			int totalCount = accountService.findCount(params);
			SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
			List<SysFrontAccount> list = accountService.findByPage(page, "a.create_date", "DESC", params);
			return new PaginationDto<SysFrontAccount>(totalCount, list);
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}
}

	