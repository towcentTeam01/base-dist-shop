package com.towcent.dist.shop.web.coupon.web;

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
import com.towcent.dist.shop.web.coupon.entity.CouponAct;
import com.towcent.dist.shop.web.coupon.service.CouponActService;

/**
 * 优惠券Controller
 * @author yxp
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/couponAct")
public class CouponActController extends BaseController {

	@Autowired
	private CouponActService couponActService;
	
	@ModelAttribute
	public CouponAct get(@RequestParam(required=false) String id) {
		CouponAct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = couponActService.get(id);
		}
		if (entity == null){
			entity = new CouponAct();
		}
		return entity;
	}
	
	@RequiresPermissions("coupon:couponAct:view")
	@RequestMapping(value = {"list", ""})
	public String list(CouponAct couponAct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		couponAct.setMerchantId(merchantId);
		Page<CouponAct> p = new Page<CouponAct>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<CouponAct> page = couponActService.findPage(p, couponAct); 
		model.addAttribute("page", page);
		return "web/coupon/couponActList";
	}

	@RequiresPermissions("coupon:couponAct:view")
	@RequestMapping(value = "form")
	public String form(CouponAct couponAct, Model model) {
		model.addAttribute("couponAct", couponAct);
		return "web/coupon/couponActForm";
	}

	@RequiresPermissions("coupon:couponAct:edit")
	@RequestMapping(value = "save")
	public String save(CouponAct couponAct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, couponAct)){
			return form(couponAct, model);
		}
		if(StringUtils.isNotBlank(couponAct.getId())){
			couponAct.setResidQty(couponAct.getTotalQty());
		} else {
			couponAct.setMerchantId(UserUtils.getMerchantId());
		}
		couponActService.save(couponAct);
		addMessage(redirectAttributes, "保存优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/couponAct/?repage";
	}
	
	@RequiresPermissions("coupon:couponAct:edit")
	@RequestMapping(value = "delete")
	public String delete(CouponAct couponAct, RedirectAttributes redirectAttributes) {
		couponActService.delete(couponAct);
		addMessage(redirectAttributes, "删除优惠券成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/couponAct/?repage";
	}

}