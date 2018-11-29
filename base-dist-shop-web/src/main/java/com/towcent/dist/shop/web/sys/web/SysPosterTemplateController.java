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
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.web.sys.entity.SysPosterTemplate;
import com.towcent.dist.shop.web.sys.service.SysPosterTemplateService;

/**
 * 海报模板Controller
 * @author HuangTao
 * @version 2018-07-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sysPosterTemplate")
public class SysPosterTemplateController extends BaseController {

	@Autowired
	private SysPosterTemplateService sysPosterTemplateService;
	
	@ModelAttribute
	public SysPosterTemplate get(@RequestParam(required=false) String id) {
		SysPosterTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysPosterTemplateService.get(id);
		}
		if (entity == null){
			entity = new SysPosterTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("sys:sysPosterTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysPosterTemplate sysPosterTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		sysPosterTemplate.setMerchantId(UserUtils.getMerchantId());
		Page<SysPosterTemplate> p = new Page<SysPosterTemplate>(request, response);
		p.setOrderBy("a.sort");
		Page<SysPosterTemplate> page = sysPosterTemplateService.findPage(p, sysPosterTemplate); 
		model.addAttribute("page", page);
		return "web/sys/sysPosterTemplateList";
	}

	@RequiresPermissions("sys:sysPosterTemplate:view")
	@RequestMapping(value = "form")
	public String form(SysPosterTemplate sysPosterTemplate, Model model) {
		model.addAttribute("sysPosterTemplate", sysPosterTemplate);
		return "web/sys/sysPosterTemplateForm";
	}

	@RequiresPermissions("sys:sysPosterTemplate:edit")
	@RequestMapping(value = "save")
	public String save(SysPosterTemplate sysPosterTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysPosterTemplate)){
			return form(sysPosterTemplate, model);
		}
		sysPosterTemplateService.save(sysPosterTemplate);
		addMessage(redirectAttributes, "保存海报模板成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysPosterTemplate/?repage";
	}
	
	@RequiresPermissions("sys:sysPosterTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(SysPosterTemplate sysPosterTemplate, RedirectAttributes redirectAttributes) {
		sysPosterTemplateService.delete(sysPosterTemplate);
		addMessage(redirectAttributes, "删除海报模板成功");
		return "redirect:"+Global.getAdminPath()+"/sys/sysPosterTemplate/?repage";
	}

}