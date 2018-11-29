package com.towcent.dist.shop.portal.mall.vo.input;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.3.1 创建订单接口
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class OrderCreateIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotBlank(message = "goodsStr不能为空.")
	private String goodsStr;		// 商品Id:数量:规格(Id:qty:spec;Id:qty:spec)  (多个商品使用;分割) 例如(1:1:1;2:1:2)

	@NotBlank(message = "payWay不能为空.")
	private String payWay;		// 付款方式(0:现金支付 1:线上支付)
	
	private String remark;		// 买家留言
	
	@NotNull(message = "consigneeAddrId不能为空.")
	private Integer consigneeAddrId;		// 用户收货地址Id
	
	private Integer couponId;		// 优惠券id

}