package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.GoodsChannelDtlMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsChannelDtlService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsChannelDtlServiceImpl")
public class GoodsChannelDtlServiceImpl extends BaseCrudServiceImpl implements GoodsChannelDtlService {

    @Resource
    private GoodsChannelDtlMapper goodsChannelDtlMapper;

    @Override
    public CrudMapper init() {
        return goodsChannelDtlMapper;
    }

}