package com.towcent.dist.shop.app.server.sys.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.dist.shop.app.client.sys.dto.SysMerchantInfo;
import com.towcent.dist.shop.app.server.BaseServiceTest;
import com.towcent.dist.shop.app.server.sys.dao.SysMerchantInfoMapper;
import java.util.Date;

/**
 * sys_merchant_info 数据库操作接口测试用例
 * 
 * @author huangtao
 * @date 2018-07-05 18:00:37
 * @version 1.0
 * @copyright facegarden.com
 */
public class SysMerchantInfoMapperTest extends BaseServiceTest {
	
	@Resource
	private SysMerchantInfoMapper mapper;
	
	@Test
	public void insertSelective() {
		SysMerchantInfo entity = new SysMerchantInfo();
		// 主键id
		entity.setId(1);
		// 系统账号
		entity.setSysUserId(1);
		// 店铺编码
		entity.setHouseNumber("");
		// 店铺名称
		entity.setShopName("");
		// 店铺类别(1:实体店 2:店铺)
		entity.setShopType("");
		// 状态(1:待审核 2:审核通过 3:审核拒绝)
		entity.setApplyStatus("");
		// 联系电话
		entity.setPhone("");
		// 商家logo
		entity.setLogo("");
		// 省(开店地址)
		entity.setProvince(1);
		// 市(开店地址)
		entity.setCity(1);
		// 区(开店地址)
		entity.setDistrict(1);
		// 街道
		entity.setStreet("");
		// 详细地址
		entity.setAddress("");
		// 经度
		entity.setLongtitude("");
		// 纬度
		entity.setLatitude("");
		// 邮箱（联系人）
		entity.setEmail("");
		// qq号码（联系人）
		entity.setQq("");
		// 微信号码（联系人）
		entity.setWxCode("");
		// 微信二维码地址（联系人）
		entity.setWxQrCode("");
		// 客服电话
		entity.setServicePhone("");
		// 二维码地址(小店的二维码)
		entity.setQrCode("");
		// 法人姓名
		entity.setSelfEmployedName("");
		// 身份证正面
		entity.setIdCardFrontUrl("");
		// 身份证号码
		entity.setApplyIdCard("");
		// 营业执照照片地址
		entity.setBizLicUrl("");
		// 封面图
		entity.setCoverUrl("");
		// 身份证反面
		entity.setIdCardBackUrl("");
		// 手持身份证照片地址
		entity.setHandIdCardUrl("");
		// 标签(多个;分割)
		entity.setAlias("");
		// 启用标识(1:启用 0:禁用)
		entity.setEnabledFlag("");
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
		SysMerchantInfo entity = new SysMerchantInfo();
		// 主键id
		entity.setId(1);
		// 系统账号
		entity.setSysUserId(1);
		// 店铺编码
		entity.setHouseNumber("");
		// 店铺名称
		entity.setShopName("");
		// 店铺类别(1:实体店 2:店铺)
		entity.setShopType("");
		// 状态(1:待审核 2:审核通过 3:审核拒绝)
		entity.setApplyStatus("");
		// 联系电话
		entity.setPhone("");
		// 商家logo
		entity.setLogo("");
		// 省(开店地址)
		entity.setProvince(1);
		// 市(开店地址)
		entity.setCity(1);
		// 区(开店地址)
		entity.setDistrict(1);
		// 街道
		entity.setStreet("");
		// 详细地址
		entity.setAddress("");
		// 经度
		entity.setLongtitude("");
		// 纬度
		entity.setLatitude("");
		// 邮箱（联系人）
		entity.setEmail("");
		// qq号码（联系人）
		entity.setQq("");
		// 微信号码（联系人）
		entity.setWxCode("");
		// 微信二维码地址（联系人）
		entity.setWxQrCode("");
		// 客服电话
		entity.setServicePhone("");
		// 二维码地址(小店的二维码)
		entity.setQrCode("");
		// 法人姓名
		entity.setSelfEmployedName("");
		// 身份证正面
		entity.setIdCardFrontUrl("");
		// 身份证号码
		entity.setApplyIdCard("");
		// 营业执照照片地址
		entity.setBizLicUrl("");
		// 封面图
		entity.setCoverUrl("");
		// 身份证反面
		entity.setIdCardBackUrl("");
		// 手持身份证照片地址
		entity.setHandIdCardUrl("");
		// 标签(多个;分割)
		entity.setAlias("");
		// 启用标识(1:启用 0:禁用)
		entity.setEnabledFlag("");
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
		int count = mapper.updateByPrimaryKeySelective(entity);
		System.out.println(count);
	}
	
	@Test
	public void selectByPrimaryKey() {
		SysMerchantInfo entity = new SysMerchantInfo();
		entity.setId(1);
		entity = mapper.selectByPrimaryKey(entity);
		System.out.println(entity);
	}
	
}