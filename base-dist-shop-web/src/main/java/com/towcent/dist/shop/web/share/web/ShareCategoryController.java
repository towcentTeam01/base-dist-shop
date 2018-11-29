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
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.web.share.entity.ShareCategory;
import com.towcent.dist.shop.web.share.service.ShareCategoryService;

/**
 * 商品分享分类Controller
 * @author shiwei
 * @version 2018-08-25
 */
@Controller
@RequestMapping(value = "${adminPath}/share/shareCategory")
public class ShareCategoryController extends BaseController {

	@Autowired
	private ShareCategoryService shareCategoryService;
	
	@ModelAttribute
	public ShareCategory get(@RequestParam(required=false) String id) {
		ShareCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = shareCategoryService.get(id);
		}
		if (entity == null){
			entity = new ShareCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("share:shareCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(ShareCategory shareCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ShareCategory> page = shareCategoryService.findPage(new Page<ShareCategory>(request, response), shareCategory); 
		model.addAttribute("page", page);
		return "web/share/shareCategoryList";
	}

	@RequiresPermissions("share:shareCategory:view")
	@RequestMapping(value = "form")
	public String form(ShareCategory shareCategory, Model model) {
		model.addAttribute("shareCategory", shareCategory);
		return "web/share/shareCategoryForm";
	}

	@RequiresPermissions("share:shareCategory:edit")
	@RequestMapping(value = "save")
	public String save(ShareCategory shareCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, shareCategory)){
			return form(shareCategory, model);
		}
		Integer merchantId = UserUtils.getMerchantId();
		shareCategory.setMerchantId(merchantId);
		shareCategoryService.save(shareCategory);
		addMessage(redirectAttributes, "保存商品分享分类成功");
		return "redirect:"+Global.getAdminPath()+"/share/shareCategory/?repage";
	}
	
	@RequiresPermissions("share:shareCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(ShareCategory shareCategory, RedirectAttributes redirectAttributes) {
		shareCategoryService.delete(shareCategory);
		addMessage(redirectAttributes, "删除商品分享分类成功");
		return "redirect:"+Global.getAdminPath()+"/share/shareCategory/?repage";
	}

}