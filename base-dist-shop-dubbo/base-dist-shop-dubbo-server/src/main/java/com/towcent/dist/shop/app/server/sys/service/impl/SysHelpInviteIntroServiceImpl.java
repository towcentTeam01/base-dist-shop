package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysHelpInviteIntroMapper;
import com.towcent.dist.shop.app.server.sys.service.SysHelpInviteIntroService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-07-02 11:30:34
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysHelpInviteIntroServiceImpl")
public class SysHelpInviteIntroServiceImpl extends BaseCrudServiceImpl implements SysHelpInviteIntroService {

    @Resource
    private SysHelpInviteIntroMapper sysHelpInviteIntroMapper;

    @Override
    public CrudMapper init() {
        return sysHelpInviteIntroMapper;
    }

}