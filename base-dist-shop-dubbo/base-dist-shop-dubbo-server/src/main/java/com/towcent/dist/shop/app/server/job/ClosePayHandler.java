package com.towcent.dist.shop.app.server.job;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.dist.shop.common.Constant.PAY_STATUS_0;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.mall.dto.OrderPayRecord;
import com.towcent.dist.shop.app.client.mall.sevice.PayApi;
import com.towcent.dist.shop.app.client.sys.dto.SysMerchantInfo;
import com.towcent.dist.shop.app.server.mall.service.OrderPayRecordService;
import com.towcent.dist.shop.app.server.sys.service.SysMerchantInfoService;
import com.towcent.dist.shop.common.Constant;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 关闭超时未支付的订单定时任务
 * 
 * 开发步骤：
 * 1、继承 “IJobHandler” ；
 * 2、装配到Spring，例如加 “@Service” 注解；
 * 3、加 “@JobHander” 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割;
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 * 
 */
@JobHander(value="closePayJobHandler")
@Service
public class ClosePayHandler extends IJobHandler {
	@Resource
	private SysMerchantInfoService merchantService;
	@Resource
	private PayApi payApi;
	@Resource
	private OrderPayRecordService orderPayRecordService;
	@Resource
	private SysPropertyService sysPropertyService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		XxlJobLogger.log("定时扫描超时未支付的交易...");
		
		Map<String, Object> merchantParams = Maps.newHashMap();
		merchantParams.put("delFlag", Constant.DEL_FLAG_0);
		merchantParams.put("enabledFlag", "1");
		List<SysMerchantInfo> merchantList = merchantService.findByBiz(merchantParams);
		
		if (!CollectionUtils.isEmpty(merchantList)) {
			for (SysMerchantInfo merchantInfo : merchantList) {
				// 订单超时未支付关闭时间(分钟)
				Integer time = sysPropertyService.getSysPropertyToInt(merchantInfo.getId(), "order_close_time");
				if (null == time) {
					continue;
				}
				
				Map<String, Object> mapParams = Maps.newHashMap();
				mapParams.put("payStatus", PAY_STATUS_0);
				// 当前时间往前推30分钟
				mapParams.put("createDate30", DateUtils.getDateDiff(new Date(), 30));
		        mapParams.put("orderIdNotNull", true);
				mapParams.put("delFlag", DEL_FLAG_0);
				List<OrderPayRecord> payRecords = orderPayRecordService.findByBiz(mapParams);
				int index = 0;
		        XxlJobLogger.log(MessageFormat.format("商户:{0}, 本次一共扫描到{1}个超时未支付交易!", merchantInfo.getShopName(), payRecords.size()));
				if (!CollectionUtils.isEmpty(payRecords)) {
					for (OrderPayRecord payRecord : payRecords) {
					    index++;
		                XxlJobLogger.log(MessageFormat.format("商户:{0}, 正在关闭第{1}个订单,订单id:{2},交易号:{3}",
		                		merchantInfo.getShopName(),
		                        index,
		                        payRecord.getOrderId(),
		                        payRecord.getPayRecordNo()));
		                
						if (payApi.closeTrade(payRecord.getOrderId(), true)) {
		                    myLogger(merchantInfo.getShopName(), index, payRecord.getOrderId(), payRecord.getPayRecordNo(), "关闭成功！");
		                } else {
		                    myLogger(merchantInfo.getShopName(), index, payRecord.getOrderId(), payRecord.getPayRecordNo(), "关闭失败！");
		                }
					}
					index = 0;
				}
			}
		}		
		
		XxlJobLogger.log("定时扫描超时未支付的交易...end");
		return ReturnT.SUCCESS;
	}

	private void myLogger(String merchantName, int index, int orderId, String orderNo, String message) {
        XxlJobLogger.log(MessageFormat.format("商户:{0}, 第{1}个订单, 订单id:{2}, 交易号:{3}, {4}",
                index,
                orderId,
                orderNo,
                message));
    }

	
}