package com.towcent.dist.shop.web.common;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.model.SmsParamDto;
import com.towcent.base.manager.SmsNotifyApi;
import com.towcent.dist.shop.web.BaseServiceTest;

public class SmsTest extends BaseServiceTest {
	
	@Resource
	private SmsNotifyApi notifyApi;
	
	@Test
	public void testSmsSend() throws RpcException {
		SmsParamDto param = new SmsParamDto();
		param.setMobile("18666296035");
		param.setSmsType((byte) 1);
		// param.setSmsParams(smsParams);
		notifyApi.sendSms(param);
	}
	
}