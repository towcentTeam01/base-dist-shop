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
import com.towcent.dist.shop.web.coupon.entity.CouponClaim;
import com.towcent.dist.shop.web.coupon.service.CouponClaimService;

/**
 * 优惠券Controller
 * @author yxp
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/couponClaim")
public class CouponClaimController extends BaseController {

	@Autowired
	private CouponClaimService couponClaimService;
	
	@ModelAttribute
	public CouponClaim get(@RequestParam(required=false) String id) {
		CouponClaim entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = couponClaimService.get(id);
		}
		if (entity == null){
			entity = new CouponClaim();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(CouponClaim couponClaim, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		couponClaim.setMerchantId(merchantId);
		Page<CouponClaim> p = new Page<CouponClaim>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<CouponClaim> page = couponClaimService.findPage(p, couponClaim); 
		model.addAttribute("page", page);
		return "web/coupon/couponClaimList";
	}

	@RequiresPermissions("coupon:couponClaim:view")
	@RequestMapping(value = "form")
	public String form(CouponClaim couponClaim, Model model) {
		model.addAttribute("couponClaim", couponClaim);
		return "web/coupon/couponClaimForm";
	}

	@RequiresPermissions("coupon:couponClaim:edit")
	@RequestMapping(value = "save")
	public String save(CouponClaim couponClaim, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, couponClaim)){
			return form(couponClaim, model);
		}
		if (StringUtils.isBlank(couponClaim.getId())) {
			couponClaim.setMerchantId(UserUtils.getMerchantId());
		}
		couponClaimService.save(couponClaim);
		addMessage(redirectAttributes, "保存优惠券领取成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/couponClaim/?repage";
	}
	
	@RequiresPermissions("coupon:couponClaim:edit")
	@RequestMapping(value = "delete")
	public String delete(CouponClaim couponClaim, RedirectAttributes redirectAttributes) {
		couponClaimService.delete(couponClaim);
		addMessage(redirectAttributes, "删除优惠券领取成功");
		return "redirect:"+Global.getAdminPath()+"/coupon/couponClaim/?repage";
	}

}