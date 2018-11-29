package com.towcent.dist.shop.app.server.mall.service;

import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.service.BaseCrudService;

import java.util.Map;

/**
 * order_main 数据库操作Service接口
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
public interface OrderMainService extends BaseCrudService {

    Map<String, Object> queryOrderNum(Map<String, Object> params) throws ServiceException;
	
}