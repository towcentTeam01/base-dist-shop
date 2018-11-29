package com.towcent.dist.shop.web.order.web;

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
import com.towcent.dist.shop.web.order.entity.OrderPayRecord;
import com.towcent.dist.shop.web.order.service.OrderPayRecordService;

/**
 * 交易记录Controller
 * @author HuangTao
 * @version 2018-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderPayRecord")
public class OrderPayRecordController extends BaseController {

	@Autowired
	private OrderPayRecordService orderPayRecordService;
	
	@ModelAttribute
	public OrderPayRecord get(@RequestParam(required=false) String id) {
		OrderPayRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderPayRecordService.get(id);
		}
		if (entity == null){
			entity = new OrderPayRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orderPayRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderPayRecord orderPayRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		orderPayRecord.setMerchantId(merchantId);
		Page<OrderPayRecord> p = new Page<OrderPayRecord>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<OrderPayRecord> page = orderPayRecordService.findPage(p, orderPayRecord); 
		model.addAttribute("page", page);
		return "web/order/orderPayRecordList";
	}

	@RequiresPermissions("order:orderPayRecord:view")
	@RequestMapping(value = "form")
	public String form(OrderPayRecord orderPayRecord, Model model) {
		model.addAttribute("orderPayRecord", orderPayRecord);
		return "web/order/orderPayRecordForm";
	}

	@RequiresPermissions("order:orderPayRecord:edit")
	@RequestMapping(value = "save")
	public String save(OrderPayRecord orderPayRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderPayRecord)){
			return form(orderPayRecord, model);
		}
		orderPayRecordService.save(orderPayRecord);
		addMessage(redirectAttributes, "保存交易记录成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderPayRecord/?repage";
	}
	
	@RequiresPermissions("order:orderPayRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderPayRecord orderPayRecord, RedirectAttributes redirectAttributes) {
		orderPayRecordService.delete(orderPayRecord);
		addMessage(redirectAttributes, "删除交易记录成功");
		return "redirect:"+Global.getAdminPath()+"/order/orderPayRecord/?repage";
	}

}