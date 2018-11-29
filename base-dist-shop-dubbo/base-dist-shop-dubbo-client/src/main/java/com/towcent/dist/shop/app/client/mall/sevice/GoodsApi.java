package com.towcent.dist.shop.app.client.mall.sevice;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.client.mall.dto.GoodsEva;
import com.towcent.dist.shop.app.client.mall.dto.GoodsSku;
import com.towcent.dist.shop.app.client.mall.vo.GoodsCategoryVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsChannelVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsSpecVo;
import com.towcent.dist.shop.app.client.mall.vo.GoodsVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

import java.util.List;
import java.util.Map;

/**
 * goods 接口
 *
 * @author yxp
 */
public interface GoodsApi {

    /**
     * 查询商品分页列表信息
     *
     * @param params
     * @param pageDto
     * @return
     * @throws RpcException
     */
    PaginationDto<Goods> listForPage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException;

    /**
     * 查询商品频道列表信息
     *
     * @param params
     * @return
     * @throws RpcException
     */
    List<GoodsChannelVo> queryChannelList(Map<String, Object> params) throws RpcException;

    /**
     * 查询商品分类列表信息
     *
     * @param params
     * @return
     * @throws RpcException
     */
    List<GoodsCategoryVo> queryCategoryList(Map<String, Object> params) throws RpcException;

    /**
     * 查询商品详细信息<br>
     * <b>默认查询规格列表</b>
     *
     * @param goodsId
     * @return
     * @throws RpcException
     */
    GoodsVo queryGoodsById(Integer goodsId) throws RpcException;

    /**
     * 查询商品详细信息
     *
     * @param goodsId
     * @param containSpec 是否查询规格
     * @return
     * @throws RpcException
     */
    GoodsVo queryGoodsById(Integer goodsId, boolean containSpec) throws RpcException;

    /**
     * 查询商品规格属性
     *
     * @param goodsId
     * @return
     * @throws RpcException
     */
    List<GoodsSpecVo> getGoodsSpecList(Integer goodsId, boolean showGoodsSku) throws RpcException;

    /**
     * 查询商品sku属性
     *
     * @param goodsSpecId
     * @return
     * @throws RpcException
     */
    List<GoodsSku> getGoodsSkuList(Integer goodsSpecId) throws RpcException;

    /**
     * 查询商品评价分页列表信息
     *
     * @param params
     * @param pageDto
     * @return
     * @throws RpcException
     */
    PaginationDto<GoodsEva> queryEvalPage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException;

    /**
     * 商品收藏
     *
     * @param goodsId
     * @param account
     * @return
     * @throws RpcException
     */
    boolean goodsCollect(Integer goodsId, SysFrontAccount account, Integer flag) throws RpcException;

    /**
     * 商品收藏状态
     *
     * @param goodsId
     * @param account
     * @return flag 收藏标识 1：取消 0：收藏
     * @throws RpcException
     */
    boolean checkGoodsCollectStatus(Integer goodsId, SysFrontAccount account) throws RpcException;
}
