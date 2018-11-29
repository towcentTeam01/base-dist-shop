package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysIntegralRecordMapper;
import com.towcent.dist.shop.app.server.sys.service.SysIntegralRecordService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-25 16:00:12
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysIntegralRecordServiceImpl")
public class SysIntegralRecordServiceImpl extends BaseCrudServiceImpl implements SysIntegralRecordService {

    @Resource
    private SysIntegralRecordMapper sysIntegralRecordMapper;

    @Override
    public CrudMapper init() {
        return sysIntegralRecordMapper;
    }

}