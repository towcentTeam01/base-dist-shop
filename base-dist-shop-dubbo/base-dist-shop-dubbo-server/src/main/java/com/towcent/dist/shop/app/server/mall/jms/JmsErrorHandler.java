package com.towcent.dist.shop.app.server.mall.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
public class JmsErrorHandler implements ErrorHandler {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void handleError(Throwable t) {
		logger.error("jms消息处理异常........" + t.getMessage(),t);
	}
}
