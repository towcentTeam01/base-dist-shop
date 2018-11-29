package com.towcent.dist.shop.web.goods.web;

import com.google.common.collect.Lists;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.mapper.JsonMapper;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.sc.web.common.config.ConfigUtils;
import com.towcent.base.sc.web.common.config.Global;
import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.entity.User;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.web.goods.entity.Goods;
import com.towcent.dist.shop.web.goods.entity.GoodsCategory;
import com.towcent.dist.shop.web.goods.entity.GoodsSku;
import com.towcent.dist.shop.web.goods.entity.GoodsSpec;
import com.towcent.dist.shop.web.goods.service.GoodsCategoryService;
import com.towcent.dist.shop.web.goods.service.GoodsService;
import com.towcent.dist.shop.web.goods.service.GoodsSkuService;
import com.towcent.dist.shop.web.goods.service.GoodsSpecService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

import static com.towcent.base.common.constants.BaseConstant.*;
import static com.towcent.dist.shop.common.Constant.*;

/**
 * 商品Controller
 *
 * @author yxp
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/goods/goods")
public class GoodsController extends BaseController {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsSpecService goodsSpecService;
	@Autowired
	private GoodsSkuService goodsSkuService;
	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Resource
	private BaseCommonApi commonApi;

	@ModelAttribute
	public Goods get(@RequestParam(required = false) String id) {
		Goods entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = goodsService.get(id);
		}
		if (entity == null) {
			entity = new Goods();
			entity.setMerchantId(UserUtils.getMerchantId());
		}
		return entity;
	}

	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = { "list", "" })
	public String list(Goods goods, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		goods.setMerchantId(merchantId);
		Page<Goods> page = goodsService.findPage(new Page<Goods>(request, response), goods);
		if (null != page && !CollectionUtils.isEmpty(page.getList())) {
			for (Goods g : page.getList()) {
				g.setPicUrl(GoodsUtils.getGoodsListPicUrl(ConfigUtils.getUrlHeader(), g.getMainUrls(),
						g.getDescPicV().intValue()));
			}
		}
		model.addAttribute("page", page);
		return "web/goods/goodsList";
	}

	@RequiresPermissions("goods:goods:view")
	@RequestMapping(value = "form")
	public String form(Goods goods, Model model) {
		model.addAttribute("goods", goods);
		if (StringUtils.isBlank(goods.getId())) {
			return "web/goods/goodsForm";
		}
		
		GoodsSpec spec = new GoodsSpec();
		spec.setGoodsId(Integer.valueOf(goods.getId()));
		spec.setDelFlag(NO);
		List<GoodsSpec> specList = goodsSpecService.findList(spec);
		if (GOODS_TYPE_1.equals(goods.getGoodsType()) && !CollectionUtils.isEmpty(specList)) {
			// 批发商品
			GoodsSku goodsSku = null;
			for (GoodsSpec goodsSpec : specList) {
				goodsSku = new GoodsSku();
				goodsSku.setGoodsSpecId(goodsSpec.getId());
				List<GoodsSku> skuList = goodsSkuService.findList(goodsSku);
				Collections.sort(skuList, new Comparator<GoodsSku>() {
					@Override
					public int compare(GoodsSku o1, GoodsSku o2) {
						// 返回值为int类型，大于0表示正序，小于0表示逆序
						return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
					}
				});

				model.addAttribute("skuList", skuList);
				goods.setStock(goodsSpec.getStock().intValue());
				goods.setUnit(goodsSpec.getUnit());
			}
		}
		Collections.sort(specList, new Comparator<GoodsSpec>() {
			@Override
			public int compare(GoodsSpec o1, GoodsSpec o2) {
				// 返回值为int类型，大于0表示正序，小于0表示逆序
				return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
			}
		});
		model.addAttribute("specList", specList);

		if (null != goods.getGoodsCategory() && StringUtils.isNotBlank(goods.getGoodsCategory().getId())) {
			GoodsCategory goodsCategory = goodsCategoryService.get(goods.getGoodsCategory().getId());
			if (null != goods) {
				goods.setGoodsCategory(goodsCategory);
			}
		}

		goods.setDescription(StringEscapeUtils.unescapeHtml3(goods.getDescription()));
		if (StringUtils.isNotBlank(goods.getMainUrls())) {
			model.addAttribute("picList", JsonMapper.toJsonString(StringUtils.split(goods.getMainUrls(), ";")));
		}
		if (StringUtils.isNotBlank(goods.getDescPic())) {
			model.addAttribute("descPicList", JsonMapper.toJsonString(StringUtils.split(goods.getDescPic(), ";")));
		}
		return "web/goods/goodsForm";
	}

	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "save")
	public String save(Goods goods, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws RpcException {
		if (!beanValidator(model, goods)) {
			return form(goods, model);
		}

		// 组装规格和sku
		convertSpecAndSku(goods, request);

		// 组装图片链接
		convertPicUrl(goods, request);

		if (StringUtils.isBlank(goods.getId())) {
			goods.setGoodsNo(commonApi.getSerialNo(UserUtils.getMerchantId(), RULE_GOODS_NO));
			goods.setGoodsStatus("1"); // 商品未发布
			goods.setMerchantId(UserUtils.getMerchantId());
		}

		goodsService.save(goods);
		addMessage(redirectAttributes, "保存商品成功");
		return "redirect:" + Global.getAdminPath() + "/goods/goods/?repage";
	}

	/**
	 * 组装图片链接
	 *
	 * @param goods
	 * @param request
	 */
	private void convertPicUrl(Goods goods, HttpServletRequest request) {
		// 商品主图
		String goodsPicList[] = request.getParameterValues("goodsPic");
		if (ArrayUtils.isNotEmpty(goodsPicList)) {
			for (int i = 0; i < goodsPicList.length; i++) {
				goodsPicList[i] = StringUtils.substringBefore(goodsPicList[i], "?");
			}
			goods.setGoodsPicList(Arrays.asList(goodsPicList));
			goods.setMainUrls(StringUtils.join(goodsPicList, ";").replace("_s.jpg", ".jpg"));
		}

		// 商品描述图
		String goodsDescPicList[] = request.getParameterValues("goodsDescPic");
		if (ArrayUtils.isNotEmpty(goodsDescPicList)) {
			for (int i = 0; i < goodsDescPicList.length; i++) {
				goodsDescPicList[i] = StringUtils.substringBefore(goodsDescPicList[i], "?");
			}
			goods.setGoodsDescPicList(Arrays.asList(goodsDescPicList));

			goods.setDescPic(StringUtils.join(goodsDescPicList, ";").replace("_s.jpg", ".jpg"));
		}
	}

	/**
	 * 组装规格和sku
	 *
	 * @param goods
	 * @param request
	 */
	private void convertSpecAndSku(Goods goods, HttpServletRequest request) {
		List<GoodsSpec> goodsSpec = Lists.newArrayList();
		User user = UserUtils.getUser();

		String goodsType = goods.getGoodsType();
		if (GOODS_TYPE_1.equals(goodsType)) { 
			// 批发商品
			GoodsSpec gs = setGoodsSpec("默认", BigDecimal.ZERO, goods.getStock(), goods.getUnit(), user);
			goodsSpec.add(gs);
			
			List<GoodsSku> skuList = Lists.newArrayList();
			gs.setSkuList(skuList);

			String[] skus = request.getParameterValues("goodsSku");
			if (skus == null || skus.length <= 1 || skus[1].equals("")) {
				if (StringUtils.isBlank(goods.getId())) {
					return;
				}
			}
			if (ArrayUtils.isNotEmpty(skus)) {
				String sps[] = skus[1].split(";");
				for (String s : sps) {
					String[] ss = s.split(":");
					GoodsSku sku = setGoodsSku(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]),
							new BigDecimal(ss[2]), user);
					if (ss.length > 3) {
						sku.setId(ss[3]);
					}
					skuList.add(sku);
				}
			}

		} else { 
			// 普通商品
			String[] specs = request.getParameterValues("goodsSpec");
			if (specs == null || specs.length <= 1 || specs[1].equals("")) {
				if (StringUtils.isBlank(goods.getId())) {
					return;
				}
			}

			if (ArrayUtils.isNotEmpty(specs)) {
				String sps[] = specs[1].split(";");
				for (String s : sps) {
					String[] ss = s.split(":");
					GoodsSpec gs = setGoodsSpec(ss[0], new BigDecimal(ss[1]),
							Integer.parseInt(ss[2]), goods.getUnit(), user);
					if (ss.length > 3) {
						gs.setId(ss[3]);
					}
					if (StringUtils.isNotBlank(goods.getUnit())) {
						gs.setUnit(goods.getUnit());
					}
					goodsSpec.add(gs);
				}
			}
		}

		goods.setGoodsSpecs(goodsSpec);
	}

	/**
	 * 创建规格数据
	 *
	 * @param name
	 * @param price
	 * @param stock
	 * @param user
	 */
	private GoodsSpec setGoodsSpec(String name, BigDecimal price, Integer stock, String unit, User user) {
		GoodsSpec gs = new GoodsSpec();
		gs.setName(name);
		gs.setPrice(price);
		gs.setStock(stock);
		gs.setUnit(unit);

		gs.setCreateBy(user);
		gs.setCreateDate(new Date());
		gs.setUpdateBy(user);
		gs.setUpdateDate(new Date());
		return gs;
	}

	/**
	 * 创建sku数据
	 *
	 * @param minNum
	 * @param maxNum
	 * @param price
	 * @param user
	 * @return
	 */
	private GoodsSku setGoodsSku(Integer minNum, Integer maxNum, BigDecimal price, User user) {
		GoodsSku gs = new GoodsSku();
		gs.setMinNum(minNum);
		gs.setMaxNum(maxNum);
		gs.setPrice(price);

		gs.setCreateBy(user);
		gs.setCreateDate(new Date());
		gs.setUpdateBy(user);
		gs.setUpdateDate(new Date());
		gs.setDelFlag("0");
		return gs;
	}

	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "delete")
	public String delete(Goods goods, RedirectAttributes redirectAttributes) {
		goodsService.delete(goods);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:" + Global.getAdminPath() + "/goods/goods/?repage";
	}
	
	@RequiresPermissions("goods:goods:edit")
	@RequestMapping(value = "putOff")
	public String putOff(Goods goods, RedirectAttributes redirectAttributes) {
		Goods g = goodsService.get(goods);
		Goods modify = new Goods();
		modify.setId(g.getId());
		modify.setUpdateDate(new Date());
		modify.setUpdateBy(UserUtils.getUser());
		if (StringUtils.equals(g.getGoodsStatus(), Constant.GOODS_STATUS_1) 
				|| StringUtils.equals(g.getGoodsStatus(), Constant.GOODS_STATUS_3)) {
			modify.setGoodsStatus(Constant.GOODS_STATUS_2);
			goodsService.updateSelective(modify);
			addMessage(redirectAttributes, "商品上架成功");
		} else if (StringUtils.equals(g.getGoodsStatus(), Constant.GOODS_STATUS_2)) {
			modify.setGoodsStatus(Constant.GOODS_STATUS_3);
			goodsService.updateSelective(modify);
			addMessage(redirectAttributes, "商品下架成功");
		}
		return "redirect:" + Global.getAdminPath() + "/goods/goods/?repage";
	}
}
