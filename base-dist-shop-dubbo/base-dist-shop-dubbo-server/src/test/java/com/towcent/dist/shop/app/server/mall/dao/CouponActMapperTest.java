package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.CouponAct;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.CouponActMapper;
import java.util.Date;
import java.math.BigDecimal;

/**
 * coupon_act 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public class CouponActMapperTest extends BaseServiceTest {
	
	@Resource
	private CouponActMapper mapper;
	
	@Test
	public void insertSelective() {
		CouponAct entity = new CouponAct();
		// 主键id
		entity.setId(1);
		// 活动名称
		entity.setActName("");
		// 活动开始时间
		entity.setStartTime(new Date());
		// 活动结束时间
		entity.setEndTime(new Date());
		// 类型(1:现金券 2:折扣券 3:满减券 )
		entity.setActType("");
		// 满减金额
		entity.setLimitAmount(BigDecimal.ONE);
		// 优惠券金额/折扣金额
		entity.setAmount(BigDecimal.ONE);
		// 总发行数量
		entity.setTotalQty(1);
		// 剩余数量
		entity.setResidQty(1);
		// 是否开启(0:否 1:是) yes_no
		entity.setOpenFlag("");
		// 活动页地址
		entity.setActUrl("");
		// 活动页地址二维码
		entity.setActQrCode("");
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
		CouponAct entity = new CouponAct();
		// 主键id
		entity.setId(1);
		// 活动名称
		entity.setActName("");
		// 活动开始时间
		entity.setStartTime(new Date());
		// 活动结束时间
		entity.setEndTime(new Date());
		// 类型(1:现金券 2:折扣券 3:满减券 )
		entity.setActType("");
		// 满减金额
		entity.setLimitAmount(BigDecimal.ONE);
		// 优惠券金额/折扣金额
		entity.setAmount(BigDecimal.ONE);
		// 总发行数量
		entity.setTotalQty(1);
		// 剩余数量
		entity.setResidQty(1);
		// 是否开启(0:否 1:是) yes_no
		entity.setOpenFlag("");
		// 活动页地址
		entity.setActUrl("");
		// 活动页地址二维码
		entity.setActQrCode("");
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
		CouponAct entity = new CouponAct();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}