package com.towcent.dist.shop.portal.share.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.me.service.DistMemberApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.share.biz.DistMemberService;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerCountIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerListIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInfoIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberUpgradeIn;
import com.towcent.dist.shop.portal.share.vo.output.DistMemberCustomerCountOut;
import com.towcent.dist.shop.portal.share.vo.output.DistMemberCustomerListOut;
import com.towcent.dist.shop.portal.share.vo.output.DistMemberInfoOut;

/**
 * DistMemberServiceImpl
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class DistMemberServiceImpl extends BasePortalService implements DistMemberService {
	
	@Resource
	private DistMemberApi distMemberApi;
	
	@Override
	public ResultVo info(DistMemberInfoIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistMemberInfoOut outParam = new DistMemberInfoOut();
			account = distMemberApi.getDistMemberById(account.getId());
			outParam.setNickName(account.getNickName());
			outParam.setPost(account.getLevelVipDesc());  // 等级别名
			outParam.setJobNo(account.getJobNo());
			outParam.setMarginAmount(account.getMarginAmount());
			outParam.setSettledAmount(account.getSettledAmount());
			outParam.setAmount(account.getAmount());
			outParam.setPortrait(account.getPortrait());
			
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo upgrade(DistMemberUpgradeIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			SysFrontAccount modify = new SysFrontAccount();
			modify.setId(account.getId());
			modify.setNickName(paramIn.getNickName());
			modify.setMobile(paramIn.getMobile());
			modify.setAccount(paramIn.getMobile());
			modify.setBindWx(paramIn.getBindWx());
			modify.setWxQrCode(paramIn.getWxQrCode());
			modify.setLevelVip(account.getLevelVip());

			distMemberApi.resellerDistMember(modify);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo customerCount(DistMemberCustomerCountIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistMemberCustomerCountOut outParam = new DistMemberCustomerCountOut();
			Map<String, Integer> map = distMemberApi.customerCount(account.getId());
			outParam.setAgentNum(MapUtils.getInteger(map, "agentNum", 0));  // 代理商数量
			outParam.setNomalNum(MapUtils.getInteger(map, "nomalNum", 0));  // 普通用户数
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo customerList(DistMemberCustomerListIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			SimplePageDto page = this.buildPage(paramIn);
			Map<String, Object> params = Maps.newHashMap();
			params.put("merchantId", account.getMerchantId());
			params.put("parentId", account.getId());
			params.put("searchStr", paramIn.getSearchStr());
			params.put("tabFlag", paramIn.getTabFlag());
			PaginationDto<SysFrontAccount> pagin = distMemberApi.customerListByPage(params, page);
			List<DistMemberCustomerListOut> list = Lists.newArrayList();
			if (null != pagin && !CollectionUtils.isEmpty(pagin.getList())) {
				DistMemberCustomerListOut out = null;
				for (SysFrontAccount faccount : pagin.getList()) {
					out = new DistMemberCustomerListOut();
					out.setPortrait(faccount.getPortrait());
					out.setNickName(faccount.getNickName());
					out.setJobNo(faccount.getJobNo());
					out.setLevelVip(Integer.valueOf(faccount.getLevelVip()));
					out.setCustomerNum(faccount.getCustomerNum());
					out.setMobilePhone(faccount.getAccount());
					out.setWxQrCode(faccount.getWxQrCode());
					out.setCreateDate(faccount.getCreateDate());
					out.setType(out.getLevelVip() == 1 ? "1" : "2");  // 1:代理商 2:普通用户	
					list.add(out);
				}
			}
			
			PaginationDto<DistMemberCustomerListOut> outPage = new PaginationDto<DistMemberCustomerListOut>(pagin.getTotalCount(), list); 
			outPage.setTotalPage(page.getPageSize());
			resultVo.setData(outPage);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}
}