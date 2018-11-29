package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.OrderDtlMapper;
import java.math.BigDecimal;

/**
 * order_dtl 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-28 18:16:12
 * @version 1.0
 * @copyright facegarden.com
 */
public class OrderDtlMapperTest extends BaseServiceTest {
	
	@Resource
	private OrderDtlMapper mapper;
	
	@Test
	public void insertSelective() {
		OrderDtl entity = new OrderDtl();
		// 主键id
		entity.setId(1);
		// 订单id
		entity.setOrderId(1);
		// 商品id
		entity.setGoodsId(1);
		// 商品名称
		entity.setGoodsName("");
		// 商品图片
		entity.setGoodsPicUrl("");
		// 规格
		entity.setSpec("");
		// 数量
		entity.setQty(1);
		// 单价(元)
		entity.setPrice(BigDecimal.ONE);
		// 金额=数量*单价(元)
		entity.setAmount(BigDecimal.ONE);
		// 兑换积分
		entity.setIntegral(BigDecimal.ONE);
		// 评价标识(0:未评价 1:已评价) yes_no
		entity.setEvalFlag("");
		// 商户id
		entity.setMerchantId(1);
		// 规格id
		entity.setSpecId(1);
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
		OrderDtl entity = new OrderDtl();
		// 主键id
		entity.setId(1);
		// 订单id
		entity.setOrderId(1);
		// 商品id
		entity.setGoodsId(1);
		// 商品名称
		entity.setGoodsName("");
		// 商品图片
		entity.setGoodsPicUrl("");
		// 规格
		entity.setSpec("");
		// 数量
		entity.setQty(1);
		// 单价(元)
		entity.setPrice(BigDecimal.ONE);
		// 金额=数量*单价(元)
		entity.setAmount(BigDecimal.ONE);
		// 兑换积分
		entity.setIntegral(BigDecimal.ONE);
		// 评价标识(0:未评价 1:已评价) yes_no
		entity.setEvalFlag("");
		// 商户id
		entity.setMerchantId(1);
		// 规格id
		entity.setSpecId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		OrderDtl entity = new OrderDtl();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}