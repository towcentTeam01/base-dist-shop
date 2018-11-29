package com.towcent.dist.shop.web.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.coupon.entity.CouponAct;
import com.towcent.dist.shop.web.coupon.dao.CouponActDao;

/**
 * 优惠券Service
 * @author yxp
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class CouponActService extends CrudService<CouponActDao, CouponAct> {

	public CouponAct get(String id) {
		return super.get(id);
	}
	
	public List<CouponAct> findList(CouponAct couponAct) {
		return super.findList(couponAct);
	}
	
	public Page<CouponAct> findPage(Page<CouponAct> page, CouponAct couponAct) {
		return super.findPage(page, couponAct);
	}
	
	@Transactional(readOnly = false)
	public void save(CouponAct couponAct) {
		super.save(couponAct);
	}
	
	@Transactional(readOnly = false)
	public void delete(CouponAct couponAct) {
		super.delete(couponAct);
	}
	
}