package com.towcent.dist.shop.portal.mall.vo.input;



import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.0.3 商品分类接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsCategoryListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	private Integer parentId;		// 父级分类Id
	
	private Integer level;		// 查询级别 总共三级
	
}