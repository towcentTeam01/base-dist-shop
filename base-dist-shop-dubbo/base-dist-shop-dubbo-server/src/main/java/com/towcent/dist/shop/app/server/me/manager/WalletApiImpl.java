/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-dubbo-server
 * @Title : WalletApiImpl.java
 * @Package : com.towcent.dist.shop.app.server.me.manager
 * @date : 2018年6月30日下午12:03:36
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.me.manager;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.dist.shop.common.Constant.ID;
import static com.towcent.dist.shop.common.Constant.WITHDRAW_APPLY_0;
import static com.towcent.dist.shop.common.Constant.WITHDRAW_APPLY_2;
import static com.towcent.dist.shop.common.Constant.WX_TEMPLATE_BACK_URL;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.mall.dto.CouponClaim;
import com.towcent.dist.shop.app.client.me.service.WalletApi;
import com.towcent.dist.shop.app.client.me.vo.AccountRecordVo;
import com.towcent.dist.shop.app.client.sys.dto.SysAmountRecord;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.client.sys.dto.SysIntegralRecord;
import com.towcent.dist.shop.app.client.sys.dto.SysWithdrawApply;
import com.towcent.dist.shop.app.server.mall.service.CouponClaimService;
import com.towcent.dist.shop.app.server.sys.service.SysAmountRecordService;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.app.server.sys.service.SysIntegralRecordService;
import com.towcent.dist.shop.app.server.sys.service.SysWithdrawApplyService;
import com.towcent.dist.shop.common.Constant;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * @author huangtao
 * @version 1.0.0
 * @ClassName: WalletApiImpl
 * @Description: 钱包相关接口实现类
 * @date 2018年6月30日 下午12:03:36
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Service
public class WalletApiImpl extends BaseService implements WalletApi {

    @Resource
    private SysAmountRecordService sysAmountRecordService;
    @Resource
    private CouponClaimService couponClaimService;
    @Resource
    private SysIntegralRecordService sysIntegralRecordService;
    @Resource
    private SysFrontAccountService sysFrontAccountService;
    @Resource
    private SysWithdrawApplyService withdrawApplyService;
    @Resource
    private SysPropertyService propertyService;

    @Override
    public PaginationDto<AccountRecordVo> queryIncomePage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException {
        Object tabFlag = params.get("tabFlag");

        List<AccountRecordVo> outList = Lists.newArrayList();
        try {
            params.put("delFlag", DEL_FLAG_0);

            AccountRecordVo vo = null;
            if (Constant.ACCOUNT_RECORD_INTEGRAL.equals(tabFlag)) {
                int totalCount = sysIntegralRecordService.findCount(params);
                SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
                List<SysIntegralRecord> list = sysIntegralRecordService.findByPage(page, "create_date", "DESC", params);
                if (null != list && !CollectionUtils.isEmpty(list)) {
                    for (SysIntegralRecord entity : list) {
                        vo = new AccountRecordVo();
                        BeanUtils.copyProperties(entity, vo);
                        outList.add(vo);
                    }
                }
                return new PaginationDto<>(totalCount, outList);
            } else {
                int totalCount = sysAmountRecordService.findCount(params);
                SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
                List<SysAmountRecord> list = sysAmountRecordService.findByPage(page, "create_date", "DESC", params);
                if (null != list && !CollectionUtils.isEmpty(list)) {
                    for (SysAmountRecord entity : list) {
                        vo = new AccountRecordVo();
                        BeanUtils.copyProperties(entity, vo);
                        outList.add(vo);
                    }
                }
                return new PaginationDto<>(totalCount, outList);
            }
        } catch (ServiceException e) {
            logger.error("查询消费记录失败", e);
            throw new RpcException("", "查询消费记录失败", e);
        }
    }

    @Override
    public PaginationDto<CouponClaim> queryCouponPage(Map<String, Object> params, SimplePageDto pageDto)
            throws RpcException {
        try {
            params.put("delFlag", DEL_FLAG_0);
            int totalCount = couponClaimService.findCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<CouponClaim> list = couponClaimService.findByPage(page, "create_date", "DESC", params);
            return new PaginationDto<>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询优惠券领取分页列表失败", e);
            throw new RpcException("", "查询优惠券领取分页失败", e);
        }
    }

