package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.OrderPayRecordMapper;
import com.towcent.dist.shop.app.server.mall.service.OrderPayRecordService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("orderPayRecordServiceImpl")
public class OrderPayRecordServiceImpl extends BaseCrudServiceImpl implements OrderPayRecordService {

    @Resource
    private OrderPayRecordMapper orderPayRecordMapper;

    @Override
    public CrudMapper init() {
        return orderPayRecordMapper;
    }

}