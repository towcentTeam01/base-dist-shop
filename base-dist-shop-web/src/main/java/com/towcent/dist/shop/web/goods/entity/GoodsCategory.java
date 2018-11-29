/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.towcent.dist.shop.web.goods.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.towcent.base.sc.web.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 商品分类Entity
 * @author alice
 * @version 2017-02-23
 */
public class GoodsCategory extends DataEntity<GoodsCategory> {
	
	private static final long serialVersionUID = 1L;
	private String categoryNo;		// 分类编码
	private String categoryName;		// 分类名称
	private Integer level;		// 级别
	private GoodsCategory parent;		// 父id
	private String structureNo;		// 结构体编码
	private String structureName;		// 结构体名称
	private Integer sort;		// 排序
	private String parentIds;		// 所有父id
	private String remarks;		// 注备
	private Integer merchantId;//商家ID
	private String categoryIcon;//分类图片

	public GoodsCategory() {
		super();
	}

	public GoodsCategory(String id){
		super(id);
	}

	@Length(min=0, max=20, message="分类编码长度必须介于 0 和 20 之间")
	public String getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(String categoryNo) {
		this.categoryNo = categoryNo.trim();
	}

	@Length(min=0, max=100, message="分类名称长度必须介于 0 和 100 之间")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@JsonBackReference
	public GoodsCategory getParent() {
		return parent;
	}

	public void setParent(GoodsCategory parent) {
		this.parent = parent;
	}

	public String getStructureNo() {
		return structureNo;
	}

	public void setStructureNo(String structureNo) {
		this.structureNo = structureNo;
	}

	@Length(min=0, max=500, message="结构体名称长度必须介于 0 和 500 之间")
	public String getStructureName() {
		return structureName;
	}

	public void setStructureName(String structureName) {
		this.structureName = structureName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Length(min=0, max=100, message="所有父id长度必须介于 0 和 100 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Length(min=0, max=500, message="注备长度必须介于 0 和 500 之间")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : getRootId();
	}


	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	@Length(min=0, max=200, message="注备长度必须介于 0 和 200 之间")
	public String getCategoryIcon() {
		return categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	@JsonIgnore
	public static void sortList(List<GoodsCategory> list, List<GoodsCategory> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			GoodsCategory e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						GoodsCategory child = sourcelist.get(j);
						if (child.getParent()!=null && child.getParent().getId()!=null
								&& child.getParent().getId().equals(e.getId())){
							sortList(list, sourcelist, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

	@JsonIgnore
	public static String getRootId(){
		return "0";
	}
	
}