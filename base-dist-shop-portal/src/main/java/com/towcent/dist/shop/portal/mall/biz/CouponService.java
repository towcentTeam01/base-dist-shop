package com.towcent.dist.shop.portal.mall.biz;

import com.towcent.dist.shop.portal.mall.vo.input.CouponDrawIn;
import com.towcent.dist.shop.portal.mall.vo.input.CouponListIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * CouponService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface CouponService {

    ResultVo list(CouponListIn paramIn);

    ResultVo draw(CouponDrawIn paramIn);
}