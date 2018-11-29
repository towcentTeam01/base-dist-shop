package com.towcent.dist.shop.web.goods.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.goods.entity.GoodsSpec;

/**
 * 商品规格DAO接口
 * @author yxp
 * @version 2018-07-03
 */
@MyBatisDao
public interface GoodsSpecDao extends CrudDao<GoodsSpec> {
	
}