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
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.sevice.OrderApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysMerchantInfo;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.app.server.sys.service.SysMerchantInfoService;
import com.towcent.dist.shop.common.Constant;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;

/**
 * 确认收货定时任务
 * 
 * 开发步骤：
 * 1、继承 “IJobHandler” ；
 * 2、装配到Spring，例如加 “@Service” 注解；
 * 3、加 “@JobHander” 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割;
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 * 
 */
@JobHander(value="confirmReceiptHandler")
@Service
public class ConfirmReceiptHandler extends IJobHandler {
	@Resource
	private SysMerchantInfoService merchantService;
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private OrderApi orderApi;
	@Resource
	private SysFrontAccountService frontAccountService;
	@Resource
	private SysPropertyService sysPropertyService;
	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		XxlJobLogger.log("定时扫描超时需要自动确认收货的订单...");
		
		Map<String, Object> merchantParams = Maps.newHashMap();
		merchantParams.put("delFlag", Constant.DEL_FLAG_0);
		merchantParams.put("enabledFlag", "1");
		List<SysMerchantInfo> merchantList = merchantService.findByBiz(merchantParams);
		
		if (!CollectionUtils.isEmpty(merchantList)) {
			for (SysMerchantInfo merchantInfo : merchantList) {
				// 自动确认收货的时间（分钟）
				Integer time = sysPropertyService.getSysPropertyToInt(merchantInfo.getId(), "order_confirm_time");
				if (null == time) {
					continue;
				}
				
				Map<String, Object> mapParams = Maps.newHashMap();
				mapParams.put("payStatus", PAY_STATUS_0);
				mapParams.put("orderStatus", Constant.ORDER_STATUS_4);
				// 当前时间往前推分钟
				mapParams.put("createDate30", DateUtils.getDateDiff(new Date(), time));
				mapParams.put("delFlag", DEL_FLAG_0);
				List<OrderMain> orderMains = orderMainService.findByBiz(mapParams);
				XxlJobLogger.log(MessageFormat.format("商户:{0}, 本次一共扫描到{1}个超时未确认收货的订单!", merchantInfo.getShopName(), orderMains.size()));
				if (!CollectionUtils.isEmpty(orderMains)) {
					int count = 1;
					for (OrderMain orderMain : orderMains) {
						SysFrontAccount account = frontAccountService.getAccountById(orderMain.getCreateBy());
						boolean result = orderApi.orderReceipt(orderMain.getId(), account);
						if (result) {
							this.printLogger(merchantInfo, count, orderMain, "确认订单成功");
						} else {
							this.printLogger(merchantInfo, count, orderMain, "确认订单失败");
						}
						count++;
					}
				}
			}
		}
		
		XxlJobLogger.log("定时扫描超时需要自动确认收货的订单...end");
		return ReturnT.SUCCESS;
	}

	private void printLogger(SysMerchantInfo merchantInfo, int count, OrderMain order, String message) {
        XxlJobLogger.log(MessageFormat.format("商户:{0}, 确认收货第{1}个订单, 订单号:{2}, {3}",
                merchantInfo.getShopName(),
                count,
                order.getOrderNo(), message));
    }

	
}