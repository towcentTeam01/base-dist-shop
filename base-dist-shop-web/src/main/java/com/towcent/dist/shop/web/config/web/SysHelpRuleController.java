package com.towcent.dist.shop.web.config.web;

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
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.web.config.entity.SysHelpRule;
import com.towcent.dist.shop.web.config.service.SysHelpRuleService;

/**
 * 等级规则描述Controller
 * @author HuangTao
 * @version 2018-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/config/sysHelpRule")
public class SysHelpRuleController extends BaseController {

	@Autowired
	private SysHelpRuleService sysHelpRuleService;
	
	@ModelAttribute
	public SysHelpRule get(@RequestParam(required=false) String id) {
		SysHelpRule entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysHelpRuleService.get(id);
		}
		if (entity == null){
			entity = new SysHelpRule();
		}
		return entity;
	}
	
	@RequiresPermissions("config:sysHelpRule:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysHelpRule sysHelpRule, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysHelpRule.setMerchantId(merchantId);
		Page<SysHelpRule> p = new Page<SysHelpRule>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<SysHelpRule> page = sysHelpRuleService.findPage(p, sysHelpRule); 
		model.addAttribute("page", page);
		return "web/config/sysHelpRuleList";
	}

	@RequiresPermissions("config:sysHelpRule:view")
	@RequestMapping(value = "form")
	public String form(SysHelpRule sysHelpRule, Model model) {
		model.addAttribute("sysHelpRule", sysHelpRule);
		return "web/config/sysHelpRuleForm";
	}

	@RequiresPermissions("config:sysHelpRule:edit")
	@RequestMapping(value = "save")
	public String save(SysHelpRule sysHelpRule, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysHelpRule)){
			return form(sysHelpRule, model);
		}
		if (StringUtils.isBlank(sysHelpRule.getId())) {
			sysHelpRule.setMerchantId(UserUtils.getMerchantId());
		}
		sysHelpRuleService.save(sysHelpRule);
		addMessage(redirectAttributes, "保存等级规则描述成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysHelpRule/?repage";
	}
	
	@RequiresPermissions("config:sysHelpRule:edit")
	@RequestMapping(value = "delete")
	public String delete(SysHelpRule sysHelpRule, RedirectAttributes redirectAttributes) {
		sysHelpRuleService.delete(sysHelpRule);
		addMessage(redirectAttributes, "删除等级规则描述成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysHelpRule/?repage";
	}

}