package com.towcent.dist.shop.portal.mall.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;
import static com.towcent.base.common.constants.BaseConstant.YES;
import static com.towcent.dist.shop.common.Constant.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.client.mall.dto.GoodsEva;
import com.towcent.dist.shop.app.client.mall.sevice.GoodsApi;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.app.client.mall.vo.GoodsCategoryVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsChannelVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.mall.biz.GoodsService;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsCategoryListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsChannelListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsCollectIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsDetailsIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsEvalListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsListIn;
import com.towcent.dist.shop.portal.mall.vo.input.GoodsSreachListIn;
import com.towcent.dist.shop.portal.mall.vo.output.GoodsCategoryListOut;
import com.towcent.dist.shop.portal.mall.vo.output.GoodsChannelListOut;
import com.towcent.dist.shop.portal.mall.vo.output.GoodsDetailsOut;
import com.towcent.dist.shop.portal.mall.vo.output.GoodsEvalListOut;
import com.towcent.dist.shop.portal.mall.vo.output.GoodsListOut;
import com.towcent.dist.shop.portal.mall.vo.output.GoodsSreachListOut;

/**
 * GoodsServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class GoodsServiceImpl extends BasePortalService implements GoodsService {

    @Resource
    private GoodsApi goodsApi;

    @Override
    public ResultVo sreachList(GoodsSreachListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            // SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            SimplePageDto page = this.buildPage(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", paramIn.getMerchantId());
            params.put("searchStr", paramIn.getSearchStr());

            if (StringUtils.isBlank(paramIn.getOrderBy())) paramIn.setOrderBy("0");
            params.put("orderBy", "0".equals(paramIn.getOrderBy()) ? "DESC" : "ASC");

            if (StringUtils.isBlank(paramIn.getOrderByField())) paramIn.setOrderByField("0");
            String orderByField = "a.create_date";
            if (StringUtils.equals("1", paramIn.getOrderByField())) {
                orderByField = "a.sales";
            } else if (StringUtils.equals("2", paramIn.getOrderByField())) {
                orderByField = "a.price";
            }
            params.put("orderByField", orderByField);

            PaginationDto<Goods> PageDto = goodsApi.listForPage(params, page);

            List<GoodsSreachListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                GoodsSreachListOut out = null;
                for (Goods entity : PageDto.getList()) {
                    out = new GoodsSreachListOut();
                    BeanUtils.copyProperties(entity, out);
                    out.setPicUrl(GoodsUtils.getGoodsMidListPicUrl(entity.getMainUrls(), entity.getDescPicV()));

                    out.setEvalNum(entity.getEvaNum());

                    if (null != entity.getEvaNum() && entity.getEvaNum() > 0) {
                        BigDecimal goodsEvalRate = entity.getGoodEvalRate().multiply(BigDecimal.valueOf(INTEGER_NUM_100))
                                .divide(BigDecimal.valueOf(entity.getEvaNum().doubleValue()), 2)
                                .setScale(0, BigDecimal.ROUND_DOWN);
                        out.setGoodEvalRate(goodsEvalRate.toString() + "%");
                    } else {
                        out.setGoodEvalRate(INTEGER_NUM_100 + "%");
                    }

                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<GoodsSreachListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo channelList(GoodsChannelListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            // SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", paramIn.getMerchantId());
            params.put("channelStatus", YES);

            List<GoodsChannelVo> list = goodsApi.queryChannelList(params);

            List<GoodsChannelListOut> outList = Lists.newArrayList();
            if (null != list && !CollectionUtils.isEmpty(list)) {
                GoodsChannelListOut out = null;
                for (GoodsChannelVo entity : list) {
                    out = new GoodsChannelListOut();
                    out.setId(entity.getId());
                    out.setName(entity.getChannelName());
                    out.setList(entity.getList());
                    outList.add(out);
                }
            }

            resultVo.setData(outList);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo categoryList(GoodsCategoryListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            // SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            Integer parentId = null != paramIn.getParentId() ? paramIn.getParentId() : 0;
            Integer level = null != paramIn.getLevel() ? paramIn.getLevel() : 1;

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", paramIn.getMerchantId());
            params.put("parentId", parentId);
            params.put("level", level);

            List<GoodsCategoryVo> list = goodsApi.queryCategoryList(params);

            List<GoodsCategoryListOut> outList = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(list)) {
                converCategoryListOut(list, outList);
            }

            resultVo.setData(outList);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }

        return resultVo;
    }

    private void converCategoryListOut(List<GoodsCategoryVo> list, List<GoodsCategoryListOut> outList) {
        if (!CollectionUtils.isEmpty(list)) {
            GoodsCategoryListOut out = null;
            for (GoodsCategoryVo entity : list) {
                out = new GoodsCategoryListOut();
                out.setId(entity.getId());
                out.setIcon(entity.getCategoryIcon());
                out.setStructureNo(entity.getStructureNo());
                out.setName(entity.getCategoryName());
                if (!CollectionUtils.isEmpty(entity.getList())) {
                    List<GoodsCategoryListOut> subList = Lists.newArrayList();
                    out.setList(subList);
                    converCategoryListOut(entity.getList(), subList);
                }
                outList.add(out);
            }
        }
    }

    @Override
    public ResultVo list(GoodsListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            // SysFrontAccount account = UserUtils.getUserAccount(paramIn);
            SimplePageDto page = this.buildPage(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", paramIn.getMerchantId());
            params.put("categoryId", paramIn.getCategoryId());
            params.put("structureNo", paramIn.getStructureNo());

            if (StringUtils.isBlank(paramIn.getOrderBy())) paramIn.setOrderBy("0");
            params.put("orderBy", "0".equals(paramIn.getOrderBy()) ? "DESC" : "ASC");

            if (StringUtils.isBlank(paramIn.getOrderByField())) paramIn.setOrderByField("0");
            String orderByField = "a.create_date";
            if (StringUtils.equals("1", paramIn.getOrderByField())) {
                orderByField = "a.sales";
            } else if (StringUtils.equals("2", paramIn.getOrderByField())) {
                orderByField = "a.price";
            }
            params.put("orderByField", orderByField);

            PaginationDto<Goods> PageDto = goodsApi.listForPage(params, page);

            List<GoodsListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                GoodsListOut out = null;
                for (Goods entity : PageDto.getList()) {
                    out = new GoodsListOut();
                    BeanUtils.copyProperties(entity, out);
                    out.setPicUrl(GoodsUtils.getGoodsMidListPicUrl(entity.getMainUrls(), entity.getDescPicV()));

                    out.setEvalNum(entity.getEvaNum());

                    if (null != entity.getEvaNum() && entity.getEvaNum() > 0) {
                        BigDecimal goodsEvalRate = entity.getGoodEvalRate().multiply(BigDecimal.valueOf(INTEGER_NUM_100))
                                .divide(BigDecimal.valueOf(entity.getEvaNum().doubleValue()), 2)
                                .setScale(0, BigDecimal.ROUND_DOWN);
                        out.setGoodEvalRate(goodsEvalRate.toString() + "%");
                    } else {
                        out.setGoodEvalRate(INTEGER_NUM_100 + "%");
                    }

                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<GoodsListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo details(GoodsDetailsIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {


            GoodsDetailsOut outParam = new GoodsDetailsOut();

            GoodsVo goods = goodsApi.queryGoodsById(paramIn.getGoodsId());
            BeanUtils.copyProperties(goods, outParam);

            outParam.setIsConcern(INTEGER_NUM_0);

            if (StringUtils.isNotBlank(paramIn.getTokenId())) {
                SysFrontAccount account = UserUtils.getUserAccount(paramIn);
                if (null != account) {
                    boolean flag = goodsApi.checkGoodsCollectStatus(goods.getId(), account);
                    if (flag) {
                        outParam.setIsConcern(INTEGER_NUM_1);
                    }
                }
            }

            outParam.setEvalNum(goods.getEvaNum());

            if (null != goods.getEvaNum() && goods.getEvaNum() > 0) {
                BigDecimal goodsEvalRate = goods.getGoodEvalRate().multiply(BigDecimal.valueOf(INTEGER_NUM_100))
                        .divide(BigDecimal.valueOf(goods.getEvaNum().doubleValue()), 2)
                        .setScale(0, BigDecimal.ROUND_DOWN);
                outParam.setGoodEvalRate(goodsEvalRate.toString() + "%");
            } else {
                outParam.setGoodEvalRate(INTEGER_NUM_100 + "%");
            }

            resultVo.setData(outParam);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo evalList(GoodsEvalListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SimplePageDto page = this.buildPage(paramIn);

            // SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", paramIn.getMerchantId());
            params.put("goodsId", paramIn.getGoodsId());

            PaginationDto<GoodsEva> PageDto = goodsApi.queryEvalPage(params, page);
            List<GoodsEvalListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                GoodsEvalListOut out = null;
                for (GoodsEva entity : PageDto.getList()) {
                    out = new GoodsEvalListOut();
                    BeanUtils.copyProperties(entity, out);
                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<GoodsEvalListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo collect(GoodsCollectIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {

            if (null == paramIn.getFlag()) {
                paramIn.setFlag(INTEGER_NUM_0);
            }

            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            goodsApi.goodsCollect(paramIn.getGoodsId(), account, paramIn.getFlag());

        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, e.getMessage());
            logger.error("", e);
        }
        return resultVo;
    }
}
