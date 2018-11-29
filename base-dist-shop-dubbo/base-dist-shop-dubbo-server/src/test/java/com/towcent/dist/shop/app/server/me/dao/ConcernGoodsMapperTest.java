package com.towcent.dist.shop.app.server.me.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.me.dto.ConcernGoods;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.me.dao.ConcernGoodsMapper;
import java.util.Date;

/**
 * concern_goods 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 18:41:27
 * @version 1.0
 * @copyright facegarden.com
 */
public class ConcernGoodsMapperTest extends BaseServiceTest {
	
	@Resource
	private ConcernGoodsMapper mapper;
	
	@Test
	public void insertSelective() {
		ConcernGoods entity = new ConcernGoods();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 注备
		entity.setRemarks("");
		// 创建者
		entity.setCreateBy(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 更新者
		entity.setUpdateBy(1);
		// 更新时间
		entity.setUpdateDate(new Date());
		// 删除标记(0:正常1:删除)
		entity.setDelFlag("");
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
		ConcernGoods entity = new ConcernGoods();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 注备
		entity.setRemarks("");
		// 创建者
		entity.setCreateBy(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 更新者
		entity.setUpdateBy(1);
		// 更新时间
		entity.setUpdateDate(new Date());
		// 删除标记(0:正常1:删除)
		entity.setDelFlag("");
		// 商户id
		entity.setMerchantId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		ConcernGoods entity = new ConcernGoods();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}