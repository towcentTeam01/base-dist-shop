package com.towcent.dist.shop.web.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.sys.entity.SysMerchantInfo;
import com.towcent.dist.shop.web.sys.dao.SysMerchantInfoDao;

/**
 * 商户信息Service
 * @author yxp
 * @version 2018-07-19
 */
@Service
@Transactional(readOnly = true)
public class SysMerchantInfoService extends CrudService<SysMerchantInfoDao, SysMerchantInfo> {

	public SysMerchantInfo get(String id) {
		return super.get(id);
	}
	
	public List<SysMerchantInfo> findList(SysMerchantInfo sysMerchantInfo) {
		return super.findList(sysMerchantInfo);
	}
	
	public Page<SysMerchantInfo> findPage(Page<SysMerchantInfo> page, SysMerchantInfo sysMerchantInfo) {
		return super.findPage(page, sysMerchantInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(SysMerchantInfo sysMerchantInfo) {
		super.save(sysMerchantInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysMerchantInfo sysMerchantInfo) {
		super.delete(sysMerchantInfo);
	}
	
}