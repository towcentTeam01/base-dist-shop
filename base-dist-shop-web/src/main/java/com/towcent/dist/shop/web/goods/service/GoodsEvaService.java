package com.towcent.dist.shop.web.goods.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.goods.entity.GoodsEva;
import com.towcent.dist.shop.web.goods.dao.GoodsEvaDao;

/**
 * 商品评价Service
 * @author yxp
 * @version 2018-07-09
 */
@Service
@Transactional(readOnly = true)
public class GoodsEvaService extends CrudService<GoodsEvaDao, GoodsEva> {

	public GoodsEva get(String id) {
		return super.get(id);
	}
	
	public List<GoodsEva> findList(GoodsEva goodsEva) {
		return super.findList(goodsEva);
	}
	
	public Page<GoodsEva> findPage(Page<GoodsEva> page, GoodsEva goodsEva) {
		return super.findPage(page, goodsEva);
	}
	
	@Transactional(readOnly = false)
	public void save(GoodsEva goodsEva) {
		super.save(goodsEva);
	}
	
	@Transactional(readOnly = false)
	public void delete(GoodsEva goodsEva) {
		super.delete(goodsEva);
	}
	
}