package com.towcent.dist.shop.portal.client.me;

import com.towcent.dist.shop.portal.me.vo.input.StatisticsInfoIn;
import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class StatisticsControllerTest extends BaseAppTest {

	static {
		descMap.put("me/statistics/info", "我的信息汇总接口");
		
	}

	@Test
	public void info() throws IOException {
		String path = "me/statistics/info";
		StatisticsInfoIn paramVo = new StatisticsInfoIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}