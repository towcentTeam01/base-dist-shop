package com.towcent.dist.shop.portal.share.web;

import com.towcent.dist.shop.portal.share.vo.input.DistHelpFriendIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpShopIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterTemplateIn;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;
import com.towcent.dist.shop.portal.share.biz.DistHelpService;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpCustomerServiceIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpGuideIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpLevelDescIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInviteIn;
import com.towcent.dist.shop.portal.share.vo.input.DistProductIn;

/**
 * DistHelpController
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "share/distHelp", method = RequestMethod.POST)
public class DistHelpController extends BaseController {

	@Resource
	private DistHelpService distHelpService;

	// 4.1.1 专属客服
	@RequestMapping(value = "customerService") @Loggable
	public ResultVo customerService(@RequestBody DistHelpCustomerServiceIn paramIn) {
		return distHelpService.customerService(paramIn);
	}

	// 4.1.2 生成专属海报
	@RequestMapping(value = "poster") @Loggable
	public ResultVo poster(@RequestBody DistHelpPosterIn paramIn) {
		return distHelpService.poster(paramIn);
	}

	// 4.1.3 操作指南
	@RequestMapping(value = "guide") @Loggable
	public ResultVo guide(@RequestBody DistHelpGuideIn paramIn) {
		return distHelpService.guide(paramIn);
	}

	// 4.1.4 分类等级介绍
	@RequestMapping(value = "levelDesc") @Loggable
	public ResultVo levelDesc(@RequestBody DistHelpLevelDescIn paramIn) {
		return distHelpService.levelDesc(paramIn);
	}
	
	// 4.0.5 邀请好友
	@RequestMapping(value = "invite") @Loggable
	public ResultVo invite(@RequestBody DistMemberInviteIn paramIn) {
		return distHelpService.invite(paramIn);
	}
	
	// 4.1.6 商品分享
	@RequestMapping(value = "product") @Loggable
	public ResultVo product(@RequestBody DistProductIn paramIn) {
		return distHelpService.product(paramIn);
	}

	// 4.1.7 获取海报模板
	@RequestMapping(value = "posterTemplate") @Loggable
	public ResultVo posterTemplate(@RequestBody DistHelpPosterTemplateIn paramIn) {
		return distHelpService.posterTemplate(paramIn);
	}

	// 4.1.8 商城分享
	@RequestMapping(value = "shop") @Loggable
	public ResultVo shop(@RequestBody DistHelpShopIn paramIn) {
		return distHelpService.shop(paramIn);
	}

	// 4.1.9 邀请好友加入
	@RequestMapping(value = "friend") @Loggable
	public ResultVo friend(@RequestBody DistHelpFriendIn paramIn) {
		return distHelpService.friend(paramIn);
	}
}