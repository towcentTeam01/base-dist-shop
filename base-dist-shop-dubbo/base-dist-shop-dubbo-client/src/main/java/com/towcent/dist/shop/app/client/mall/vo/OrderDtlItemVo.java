package com.towcent.dist.shop.app.client.mall.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品数据初始化接口数组子对象
 * 
 * @author yxp
 */
@Setter
@Getter
public class OrderDtlItemVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id.
	 */
	private Integer id;

	/**
	 * 订单id.
	 */
	private Integer orderId;

	/**
	 * 商品id.
	 */
	private Integer goodsId;

	/**
	 * 商品名称.
	 */
	private String goodsName;

	/**
	 * 商品图片.
	 */
	private String goodsPicUrl;

	/**
	 * 规格.
	 */
	private String spec;

	/**
	 * 数量.
	 */
	private Integer qty;

	/**
	 * 单价(元).
	 */
	private BigDecimal price;

	/**
	 * 金额=数量*单价(元).
	 */
	private BigDecimal amount;

	/**
	 * 兑换积分.
	 */
	private BigDecimal integral;

	/**
	 * 评价标识(0:未评价 1:已评价) yes_no.
	 */
	private String evalFlag;

	/**
	 * 商户id.
	 */
	private Integer merchantId;
}
