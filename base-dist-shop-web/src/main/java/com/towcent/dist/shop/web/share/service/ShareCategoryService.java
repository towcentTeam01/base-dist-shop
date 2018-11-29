package com.towcent.dist.shop.web.share.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.share.entity.ShareCategory;
import com.towcent.dist.shop.web.share.dao.ShareCategoryDao;

/**
 * 商品分享分类Service
 * @author shiwei
 * @version 2018-08-25
 */
@Service
@Transactional(readOnly = true)
public class ShareCategoryService extends CrudService<ShareCategoryDao, ShareCategory> {

	public ShareCategory get(String id) {
		return super.get(id);
	}
	
	public List<ShareCategory> findList(ShareCategory shareCategory) {
		return super.findList(shareCategory);
	}
	
	public Page<ShareCategory> findPage(Page<ShareCategory> page, ShareCategory shareCategory) {
		return super.findPage(page, shareCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(ShareCategory shareCategory) {
		super.save(shareCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShareCategory shareCategory) {
		super.delete(shareCategory);
	}
	
}