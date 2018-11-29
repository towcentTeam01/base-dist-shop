package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysHelpRuleMapper;
import com.towcent.dist.shop.app.server.sys.service.SysHelpRuleService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-07-01 17:34:29
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysHelpRuleServiceImpl")
public class SysHelpRuleServiceImpl extends BaseCrudServiceImpl implements SysHelpRuleService {

    @Resource
    private SysHelpRuleMapper sysHelpRuleMapper;

    @Override
    public CrudMapper init() {
        return sysHelpRuleMapper;
    }

}