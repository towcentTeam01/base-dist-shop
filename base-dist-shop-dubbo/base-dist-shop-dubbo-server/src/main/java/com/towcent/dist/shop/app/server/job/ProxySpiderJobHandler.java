package com.towcent.dist.shop.app.server.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.towcent.base.common.spider.SpiderThread;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;


/**
 * 代理服务器ip抓取
 * 
 * 开发步骤：
 * 1、继承 “IJobHandler” ；
 * 2、装配到Spring，例如加 “@Service” 注解；
 * 3、加 “@JobHander” 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割;
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 * 
 */
@JobHander(value="proxySpiderJobHandler")
@Service
public class ProxySpiderJobHandler extends IJobHandler {

	@Resource
	private  SpiderThread  spiderThread;
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		XxlJobLogger.log("获取代理http请求代理服务器信息开始。。。。");
		
		// 通用服务
		spiderThread.spiderThread(0);
		
		XxlJobLogger.log("获取代理http请求代理服务器信息...end");
		return ReturnT.SUCCESS;
	}
	
}