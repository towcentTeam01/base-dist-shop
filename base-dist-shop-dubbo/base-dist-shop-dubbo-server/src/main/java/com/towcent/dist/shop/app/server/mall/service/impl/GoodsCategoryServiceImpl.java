package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.GoodsCategoryMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsCategoryService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsCategoryServiceImpl")
public class GoodsCategoryServiceImpl extends BaseCrudServiceImpl implements GoodsCategoryService {

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public CrudMapper init() {
        return goodsCategoryMapper;
    }

}