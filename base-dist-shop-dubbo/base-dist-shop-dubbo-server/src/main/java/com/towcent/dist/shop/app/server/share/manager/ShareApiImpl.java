/**
 * 
 */
package com.towcent.dist.shop.app.server.share.manager;

import static com.towcent.dist.shop.common.Constant.ID;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.service.BaseService;
import com.towcent.base.service.SysPropertyService;
import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.client.mall.utils.GoodsUtils;
import com.towcent.dist.shop.app.client.share.dto.ShareCategory;
import com.towcent.dist.shop.app.client.share.dto.ShareInfo;
import com.towcent.dist.shop.app.client.share.service.ShareApi;
import com.towcent.dist.shop.app.client.share.vo.ShareVo;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import com.towcent.dist.shop.app.server.mall.service.GoodsService;
import com.towcent.dist.shop.app.server.share.service.ShareCategoryService;
import com.towcent.dist.shop.app.server.share.service.ShareInfoService;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.common.ConfigUtil;

/**
 * @author shiwei
 *
 */
@Service
public class ShareApiImpl extends BaseService implements ShareApi {
	
	@Resource
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Resource
	private GoodsService goodsService;
	@Resource
	private ShareCategoryService shareCategoryService;
	@Resource
	private ShareInfoService shareInfoService;
	@Resource
	private SysPropertyService propertyService;
	@Resource
	private SysFrontAccountService sysFrontAccountService;

