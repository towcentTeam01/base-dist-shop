package com.towcent.dist.shop.portal.mall.vo.output;

import java.util.List;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.0.2 首页商品推荐接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsChannelListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 频道id	
	private String name;		// 频道名称	
	private List list;		// 商品列表	
	
}