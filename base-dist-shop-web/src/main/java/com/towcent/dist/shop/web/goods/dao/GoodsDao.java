package com.towcent.dist.shop.web.goods.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.goods.entity.Goods;

/**
 * 商品DAO接口
 * @author yxp
 * @version 2018-07-02
 */
@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {
	
}