package com.towcent.dist.shop.web.config.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.config.entity.PayAccount;

/**
 * 支付配置DAO接口
 * @author huangtao
 * @version 2018-07-02
 */
@MyBatisDao
public interface PayAccountDao extends CrudDao<PayAccount> {
	
}