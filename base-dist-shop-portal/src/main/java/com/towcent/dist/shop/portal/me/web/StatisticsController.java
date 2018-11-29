package com.towcent.dist.shop.portal.me.web;

import com.towcent.dist.shop.portal.me.vo.input.StatisticsInfoIn;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;

import com.towcent.dist.shop.portal.me.biz.StatisticsService;

/**
 * StatisticsController
 *
 * @author huangtao
 * @version 0.0.1
 */
@RestController
@RequestMapping(value = "me/statistics", method = RequestMethod.POST)
public class StatisticsController extends BaseController {

    @Resource
    private StatisticsService statisticsService;

    // 3.0.1 我的信息汇总接口
    @RequestMapping(value = "info")
    @Loggable
    public ResultVo info(@RequestBody StatisticsInfoIn paramIn) {
        return statisticsService.info(paramIn);
    }
}