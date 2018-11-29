/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : sdx-logistics-dubbo-client
 * @Title : PayBalanceVo.java
 * @Package : com.towcent.sdx.logistics.app.client.pay.vo
 * @date : 2018年1月25日下午4:02:13
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: PayBalanceVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年1月25日 下午4:02:13
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Setter @Getter
public class PayBalanceVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否支付成功.
	 */
	private boolean result;
	
	/**
	 * 支付金额.
	 */
	private BigDecimal payAmount;
	
	/**
	 * 支付失败原因.
	 */
	private String errorMsg;

}

	