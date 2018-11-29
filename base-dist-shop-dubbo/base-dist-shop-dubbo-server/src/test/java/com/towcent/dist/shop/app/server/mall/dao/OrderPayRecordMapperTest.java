package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.OrderPayRecord;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.OrderPayRecordMapper;
import java.util.Date;
import java.math.BigDecimal;

/**
 * order_pay_record 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
public class OrderPayRecordMapperTest extends BaseServiceTest {
	
	@Resource
	private OrderPayRecordMapper mapper;
	
	@Test
	public void insertSelective() {
		OrderPayRecord entity = new OrderPayRecord();
		// 主键id
		entity.setId(1);
		// 支付交易号
		entity.setPayRecordNo("");
		// 业务类型(0:订单 1:余额充值)
		entity.setBizType("");
		// 订单id
		entity.setOrderId(1);
		// 支付方式(0:余额支付 1:微信支付 2:支付宝)
		entity.setPayType("");
		// 支付时间
		entity.setPayDate(new Date());
		// 支付金额
		entity.setPayAmount(BigDecimal.ONE);
		// 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
		entity.setPayStatus("");
		// 余额金额
		entity.setBalanceAmount(BigDecimal.ONE);
		// 支付积分
		entity.setInterAmount(BigDecimal.ONE);
		// 网关支付金额
		entity.setGatewayAmount(BigDecimal.ONE);
		// 第三方支付流水号
		entity.setThirdPaySn("");
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
		OrderPayRecord entity = new OrderPayRecord();
		// 主键id
		entity.setId(1);
		// 支付交易号
		entity.setPayRecordNo("");
		// 业务类型(0:订单 1:余额充值)
		entity.setBizType("");
		// 订单id
		entity.setOrderId(1);
		// 支付方式(0:余额支付 1:微信支付 2:支付宝)
		entity.setPayType("");
		// 支付时间
		entity.setPayDate(new Date());
		// 支付金额
		entity.setPayAmount(BigDecimal.ONE);
		// 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
		entity.setPayStatus("");
		// 余额金额
		entity.setBalanceAmount(BigDecimal.ONE);
		// 支付积分
		entity.setInterAmount(BigDecimal.ONE);
		// 网关支付金额
		entity.setGatewayAmount(BigDecimal.ONE);
		// 第三方支付流水号
		entity.setThirdPaySn("");
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
		OrderPayRecord entity = new OrderPayRecord();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}