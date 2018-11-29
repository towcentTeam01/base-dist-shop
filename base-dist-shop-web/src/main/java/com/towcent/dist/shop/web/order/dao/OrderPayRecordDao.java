package com.towcent.dist.shop.web.order.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.order.entity.OrderPayRecord;

/**
 * 交易记录DAO接口
 * @author HuangTao
 * @version 2018-07-08
 */
@MyBatisDao
public interface OrderPayRecordDao extends CrudDao<OrderPayRecord> {
	
}