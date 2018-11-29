package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.GoodsSpecMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsSpecService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 12:16:53
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsSpecServiceImpl")
public class GoodsSpecServiceImpl extends BaseCrudServiceImpl implements GoodsSpecService {

    @Resource
    private GoodsSpecMapper goodsSpecMapper;

    @Override
    public CrudMapper init() {
        return goodsSpecMapper;
    }

}