package com.towcent.dist.shop.app.server.mall.service.impl;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.server.mall.dao.GoodsMapper;
import com.towcent.dist.shop.app.server.mall.service.GoodsService;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("goodsServiceImpl")
public class GoodsServiceImpl extends BaseCrudServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public CrudMapper init() {
        return goodsMapper;
    }

    @Override
    public Goods getGoodsByNo(Integer merchantId, String goodsNo) throws ServiceException {
    	Map<String, Object> params = Maps.newHashMap();
    	params.put("merchantId", merchantId);
    	params.put("goodsNo", goodsNo);
    	params.put("delFlag", DEL_FLAG_0);
    	List<Goods> list = this.findByBiz(params);
    	return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }
    
    @Override
    public boolean modifyGoodsPicByNo(Integer merchantId, String goodsNo, String mainUrls, String descPic)
    		throws ServiceException {
    	try {
			Goods goods = getGoodsByNo(merchantId, goodsNo);
			Goods modifyModel = new Goods();
			modifyModel.setId(goods.getId());
			modifyModel.setMainUrls(mainUrls);
			modifyModel.setDescPic(descPic);
			// modifyModel.setGoodsStatus("1"); // 未发布
			// modifyModel.setQrCode(commonApi.generateGoodsQrCode(goods.getId()));
			return goodsMapper.updateByPrimaryKeySelective(modifyModel) > 0;
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
    }
}