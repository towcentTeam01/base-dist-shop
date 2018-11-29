package com.towcent.dist.shop.web.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.sys.entity.SysHelpGuide;
import com.towcent.dist.shop.web.sys.dao.SysHelpGuideDao;

/**
 * 系统教程Service
 * @author HuangTao
 * @version 2018-07-22
 */
@Service
@Transactional(readOnly = true)
public class SysHelpGuideService extends CrudService<SysHelpGuideDao, SysHelpGuide> {

	public SysHelpGuide get(String id) {
		return super.get(id);
	}
	
	public List<SysHelpGuide> findList(SysHelpGuide sysHelpGuide) {
		return super.findList(sysHelpGuide);
	}
	
	public Page<SysHelpGuide> findPage(Page<SysHelpGuide> page, SysHelpGuide sysHelpGuide) {
		return super.findPage(page, sysHelpGuide);
	}
	
	@Transactional(readOnly = false)
	public void save(SysHelpGuide sysHelpGuide) {
		super.save(sysHelpGuide);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysHelpGuide sysHelpGuide) {
		super.delete(sysHelpGuide);
	}
	
}