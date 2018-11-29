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
import com.towcent.dist.shop.web.config.entity.SysWxConfig;
import com.towcent.dist.shop.web.config.service.SysWxConfigService;

/**
 * 公众号配置Controller
 * @author huangtao
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/config/sysWxConfig")
public class SysWxConfigController extends BaseController {

	@Autowired
	private SysWxConfigService sysWxConfigService;
	
	@ModelAttribute
	public SysWxConfig get(@RequestParam(required=false) String id) {
		SysWxConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysWxConfigService.get(id);
		}
		if (entity == null){
			entity = new SysWxConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("config:sysWxConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysWxConfig sysWxConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysWxConfig.setMerchantId(merchantId);
		Page<SysWxConfig> page = sysWxConfigService.findPage(new Page<SysWxConfig>(request, response), sysWxConfig); 
		model.addAttribute("page", page);
		return "web/config/sysWxConfigList";
	}

	@RequiresPermissions("config:sysWxConfig:view")
	@RequestMapping(value = "form")
	public String form(SysWxConfig sysWxConfig, Model model) {
		model.addAttribute("sysWxConfig", sysWxConfig);
		return "web/config/sysWxConfigForm";
	}

	@RequiresPermissions("config:sysWxConfig:edit")
	@RequestMapping(value = "save")
	public String save(SysWxConfig sysWxConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysWxConfig)){
			return form(sysWxConfig, model);
		}
		if (StringUtils.isBlank(sysWxConfig.getId())) {
			sysWxConfig.setMerchantId(UserUtils.getMerchantId());
		}
		sysWxConfigService.save(sysWxConfig);
		addMessage(redirectAttributes, "保存公众号配置成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysWxConfig/?repage";
	}
	
	@RequiresPermissions("config:sysWxConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(SysWxConfig sysWxConfig, RedirectAttributes redirectAttributes) {
		sysWxConfigService.delete(sysWxConfig);
		addMessage(redirectAttributes, "删除公众号配置成功");
		return "redirect:"+Global.getAdminPath()+"/config/sysWxConfig/?repage";
	}

}