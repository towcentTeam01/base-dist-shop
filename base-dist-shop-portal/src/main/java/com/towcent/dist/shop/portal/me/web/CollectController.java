package com.towcent.dist.shop.portal.me.web;

import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsDelIn;
import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsListIn;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.me.biz.CollectService;

/**
 * CollectController
 *
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "me/collect", method = RequestMethod.POST)
public class CollectController extends BaseController {

    @Resource
    private CollectService collectService;

    // 3.2.1 我收藏的商品
    @RequestMapping(value = "goodsList")
    @Loggable
    public ResultVo goodsList(@RequestBody CollectGoodsListIn paramIn) {
        return collectService.goodsList(paramIn);
    }

    // 3.2.2 删除收藏商品
    @RequestMapping(value = "goodsDel")
    @Loggable
    public ResultVo goodsDel(@RequestBody CollectGoodsDelIn paramIn) {
        return collectService.goodsDel(paramIn);
    }
}