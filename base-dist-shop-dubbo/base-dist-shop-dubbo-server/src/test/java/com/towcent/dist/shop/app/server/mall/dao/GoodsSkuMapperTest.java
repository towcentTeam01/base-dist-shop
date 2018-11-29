package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.GoodsSku;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsSkuMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * goods_sku 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-28 17:42:23
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsSkuMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsSkuMapper mapper;
	
	@Test
	public void insertSelective() {
		GoodsSku entity = new GoodsSku();
		// 主键id
		entity.setId(1);
		// 商品规格id
		entity.setGoodsSpecId(1);
		// 规格价格
		entity.setPrice(BigDecimal.ONE);
		// 最小数量 批发商品专有
		entity.setMinNum(1);
		// 最大数量 批发商品专有
		entity.setMaxNum(1);
		// 备注
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
		GoodsSku entity = new GoodsSku();
		// 主键id
		entity.setId(1);
		// 商品规格id
		entity.setGoodsSpecId(1);
		// 规格价格
		entity.setPrice(BigDecimal.ONE);
		// 最小数量 批发商品专有
		entity.setMinNum(1);
		// 最大数量 批发商品专有
		entity.setMaxNum(1);
		// 备注
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
		GoodsSku entity = new GoodsSku();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}