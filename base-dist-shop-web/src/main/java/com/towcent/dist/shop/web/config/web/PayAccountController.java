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
import com.towcent.dist.shop.web.config.entity.PayAccount;
import com.towcent.dist.shop.web.config.service.PayAccountService;

/**
 * 支付配置Controller
 * @author huangtao
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/config/payAccount")
public class PayAccountController extends BaseController {

	@Autowired
	private PayAccountService payAccountService;
	
	@ModelAttribute
	public PayAccount get(@RequestParam(required=false) String id) {
		PayAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = payAccountService.get(id);
		}
		if (entity == null){
			entity = new PayAccount();
		}
		return entity;
	}
	
	@RequiresPermissions("config:payAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(PayAccount payAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		payAccount.setMerchantId(merchantId);
		Page<PayAccount> page = payAccountService.findPage(new Page<PayAccount>(request, response), payAccount); 
		model.addAttribute("page", page);
		return "web/config/payAccountList";
	}

	@RequiresPermissions("config:payAccount:view")
	@RequestMapping(value = "form")
	public String form(PayAccount payAccount, Model model) {
		model.addAttribute("payAccount", payAccount);
		return "web/config/payAccountForm";
	}

	@RequiresPermissions("config:payAccount:edit")
	@RequestMapping(value = "save")
	public String save(PayAccount payAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, payAccount)){
			return form(payAccount, model);
		}
		if (StringUtils.isBlank(payAccount.getId())) {
			payAccount.setMerchantId(UserUtils.getMerchantId());
		}
		payAccountService.save(payAccount);
		addMessage(redirectAttributes, "保存支付配置成功");
		return "redirect:"+Global.getAdminPath()+"/config/payAccount/?repage";
	}
	
	@RequiresPermissions("config:payAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(PayAccount payAccount, RedirectAttributes redirectAttributes) {
		payAccountService.delete(payAccount);
		addMessage(redirectAttributes, "删除支付配置成功");
		return "redirect:"+Global.getAdminPath()+"/config/payAccount/?repage";
	}

}