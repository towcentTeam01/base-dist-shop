package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysLogisticsCompanyMerchant;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysLogisticsCompanyMerchantMapper;
import java.util.Date;

/**
 * sys_logistics_company_merchant 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-07-11 18:40:58
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysLogisticsCompanyMerchantMapperTest extends BaseServiceTest {
	
	@Resource
	private SysLogisticsCompanyMerchantMapper mapper;
	
	@Test
	public void insertSelective() {
		SysLogisticsCompanyMerchant entity = new SysLogisticsCompanyMerchant();
		// 主键Id
		entity.setId(1);
		// 物流公司Id
		entity.setCompanyId(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 删除标记(0:正常 1:删除)
		entity.setDelFlag("");
		// 商家Id
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
		SysLogisticsCompanyMerchant entity = new SysLogisticsCompanyMerchant();
		// 主键Id
		entity.setId(1);
		// 物流公司Id
		entity.setCompanyId(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 删除标记(0:正常 1:删除)
		entity.setDelFlag("");
		// 商家Id
		entity.setMerchantId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysLogisticsCompanyMerchant entity = new SysLogisticsCompanyMerchant();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}