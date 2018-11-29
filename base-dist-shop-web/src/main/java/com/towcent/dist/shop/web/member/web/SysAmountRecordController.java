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
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.dist.shop.web.member.entity.SysAmountRecord;
import com.towcent.dist.shop.web.member.service.SysAmountRecordService;

/**
 * 钱包明细Controller
 * @author huangtao
 * @version 2018-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/member/sysAmountRecord")
public class SysAmountRecordController extends BaseController {

	@Autowired
	private SysAmountRecordService sysAmountRecordService;
	
	@ModelAttribute
	public SysAmountRecord get(@RequestParam(required=false) String id) {
		SysAmountRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = sysAmountRecordService.get(id);
		}
		if (entity == null){
			entity = new SysAmountRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("member:sysAmountRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysAmountRecord sysAmountRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Integer merchantId = UserUtils.getMerchantId();
		sysAmountRecord.setMerchantId(merchantId);
		Page<SysAmountRecord> p = new Page<SysAmountRecord>(request, response);
		p.setOrderBy("a.create_date DESC");
		Page<SysAmountRecord> page = sysAmountRecordService.findPage(p, sysAmountRecord); 
		model.addAttribute("page", page);
		return "web/member/sysAmountRecordList";
	}

	@RequiresPermissions("member:sysAmountRecord:view")
	@RequestMapping(value = "form")
	public String form(SysAmountRecord sysAmountRecord, Model model) {
		model.addAttribute("sysAmountRecord", sysAmountRecord);
		return "web/member/sysAmountRecordForm";
	}

	@RequiresPermissions("member:sysAmountRecord:edit")
	@RequestMapping(value = "save")
	public String save(SysAmountRecord sysAmountRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysAmountRecord)){
			return form(sysAmountRecord, model);
		}
		sysAmountRecordService.save(sysAmountRecord);
		addMessage(redirectAttributes, "保存钱包明细成功");
		return "redirect:"+Global.getAdminPath()+"/member/sysAmountRecord/?repage";
	}
	
	@RequiresPermissions("member:sysAmountRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(SysAmountRecord sysAmountRecord, RedirectAttributes redirectAttributes) {
		sysAmountRecordService.delete(sysAmountRecord);
		addMessage(redirectAttributes, "删除钱包明细成功");
		return "redirect:"+Global.getAdminPath()+"/member/sysAmountRecord/?repage";
	}

}