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
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.web.member.entity.SysFrontAccount;
import com.towcent.dist.shop.web.member.service.SysFrontAccountService;

/**
 * 会员Controller
 * @author huangtao
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/member/sysFrontAccount")
public class SysFrontAccountController extends BaseController {

	@Autowired
	private SysFrontAccountService sysFrontAccountService;
	
	@ModelAttribute
	public SysFrontAccount get(@RequestParam(required=false) String id) {
		SysFrontAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysFrontAccountService.get(id);
		}
		if (entity == null){
			entity = new SysFrontAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("member:sysFrontAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysFrontAccount sysFrontAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysFrontAccount.setMerchantId(merchantId);
		Page<SysFrontAccount> p = new Page<SysFrontAccount>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<SysFrontAccount> page = sysFrontAccountService.findPage(p, sysFrontAccount); 
		model.addAttribute("page", page);
		return "web/member/sysFrontAccountList";
	}

	@RequiresPermissions("member:sysFrontAccount:view")
	@RequestMapping(value = "form")
	public String form(SysFrontAccount sysFrontAccount, Model model) {
		model.addAttribute("sysFrontAccount", sysFrontAccount);
		return "web/member/sysFrontAccountForm";
	}

	@RequiresPermissions("member:sysFrontAccount:edit")
	@RequestMapping(value = "save")
	public String save(SysFrontAccount sysFrontAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysFrontAccount)){
			return form(sysFrontAccount, model);
		}
		if (StringUtils.isBlank(sysFrontAccount.getId())) {
			sysFrontAccount.setMerchantId(UserUtils.getMerchantId());
		}
		sysFrontAccountService.save(sysFrontAccount);
		addMessage(redirectAttributes, "保存会员成功");
		return "redirect:"+Global.getAdminPath()+"/member/sysFrontAccount/?repage";
	}
	
	@RequiresPermissions("member:sysFrontAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(SysFrontAccount sysFrontAccount, RedirectAttributes redirectAttributes) {
		sysFrontAccountService.delete(sysFrontAccount);
		addMessage(redirectAttributes, "删除会员成功");
		return "redirect:"+Global.getAdminPath()+"/member/sysFrontAccount/?repage";
	}

    // 会员选择列表
    @RequiresPermissions("user")
    @RequestMapping(value = "searchMemberList")
    public String searchCarList(SysFrontAccount sysFrontAccount, HttpServletRequest request, HttpServletResponse response,
                                Model model) {
    	Integer merchantId = UserUtils.getMerchantId();
		sysFrontAccount.setMerchantId(merchantId);
    	Page<SysFrontAccount> page = sysFrontAccountService.findPage(new Page<SysFrontAccount>(request, response), sysFrontAccount);
        model.addAttribute("page", page);
        model.addAttribute("myId", request.getParameter("myId"));
        model.addAttribute("myName", request.getParameter("myName"));
        model.addAttribute("sysFrontAccount", sysFrontAccount);
        return "modules/tag/searchMemberList";
    }
}