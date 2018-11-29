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
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.dist.shop.web.config.entity.SysHelpInviteIntro;
import com.towcent.dist.shop.web.config.service.SysHelpInviteIntroService;

/**
 * 邀请介绍Controller
 * @author HuangTao
 * @version 2018-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/config/sysHelpInviteIntro")
public class SysHelpInviteIntroController extends BaseController {

	@Autowired
	private SysHelpInviteIntroService sysHelpInviteIntroService;
	
	@ModelAttribute
	public SysHelpInviteIntro get(@RequestParam(required=false) String id) {
		SysHelpInviteIntro entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysHelpInviteIntroService.get(id);
		}
		if (entity == null){
			entity = new SysHelpInviteIntro();
		}
		return entity;
	}
	
	@RequiresPermissions("config:sysHelpInviteIntro:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysHelpInviteIntro sysHelpInviteIntro, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysHelpInviteIntro.setMerchantId(merchantId);
		Page<SysHelpInviteIntro> p = new Page<SysHelpInviteIntro>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<SysHelpInviteIntro> page = sysHelpInviteIntroService.findPage(p, sysHelpInviteIntro); 
		model.addAttribute("page", page);
		return "web/config/sysHelpInviteIntroList";
	}

	@RequiresPermissions("config:sysHelpInviteIntro:view")
	@RequestMapping(value = "form")
	public String form(SysHelpInviteIntro sysHelpInviteIntro, Model model) {
		model.addAttribute("sysHelpInviteIntro", sysHelpInviteIntro);
		return "web/config/sysHelpInviteIntroForm";
	}

	@RequiresPermissions("config:sysHelpInviteIntro:edit")
	@RequestMapping(value = "save")
	public String save(SysHelpInviteIntro sysHelpInviteIntro, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysHelpInviteIntro)){
			return form(sysHelpInviteIntro, model);
		}
		if (StringUtils.isBlank(sysHelpInviteIntro.getId())) {
			sysHelpInviteIntro.setMerchantId(UserUtils.getMerchantId());
		}
		sysHelpInviteIntroService.save(sysHelpInviteIntro);
		addMessage(redirectAttributes, "保存邀请介绍成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysHelpInviteIntro/?repage";
	}
	
	@RequiresPermissions("config:sysHelpInviteIntro:edit")
	@RequestMapping(value = "delete")
	public String delete(SysHelpInviteIntro sysHelpInviteIntro, RedirectAttributes redirectAttributes) {
		sysHelpInviteIntroService.delete(sysHelpInviteIntro);
		addMessage(redirectAttributes, "删除邀请介绍成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysHelpInviteIntro/?repage";
	}

}