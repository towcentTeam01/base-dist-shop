package com.towcent.dist.shop.portal.mall.biz;

import com.towcent.dist.shop.portal.mall.vo.input.*;
import com.towcent.base.common.vo.ResultVo;

/**
 * OrderService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface OrderService {

    ResultVo create(OrderCreateIn paramIn);

    ResultVo list(OrderListIn paramIn);

    ResultVo evalList(OrderEvalListIn paramIn);

    ResultVo del(OrderDelIn paramIn);

    ResultVo eval(OrderEvalIn paramIn);

    ResultVo cancel(OrderCancelIn paramIn);

    ResultVo receipt(OrderReceiptIn paramIn);

    ResultVo detail(OrderDetailIn paramIn);
    
    ResultVo trace(LogisTraceIn paramIn);
}