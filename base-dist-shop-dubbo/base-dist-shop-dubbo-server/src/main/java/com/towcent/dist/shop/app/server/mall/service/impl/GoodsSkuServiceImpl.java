package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.GoodsSkuMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsSkuService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 17:42:23
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsSkuServiceImpl")
public class GoodsSkuServiceImpl extends BaseCrudServiceImpl implements GoodsSkuService {

    @Resource
    private GoodsSkuMapper goodsSkuMapper;

    @Override
    public CrudMapper init() {
        return goodsSkuMapper;
    }

}