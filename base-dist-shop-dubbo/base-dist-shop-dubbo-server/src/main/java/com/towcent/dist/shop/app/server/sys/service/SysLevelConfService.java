package com.towcent.dist.shop.app.server.sys.service;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.service.BaseCrudService;
import com.towcent.dist.shop.app.client.sys.dto.SysLevelConf;

/**
 * sys_level_conf 数据库操作Service接口
 * 
 * @author huangtao
 * @date 2018-06-28 09:35:14
 * @version 1.0
 * @copyright facegarden.com
 */
public interface SysLevelConfService extends BaseCrudService {
	
	/**
	 * 获取当前级别基本信息.
	 * @Title getLevelConfByParam
	 * @param merchantId  商户Id
	 * @param level       会员等级
	 * @return
	 * @throws ServiceException
	 */
	SysLevelConf getLevelConfByParam(Integer merchantId, String level) throws ServiceException;
	
	/**
	 * 获取当前级别上一级的信息.
	 * @Title getUpLevelConfByParam
	 * @param merchantId  商户Id
	 * @param level       会员等级
	 * @return
	 * @throws ServiceException
	 */
	SysLevelConf getUpLevelConfByParam(Integer merchantId, String level) throws ServiceException;
	
	/**
	 * 查看会员等级描述.
	 * @Title queryLevelDesc
	 * @param merchantId  商户Id
	 * @param level       等级
	 * @return
	 * @throws RpcException
	 */
	String queryLevelDesc(Integer merchantId, String level) throws ServiceException;
}