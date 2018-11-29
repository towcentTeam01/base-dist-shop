package com.towcent.dist.shop.web.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.config.entity.SysWxConfig;
import com.towcent.dist.shop.web.config.dao.SysWxConfigDao;

/**
 * 公众号配置Service
 * @author huangtao
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class SysWxConfigService extends CrudService<SysWxConfigDao, SysWxConfig> {

	public SysWxConfig get(String id) {
		return super.get(id);
	}
	
	public List<SysWxConfig> findList(SysWxConfig sysWxConfig) {
		return super.findList(sysWxConfig);
	}
	
	public Page<SysWxConfig> findPage(Page<SysWxConfig> page, SysWxConfig sysWxConfig) {
		return super.findPage(page, sysWxConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(SysWxConfig sysWxConfig) {
		super.save(sysWxConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysWxConfig sysWxConfig) {
		super.delete(sysWxConfig);
	}
	
}