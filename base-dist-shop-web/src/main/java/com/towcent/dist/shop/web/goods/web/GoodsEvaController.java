package com.towcent.dist.shop.web.goods.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.towcent.base.sc.web.common.config.Global;
import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.common.ConfigUtil;
import com.towcent.dist.shop.web.goods.entity.GoodsEva;
import com.towcent.dist.shop.web.goods.service.GoodsEvaService;

/**
 * 商品评价Controller
 * @author yxp
 * @version 2018-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsEva")
public class GoodsEvaController extends BaseController {

	@Autowired
	private GoodsEvaService goodsEvaService;
	
	@ModelAttribute
	public GoodsEva get(@RequestParam(required=false) String id) {
		GoodsEva entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = goodsEvaService.get(id);
		}
		if (entity == null){
			entity = new GoodsEva();
			entity.setMerchantId(UserUtils.getMerchantId());
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(GoodsEva goodsEva, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		goodsEva.setMerchantId(merchantId);
		Page<GoodsEva> p = new Page<GoodsEva>(request, response);
        p.setOrderBy("a.create_date DESC");
		Page<GoodsEva> page = goodsEvaService.findPage(p, goodsEva); 
		if (null != page && !CollectionUtils.isEmpty(page.getList())) {
			for (GoodsEva eva : page.getList()) {
				eva.setGoodsSmallPic(GoodsUtils.getGoodsListPicUrl(ConfigUtil.getUrlHeader(), eva.getGoods().getMainUrls(), eva.getGoods().getDescPicV()));
			}
		}
		model.addAttribute("page", page);
		return "web/goods/goodsEvaList";
	}

	@RequiresPermissions("goods:goodsEva:view")
	@RequestMapping(value = "form")
	public String form(GoodsEva goodsEva, Model model) {
		model.addAttribute("goodsEva", goodsEva);
		return "web/goods/goodsEvaForm";
	}

	@RequiresPermissions("goods:goodsEva:edit")
	@RequestMapping(value = "save")
	public String save(GoodsEva goodsEva, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, goodsEva)){
			return form(goodsEva, model);
		}
		goodsEvaService.save(goodsEva);
		addMessage(redirectAttributes, "保存商品评价成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goodsEva/?repage";
	}
	
	@RequiresPermissions("goods:goodsEva:edit")
	@RequestMapping(value = "delete")
	public String delete(GoodsEva goodsEva, RedirectAttributes redirectAttributes) {
		goodsEvaService.delete(goodsEva);
		addMessage(redirectAttributes, "删除商品评价成功");
		return "redirect:"+Global.getAdminPath()+"/goods/goodsEva/?repage";
	}

}