package com.towcent.dist.shop.web.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.member.entity.SysFrontAccount;
import com.towcent.dist.shop.web.member.dao.SysFrontAccountDao;

/**
 * 会员Service
 * @author huangtao
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class SysFrontAccountService extends CrudService<SysFrontAccountDao, SysFrontAccount> {

	public SysFrontAccount get(String id) {
		return super.get(id);
	}
	
	public List<SysFrontAccount> findList(SysFrontAccount sysFrontAccount) {
		return super.findList(sysFrontAccount);
	}
	
	public Page<SysFrontAccount> findPage(Page<SysFrontAccount> page, SysFrontAccount sysFrontAccount) {
		return super.findPage(page, sysFrontAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(SysFrontAccount sysFrontAccount) {
		super.save(sysFrontAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysFrontAccount sysFrontAccount) {
		super.delete(sysFrontAccount);
	}
	
}