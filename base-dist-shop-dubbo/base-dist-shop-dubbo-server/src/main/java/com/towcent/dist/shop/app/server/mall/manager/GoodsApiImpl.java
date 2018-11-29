package com.towcent.dist.shop.app.server.mall.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.service.BaseService;
import com.towcent.dist.shop.app.client.mall.dto.*;
import com.towcent.dist.shop.app.client.mall.sevice.GoodsApi;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.app.client.mall.vo.GoodsCategoryVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsChannelVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSpecVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import com.towcent.dist.shop.app.client.me.dto.ConcernGoods;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.server.mall.service.*;
import com.towcent.dist.shop.app.server.me.service.ConcernGoodsService;
import com.towcent.dist.shop.common.ConfigUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_1;
import static com.towcent.dist.shop.common.Constant.GOODS_TYPE_1;
import static com.towcent.dist.shop.common.Constant.INTEGER_NUM_0;
import static com.towcent.dist.shop.common.Constant.INTEGER_NUM_1;

@Service
public class GoodsApiImpl extends BaseService implements GoodsApi {

    @Resource
    private GoodsService goodsService;
    @Resource
    private GoodsChannelService goodsChannelService;
    @Resource
    private GoodsChannelDtlService goodsChannelDtlService;
    @Resource
    private GoodsCategoryService goodsCategoryService;
    @Resource
    private GoodsEvaService goodsEvaService;
    @Resource
    private ConcernGoodsService concernGoodsService;
    @Resource
    private GoodsSpecService goodsSpecService;
    @Resource
    private GoodsSkuService goodsSkuService;

    @Override
    public PaginationDto<Goods> listForPage(Map<String, Object> params, SimplePageDto pageDto) throws RpcException {
        try {
            String orderBy = "DESC";
            String orderByField = "a.create_date";
            if (null != params.get("orderByField")) {
                orderByField = String.valueOf(params.get("orderByField"));
            }
            if (null != params.get("orderBy")) {
                orderBy = String.valueOf(params.get("orderBy"));
            }
            params.put("delFlag", DEL_FLAG_0);
            int totalCount = goodsService.findCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<Goods> list = goodsService.findByPage(page, orderByField, orderBy, params);
            return new PaginationDto<Goods>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询商品分页信息失败", e);
            throw new RpcException("", "查询商品分页信息失败", e);
        }
    }

    @Override
    public List<GoodsChannelVo> queryChannelList(Map<String, Object> params) throws RpcException {
        List<GoodsChannelVo> outList = Lists.newArrayList();
        try {
            params.put("delFlag", DEL_FLAG_0);
            List<GoodsChannel> list = goodsChannelService.findByBiz(params);
            if (!CollectionUtils.isEmpty(list)) {
                GoodsChannelVo vo = null;
                for (GoodsChannel entity : list) {
                    vo = new GoodsChannelVo();
                    BeanUtils.copyProperties(entity, vo);

                    List<GoodsVo> goodsList = Lists.newArrayList();

                    Map<String, Object> map = Maps.newHashMap();
                    map.put("channelId", entity.getId());
                    map.put("merchantId", entity.getMerchantId());
                    map.put("delFlag", DEL_FLAG_0);

                    List<GoodsChannelDtl> dtlList = goodsChannelDtlService.findByBiz(map);
                    if (!CollectionUtils.isEmpty(dtlList)) {
                        GoodsVo goodsVo = null;
                        for (GoodsChannelDtl dtl : dtlList) {
                            goodsVo = this.queryGoodsById(dtl.getGoodsId(), false);
                            goodsList.add(goodsVo);
                        }
                    }
                    vo.setList(goodsList);
                    outList.add(vo);
                }
            }
        } catch (ServiceException e) {
            logger.error("查询商品频道信息失败", e);
            throw new RpcException("", "查询商品频道信息失败", e);
        }
        return outList;
    }

