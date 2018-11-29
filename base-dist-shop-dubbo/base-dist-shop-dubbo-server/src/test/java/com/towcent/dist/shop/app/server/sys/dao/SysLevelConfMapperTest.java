package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysLevelConf;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysLevelConfMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sys_level_conf 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-28 09:35:14
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysLevelConfMapperTest extends BaseServiceTest {
	
	@Resource
	private SysLevelConfMapper mapper;
	
	@Test
	public void insertSelective() {
		SysLevelConf entity = new SysLevelConf();
		// 等级id
		entity.setId(1);
		// 会员等级别名
		entity.setLevelName("");
		// 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)
		entity.setLevel("");
		// 升级所需锁粉数
		entity.setLockFans(1);
		// 升级所需直推订单数
		entity.setRecOrder(1);
		// 升级所需费用
		entity.setPayFee(BigDecimal.ONE);
		// 是否默认等级(0:否 1:是) yes_no
		entity.setDefaultFlag("");
		// 直推佣金比例()
		entity.setDirectBkge(BigDecimal.ONE);
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
		SysLevelConf entity = new SysLevelConf();
		// 等级id
		entity.setId(1);
		// 会员等级别名
		entity.setLevelName("");
		// 用户等级(数据字典1:普通用户 2:黄金会员 3:铂金会员 4:钻石会员)
		entity.setLevel("");
		// 升级所需锁粉数
		entity.setLockFans(1);
		// 升级所需直推订单数
		entity.setRecOrder(1);
		// 升级所需费用
		entity.setPayFee(BigDecimal.ONE);
		// 是否默认等级(0:否 1:是) yes_no
		entity.setDefaultFlag("");
		// 直推佣金比例()
		entity.setDirectBkge(BigDecimal.ONE);
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
		SysLevelConf entity = new SysLevelConf();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}