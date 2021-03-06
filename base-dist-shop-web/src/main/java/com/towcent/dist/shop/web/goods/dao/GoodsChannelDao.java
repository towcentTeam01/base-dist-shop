package com.towcent.dist.shop.web.goods.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.goods.entity.GoodsChannel;

/**
 * 商品频道DAO接口
 * @author yxp
 * @version 2018-07-09
 */
@MyBatisDao
public interface GoodsChannelDao extends CrudDao<GoodsChannel> {
	
}