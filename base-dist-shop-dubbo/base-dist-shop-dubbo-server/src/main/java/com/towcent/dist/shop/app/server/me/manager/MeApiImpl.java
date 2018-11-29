package com.towcent.dist.shop.app.server.me.manager;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.base.common.constants.BaseConstant.NO;
import static com.towcent.base.common.constants.BaseConstant.YES;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.model.SysDictDtl;
import com.towcent.base.common.page.PaginationDto;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.common.page.SimplePageDto;
import com.towcent.base.common.utils.Assert;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysAreaService;
import com.towcent.dist.shop.app.client.mall.sevice.OrderApi;
import com.towcent.dist.shop.app.client.me.dto.ConcernGoods;
import com.towcent.dist.shop.app.client.me.dto.ConsigneeAddr;
import com.towcent.dist.shop.app.client.me.service.MeApi;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.app.server.me.service.ConcernGoodsService;
import com.towcent.dist.shop.app.server.me.service.ConsigneeAddrService;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;

@Service
public class MeApiImpl extends BaseService implements MeApi {

	private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
	
	@Resource
	private ConcernGoodsService concernGoodsService;
	@Resource
	private ConsigneeAddrService consigneeAddrService;
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private SysFrontAccountService sysFrontAccountService;
	@Resource
	private BaseCommonApi baseCommonApi;
	@Resource
	private SysAreaService areaService;
	@Resource
	private OrderApi orderApi;

