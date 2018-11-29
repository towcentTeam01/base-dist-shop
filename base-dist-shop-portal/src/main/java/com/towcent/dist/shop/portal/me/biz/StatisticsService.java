package com.towcent.dist.shop.portal.me.biz;

import com.towcent.dist.shop.portal.me.vo.input.StatisticsInfoIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * StatisticsService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface StatisticsService {

    ResultVo info(StatisticsInfoIn paramIn);
}