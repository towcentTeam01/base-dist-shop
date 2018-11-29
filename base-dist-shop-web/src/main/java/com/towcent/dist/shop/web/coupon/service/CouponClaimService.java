package com.towcent.dist.shop.web.coupon.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.coupon.entity.CouponClaim;
import com.towcent.dist.shop.web.coupon.dao.CouponClaimDao;

/**
 * 优惠券Service
 * @author yxp
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class CouponClaimService extends CrudService<CouponClaimDao, CouponClaim> {

	public CouponClaim get(String id) {
		return super.get(id);
	}
	
	public List<CouponClaim> findList(CouponClaim couponClaim) {
		return super.findList(couponClaim);
	}
	
	public Page<CouponClaim> findPage(Page<CouponClaim> page, CouponClaim couponClaim) {
		return super.findPage(page, couponClaim);
	}
	
	@Transactional(readOnly = false)
	public void save(CouponClaim couponClaim) {
		super.save(couponClaim);
	}
	
	@Transactional(readOnly = false)
	public void delete(CouponClaim couponClaim) {
		super.delete(couponClaim);
	}
	
}