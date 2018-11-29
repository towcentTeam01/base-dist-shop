package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysHelpGuideMapper;
import com.towcent.dist.shop.app.server.sys.service.SysHelpGuideService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-07-02 11:30:33
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysHelpGuideServiceImpl")
public class SysHelpGuideServiceImpl extends BaseCrudServiceImpl implements SysHelpGuideService {

    @Resource
    private SysHelpGuideMapper sysHelpGuideMapper;

    @Override
    public CrudMapper init() {
        return sysHelpGuideMapper;
    }

}