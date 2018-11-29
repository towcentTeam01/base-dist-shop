package com.towcent.dist.shop.app.server.sys.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.sys.dao.SysLogisticsCompanyMerchantMapper;
import com.towcent.dist.shop.app.server.sys.service.SysLogisticsCompanyMerchantService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-07-11 18:40:58
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysLogisticsCompanyMerchantServiceImpl")
public class SysLogisticsCompanyMerchantServiceImpl extends BaseCrudServiceImpl implements SysLogisticsCompanyMerchantService {

    @Resource
    private SysLogisticsCompanyMerchantMapper sysLogisticsCompanyMerchantMapper;

    @Override
    public CrudMapper init() {
        return sysLogisticsCompanyMerchantMapper;
    }

}