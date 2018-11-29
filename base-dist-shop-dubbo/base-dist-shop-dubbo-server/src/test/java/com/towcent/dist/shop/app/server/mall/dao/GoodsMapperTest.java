package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * goods 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsMapper mapper;
	
	@Test
	public void insertSelective() {
		Goods entity = new Goods();
		// 主键id
		entity.setId(1);
		// 商品分类id
		entity.setCategoryId(1);
		// 分类结构编码
		entity.setStructureNo("");
		// 分类结构名称
		entity.setStructureName("");
		// 商品编码
		entity.setGoodsNo("");
		// 商品名称
		entity.setGoodsName("");
		// 商品类型(0:普通商品 1:批发商品)
		entity.setGoodsType("");
		// 商品条形码
		entity.setGoodsBarcode("");
		// 商品状态(1:未发布 2:上架 3:下架 4:图片处理中)
		entity.setGoodsStatus("");
		// 商品品牌
		entity.setBrand("");
		// 商品价格
		entity.setPrice(BigDecimal.ONE);
		// 兑换积分
		entity.setIntegral(1);
		// 最小商品价格,当商品类型为批发商品时独有
		entity.setMinPrice(BigDecimal.ONE);
		// 最大商品价格,当商品类型为批发商品时独有
		entity.setMaxPrice(BigDecimal.ONE);
		// 商品成本价
		entity.setCostPrice(BigDecimal.ONE);
		// 商品图片url(多个;分割)
		entity.setMainUrls("");
		// 商品简介
		entity.setDescription("");
		// 商品图片描述
		entity.setDescPic("");
		// 图片版本
		entity.setDescPicV(1);
		// 重量(kg)
		entity.setWeight(BigDecimal.ONE);
		// 销量
		entity.setSales(1);
		// 二维码地址
		entity.setQrCode("");
		// 评价数量
		entity.setEvaNum(1);
		// 好评率
		entity.setGoodEvalRate(BigDecimal.ONE);
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
		Goods entity = new Goods();
		// 主键id
		entity.setId(1);
		// 商品分类id
		entity.setCategoryId(1);
		// 分类结构编码
		entity.setStructureNo("");
		// 分类结构名称
		entity.setStructureName("");
		// 商品编码
		entity.setGoodsNo("");
		// 商品名称
		entity.setGoodsName("");
		// 商品类型(0:普通商品 1:批发商品)
		entity.setGoodsType("");
		// 商品条形码
		entity.setGoodsBarcode("");
		// 商品状态(1:未发布 2:上架 3:下架 4:图片处理中)
		entity.setGoodsStatus("");
		// 商品品牌
		entity.setBrand("");
		// 商品价格
		entity.setPrice(BigDecimal.ONE);
		// 兑换积分
		entity.setIntegral(1);
		// 最小商品价格,当商品类型为批发商品时独有
		entity.setMinPrice(BigDecimal.ONE);
		// 最大商品价格,当商品类型为批发商品时独有
		entity.setMaxPrice(BigDecimal.ONE);
		// 商品成本价
		entity.setCostPrice(BigDecimal.ONE);
		// 商品图片url(多个;分割)
		entity.setMainUrls("");
		// 商品简介
		entity.setDescription("");
		// 商品图片描述
		entity.setDescPic("");
		// 图片版本
		entity.setDescPicV(1);
		// 重量(kg)
		entity.setWeight(BigDecimal.ONE);
		// 销量
		entity.setSales(1);
		// 二维码地址
		entity.setQrCode("");
		// 评价数量
		entity.setEvaNum(1);
		// 好评率
		entity.setGoodEvalRate(BigDecimal.ONE);
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
		Goods entity = new Goods();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}