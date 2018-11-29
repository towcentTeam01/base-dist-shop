package com.towcent.dist.shop.web.config.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.config.entity.PayAccount;
import com.towcent.dist.shop.web.config.dao.PayAccountDao;

/**
 * 支付配置Service
 * @author huangtao
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class PayAccountService extends CrudService<PayAccountDao, PayAccount> {

	public PayAccount get(String id) {
		return super.get(id);
	}
	
	public List<PayAccount> findList(PayAccount payAccount) {
		return super.findList(payAccount);
	}
	
	public Page<PayAccount> findPage(Page<PayAccount> page, PayAccount payAccount) {
		return super.findPage(page, payAccount);
	}
	
	@Transactional(readOnly = false)
	public void save(PayAccount payAccount) {
		super.save(payAccount);
	}
	
	@Transactional(readOnly = false)
	public void delete(PayAccount payAccount) {
		super.delete(payAccount);
	}
	
}