package com.towcent.dist.shop.portal.common.filter;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.towcent.base.dal.auth.dubbo.BaseDubboFilter;

@Component("consumerFilter")
@Activate(group = "consumer")
public class ConsumerFilter extends BaseDubboFilter implements Filter {

	@Override
	protected void preInvoke(Invoker<?> invoker, Invocation invocation) {
//		UserUtils.get
//		String merchantId = "124";
//		RpcContext.getContext().setAttachment("merchantId", merchantId); 
//		
//		logger.debug("dubbo client merchantId:{0}.", merchantId);
	}
	
	@Override
	protected void afterInvoke(Result result) {
		clear();
	}

	@Override
	protected void error(RpcException ex) {
		clear();
		super.error(ex);
	}
	
	private void clear(){
//		try {
//			RpcContext.getContext().removeAttachment("merchantId");
//		} catch (Exception e) {
//			logger.error("clearn security info from client.",e);
//		}
	}
}