	@Override
	public ShareVo shareProduct(final Integer userId, final Integer productId)
			throws RpcException {
		try {
			ShareVo vo = new ShareVo();
			final Goods goods = goodsService.findByKeyValSingle(ID, productId);
			
			// 获取分享内容
			ShareCategory shareCategory = getShareContent(goods);

			if (null != shareCategory) {
				BeanUtils.copyProperties(shareCategory, vo);
			} else {
				// 先查询配置中是否包含
				String shareProductTitle = propertyService.getSysPropertyToString(goods.getMerchantId(), "share_product_title");
				String shareProductDesc = propertyService.getSysPropertyToString(goods.getMerchantId(), "share_product_desc");
				vo.setShareDesc(goods.getGoodsName() +" "+ shareProductDesc);
				vo.setShareTitle(shareProductTitle +" "+goods.getGoodsName());
				vo.setShareImage(GoodsUtils.getGoodsListPicUrl(ConfigUtil.getUrlHeader(), goods.getMainUrls(), goods.getDescPicV()));
			}
			final SysFrontAccount sysFrontAccount = sysFrontAccountService.findByKeyValSingle(ID, userId);
			// 分享链接拼接
			String shareProductLink = propertyService.getSysPropertyToString(goods.getMerchantId(), "share_product_link");
			StringBuffer shareUrl = new StringBuffer();
			
			shareUrl.append(shareProductLink);
			shareUrl.append(productId);
			shareUrl.append("&jobNo=");
			shareUrl.append(sysFrontAccount.getJobNo());
			
			vo.setShareUrl(shareUrl.toString());
			
			final ShareVo vo1 = vo;
			taskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						// 插入分享信息表
						ShareInfo shareInfo = new ShareInfo();
						
						BeanUtils.copyProperties(goods, shareInfo);
						BeanUtils.copyProperties(vo1, shareInfo);
						shareInfo.setProductId(productId);
						shareInfo.setCreateBy(userId);
						shareInfo.setJobNo(sysFrontAccount.getJobNo());
						shareInfo.setMerchantId(sysFrontAccount.getMerchantId());
						Map<String, Object> map = Maps.newHashMap();
						map.put("createBy", userId);
						map.put("productId", productId);
						map.put("jobNo", sysFrontAccount.getJobNo());
						map.put("merchantId", sysFrontAccount.getMerchantId());
						map.put("shareUrl", vo1.getShareUrl());
						if(shareInfoService.findCount(map) == 0)
							shareInfoService.add(shareInfo);
					} catch (ServiceException e) {
						logger.error("插入分享信息失败", e);
					}
				}
			});
			
			return vo;
		} catch (ServiceException e) {
			logger.error("分享失败", e);
			throw new RpcException("分享失败", e);
		}

	}

	/**
	 * 获取分享内容
	 * @param goods
	 * @param shareCategory
	 * @return
	 * @throws ServiceException
	 */
	private ShareCategory getShareContent(Goods goods) throws ServiceException {
		ShareCategory shareCategory = null;
		// 通过商户id查询对应分类下是否有分享内容信息 没有则通过商品名称组装
		Map<String, Object> map = Maps.newHashMap();

		map.put("merchantId", goods.getMerchantId());
		map.put("categoryId", goods.getCategoryId());
		List<ShareCategory> list = shareCategoryService.findByBiz(map);
		
		if (!CollectionUtils.isEmpty(list)) {
			shareCategory = list.get(0);
		} else {
			map.remove("categoryId");
			String[] structureNos = goods.getStructureNo().split(",");
			if (structureNos.length == 3) {
				map.put("structureNo", structureNos[0] + "," + structureNos[1]);
			} else if (structureNos.length == 2) {
				map.put("structureNo", structureNos[0] + ",");
			} else if (structureNos.length == 1) {
				map.put("structureNo", goods.getStructureNo());
			}

			// 查询上一级分享分类内容
			list = shareCategoryService.findByBiz(map);
			if (!CollectionUtils.isEmpty(list)) {
				shareCategory = list.get(0);
			} else {
				if (structureNos.length == 3) {
					map.put("structureNo", structureNos[0] + ",");
				} else {
					map.put("structureNo", goods.getStructureNo());
				}

				// 查询一级
				list = shareCategoryService.findByBiz(map);
				if (!CollectionUtils.isEmpty(list)) {
					shareCategory = list.get(0);
				}
			}
		}
		return shareCategory;
	}

	@Override
	public ShareVo shareShop(final Integer userId) throws RpcException {
		try {
			ShareVo vo = new ShareVo();
			final SysFrontAccount sysFrontAccount = sysFrontAccountService.findByKeyValSingle("id", userId);
			// 先查询配置中是否包含
			String shareShopTitle = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_shop_title");
			String shareShopDesc = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_shop_desc");
			String shareShopImage = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_shop_image");
			vo.setShareDesc(shareShopDesc);
			vo.setShareTitle(shareShopTitle);
			vo.setShareImage(shareShopImage);
			
			// 分享链接拼接
			String shareShopLink = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_shop_link");
			StringBuffer shareUrl = new StringBuffer();
			
			shareUrl.append(shareShopLink);
			shareUrl.append(sysFrontAccount.getJobNo());
			
			vo.setShareUrl(shareUrl.toString());
			
			final ShareVo vo1 = vo;
			taskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						// 插入分享信息表
						ShareInfo shareInfo = new ShareInfo();
						
						BeanUtils.copyProperties(vo1, shareInfo);
						shareInfo.setCreateBy(userId);
						shareInfo.setJobNo(sysFrontAccount.getJobNo());
						shareInfo.setMerchantId(sysFrontAccount.getMerchantId());
						Map<String, Object> map = Maps.newHashMap();
						map.put("createBy", userId);
						map.put("jobNo", sysFrontAccount.getJobNo());
						map.put("merchantId", sysFrontAccount.getMerchantId());
						map.put("shareUrl", vo1.getShareUrl());
						if(shareInfoService.findCount(map) == 0)
							shareInfoService.add(shareInfo);
					} catch (ServiceException e) {
						logger.error("插入分享信息失败", e);
					}
				}
			});
			
			return vo;
		} catch (ServiceException e) {
			logger.error("分享失败", e);
			throw new RpcException("分享失败", e);
		}

	}

	@Override
	public ShareVo shareFriend(final Integer userId) throws RpcException {
		try {
			ShareVo vo = new ShareVo();
			final SysFrontAccount sysFrontAccount = sysFrontAccountService.findByKeyValSingle("id", userId);
			// 先查询配置中是否包含
			String shareFriendTitle = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_friend_title");
			String shareFriendDesc = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_friend_desc");
			String shareFriendImage = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_friend_image");
			vo.setShareDesc(shareFriendDesc);
			vo.setShareTitle(shareFriendTitle);
			vo.setShareImage(shareFriendImage);
			
			// 分享链接拼接
			String shareInviteLink = propertyService.getSysPropertyToString(sysFrontAccount.getMerchantId(), "share_invite_link");
			StringBuffer shareUrl = new StringBuffer();
			shareUrl.append(shareInviteLink);
			shareUrl.append(sysFrontAccount.getJobNo());
			vo.setShareUrl(shareUrl.toString());
			
			final ShareVo vo1 = vo;
			taskExecutor.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						// 插入分享信息表
						ShareInfo shareInfo = new ShareInfo();
						
						BeanUtils.copyProperties(vo1, shareInfo);
						shareInfo.setCreateBy(userId);
						shareInfo.setJobNo(sysFrontAccount.getJobNo());
						shareInfo.setMerchantId(sysFrontAccount.getMerchantId());
						Map<String, Object> map = Maps.newHashMap();
						map.put("createBy", userId);
						map.put("jobNo", sysFrontAccount.getJobNo());
						map.put("merchantId", sysFrontAccount.getMerchantId());
						map.put("shareUrl", vo1.getShareUrl());
						if(shareInfoService.findCount(map) == 0)
							shareInfoService.add(shareInfo);
					} catch (ServiceException e) {
						logger.error("插入分享信息失败", e);
					}
				}
			});
			
			return vo;
		} catch (ServiceException e) {
			logger.error("分享失败", e);
			throw new RpcException("分享失败", e);
		}

	}
}
