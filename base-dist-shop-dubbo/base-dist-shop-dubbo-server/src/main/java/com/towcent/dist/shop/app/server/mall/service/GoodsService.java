package com.towcent.dist.shop.app.server.mall.service;

import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.service.BaseCrudService;
import com.towcent.dist.shop.app.client.mall.dto.Goods;

/**
 * goods 数据库操作Service接口
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public interface GoodsService extends BaseCrudService {
	
	/**
	 * 通过商品编码获取商品信息.
	 * @Title getGoodsByNo
	 * @param merchantId   商户Id
	 * @param goodsNo      商品编码
	 * @return
	 * @throws ServiceException
	 */
	Goods getGoodsByNo(Integer merchantId, String goodsNo) throws ServiceException;
	
	/**
	 * 更新商品图片资料.
	 * @Title modifyGoodsPicByNo
	 * @param merchantId   商户Id
	 * @param goodsNo      商品编码
	 * @param mainUrls     商品主图(;分割)
	 * @param descPic      商品描述图(;分割)
	 * @return
	 * @throws ServiceException
	 */
	boolean modifyGoodsPicByNo(Integer merchantId, String goodsNo, String mainUrls, String descPic) throws ServiceException;
}