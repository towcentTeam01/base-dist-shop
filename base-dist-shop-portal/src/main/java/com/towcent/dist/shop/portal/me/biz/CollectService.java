package com.towcent.dist.shop.portal.me.biz;

import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsDelIn;
import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsListIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * CollectService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface CollectService {

    ResultVo goodsList(CollectGoodsListIn paramIn);

    ResultVo goodsDel(CollectGoodsDelIn paramIn);
}