package com.towcent.dist.shop.portal.me.biz;

import com.towcent.dist.shop.portal.common.vo.BaseParam;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrDelIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrSaveIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrListIn;
import com.towcent.base.common.vo.ResultVo;

/**
 * ConsigneeAddrService
 *
 * @author huangtao
 * @version 0.0.1
 */
public interface ConsigneeAddrService {

    ResultVo list(ConsigneeAddrListIn paramIn);

    ResultVo save(ConsigneeAddrSaveIn paramIn);

    ResultVo del(ConsigneeAddrDelIn paramIn);

    ResultVo getDefault(BaseParam paramIn);
}
