package com.towcent.dist.shop.portal.client.share;

import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerCountIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberCustomerListIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInfoIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberUpgradeIn;

public class DistMemberControllerTest extends BaseAppTest {

	static {
		descMap.put("share/distMember/customerList", "客户管理列表");
		descMap.put("share/distMember/customerCount", "客户管理汇总信息");
		descMap.put("share/distMember/upgrade", "升级分销");
		descMap.put("share/distMember/info", "分销用户信息");
		
	}

	@Test
	public void info() throws IOException {
		String path = "share/distMember/info";
		DistMemberInfoIn paramVo = new DistMemberInfoIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void upgrade() throws IOException {
		String path = "share/distMember/upgrade";
		DistMemberUpgradeIn paramVo = new DistMemberUpgradeIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setNickName("");
		paramVo.setMobile("");
		paramVo.setBindWx("");
		paramVo.setWxQrCode("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void customerCount() throws IOException {
		String path = "share/distMember/customerCount";
		DistMemberCustomerCountIn paramVo = new DistMemberCustomerCountIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void customerList() throws IOException {
		String path = "share/distMember/customerList";
		DistMemberCustomerListIn paramVo = new DistMemberCustomerListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setTabFlag("1");   // Tab(1:代理商 2:普通用户) 默认0全部
		paramVo.setSearchStr("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

}