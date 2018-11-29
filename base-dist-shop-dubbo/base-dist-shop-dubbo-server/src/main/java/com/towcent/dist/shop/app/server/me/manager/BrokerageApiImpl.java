/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : BrokerageApiImpl.java
 * @Package : com.towcent.dist.shop.app.server.me.manager
 * @date : 2018年6月28日下午3:13:16
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.me.manager;

import static com.towcent.base.common.utils.PreciseCompute.multiply;
import static com.towcent.base.common.utils.PreciseCompute.round;
import static com.towcent.dist.shop.common.Constant.ID;
import static com.towcent.dist.shop.common.Constant.INCOME_TYPE_1;
import static com.towcent.dist.shop.common.Constant.WX_TEMPLATE_BACK_URL;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.dto.OrderPayRecord;
import com.towcent.dist.shop.app.client.me.service.BrokerageApi;
import com.towcent.dist.shop.app.client.sys.dto.SysAmountRecord;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysLevelConf;
import com.towcent.dist.shop.app.server.mall.service.OrderDtlService;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.app.server.mall.service.OrderPayRecordService;
import com.towcent.dist.shop.app.server.sys.service.SysAmountRecordService;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.app.server.sys.service.SysLevelConfService;
import com.towcent.dist.shop.common.Constant;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @ClassName: BrokerageApiImpl 
 * @Description: 分销分佣算法接口实现
 *
 * @author huangtao
 * @date 2018年6月28日 下午3:13:16
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class BrokerageApiImpl extends BaseService implements BrokerageApi {
	
	@Resource
	private SysFrontAccountService accountService;
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private OrderDtlService orderDtlService;
	@Resource
	private OrderPayRecordService payRecordService;
	@Resource
	private SysLevelConfService levelService;
	@Resource
	private SysAmountRecordService amountRecordService;
	@Resource
	private SysPropertyService propertyService;
	
	@Override @Transactional
	public void calculateBkge(Integer userId, Integer orderId) throws RpcException {
		Assert.notNull(userId, "用户Id不能为空");
		Assert.notNull(orderId, "订单Id不能为空");
		
		try {
			SysFrontAccount account = accountService.getAccountById(userId);
			// 1. 当没有上级时，所有人都没有佣金
			if (null == account.getParentId()) return;
			
			OrderMain order = orderMainService.findByKeyValSingle(ID, orderId);
			OrderDtl orderDtl = orderDtlService.findByKeyValSingle("orderId", orderId);
			// 订单的分佣中金额（除开运费总金额）
			BigDecimal amount = order.getTotalAmount();
			
			// 上级用户
			SysFrontAccount parentAccount = accountService.getAccountById(account.getParentId());
			parentAccount = this.findDistAccount(parentAccount);  // 依次查找最近的那个分销商
			if (null == parentAccount) return;
			
			// 1. 直推佣金
			SysLevelConf ztLevel = levelService.getLevelConfByParam(account.getMerchantId(), parentAccount.getLevelVip());
			BigDecimal ztBkge = round(multiply(ztLevel.getDirectBkge(), amount));
			this.saveBkge(parentAccount, order, ztBkge, orderDtl.getGoodsName(), "业绩提成", 1);
			
			if (null != parentAccount.getParentId()) {
				// 2. 下级分佣
				SysFrontAccount parentParentAccount = accountService.getAccountById(parentAccount.getParentId());
				parentParentAccount = this.findDistAccount(parentParentAccount); // 依次查找最近的那个分销商
				if (null == parentParentAccount) return;
				SysLevelConf upLevel = levelService.getLevelConfByParam(account.getMerchantId(), parentParentAccount.getLevelVip());
				if (shiftLevel(ztLevel.getLevel()) < shiftLevel(upLevel.getLevel())) {
					// 级别差(高级别-低级别)
					BigDecimal diff = upLevel.getDirectBkge().subtract(ztLevel.getDirectBkge());
					BigDecimal upBkge = round(multiply(diff, amount));
					this.saveBkge(parentParentAccount, order, upBkge, orderDtl.getGoodsName(), "下级提成", 2);
				} else if (shiftLevel(ztLevel.getLevel()) == shiftLevel(upLevel.getLevel())) {
					// 平级奖励
					BigDecimal peersRewardRate = propertyService.getSysPropertyToBigDecimal(account.getMerchantId(), "peers_reward_rate");
					BigDecimal reward = round(multiply(peersRewardRate, amount));
					this.saveBkge(parentParentAccount, order, reward, orderDtl.getGoodsName(), "平级奖励", 3);
				}
			}
			
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}	
	}

	@Override
	public void saveBkge(SysFrontAccount account, OrderMain order, BigDecimal bkge, String orderTitle, String remarks, int tier)
			throws RpcException {
		try {
			SysFrontAccount modifyAccount = new SysFrontAccount();
			modifyAccount.setId(account.getId());
			modifyAccount.setAmount(account.getAmount().add(bkge));
			accountService.modifyById(modifyAccount);
			
			SysAmountRecord record = new SysAmountRecord();
			record.setUserId(account.getId());
			record.setDealNo(order.getOrderNo());
			record.setType(INCOME_TYPE_1);
			record.setAmount(bkge);
			record.setAmountAfter(modifyAccount.getAmount());
			record.setOrderAmount(order.getTotalAmount());
			record.setOrderTitle(orderTitle);
			record.setRemarks(remarks);
			record.setCreateDate(new Date());
			record.setMerchantId(account.getMerchantId());
			
			amountRecordService.add(record);
			
			// 接入微信模板消息通知
			String first = "";
			if (tier == 1) {
				first = MessageFormat.format(Constant.BKGE_FIRST_TITLE_1, account.getNickName(), orderTitle, bkge);
			} else if (tier == 2) {
				first = MessageFormat.format(Constant.BKGE_FIRST_TITLE_2, account.getNickName(), orderTitle, bkge);
			} else if (tier == 3) {
				first = MessageFormat.format(Constant.BKGE_FIRST_TITLE_3, account.getNickName(), orderTitle, bkge);
			}
			this.assembleTemplateMssage(account, first, bkge, record.getCreateDate());
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}
	
	@Override @Transactional
	public void calculateBkgeBuyMember(OrderPayRecord payRecord) throws RpcException {
		Assert.notNull(payRecord, "payRecord不能为空");
		try {
			SysFrontAccount account = accountService.getAccountById(payRecord.getCreateBy());
			// 1. 当没有上级时，所有人都没有佣金
			if (null == account.getParentId()) return;
			
			// 交易中的分佣中金额
			BigDecimal amount = payRecord.getPayAmount(); 
			SysLevelConf buyLevel = levelService.getLevelConfByParam(account.getMerchantId(), (Integer.valueOf(payRecord.getBizType()) + 1) + "");
			String title = buyLevel.getLevelName();
			
			// 上级用户
			SysFrontAccount parentAccount = accountService.getAccountById(account.getParentId());
			parentAccount = this.findDistAccount(parentAccount);  // 依次查找最近的那个分销商
			if (null == parentAccount) return;
			
			// 1. 直推佣金
			SysLevelConf ztLevel = levelService.getLevelConfByParam(account.getMerchantId(), parentAccount.getLevelVip());
			BigDecimal ztBkge = round(multiply(ztLevel.getDirectBkge(), amount));
			this.saveBkgeBuyMember(parentAccount, payRecord, ztBkge, title, "业绩提成", 1);
			
			if (null != parentAccount.getParentId()) {
				// 2. 下级分佣
				SysFrontAccount parentParentAccount = accountService.getAccountById(parentAccount.getParentId());
				parentParentAccount = this.findDistAccount(parentParentAccount); // 依次查找最近的那个分销商
				if (null == parentParentAccount) return;
				SysLevelConf upLevel = levelService.getLevelConfByParam(account.getMerchantId(), parentParentAccount.getLevelVip());
				if (shiftLevel(ztLevel.getLevel()) < shiftLevel(upLevel.getLevel())) {
					// 级别差(高级别-低级别)
					BigDecimal diff = upLevel.getDirectBkge().subtract(ztLevel.getDirectBkge());
					BigDecimal upBkge = round(multiply(diff, amount));
					this.saveBkgeBuyMember(parentParentAccount, payRecord, upBkge, title, "下级提成", 2);
				} else if (shiftLevel(ztLevel.getLevel()) == shiftLevel(upLevel.getLevel())) {
					// 平级奖励
					BigDecimal peersRewardRate = propertyService.getSysPropertyToBigDecimal(account.getMerchantId(), "peers_reward_rate");
					BigDecimal reward = round(multiply(peersRewardRate, amount));
					this.saveBkgeBuyMember(parentParentAccount, payRecord, reward, title, "平级奖励", 3);
				}
			}
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}
	
	@Override
	public void saveBkgeBuyMember(SysFrontAccount account, OrderPayRecord payRecord, BigDecimal bkge, String title,
			String remarks, int tier) throws RpcException {
		try {
			SysFrontAccount modifyAccount = new SysFrontAccount();
			modifyAccount.setId(account.getId());
			modifyAccount.setAmount(account.getAmount().add(bkge));
			accountService.modifyById(modifyAccount);
			
			SysAmountRecord record = new SysAmountRecord();
			record.setUserId(account.getId());
			record.setDealNo(payRecord.getPayRecordNo());
			record.setType(INCOME_TYPE_1);
			record.setAmount(bkge);
			record.setAmountAfter(modifyAccount.getAmount());
			record.setOrderAmount(payRecord.getPayAmount());
			record.setOrderTitle(title);
			record.setRemarks(remarks);
			record.setCreateDate(new Date());
			record.setMerchantId(account.getMerchantId());
			
			amountRecordService.add(record);
			
			// 接入微信模板消息通知
			String first = "";
			if (tier == 1) {
				first = MessageFormat.format(Constant.BKGE_FIRST_TITLE_1, account.getNickName(), title, bkge);
			} else if (tier == 2) {
				first = MessageFormat.format(Constant.BKGE_FIRST_TITLE_2, account.getNickName(), title, bkge);
			} else if (tier == 3) {
				first = MessageFormat.format(Constant.BKGE_FIRST_TITLE_3, account.getNickName(), title, bkge);
			}
			this.assembleTemplateMssage(account, first, bkge, record.getCreateDate());
		} catch (ServiceException e) {
			logger.error("", e);
			throw new RpcException("", "", e);
		}
	}
	
	/**
	 * 依次向上查找分销商.
	 * @Title findDistAccount
	 * @param account
	 * @return
	 * @throws ServiceException 
	 */
	private SysFrontAccount findDistAccount(SysFrontAccount account) throws ServiceException {
		int level = shiftLevel(account.getLevelVip());
		if (level > 1) {  // 查找到分销商则返回
			return account;
		} else {
			if (null == account.getParentId()) {  // 分销商层级断裂，则返回
				return null;
			} else { // 依次递归的向上寻找到最近的分销商
				SysFrontAccount parentAccount = accountService.getAccountById(account.getParentId());
				return findDistAccount(parentAccount);
			}
		}
	}
	
	/**
	 * 转换等级，将之转化为可以比较的类型.
	 * @Title shiftLevel
	 * @param level
	 * @return
	 */
	private int shiftLevel(String level) {
		return Integer.valueOf(level);
	}
	
	/**
	 * 佣金提醒<br>
	 * 装配微信公众模板消息Vo.
	 * @Title assembleTemplateMssage
	 * @param account  会员对象
	 * @param first    标题
	 * @param bkge     佣金
	 * @param date     时间(格式化)
	 * @return
	 * @throws ServiceException 
	 */
	private WxMpTemplateMessage assembleTemplateMssage(SysFrontAccount account, String first, BigDecimal bkge, Date date) throws ServiceException {
		if (StringUtils.isBlank(account.getOpenId())) return null;
		WxMpTemplateMessage vo = new WxMpTemplateMessage();
		vo.setToUser(account.getOpenId());
		vo.setTemplateId("PnN5sQJvf1k4vwcD7xCFWOtaA-bqM_wqEVinnr4Ykk0");  // 佣金提醒的消息模板Id
		// 返回公众号的链接地址
		vo.setUrl(propertyService.getSysPropertyToString(account.getMerchantId(), WX_TEMPLATE_BACK_URL));
		vo.addData(new WxMpTemplateData("first", first));
		vo.addData(new WxMpTemplateData("keyword1", bkge + "元"));
		vo.addData(new WxMpTemplateData("keyword2", DateUtils.formatDateTime(date)));
		return vo;
	}
}

	