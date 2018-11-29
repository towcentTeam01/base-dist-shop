package com.towcent.dist.shop.app.server.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.client.sys.dto.SysLevelConf;
import com.towcent.dist.shop.app.server.sys.dao.SysLevelConfMapper;
import com.towcent.dist.shop.app.server.sys.service.SysLevelConfService;
import com.towcent.dist.shop.common.Constant;

/**
 * 
 * @author huangtao
 * @date 2018-06-28 09:35:14
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("sysLevelConfServiceImpl")
public class SysLevelConfServiceImpl extends BaseCrudServiceImpl implements SysLevelConfService {

    @Resource
    private SysLevelConfMapper sysLevelConfMapper;

    @Override
    public CrudMapper init() {
        return sysLevelConfMapper;
    }

    @Override
    public SysLevelConf getLevelConfByParam(Integer merchantId, String level) throws ServiceException {
    	try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("merchantId", merchantId);
			params.put("level", level);
			params.put("delFlag", Constant.DEL_FLAG_0);
			
			List<SysLevelConf> list = this.findByBiz(params);
			return CollectionUtils.isEmpty(list) ? null : list.get(0);
		} catch (Exception e) {
			throw new ServiceException("", e);
		}
    }
    
    @Override
    public SysLevelConf getUpLevelConfByParam(Integer merchantId, String level) throws ServiceException {
    	Integer upLevel = Integer.valueOf(level) + 1;
    	return this.getLevelConfByParam(merchantId, upLevel + "");
    }
    
    @Override
    public String queryLevelDesc(Integer merchantId, String level) throws ServiceException {
    	SysLevelConf conf = this.getLevelConfByParam(merchantId, level);
    	return null == conf ? "未知" : conf.getLevelName();
    }
}