	@Override
	public Map<String, Object> memberCenter(SysFrontAccount account) throws RpcException {
		try {
			Map<String, Object> map = Maps.newHashMap();
			account = sysFrontAccountService.getAccountById(account.getId());

			Map<String, Object> member = Maps.newHashMap();
			member.put("nickName", account.getNickName());
			member.put("levelVip", account.getLevelVip());
			SysDictDtl dict = baseCommonApi.getDictByKeyVal(account.getMerchantId(), "level_vip", account.getLevelVip());
			if(null != dict){
				member.put("levelVipDesc", dict.getName());
			}
			member.put("headimgurl", account.getPortrait());
			map.put("member", member);

			Map<String, Object> params = Maps.newHashMap();
			params.put("merchantId", account.getMerchantId());
			params.put("createBy", account.getId());
			params.put("delFlag", DEL_FLAG_0);
			Map<String, Object> order = orderMainService.queryOrderNum(params);
			map.put("order", order);

			Map<String, Object> wallet = Maps.newHashMap();
			wallet.put("marginAmount", account.getMarginAmount());
			wallet.put("settledAmount", account.getSettledAmount());
			wallet.put("amount", account.getAmount());
			wallet.put("integral", account.getInter());
			map.put("wallet", wallet);

			final Integer userId = account.getId();
			// 异步更新用户已经发货的订单的物流信息
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						orderApi.updateLogisticsTrace(userId);
					} catch (Exception e) {
						logger.error("更新物流信息失败", e);
					}
				}
			});
			return map;
		} catch (ServiceException e) {
			logger.error("查询个人中心信息失败", e);
			throw new RpcException("", "查询个人中心信息失败", e);
		}
	}

	@Override
	public PaginationDto<ConsigneeAddr> queryConsigneeAddrPage(Map<String, Object> params, SimplePageDto pageDto)
			throws RpcException {
		try {
			params.put("delFlag", DEL_FLAG_0);
			int totalCount = consigneeAddrService.findCount(params);
			SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
			List<ConsigneeAddr> list = consigneeAddrService.findByPage(page, "create_date", "DESC", params);
			return new PaginationDto<>(totalCount, list);
		} catch (ServiceException e) {
			logger.error("查询收货地址失败", e);
			throw new RpcException("", "查询收货地址失败", e);
		}
	}

	@Override
	@Transactional
	public boolean saveConsigneeAddr(ConsigneeAddr entity) throws RpcException {
		try {
			entity.setAddress(getFullAddress(entity.getProvince(), entity.getCity(), entity.getDistrict(), entity.getDetailAddr()));
			if (null != entity.getId()) {
				ConsigneeAddr addr = consigneeAddrService.findById(entity);
				if (null != addr) {
					entity.setId(addr.getId());
					entity.setUpdateDate(new Date());
					entity.setUpdateBy(entity.getUserId());

					setDefaultAddr(entity);

					return consigneeAddrService.modifyById(entity) > 0;
				}
			}
			entity.setCreateBy(entity.getUserId());
			entity.setUpdateBy(entity.getUserId());
			entity.setCreateDate(new Date());
			entity.setUpdateDate(new Date());

			setDefaultAddr(entity);

			return consigneeAddrService.add(entity) > 0;
		} catch (ServiceException e) {
			logger.error("保存收货地址失败", e);
			throw new RpcException("", "保存收货地址失败", e);
		}
	}

	private void setDefaultAddr(ConsigneeAddr addr) throws ServiceException {
		if (YES.equals(addr.getDefaultFlag())) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("defaultFlag", YES);
			map.put("delFlag", DEL_FLAG_0);
			map.put("userId", addr.getUserId());
			map.put("merchantId", addr.getMerchantId());
			List<ConsigneeAddr> list = consigneeAddrService.findByBiz(map);
			if (!CollectionUtils.isEmpty(list)) {
				for (ConsigneeAddr entity : list) {
					entity.setDefaultFlag(NO);
					consigneeAddrService.modifyById(entity);
				}
			}
		}
	}

	@Override
	public boolean delConsigneeAddr(Integer id) throws RpcException {
		Assert.notNull(id, "id不能为空");

		try {
			ConsigneeAddr entity = new ConsigneeAddr();
			entity.setId(id);
			int num = consigneeAddrService.deleteById(entity);
			return num > 0;
		} catch (ServiceException e) {
			logger.error("删除收货地址失败", e);
			throw new RpcException("", "删除收货地址失败", e);
		}
	}

	@Override
	public ConsigneeAddr getConsigneeAddr(Integer id) throws RpcException {
		try {
			ConsigneeAddr entity = new ConsigneeAddr();
			entity.setId(id);
			return consigneeAddrService.findById(entity);
		} catch (ServiceException e) {
			logger.error("获取收货地址失败", e);
			throw new RpcException("", "获取收货地址失败", e);
		}
	}

	@Override
	public PaginationDto<ConcernGoods> queryCollectGoodsPage(Map<String, Object> params, SimplePageDto pageDto)
			throws RpcException {
		try {
			params.put("delFlag", DEL_FLAG_0);
			int totalCount = concernGoodsService.findCount(params);
			SimplePage page = new SimplePage(pageDto.getPageNo(), pageDto.getPageSize(), totalCount);
			List<ConcernGoods> list = concernGoodsService.findByPage(page, "create_date", "DESC", params);
			return new PaginationDto<>(totalCount, list);
		} catch (ServiceException e) {
			logger.error("查询收藏商品失败", e);
			throw new RpcException("", "查询收藏商品失败", e);
		}
	}

	@Override
	public boolean delConcernGoods(Integer id) throws RpcException {
		Assert.notNull(id, "id不能为空");

		try {
			ConcernGoods entity = new ConcernGoods();
			entity.setId(id);
			int num = concernGoodsService.deleteById(entity);
			return num > 0;
		} catch (ServiceException e) {
			logger.error("删除收藏商品失败", e);
			throw new RpcException("", "删除收藏商品失败", e);
		}
	}
	
	/**
	 * 获取省/市/区全地址.
	 * @param provinceId  省ID
	 * @param cityId      城市ID
	 * @param districtId  区县ID
	 * @param address     详细地址
	 * @return
	 * @throws ServiceException 
	 */
	private String getFullAddress(Integer provinceId, Integer cityId, Integer districtId, String address) throws ServiceException {
		String fullArea = areaService.getAreaDesc(provinceId, cityId, districtId, "");
		return fullArea + address;
	}
}
