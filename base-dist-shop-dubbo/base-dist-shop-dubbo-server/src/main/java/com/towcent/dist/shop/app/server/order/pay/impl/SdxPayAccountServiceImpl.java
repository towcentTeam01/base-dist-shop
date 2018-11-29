/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : sdx-logistics-dubbo-server
 * @Title : SdxPayAccountServiceImpl.java
 * @Package : com.towcent.yike.shop.app.server.order.pay.impl
 * @date : 2018年1月26日下午12:20:37
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.order.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.pay.entity.PayAccount;
import com.towcent.base.service.PayAccountService;
import com.towcent.dist.shop.app.server.order.pay.SdxPayAccountService;
import com.towcent.dist.shop.app.server.order.pay.SdxPayResponse;

/**
 * @ClassName: SdxPayAccountServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年1月26日 下午12:20:37
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class SdxPayAccountServiceImpl implements SdxPayAccountService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	// 缓存
    private final static Map<String, SdxPayResponse> payResponses = new HashMap<String, SdxPayResponse>();
    
    @Resource
    private AutowireCapableBeanFactory spring;
    
    @Resource
    private PayAccountService payAccountService;
    
    /**
     *  获取支付响应
     * @param id 账户id
     * @return
     * @throws ServiceException 
     */
    @Override
    public SdxPayResponse getPayResponse(Integer merchantId, Integer id) {
    	String cacheId = merchantId + "#" + id;
    	SdxPayResponse payResponse = payResponses.get(cacheId);
        try {
			if (payResponse  == null) {
				Map<String, Object> params = Maps.newHashMap();
				params.put("payId", id);
				params.put("merchantId", merchantId);
				List<PayAccount> list = payAccountService.findByBiz(params);
			    PayAccount payAccount = CollectionUtils.isEmpty(list) ? null : list.get(0);
			    if (payAccount == null) {
			        throw new IllegalArgumentException ("无法查询");
			    }
			    payResponse = new SdxPayResponse();
			    spring.autowireBean(payResponse);
			    payResponse.init(payAccount);
			    payResponses.put(cacheId, payResponse);
			    // 查询
			}
		} catch (BeansException e) {
			logger.error("", e);
		} catch (ServiceException e) {
			logger.error("", e);
		}
        return payResponse;
    }

}

	