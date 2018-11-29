package com.towcent.dist.shop.portal.sys.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;
import static com.towcent.dist.shop.common.Constant.SYS_TYPE_4;
import static com.towcent.dist.shop.common.Constant.SYS_TYPE_5;

import java.util.Map;

import javax.annotation.Resource;

import me.chanjar.weixin.common.error.WxErrorException;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.model.SmsParamDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.manager.SmsNotifyApi;
import com.towcent.base.wx.service.WxInstanceFactory;
import com.towcent.dist.shop.app.client.sys.dto.Session;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysParamQueryVo;
import com.towcent.dist.shop.app.client.sys.service.SessionApi;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.common.vo.BaseParam;
import com.towcent.dist.shop.portal.sys.biz.MemberAccountService;
import com.towcent.dist.shop.portal.sys.vo.input.LoginIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountBaseInfoIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountFastLoginIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountLoginForWxIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountLoginIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountPhoneExistIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountRegisterIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountResetIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountSaveBaseInfoIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountUpdatePasswdIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountUpdatePhoneIn;
import com.towcent.dist.shop.portal.sys.vo.input.MemberAccountUpdateWithdrawPassIn;
import com.towcent.dist.shop.portal.sys.vo.output.MemberAccountBaseInfoOut;
import com.towcent.dist.shop.portal.sys.vo.output.MemberAccountLoginOut;
import com.towcent.dist.shop.portal.sys.vo.output.MemberAccountPhoneExistOut;

/**
 * MemberAccountServiceImpl
 *
 * @author huangtao
 * @version 0.0.3
 */
@Service
public class MemberAccountServiceImpl extends BasePortalService implements MemberAccountService {

    @Resource
    private SessionApi sessionApi;
    @Resource
    private SmsNotifyApi smsNotifyApi;
    @Resource
    private SysFrontAccountApi sysFrontAccountApi;
    @Resource
	private WxInstanceFactory wxInstanceFactory;
    // @Resource
    // private NoticeApi noticeApi;

