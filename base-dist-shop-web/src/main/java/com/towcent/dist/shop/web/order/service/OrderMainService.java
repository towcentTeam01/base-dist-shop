package com.towcent.dist.shop.web.order.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.order.entity.OrderMain;
import com.towcent.dist.shop.web.order.dao.OrderMainDao;

/**
 * 订单列表Service
 * @author huangtao
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class OrderMainService extends CrudService<OrderMainDao, OrderMain> {

	public OrderMain get(String id) {
		return super.get(id);
	}
	
	public List<OrderMain> findList(OrderMain orderMain) {
		return super.findList(orderMain);
	}
	
	public Page<OrderMain> findPage(Page<OrderMain> page, OrderMain orderMain) {
		return super.findPage(page, orderMain);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderMain orderMain) {
		super.save(orderMain);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderMain orderMain) {
		super.delete(orderMain);
	}

	@Transactional(readOnly = false)
	public void sendGoods(OrderMain orderMain) {
		super.updateSelective(orderMain);
	}
	
}