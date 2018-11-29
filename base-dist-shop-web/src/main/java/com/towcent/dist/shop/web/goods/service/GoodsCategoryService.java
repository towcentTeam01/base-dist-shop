/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.towcent.dist.shop.web.goods.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.dist.shop.web.goods.dao.GoodsCategoryDao;
import com.towcent.dist.shop.web.goods.entity.GoodsCategory;

/**
 * 商品分类Service
 * 
 * @author alice
 * @version 2017-02-23
 */
@Service
@Transactional(readOnly = true)
public class GoodsCategoryService extends CrudService<GoodsCategoryDao, GoodsCategory> {

//	@Autowired
//	private GoodsService goodsService;
	
	public GoodsCategory get(String id) {
		return super.get(id);
	}

	public List<GoodsCategory> findList(GoodsCategory goodsCategory) {
		if (StringUtils.isNotBlank(goodsCategory.getParentIds())) {
			goodsCategory.setParentIds(goodsCategory.getParentIds()+"%");
		} 
		return dao.findByParentIdsLike(goodsCategory);
	}

	public Page<GoodsCategory> findPage(Page<GoodsCategory> page, GoodsCategory goodsCategory) {
		return super.findPage(page, goodsCategory);
	}

	/**
	 * 1:更新
	 */
	@Transactional(readOnly = false)
	public void save(GoodsCategory goodsCategory) {
		if (null != goodsCategory.getParent() && StringUtils.isBlank(goodsCategory.getParent().getId())) {
			// 添加一级分类
			goodsCategory.getParent().setId("0");
			goodsCategory.setParentIds("0,");
			goodsCategory.setLevel(1);
			goodsCategory.setStructureNo(goodsCategory.getCategoryNo());
			goodsCategory.setStructureName(goodsCategory.getCategoryName());
			super.save(goodsCategory);
			return;
		}
		
		// 获取父节点实体
		if(!goodsCategory.getParent().getId().equals(GoodsCategory.getRootId())){
			goodsCategory.setParent(this.get(goodsCategory.getParent().getId()));
		}
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = goodsCategory.getParentIds();
		String oldStructureNo = goodsCategory.getStructureNo();
		String oldStructureName = goodsCategory.getCategoryName();

		// 设置新的父节点串
		goodsCategory.setParentIds((StringUtils.isNotEmpty(goodsCategory.getParent().getParentIds())?goodsCategory.getParent().getParentIds():"") + goodsCategory.getParent().getId() + ",");
		//设置level
		goodsCategory.setLevel(Integer.parseInt(goodsCategory.getParentIds().split(",").length+""));
		
		//当父id发生变化 生成结构编码和结构名称  结构编码为所有父节点编码+本节点编码    结构名称为所有父节点名称+本节点名称
		if(!goodsCategory.getParentIds().equals(oldParentIds)){
			String structureNoPrefix = "", structureNamePrefix = "";
			String[] parentIdArr = goodsCategory.getParentIds().split(",");
			for (String parentId : parentIdArr) {
				if(parentId.trim().length()>0&&!parentId.equals(GoodsCategory.getRootId())){
					GoodsCategory parent = get(parentId);
					structureNoPrefix = structureNoPrefix + parent.getCategoryNo()+",";
					structureNamePrefix = structureNamePrefix + parent.getCategoryName()+",";
				}
			}
			goodsCategory.setStructureNo(structureNoPrefix+ goodsCategory.getCategoryNo());
			goodsCategory.setStructureName(structureNamePrefix+= goodsCategory.getCategoryName());
		}
		super.save(goodsCategory);

		// 更新子节点 parentIds及结构体编码 名称
		GoodsCategory gc = new GoodsCategory();
		gc.setParentIds("%," + gc.getId() + ",%");
		List<GoodsCategory> list = dao.findByParentIdsLike(gc);
		for (GoodsCategory e : list) {
			e.setParentIds(e.getParentIds().replace(oldParentIds, goodsCategory.getParentIds()));
			e.setStructureNo(e.getStructureNo().replace(oldStructureNo, goodsCategory.getStructureNo()));
			e.setStructureName(e.getStructureName().replace(oldStructureName, goodsCategory.getStructureName()));
			dao.updateParentIds(e);
		}

		//如果结构体发生改变  更新所有对应商品的结构体编码和名称
		if(!goodsCategory.getStructureNo().equals(oldStructureNo)){
//			Goods e = new Goods();
//			e.setGoodsCategory(new GoodsCategory(goodsCategory.getId()));
//			List<Goods> goodsArr = goodsService.findList(e);
//			Goods  goods;
//			for (Goods g : goodsArr) {
//				goods = new Goods(g.getId());
//				goods.setGoodsCategory(goodsCategory);
//				goodsService.updateSelective(goods);
//			}
		}
	}

	/**
	 * 删除商品分类
	 * @param goodsCategory
	 * @return  返回false表明该分类或子分类下还有商品，不能进行删除
	 */
	@Transactional(readOnly = false)
	public Boolean deleteGoodsCategory(GoodsCategory goodsCategory) {
		if(dao.countCategoryGoods(goodsCategory)==0){
			super.delete(goodsCategory);
			return true;
		}
		return false;
	}

	/**
	 * 更新菜单分类排序
	 */
	@Transactional(readOnly = false)
	public void updateGoodsCategorySort(GoodsCategory goodsCategory) {
		dao.updateSort(goodsCategory);
	}

	/**
	 * 获取当前商家当前父节点下下一子节点的排序
	 * @param merchantId
	 * @param parentId
	 * @return
	 */
	public Integer getSort(Integer merchantId,String parentId) {
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setParent(new GoodsCategory(parentId));
		goodsCategory.setMerchantId(merchantId);
		Integer sort = dao.getCurrentMaxSort(goodsCategory);
		return (sort!=null?sort:0)+1;
	}

	/**
	 * 检验分类编码是否可用   分类编码必须唯一
	 * @param categoryNo
	 * @return
	 */
	public Boolean checkCategoryNo(Integer merchantId,String categoryNo) {
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setMerchantId(merchantId);
		goodsCategory.setCategoryNo(categoryNo);
		Integer count = dao.countCategoryNo(goodsCategory);
		return count==null||count<1;
	}

	/**
	 * 通过分类结构体编码查找分类名称及ID
	 * @param structureNo
	 * @param merchantId
	 * @return
	 */
	/*public GoodsCategory getByStructureNo(String structureNo, Long merchantId) {
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setStructureNo(structureNo);
		goodsCategory.setMerchantId(merchantId);
		return dao.getByStructureNo(goodsCategory);
	}*/
}