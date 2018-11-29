package com.towcent.dist.shop.web.sys.web;

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
import com.towcent.dist.shop.web.sys.entity.SysHelpGuide;
import com.towcent.dist.shop.web.sys.service.SysHelpGuideService;

/**
 * 系统教程Controller
 * @author HuangTao
 * @version 2018-07-22
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysHelpGuide")
public class SysHelpGuideController extends BaseController {

	@Autowired
	private SysHelpGuideService sysHelpGuideService;
	
	@ModelAttribute
	public SysHelpGuide get(@RequestParam(required=false) String id) {
		SysHelpGuide entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysHelpGuideService.get(id);
		}
		if (entity == null){
			entity = new SysHelpGuide();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysHelpGuide:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysHelpGuide sysHelpGuide, HttpServletRequest request, HttpServletResponse response, Model model) {
		sysHelpGuide.setMerchantId(UserUtils.getMerchantId());
		Page<SysHelpGuide> p = new Page<SysHelpGuide>(request, response);
		p.setOrderBy("a.sort");
		Page<SysHelpGuide> page = sysHelpGuideService.findPage(p, sysHelpGuide); 
		model.addAttribute("page", page);
		return "web/sys/sysHelpGuideList";
	}

	@RequiresPermissions("sys:sysHelpGuide:view")
	@RequestMapping(value = "form")
	public String form(SysHelpGuide sysHelpGuide, Model model) {
		model.addAttribute("sysHelpGuide", sysHelpGuide);
		return "web/sys/sysHelpGuideForm";
	}

	@RequiresPermissions("sys:sysHelpGuide:edit")
	@RequestMapping(value = "save")
	public String save(SysHelpGuide sysHelpGuide, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysHelpGuide)){
			return form(sysHelpGuide, model);
		}
		if (StringUtils.isBlank(sysHelpGuide.getId())) {
			sysHelpGuide.setMerchantId(UserUtils.getMerchantId());
		}
		sysHelpGuideService.save(sysHelpGuide);
		addMessage(redirectAttributes, "保存系统教程成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysHelpGuide/?repage";
	}
	
	@RequiresPermissions("sys:sysHelpGuide:edit")
	@RequestMapping(value = "delete")
	public String delete(SysHelpGuide sysHelpGuide, RedirectAttributes redirectAttributes) {
		sysHelpGuideService.delete(sysHelpGuide);
		addMessage(redirectAttributes, "删除系统教程成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysHelpGuide/?repage";
	}

}