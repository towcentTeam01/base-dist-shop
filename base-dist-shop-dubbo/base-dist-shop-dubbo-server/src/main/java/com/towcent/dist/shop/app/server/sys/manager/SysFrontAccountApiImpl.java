package com.towcent.dist.shop.app.server.sys.manager;

import static com.towcent.dist.shop.common.Constant.MEMBER_JOIN_FIRST;
import static com.towcent.dist.shop.common.Constant.RULE_JOB_NO;
import static com.towcent.dist.shop.common.Constant.WX_TEMPLATE_BACK_URL;

import java.io.File;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.qrcode.QuickMarkService;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.common.utils.FileUtils;
import com.towcent.base.common.utils.IdGen;
import com.towcent.base.common.utils.Md5Utils;
import com.towcent.base.common.utils.PictureUtils;
import com.towcent.base.common.utils.SpringFTPUtil;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysPropertyService;
import com.towcent.base.wx.service.WeixinMaService;
import com.towcent.base.wx.service.WeixinService;
import com.towcent.base.wx.service.WxInstanceFactory;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.service.SysCommonApi;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.common.ConfigUtil;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @author licheng、huangtao
 * @version 0.0.1
 * @date 2018/1/18 0018 11:39
 */
@Service
public class SysFrontAccountApiImpl extends BaseService implements SysFrontAccountApi {

	@Resource
	private BaseCommonApi baseCommonApi;
	@Resource
	private SysCommonApi sysCommonApi;
	@Resource
    private SysFrontAccountService sysFrontAccountService;
	@Resource
	private WxInstanceFactory wxInstanceFactory;
	@Resource
	private SysPropertyService propertyService;
	@Resource
	private QuickMarkService quickMarkService;
	@Resource
	private MessageChannel ftpChannel;
	
    @Override
    public SysFrontAccount loginForMiniApp(Integer merchantId, String code, String encryptedData, String iv, byte appType, Integer parentUserId) throws RpcException {
        Assert.isNotEmpty(code, "code不能为空");
        Assert.isNotEmpty(encryptedData, "encryptedData不能为空");
        Assert.isNotEmpty(iv, "iv不能为空");
        try {
        	WeixinMaService wxMaService = wxInstanceFactory.getMiniAppInstance(merchantId);
        	WxMaJscode2SessionResult sessionResult = wxMaService.getUserService().getSessionInfo(code);
        	WxMaUserInfo wxMaUser = wxMaService.getUserService().getUserInfo(sessionResult.getSessionKey(), encryptedData, iv);
            if (null == wxMaUser) {
                throw new RuntimeException("获取用户信息失败");
            }
            SysFrontAccount member = getMemberByMiniApp(wxMaUser, merchantId, appType, parentUserId);
            member.setSessionKey(sessionResult.getSessionKey());
            
            return member;
        } catch (Exception e) {
            logger.error("登录失败", e);
            throw new RpcException("", "登录失败", e);
        }
    }
	
    @Override
    public SysFrontAccount loginForWx(Integer merchantId, String code, byte appType, Integer parentUserId) throws RpcException {
    	Assert.isNotEmpty(code, "code不能为空");
    	try {
    		
        	WeixinService wxService = wxInstanceFactory.getInstance(merchantId);
        	//根据微信code换取openId 
            String openId = "";
            if(StringUtils.isNotEmpty(code)){
            	WxMpOAuth2AccessToken authToken = wxService.oauth2getAccessToken(code);
    			openId = authToken.getOpenId();
    			logger.info("user openId ： " + openId);
            }
            SysFrontAccount member = null;
        	if (StringUtils.isNotBlank(openId)) {
        		WxMpUser wxMpUser = wxService.getUserService().userInfo(openId);
            	member = getMemberByWx(wxMpUser, merchantId, appType, parentUserId);
            	
            	logger.info("微信账号信息为：" + wxMpUser.toString());
        	}
            return member;
        } catch (Exception e) {
            logger.error("登录失败", e);
            throw new RpcException("", "登录失败", e);
        }
    }
    
