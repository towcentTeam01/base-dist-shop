package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.CouponClaimMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * coupon_claim 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public class CouponClaimMapperTest extends BaseServiceTest {
	
	@Resource
	private CouponClaimMapper mapper;
	
	@Test
	public void insertSelective() {
		CouponClaim entity = new CouponClaim();
		// 主键id
		entity.setId(1);
		// 活动id
		entity.setActId(1);
		// 会员id
		entity.setUserId(1);
		// 会员昵称
		entity.setNickName("");
		// 满减金额
		entity.setLimitAmount(BigDecimal.ONE);
		// 优惠券金额/折扣金额
		entity.setAmount(BigDecimal.ONE);
		// 使用状态(0:未使用 1:已使用) use_flag
		entity.setUseFlag("");
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
		CouponClaim entity = new CouponClaim();
		// 主键id
		entity.setId(1);
		// 活动id
		entity.setActId(1);
		// 会员id
		entity.setUserId(1);
		// 会员昵称
		entity.setNickName("");
		// 满减金额
		entity.setLimitAmount(BigDecimal.ONE);
		// 优惠券金额/折扣金额
		entity.setAmount(BigDecimal.ONE);
		// 使用状态(0:未使用 1:已使用) use_flag
		entity.setUseFlag("");
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
		CouponClaim entity = new CouponClaim();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}