package com.towcent.dist.shop.portal.mall.vo.input;


import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.3.2 订单列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotBlank(message = "tabFlag不能为空.")
	private String tabFlag;		// Tab(1:全部 2:待付款 3:已支付 4:待收货 5:待评价 6:已完成 7:已取消) 默认1全部
	
}