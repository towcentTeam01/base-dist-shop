package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysWithdrawApply;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysWithdrawApplyMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sys_withdraw_apply 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-30 00:10:57
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysWithdrawApplyMapperTest extends BaseServiceTest {
	
	@Resource
	private SysWithdrawApplyMapper mapper;
	
	@Test
	public void insertSelective() {
		SysWithdrawApply entity = new SysWithdrawApply();
		// 主键Id
		entity.setId(1);
		// 提现金额
		entity.setAmount(BigDecimal.ONE);
		// 提现状态(0:已申请 1:处理中 2:已处理)
		entity.setStatus("");
		// 备注
		entity.setRemarks("");
		// 创建人(申请人)
		entity.setCreateBy(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 更新人
		entity.setUpdateBy(1);
		// 更新时间
		entity.setUpdateDate(new Date());
		// 删除标记(0:正常 1:删除)
		entity.setDelFlag("");
		// 商户Id
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
		SysWithdrawApply entity = new SysWithdrawApply();
		// 主键Id
		entity.setId(1);
		// 提现金额
		entity.setAmount(BigDecimal.ONE);
		// 提现状态(0:已申请 1:处理中 2:已处理)
		entity.setStatus("");
		// 备注
		entity.setRemarks("");
		// 创建人(申请人)
		entity.setCreateBy(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 更新人
		entity.setUpdateBy(1);
		// 更新时间
		entity.setUpdateDate(new Date());
		// 删除标记(0:正常 1:删除)
		entity.setDelFlag("");
		// 商户Id
		entity.setMerchantId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysWithdrawApply entity = new SysWithdrawApply();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}