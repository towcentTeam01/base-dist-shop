package com.towcent.dist.shop.app.server.order.pay.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.PayMessage;
import com.egzosn.pay.common.bean.PayOutMessage;
import com.egzosn.pay.common.exception.PayErrorException;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.common.utils.SpringContextHolder;
import com.towcent.base.pay.service.handler.BasePayMessageHandler;
import com.towcent.dist.shop.app.server.order.manager.SdxPayAccountApi;
import com.towcent.dist.shop.app.server.order.vo.PayCallVo;

/**
 * 微信支付回调处理器
 */
public class SdxWxPayMessageHandler extends BasePayMessageHandler {

	protected Logger logger = LoggerFactory.getLogger(getClass());


    public SdxWxPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
        Map<String, Object> messageMap = payMessage.getPayMessage();
    	//交易状态
        if ("SUCCESS".equals(messageMap.get("result_code"))){
        	SdxPayAccountApi sdxPayAccountApi = SpringContextHolder.getBean(SdxPayAccountApi.class);
            // 这里进行成功的处理
        	PayCallVo payCallVo = new PayCallVo();
        	payCallVo.setPayDate(DateUtils.parseDate(messageMap.get("time_end")));
        	payCallVo.setPayRecordNo((String) messageMap.get("out_trade_no"));
        	payCallVo.setThirdPaySn((String) messageMap.get("transaction_id"));
        	try {
				boolean result = sdxPayAccountApi.finishPayCall(payCallVo);
				if (result) {
					logger.info("微信支付成功回调处理完成! out_trade_no:{}", payCallVo.getPayRecordNo());
					return  payService.getPayOutMessage("SUCCESS", "OK");
				}
			} catch (ServiceException e) {
				logger.error("处理支付完成逻辑异常.", e);
			} catch (RpcException e) {
				logger.error("处理支付完成逻辑异常.", e);
			}
        }
        
        logger.error("微信支付成功回调处理失败! out_trade_no:{}", messageMap.get("out_trade_no"));
        return  payService.getPayOutMessage("FAIL", "失败");
    }
}
