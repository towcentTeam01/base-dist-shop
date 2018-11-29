package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysAmountRecord;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysAmountRecordMapper;
import java.math.BigDecimal;
import java.util.Date;

/**
 * sys_amount_record 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-25 15:56:30
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysAmountRecordMapperTest extends BaseServiceTest {
	
	@Resource
	private SysAmountRecordMapper mapper;
	
	@Test
	public void insertSelective() {
		SysAmountRecord entity = new SysAmountRecord();
		// 主键id
		entity.setId(1);
		// 用户id
		entity.setUserId(1);
		// 交易号
		entity.setDealNo("");
		// 交易去向(作废)
		entity.setDirection("");
		// 类型(0:支出，1:收入)
		entity.setType("");
		// 此次交易金额
		entity.setAmount(BigDecimal.ONE);
		// 此次流水之后的余额（暂时不适用）
		entity.setAmountAfter(BigDecimal.ONE);
		// 订单金额
		entity.setOrderAmount(BigDecimal.ONE);
		// 订单title(默认商品名称)
		entity.setOrderTitle("");
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
		SysAmountRecord entity = new SysAmountRecord();
		// 主键id
		entity.setId(1);
		// 用户id
		entity.setUserId(1);
		// 交易号
		entity.setDealNo("");
		// 交易去向(作废)
		entity.setDirection("");
		// 类型(0:支出，1:收入)
		entity.setType("");
		// 此次交易金额
		entity.setAmount(BigDecimal.ONE);
		// 此次流水之后的余额（暂时不适用）
		entity.setAmountAfter(BigDecimal.ONE);
		// 订单金额
		entity.setOrderAmount(BigDecimal.ONE);
		// 订单title(默认商品名称)
		entity.setOrderTitle("");
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
		SysAmountRecord entity = new SysAmountRecord();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}