/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : SysCommonApiTest.java
 * @Package : com.towcent.dist.shop.app.server.sys.service
 * @date : 2018年8月5日下午12:19:30
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.sys.service;

import static com.towcent.dist.shop.common.Constant.MEMBER_JOIN_FIRST;
import static com.towcent.dist.shop.common.Constant.WX_TEMPLATE_BACK_URL;

import java.text.MessageFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.service.SysCommonApi;
import com.towcent.dist.shop.app.server.BaseServiceTest;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @ClassName: SysCommonApiTest 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年8月5日 下午12:19:30
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class SysCommonApiTest extends BaseServiceTest {

	@Resource
	private SysCommonApi commonApi;
	@Resource
	private SysPropertyService propertyService;
	@Resource
	private SysFrontAccountService accountService;
	
	
	@Test
	public void sendWxTemplateMessage() throws ServiceException, RpcException {
		SysFrontAccount account = accountService.getAccountById(225);
		WxMpTemplateMessage vo = this.assembleTemplateMssage(account, new Date());
		commonApi.sendWxTemplateMessage(0, vo);
	}

	/**
	 * 新会员加入通知<br>
	 * 装配微信公众模板消息Vo.
	 * @Title assembleTemplateMssage
	 * @param account  会员对象
	 * @param date     时间(格式化)
	 * @return
	 * @throws ServiceException 
	 */
	private WxMpTemplateMessage assembleTemplateMssage(SysFrontAccount account, Date date) throws ServiceException {
		if (StringUtils.isBlank(account.getOpenId())) return null;
		WxMpTemplateMessage vo = new WxMpTemplateMessage();
		vo.setToUser(account.getOpenId());
		vo.setTemplateId("h-IEuLO5NWjbLaNKjldnn8OC-tJE7toauvf1-4ELSPY");  // 新会员加入提醒的消息模板Id
		// 返回公众号的链接地址
		vo.setUrl(propertyService.getSysPropertyToString(account.getMerchantId(), WX_TEMPLATE_BACK_URL));
		vo.addData(new WxMpTemplateData("first", MessageFormat.format(MEMBER_JOIN_FIRST, account.getNickName())));
		vo.addData(new WxMpTemplateData("keyword1", account.getJobNo()));
		vo.addData(new WxMpTemplateData("keyword2", DateUtils.formatDateTime(date)));
		return vo;
	}
}

	