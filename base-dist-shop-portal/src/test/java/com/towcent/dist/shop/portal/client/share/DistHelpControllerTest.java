package com.towcent.dist.shop.portal.client.share;

import com.towcent.dist.shop.portal.share.vo.input.DistHelpFriendIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpShopIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterTemplateIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpLevelDescIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpGuideIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpPosterIn;
import com.towcent.dist.shop.portal.share.vo.input.DistMemberInviteIn;
import com.towcent.dist.shop.portal.share.vo.input.DistHelpCustomerServiceIn;
import com.towcent.dist.shop.portal.share.vo.input.DistProductIn;

import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class DistHelpControllerTest extends BaseAppTest {

	static {
		descMap.put("share/DistHelp/friend", "邀请好友加入");
		descMap.put("share/DistHelp/shop", "商城分享");
		descMap.put("share/DistHelp/posterTemplate", "获取海报模板");
		descMap.put("share/distHelp/levelDesc", "分类等级介绍");
		descMap.put("share/distHelp/guide", "操作指南");
		descMap.put("share/distHelp/poster", "生成专属海报");
		descMap.put("share/distHelp/customerService", "专属客服");
		descMap.put("share/distHelp/invite", "邀请好友");
		descMap.put("share/distHelp/product", "商品分享");
	}

	@Test
	public void customerService() throws IOException {
		String path = "share/distHelp/customerService";
		DistHelpCustomerServiceIn paramVo = new DistHelpCustomerServiceIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void poster() throws IOException {
		String path = "share/distHelp/poster";
		DistHelpPosterIn paramVo = new DistHelpPosterIn();
		paramVo.setTemplateId(1);
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void guide() throws IOException {
		String path = "share/distHelp/guide";
		DistHelpGuideIn paramVo = new DistHelpGuideIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void levelDesc() throws IOException {
		String path = "share/distHelp/levelDesc";
		DistHelpLevelDescIn paramVo = new DistHelpLevelDescIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
	
	@Test
	public void invite() throws IOException {
		String path = "share/distHelp/invite";
		DistMemberInviteIn paramVo = new DistMemberInviteIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
	
	@Test
	public void product() throws IOException {
		String path = "share/distHelp/product";
		DistProductIn paramVo = new DistProductIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		paramVo.setProductId("");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void posterTemplate() throws IOException {
		String path = "share/distHelp/posterTemplate";
		DistHelpPosterTemplateIn paramVo = new DistHelpPosterTemplateIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void shop() throws IOException {
		String path = "share/distHelp/shop";
		DistHelpShopIn paramVo = new DistHelpShopIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void friend() throws IOException {
		String path = "share/DistHelp/friend";
		DistHelpFriendIn paramVo = new DistHelpFriendIn();
		this.setCommonParam(paramVo);
		
		this.setLoginFlag(paramVo);	
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}