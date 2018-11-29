/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.towcent.dist.shop.web.goods.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.sc.web.common.config.Global;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.web.goods.entity.GoodsCategory;
import com.towcent.dist.shop.web.goods.service.GoodsCategoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

import static com.towcent.dist.shop.common.Constant.RULE_GOODS_CATEGORY_NO;

/**
 * 商品分类Controller
 *
 * @author alice
 * @version 2017-02-23
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goodsCategory")
public class GoodsCategoryController extends BaseController {

	@Autowired
	private GoodsCategoryService goodsCategoryService;

	@Resource
	private BaseCommonApi commonApi;

	@ModelAttribute
	public GoodsCategory get(@RequestParam(required = false) String id) {
		GoodsCategory entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = goodsCategoryService.get(id);
		}
		if (entity == null) {
			entity = new GoodsCategory();
			entity.setMerchantId(UserUtils.getMerchantId());
		}
		return entity;
	}

	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = { "" })
	public String index(GoodsCategory goodsCategory, Model model) {
		return "web/goods/goodsCategoryIndex";
	}

	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = { "list" })
	public String list(GoodsCategory goodsCategory, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		goodsCategory.setMerchantId(merchantId);
		List<GoodsCategory> list = goodsCategoryService.findList(goodsCategory);
		model.addAttribute("list", list);
		return "web/goods/goodsCategoryList";
	}

	@RequiresPermissions("goods:goodsCategory:view")
	@RequestMapping(value = "form")
	public String form(GoodsCategory goodsCategory, Model model) {
		goodsCategory.setParent(goodsCategoryService.get(goodsCategory.getParentId()));
		// 获取排序号，同一父目录下的最大序号加1
		if (StringUtils.isBlank(goodsCategory.getId())) {
			goodsCategory
					.setSort(goodsCategoryService.getSort(goodsCategory.getMerchantId(), goodsCategory.getParentId()));
		}
		model.addAttribute("goodsCategory", goodsCategory);
		return "web/goods/goodsCategoryForm";
	}

	@RequiresPermissions("goods:goodsCategory:edit")
	@RequestMapping(value = "save")
	public String save(GoodsCategory goodsCategory, Model model, RedirectAttributes redirectAttributes)
			throws RpcException {
		if (!beanValidator(model, goodsCategory)) {
			return form(goodsCategory, model);
		}
		if (StringUtils.isBlank(goodsCategory.getId())) {
			goodsCategory.setMerchantId(UserUtils.getMerchantId());
			goodsCategory
					.setCategoryNo(commonApi.getSerialNo(UserUtils.getMerchantId().intValue(), RULE_GOODS_CATEGORY_NO));
		}
		goodsCategoryService.save(goodsCategory);

		addMessage(redirectAttributes, "保存商品分类'" + goodsCategory.getCategoryName() + "'成功");
		String id = "0".equals(goodsCategory.getParentId()) ? "" : goodsCategory.getParentId();
		return "redirect:" + adminPath + "/goods/goodsCategory/list?id=" + id + "&parentIds="
				+ goodsCategory.getParentIds();
	}

	@RequiresPermissions("goods:goodsCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(GoodsCategory goodsCategory, RedirectAttributes redirectAttributes) {
		if (goodsCategoryService.deleteGoodsCategory(goodsCategory)) {
			addMessage(redirectAttributes, "删除商品分类成功");
		} else {
			addMessage(redirectAttributes, "该商品分类或子分类下还含有商品，不能进行删除！");
		}
		return "redirect:" + Global.getAdminPath() + "/goods/goodsCategory/list?id=" + goodsCategory.getParentId()
				+ "&parentIds=" + goodsCategory.getParentIds();
	}

	/**
	 * @param extId
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData/{merchantId}")
	public List<Map<String, Object>> treeData(@PathVariable("merchantId") Integer merchantId,
			@RequestParam(required = false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setMerchantId(UserUtils.getMerchantId());
		List<GoodsCategory> list = goodsCategoryService.findList(goodsCategory);
		for (int i = 0; i < list.size(); i++) {
			GoodsCategory e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId != null && !extId.equals(e.getId())
					&& e.getParentIds().indexOf("," + extId + ",") == -1)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getCategoryName());
				map.put("pIds", e.getParentIds());
				mapList.add(map);
			}
		}
		return mapList;
	}

	/** 批量修改菜单排序 */
	@RequiresPermissions("goods:goodsCategory:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		for (int i = 0; i < ids.length; i++) {
			GoodsCategory category = new GoodsCategory(ids[i]);
			category.setSort(sorts[i]);
			goodsCategoryService.updateGoodsCategorySort(category);
		}
		addMessage(redirectAttributes, "保存菜单排序成功!");
		return "redirect:" + adminPath + "/goods/goodsCategory/";
	}

	/**
	 * 校验categoryNo唯一性
	 *
	 * @param categoryNo
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "check/categoryNo/{merchantId}/{categoryNo}")
	public Boolean checkCategoryNo(@PathVariable("merchantId") Integer merchantId,
			@PathVariable("categoryNo") String categoryNo, HttpServletResponse response) {
		return goodsCategoryService.checkCategoryNo(merchantId, categoryNo.trim());
	}
}
