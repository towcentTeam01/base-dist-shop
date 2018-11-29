package com.towcent.dist.shop.portal.mall.biz;

import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartConfirmIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartGoodsQtyIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartListIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartEditIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartDelIn;
import com.towcent.dist.shop.portal.mall.vo.input.ShoppingCartAddIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * ShoppingCartService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface ShoppingCartService {

    ResultVo add(ShoppingCartAddIn paramIn);

    ResultVo del(ShoppingCartDelIn paramIn);

    ResultVo edit(ShoppingCartEditIn paramIn);

    ResultVo list(ShoppingCartListIn paramIn);

    ResultVo goodsQty(ShoppingCartGoodsQtyIn paramIn);

    ResultVo confirm(ShoppingCartConfirmIn paramIn);
}