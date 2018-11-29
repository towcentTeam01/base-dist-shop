package com.towcent.dist.shop.web.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.goods.entity.GoodsSku;
import com.towcent.dist.shop.web.goods.dao.GoodsSkuDao;

/**
 * 商品SKUService
 * @author yxp
 * @version 2018-07-03
 */
@Service
@Transactional(readOnly = true)
public class GoodsSkuService extends CrudService<GoodsSkuDao, GoodsSku> {

	public GoodsSku get(String id) {
		return super.get(id);
	}

	public List<GoodsSku> findList(GoodsSku goodsSku) {
		return super.findList(goodsSku);
	}

	public Page<GoodsSku> findPage(Page<GoodsSku> page, GoodsSku goodsSku) {
		return super.findPage(page, goodsSku);
	}

	@Transactional(readOnly = false)
	public void save(GoodsSku goodsSku) {
		super.save(goodsSku);
	}

	@Transactional(readOnly = false)
	public void delete(GoodsSku goodsSku) {
		super.delete(goodsSku);
	}

}