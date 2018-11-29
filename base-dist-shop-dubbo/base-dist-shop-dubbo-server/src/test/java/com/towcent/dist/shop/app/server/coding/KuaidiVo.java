/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : TVo.java
 * @Package : com.towcent.dist.shop.app.server.coding
 * @date : 2018年7月11日下午6:48:48
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.coding;

import com.towcent.base.common.utils.excel.annotation.ExcelField;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: TVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年7月11日 下午6:48:48
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Setter @Getter
public class KuaidiVo {

	@ExcelField(title="", groups={0})
	private String companyNo;
	
	@ExcelField(title="", groups={0})
	private String companyName;

}

	