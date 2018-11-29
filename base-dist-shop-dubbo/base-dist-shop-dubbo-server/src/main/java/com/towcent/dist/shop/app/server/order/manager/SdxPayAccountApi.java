package com.towcent.dist.shop.app.server.order.manager;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.dist.shop.app.server.order.pay.SdxPayResponse;
import com.towcent.dist.shop.app.server.order.vo.PayCallVo;

/**
 * Created by huangtao on 2017/6/29.
 */
public interface SdxPayAccountApi {

    /**
     *  获取支付响应
     * @param id 账户id
     * @return
     */
	SdxPayResponse getPayResponse(Integer merchantId, Integer id);

	/**
	 * @Title: finishPayCall
	 * @Description: 完成支付，处理业务逻辑.
	 * @param payCallVo
	 * @return
	 * @throws ServiceException
	 * @return: boolean
	 */
	boolean finishPayCall(PayCallVo payCallVo) throws ServiceException, RpcException;
}
