/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : sdx-logistics-dubbo-server
 * @Title : SdxPayAccountService.java
 * @Package : com.towcent.yike.shop.app.server.order.pay
 * @date : 2018年1月26日下午12:19:54
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.order.pay;

/**
 * @ClassName: SdxPayAccountService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年1月26日 下午12:19:54
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public interface SdxPayAccountService {

	/**
     *  获取支付响应
     * @param id 账户id
     * @return
     */
    SdxPayResponse getPayResponse(Integer merchantId, Integer id);
}

	