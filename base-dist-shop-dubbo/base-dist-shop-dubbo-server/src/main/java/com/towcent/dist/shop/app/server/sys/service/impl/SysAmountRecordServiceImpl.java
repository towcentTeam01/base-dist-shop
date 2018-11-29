package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysAmountRecordMapper;
import com.towcent.dist.shop.app.server.sys.service.SysAmountRecordService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-25 15:56:30
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysAmountRecordServiceImpl")
public class SysAmountRecordServiceImpl extends BaseCrudServiceImpl implements SysAmountRecordService {

    @Resource
    private SysAmountRecordMapper sysAmountRecordMapper;

    @Override
    public CrudMapper init() {
        return sysAmountRecordMapper;
    }

}