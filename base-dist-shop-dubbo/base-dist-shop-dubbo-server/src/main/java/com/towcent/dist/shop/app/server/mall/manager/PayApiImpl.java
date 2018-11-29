/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : PayApiImpl.java
 * @Package : com.towcent.dist.shop.app.server.mall.manager
 * @date : 2018年6月24日下午6:46:12
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.mall.manager;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_0;
import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_1;
import static com.towcent.dist.shop.common.Constant.ID;
import static com.towcent.dist.shop.common.Constant.PAY_STATUS_0;
import static com.towcent.dist.shop.common.Constant.PAY_STATUS_1;
import static com.towcent.dist.shop.common.Constant.PAY_TYPE_0;
import static com.towcent.dist.shop.common.Constant.PAY_TYPE_1;
import static com.towcent.dist.shop.common.Constant.PAY_TYPE_2;
import static com.towcent.dist.shop.common.Constant.PAY_TYPE_3;
import static com.towcent.dist.shop.common.Constant.RULE_PAY_RECORD_NO;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.egzosn.pay.ali.bean.AliTransactionType;
import com.egzosn.pay.common.api.PayConfigStorage;
import com.egzosn.pay.common.bean.PayMessage;
import com.egzosn.pay.common.bean.PayOrder;
import com.egzosn.pay.common.bean.PayOutMessage;
import com.egzosn.pay.common.exception.PayErrorException;
import com.egzosn.pay.common.util.sign.SignUtils;
import com.egzosn.pay.wx.bean.WxTransactionType;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.pay.entity.PayType;
import com.towcent.base.service.BaseService;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.dto.OrderPayRecord;
import com.towcent.dist.shop.app.client.mall.sevice.PayApi;
import com.towcent.dist.shop.app.client.mall.vo.OrderPaySuccessVo;
import com.towcent.dist.shop.app.client.mall.vo.PayBalanceVo;
import com.towcent.dist.shop.app.client.mall.vo.PayOnlineVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.app.server.mall.service.OrderPayRecordService;
import com.towcent.dist.shop.app.server.order.manager.SdxPayAccountApi;
import com.towcent.dist.shop.app.server.order.pay.SdxPayResponse;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.common.ConfigUtil;
import com.towcent.dist.shop.common.Constant;

