package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.GoodsCategory;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsCategoryMapper;
import java.util.Date;

/**
 * goods_category 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsCategoryMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsCategoryMapper mapper;
	
	@Test
	public void insertSelective() {
		GoodsCategory entity = new GoodsCategory();
		// 主键id
		entity.setId(1);
		// 分类编码
		entity.setCategoryNo("");
		// 分类名称
		entity.setCategoryName("");
		// 级别
		entity.setLevel(1);
		// 父id
		entity.setParentId(1);
		// 结构体编码
		entity.setStructureNo("");
		// 结构体名称
		entity.setStructureName("");
		// 排序
		entity.setSort(1);
		// 所有父id
		entity.setParentIds("");
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
		// 分类图片
		entity.setCategoryIcon("");
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
		GoodsCategory entity = new GoodsCategory();
		// 主键id
		entity.setId(1);
		// 分类编码
		entity.setCategoryNo("");
		// 分类名称
		entity.setCategoryName("");
		// 级别
		entity.setLevel(1);
		// 父id
		entity.setParentId(1);
		// 结构体编码
		entity.setStructureNo("");
		// 结构体名称
		entity.setStructureName("");
		// 排序
		entity.setSort(1);
		// 所有父id
		entity.setParentIds("");
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
		// 分类图片
		entity.setCategoryIcon("");
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		GoodsCategory entity = new GoodsCategory();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}