    @Override
    public List<GoodsCategoryVo> queryCategoryList(Map<String, Object> params) throws RpcException {
        Integer parentId = Integer.parseInt(String.valueOf(params.get("parentId")));
        Integer level = Integer.parseInt(String.valueOf(params.get("level")));
        Assert.notNull(parentId, "parentId不能为空");
        Assert.notNull(level, "level不能为空");

        List<GoodsCategoryVo> outList = Lists.newArrayList();
        try {
            params.remove("level");
            params.put("delFlag", DEL_FLAG_0);
            List<GoodsCategory> list = goodsCategoryService.findByBiz(params);
            if (!CollectionUtils.isEmpty(list)) {
                converCategoryVoList(list, outList, level);
            }
            return outList;
        } catch (ServiceException e) {
            logger.error("查询商品分类列表失败", e);
            throw new RpcException("", "查询商品分类列表失败", e);
        }
    }

    /**
     * 递归组装分类列表.
     *
     * @param list
     * @param outList
     * @param level
     * @throws ServiceException
     * @Title converCategoryVoList
     */
    private void converCategoryVoList(List<GoodsCategory> list, List<GoodsCategoryVo> outList, Integer level) throws ServiceException {
        if (!CollectionUtils.isEmpty(list)) {
            GoodsCategoryVo vo = null;
            for (GoodsCategory entity : list) {
                vo = new GoodsCategoryVo();
                BeanUtils.copyProperties(entity, vo);
                if (level > entity.getLevel()) {
                    Map<String, Object> params = Maps.newHashMap();
                    params.put("parentId", entity.getId());
                    params.put("delFlag", DEL_FLAG_0);
                    List<GoodsCategory> sublist = goodsCategoryService.findByBiz(params);
                    converCategoryVoList(sublist, vo.getList(), level);
                }
                outList.add(vo);
            }
        }
    }

    @Override
    public GoodsVo queryGoodsById(Integer goodsId) throws RpcException {
        return queryGoodsById(goodsId, true);
    }

    @Override
    public GoodsVo queryGoodsById(Integer goodsId, boolean containSpec) throws RpcException {
        Assert.notNull(goodsId, "goodsId不能为空");
        try {
            Goods goods = new Goods();
            goods.setId(goodsId);
            goods = goodsService.findById(goods);

            Assert.notNull(goods, "商品不存在");

            GoodsVo goodsVo = new GoodsVo();
            BeanUtils.copyProperties(goods, goodsVo);

            if (null != goods.getDescPic()) {
                goodsVo.setDescPicList(GoodsUtils.getGoodsDescPicUrlList(goods.getDescPic(), goods.getDescPicV()));
            }

            if (null != goods.getMainUrls()) {
                goodsVo.setPicUrlList(GoodsUtils.getGoodsPigPicUrlList(goods.getMainUrls(), goods.getDescPicV()));

                goodsVo.setPicUrl(GoodsUtils.getGoodsListPicUrl(ConfigUtil.getUrlHeader(), goods.getMainUrls(),
                        goods.getDescPicV()));
            }

            if (containSpec) {
                goodsVo.setSpecList(getGoodsSpecList(goodsId, GOODS_TYPE_1.equals(goodsVo.getGoodsType())));
            }

            return goodsVo;
        } catch (ServiceException e) {
            logger.error("查询商品信息失败", e);
            throw new RpcException("", "查询商品信息失败", e);
        }
    }

    @Override
    public List<GoodsSpecVo> getGoodsSpecList(Integer goodsId, boolean showGoodsSku) throws RpcException {
        try {
            List<GoodsSpecVo> list = Lists.newArrayList();
            Map<String, Object> map = Maps.newHashMap();
            map.put("goodsId", goodsId);
            map.put("delFlag", DEL_FLAG_0);
            List<GoodsSpec> specList = goodsSpecService.findByBiz(map);
            if (null != specList && !CollectionUtils.isEmpty(specList)) {
                GoodsSpecVo vo = null;
                for (GoodsSpec spec : specList) {
                    vo = new GoodsSpecVo();
                    BeanUtils.copyProperties(spec, vo);
                    if (showGoodsSku) {
                        vo.setSkuList(getGoodsSkuList(spec.getId()));
                    }
                    list.add(vo);
                }
            }
            return list;
        } catch (ServiceException e) {
            logger.error("查询商品规格列表信息失败", e);
            throw new RpcException("", "查询商品规格列表信息失败", e);
        }
    }

