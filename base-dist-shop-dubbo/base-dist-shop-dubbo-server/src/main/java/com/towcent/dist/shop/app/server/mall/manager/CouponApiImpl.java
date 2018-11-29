package com.towcent.dist.shop.app.server.mall.manager;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.service.BaseService;
import com.towcent.dist.shop.app.client.mall.dto.CouponAct;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.mall.sevice.CouponApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.server.mall.service.CouponActService;
import com.towcent.dist.shop.app.server.mall.service.CouponClaimService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.base.common.constants.BaseConstant.NO;
import static com.towcent.base.common.constants.BaseConstant.YES;
import static com.towcent.dist.shop.common.Constant.*;

@Service
public class CouponApiImpl extends BaseService implements CouponApi {

	@Resource
	private CouponActService couponActService;

	@Resource
	private CouponClaimService couponClaimService;

	@Override
	public PaginationDto<CouponAct> listForPage(Map<String, Object> params, SimplePageDto pageDto) throws RpcException {
		try {
			params.put("delFlag", DEL_FLAG_0);
			int totalCount = couponActService.findCount(params);
			SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
			List<CouponAct> list = couponActService.findByPage(page, "create_date", "DESC", params);
			return new PaginationDto<>(totalCount, list);
		} catch (ServiceException e) {
			logger.error("查询优惠券活动分页信息失败", e);
			throw new RpcException("", "查询优惠券活动分页信息失败", e);
		}
	}

	@Override
	public List<CouponAct> queryList(Map<String, Object> params) throws RpcException {
		try {
			params.put("delFlag", DEL_FLAG_0);
			params.put("openFlag", YES);
			return couponActService.findByBiz(params);
		} catch (ServiceException e) {
			logger.error("查询优惠券活动列表失败", e);
			throw new RpcException("", "查询优惠券活动列表失败", e);
		}
	}

	@Override
	@Transactional
	public boolean couponDraw(Integer actId, SysFrontAccount account) throws RpcException {
		try {
			CouponAct act = new CouponAct();
			act.setId(actId);
			act = couponActService.findById(act);
			if (null == act) {
				throw new ServiceException("该优惠券活动已不存在");
			}

			if (act.getResidQty() <= 0) {
				throw new ServiceException("优惠券剩余数量为零");
			}

			Date currDate = new Date();

			// 判断活动是否开始
			if (!currDate.after(act.getStartTime())) {
				throw new ServiceException("该优惠券活动位开始");
			}
			// 判断活动是否结束
			if (!currDate.before(act.getEndTime())) {
				throw new ServiceException("该优惠券活动已结束");
			}

			Map<String, Object> map = Maps.newHashMap();
			map.put("actId", actId);
			map.put("merchantId", act.getMerchantId());
			int count = couponClaimService.findCount(map);
			if (count > 0) {
				throw new ServiceException("已经领取过该优惠券");
			}

			CouponClaim claim = new CouponClaim();
			claim.setActId(actId);
			claim.setAmount(act.getAmount());
			claim.setLimitAmount(act.getLimitAmount());
			claim.setUserId(account.getId());
			claim.setMerchantId(act.getMerchantId());
			claim.setNickName(account.getNickName());
			claim.setUseFlag(NO);
			claim.setDelFlag(DEL_FLAG_0);
			claim.setCreateBy(account.getId());
			claim.setUpdateBy(account.getId());
			claim.setCreateDate(new Date());
			claim.setUpdateDate(new Date());

			int num = couponClaimService.add(claim);

			act.setResidQty(act.getResidQty() - 1);
			couponActService.modifyById(act);

			return num > 0 ? true : false;
		} catch (ServiceException e) {
			logger.error("领取优惠券失败", e);
			throw new RpcException("", "领取优惠券失败", e);
		}
	}

	@Override
	public CouponClaim get(Integer couponId) throws RpcException {
		try {
			CouponClaim claim = new CouponClaim();
			claim.setId(couponId);
			claim = couponClaimService.findById(claim);
			return claim;
		} catch (ServiceException e) {
			logger.error("获取优惠券失败", e);
			throw new RpcException("", "获取优惠券失败", e);
		}
	}

	@Override
	public CouponClaim get(Integer couponId, String useFlag) throws RpcException {
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("id", couponId);
			if (StringUtils.isNotBlank(useFlag)) {
				params.put("useFlag", useFlag);
			}
			params.put("delFlag", DEL_FLAG_0);
			List<CouponClaim> list = couponClaimService.findByBiz(params);
			if (!CollectionUtils.isEmpty(list)) {
				return list.get(0);
			}
		} catch (ServiceException e) {
			logger.error("获取优惠券失败", e);
			throw new RpcException("", "获取优惠券失败", e);
		}
		return null;
	}

	@Override
	public BigDecimal calcCoupon(BigDecimal totalGoodsAmount, Integer couponId) throws RpcException {
		BigDecimal amount = BigDecimal.ZERO;
		try {
			if (null == couponId) return amount;
			
			CouponClaim claim = get(couponId, NO);
			if (null != claim && null != claim.getCouponAct()) {
				Date currDate = new Date();
				// 判断活动是否开始或已经结束
				if (!currDate.after(claim.getCouponAct().getStartTime())
						|| !currDate.before(claim.getCouponAct().getEndTime())) {
					return amount;
				}

				if (COUPON_TYPE_FULLSUB.equals(claim.getCouponAct().getActType())) { // 满减券
					// 判断订单是否满足满减条件
					if (totalGoodsAmount.compareTo(claim.getLimitAmount()) > 0) {
						amount = claim.getAmount();
					}
				} else if (COUPON_TYPE_CASH.equals(claim.getCouponAct().getActType())) { // 现金券
					amount = claim.getAmount();
				} else if (COUPON_TYPE_DISCOUNT.equals(claim.getCouponAct().getActType())) { // 折扣券
					// 优惠金额 = 商品总价 * (10 - 折扣率) / 10
					amount = totalGoodsAmount.multiply(BigDecimal.valueOf(10).subtract(claim.getAmount()))
							.divide(BigDecimal.valueOf(10));
				}

				if (amount.compareTo(BigDecimal.ZERO) < 0) {
					amount = BigDecimal.ZERO;
				}
			}
		} catch (RpcException e) {
			logger.error("获取优惠券失败", e);
			throw new RpcException("", "获取优惠券失败", e);
		}
		return amount;
	}

	@Override
	public List<CouponClaim> queryCouponList(Map<String, Object> params) throws RpcException {
		try {
			params.put("delFlag", DEL_FLAG_0);
			return couponClaimService.findByBiz(params);
		} catch (ServiceException e) {
			logger.error("获取优惠券列表失败", e);
			throw new RpcException("", "获取优惠券列表失败", e);
		}
	}
}
