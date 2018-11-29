package com.towcent.dist.shop.app.server.share.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.share.dto.ShareInfo;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.share.dao.ShareInfoMapper;
import java.util.Date;

/**
 * share_info 数据库操作接口测试用例
 * 
 * @author shiwei
 * @date 2018-07-31 20:14:05
 * @version 1.0
 * @copyright facegarden.com
 */
public class ShareInfoMapperTest extends BaseServiceTest {
	
	@Resource
	private ShareInfoMapper mapper;
	
	@Test
	public void insertSelective() {
		ShareInfo entity = new ShareInfo();
		// id
		entity.setId(1L);
		// 工号
		entity.setJobNo("");
		// 用户ID
		entity.setCreateBy(1);
		// 商户id
		entity.setMerchantId(1);
		// 商品ID
		entity.setProductId(1);
		// 分享信息
		entity.setShareDesc("");
		// 分享图片
		entity.setShareImage("");
		// 分享链接
		entity.setShareUrl("");
		// 创建时间
		entity.setCreateDate(new Date());
		// 浏览量
		entity.setViewNum(1);
		// 成交量
		entity.setTradeNum(1);
		// 分享标题
		entity.setShareTitle("");
		int count = mapper.insertSelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void deleteByPrimaryKey() {
		int count = mapper.deleteByPrimaryKey(1 + "");
		System.out.println(count);
	}
	
	@Test
	public void updateByPrimaryKeySelective() {
		ShareInfo entity = new ShareInfo();
		// id
		entity.setId(1L);
		// 工号
		entity.setJobNo("");
		// 用户ID
		entity.setCreateBy(1);
		// 商户id
		entity.setMerchantId(1);
		// 商品ID
		entity.setProductId(1);
		// 分享信息
		entity.setShareDesc("");
		// 分享图片
		entity.setShareImage("");
		// 分享链接
		entity.setShareUrl("");
		// 创建时间
		entity.setCreateDate(new Date());
		// 浏览量
		entity.setViewNum(1);
		// 成交量
		entity.setTradeNum(1);
		// 分享标题
		entity.setShareTitle("");
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		ShareInfo entity = new ShareInfo();
		entity.setId(1l);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}