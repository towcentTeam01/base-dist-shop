/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : QuickMarkServiceTest.java
 * @Package : com.towcent.dist.shop.app.server.sys.service
 * @date : 2018年8月1日下午11:31:54
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.sys.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.base.common.qrcode.QuickMarkService;
import com.towcent.dist.shop.app.server.BaseServiceTest;

/**
 * @ClassName: QuickMarkServiceTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年8月1日 下午11:31:54
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class QuickMarkServiceTest extends BaseServiceTest {

	@Resource
	private QuickMarkService quickMarkService;
	
	// 测试生成二维码
	@Test
	public void encoderGQRCode2File() {
		String content = "http://wx2.51jll.cn/index.html";
		String imgPath = "d:\\ut.png";
		String imgType = "png";
		int size = 350;
		quickMarkService.encoderGQRCode2File(content, imgPath, imgType, size);
		
		System.out.println("完毕！");
	}

}

	