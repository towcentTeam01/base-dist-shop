package com.towcent.dist.shop.web.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.order.entity.OrderPayRecord;
import com.towcent.dist.shop.web.order.dao.OrderPayRecordDao;

/**
 * 交易记录Service
 * @author HuangTao
 * @version 2018-07-08
 */
@Service
@Transactional(readOnly = true)
public class OrderPayRecordService extends CrudService<OrderPayRecordDao, OrderPayRecord> {

	public OrderPayRecord get(String id) {
		return super.get(id);
	}
	
	public List<OrderPayRecord> findList(OrderPayRecord orderPayRecord) {
		return super.findList(orderPayRecord);
	}
	
	public Page<OrderPayRecord> findPage(Page<OrderPayRecord> page, OrderPayRecord orderPayRecord) {
		return super.findPage(page, orderPayRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderPayRecord orderPayRecord) {
		super.save(orderPayRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderPayRecord orderPayRecord) {
		super.delete(orderPayRecord);
	}
	
}