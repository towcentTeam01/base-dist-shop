package com.towcent.dist.shop.web.coupon.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.coupon.entity.CouponAct;

/**
 * 优惠券DAO接口
 * @author yxp
 * @version 2018-07-06
 */
@MyBatisDao
public interface CouponActDao extends CrudDao<CouponAct> {
	
}