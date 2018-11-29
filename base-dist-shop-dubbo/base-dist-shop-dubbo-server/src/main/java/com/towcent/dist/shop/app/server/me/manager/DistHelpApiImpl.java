/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : DistHelpApiImpl.java
 * @Package : com.towcent.dist.shop.app.server.me.manager
 * @date : 2018年6月30日下午8:20:20
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.me.manager;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.dist.shop.common.Constant.ID;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.constants.BaseConstant;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.common.utils.FileUtils;
import com.towcent.base.common.utils.IdGen;
import com.towcent.base.common.utils.PictureUtils;
import com.towcent.base.common.utils.SpringFTPUtil;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.me.service.DistHelpApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysHelpGuide;
import com.towcent.dist.shop.app.client.sys.dto.SysHelpInviteIntro;
import com.towcent.dist.shop.app.client.sys.dto.SysHelpRule;
import com.towcent.dist.shop.app.client.sys.dto.SysMerchantInfo;
import com.towcent.dist.shop.app.client.sys.dto.SysPosterTemplate;
import com.towcent.dist.shop.app.server.common.util.ImageSynthesisUtils;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.app.server.sys.service.SysHelpGuideService;
import com.towcent.dist.shop.app.server.sys.service.SysHelpInviteIntroService;
import com.towcent.dist.shop.app.server.sys.service.SysHelpRuleService;
import com.towcent.dist.shop.app.server.sys.service.SysMerchantInfoService;
import com.towcent.dist.shop.app.server.sys.service.SysPosterTemplateService;
import com.towcent.dist.shop.common.ConfigUtil;

