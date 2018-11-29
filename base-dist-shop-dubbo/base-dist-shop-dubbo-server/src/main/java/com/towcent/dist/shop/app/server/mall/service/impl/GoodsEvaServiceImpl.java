package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.GoodsEvaMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsEvaService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-27 15:44:01
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsEvaServiceImpl")
public class GoodsEvaServiceImpl extends BaseCrudServiceImpl implements GoodsEvaService {

    @Resource
    private GoodsEvaMapper goodsEvaMapper;

    @Override
    public CrudMapper init() {
        return goodsEvaMapper;
    }

}