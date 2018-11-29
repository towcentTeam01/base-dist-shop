package com.towcent.dist.shop.app.server.share.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.share.dao.ShareInfoMapper;
import com.towcent.dist.shop.app.server.share.service.ShareInfoService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author shiwei
 * @date 2018-07-31 20:14:05
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("shareInfoServiceImpl")
public class ShareInfoServiceImpl extends BaseCrudServiceImpl implements ShareInfoService {

    @Resource
    private ShareInfoMapper shareInfoMapper;

    @Override
    public CrudMapper init() {
        return shareInfoMapper;
    }

}