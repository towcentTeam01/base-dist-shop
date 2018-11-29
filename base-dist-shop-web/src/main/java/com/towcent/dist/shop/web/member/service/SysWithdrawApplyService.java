package com.towcent.dist.shop.web.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.member.entity.SysWithdrawApply;
import com.towcent.dist.shop.web.member.dao.SysWithdrawApplyDao;

/**
 * 提现申请Service
 * @author HuangTao
 * @version 2018-07-08
 */
@Service
@Transactional(readOnly = true)
public class SysWithdrawApplyService extends CrudService<SysWithdrawApplyDao, SysWithdrawApply> {

	public SysWithdrawApply get(String id) {
		return super.get(id);
	}
	
	public List<SysWithdrawApply> findList(SysWithdrawApply sysWithdrawApply) {
		return super.findList(sysWithdrawApply);
	}
	
	public Page<SysWithdrawApply> findPage(Page<SysWithdrawApply> page, SysWithdrawApply sysWithdrawApply) {
		return super.findPage(page, sysWithdrawApply);
	}
	
	@Transactional(readOnly = false)
	public void save(SysWithdrawApply sysWithdrawApply) {
		super.save(sysWithdrawApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysWithdrawApply sysWithdrawApply) {
		super.delete(sysWithdrawApply);
	}
	
}