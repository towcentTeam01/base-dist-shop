package com.towcent.dist.shop.web.goods.service;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;
import static com.towcent.dist.shop.common.Constant.GOODS_IMAGE_TYPE_0;
import static com.towcent.dist.shop.common.Constant.GOODS_IMAGE_TYPE_1;
import static com.towcent.dist.shop.common.Constant.GOODS_TYPE_1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.common.mapper.JsonMapper;
import com.towcent.base.common.redis.RedisTemplateExt;
import com.towcent.base.common.vo.ImageJmsVo;
import com.towcent.base.common.vo.ImageUrlVo;
import com.towcent.base.sc.web.common.persistence.Page;
import com.towcent.base.sc.web.common.service.CrudService;
import com.towcent.base.sc.web.modules.sys.entity.ImageJmsLog;
import com.towcent.base.sc.web.modules.sys.service.ImageJmsLogService;
import com.towcent.dist.shop.web.goods.dao.GoodsDao;
import com.towcent.dist.shop.web.goods.entity.Goods;
import com.towcent.dist.shop.web.goods.entity.GoodsCategory;
import com.towcent.dist.shop.web.goods.entity.GoodsSku;
import com.towcent.dist.shop.web.goods.entity.GoodsSpec;
import com.towcent.dist.shop.web.jms.ImageMessageProducer;

/**
 * 商品Service
 *
 * @author yxp
 * @version 2018-07-02
 */
@Service
@Transactional(readOnly = true)
public class GoodsService extends CrudService<GoodsDao, Goods> {

	@Resource
	private GoodsCategoryService goodsCategoryService;
	@Resource
	private GoodsChannelDtlService goodsChannelDtlService;
	@Resource
	private RedisTemplateExt<String, Object> redisTemplateExt;
	@Resource
	private TaskExecutor taskExecutor;
	@Resource
	private ImageMessageProducer imageMessageProducer;
	@Resource
	private ImageJmsLogService imageJmsLogService;
	@Resource
	private GoodsSpecService goodsSpecService;
	@Resource
	private GoodsSkuService goodsSkuService;

	public Goods get(String id) {
		return super.get(id);
	}

	public List<Goods> findList(Goods goods) {
		return super.findList(goods);
	}

	public Page<Goods> findPage(Page<Goods> page, Goods goods) {
		return super.findPage(page, goods);
	}

	@Transactional(readOnly = false)
	public void save(Goods goods) {
		GoodsCategory goodsCategory = goodsCategoryService.get(goods.getGoodsCategory().getId());
		goods.setGoodsCategory(goodsCategory);
		goods.setStructureName(goodsCategory.getStructureName());
		goods.setStructureNo(goodsCategory.getStructureNo());

		if (null == goods.getWeight()) {
			goods.setWeight(BigDecimal.ZERO);
		}

		if (!goods.getIsNewRecord()) {
			Goods tgd = super.get(goods.getId());
			goods.setDescPicV(tgd.getDescPicV() == null ? 1 : tgd.getDescPicV() + 1);
		}
		
		if (CollectionUtils.isNotEmpty(goods.getGoodsSpecs())) {
			goods.setPrice(goods.getGoodsSpecs().get(0).getPrice());
		}

		super.save(goods);

		List<GoodsSpec> specList = getGoodsSpecList(goods.getId());

		if (GOODS_TYPE_1.equals(goods.getGoodsType())) { 
			// 批发商品
			saveGoodsSku(goods, specList);
		} else { 
			// 普通商品
			saveGoodsSpec(goods, specList);
		}

		this.saveAndSendImages(goods);
	}

	// 保存商品规格
	private void saveGoodsSpec(Goods goods, List<GoodsSpec> specList) {
		if (CollectionUtils.isNotEmpty(goods.getGoodsSpecs())) {
			delGoodsSpecs(specList);
			for (GoodsSpec spec : goods.getGoodsSpecs()) {
				spec.setGoodsId(Integer.parseInt(goods.getId()));
				spec.setDelFlag(DEL_FLAG_0);
				if (StringUtils.isNotBlank(spec.getId())) {
					spec.setIsNewRecord(false);
				}
				goodsSpecService.save(spec);
			}
		}
	}