/**
 * @ClassName: DistHelpApiImpl 
 * @Description: 系统帮助相关接口实现类 
 *
 * @author huangtao
 * @date 2018年6月30日 下午8:20:20
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class DistHelpApiImpl extends BaseService implements DistHelpApi {
	
	@Resource
	private SysFrontAccountService accountService;
	@Resource
	private SysHelpGuideService guideService;
	@Resource
	private SysHelpRuleService ruleService;
	@Resource
	private SysHelpInviteIntroService inviteIntroService;
	@Resource
	private SysPropertyService propertyService;
	@Resource
	private SysMerchantInfoService merchantInfoService;
	@Resource
	private SysPosterTemplateService posterTemplateService;
	@Resource
	private BaseCommonApi baseCommonApi;
	@Resource
	private MessageChannel ftpChannel;
	
	@Override
	public SysFrontAccount customerService(Integer merchantId, Integer parentId) throws RpcException {
		// Assert.notNull(parentId, "parentId不能为空");
		try {
			SysFrontAccount account = null;
			if (null == parentId) {  // 上级为空时
				account = new SysFrontAccount();
				SysMerchantInfo merchant = merchantInfoService.findByKeyValSingle(ID, merchantId);
				account.setJobNo(merchant.getHouseNumber());
				account.setLevelVip("4");
				account.setAccount(merchant.getPhone());
				account.setNickName(merchant.getShopName());
				account.setPortrait(merchant.getLogo());
				account.setWxQrCode(merchant.getWxQrCode());
			} else {
				Map<String, Object> params = Maps.newHashMap();
				params.put("id", parentId);
				params.put("delFlag", DEL_FLAG_0);
				// params.put("isQty", true);
				List<SysFrontAccount> accounts = accountService.findByBiz(params);
				account = CollectionUtils.isEmpty(accounts) ? null : accounts.get(0);
			}
			String serviceWxQrCode = propertyService.getSysPropertyToString(account.getMerchantId(), "service_wx_qr_code");
			account.setServiceWxQrCode(serviceWxQrCode);
			String customerServiceDesc = propertyService.getSysPropertyToString(account.getMerchantId(), "customer_service_desc");
			account.setCustomerServiceDesc(customerServiceDesc);
			return account;
		} catch (ServiceException e) {
			logger.error("获取专属客服信息失败", e);
			throw new RpcException("", "获取专属客服失败", e);
		}
	}

	@Override
	public Map<String, String> poster(Integer id, Integer templateId) throws RpcException {
		Assert.notNull(id, "会员Id不能为空");
		Assert.notNull(templateId, "海报模板Id不能为空");
		
		try {
			SysFrontAccount account = accountService.getAccountById(id);
			// 查询海报模板
			SysPosterTemplate template = posterTemplateService.findByKeyValSingle(ID, templateId);
			BufferedImage templateImage = ImageIO.read(new URL(template.getPicUrl())); 
			// 重新生成海报
			String tempFileName = IdGen.uuid() + PictureUtils.JPG;
			File outFile = new File(this.getTempPath() + tempFileName);
			ImageSynthesisUtils.compositeImage(account, templateImage, outFile);
			// 图片类型9:推广海报
			String relativePath = baseCommonApi.getImageRelativePath(account.getMerchantId(), 9);
			SpringFTPUtil.ftpUpload(ftpChannel, outFile, relativePath);
			
			// 生成后的海报图片地址
			String posterUrl = StringUtils.substringBeforeLast(ConfigUtil.getUrlHeader(), "/") + relativePath + tempFileName;
			// 删除临时文件
			FileUtils.deleteQuietly(outFile);
			Map<String, String> result = Maps.newHashMap();
			result.put("posterUrl", posterUrl);
			return result;
		} catch (Exception e) {
			logger.error("获取代理商的专属海报失败", e);
			throw new RpcException("", "获取代理商的专属海报失败", e);
		}
	}

	@Override
	public String guide(Integer merchantId) throws RpcException {
		Assert.notNull(merchantId, "merchantId不能为空");
		
		StringBuffer sb = new StringBuffer();
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("merchantId", merchantId);
			params.put("delFlag", BaseConstant.DEL_FLAG_0);
			List<SysHelpGuide> list = guideService.findByPage(new SimplePage(), "a.sort", "asc", params);
			if (!CollectionUtils.isEmpty(list)) {
				for (SysHelpGuide sysHelpGuide : list) {
					sb.append(sysHelpGuide.getPicUrl()).append(";");
				}
				sb.setLength(sb.length() - 1);
			}
		} catch (ServiceException e) {
			logger.error("获取操作指南失败", e);
			throw new RpcException("", "获取操作指南失败", e);
		}
		return sb.toString();
	}

	@Override
	public String levelDesc(Integer merchantId) throws RpcException {
		Assert.notNull(merchantId, "merchantId不能为空");
		
		StringBuffer sb = new StringBuffer();
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("merchantId", merchantId);
			params.put("delFlag", BaseConstant.DEL_FLAG_0);
			List<SysHelpRule> list = ruleService.findByPage(new SimplePage(), "a.sort", "asc", params);
			if (!CollectionUtils.isEmpty(list)) {
				for (SysHelpRule sysHelpRule : list) {
					sb.append(sysHelpRule.getPicUrl()).append(";");
				}
				sb.setLength(sb.length() - 1);
			}
		} catch (ServiceException e) {
			logger.error("获取分销等级介绍失败", e);
			throw new RpcException("", "获取分销等级介绍失败", e);
		}
		return sb.toString();
	}

	@Override
	public SysFrontAccount invite(Integer id) throws RpcException {
		Assert.notNull(id, "id不能为空");
		
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("id", id);
			params.put("delFlag", DEL_FLAG_0);
			// params.put("isQty", true);
			List<SysFrontAccount> accounts = accountService.findByBiz(params);
			SysFrontAccount account = CollectionUtils.isEmpty(accounts) ? null : accounts.get(0);
			// 分享链接拼接
			String shareInviteLink = propertyService.getSysPropertyToString(account.getMerchantId(), "share_invite_link");
			StringBuffer shareUrl = new StringBuffer();
			
			shareUrl.append(shareInviteLink);
			if(shareInviteLink.indexOf("?") != -1)
				shareUrl.append("&shareCode=");
			else
				shareUrl.append("?shareCode=");
			shareUrl.append(account.getJobNo());
			shareUrl.append("#");
			shareUrl.append(account.getMerchantId().intValue());
			shareUrl.append("#");
			
			account.setShareInviteLink(shareUrl.toString());
			
			// 系统邀请介绍图片
			params.clear();
			params.put("merchantId", account.getMerchantId());
			params.put("delFlag", DEL_FLAG_0);
			List<SysHelpInviteIntro> introList = inviteIntroService.findByPage(new SimplePage(), "a.sort", "ASC", params);
			if (!CollectionUtils.isEmpty(introList)) {
				StringBuffer sb = new StringBuffer();
				for (SysHelpInviteIntro intro : introList) {
					sb.append(intro.getPicUrl()).append(";");
				}
				sb.setLength(sb.length() - 1);
				account.setInviteIntros(sb.toString());
			}
			return account;
		} catch (ServiceException e) {
			logger.error("获取专属客服信息失败", e);
			throw new RpcException("", "获取专属客服失败", e);
		}
	}
	
	@Override
	public List<SysPosterTemplate> listForMerchant(Integer merchantId) throws RpcException {
		Assert.notNull(merchantId, "商户Id不能为空");
		
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("merchantId", merchantId);
			params.put("delFlag", DEL_FLAG_0);
			List<SysPosterTemplate> list = posterTemplateService.findByBiz(params);
			return list;
		} catch (ServiceException e) {
			logger.error("获取海报模板列表失败", e);
			throw new RpcException("", "获取海报模板列表失败", e);
		}
	}
	
}

	