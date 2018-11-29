package com.towcent.dist.shop.app.server.me.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.me.dao.ConsigneeAddrMapper;
import com.towcent.dist.shop.app.server.me.service.ConsigneeAddrService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 10:20:15
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("consigneeAddrServiceImpl")
public class ConsigneeAddrServiceImpl extends BaseCrudServiceImpl implements ConsigneeAddrService {

    @Resource
    private ConsigneeAddrMapper consigneeAddrMapper;

    @Override
    public CrudMapper init() {
        return consigneeAddrMapper;
    }

}