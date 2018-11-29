package com.towcent.dist.shop.portal.mall.vo.input;


import com.towcent.dist.shop.portal.common.vo.BaseParam;
import lombok.Data;

/**
 * 2.3.3 订单商品评价列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderEvalListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;		// 订单Id
	
}