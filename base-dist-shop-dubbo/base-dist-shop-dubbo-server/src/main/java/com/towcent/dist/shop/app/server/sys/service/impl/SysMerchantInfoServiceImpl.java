package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysMerchantInfoMapper;
import com.towcent.dist.shop.app.server.sys.service.SysMerchantInfoService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-07-05 18:00:37
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysMerchantInfoServiceImpl")
public class SysMerchantInfoServiceImpl extends BaseCrudServiceImpl implements SysMerchantInfoService {

    @Resource
    private SysMerchantInfoMapper sysMerchantInfoMapper;

    @Override
    public CrudMapper init() {
        return sysMerchantInfoMapper;
    }

}