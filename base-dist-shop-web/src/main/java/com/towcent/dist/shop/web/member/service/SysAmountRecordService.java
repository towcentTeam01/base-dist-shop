package com.towcent.dist.shop.web.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.member.entity.SysAmountRecord;
import com.towcent.dist.shop.web.member.dao.SysAmountRecordDao;

/**
 * 钱包明细Service
 * @author huangtao
 * @version 2018-07-08
 */
@Service
@Transactional(readOnly = true)
public class SysAmountRecordService extends CrudService<SysAmountRecordDao, SysAmountRecord> {

	public SysAmountRecord get(String id) {
		return super.get(id);
	}
	
	public List<SysAmountRecord> findList(SysAmountRecord sysAmountRecord) {
		return super.findList(sysAmountRecord);
	}
	
	public Page<SysAmountRecord> findPage(Page<SysAmountRecord> page, SysAmountRecord sysAmountRecord) {
		return super.findPage(page, sysAmountRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(SysAmountRecord sysAmountRecord) {
		super.save(sysAmountRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysAmountRecord sysAmountRecord) {
		super.delete(sysAmountRecord);
	}
	
}