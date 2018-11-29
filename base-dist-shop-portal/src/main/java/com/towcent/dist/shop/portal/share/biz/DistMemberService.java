package com.towcent.dist.shop.portal.share.biz;

import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerCountIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerListIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInfoIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberUpgradeIn;

/**
 * DistMemberService
 * @author huangtao
 * @version 0.0.1
 */
public interface DistMemberService {

	ResultVo info(DistMemberInfoIn paramIn);

	ResultVo upgrade(DistMemberUpgradeIn paramIn);

	ResultVo customerCount(DistMemberCustomerCountIn paramIn);

	ResultVo customerList(DistMemberCustomerListIn paramIn);
}