    @Override
    public boolean withdrawApply(Integer userId, BigDecimal amount) throws RpcException {
        Assert.notNull(userId, "userId不能为空");
        Assert.notNull(amount, "amount不能为空");
        try {
            SysFrontAccount account = sysFrontAccountService.getAccountById(userId);
            if (account.getAmount().compareTo(amount) < 0) {
                throw new RpcException("", "申请金额大于余额, 申请提现失败!");
            }

            SysWithdrawApply apply = new SysWithdrawApply();
            apply.setAmount(amount);
            apply.setStatus(WITHDRAW_APPLY_0);  // 已申请
            apply.setCreateBy(userId);
            apply.setCreateDate(new Date());
            apply.setUpdateBy(apply.getCreateBy());
            apply.setUpdateDate(apply.getCreateDate());

            return withdrawApplyService.add(apply) > 0;

            // TODO 接入微信企业付款功能（直接转到微信零钱）
        } catch (ServiceException e) {
            logger.error("查询优惠券领取分页列表失败", e);
            throw new RpcException("", "查询优惠券领取分页失败", e);
        }
    }

    @Override
    public PaginationDto<SysWithdrawApply> withdrawList(Integer userId, SimplePageDto pageDto) throws RpcException {
        Assert.notNull(userId, "userId不能为空");
        try {
            Map<String, Object> params = Maps.newHashMap();
            params.put("createBy", userId);
            params.put("delFlag", DEL_FLAG_0);
            int totalCount = withdrawApplyService.findCount(params);
            SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
            List<SysWithdrawApply> list = withdrawApplyService.findByPage(page, "a.create_date", "DESC", params);
            return new PaginationDto<SysWithdrawApply>(totalCount, list);
        } catch (ServiceException e) {
            logger.error("查询提现申请列表失败", e);
            throw new RpcException("", "查询提现申请列表失败", e);
        }
    }

    @Override
    public boolean withdrawMark(Integer userId, Integer applyId) throws RpcException {
        Assert.notNull(userId, "userId不能为空");
        Assert.notNull(userId, "applyId不能为空");
        try {
            // TODO 1. 应该判断是否为该商户的管理员，否则不能操作此接口

            // 2. 判断该用户与提现申请人是否同商户，否则也不能操作此接口

            SysWithdrawApply apply = withdrawApplyService.findByKeyValSingle(ID, applyId);
            if (null == apply || StringUtils.equals(WITHDRAW_APPLY_2, apply.getStatus())) {
                // 提现申请不存在、提现申请已被处理，则操作终止
                return false;
            }

            apply.setStatus(WITHDRAW_APPLY_2);
            apply.setUpdateBy(userId);
            apply.setUpdateDate(new Date());
            boolean result = withdrawApplyService.modifyById(apply) > 0;

            if (result) {
                // 标记提现申请已处理，则推送微信模板消息
                SysFrontAccount account = sysFrontAccountService.getAccountById(apply.getCreateBy());
                this.assembleTemplateMssage(account, apply.getAmount(), apply.getUpdateDate());
            }

            return result;
        } catch (ServiceException e) {
            logger.error("查询提现申请列表失败", e);
            throw new RpcException("", "查询提现申请列表失败", e);
        }
    }

    /**
     * 提现成功通知<br>
     * 装配微信公众模板消息Vo.
     *
     * @param account 会员对象
     * @param amount  提现金额
     * @param date    时间(格式化)
     * @return
     * @throws ServiceException
     * @Title assembleTemplateMssage
     */
    private WxMpTemplateMessage assembleTemplateMssage(SysFrontAccount account, BigDecimal amount, Date date) throws ServiceException {
        if (StringUtils.isBlank(account.getOpenId())) return null;
        WxMpTemplateMessage vo = new WxMpTemplateMessage();
        vo.setToUser(account.getOpenId());
        vo.setTemplateId("WunfIClCn7Grsdb2YZatc2_VFfhQxRiOw0g-tMgzg78");  // 提现提醒的消息模板Id
        // 返回公众号的链接地址
        vo.setUrl(propertyService.getSysPropertyToString(account.getMerchantId(), WX_TEMPLATE_BACK_URL));
        vo.addData(new WxMpTemplateData("first", Constant.WITHDRAW_APPLY_FIRST));
        vo.addData(new WxMpTemplateData("keyword1", amount + "元"));
        vo.addData(new WxMpTemplateData("keyword2", DateUtils.formatDateTime(date)));
        return vo;
    }

}

	