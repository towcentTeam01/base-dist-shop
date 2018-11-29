package com.towcent.dist.shop.web.order.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.sc.web.common.config.Global;
import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.utils.StringUtils;
import com.towcent.base.sc.web.common.web.BaseController;
import com.towcent.base.sc.web.modules.sys.utils.UserUtils;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.web.config.entity.SysLogisticsCompanyMerchant;
import com.towcent.dist.shop.web.config.service.SysLogisticsCompanyMerchantService;
import com.towcent.dist.shop.web.order.entity.OrderDtl;
import com.towcent.dist.shop.web.order.entity.OrderMain;
import com.towcent.dist.shop.web.order.service.OrderDtlService;
import com.towcent.dist.shop.web.order.service.OrderMainService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.E_000;
import static com.towcent.base.common.constants.BaseConstant.E_001;

/**
 * 订单列表Controller
 *
 * @author huangtao
 * @version 2018-07-02
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderMain")
public class OrderMainController extends BaseController {

    @Autowired
    private OrderMainService orderMainService;

    @Autowired
    private OrderDtlService orderDtlService;

    @Autowired
    private SysLogisticsCompanyMerchantService logisticsCompanyMerchantService;

    @ModelAttribute
    public OrderMain get(@RequestParam(required = false) String id) {
        OrderMain entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = orderMainService.get(id);
        }
        if (entity == null) {
            entity = new OrderMain();
        }
        return entity;
    }

    @RequiresPermissions("order:orderMain:view")
    @RequestMapping(value = {"list", ""})
    public String list(
            OrderMain orderMain, HttpServletRequest request, HttpServletResponse response, Model model) {
    	Integer merchantId = UserUtils.getMerchantId();
    	orderMain.setMerchantId(merchantId);
    	Page<OrderMain> p = new Page<OrderMain>(request, response);
        p.setOrderBy("a.create_date DESC");
        Page<OrderMain> page = orderMainService.findPage(p, orderMain);
        model.addAttribute("page", page);
        return "web/order/orderMainList";
    }

    @RequiresPermissions("order:orderMain:view")
    @RequestMapping(value = "form")
    public String form(OrderMain orderMain, Model model) {
        model.addAttribute("orderMain", orderMain);
        return "web/order/orderMainForm";
    }

    @RequiresPermissions("order:orderMain:edit")
    @RequestMapping(value = "save")
    public String save(OrderMain orderMain, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, orderMain)) {
            return form(orderMain, model);
        }
        orderMainService.save(orderMain);
        addMessage(redirectAttributes, "保存订单成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderMain/?repage";
    }

    @RequiresPermissions("order:orderMain:edit")
    @RequestMapping(value = "delete")
    public String delete(OrderMain orderMain, RedirectAttributes redirectAttributes) {
        orderMainService.delete(orderMain);
        addMessage(redirectAttributes, "删除订单成功");
        return "redirect:" + Global.getAdminPath() + "/order/orderMain/?repage";
    }

    @ResponseBody
    @RequestMapping(value = "sendGoods")
    public ResultVo sendGoods(
            @RequestParam(required = true) String id,
            String freightNumber,
            String logisticsNo,
            String logisticsName) {
        ResultVo resultVo = new ResultVo();
        try {

            OrderMain entity = orderMainService.get(id);
            if (null != entity) {
                if (Constant.ORDER_STATUS_1.equals(entity.getOrderStatus())) {
                    entity.setOrderStatus(Constant.ORDER_STATUS_2);
                    entity.setLogisticsNo(logisticsNo);
                    entity.setLogisticsName(logisticsName);
                    entity.setFreightNumber(freightNumber);
                    entity.setDeliveryTime(new Date());
                    orderMainService.sendGoods(entity);

                    return resultVo(resultVo, E_000, "发货成功");

                } else {
                    return resultVo(resultVo, E_001, "该状态的订单不能执行此操作");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVo(resultVo, E_001, "系统异常，请稍候再试");
    }

    protected ResultVo resultVo(ResultVo vo, String code, String message) {
        vo.setCode(code);
        vo.setErrorMessage(message);
        return vo;
    }

    @RequiresPermissions("order:orderMain:edit")
    @RequestMapping(value = "detail")
    public String detail(OrderMain orderMain, Model model) {

        OrderDtl orderDtl = new OrderDtl();
        orderDtl.setOrderId(orderMain.getId());
        List<OrderDtl> dlist = orderDtlService.findList(orderDtl);
        model.addAttribute("orderDtlList", dlist);

        model.addAttribute("orderMain", orderMain);

        return "web/order/orderDetail";
    }

    @ResponseBody
    @RequestMapping(value = "checkLogNo")
    public ResultVo checkLogNo(
            @RequestParam(required = false) String freightNumber, String logisticsNo,
            String logisticsName) {
        ResultVo resultVo = new ResultVo();
        try {

            OrderMain entity = new OrderMain();
            entity.setFreightNumber(freightNumber);
            entity.setLogisticsNo(logisticsNo);
            entity.setLogisticsName(logisticsName);
            List<OrderMain> applyList = orderMainService
                    .findList(entity);
            if (CollectionUtils.isNotEmpty(applyList)) {
                return resultVo(resultVo, E_000, "该物流单号已使用");
            } else {
                return resultVo(resultVo, E_000, "物流单号可以使用");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultVo(resultVo, E_001, "系统异常，请稍候再试");
    }

    @ResponseBody
    @RequestMapping(value = "getExpressCompany")
    public List<Map<String, Object>> getExpressCompany() {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        SysLogisticsCompanyMerchant expressCompany = new SysLogisticsCompanyMerchant();
        expressCompany.setMerchantId(UserUtils.getMerchantId());
        expressCompany.setDelFlag(Constant.DEL_FLAG_0);
        List<SysLogisticsCompanyMerchant> list = logisticsCompanyMerchantService.findList(expressCompany);
        for (SysLogisticsCompanyMerchant entity : list) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", entity.getCompany().getId());
            map.put("code", entity.getCompany().getCompanyNo());
            map.put("name", entity.getCompany().getCompanyName());
            mapList.add(map);
        }
        return mapList;
    }

}