	@Override
	public SysFrontAccount getAccountById(Integer id) throws RpcException {
		Assert.notNull(id, "账户Id不能为空");
		try {
			SysFrontAccount account = sysFrontAccountService.getAccountById(id);
			if (StringUtils.isBlank(account.getPosterUrl())) {
            	// 生成推广二维码
				account.setPosterUrl(createUserGeneralizeQrCode(account.getMerchantId(), account.getJobNo()));
            	sysFrontAccountService.modifyById(account);
            }
			return account;
		} catch (ServiceException e) {
			logger.error("查询账号对象失败", e);
            throw new RpcException("", "查询账户对象失败", e);
		}
	}

	@Override
	public SysFrontAccount getAccountByParams(Map<String, Object> params) throws RpcException {
		try {
			return sysFrontAccountService.getAccountByParams(params);
		} catch (ServiceException e) {
			logger.error("查询账号对象失败", e);
            throw new RpcException("", "查询账户对象失败", e);
		}
	}

	@Override
	public String getUserAccountById(Integer id) throws RpcException {
		SysFrontAccount account = this.getAccountById(id);
		return null == account ? "" : account.getAccount();
	}

    @Override
    public boolean mobileIsExist(Integer merchantId, String mobile, byte appType) throws RpcException {
        Assert.isNotEmpty(mobile, "mobile不能为空");
    	try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", merchantId);
            params.put("mobile", mobile);
            params.put("userType", appType + "");
            return sysFrontAccountService.findCount(params) > 0;
        } catch (ServiceException e) {
            logger.error("判断账户手机号码失败");
            throw new RpcException("", "判断账户手机号码失败", e);
        }
    }

    @Override
    public boolean emailIsExist(Integer merchantId, String email, byte appType) throws RpcException {
    	Assert.isNotEmpty(email, "email不能为空");
    	try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", merchantId);
            params.put("email", email);
            params.put("userType", appType + "");
            return sysFrontAccountService.findCount(params) > 0;
        } catch (ServiceException e) {
            logger.error("检查邮箱是否存在失败");
            throw new RpcException("", "检查邮箱是否存在失败", e);
        }
    }

    @Override
    public boolean accountIsExist(Integer merchantId, String account, byte appType) throws RpcException {
    	Assert.isNotEmpty(account, "account不能为空");
    	try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", merchantId);
            params.put("account", account);
            params.put("userType", appType + "");
            return sysFrontAccountService.findCount(params) > 0;
        } catch (ServiceException e) {
            logger.error("检查账户名失败");
            throw new RpcException("", "检查账户名失败", e);
        }
    }

    @Override
    public boolean reset(SysFrontAccount account) throws RpcException {
    	Assert.notNull(account, "账户对象不能为空");
    	Map<String, Object> params = Maps.newHashMap();
    	params.put("merchantId", account.getMerchantId());
    	params.put("mobile", account.getMobile());
    	params.put("userType", account.getUserType());
    	SysFrontAccount oldAccount = this.getAccountByParams(params);
        try {
            account.setId(oldAccount.getId());
            account.setPassword(account.getPassword());
            return sysFrontAccountService.modifyById(account) > 0;
        } catch (ServiceException e) {
            logger.error("重置密码失败", e);
            throw new RpcException("", "重置密码失败", e);
        }
    }

	@Override @Transactional
	public boolean register(Integer merchantId, String phone, String password, byte appType, Integer parentUserId) throws RpcException {
		Assert.isNotEmpty(phone, "手机号码不能为空");
		Assert.isNotEmpty(password, "密码不能为空");
		boolean result = false;
		try {
			SysFrontAccount sysFrontAccount = new SysFrontAccount();
			sysFrontAccount.setMobile(phone);
			sysFrontAccount.setAccount(phone);
			sysFrontAccount.setPassword(password);
			sysFrontAccount.setUserType(appType + "");
			sysFrontAccount.setCreateDate(new Date());
			sysFrontAccount.setLoginTimes(0);
			sysFrontAccount.setMerchantId(merchantId);
			sysFrontAccount.setParentId(parentUserId);
			result = sysFrontAccountService.add(sysFrontAccount) > 0;
			
		} catch (ServiceException e) {
			logger.error("注册失败", e);
            throw new RpcException("", "注册失败", e);
		}
		return result;
	}
	
	@Override
	public boolean saveBaseInfo(SysFrontAccount sysFrontAccount) throws RpcException {
		try {
			return sysFrontAccountService.modifyById(sysFrontAccount) > 0;
		} catch (ServiceException e) {
			logger.error("更新账号资料失败", e);
			throw new RpcException("", "更新账号资料失败", e);
		}
	}

	@Override
	public SysFrontAccount updateAccount(SysFrontAccount account) throws RpcException {
		try {
			boolean flag = sysFrontAccountService.modifyById(account) > 0;
			if (flag) {
				return sysFrontAccountService.getAccountById(account.getId());
			} else {
				return null;
			}
		} catch (ServiceException e) {
			logger.error("更新账号资料失败", e);
			throw new RpcException("", "更新账号资料失败", e);
		}
	}
	
	// 用于小程序获取用户信息
    private SysFrontAccount getMemberByMiniApp(WxMaUserInfo wxMaUser, Integer merchantId, byte appType, Integer parentUserId) throws RpcException {
        try {
        	Map<String, Object> params = Maps.newHashMap();
        	params.put("merchantId", merchantId);
        	params.put("unionId", wxMaUser.getUnionId());
        	List<SysFrontAccount> list = sysFrontAccountService.findByBiz(params);
        	SysFrontAccount member = null;
        	if (!CollectionUtils.isEmpty(list)) {
        		member = list.get(0);
        	}
            if (null == member) {
                member = new SysFrontAccount();
                String jobNo = baseCommonApi.getSerialNo(merchantId, RULE_JOB_NO);
                member.setAccount(jobNo);  // 暂时将工号当做账号
                member.setPassword(Md5Utils.encryption(jobNo));
                member.setJobNo(jobNo);   // 工号
                
                member.setUserType(appType + "");
                member.setCreateDate(new Date());
                member.setLoginTimes(0);

                member.setMiniOpenId(wxMaUser.getOpenId());
                member.setUnionId(wxMaUser.getUnionId());
                member.setNickName(wxMaUser.getNickName());
                member.setPortrait(wxMaUser.getAvatarUrl());
                member.setSex(wxMaUser.getGender());
                
                member.setMerchantId(merchantId);  // 商户
                member.setParentId(parentUserId);  // 上级用户Id
                
                sysFrontAccountService.add(member);
            } else {
                if (StringUtils.isNotBlank(member.getMiniOpenId())) {
                	member.setMiniOpenId(wxMaUser.getOpenId());
                	sysFrontAccountService.modifyById(member);
                }
            }
            return member;
        } catch (ServiceException e) {
            logger.error("登录失败", e);
            throw new RpcException("", "登录失败", e);
        }
    }
    
    private SysFrontAccount getMemberByWx(WxMpUser wxMpUser, Integer merchantId, byte appType, Integer parentUserId) throws RpcException {
        try {
        	Map<String, Object> params = Maps.newHashMap();
        	params.put("merchantId", merchantId);
        	// params.put("unionId", wxMpUser.getUnionId());
        	params.put("openId", wxMpUser.getOpenId());
        	List<SysFrontAccount> list = sysFrontAccountService.findByBiz(params);
        	SysFrontAccount member = null;
        	if (!CollectionUtils.isEmpty(list)) {
        		member = list.get(0);
        	}
            if (null == member) {
                member = new SysFrontAccount();
                String jobNo = baseCommonApi.getSerialNo(merchantId, RULE_JOB_NO);
                member.setAccount(jobNo);  // 暂时将工号当做账号
                member.setPassword(Md5Utils.encryption(jobNo));
                member.setJobNo(jobNo);   // 工号
                
                member.setUserType(appType + "");
                member.setCreateDate(new Date());
                member.setLoginTimes(0);

                member.setOpenId(wxMpUser.getOpenId());
                member.setUnionId(wxMpUser.getUnionId());
                member.setNickName(wxMpUser.getNickname());
                member.setPortrait(wxMpUser.getHeadImgUrl());
                if (wxMpUser.getSex() != null && (1 == wxMpUser.getSex() || 2 == wxMpUser.getSex())) {
                	member.setSex(wxMpUser.getSex() + "");
                } else {
                	member.setSex("1");
                }
                
                member.setMerchantId(merchantId);  // 商户
                member.setParentId(parentUserId);  // 上级用户Id
                
                // 生成推广二维码
                member.setPosterUrl(createUserGeneralizeQrCode(merchantId, jobNo));
                
                if (sysFrontAccountService.add(member) > 0) {
                	if (null != parentUserId) {
                		// 发送微信公众号模板消息
                		SysFrontAccount parentAccount = this.getAccountById(parentUserId);
                		if (null != parentAccount.getOpenId()) {
	                		WxMpTemplateMessage message = this.assembleTemplateMssage(parentAccount.getOpenId(), member);
	                		sysCommonApi.sendWxTemplateMessage(merchantId, message);
                		}
                	}
                }
                
            } else {
                if (StringUtils.isNotBlank(member.getMiniOpenId())) {
                	member.setOpenId(wxMpUser.getOpenId());
                	sysFrontAccountService.modifyById(member);
                }
                if (StringUtils.isBlank(member.getPosterUrl())) {
                	// 生成推广二维码
                    member.setPosterUrl(createUserGeneralizeQrCode(merchantId, member.getJobNo()));
                	sysFrontAccountService.modifyById(member);
                }
            }
            return member;
        } catch (ServiceException e) {
            logger.error("登录失败", e);
            throw new RpcException("", "登录失败", e);
        }
    }
    
    /**
     * 创建推广二维码.
     * @param merchantId  商户Id
     * @param jobNo       工号
     * @return
     * @throws ServiceException 
     */
    private String createUserGeneralizeQrCode(Integer merchantId, String jobNo) throws ServiceException {
    	try {
			String link = propertyService.getSysPropertyToString(merchantId, "share_shop_link");
			String content = link + jobNo; 
			String tempFileName = IdGen.uuid() + PictureUtils.JPG;
			String tempPath = this.getTempPath();
			quickMarkService.encoderGQRCode2File(content, tempPath + tempFileName, "png", 450);
			
			// 图片类型
			String relativePath = baseCommonApi.getImageRelativePath(merchantId, 9);
			SpringFTPUtil.ftpUpload(ftpChannel, new File(tempPath + tempFileName), relativePath);
			
			// 生成后的海报图片地址
			String qrCodeUrl = StringUtils.substringBeforeLast(ConfigUtil.getUrlHeader(), "/") + relativePath + tempFileName;
			// 删除临时文件
			FileUtils.deleteFile(tempPath + tempFileName);
			return qrCodeUrl;
		} catch (Exception e) {
			logger.error("生成推广二维码失败", e);
            throw new ServiceException("生成推广二维码失败", e);
		}
    }
    
	/**
	 * 新会员加入通知<br>
	 * 装配微信公众模板消息Vo.
	 * @Title assembleTemplateMssage
	 * @param openId   目标用户的openId（上级用户）
	 * @param account  新加入的会员对象
	 * @return
	 * @throws ServiceException 
	 */
	private WxMpTemplateMessage assembleTemplateMssage(String openId, SysFrontAccount account) throws ServiceException {
		if (StringUtils.isBlank(account.getOpenId())) return null;
		WxMpTemplateMessage vo = new WxMpTemplateMessage();
		vo.setToUser(openId);
		vo.setTemplateId("h-IEuLO5NWjbLaNKjldnn8OC-tJE7toauvf1-4ELSPY");  // 新会员加入提醒的消息模板Id
		// 返回公众号的链接地址
		vo.setUrl(propertyService.getSysPropertyToString(account.getMerchantId(), WX_TEMPLATE_BACK_URL));
		vo.addData(new WxMpTemplateData("first", MessageFormat.format(MEMBER_JOIN_FIRST, account.getNickName())));
		vo.addData(new WxMpTemplateData("keyword1", account.getJobNo()));
		vo.addData(new WxMpTemplateData("keyword2", DateUtils.formatDateTime(account.getCreateDate())));
		return vo;
	}
}