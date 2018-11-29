package com.towcent.dist.shop.portal.mall.vo.output;

import java.util.List;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.0.3 商品分类接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsCategoryListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 分类id	
	private String name;		// 分类名称	
	private String structureNo;  // 分类结构编码
	private String icon;		// 分类图标	
	private List<GoodsCategoryListOut> list;		// 分类列表(当level不小于2是有值)	
	
}