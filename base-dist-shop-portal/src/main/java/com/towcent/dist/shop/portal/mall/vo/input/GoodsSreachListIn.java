package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.0.1 首页搜索商品接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsSreachListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	@NotBlank(message = "searchStr不能为空.")
	private String searchStr;		// 查询条件(商品名称)
	
	private String orderByField;		// 排序字段 0:默认(时间倒序) 1:销量 2:价格
	
	private String orderBy;		// 排序 0:降序 1:升序
	
}