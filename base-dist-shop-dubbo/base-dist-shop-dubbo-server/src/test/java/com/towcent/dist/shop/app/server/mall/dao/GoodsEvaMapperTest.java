package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.GoodsEva;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsEvaMapper;
import java.util.Date;

/**
 * goods_eva 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-27 15:44:01
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsEvaMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsEvaMapper mapper;
	
	@Test
	public void insertSelective() {
		GoodsEva entity = new GoodsEva();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 订单id
		entity.setOrderId(1);
		// 订单详情id
		entity.setOrderDtlId(1);
		// 评价内容
		entity.setEvaContent("");
		// 会员id
		entity.setUserId(1);
		// 图片eva_urls(多个以;分割)
		entity.setEvaUrls("");
		// 评价星级(1-5星)
		entity.setEvaStar("");
		// 商家回复
		entity.setReplyContent("");
		// 商家回复时间
		entity.setReplyDate(new Date());
		// 是否匿名 (0:否 1:是)  yes_no
		entity.setIsHideName("");
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
		GoodsEva entity = new GoodsEva();
		// 主键id
		entity.setId(1);
		// 商品id
		entity.setGoodsId(1);
		// 订单id
		entity.setOrderId(1);
		// 订单详情id
		entity.setOrderDtlId(1);
		// 评价内容
		entity.setEvaContent("");
		// 会员id
		entity.setUserId(1);
		// 图片eva_urls(多个以;分割)
		entity.setEvaUrls("");
		// 评价星级(1-5星)
		entity.setEvaStar("");
		// 商家回复
		entity.setReplyContent("");
		// 商家回复时间
		entity.setReplyDate(new Date());
		// 是否匿名 (0:否 1:是)  yes_no
		entity.setIsHideName("");
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
		GoodsEva entity = new GoodsEva();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}