package com.towcent.dist.shop.web.share.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.share.entity.ShareInfo;

/**
 * 分享记录DAO接口
 * @author shiwei
 * @version 2018-08-25
 */
@MyBatisDao
public interface ShareInfoDao extends CrudDao<ShareInfo> {
	
}