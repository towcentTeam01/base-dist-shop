package com.towcent.dist.shop.portal.mall.biz;

import com.towcent.dist.shop.portal.mall.vo.input.GoodsCollectIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsEvalListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsDetailsIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsCategoryListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsChannelListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsSreachListIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * GoodsService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface GoodsService {

    ResultVo sreachList(GoodsSreachListIn paramIn);

    ResultVo channelList(GoodsChannelListIn paramIn);

    ResultVo categoryList(GoodsCategoryListIn paramIn);

    ResultVo list(GoodsListIn paramIn);

    ResultVo details(GoodsDetailsIn paramIn);

    ResultVo evalList(GoodsEvalListIn paramIn);

    ResultVo collect(GoodsCollectIn paramIn);
}