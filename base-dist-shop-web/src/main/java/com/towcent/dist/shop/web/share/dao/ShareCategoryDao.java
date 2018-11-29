package com.towcent.dist.shop.web.share.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.share.entity.ShareCategory;

/**
 * 商品分享分类DAO接口
 * @author shiwei
 * @version 2018-08-25
 */
@MyBatisDao
public interface ShareCategoryDao extends CrudDao<ShareCategory> {
	
}