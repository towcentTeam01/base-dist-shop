package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.dist.shop.app.server.sys.dao.SysUserMapper;
import com.towcent.dist.shop.app.server.sys.service.SysUserService;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Generator
 * @date 2017-06-17 23:27:06
 * @version 1.0.0
 * @copyright facegarden.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseCrudServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public CrudMapper init() {
        return sysUserMapper;
    }
}