package com.towcent.dist.shop.portal.client.me;

import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrDelIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrSaveIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrListIn;
import java.io.IOException;

import org.junit.Test;

import com.towcent.dist.shop.portal.client.BaseAppTest;

public class ConsigneeAddrControllerTest extends BaseAppTest {

	static {
		descMap.put("me/consigneeAddr/del", "删除收货地址管理");
		descMap.put("me/consigneeAddr/save", "新增/修改收货地址管理");
		descMap.put("me/consigneeAddr/list", "收货地址管理");
		
	}

	@Test
	public void list() throws IOException {
		String path = "me/consigneeAddr/list";
		ConsigneeAddrListIn paramVo = new ConsigneeAddrListIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setPageNo(1);
		paramVo.setPageSize(10);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void save() throws IOException {
		String path = "me/consigneeAddr/save";
		ConsigneeAddrSaveIn paramVo = new ConsigneeAddrSaveIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		//paramVo.setId(1);
		paramVo.setConsigneeName("李四");
		paramVo.setMobilePhone("13838389438");
		paramVo.setTelephone("13838389438");
		paramVo.setProvince(110000);
		paramVo.setCity(110100);
		paramVo.setDistrict(110101);
		paramVo.setDetailAddr("天安门广场");
		paramVo.setAddress("北京市东城区天安门广场");
		paramVo.setDefaultFlag("1");
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}

	@Test
	public void del() throws IOException {
		String path = "me/consigneeAddr/del";
		ConsigneeAddrDelIn paramVo = new ConsigneeAddrDelIn();
		this.setCommonParam(paramVo);
		this.setLoginFlag(paramVo);
		paramVo.setId(1);
		String content = sendHttp(paramVo, path);
		System.out.println(content);
	}
}