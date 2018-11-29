package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.CouponActMapper;
import com.towcent.dist.shop.app.server.mall.service.CouponActService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("couponActServiceImpl")
public class CouponActServiceImpl extends BaseCrudServiceImpl implements CouponActService {

    @Resource
    private CouponActMapper couponActMapper;

    @Override
    public CrudMapper init() {
        return couponActMapper;
    }

}