package com.towcent.dist.shop.web.sys.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.sys.entity.SysUserMerchantRel;

/**
 * 系统用户商户关系DAO接口
 * @author yxp
 * @version 2018-07-19
 */
@MyBatisDao
public interface SysUserMerchantRelDao extends CrudDao<SysUserMerchantRel> {
	
}