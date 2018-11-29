/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.towcent.dist.shop.web.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.entity.User;
import com.towcent.base.sc.web.modules.sys.service.UserService;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.web.goods.entity.Goods;
import com.towcent.dist.shop.web.goods.service.GoodsService;
import com.towcent.dist.shop.web.order.entity.OrderMain;
import com.towcent.dist.shop.web.order.service.OrderMainService;

@Controller
@RequestMapping(value = "${adminPath}/sys/tag")
public class SysTagController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderMainService orderMainService;

	/** 树结构选择标签（treeselect.tag） */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url")); // 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll", request.getParameter("isAll")); // 是否读取全部数据，不进行权限过滤
		model.addAttribute("module", request.getParameter("module")); // 过滤栏目模型（仅针对CMS的Category树）
		model.addAttribute("id", request.getParameter("id")); // 过滤栏目模型（仅针对CMS的Category树）
		return "modules/sys/tagTreeselect";
	}

	/** 图标选择标签（iconselect.tag） */
	@RequiresPermissions("user")
	@RequestMapping(value = "iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		model.addAttribute("id", request.getParameter("id"));
		return "modules/sys/tagIconselect";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "searchGoodsList")
	public String searchGoodsList(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		goods.setMerchantId(merchantId);
		Page<Goods> page = goodsService.findPage(new Page<Goods>(request, response), goods);
		model.addAttribute("page", page);
		model.addAttribute("myId", request.getParameter("myId"));
		model.addAttribute("myName", request.getParameter("myName"));
		return "modules/tag/searchGoodsList";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "searchOrderList")
	public String searchOrderList(OrderMain orderMain, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		orderMain.setMerchantId(merchantId);
		Page<OrderMain> page = orderMainService.findPage(new Page<OrderMain>(request, response), orderMain);
		model.addAttribute("page", page);
		model.addAttribute("myId", request.getParameter("myId"));
		model.addAttribute("myName", request.getParameter("myName"));
		return "modules/tag/searchOrderList";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "specsInput")
	public String specsInput(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/tag/specsInput";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "skusInput")
	public String skusInput(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/tag/skusInput";
	}
	
	// 选择系统用户
	@RequiresPermissions("user")
	@RequestMapping(value = "searchUserList")
	public String searchGoodsList(User user, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<User> page = userService.findPage(new Page<User>(request, response), user);
		model.addAttribute("page", page);
		model.addAttribute("myId", request.getParameter("myId"));
		model.addAttribute("myName", request.getParameter("myName"));
		return "modules/tag/searchUserList";
	}
}
