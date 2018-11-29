package com.towcent.dist.shop.portal.me.biz.impl;

import static com.towcent.base.common.constants.BaseConstant.E_001;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.service.BasePortalService;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.dist.shop.app.client.me.dto.ConsigneeAddr;
import com.towcent.dist.shop.app.client.me.service.MeApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.common.Constant;
import com.towcent.dist.shop.portal.common.utils.UserUtils;
import com.towcent.dist.shop.portal.common.vo.BaseParam;
import com.towcent.dist.shop.portal.mall.vo.output.ConsigneeAddrOut;
import com.towcent.dist.shop.portal.me.biz.ConsigneeAddrService;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrDelIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrListIn;
import com.towcent.dist.shop.portal.me.vo.input.ConsigneeAddrSaveIn;
import com.towcent.dist.shop.portal.me.vo.output.ConsigneeAddrListOut;

/**
 * ConsigneeAddrServiceImpl
 *
 * @author huangtao
 * @version 0.0.1
 */
@Service
public class ConsigneeAddrServiceImpl extends BasePortalService implements ConsigneeAddrService {

    @Resource
    private MeApi meApi;

    @Override
    public ResultVo list(ConsigneeAddrListIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SimplePageDto page = this.buildPage(paramIn);
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("userId", account.getId());
            PaginationDto<ConsigneeAddr> PageDto = meApi.queryConsigneeAddrPage(params, page);

            List<ConsigneeAddrListOut> list = Lists.newArrayList();
            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {
                ConsigneeAddrListOut out = null;
                for (ConsigneeAddr entity : PageDto.getList()) {
                    out = new ConsigneeAddrListOut();
                    BeanUtils.copyProperties(entity, out);
                    list.add(out);
                }
            }

            int totalCount = null == PageDto ? 0 : PageDto.getTotalCount();
            PaginationDto<ConsigneeAddrListOut> outPage = new PaginationDto<>(totalCount, list);
            outPage.setTotalPage(page.getPageSize());

            resultVo.setData(outPage);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo save(ConsigneeAddrSaveIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            ConsigneeAddr entity = new ConsigneeAddr();
            BeanUtils.copyProperties(paramIn, entity);
            entity.setUserId(account.getId());
            meApi.saveConsigneeAddr(entity);
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo del(ConsigneeAddrDelIn paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            meApi.delConsigneeAddr(paramIn.getId());
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }

    @Override
    public ResultVo getDefault(BaseParam paramIn) {
        ResultVo resultVo = new ResultVo();
        if (!validationObj(paramIn, resultVo)) {
            return resultVo;
        }
        try {
            SysFrontAccount account = UserUtils.getUserAccount(paramIn);

            SimplePageDto pageDto = new SimplePageDto();
            pageDto.setPageNo(1);
            pageDto.setPageSize(100);

            Map<String, Object> params = Maps.newHashMap();
            params.put("merchantId", account.getMerchantId());
            params.put("userId", account.getId());

            PaginationDto<ConsigneeAddr> PageDto = meApi.queryConsigneeAddrPage(params, pageDto);

            if (null != PageDto && !CollectionUtils.isEmpty(PageDto.getList())) {

                ConsigneeAddrOut out = new ConsigneeAddrOut();
                for (ConsigneeAddr entity : PageDto.getList()) {
                    if (Constant.YES.equals(entity.getDefaultFlag())) {
                        BeanUtils.copyProperties(entity, out);
                        if (Constant.YES.equals(entity.getDefaultFlag())) {
                            break;
                        }
                        BeanUtils.copyProperties(entity, out);
                    }
                }
                if (null == out.getId()) {
                    BeanUtils.copyProperties(PageDto.getList().get(0), out);
                }
                resultVo.setData(out);
            }
        } catch (RpcException e) {
            assemblyVo(resultVo, E_001, "失败");
            logger.error("", e);
        }
        return resultVo;
    }
}
