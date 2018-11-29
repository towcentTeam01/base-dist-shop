package com.towcent.dist.shop.app.client.mall.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品sku对象
 *
 * @author huangtao
 * @date 2017年3月25日 上午1:29:00
 * @version 0.1.0
 * @copyright towcent.com
 */
@Setter
@Getter
public class GoodsSkuVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer qty;
	private Integer spec;
	private GoodsVo goods; // 对应的商品对象

	private BigDecimal price;

	private Integer stock = 0;

	private String specName;

	public GoodsSkuVo() {
		super();
	}

	/**
	 * Id:qty:spec
	 *
	 * @param str
	 */
	public GoodsSkuVo(String str) {
		String[] arr = str.split(":");
		id = Integer.parseInt(arr[0].trim());
		qty = Integer.parseInt(arr[1].trim());
		spec = Integer.parseInt(arr[2].trim());
	}
}
