package com.towcent.dist.shop.web.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.config.dao.SysHelpRuleDao;
import com.towcent.dist.shop.web.config.entity.SysHelpRule;

/**
 * 等级规则描述Service
 * @author HuangTao
 * @version 2018-07-08
 */
@Service
@Transactional(readOnly = true)
public class SysHelpRuleService extends CrudService<SysHelpRuleDao, SysHelpRule> {

	public SysHelpRule get(String id) {
		return super.get(id);
	}
	
	public List<SysHelpRule> findList(SysHelpRule sysHelpRule) {
		return super.findList(sysHelpRule);
	}
	
	public Page<SysHelpRule> findPage(Page<SysHelpRule> page, SysHelpRule sysHelpRule) {
		return super.findPage(page, sysHelpRule);
	}
	
	@Transactional(readOnly = false)
	public void save(SysHelpRule sysHelpRule) {
		super.save(sysHelpRule);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysHelpRule sysHelpRule) {
		super.delete(sysHelpRule);
	}
	
}