package com.towcent.dist.shop.app.server.share.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.share.dao.ShareCategoryMapper;
import com.towcent.dist.shop.app.server.share.service.ShareCategoryService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author shiwei
 * @date 2018-07-31 11:36:43
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("shareCategoryServiceImpl")
public class ShareCategoryServiceImpl extends BaseCrudServiceImpl implements ShareCategoryService {

    @Resource
    private ShareCategoryMapper shareCategoryMapper;

    @Override
    public CrudMapper init() {
        return shareCategoryMapper;
    }

}