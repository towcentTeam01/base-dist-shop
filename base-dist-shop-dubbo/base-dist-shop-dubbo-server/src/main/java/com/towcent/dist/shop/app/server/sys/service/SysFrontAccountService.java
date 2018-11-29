package com.towcent.dist.shop.app.server.sys.service;

import java.math.BigDecimal;
import java.util.Map;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.base.service.BaseCrudService;

/**
 * sys_front_account 数据库操作Service接口
 * 
 * @author huangtao
 * @date 2017-12-28 10:33:12
 * @version 1.0
 * @copyright facegarden.com
 */
public interface SysFrontAccountService extends BaseCrudService {
	
	/**
	 * 通过参数查询账户对象.
	 * @Title getAccountByParams
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	SysFrontAccount getAccountByParams(Map<String, Object> params) throws ServiceException;
	
	/**
	 * 通过参数查询账户对象.
	 * @Title getAccountById
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	SysFrontAccount getAccountById(Integer id) throws ServiceException;
		
    /**
     * 扣减余额.
     * @Title: discountAmount
     * @param userId
     * @param amount
     * @param payRecordNo
     * @param isFreeze 是否从冻结资金中扣减
     * @return
     * @throws RpcException
     * @return: boolean
     */
    boolean discountAmount(Integer userId, BigDecimal amount, String payRecordNo, boolean isFreeze) throws ServiceException;
    
    /**
     * 增加钱包余额.
     * @Title increaseAmount
     * @param userId   用户Id
     * @param amount   增加的金额
     * @param payRecordNo  交易号
     * @return
     * @throws ServiceException
     */
    boolean increaseAmount(Integer userId, BigDecimal amount, String payRecordNo) throws ServiceException;
    
    /**
     * 冻结资金.
     * @Title: frozenAmount
     * @param userId
     * @param amount
     * @param payRecordNo
     * @return
     * @throws RpcException
     * @return: boolean
     */
    boolean frozenAmount(Integer userId, BigDecimal amount, String payRecordNo) throws ServiceException;

    /**
     * 解冻资金.
     * @Title: unFrozenAmount
     * @param userId
     * @param amount
     * @param payRecordNo
     * @return
     * @throws RpcException
     * @return: boolean
     */
    boolean unFrozenAmount(Integer userId, BigDecimal amount, String payRecordNo) throws ServiceException;
    
    /**
     * 会员升级（升级到指定等级）.
     * @Title upgradeMember
     * @param userId   会员Id
     * @param level    指定会员等级
     * @return
     * @throws ServiceException
     */
    boolean upgradeMember(Integer userId, String level) throws ServiceException;
}