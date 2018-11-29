package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.GoodsSpec;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsSpecMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * goods_spec 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-28 12:16:53
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsSpecMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsSpecMapper mapper;
	
	@Test
	public void insertSelective() {
		GoodsSpec entity = new GoodsSpec();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 商品类型( 0:普通商品 1:批发商品 )
		entity.setGoodsType("");
		// 规格名称
		entity.setName("");
		// 规格价格
		entity.setPrice(BigDecimal.ONE);
		// 规格库存
		entity.setStock(1);
		// 单位(件/套/...)
		entity.setUnit("");
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
		GoodsSpec entity = new GoodsSpec();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 商品类型( 0:普通商品 1:批发商品 )
		entity.setGoodsType("");
		// 规格名称
		entity.setName("");
		// 规格价格
		entity.setPrice(BigDecimal.ONE);
		// 规格库存
		entity.setStock(1);
		// 单位(件/套/...)
		entity.setUnit("");
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
		GoodsSpec entity = new GoodsSpec();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}