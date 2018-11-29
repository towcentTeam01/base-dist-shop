package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysPosterTemplateMapper;
import com.towcent.dist.shop.app.server.sys.service.SysPosterTemplateService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-07-29 23:32:43
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysPosterTemplateServiceImpl")
public class SysPosterTemplateServiceImpl extends BaseCrudServiceImpl implements SysPosterTemplateService {

    @Resource
    private SysPosterTemplateMapper sysPosterTemplateMapper;

    @Override
    public CrudMapper init() {
        return sysPosterTemplateMapper;
    }

}