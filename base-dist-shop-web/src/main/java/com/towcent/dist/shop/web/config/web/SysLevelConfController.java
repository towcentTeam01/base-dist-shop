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
import com.towcent.dist.shop.web.config.entity.SysLevelConf;
import com.towcent.dist.shop.web.config.service.SysLevelConfService;

/**
 * 等级配置Controller
 * @author huangtao
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/config/sysLevelConf")
public class SysLevelConfController extends BaseController {

	@Autowired
	private SysLevelConfService sysLevelConfService;
	
	@ModelAttribute
	public SysLevelConf get(@RequestParam(required=false) String id) {
		SysLevelConf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysLevelConfService.get(id);
		}
		if (entity == null){
			entity = new SysLevelConf();
		}
		return entity;
	}
	
	@RequiresPermissions("config:sysLevelConf:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysLevelConf sysLevelConf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysLevelConf.setMerchantId(merchantId);
		Page<SysLevelConf> p = new Page<SysLevelConf>(request, response);
		p.setOrderBy("a.level");
		Page<SysLevelConf> page = sysLevelConfService.findPage(p, sysLevelConf); 
		model.addAttribute("page", page);
		return "web/config/sysLevelConfList";
	}

	@RequiresPermissions("config:sysLevelConf:view")
	@RequestMapping(value = "form")
	public String form(SysLevelConf sysLevelConf, Model model) {
		model.addAttribute("sysLevelConf", sysLevelConf);
		return "web/config/sysLevelConfForm";
	}

	@RequiresPermissions("config:sysLevelConf:edit")
	@RequestMapping(value = "save")
	public String save(SysLevelConf sysLevelConf, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysLevelConf)){
			return form(sysLevelConf, model);
		}
		if (StringUtils.isBlank(sysLevelConf.getId())) {
			sysLevelConf.setMerchantId(UserUtils.getMerchantId());
		}
		sysLevelConfService.save(sysLevelConf);
		addMessage(redirectAttributes, "保存等级配置成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysLevelConf/?repage";
	}
	
	@RequiresPermissions("config:sysLevelConf:edit")
	@RequestMapping(value = "delete")
	public String delete(SysLevelConf sysLevelConf, RedirectAttributes redirectAttributes) {
		sysLevelConfService.delete(sysLevelConf);
		addMessage(redirectAttributes, "删除等级配置成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysLevelConf/?repage";
	}

}