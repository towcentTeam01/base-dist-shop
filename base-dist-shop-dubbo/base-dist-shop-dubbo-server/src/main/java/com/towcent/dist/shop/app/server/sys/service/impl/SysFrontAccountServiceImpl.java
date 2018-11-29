package com.towcent.dist.shop.app.server.sys.service.impl;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_1;
import static com.towcent.dist.shop.common.Constant.ID;
import static com.towcent.dist.shop.common.Constant.INCOME_TYPE_0;
import static com.towcent.dist.shop.common.Constant.INCOME_TYPE_1;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.client.sys.dto.SysAmountRecord;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysLevelConf;
import com.towcent.dist.shop.app.server.sys.dao.SysAmountRecordMapper;
import com.towcent.dist.shop.app.server.sys.dao.SysFrontAccountMapper;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.app.server.sys.service.SysLevelConfService;

/**
 * 
 * @author huangtao
 * @date 2017-12-28 10:33:12
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysFrontAccountServiceImpl")
public class SysFrontAccountServiceImpl extends BaseCrudServiceImpl implements SysFrontAccountService {

    @Resource
    private SysFrontAccountMapper sysFrontAccountMapper;
    @Resource
    private SysAmountRecordMapper sysAmountRecordMapper;
    @Resource
    private SysLevelConfService levelConfService;
    @Resource
    private SysLevelConfService levelService;
    
    @Override
    public CrudMapper init() {
        return sysFrontAccountMapper;
    }
    
    @Override
    public SysFrontAccount getAccountByParams(Map<String, Object> params) throws ServiceException {
		List<SysFrontAccount> list = this.findByBiz(params);
		SysFrontAccount account = CollectionUtils.isEmpty(list) ? null : list.get(0); 
		
		if (null != account) {
			// 等级别名
			SysLevelConf level = levelConfService.getLevelConfByParam(account.getMerchantId(), account.getLevelVip());
			if (null != level) {
				account.setLevelVipDesc(level.getLevelName());
			}
		}
				
		return account;
    }
    
    @Override
    public SysFrontAccount getAccountById(Integer id) throws ServiceException {
    	Map<String, Object> params = Maps.newHashMap();
		params.put(ID, id);
		params.put("delFlag", DEL_FLAG_0);
		// TODO Redis缓存
		SysFrontAccount account = this.getAccountByParams(params);
		// 处理等级描述
		account.setLevelVipDesc(levelService.queryLevelDesc(account.getMerchantId(), account.getLevelVip()));
		return account;
    }
    
    @Override
    public boolean discountAmount(Integer userId, BigDecimal amount, String payRecordNo, 
    		boolean isFreeze) throws ServiceException {   	
    	try {
    		if (null != amount && amount.intValue() == 0) return false;
    		
			SysFrontAccount account = this.getAccountById(userId);
			if (null == account) return false;
			SysFrontAccount nAccount = new SysFrontAccount();
			nAccount.setId(userId);
			
			// isFreeze 需要扣减的资金是否已经再之前就冻结了。
			if (isFreeze) {
				// 1. 需要从冻结资金中扣减掉
				// 2. 当钱包被冻结余额小于要扣减金额时，扣减失败
				if (account.getFreezeAmount().doubleValue() < amount.doubleValue()) {
					throw new ServiceException("钱包余额不足");
				}
				nAccount.setAmount(account.getAmount());
				nAccount.setFreezeAmount(account.getFreezeAmount().subtract(amount));
				
				// 3. 删除冻结记录（逻辑）
				Map<String, Object> params = Maps.newHashMap();
				params.put("dealNo", payRecordNo);
				params.put("delFlag", DEL_FLAG_0);
				List<SysAmountRecord> list = sysAmountRecordMapper.selectByParams(params);
				if (!CollectionUtils.isEmpty(list)) {
					for (SysAmountRecord sysAmountRecord : list) {
						sysAmountRecord.setDelFlag(DEL_FLAG_1);
						sysAmountRecord.setRemarks("临时解冻删除");
						sysAmountRecordMapper.updateByPrimaryKeySelective(sysAmountRecord);
					}
				}
			} else {
				// 1. 需要从本身的账户余额中扣减掉
				// 2. 当钱包余额小于要扣减金额时，扣减失败
				if (account.getAmount().doubleValue() < amount.doubleValue()) {
					throw new ServiceException("钱包余额不足");
				}
				nAccount.setAmount(account.getAmount().subtract(amount));
			}
			// 3. 更新余额
			boolean flag = sysFrontAccountMapper.updateByPrimaryKeySelective(nAccount) > 0;
			if (flag) {
				// 添加日志
				SysAmountRecord record = new SysAmountRecord();
				record.setAmount(amount);
				record.setAmountAfter(nAccount.getAmount());
				record.setCreateDate(new Date());
				record.setType(INCOME_TYPE_0);
				record.setUserId(userId);
				record.setDealNo(payRecordNo);
				record.setRemarks("扣减" + amount);
				sysAmountRecordMapper.insertSelective(record);
			}
			return flag;
		} catch (ServiceException e) {
			logger.error("扣减钱包余额失败");
            throw new ServiceException("扣减钱包余额失败", e);
		}
    }
    
    @Override
    public boolean increaseAmount(Integer userId, BigDecimal amount, String payRecordNo) throws ServiceException {
    	try {
			SysFrontAccount account = this.getAccountById(userId);
			if (null == account) return false;
			SysFrontAccount nAccount = new SysFrontAccount();
			nAccount.setId(userId);
			nAccount.setAmount(account.getAmount().add(amount));
			
			// 3. 更新余额
			boolean flag = sysFrontAccountMapper.updateByPrimaryKeySelective(nAccount) > 0;
			if (flag) {
				// 添加日志
				SysAmountRecord record = new SysAmountRecord();
				record.setAmount(amount);
				record.setAmountAfter(nAccount.getAmount());
				record.setCreateDate(new Date());
				record.setType(INCOME_TYPE_1);
				record.setUserId(userId);
				record.setDealNo(payRecordNo);
				record.setRemarks("入账" + amount);
				sysAmountRecordMapper.insertSelective(record);
			}
			return flag;
		} catch (ServiceException e) {
			logger.error("增加钱包余额失败");
            throw new ServiceException("增加钱包余额失败", e);
		}
    }
    
	@Override
	public boolean frozenAmount(Integer userId, BigDecimal amount, String payRecordNo) throws ServiceException {
		try {
			SysFrontAccount account = this.getAccountById(userId);
			if (null == account) {
				return false;
			}
			// 当钱包余额小于要冻结金额时，冻结失败
			if (account.getAmount().doubleValue() < amount.doubleValue()) {
				throw new ServiceException("冻结钱包余额失败");
			}
			SysFrontAccount nAccount = new SysFrontAccount();
			nAccount.setId(userId);
			nAccount.setAmount(account.getAmount().subtract(amount));
			nAccount.setFreezeAmount(account.getFreezeAmount().add(amount));
			
			boolean flag = this.modifyById(nAccount) > 0;
			if (flag) {
				// 记录日志
				SysAmountRecord record = new SysAmountRecord();
				record.setAmount(amount);
				record.setAmountAfter(nAccount.getAmount());
				record.setCreateDate(new Date());
				record.setType(INCOME_TYPE_0);
				record.setUserId(userId);
				record.setDealNo(payRecordNo);
				record.setRemarks("临时冻结" + amount);
				sysAmountRecordMapper.insertSelective(record);
			}
			return flag; 
		} catch (ServiceException e) {
			logger.error("冻结钱包余额失败");
            throw new ServiceException("冻结钱包余额失败", e);
		}
	}

	@Override
	public boolean unFrozenAmount(Integer userId, BigDecimal amount, String payRecordNo) throws ServiceException {
		try {
			SysFrontAccount account = this.getAccountById(userId);
			if (null == account) {
                return false;
            }
			// 当钱包被冻结余额小于要解冻金额时，解冻失败
			if (account.getFreezeAmount().doubleValue() < amount.doubleValue()) {
				throw new ServiceException("解冻钱包余额失败");
			}
			SysFrontAccount nAccount = new SysFrontAccount();
			nAccount.setId(userId);
			nAccount.setAmount(account.getAmount().add(amount));
			nAccount.setFreezeAmount(account.getFreezeAmount().subtract(amount));
			boolean flag = this.modifyById(nAccount) > 0;
			if (flag) {
				// 将之前的冻结记录日志逻辑删除
				Map<String, Object> params = Maps.newHashMap();
				params.put("dealNo", payRecordNo);
				params.put("delFlag", DEL_FLAG_0);
				List<SysAmountRecord> list = sysAmountRecordMapper.selectByParams(params);
				if (!CollectionUtils.isEmpty(list)) {
					for (SysAmountRecord sysAmountRecord : list) {
						sysAmountRecord.setDelFlag(DEL_FLAG_1);
						sysAmountRecord.setRemarks("临时解冻删除");
						sysAmountRecordMapper.updateByPrimaryKeySelective(sysAmountRecord);
					}
				}
			}
			
			return flag;
		} catch (ServiceException e) {
			logger.error("冻结钱包余额失败", e);
            throw new ServiceException("冻结钱包余额失败", e);
		}
	}
	
	@Override
	public boolean upgradeMember(Integer userId, String level) throws ServiceException {
		SysFrontAccount account = this.getAccountById(userId);
		if (Integer.valueOf(account.getLevelVip()) >= Integer.valueOf(level)) {
			return true;
		}
		
		SysFrontAccount o = new SysFrontAccount();
		o.setId(userId);
		o.setLevelVip(level);
		return this.modifyById(o) > 0;
	}
}