/**
 * @ClassName: PayApiImpl
 * @Description: 支付相关接口实现
 *
 * @author huangtao
 * @date 2018年6月24日 下午6:46:12
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class PayApiImpl extends BaseService implements PayApi {

	@Resource
	private OrderMainService orderMainService;
	@Resource
	private OrderPayRecordService orderPayRecordService;
	@Resource
	private SysFrontAccountService sysFrontAccountService;
	@Resource
	private SysFrontAccountApi accountApi;
	@Resource
	private BaseCommonApi baseCommonApi;
	@Resource
    private SdxPayAccountApi payAccountApi;

	@Override @Transactional
	public Map<String, Object> createPayBalParam(Integer userId, BigDecimal amount, String payType, String openId)
			throws RpcException {
		Assert.notNull(userId, "userId不能为空");
		Assert.notNull(amount, "amount不能为空");
		Assert.isNotEmpty(payType, "payType不能为空");
		// Assert.isNotEmpty(openId, "openId不能为空");
		try {
			SysFrontAccount account = accountApi.getAccountById(userId);

			Map<String, Object> orderInfoMap = null;

			PayOnlineVo payOnlineVo = new PayOnlineVo();
			payOnlineVo.setBalance(BigDecimal.ZERO);

			OrderPayRecord record = new OrderPayRecord();
			String payRecordNo = baseCommonApi.getSerialNo(0, RULE_PAY_RECORD_NO);
			record.setPayRecordNo(payRecordNo);
			// record.setOrderId(orderId);
			record.setPayType(payType);
			record.setBalanceAmount(BigDecimal.ZERO);
			record.setPayAmount(amount);
			record.setGatewayAmount(record.getPayAmount());
			record.setPayStatus(PAY_STATUS_0);
			record.setCreateBy(userId);
			record.setCreateDate(new Date());
			record.setRemarks(StringUtils.isNotBlank(openId) ? "OPENID" : "APP");
			record.setBizType(BIZ_TYPE_1);
			if (orderPayRecordService.add(record) > 0) {
				// 生成付款二维码
				//获取对应的支付账户操作工具（可根据账户id）
		        SdxPayResponse payResponse = payAccountApi.getPayResponse(account.getMerchantId(), Integer.valueOf(payType));
		        String transactionType = WxTransactionType.JSAPI.name();
		        if (PAY_TYPE_1.equals(payType) && StringUtils.isNotBlank(openId)) {
		        	// 公众号支付
		        	transactionType = WxTransactionType.JSAPI.name();
		        } else if (PAY_TYPE_3.equals(payType) && StringUtils.isBlank(openId)) {
		        	// 微信APP支付
		        	transactionType = WxTransactionType.APP.name();
		        } else if (PAY_TYPE_2.equals(payType)) {
		        	// 支付宝APP支付
		        	transactionType = AliTransactionType.APP.name();
		        }

		        // 真实支付金额(如果当前为测试环境，则临时修改网关支付金额)
		        BigDecimal realPayAmount = ConfigUtil.getPayTestFlag() ? new BigDecimal("0.01") : record.getPayAmount();
		        String subject = "充值余额" + (ConfigUtil.getPayTestFlag() ? "(测试)" : "");
		        PayOrder pOrder = new PayOrder(subject, "摘要", realPayAmount, record.getPayRecordNo(), PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
		        if (StringUtils.isNotBlank(openId)) {
		        	pOrder.setOpenid(openId);
		        }

		        orderInfoMap = payResponse.getService().orderInfo(pOrder);
		        orderInfoMap.put("signData", getOrderInfo(orderInfoMap, payType));
		        orderInfoMap.put("payRecordNo", payRecordNo);
			}
			return orderInfoMap;
		} catch (Exception e) {
			logger.error("生成充值交易记录（APP/公众号支付）", e);
			throw new RpcException("", "生成充值交易记录（APP/公众号支付）",e);
		}
	}

	@Override
	public boolean queryPayBalanceStatus(Integer userId, String payRecordNo) throws RpcException {
		Assert.notNull(userId, "userId不能为空");
		Assert.isNotEmpty(payRecordNo, "payRecordNo不能为空");
		try {
			OrderPayRecord record = orderPayRecordService.findByKeyValSingle("payRecordNo", payRecordNo);
			if (null == record || !userId.equals(record.getCreateBy())) {
				return false;
			}
			return StringUtils.equals(record.getPayStatus(), PAY_STATUS_1);
		} catch (ServiceException e) {
			logger.error("查询充值余额支付状态失败", e);
			throw new RpcException("", "查询充值余额支付状态失败",e);
		}
	}

	@Override @Transactional
	public PayBalanceVo balancePay(Integer userId, Integer orderId, String tradePassword) throws RpcException {
		try {
			PayBalanceVo payBalanceVo = new PayBalanceVo();
			boolean result = false;
			BigDecimal payBal = BigDecimal.ZERO;

			SysFrontAccount account = accountApi.getAccountById(userId);
            // 交易验证密码
			if (!StringUtils.endsWithIgnoreCase(tradePassword,
					account.getTradePassword()) && StringUtils.isNotEmpty(tradePassword)) {
				payBalanceVo.setResult(result);
				payBalanceVo.setErrorMsg("交易密码不正确");
				return payBalanceVo;
			}

			// 获取账户余额
			BigDecimal balance = account.getAmount();
			OrderMain orderMain = orderMainService.findByKeyValSingle(ID, orderId);
			if (orderMain.getTotalAmount().doubleValue() <= balance.doubleValue()) {
				payBal = orderMain.getTotalAmount();
			} else {
				payBalanceVo.setResult(result);
				payBalanceVo.setErrorMsg("余额不足");
				return payBalanceVo;
			}

			OrderPayRecord record = new OrderPayRecord();
			String payRecordNo = baseCommonApi.getSerialNo(0, RULE_PAY_RECORD_NO);
			record.setPayRecordNo(payRecordNo);
			record.setPayType(PAY_TYPE_0);
			record.setBalanceAmount(payBal);
			record.setPayAmount(BigDecimal.ZERO);
			record.setGatewayAmount(BigDecimal.ZERO);
			record.setPayStatus(PAY_STATUS_1);
			record.setOrderId(orderId);
			record.setCreateBy(userId);
			record.setCreateDate(new Date());
			record.setPayDate(record.getCreateDate());
			record.setRemarks("BALANCE");
			record.setBizType(BIZ_TYPE_0);
			if (orderPayRecordService.add(record) > 0) {
				// 扣减余额
				payBalanceVo.setResult(sysFrontAccountService.discountAmount(userId, payBal, payRecordNo, false));
			}
			OrderPaySuccessVo orderPaySuccessVo = new OrderPaySuccessVo();
			orderPaySuccessVo.setOrderId(orderId);
			orderPaySuccessVo.setOutBizNo(payRecordNo);
			orderPaySuccessVo.setPayAmount(payBal);
			orderPaySuccessVo.setRemark("余额全额支付");
			orderPaySuccessVo.setPayType(PAY_TYPE_0);

			orderMain.setPayDate(new Date());
			orderMain.setPayStatus(Constant.PAY_STATUS_1);
			orderMainService.modifyById(orderMain);
			return payBalanceVo;
		} catch (ServiceException e) {
			logger.error("生成订单交易记录失败", e);
			throw new RpcException("", "生成订单交易记录失败",e);
		}
	}

	@Override @Transactional
	public Map<String, Object> createPayParam(Integer userId, Integer orderId, String payType, String openId)
			throws RpcException {
		Assert.notNull(userId, "userId不能为空");
		Assert.notNull(orderId, "orderId不能为空");
		Assert.isNotEmpty(payType, "payType不能为空");
		try {
			SysFrontAccount account = accountApi.getAccountById(userId);

			Map<String, Object> orderInfoMap = null;

			BigDecimal payBal = BigDecimal.ZERO;
			// 获取账户余额
			BigDecimal balance = account.getAmount();
			OrderMain orderMain = orderMainService.findByKeyValSingle(ID, orderId);
			if (orderMain.getTotalAmount().doubleValue() > balance.doubleValue()) {
				payBal = balance;
			}

			// 判断订单的支付状态   (已支付的订单不能进行付款操作)
			if (StringUtils.endsWith(PAY_STATUS_1, orderMain.getPayStatus())) {
				throw new RpcException("", "已支付的订单不能进行付款操作");
			}

			OrderPayRecord record = new OrderPayRecord();
			String payRecordNo = baseCommonApi.getSerialNo(0, RULE_PAY_RECORD_NO);
			record.setPayRecordNo(payRecordNo);
			record.setPayType(payType);
			record.setBalanceAmount(payBal);
			record.setPayAmount(orderMain.getTotalAmount().subtract(payBal));
			record.setGatewayAmount(record.getPayAmount());
			record.setPayStatus(PAY_STATUS_0);
			record.setOrderId(orderId);
			record.setCreateBy(userId);
			record.setCreateDate(new Date());
			record.setRemarks(StringUtils.isNotBlank(openId) ? "OPENID" : "APP");
			record.setBizType(BIZ_TYPE_0);
			if (orderPayRecordService.add(record) > 0) {
				//获取对应的支付账户操作工具（可根据账户id）
		        SdxPayResponse payResponse = payAccountApi.getPayResponse(account.getMerchantId(), Integer.valueOf(payType));
		        String transactionType = WxTransactionType.JSAPI.name();
		        if (PAY_TYPE_1.equals(payType) && StringUtils.isNotBlank(openId)) {
		        	// 公众号支付
		        	transactionType = WxTransactionType.JSAPI.name();
		        } else if (PAY_TYPE_3.equals(payType) && StringUtils.isBlank(openId)) {
		        	// 微信APP支付
		        	transactionType = WxTransactionType.APP.name();
		        } else if (PAY_TYPE_2.equals(payType)) {
		        	// 支付宝APP支付
		        	transactionType = AliTransactionType.APP.name();
		        }

		        // 真实支付金额(如果当前为测试环境，则临时修改网关支付金额)
		        BigDecimal realPayAmount = ConfigUtil.getPayTestFlag() ? new BigDecimal("0.01") : record.getPayAmount();
		        String subject = "订单" + (ConfigUtil.getPayTestFlag() ? "(测试)" : "");
		        PayOrder pOrder = new PayOrder(subject, "摘要", realPayAmount, record.getPayRecordNo(), PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
		        if (StringUtils.isNotBlank(openId)) {
		        	pOrder.setOpenid(openId);
		        }

		        orderInfoMap = payResponse.getService().orderInfo(pOrder);
		        orderInfoMap.put("signData", getOrderInfo(orderInfoMap, payType));
				// 冻结余额
				if (payBal.doubleValue() > 0) {
					sysFrontAccountService.frozenAmount(userId, payBal, payRecordNo);
				}
			}
			return orderInfoMap;
		} catch (Exception e) {
			logger.error("生成订单交易记录失败（APP/公众号支付）", e);
			throw new RpcException("", "生成订单交易记录失败（APP/公众号支付）", checkException(e));
		}
	}
	
	@Override
	public boolean queryOrderPayStatus(Integer userId, Integer orderId) throws RpcException {
		Assert.notNull(userId, "userId不能为空");
		Assert.notNull(orderId, "orderId不能为空");
		try {
			OrderMain orderMain = orderMainService.findByKeyValSingle(ID, orderId);
			if (null == orderMain) {//|| !userId.equals(orderMain.getUserId())
				return false;
			}
			return StringUtils.equals(orderMain.getPayStatus(), PAY_STATUS_1);
		} catch (ServiceException e) {
			logger.error("查询订单交易记录失败", e);
			throw new RpcException("", "查询订单交易记录失败",e);
		}
	}

	@SuppressWarnings("unused")
	@Override
	public String payCall(String payType, Map<String, Object> params) throws RpcException {
		try {
			// 交易号
			String payRecordNo = (String) params.get("out_trade_no");
			OrderPayRecord record = orderPayRecordService.findByKeyValSingle("payRecordNo", payRecordNo);
			//根据账户id，获取对应的支付账户操作工具
			SdxPayResponse payResponse = payAccountApi.getPayResponse(record.getMerchantId(), Integer.valueOf(payType));
			PayConfigStorage storage = payResponse.getStorage();
			//获取支付方返回的对应参数
			// Map<String, Object> params = payResponse.getService().getParameter2Map(maps, in);
			if (null == params) {
			    return payResponse.getService().getPayOutMessage("fail", "失败").toMessage();
			}

			//校验
			if (payResponse.getService().verify(params)) {
			    PayMessage message = new PayMessage(params, storage.getPayType(), storage.getMsgType().name());
			    PayOutMessage outMessage = payResponse.getRouter().route(message);
			    return outMessage.toMessage();
			}

			return payResponse.getService().getPayOutMessage("fail", "失败").toMessage();
		} catch (ServiceException e) {
			logger.error("支付回调失败", e);
			throw new RpcException("", "支付回调失败",e);
		}
	}

	@Override
	public boolean closeTrade(Integer orderId, boolean isCloseOrder) throws RpcException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("orderId", orderId);
		params.put("payStatus", PAY_STATUS_0);
		params.put("delFlag", DEL_FLAG_0);

		try {
			List<OrderPayRecord> payRecords = orderPayRecordService.findByBiz(params);
			if (!CollectionUtils.isEmpty(payRecords)) {
				for (OrderPayRecord orderPayRecord : payRecords) {
					if (StringUtils.equals(orderPayRecord.getPayType(), PAY_TYPE_0)) {
						continue;
					}

			        // 2. 对这笔交易冻结的余额进行解冻
			        if (orderPayRecord.getBalanceAmount().doubleValue() > 0)
			        	sysFrontAccountService.unFrozenAmount(orderPayRecord.getCreateBy(), orderPayRecord.getBalanceAmount(), orderPayRecord.getPayRecordNo());
			        // 3. 作废这笔交易记录
			        orderPayRecord.setDelFlag(Constant.DEL_FLAG_1);
			        orderPayRecord.setUpdateDate(new Date());
			        orderPayRecordService.modifyById(orderPayRecord);

			        // 4. 判断是否需要关闭订单
			        if (isCloseOrder) {
			        	OrderMain order = orderMainService.findByKeyValSingle(ID, orderPayRecord.getOrderId());
			        	if (StringUtils.equals(order.getPayStatus(), Constant.PAY_STATUS_0)
			        			&& StringUtils.equals(order.getOrderStatus(), Constant.ORDER_STATUS_1)) {
			        		order.setOrderStatus(Constant.ORDER_STATUS_5);
			        		order.setUpdateDate(new Date());
			        		orderMainService.modifyById(order);
			        	}
			        }
				}
			}
		} catch (ServiceException e) {
			logger.error("关闭无效交易记录", e);
			throw new RpcException("", "关闭无效交易记录", e);
		}
		return true;
	}

	@Override
	public Map<String, Object> createPayBuyMemberParam(Integer userId, BigDecimal amount, String payType, String openId, String bizType)
			throws RpcException {
		Assert.notNull(userId, "userId不能为空");
		Assert.notNull(amount, "amount不能为空");
		Assert.isNotEmpty(payType, "payType不能为空");
		// Assert.isNotEmpty(openId, "openId不能为空");
		try {
			SysFrontAccount account = accountApi.getAccountById(userId);

			Map<String, Object> orderInfoMap = null;

			PayOnlineVo payOnlineVo = new PayOnlineVo();
			payOnlineVo.setBalance(BigDecimal.ZERO);

			OrderPayRecord record = new OrderPayRecord();
			String payRecordNo = baseCommonApi.getSerialNo(0, RULE_PAY_RECORD_NO);
			record.setPayRecordNo(payRecordNo);
			// record.setOrderId(orderId);
			record.setPayType(payType);
			record.setBalanceAmount(BigDecimal.ZERO);
			record.setPayAmount(amount);
			record.setGatewayAmount(record.getPayAmount());
			record.setPayStatus(PAY_STATUS_0);
			record.setCreateBy(userId);
			record.setCreateDate(new Date());
			record.setRemarks(StringUtils.isNotBlank(openId) ? "OPENID" : "APP");
			record.setBizType(bizType);
			if (orderPayRecordService.add(record) > 0) {
				// 生成付款二维码
				//获取对应的支付账户操作工具（可根据账户id）
		        SdxPayResponse payResponse = payAccountApi.getPayResponse(account.getMerchantId(), Integer.valueOf(payType));
		        String transactionType = WxTransactionType.JSAPI.name();
		        if (PAY_TYPE_1.equals(payType) && StringUtils.isNotBlank(openId)) {
		        	// 公众号支付
		        	transactionType = WxTransactionType.JSAPI.name();
		        } else if (PAY_TYPE_3.equals(payType) && StringUtils.isBlank(openId)) {
		        	// 微信APP支付
		        	transactionType = WxTransactionType.APP.name();
		        } else if (PAY_TYPE_2.equals(payType)) {
		        	// 支付宝APP支付
		        	transactionType = AliTransactionType.APP.name();
		        }

		        // 真实支付金额(如果当前为测试环境，则临时修改网关支付金额)
		        BigDecimal realPayAmount = ConfigUtil.getPayTestFlag() ? new BigDecimal("0.01") : record.getPayAmount();
		        String subject = "购买会员" + (ConfigUtil.getPayTestFlag() ? "(测试)" : "");
		        PayOrder pOrder = new PayOrder(subject, "摘要", realPayAmount, record.getPayRecordNo(), PayType.valueOf(payResponse.getStorage().getPayType()).getTransactionType(transactionType));
		        if (StringUtils.isNotBlank(openId)) {
		        	pOrder.setOpenid(openId);
		        }

		        orderInfoMap = payResponse.getService().orderInfo(pOrder);
		        orderInfoMap.put("signData", getOrderInfo(orderInfoMap, payType));
		        orderInfoMap.put("payRecordNo", payRecordNo);
			}
			return orderInfoMap;
		} catch (Exception e) {
			logger.error("生成充值交易记录（APP/公众号支付）", e);
			throw new RpcException("", "生成充值交易记录（APP/公众号支付）", checkException(e));
		}
	}

	@Override
	public boolean queryPayBuyMemberStatus(Integer userId, String payRecordNo) throws RpcException {
		Assert.notNull(userId, "userId不能为空");
		Assert.isNotEmpty(payRecordNo, "payRecordNo不能为空");
		try {
			OrderPayRecord record = orderPayRecordService.findByKeyValSingle("payRecordNo", payRecordNo);
			if (null == record || !userId.equals(record.getCreateBy())) {
				return false;
			}
			return StringUtils.equals(record.getPayStatus(), PAY_STATUS_1);
		} catch (ServiceException e) {
			logger.error("查询购买会员支付状态失败", e);
			throw new RpcException("", "查询购买会员支付状态失败",e);
		}
	}

	private String getOrderInfo(Map<String, Object> orderInfoMap, String payType) throws UnsupportedEncodingException {
		if (PAY_TYPE_2.equals(payType)) {
			if (!CollectionUtils.isEmpty(orderInfoMap)) {
				Map<String, Object> tmpMap = Maps.newHashMap();
				for (String key : orderInfoMap.keySet()) {
					tmpMap.put(key, URLEncoder.encode(orderInfoMap.get(key).toString(), "UTF-8"));
				}
				// return SignUtils.parameterText(tmpMap, "&") + "&sign=" + tmpMap.get("sign");
				return SignUtils.parameterText(tmpMap, "&", "key");
			}
		} else {

		}
		return null;
    }
	
	private Exception checkException(Exception e) {
		if (e instanceof PayErrorException) {
			return new Exception(e.getMessage());
		}
		return e;
	}
}

