package com.towcent.dist.shop.app.server.mall.service;

import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.service.BaseCrudService;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * order_dtl 数据库操作Service接口
 * 
 * @author huangtao
 * @date 2018-06-28 18:16:12
 * @version 1.0
 * @copyright facegarden.com
 */
public interface OrderDtlService extends BaseCrudService {

    /**
     * 查询评价订单商品
     *
     * @param params
     * @return
     */
    Integer selectEvalCount(@Param("params") Map<String, Object> params) throws ServiceException;

    /**
     * 查询评价订单商品
     *
     * @param params
     * @return
     */
    List<OrderDtl> selectEvalByPage(@Param("page") SimplePage page, @Param("params") Map<String, Object> params) throws ServiceException;
	
}