	// 保存商品SKU
	private void saveGoodsSku(Goods goods, List<GoodsSpec> specList) {
		GoodsSpec spec = goods.getGoodsSpecs().get(0);
		List<GoodsSku> skuList = spec.getSkuList();
		if (CollectionUtils.isNotEmpty(specList)) {
			delGoodsSkus(getGoodsSkuList(specList.get(0).getId()));
			spec.setIsNewRecord(false);
			spec.setId(specList.get(0).getId());
		}
		goodsSpecService.save(spec);
		
		if (CollectionUtils.isNotEmpty(skuList)) {
			for (GoodsSku sku : skuList) {
				sku.setGoodsSpecId(spec.getId());
				sku.setDelFlag(DEL_FLAG_0);
				if (StringUtils.isNotBlank(sku.getId())) {
					sku.setIsNewRecord(false);
				}
				goodsSkuService.save(sku);
			}
		}
	}

	private List<GoodsSku> getGoodsSkuList(String goodsSpecId) {
		if (StringUtils.isBlank(goodsSpecId)) {
			return null;		
		}
		GoodsSku spec = new GoodsSku();
		spec.setGoodsSpecId(goodsSpecId);
		List<GoodsSku> specList = goodsSkuService.findList(spec);
		return specList;
	}

	private List<GoodsSpec> getGoodsSpecList(String goodsId) {
		if (StringUtils.isBlank(goodsId)) {
			return null;
		}
		
		GoodsSpec spec = new GoodsSpec();
		spec.setGoodsId(Integer.parseInt(goodsId));
		List<GoodsSpec> specList = goodsSpecService.findList(spec);
		return specList;
	}
	
	private void delGoodsSpecs(List<GoodsSpec> specList) {
		if (!CollectionUtils.isEmpty(specList)) {
			for (GoodsSpec spe : specList) {
				goodsSpecService.delete(spe);
			}
		}
	}

	private void delGoodsSkus(List<GoodsSku> skuList) {
		if (!CollectionUtils.isEmpty(skuList)) {
			for (GoodsSku sku : skuList) {
				goodsSkuService.delete(sku);
			}
		}
	}

	/** * 图片缓存和发送jms */
	private void saveAndSendImages(Goods goods) {
		final String goodsNo = goods.getGoodsNo();
		final ImageJmsVo imageJmsVo = new ImageJmsVo(goodsNo);
		imageJmsVo.setMerchantId(goods.getMerchantId());
		
		List<String> winPicList = goods.getGoodsPicList();
		List<String> descPicList = goods.getGoodsDescPicList();

		if (!CollectionUtils.isEmpty(winPicList)) {
			imageJmsVo.putImageMap(GOODS_IMAGE_TYPE_0, this.getImageUrlList(winPicList, goodsNo));
		}
		if (!CollectionUtils.isEmpty(descPicList)) {
			imageJmsVo.putImageMap(GOODS_IMAGE_TYPE_1, this.getImageUrlList(descPicList, goodsNo));
		}

		imageJmsVo.setSaveFlag(goods.getGoodsStatus());

		logger.info("jms message:" + JsonMapper.toJsonString(imageJmsVo));
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					// Thread.sleep(500);
					logger.info("发送jms图片处理消息start");
					imageMessageProducer.sendMessage(imageJmsVo);
					logger.info("发送jms图片处理消息end");

					// 保存图片jms处理日志
					ImageJmsLog imgLog = new ImageJmsLog();
					String message = JsonMapper.toJsonString(imageJmsVo);
					imgLog.setMessage(message);
					imgLog.setGoodsNo(goodsNo);
					imgLog.setActionnum(1);
					imgLog.setMerchantId(imageJmsVo.getMerchantId());
					imageJmsLogService.save(imgLog);
				} catch (Exception e) {
					logger.info("发送jms图片处理消息失败", e);
					e.printStackTrace();
				}
			}
		});
	}

	public List<ImageUrlVo> getImageUrlList(List<String> picList, String goodsNo) {
		List<ImageUrlVo> imgUrlList = new ArrayList<ImageUrlVo>();
		ImageUrlVo vo = null;
		for (int i = 0; i < picList.size(); i++) {
			vo = new ImageUrlVo();
			vo.setSeq(i + 1);
			vo.setUrl(picList.get(i).replace("_s.jpg", ".jpg"));
			vo.setGoodsNo(goodsNo);
			imgUrlList.add(vo);
		}
		return imgUrlList;
	}

	@Transactional(readOnly = false)
	public void delete(Goods goods) {
		super.delete(goods);
		goodsChannelDtlService.deleteGoods(goods.getId());
	}
}
