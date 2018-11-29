package com.towcent.dist.shop.portal.share.biz.impl;


import static com.towcent.base.common.constants.BaseConstant.E_001;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.mall.sevice.GoodsApi;
import com.towcent.dist.shop.app.client.me.service.DistHelpApi;
import com.towcent.dist.shop.app.client.share.service.ShareApi;
import com.towcent.dist.shop.app.client.share.vo.ShareVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysPosterTemplate;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.share.biz.DistHelpService;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpCustomerServiceIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpFriendIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpGuideIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpLevelDescIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterTemplateIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpShopIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInviteIn;
import com.towcent.dist.shop.portal.share.vo.input.DistProductIn;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpCustomerServiceOut;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpFriendOut;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpGuideOut;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpLevelDescOut;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpPosterOut;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpPosterTemplateOut;
import com.towcent.dist.shop.portal.share.vo.output.DistHelpShopOut;
import com.towcent.dist.shop.portal.share.vo.output.DistMemberInviteOut;
import com.towcent.dist.shop.portal.share.vo.output.DistProductOut;

/**
 * DistHelpServiceImpl
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class DistHelpServiceImpl extends BasePortalService implements DistHelpService {
	
	@Resource
	private DistHelpApi distHelpApi;
	@Resource
	private ShareApi shareApi;
	@Resource
    private GoodsApi goodsApi;
	
	@Override
	public ResultVo customerService(DistHelpCustomerServiceIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistHelpCustomerServiceOut outParam = new DistHelpCustomerServiceOut();
			SysFrontAccount parent = distHelpApi.customerService(account.getMerchantId(), account.getParentId());
			// outParam.setCustomerNum(parent.getCustomerNum());
			outParam.setJobNo(parent.getJobNo());
			outParam.setLevelVip(Integer.valueOf(parent.getLevelVip()));
			outParam.setMobilePhone(parent.getAccount());
			outParam.setNickName(parent.getNickName());
			outParam.setPortrait(parent.getPortrait());
			outParam.setCustomerServiceDesc(parent.getCustomerServiceDesc());
			outParam.setServiceWxQrCode(parent.getServiceWxQrCode());
			outParam.setWxQrCode(parent.getWxQrCode());
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo poster(DistHelpPosterIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistHelpPosterOut outParam = new DistHelpPosterOut();
			Map<String, String> result = distHelpApi.poster(account.getId(), paramIn.getTemplateId());
			outParam.setPosterUrl(MapUtils.getString(result, "posterUrl"));
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo guide(DistHelpGuideIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistHelpGuideOut outParam = new DistHelpGuideOut();
			outParam.setHelplist(distHelpApi.guide(account.getMerchantId()));
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo levelDesc(DistHelpLevelDescIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistHelpLevelDescOut outParam = new DistHelpLevelDescOut();
			outParam.setDesc(distHelpApi.levelDesc(account.getMerchantId()));
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}
	

	
	@Override
	public ResultVo invite(DistMemberInviteIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}		
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistMemberInviteOut outParam = new DistMemberInviteOut();
			SysFrontAccount ac = distHelpApi.invite(account.getId());
			outParam.setJobNo(ac.getJobNo());
			outParam.setLevelVip(Integer.valueOf(ac.getLevelVip()));
			outParam.setMobilePhone(ac.getAccount());
			outParam.setNickName(ac.getNickName());
			outParam.setPortrait(ac.getPortrait());
			outParam.setInviteIntros(ac.getInviteIntros());
			outParam.setShareInviteLink(ac.getShareInviteLink());
			outParam.setWxQrCode(ac.getWxQrCode());
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}
	
	@Override
	public ResultVo product(DistProductIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistProductOut outParam = new DistProductOut();

			ShareVo ShareVo = shareApi.shareProduct(account.getId(),
					Integer.parseInt(paramIn.getProductId()));
			
			BeanUtils.copyProperties(ShareVo, outParam);
			
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo posterTemplate(DistHelpPosterTemplateIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			List<SysPosterTemplate> list = distHelpApi.listForMerchant(account.getMerchantId());
			
			List<DistHelpPosterTemplateOut> outList = Lists.newArrayList();
			if (!CollectionUtils.isEmpty(list)) {
				DistHelpPosterTemplateOut out = null;
				for (SysPosterTemplate t : list) {
					out = new DistHelpPosterTemplateOut();
					out.setTemplateId(t.getId());
					out.setTempateTitle(t.getTitle());
					out.setTempateUrl(t.getPicUrl());
					outList.add(out);
				}
			}
			resultVo.setData(outList);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo shop(DistHelpShopIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistHelpShopOut outParam = new DistHelpShopOut();

			ShareVo ShareVo = shareApi.shareShop(account.getId());
			
			BeanUtils.copyProperties(ShareVo, outParam);
			
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo friend(DistHelpFriendIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			DistHelpFriendOut outParam = new DistHelpFriendOut();

			ShareVo ShareVo = shareApi.shareShop(account.getId());
			
			BeanUtils.copyProperties(ShareVo, outParam);
			
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		} 
		return resultVo;
	}
}