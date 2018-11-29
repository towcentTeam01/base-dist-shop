package com.towcent.dist.shop.app.server.share.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.share.dto.ShareCategory;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.share.dao.ShareCategoryMapper;

/**
 * share_category 数据库操作接口测试用例
 * 
 * @author shiwei
 * @date 2018-07-31 11:36:43
 * @version 1.0
 * @copyright facegarden.com
 */
public class ShareCategoryMapperTest extends BaseServiceTest {
	
	@Resource
	private ShareCategoryMapper mapper;
	
	@Test
	public void insertSelective() {
		ShareCategory entity = new ShareCategory();
		// id
		entity.setId(1);
		// 分类编码
		entity.setCategoryNo("");
		// 分享信息
		entity.setShareDesc("");
		// 分享图片
		entity.setShareImage("");
		// share_title
		entity.setShareTitle("");
		// 商户id 为0时为平台 
		entity.setMerchantId(1);
		// 商品分类id
		entity.setCategoryId(1);
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
		ShareCategory entity = new ShareCategory();
		// id
		entity.setId(1);
		// 分类编码
		entity.setCategoryNo("");
		// 分享信息
		entity.setShareDesc("");
		// 分享图片
		entity.setShareImage("");
		// share_title
		entity.setShareTitle("");
		// 商户id 为0时为平台 
		entity.setMerchantId(1);
		// 商品分类id
		entity.setCategoryId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		ShareCategory entity = new ShareCategory();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}