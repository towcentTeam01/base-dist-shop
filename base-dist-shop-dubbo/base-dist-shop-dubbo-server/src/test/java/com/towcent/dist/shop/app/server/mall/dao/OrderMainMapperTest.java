package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.OrderMainMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order_main 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
public class OrderMainMapperTest extends BaseServiceTest {
	
	@Resource
	private OrderMainMapper mapper;
	
	@Test
	public void insertSelective() {
		OrderMain entity = new OrderMain();
		// 主键id
		entity.setId(1);
		// 订单类型(0:普通订单)
		entity.setOrderType("");
		// 订单号
		entity.setOrderNo("");
		// 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
		entity.setOrderStatus("");
		// 支付状态(0:未支付 1:已支付)
		entity.setPayStatus("");
		// 付款方式(1:在线支付 2:线下付款)
		entity.setPayWay("");
		// 总金额(商品总金额-优惠金额+运费)
		entity.setTotalAmount(BigDecimal.ONE);
		// 余额支付金额
		entity.setAmount(BigDecimal.ONE);
		// 线上实付金额(总)
		entity.setPayAmount(BigDecimal.ONE);
		// 积分支付数额
		entity.setPayInter(BigDecimal.ONE);
		// 优惠金额
		entity.setCouponAmount(BigDecimal.ONE);
		// 收货人姓名
		entity.setConsigneeName("");
		// 收货人联系方式(手机或者电话至少填一项)
		entity.setMobilePhone("");
		// 收货详细地址(收货地址)
		entity.setConsigneeAddr("");
		// 商品总数量
		entity.setTotalQty(1);
		// 运费
		entity.setFreightFee(BigDecimal.ONE);
		// 运单号
		entity.setFreightNumber("");
		// 物流公司id
		entity.setLogisticsNo("");
		// 物流公司名称
		entity.setLogisticsName("");
		// 是否评论(1:是0:否)
		entity.setIsEval("");
		// 售后备注
		entity.setSaleAfterRemarks("");
		// 售后申请时间
		entity.setSaleAfterDate(new Date());
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
		// 支付时间
		entity.setPayDate(new Date());
		// 发货时间
		entity.setDeliveryTime(new Date());
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
		OrderMain entity = new OrderMain();
		// 主键id
		entity.setId(1);
		// 订单类型(0:普通订单)
		entity.setOrderType("");
		// 订单号
		entity.setOrderNo("");
		// 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
		entity.setOrderStatus("");
		// 支付状态(0:未支付 1:已支付)
		entity.setPayStatus("");
		// 付款方式(1:在线支付 2:线下付款)
		entity.setPayWay("");
		// 总金额(商品总金额-优惠金额+运费)
		entity.setTotalAmount(BigDecimal.ONE);
		// 余额支付金额
		entity.setAmount(BigDecimal.ONE);
		// 线上实付金额(总)
		entity.setPayAmount(BigDecimal.ONE);
		// 积分支付数额
		entity.setPayInter(BigDecimal.ONE);
		// 优惠金额
		entity.setCouponAmount(BigDecimal.ONE);
		// 收货人姓名
		entity.setConsigneeName("");
		// 收货人联系方式(手机或者电话至少填一项)
		entity.setMobilePhone("");
		// 收货详细地址(收货地址)
		entity.setConsigneeAddr("");
		// 商品总数量
		entity.setTotalQty(1);
		// 运费
		entity.setFreightFee(BigDecimal.ONE);
		// 运单号
		entity.setFreightNumber("");
		// 物流公司id
		entity.setLogisticsNo("");
		// 物流公司名称
		entity.setLogisticsName("");
		// 是否评论(1:是0:否)
		entity.setIsEval("");
		// 售后备注
		entity.setSaleAfterRemarks("");
		// 售后申请时间
		entity.setSaleAfterDate(new Date());
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
		// 支付时间
		entity.setPayDate(new Date());
		// 发货时间
		entity.setDeliveryTime(new Date());
		// 商户id
		entity.setMerchantId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		OrderMain entity = new OrderMain();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}