    @Override
    public List<GoodsSku> getGoodsSkuList(Integer goodsSpecId) throws RpcException {
        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("goodsSpecId", goodsSpecId);
            map.put("delFlag", DEL_FLAG_0);
            return goodsSkuService.findByBiz(map);
        } catch (ServiceException e) {
            logger.error("查询商品Sku列表信息失败", e);
            throw new RpcException("", "查询商品Sku列表信息失败", e);
        }
    }

    @Override
    public PaginationDto<GoodsEva> queryEvalPage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException {
        try {
            params.put("delFlag", DEL_FLAG_0);
            int totalCount = goodsEvaService.findCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<GoodsEva> list = goodsEvaService.findByPage(page, "a.create_date", "DESC", params);
            return new PaginationDto<GoodsEva>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询商品评价分页信息失败", e);
            throw new RpcException("", "查询商品评价分页信息失败", e);
        }
    }

    @Override
    public boolean goodsCollect(Integer goodsId, SysFrontAccount account, Integer flag) throws RpcException {
        Assert.notNull(goodsId, "goodsId不能为空");
        try {
            Goods goods = new Goods();
            goods.setId(goodsId);
            goods.setMerchantId(account.getMerchantId());
            goods = goodsService.findById(goods);

            Assert.notNull(goods, "商品不存在");


            // 查询判断商品是否已经收藏
            Map<String, Object> params = Maps.newHashMap();
            params.put("goodsId", goodsId);
            params.put("merchantId", account.getMerchantId());
            params.put("createBy", account.getId());
            params.put("delFlag", DEL_FLAG_0);
            List<ConcernGoods> list = concernGoodsService.findByBiz(params);
            // 判断操作状态和商品状态是否匹配
            if ((CollectionUtils.isEmpty(list) && flag == INTEGER_NUM_1) || (!CollectionUtils.isEmpty(list) && flag == INTEGER_NUM_0)) {
                return true;
            }

            if (!CollectionUtils.isEmpty(list)) {
                // 取消收藏
                ConcernGoods concernGoods = list.get(0);
                concernGoods.setUpdateBy(account.getId());
                concernGoods.setUpdateDate(new Date());
                concernGoods.setDelFlag(DEL_FLAG_1);
                return concernGoodsService.modifyById(concernGoods) > 0;
            } else {
                // 收藏商品
                ConcernGoods concernGoods = new ConcernGoods();
                concernGoods.setGoodsId(goodsId);
                concernGoods.setMerchantId(goods.getMerchantId());
                concernGoods.setCreateBy(account.getId());
                concernGoods.setUpdateBy(account.getId());
                concernGoods.setCreateDate(new Date());
                concernGoods.setUpdateDate(new Date());
                return concernGoodsService.add(concernGoods) > 0;
            }
        } catch (ServiceException e) {
            logger.error("收藏商品失败", e);
            throw new RpcException("", e.getMessage(), e);
        }
    }

    @Override
    public boolean checkGoodsCollectStatus(Integer goodsId, SysFrontAccount account) throws RpcException {
        Assert.notNull(goodsId, "goodsId不能为空");
        try {


            Map<String, Object> params = Maps.newHashMap();
            params.put("goodsId", goodsId);
            params.put("merchantId", account.getMerchantId());
            params.put("createBy", account.getId());
            params.put("delFlag", DEL_FLAG_0);
            int count = concernGoodsService.findCount(params);

            return count > 0 ? true : false;
        } catch (ServiceException e) {
            logger.error("查询收藏商品失败", e);
            throw new RpcException("", e.getMessage(), e);
        }
    }
}
