package com.towcent.dist.shop.portal.share.biz;

import com.towcent.dist.shop.portal.share.vo.input.DistHelpFriendIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpShopIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterTemplateIn;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpCustomerServiceIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpGuideIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpLevelDescIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInviteIn;
import com.towcent.dist.shop.portal.share.vo.input.DistProductIn;

/**
 * DistHelpService
 * @author huangtao
 * @version 0.0.1
 */
public interface DistHelpService {

	ResultVo customerService(DistHelpCustomerServiceIn paramIn);

	ResultVo poster(DistHelpPosterIn paramIn);

	ResultVo guide(DistHelpGuideIn paramIn);

	ResultVo levelDesc(DistHelpLevelDescIn paramIn);
	
	ResultVo invite(DistMemberInviteIn paramIn);
	
    ResultVo product(DistProductIn paramIn);

	ResultVo posterTemplate(DistHelpPosterTemplateIn paramIn);

	ResultVo shop(DistHelpShopIn paramIn);

	ResultVo friend(DistHelpFriendIn paramIn);
}