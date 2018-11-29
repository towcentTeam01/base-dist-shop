package com.towcent.dist.shop.portal.sys.biz;

import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.portal.sys.vo.input.AreaDescIn;
import com.towcent.dist.shop.portal.sys.vo.input.AreaListIn;

/**
 * AreaService
 * @author huangtao
 * @version 0.0.1
 */
public interface AreaService {

	ResultVo list(AreaListIn paramIn);

	ResultVo desc(AreaDescIn paramIn);
}