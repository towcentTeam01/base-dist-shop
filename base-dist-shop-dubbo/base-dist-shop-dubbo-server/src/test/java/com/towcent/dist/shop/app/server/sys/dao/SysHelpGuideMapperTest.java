package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysHelpGuide;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysHelpGuideMapper;
import java.util.Date;

/**
 * sys_help_guide 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-07-02 11:30:33
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysHelpGuideMapperTest extends BaseServiceTest {
	
	@Resource
	private SysHelpGuideMapper mapper;
	
	@Test
	public void insertSelective() {
		SysHelpGuide entity = new SysHelpGuide();
		// 主键Id
		entity.setId(1);
		// 教程类别(1:系统界面)
		entity.setGuideType("");
		// 指南标题
		entity.setTitle("");
		// 指南图片地址
		entity.setPicUrl("");
		// 排序号
		entity.setSort(1);
		// 创建人
		entity.setCreateBy(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 更新人
		entity.setUpdateBy(1);
		// 更新时间
		entity.setUpdateDate(new Date());
		// 删除标识(0:正常 1:删除)
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
		SysHelpGuide entity = new SysHelpGuide();
		// 主键Id
		entity.setId(1);
		// 教程类别(1:系统界面)
		entity.setGuideType("");
		// 指南标题
		entity.setTitle("");
		// 指南图片地址
		entity.setPicUrl("");
		// 排序号
		entity.setSort(1);
		// 创建人
		entity.setCreateBy(1);
		// 创建时间
		entity.setCreateDate(new Date());
		// 更新人
		entity.setUpdateBy(1);
		// 更新时间
		entity.setUpdateDate(new Date());
		// 删除标识(0:正常 1:删除)
		entity.setDelFlag("");
		// 商户Id
		entity.setMerchantId(1);
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysHelpGuide entity = new SysHelpGuide();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}