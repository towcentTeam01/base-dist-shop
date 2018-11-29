package com.towcent.dist.shop.web.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.sys.entity.SysPosterTemplate;
import com.towcent.dist.shop.web.sys.dao.SysPosterTemplateDao;

/**
 * 海报模板Service
 * @author HuangTao
 * @version 2018-07-31
 */
@Service
@Transactional(readOnly = true)
public class SysPosterTemplateService extends CrudService<SysPosterTemplateDao, SysPosterTemplate> {

	public SysPosterTemplate get(String id) {
		return super.get(id);
	}
	
	public List<SysPosterTemplate> findList(SysPosterTemplate sysPosterTemplate) {
		return super.findList(sysPosterTemplate);
	}
	
	public Page<SysPosterTemplate> findPage(Page<SysPosterTemplate> page, SysPosterTemplate sysPosterTemplate) {
		return super.findPage(page, sysPosterTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(SysPosterTemplate sysPosterTemplate) {
		super.save(sysPosterTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysPosterTemplate sysPosterTemplate) {
		super.delete(sysPosterTemplate);
	}
	
}