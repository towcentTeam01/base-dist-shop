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
 * 支付宝支付回调处理器
 *
 */
public class SdxAliPayMessageHandler extends BasePayMessageHandler {

	protected Logger logger = LoggerFactory.getLogger(getClass());

    public SdxAliPayMessageHandler(Integer payId) {
        super(payId);
    }

    @Override
    public PayOutMessage handle(PayMessage payMessage, Map<String, Object> context, PayService payService) throws PayErrorException {
    	Map<String, Object> messageMap = payMessage.getPayMessage();
    	//交易状态
        String trade_status = (String) messageMap.get("trade_status");
    	//交易状态
        if ("TRADE_SUCCESS".equals(trade_status) || "TRADE_FINISHED".equals(trade_status)) {
        	SdxPayAccountApi sdxPayAccountApi = SpringContextHolder.getBean(SdxPayAccountApi.class);
            // 这里进行成功的处理
        	PayCallVo payCallVo = new PayCallVo();
        	payCallVo.setPayDate(DateUtils.parseDate(messageMap.get("gmt_payment")));
        	payCallVo.setPayRecordNo((String) messageMap.get("out_trade_no"));
        	payCallVo.setThirdPaySn((String) messageMap.get("trade_no"));
        	try {
				boolean result = sdxPayAccountApi.finishPayCall(payCallVo);
				if (result) {
					logger.info("支付宝支付成功回调处理完成! out_trade_no:{}", payCallVo.getPayRecordNo());
					return  payService.getPayOutMessage("success", "OK");
				}
			} catch (ServiceException e) {
				logger.error("处理支付完成逻辑异常.", e);
			} catch (RpcException e) {
				logger.error("处理支付完成逻辑异常.", e);
			}
        }
        
        logger.error("支付宝支付成功回调处理失败! out_trade_no:{}", messageMap.get("out_trade_no"));
        return  payService.getPayOutMessage("fail", "失败");
    }
}
