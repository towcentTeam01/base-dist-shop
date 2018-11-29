package com.towcent.dist.shop.portal.share.web;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;
import com.towcent.dist.shop.portal.share.biz.DistMemberService;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerCountIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerListIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInfoIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberUpgradeIn;

/**
 * DistMemberController
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "share/distMember", method = RequestMethod.POST)
public class DistMemberController extends BaseController {

	@Resource
	private DistMemberService distMemberService;

	// 4.0.1 分销用户信息
	@RequestMapping(value = "info") @Loggable
	public ResultVo info(@RequestBody DistMemberInfoIn paramIn) {
		return distMemberService.info(paramIn);
	}

	// 4.0.2 升级分销
	@RequestMapping(value = "upgrade") @Loggable
	public ResultVo upgrade(@RequestBody DistMemberUpgradeIn paramIn) {
		return distMemberService.upgrade(paramIn);
	}

	// 4.0.3 客户管理汇总信息
	@RequestMapping(value = "customerCount") @Loggable
	public ResultVo customerCount(@RequestBody DistMemberCustomerCountIn paramIn) {
		return distMemberService.customerCount(paramIn);
	}

	// 4.0.4 客户管理列表
	@RequestMapping(value = "customerList") @Loggable
	public ResultVo customerList(@RequestBody DistMemberCustomerListIn paramIn) {
		return distMemberService.customerList(paramIn);
	}

}