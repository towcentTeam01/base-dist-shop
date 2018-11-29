package com.towcent.dist.shop.web.share.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.share.entity.ShareInfo;
import com.towcent.dist.shop.web.share.dao.ShareInfoDao;

/**
 * 分享记录Service
 * @author shiwei
 * @version 2018-08-25
 */
@Service
@Transactional(readOnly = true)
public class ShareInfoService extends CrudService<ShareInfoDao, ShareInfo> {

	public ShareInfo get(String id) {
		return super.get(id);
	}
	
	public List<ShareInfo> findList(ShareInfo shareInfo) {
		return super.findList(shareInfo);
	}
	
	public Page<ShareInfo> findPage(Page<ShareInfo> page, ShareInfo shareInfo) {
		return super.findPage(page, shareInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(ShareInfo shareInfo) {
		super.save(shareInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(ShareInfo shareInfo) {
		super.delete(shareInfo);
	}
	
}