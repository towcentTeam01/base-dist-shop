/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : sdx-logistics-dubbo-client
 * @Title : PayOnlineVo.java
 * @Package : com.towcent.sdx.logistics.app.client.pay.vo
 * @date : 2018年1月25日下午12:16:01
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.client.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: PayOnlineVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年1月25日 下午12:16:01
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Setter @Getter
public class PayOnlineVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 可抵扣余额.
	 */
	private BigDecimal balance;
	
	/**
	 * 在线支付金额.
	 */
	private BigDecimal payAmount;
	
	/**
	 * 支付二维码地址.
	 */
	private String payQrCode;
	
	/**
	 * 支付交易号.
	 */
	private String payRecordNo;
	
	/**
	 * 支付宝页面响应.
	 */
	private String responseHtml;
}

	