/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : sdx-logistics-dubbo-server
 * @Title : PayCallVo.java
 * @Package : com.towcent.sdx.logistics.app.server.pay.vo
 * @date : 2018年1月27日上午10:32:45
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.order.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: PayCallVo 
 * @Description: 支付回调Vo 
 *
 * @author huangtao
 * @date 2018年1月27日 上午10:32:45
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Setter @Getter
public class PayCallVo {

	/**
	 * 支付时间.
	 */
	private Date payDate;

	/**
	 * 第三方支付流水号.
	 */
	private String thirdPaySn;
	
	/**
	 * 支付交易号
	 */
	private String payRecordNo;
	
}

	