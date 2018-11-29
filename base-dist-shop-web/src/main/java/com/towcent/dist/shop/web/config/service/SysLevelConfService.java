package com.towcent.dist.shop.web.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.config.entity.SysLevelConf;
import com.towcent.dist.shop.web.config.dao.SysLevelConfDao;

/**
 * 等级配置Service
 * @author huangtao
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class SysLevelConfService extends CrudService<SysLevelConfDao, SysLevelConf> {

	public SysLevelConf get(String id) {
		return super.get(id);
	}
	
	public List<SysLevelConf> findList(SysLevelConf sysLevelConf) {
		return super.findList(sysLevelConf);
	}
	
	public Page<SysLevelConf> findPage(Page<SysLevelConf> page, SysLevelConf sysLevelConf) {
		return super.findPage(page, sysLevelConf);
	}
	
	@Transactional(readOnly = false)
	public void save(SysLevelConf sysLevelConf) {
		super.save(sysLevelConf);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysLevelConf sysLevelConf) {
		super.delete(sysLevelConf);
	}
	
}