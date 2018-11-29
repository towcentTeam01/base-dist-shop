package com.towcent.dist.shop.app.server.mall.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.mall.dto.GoodsChannel;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.mall.dao.GoodsChannelMapper;
import java.util.Date;

/**
 * goods_channel 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:08
 * @version 1.0
 * @copyright facegarden.com
 */
public class GoodsChannelMapperTest extends BaseServiceTest {
	
	@Resource
	private GoodsChannelMapper mapper;
	
	@Test
	public void insertSelective() {
		GoodsChannel entity = new GoodsChannel();
		// 主键id
		entity.setId(1);
		// 频道名称
		entity.setChannelName("");
		// 频道类型(1:频道商品 2:商品 3:广告) channel_type
		entity.setChannelType("");
		// 频道别名(eg. 热门(hot))
		entity.setChannelAlias("");
		// 频道商品数
		entity.setGoodsQty(1);
		// 是否开启(0:否 1:是) yes_no
		entity.setChannelStatus("");
		// 排序
		entity.setSort((short) 1);
		// 开始时间
		entity.setStartTime(new Date());
		// 结束时间
		entity.setEndTime(new Date());
		// 频道图片
		entity.setChannelImg("");
		// 链接
		entity.setChannelUrl("");
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
		GoodsChannel entity = new GoodsChannel();
		// 主键id
		entity.setId(1);
		// 频道名称
		entity.setChannelName("");
		// 频道类型(1:频道商品 2:商品 3:广告) channel_type
		entity.setChannelType("");
		// 频道别名(eg. 热门(hot))
		entity.setChannelAlias("");
		// 频道商品数
		entity.setGoodsQty(1);
		// 是否开启(0:否 1:是) yes_no
		entity.setChannelStatus("");
		// 排序
		entity.setSort((short) 1);
		// 开始时间
		entity.setStartTime(new Date());
		// 结束时间
		entity.setEndTime(new Date());
		// 频道图片
		entity.setChannelImg("");
		// 链接
		entity.setChannelUrl("");
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
		GoodsChannel entity = new GoodsChannel();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}