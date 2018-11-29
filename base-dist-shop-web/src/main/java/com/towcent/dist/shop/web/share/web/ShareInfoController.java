package com.towcent.dist.shop.web.share.web;

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
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.dist.shop.web.share.entity.ShareInfo;
import com.towcent.dist.shop.web.share.service.ShareInfoService;

/**
 * 分享记录Controller
 * @author shiwei
 * @version 2018-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/share/shareInfo")
public class ShareInfoController extends BaseController {

	@Autowired
	private ShareInfoService shareInfoService;
	
	@ModelAttribute
	public ShareInfo get(@RequestParam(required=false) String id) {
		ShareInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shareInfoService.get(id);
		}
		if (entity == null){
			entity = new ShareInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("share:shareInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShareInfo shareInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShareInfo> page = shareInfoService.findPage(new Page<ShareInfo>(request, response), shareInfo); 
		model.addAttribute("page", page);
		return "web/share/shareInfoList";
	}

	@RequiresPermissions("share:shareInfo:view")
	@RequestMapping(value = "form")
	public String form(ShareInfo shareInfo, Model model) {
		model.addAttribute("shareInfo", shareInfo);
		return "web/share/shareInfoForm";
	}

	@RequiresPermissions("share:shareInfo:edit")
	@RequestMapping(value = "save")
	public String save(ShareInfo shareInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shareInfo)){
			return form(shareInfo, model);
		}
		shareInfoService.save(shareInfo);
		addMessage(redirectAttributes, "保存分享记录成功");
		return "redirect:"+Global.getAdminPath()+"/share/shareInfo/?repage";
	}
	
	@RequiresPermissions("share:shareInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(ShareInfo shareInfo, RedirectAttributes redirectAttributes) {
		shareInfoService.delete(shareInfo);
		addMessage(redirectAttributes, "删除分享记录成功");
		return "redirect:"+Global.getAdminPath()+"/share/shareInfo/?repage";
	}

}