package com.towcent.dist.shop.web.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.goods.entity.GoodsSpec;
import com.towcent.dist.shop.web.goods.dao.GoodsSpecDao;

/**
 * 商品规格Service
 * @author yxp
 * @version 2018-07-03
 */
@Service
@Transactional(readOnly = true)
public class GoodsSpecService extends CrudService<GoodsSpecDao, GoodsSpec> {

	public GoodsSpec get(String id) {
		return super.get(id);
	}
	
	public List<GoodsSpec> findList(GoodsSpec goodsSpec) {
		return super.findList(goodsSpec);
	}
	
	public Page<GoodsSpec> findPage(Page<GoodsSpec> page, GoodsSpec goodsSpec) {
		return super.findPage(page, goodsSpec);
	}
	
	@Transactional(readOnly = false)
	public void save(GoodsSpec goodsSpec) {
		super.save(goodsSpec);
	}
	
	@Transactional(readOnly = false)
	public void delete(GoodsSpec goodsSpec) {
		super.delete(goodsSpec);
	}
	
}