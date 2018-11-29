package com.towcent.dist.shop.app.server.me.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.me.dao.ConcernGoodsMapper;
import com.towcent.dist.shop.app.server.me.service.ConcernGoodsService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 18:41:27
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("concernGoodsServiceImpl")
public class ConcernGoodsServiceImpl extends BaseCrudServiceImpl implements ConcernGoodsService {

    @Resource
    private ConcernGoodsMapper concernGoodsMapper;

    @Override
    public CrudMapper init() {
        return concernGoodsMapper;
    }

}