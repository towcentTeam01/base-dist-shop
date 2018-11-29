package com.towcent.dist.shop.web.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.towcent.base.sc.web.common.config.Global;
import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.dist.shop.web.member.entity.SysWithdrawApply;
import com.towcent.dist.shop.web.member.service.SysWithdrawApplyService;

/**
 * 提现申请Controller
 * @author HuangTao
 * @version 2018-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/member/sysWithdrawApply")
public class SysWithdrawApplyController extends BaseController {

	@Autowired
	private SysWithdrawApplyService sysWithdrawApplyService;
	
	@ModelAttribute
	public SysWithdrawApply get(@RequestParam(required=false) String id) {
		SysWithdrawApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysWithdrawApplyService.get(id);
		}
		if (entity == null){
			entity = new SysWithdrawApply();
		}
		return entity;
	}
	
	@RequiresPermissions("member:sysWithdrawApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysWithdrawApply sysWithdrawApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysWithdrawApply.setMerchantId(merchantId);
		Page<SysWithdrawApply> p = new Page<SysWithdrawApply>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<SysWithdrawApply> page = sysWithdrawApplyService.findPage(p, sysWithdrawApply); 
		model.addAttribute("page", page);
		return "web/member/sysWithdrawApplyList";
	}

	@RequiresPermissions("member:sysWithdrawApply:view")
	@RequestMapping(value = "form")
	public String form(SysWithdrawApply sysWithdrawApply, Model model) {
		model.addAttribute("sysWithdrawApply", sysWithdrawApply);
		return "web/member/sysWithdrawApplyForm";
	}

	@RequiresPermissions("member:sysWithdrawApply:edit")
	@RequestMapping(value = "save")
	public String save(SysWithdrawApply sysWithdrawApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysWithdrawApply)){
			return form(sysWithdrawApply, model);
		}
		sysWithdrawApplyService.save(sysWithdrawApply);
		addMessage(redirectAttributes, "保存提现申请成功");
		return "redirect:"+Global.getAdminPath()+"/member/sysWithdrawApply/?repage";
	}
	
	@RequiresPermissions("member:sysWithdrawApply:edit")
	@RequestMapping(value = "delete")
	public String delete(SysWithdrawApply sysWithdrawApply, RedirectAttributes redirectAttributes) {
		sysWithdrawApplyService.delete(sysWithdrawApply);
		addMessage(redirectAttributes, "删除提现申请成功");
		return "redirect:"+Global.getAdminPath()+"/member/sysWithdrawApply/?repage";
	}

}