/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.towcent.dist.shop.web.goods.dao;

import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.dist.shop.web.goods.entity.GoodsCategory;

import java.util.List;

/**
 * 商品分类DAO接口
 * @author alice
 * @version 2017-02-23
 */
@MyBatisDao
public interface GoodsCategoryDao extends CrudDao<GoodsCategory> {

	public List<GoodsCategory> findByParentIdsLike(GoodsCategory goodsCategory);

	public int updateParentIds(GoodsCategory goodsCategory);
	
	public int updateSort(GoodsCategory goodsCategory);
	
	/**
	 * 当前商家当前父id下最大的排序号
	 * @param goodsCategory
	 * @return
	 */ 
	public Integer getCurrentMaxSort(GoodsCategory goodsCategory); 
	
	/**
	 * 查询当前商家下对应商品编号的商品分类数目
	 * @param goodsCategory
	 * @return
	 */
	public Integer countCategoryNo(GoodsCategory goodsCategory);

	/**
	 * 查询该分类下的商品数目
	 * @return
	 */
	public Integer countCategoryGoods(GoodsCategory goodsCategory);
}