    @Override
    public ResultVo login(MemberAccountLoginIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            // 先查询会员信息
            SysFrontAccount member = sessionApi.getMemberInfoByAccount(paramIn.getMerchantId(), paramIn.getAccount(), paramIn.getAppType().intValue(), null);
            if (member == null) {
                return assemblyVo(resultVo, E_001, "账号不存在");
            }

            // 验证密码
            if (!StringUtils.endsWithIgnoreCase(paramIn.getPassword(),
                    member.getPassword()) && StringUtils.isNotEmpty(paramIn.getPassword())) {
                return assemblyVo(resultVo, E_001, "密码不正确");
            }
            // FIXME 需不需要增加锁定用户账号的功能
            
            MemberAccountLoginOut outParam = this.createLoginObjForOK(member, paramIn, paramIn.getCode());
            resultVo.setData(outParam);
        } catch (Exception e) {
            assemblyVo(resultVo, E_001, "登录失败");
            logger.error("登录失败", e);
        }
        return resultVo;
    }
    
    @Override
    public ResultVo loginForWx(MemberAccountLoginForWxIn paramIn) {
    	ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
        	// 通过工号查询用户信息
        	SysFrontAccount parentAccount = this.getParentAccount(paramIn.getJobNo());
            // 先查询会员信息
            SysFrontAccount member = sysFrontAccountApi.loginForWx(paramIn.getMerchantId(), paramIn.getCode(), paramIn.getAppType(), null != parentAccount ? parentAccount.getId() : null);
            if (member == null) {
                return assemblyVo(resultVo, E_001, "账号不存在");
            }

            MemberAccountLoginOut outParam = this.createLoginObjForOK(member, paramIn, null);
            resultVo.setData(outParam);
        } catch (Exception e) {
            assemblyVo(resultVo, E_001, "登录失败");
            logger.error("登录失败", e);
        }
        return resultVo;
    }
    
    @Override
    public ResultVo loginForMiniApp(LoginIn paramIn) {
    	ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
        	// 通过工号查询用户信息
        	SysFrontAccount parentAccount = this.getParentAccount(paramIn.getJobNo());
    		
            // 先查询会员信息
            SysFrontAccount member = sysFrontAccountApi.loginForMiniApp(paramIn.getMerchantId(), paramIn.getCode(), paramIn.getEncryptedData(), paramIn.getIv(), paramIn.getAppType(), null != parentAccount ? parentAccount.getId() : null);
            if (member == null) {
                return assemblyVo(resultVo, E_001, "账号不存在");
            }

            MemberAccountLoginOut outParam = this.createLoginObjForOK(member, paramIn, null);
            resultVo.setData(outParam);
        } catch (Exception e) {
            assemblyVo(resultVo, E_001, "登录失败");
            logger.error("登录失败", e);
        }
        return resultVo;
    }
    
    @Override
    public ResultVo fastLogin(MemberAccountFastLoginIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
        	// 通过工号查询用户信息
        	SysFrontAccount parentAccount = this.getParentAccount(paramIn.getJobNo());
        	
            // 先查询会员信息
            SysFrontAccount member = sessionApi.getMemberInfoByAccount(paramIn.getMerchantId(), paramIn.getAccount(), paramIn.getAppType().intValue(), null != parentAccount ? parentAccount.getId() : null);
            if (member == null) {
                return assemblyVo(resultVo, E_001, "账号不存在");
            }

            // 验证密码
            SmsParamDto smsParam = new SmsParamDto();
            smsParam.setMobile(paramIn.getAccount());
            smsParam.setSecurityCode(paramIn.getSecurityCode());
            smsParam.setSmsType(Constant.SMS_TYPE_2);
            if (!smsNotifyApi.verifySmsCode(smsParam)) {
                return assemblyVo(resultVo, E_001, "验证码不正确");
            }

            MemberAccountLoginOut outParam = this.createLoginObjForOK(member, paramIn, paramIn.getCode());
            resultVo.setData(outParam);
        } catch (Exception e) {
            assemblyVo(resultVo, E_001, "登录失败");
            logger.error("登录失败", e);
        }
        return resultVo;
    }
    
    /**
     * 返回登录成功之后需要的对象.
     * @Title createLoginObjForOK
     * @param member   会员对象
     * @param paramIn  接口入参
     * @param wxCode   微信code 再非公众号环境下为空
     * @return
     * @throws WxErrorException
     * @throws RpcException
     */
    private MemberAccountLoginOut createLoginObjForOK(SysFrontAccount member, BaseParam paramIn, String wxCode) throws WxErrorException, RpcException {
    	SysParamQueryVo queryVo = new SysParamQueryVo();
        queryVo.setMerchantId(paramIn.getMerchantId());
    	queryVo.setDeviceNo(paramIn.getDeviceNo());
        queryVo.setAppType(paramIn.getAppType().intValue());
        queryVo.setAppVersion(paramIn.getAppVersion());
        queryVo.setPhoneModel(paramIn.getPhoneModel());
        queryVo.setSysType(paramIn.getSysType());
        queryVo.setDeviceToken(paramIn.getDeviceToken());
        queryVo.setAccount(member.getAccount());
        if (paramIn.getSysType() == SYS_TYPE_5) {
        	// 小程序
        	queryVo.setOpenId(member.getMiniOpenId());
        } else if (paramIn.getSysType() == SYS_TYPE_4) {
        	// 公众号
        	queryVo.setOpenId(member.getOpenId());
        }
        
        Session obj = sessionApi.createSession(queryVo);
        MemberAccountLoginOut outParam = new MemberAccountLoginOut();
        outParam.setTokenId(obj.getTokenId());
        outParam.setNickName(member.getNickName());
        outParam.setRealName(member.getNickName());
        outParam.setIcon(member.getPortrait());
        outParam.setOpenId(queryVo.getOpenId());
        outParam.setUserId(member.getId());
        return outParam;
    }
    
    @Override
    public ResultVo reset(MemberAccountResetIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SmsParamDto spd = new SmsParamDto();
            spd.setSmsType(Constant.SMS_TYPE_3);
            spd.setMobile(paramIn.getPhone());
            spd.setSecurityCode(paramIn.getSecurityCode());
            if (!smsNotifyApi.verifySmsCode(spd)) {
                return assemblyVo(resultVo, E_001, "验证码不正确");
            }
            SysFrontAccount sysFrontAccount = new SysFrontAccount();
            sysFrontAccount.setMobile(paramIn.getPhone());
            sysFrontAccount.setPassword(paramIn.getPassword());
            sysFrontAccount.setUserType(paramIn.getAppType()+"");
            sysFrontAccount.setMerchantId(paramIn.getMerchantId());
            if (!sysFrontAccountApi.reset(sysFrontAccount)) {
                assemblyVo(resultVo, E_001, "重置失败");
            }
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "重置失败");
            logger.error("重置失败", e);
        }
        return resultVo;
    }


	@Override
	public ResultVo register(MemberAccountRegisterIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
            SmsParamDto spv = new SmsParamDto();
            spv.setMobile(paramIn.getPhone());
            spv.setSecurityCode(paramIn.getSecurityCode());
            spv.setSmsType(Constant.SMS_TYPE_1);
            if (!smsNotifyApi.verifySmsCode(spv)) {
                assemblyVo(resultVo, E_001, "验证码不正确");
                return resultVo;
            }
            
            boolean exist = sysFrontAccountApi.accountIsExist(paramIn.getMerchantId(), paramIn.getPhone(), paramIn.getAppType());
            if (exist) {
            	return assemblyVo(resultVo, E_001, "账号已经存在");
            }
            
            // 通过工号查询用户信息
        	Map<String, Object> params = Maps.newHashMap();
        	params.put("jobNo", paramIn.getJobNo());
    		SysFrontAccount sysFrontAccount = sysFrontAccountApi.getAccountByParams(params);
            
            boolean flag = sysFrontAccountApi.register(paramIn.getMerchantId(), paramIn.getPhone(), paramIn.getPassword(), paramIn.getAppType(), null != sysFrontAccount ? sysFrontAccount.getId() : null);
            if (flag) {
                SysFrontAccount member = sessionApi.getMemberInfoByAccount(paramIn.getMerchantId(), paramIn.getPhone(), paramIn.getAppType().intValue(), null != sysFrontAccount ? sysFrontAccount.getId() : null);
                
                MemberAccountLoginOut outParam = this.createLoginObjForOK(member, paramIn, paramIn.getCode());
                
                resultVo.setData(outParam);
                // 发送站内信
                // noticeApi.noticeForMail(member.getId(), SMS_TYPE_8, null);
            } else {
                assemblyVo(resultVo, E_001, "注册失败");
            }
        } catch (Exception e) {
            assemblyVo(resultVo, E_001, "注册失败");
            logger.error("注册失败", e);
        }
        return resultVo;
	}

	
	@Override
	public ResultVo phoneExist(MemberAccountPhoneExistIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			MemberAccountPhoneExistOut outParam = new MemberAccountPhoneExistOut();
			outParam.setExist(sysFrontAccountApi.mobileIsExist(paramIn.getMerchantId(), paramIn.getPhone(), paramIn.getAppType()));
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	@Override
	public ResultVo saveBaseInfo(MemberAccountSaveBaseInfoIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		   try {
		    SysFrontAccount sysFrontAccount = UserUtils.getUserAccount(paramIn);
            assembly(sysFrontAccount, paramIn);
            sysFrontAccount.setAmount(null);
            sysFrontAccount.setFreezeAmount(null);
            sysFrontAccount.setMarginAmount(null);
            sysFrontAccountApi.saveBaseInfo(sysFrontAccount);
		 } catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		 }
		return resultVo;
	}

	private void assembly(SysFrontAccount sysFrontAccount, MemberAccountSaveBaseInfoIn paramIn) {
        if (StringUtils.isNotEmpty(paramIn.getAccount())) {
            sysFrontAccount.setAccount(paramIn.getAccount());
        }
        if (StringUtils.isNotEmpty(paramIn.getNickName())) {
            sysFrontAccount.setNickName(paramIn.getNickName());
        }
        if (StringUtils.isNotEmpty(paramIn.getSex())) {
            sysFrontAccount.setSex(paramIn.getSex());
        }
        if (StringUtils.isNotEmpty(paramIn.getPortrait())) {
            sysFrontAccount.setPortrait(paramIn.getPortrait());
        }
        
        if (StringUtils.isNotEmpty(paramIn.getMobile())) {
            sysFrontAccount.setMobile(paramIn.getMobile());
        }
        if (StringUtils.isNotEmpty(paramIn.getBindWx())) {
            sysFrontAccount.setBindWx(paramIn.getBindWx());
        }
        if (StringUtils.isNotEmpty(paramIn.getEmail())) {
            sysFrontAccount.setEmail(paramIn.getEmail());
        }
        if (StringUtils.isNotEmpty(paramIn.getRemark())) {
            sysFrontAccount.setRemark(paramIn.getRemark());
        }
    }

	
	@Override
	public ResultVo baseInfo(MemberAccountBaseInfoIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
			SysFrontAccount account = UserUtils.getUserAccount(paramIn);
			MemberAccountBaseInfoOut outParam = new MemberAccountBaseInfoOut();
			account = sysFrontAccountApi.getAccountById(account.getId());
			outParam.setUserId(account.getId());
			outParam.setAccount(account.getAccount());
			outParam.setSex(account.getSex());
			outParam.setNickName(account.getNickName());
			outParam.setAppType(account.getUserType());
			outParam.setPortrait(account.getPortrait());
			outParam.setMiniQrCode(account.getMiniQrCode());
			outParam.setWxQrCode(account.getWxQrCode());
			outParam.setLevelVip(account.getLevelVip());
			outParam.setLevelVipDesc(account.getLevelVipDesc());
			outParam.setJobNo(account.getJobNo());
			outParam.setMobile(account.getMobile());
			outParam.setBindWx(account.getBindWx());
			outParam.setEmail(account.getEmail());
			outParam.setStatus(account.getStatus());
			outParam.setRemark(account.getRemark());
			resultVo.setData(outParam);
		} catch (RpcException e) {
			assemblyVo(resultVo, E_001, "失败");
			logger.error("", e);
		}
		return resultVo;
	}

	
	@Override
	public ResultVo updatePasswd(MemberAccountUpdatePasswdIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
            SysFrontAccount userAccount = UserUtils.getUserAccount(paramIn);
            if (!userAccount.getPassword().equals(paramIn.getOldPass())) {
                assemblyVo(resultVo, E_001, "原密码有误！");
                return resultVo;
            } else if (userAccount.getPassword().equals(paramIn.getNewPass())) {
                assemblyVo(resultVo, E_001, "新密码不能和原密码一样！");
                return resultVo;
            } else {
                SysFrontAccount newAccount = new SysFrontAccount();
                newAccount.setId(userAccount.getId());
                newAccount.setPassword(paramIn.getNewPass());
                newAccount = sysFrontAccountApi.updateAccount(newAccount);
                sessionApi.updateSessionAccount(paramIn.getMerchantId(), paramIn.getTokenId(), paramIn.getAppType(), newAccount);
            }
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
	}
	
	
	@Override
	public ResultVo updatePhone(MemberAccountUpdatePhoneIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
            SysFrontAccount userAccount = UserUtils.getUserAccount(paramIn);
            if (!userAccount.getMobile().equals(paramIn.getOldPhone())) {
                assemblyVo(resultVo, E_001, "原手机号码有误！");
                return resultVo;
            }
            if (!sysFrontAccountApi.mobileIsExist(paramIn.getMerchantId(), paramIn.getNewPhone(), paramIn.getAppType())) {
                SysFrontAccount newAccount = new SysFrontAccount();
                newAccount.setId(userAccount.getId());
                newAccount.setMobile(paramIn.getNewPhone());
                newAccount = sysFrontAccountApi.updateAccount(newAccount);
                sessionApi.updateSessionAccount(paramIn.getMerchantId(), paramIn.getTokenId(), paramIn.getAppType(), newAccount);
            } else {
                assemblyVo(resultVo, E_001, "新手机号已存在！");
                return resultVo;
            }
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
	}

	
	@Override
	public ResultVo updateWithdrawPass(MemberAccountUpdateWithdrawPassIn paramIn) {
		ResultVo resultVo = new ResultVo();
		if (!validationObj(paramIn, resultVo)) {
			return resultVo;
		}
		try {
            SysFrontAccount userAccount = UserUtils.getUserAccount(paramIn);
            if (userAccount.getTradePassword() != null && !userAccount.getTradePassword().equals(paramIn.getOldPass())) {
                assemblyVo(resultVo, E_001, "原提现密码有误！");
                return resultVo;
            } else if (userAccount.getTradePassword() != null && userAccount.getTradePassword().equals(paramIn.getNewPass())) {
                assemblyVo(resultVo, E_001, "新提现密码不能和原提现密码一样！");
                return resultVo;
            } else {
                SysFrontAccount newAccount = new SysFrontAccount();
                newAccount.setId(userAccount.getId());
                newAccount.setTradePassword(paramIn.getNewPass());
                newAccount = sysFrontAccountApi.updateAccount(newAccount);
                sessionApi.updateSessionAccount(paramIn.getMerchantId(), paramIn.getTokenId(), paramIn.getAppType(), newAccount);
                // 发送通知提醒
                // noticeApi.noticeForAll(userAccount.getId(), SMS_TYPE_9, new Object[]{userAccount.getAccount()});
            }
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
	}

	private SysFrontAccount getParentAccount(String jobNo) throws RpcException {
		// 通过工号查询用户信息
    	SysFrontAccount parentAccount = null;
    	if (StringUtils.isNotBlank(jobNo)) {
        	Map<String, Object> params = Maps.newHashMap();
        	params.put("jobNo", jobNo);
        	params.put("delFlag", Constant.DEL_FLAG_0);
        	parentAccount = sysFrontAccountApi.getAccountByParams(params);
    	}
    	return parentAccount;
	}
}