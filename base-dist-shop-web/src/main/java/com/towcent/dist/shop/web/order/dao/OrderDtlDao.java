package com.towcent.dist.shop.web.order.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.order.entity.OrderDtl;

/**
 * 订单明细DAO接口
 * @author huangtao
 * @version 2018-07-02
 */
@MyBatisDao
public interface OrderDtlDao extends CrudDao<OrderDtl> {
	
}