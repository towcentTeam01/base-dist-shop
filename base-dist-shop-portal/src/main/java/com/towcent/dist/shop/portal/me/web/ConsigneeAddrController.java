package com.towcent.dist.shop.portal.me.web;

import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrDelIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrSaveIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrListIn;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.me.biz.ConsigneeAddrService;

/**
 * ConsigneeAddrController
 *
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "me/consigneeAddr", method = RequestMethod.POST)
public class ConsigneeAddrController extends BaseController {

    @Resource
    private ConsigneeAddrService consigneeAddrService;

    // 3.1.1 收货地址管理
    @RequestMapping(value = "list")
    @Loggable
    public ResultVo list(@RequestBody ConsigneeAddrListIn paramIn) {
        return consigneeAddrService.list(paramIn);
    }

    // 3.1.2 新增/修改收货地址管理
    @RequestMapping(value = "save")
    @Loggable
    public ResultVo save(@RequestBody ConsigneeAddrSaveIn paramIn) {
        return consigneeAddrService.save(paramIn);
    }

    // 3.1.3 删除收货地址管理
    @RequestMapping(value = "del")
    @Loggable
    public ResultVo del(@RequestBody ConsigneeAddrDelIn paramIn) {
        return consigneeAddrService.del(paramIn);
    }
}