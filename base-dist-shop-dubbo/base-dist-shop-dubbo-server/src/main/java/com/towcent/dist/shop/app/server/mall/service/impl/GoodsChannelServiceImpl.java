package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.GoodsChannelMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsChannelService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsChannelServiceImpl")
public class GoodsChannelServiceImpl extends BaseCrudServiceImpl implements GoodsChannelService {

    @Resource
    private GoodsChannelMapper goodsChannelMapper;

    @Override
    public CrudMapper init() {
        return goodsChannelMapper;
    }

}