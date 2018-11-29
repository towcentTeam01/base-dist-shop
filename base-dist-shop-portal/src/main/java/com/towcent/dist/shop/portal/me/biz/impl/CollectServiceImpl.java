package com.towcent.dist.shop.portal.me.biz.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.app.client.me.dto.ConcernGoods;
import com.towcent.dist.shop.app.client.me.service.MeApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.common.ConfigUtil;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.me.biz.CollectService;
import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsDelIn;
import com.towcent.dist.shop.portal.me.vo.input.CollectGoodsListIn;
import com.towcent.dist.shop.portal.me.vo.output.CollectGoodsListOut;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.E_001;

/**
 * CollectServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class CollectServiceImpl extends BasePortalService implements CollectService {

    @Resource
    private MeApi meApi;

    @Override
    public ResultVo goodsList(CollectGoodsListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SimplePageDto page = this.buildPage(paramIn);

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());

            PaginationDto<ConcernGoods> PageDto = meApi.queryCollectGoodsPage(params, page);

            List<CollectGoodsListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                CollectGoodsListOut out = null;
                for (ConcernGoods entity : PageDto.getList()) {
                    if (null != entity.getGoods()) {
                        out = new CollectGoodsListOut();
                        out.setGoodsId(entity.getGoodsId());
                        out.setGoodsName(entity.getGoods().getGoodsName());
                        out.setGoodsType(entity.getGoods().getGoodsType());
                        out.setMaxPrice(entity.getGoods().getMaxPrice());
                        out.setIntegral(entity.getGoods().getIntegral());
                        out.setPrice(entity.getGoods().getPrice());
                        out.setMinPrice(entity.getGoods().getMinPrice());
                        if (StringUtils.isNotBlank(entity.getGoods().getMainUrls())) {
                            out.setPicUrl(
                                    GoodsUtils.getGoodsListPicUrl(
                                            ConfigUtil.getUrlHeader(),
                                            entity.getGoods().getMainUrls(),
                                            entity.getGoods().getDescPicV()));
                        }
                        out.setId(entity.getId());
                        list.add(out);
                    }
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<CollectGoodsListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo goodsDel(CollectGoodsDelIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            meApi.delConcernGoods(paramIn.getCollectId());

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }
}
