package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.GoodsChannelDtl;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsChannelDtlMapper;
import java.util.Date;

/**
 * goods_channel_dtl 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsChannelDtlMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsChannelDtlMapper mapper;
	
	@Test
	public void insertSelective() {
		GoodsChannelDtl entity = new GoodsChannelDtl();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 频道id
		entity.setChannelId(1);
		// 排序号
		entity.setSort(1);
		// 频道商品图片
		entity.setImg("");
		// 创建时间
		entity.setCreateDate(new Date());
		// 商户id
		entity.setMerchantId(1);
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
		GoodsChannelDtl entity = new GoodsChannelDtl();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 频道id
		entity.setChannelId(1);
		// 排序号
		entity.setSort(1);
		// 频道商品图片
		entity.setImg("");
		// 创建时间
		entity.setCreateDate(new Date());
		// 商户id
		entity.setMerchantId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		GoodsChannelDtl entity = new GoodsChannelDtl();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}