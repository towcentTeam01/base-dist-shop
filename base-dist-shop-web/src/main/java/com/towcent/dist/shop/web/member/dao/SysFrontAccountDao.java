package com.towcent.dist.shop.web.member.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.member.entity.SysFrontAccount;

/**
 * 会员DAO接口
 * @author huangtao
 * @version 2018-07-07
 */
@MyBatisDao
public interface SysFrontAccountDao extends CrudDao<SysFrontAccount> {
	
}