package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.CouponClaimMapper;
import com.towcent.dist.shop.app.server.mall.service.CouponClaimService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("couponClaimServiceImpl")
public class CouponClaimServiceImpl extends BaseCrudServiceImpl implements CouponClaimService {

    @Resource
    private CouponClaimMapper couponClaimMapper;

    @Override
    public CrudMapper init() {
        return couponClaimMapper;
    }

}