package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysIntegralRecord;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysIntegralRecordMapper;
import java.util.Date;

/**
 * sys_integral_record 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-25 16:00:12
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysIntegralRecordMapperTest extends BaseServiceTest {
	
	@Resource
	private SysIntegralRecordMapper mapper;
	
	@Test
	public void insertSelective() {
		SysIntegralRecord entity = new SysIntegralRecord();
		// 主键id
		entity.setId(1);
		// 用户id
		entity.setUserId(1);
		// 交易号
		entity.setDealNo("");
		// 类型(0:支出，1:收入)
		entity.setType("");
		// 此次交易积分
		entity.setIntegral(1);
		// 此次流水之后的积分余额（暂时不适用）
		entity.setIntegralAfter(1);
		// 备注
		entity.setRemarks("");
		// 发生的时间
		entity.setCreateDate(new Date());
		// 商户id
		entity.setMerchantId(1);
		// 删除标记(0:正常1:删除)
		entity.setDelFlag("");
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
		SysIntegralRecord entity = new SysIntegralRecord();
		// 主键id
		entity.setId(1);
		// 用户id
		entity.setUserId(1);
		// 交易号
		entity.setDealNo("");
		// 类型(0:支出，1:收入)
		entity.setType("");
		// 此次交易积分
		entity.setIntegral(1);
		// 此次流水之后的积分余额（暂时不适用）
		entity.setIntegralAfter(1);
		// 备注
		entity.setRemarks("");
		// 发生的时间
		entity.setCreateDate(new Date());
		// 商户id
		entity.setMerchantId(1);
		// 删除标记(0:正常1:删除)
		entity.setDelFlag("");
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysIntegralRecord entity = new SysIntegralRecord();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}