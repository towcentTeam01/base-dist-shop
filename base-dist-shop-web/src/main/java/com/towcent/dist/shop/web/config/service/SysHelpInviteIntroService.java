package com.towcent.dist.shop.web.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.config.dao.SysHelpInviteIntroDao;
import com.towcent.dist.shop.web.config.entity.SysHelpInviteIntro;

/**
 * 邀请介绍Service
 * @author HuangTao
 * @version 2018-07-08
 */
@Service
@Transactional(readOnly = true)
public class SysHelpInviteIntroService extends CrudService<SysHelpInviteIntroDao, SysHelpInviteIntro> {

	public SysHelpInviteIntro get(String id) {
		return super.get(id);
	}
	
	public List<SysHelpInviteIntro> findList(SysHelpInviteIntro sysHelpInviteIntro) {
		return super.findList(sysHelpInviteIntro);
	}
	
	public Page<SysHelpInviteIntro> findPage(Page<SysHelpInviteIntro> page, SysHelpInviteIntro sysHelpInviteIntro) {
		return super.findPage(page, sysHelpInviteIntro);
	}
	
	@Transactional(readOnly = false)
	public void save(SysHelpInviteIntro sysHelpInviteIntro) {
		super.save(sysHelpInviteIntro);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysHelpInviteIntro sysHelpInviteIntro) {
		super.delete(sysHelpInviteIntro);
	}
	
}