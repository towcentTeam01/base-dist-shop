package com.towcent.dist.shop.web.config.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.config.entity.SysLevelConf;

/**
 * 等级配置DAO接口
 * @author huangtao
 * @version 2018-07-02
 */
@MyBatisDao
public interface SysLevelConfDao extends CrudDao<SysLevelConf> {
	
}