package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysWithdrawApplyMapper;
import com.towcent.dist.shop.app.server.sys.service.SysWithdrawApplyService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-30 00:10:57
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysWithdrawApplyServiceImpl")
public class SysWithdrawApplyServiceImpl extends BaseCrudServiceImpl implements SysWithdrawApplyService {

    @Resource
    private SysWithdrawApplyMapper sysWithdrawApplyMapper;

    @Override
    public CrudMapper init() {
        return sysWithdrawApplyMapper;
    }

}