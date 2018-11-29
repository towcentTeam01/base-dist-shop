package com.towcent.dist.shop.app.server.mall.jms;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.rpc.RpcException;
import com.google.common.collect.Maps;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.mapper.JsonMapper;
import com.towcent.base.common.utils.BaseHttpClient;
import com.towcent.base.common.utils.DateUtils;
import com.towcent.base.common.utils.IdGen;
import com.towcent.base.common.utils.PictureUtils;
import com.towcent.base.common.utils.SpringFTPUtil;
import com.towcent.base.common.vo.ImageJmsVo;
import com.towcent.base.common.vo.ImageUrlVo;
import com.towcent.base.service.ImageMagickService;
import com.towcent.dist.shop.app.client.mall.dto.Goods;
import com.towcent.dist.shop.app.server.common.util.GoodsImageUtils;
import com.towcent.dist.shop.app.server.mall.service.GoodsService;

/**
 * 通过Jms消息触发图片处理
 * 
 * @author huangtao
 * @date 2015年7月24日 下午6:08:32
 * @version 0.1.0
 * @copyright zuojian.com
 */
@Component
public class ImageConsumer implements MessageListener {

	public static Logger logger = LoggerFactory.getLogger(ImageConsumer.class);

	/** 商品图片目录 */
	private static final String GOODSPIC = "/goodsPic/";
	private static final String TMPDIR = System.getProperty("java.io.tmpdir");
	
	@Resource
	private MessageConverter messageConverter;
	@Resource
	private ImageMagickService imageMagickService;
	@Resource
	private MessageChannel ftpChannel;
	@Resource
	private GoodsService goodsService;
	
	//@JmsListener(destination = "com.zuojian.commerce.image.queue", containerFactory = "queueListenerContainer")
	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage objectMessage = (ObjectMessage) message;
			String jmsVoStr = (String) messageConverter.fromMessage(objectMessage);
			ImageJmsVo jmsVo = (ImageJmsVo) JsonMapper.fromJsonString(jmsVoStr, ImageJmsVo.class);
			logger.info("onMessage receive : {}.", jmsVo.toString());
			
			/*	if (!checkImageJms(jmsVo)) {
				return;
			}	*/
			
			// 服务器本地临时目录
			String tempPath = this.getTempPath();
			// 商品图片的相当目录(ftp服务器)
			String relativePath = this.getImageRelativePath(jmsVo.getMerchantId(), jmsVo.getGoodsNo());
			
			if (!CollectionUtils.isEmpty(jmsVo.getImageMap())) {
				for (String imageType : jmsVo.getImageMap().keySet()) {
					List<ImageUrlVo> urlVos = jmsVo.getImageMap().get(imageType);
					if (!CollectionUtils.isEmpty(urlVos)) {
						// 排序
						Collections.sort(urlVos, new Comparator<ImageUrlVo>() {
							@Override
							public int compare(ImageUrlVo o1, ImageUrlVo o2) {
								return o1.getSeq() - o2.getSeq();
							}
						});
						for (ImageUrlVo imageUrlVo : urlVos) {
							// 是否打水印
							boolean isPlayWatermark = false; // ConfigUtils.isWatermarkSwitch(); 
							if (imageUrlVo.getUrl().contains(GOODSPIC)) {
								isPlayWatermark = false;
							}
							imageUrlVo.setMerchantId(jmsVo.getMerchantId());
							imageUrlVo.setGoodsNo(jmsVo.getGoodsNo());
							this.processImage(imageType, imageUrlVo, tempPath, isPlayWatermark);
						}
					}
				}
			}
			// 上传文件到Ftp服务器
			Map<String, File> uploadFileMap = getDirectoryFileList(tempPath, jmsVo.getGoodsNo());
			
			SpringFTPUtil.ftpUpload(ftpChannel, uploadFileMap, relativePath);
			
			// (过滤"_0.jpg")
			List<String> picList = GoodsImageUtils.buildGoodsPicList(relativePath, uploadFileMap);
			// (过滤"_5.jpg")
			List<String> descPicList = GoodsImageUtils.buildGoodsPicList(relativePath, uploadFileMap, "_5.jpg");
			// 更改商品状态		
			goodsService.modifyGoodsPicByNo(jmsVo.getMerchantId(), jmsVo.getGoodsNo(), StringUtils.join(picList, ";"), StringUtils.join(descPicList, ";"));

			// 删除本地临时目录
			FileUtils.deleteDirectory(new File(tempPath));
			logger.info("处理图片完毕.");
		} catch (MessageConversionException e) {
			logger.error("处理图片失败.", e);
		} catch (JMSException e) {
			logger.error("处理图片失败.", e);
		} catch (RpcException e) {
			logger.error("处理图片失败.", e);
		} catch (IOException e) {
			logger.error("处理图片失败.", e);
		} catch (ServiceException e) {
			logger.error("处理图片失败.", e);
		} catch (Exception e) {
			logger.error("处理图片失败.", e);
		}
	}

	/**
	 * 处理图片
	 * @param type             商品图片类型(0:主图 1:详情图)
	 * @param imageUrlVo       
	 * @param tempPath         服务器本地临时目录
	 * @param isPlayWatermark  是否打水印
	 */
	private void processImage(String type, ImageUrlVo imageUrlVo, String tempPath, boolean isPlayWatermark) {
		try {
			// 下载原图到临时目录
			String orFileName = IdGen.uuid() + PictureUtils.JPG;
			File srcFile = BaseHttpClient.getFile(GoodsImageUtils.replaceRandomUrl(imageUrlVo.getUrl()), tempPath + orFileName);
			// 获取文件的像素长宽比率
			Map<Integer, Double> pixelMap = PictureUtils.getPercentMap(srcFile);
			double percent = pixelMap.get(3); 	// PictureUtils.getPercent(srcFile);
			// Integer width = pixelMap.get(1).intValue();
			// Integer height = pixelMap.get(2).intValue();

			// 添加图片水印logo (客观图, 第一张、最后一张不用打水印)
			/*if (isPlayWatermark) {
				String tempWaterFilePath = StringUtils.join(new String[]{tempPath, IdGen.uuid(), PictureUtils.JPG});
				boolean result = imageMagickService.compositeWaterMark(
						PictureUtils.WATER_MARK_OFFSET, tempPath + orFileName,
						getWaterMarkImagePath(), tempWaterFilePath);
				if (result) {
					File tempWaterFile = new File(tempWaterFilePath);
					FileUtils.copyFile(tempWaterFile, srcFile);
					FileUtils.deleteQuietly(tempWaterFile);
				}
			}*/

			// 获取要处理的像素列表
			List<String> pixels = GoodsImageUtils.getPixelsBytype(type,	imageUrlVo.getSeq());
			for (String pixel : pixels) {
				String[] xlist = StringUtils.split(pixel, ":");
				String dest = this.getDestFile(xlist[0], imageUrlVo, tempPath);
				File destFile = null;
				if (xlist.length == 1) {
					// dest = MessageFormat.format(dest, width + "X" + height);   文件暂时取消拼接像素到文件名上
					destFile = new File(dest);
					// 原图直接拷贝
					FileUtils.copyFile(srcFile, destFile);
					// 生成缩略图
					String output = StringUtils.substringBefore(destFile.getAbsolutePath(), ".") + PictureUtils.SJPG;
					imageMagickService.resizeImageMagick(PictureUtils.calculatePixels(PictureUtils.THUMBNAIL_PEXLS, percent),
									srcFile.getAbsolutePath(), output);
				} else if (xlist.length == 2) {
					String tPixels = PictureUtils.calculatePixels(xlist[1], percent);
					// dest = MessageFormat.format(dest, tPixels);
					destFile = new File(dest);
					// 切图
					imageMagickService.resizeImageMagick(tPixels, srcFile.getAbsolutePath(), destFile.getAbsolutePath());
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取商品图片的相对目录
	 * @param merchantId 商户Id
	 * @param goodsNo    商品编码
	 * @return
	 * @throws RpcException
	 */
	private String getImageRelativePath(Integer merchantId, String goodsNo) throws ServiceException {
		StringBuilder path = new StringBuilder();
		path.append("/").append(merchantId).append(GOODSPIC);
		Goods goodsInfo = goodsService.getGoodsByNo(merchantId, goodsNo);
		String month = DateUtils.formatMonth(null == goodsInfo ? new Date()
				: goodsInfo.getCreateDate());
		path.append(month).append("/");
		path.append(goodsNo).append("/");
		return path.toString();
	}

	/**
	 * 获取服务本地临时目录
	 * @return
	 */
	private String getTempPath() {
		StringBuilder tempPath = new StringBuilder(TMPDIR);
		tempPath.append(File.separator);
		tempPath.append(IdGen.uuid()).append(File.separator);
		this.mkdir(tempPath.toString());
		return tempPath.toString();
	}

	/**
	 * 输出目标文件路径.
	 * @Title getDestFile
	 * @param type       图片类型
	 * @param urlVo      
	 * @param tempPath   服务器本地临时目录
	 * @return
	 */
	private String getDestFile(String type, ImageUrlVo urlVo, String tempPath) {
		StringBuilder dest = new StringBuilder(tempPath);
		dest.append(urlVo.getGoodsNo()).append(File.separator);
		this.mkdir(dest.toString());
		dest.append(urlVo.getGoodsNo()).append("_");
		dest.append(GoodsImageUtils.formatCode(null, urlVo.getSeq(), 2));
		dest.append("_").append(StringUtils.substringBefore(type, ":"));
		// dest.append("_").append("{0}");  // 像素大小
		dest.append(PictureUtils.JPG);
		return dest.toString();
	}

	/**
	 * 获取临时目录中裁剪好的图片列表
	 * @param tempPath    服务器本地临时目录
	 * @param goodsNo     商品编码
	 * @return
	 */
	private Map<String, File> getDirectoryFileList(String tempPath,	String goodsNo) {
		StringBuilder dest = new StringBuilder(tempPath);
		dest.append(goodsNo).append(File.separator);
		mkdir(dest.toString());
		List<File> files = (List<File>) FileUtils.listFiles(
				new File(dest.toString()), TrueFileFilter.INSTANCE,
				FalseFileFilter.INSTANCE);
		Map<String, File> uploadMap = Maps.newHashMap();
		if (!CollectionUtils.isEmpty(files)) {
			for (File file : files) {
				uploadMap.put(file.getName(), file);
			}
		}
		return uploadMap;
	}

	private void mkdir(String directory) {
		File file = new File(directory);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 获取水印logo图片路径
	 * @return
	 
	private String getWaterMarkImagePath() {
		StringBuilder waterFilePath = new StringBuilder(TMPDIR);
		waterFilePath.append(File.separator);
		waterFilePath.append(PictureUtils.WATER_MARK);
		File waterFile = new File(waterFilePath.toString());
		if (waterFile.exists()) {
			return waterFilePath.toString();
		}
		String urlHeader = ConfigUtil.getUrlHeader();
		// 如果有配置本地局域网的url头部，则需要替换
		String localUrlHeader = ConfigUtil.getLocalUrlHeader();
		String header = StringUtils.isNotBlank(localUrlHeader) ? localUrlHeader
				: urlHeader;
		String waterUrl = header + GOODSPIC + PictureUtils.WATER_MARK;
		try {
			BaseHttpClient.getFile(waterUrl, waterFilePath.toString());
		} catch (ClientProtocolException e) {
			logger.error("", e);
		} catch (IOException e) {
			logger.error("", e);
		}
		return waterFilePath.toString();
	}*/

	public MessageConverter getMessageConverter() {
		return messageConverter;
	}

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}
	
	// @Resource
	// private ImageJmsLogService jmsLogService;
	
	/*private boolean checkImageJms(ImageJmsVo jmsVo) {
		try {
			Map<String, Object> params = Maps.newHashMap();
			params.put("goodsNo", jmsVo.getGoodsNo());
			List<ImageJmsLog> list = jmsLogService.findByBiz(params);
			if (CollectionUtils.isEmpty(list)) return false;
			
			ImageJmsLog jmsLog = list.get(0);
			// 1:已完成、2:处理中
			if (jmsLog.getStatus() == 0) {
				// 将状态修改为处理中2
				jmsLog.setStatus(2);
				jmsLog.setMessage(null);
				jmsLog.setCreateTime(null);
				jmsLog.setActionnum(null);
				jmsLog.setUpdateTime(new Date());
				jmsLogService.modifyById(jmsLog);
				return true;
			}
			return false;
		} catch (ServiceException e) {
			logger.error("", e);
		}
		return false;
	}*/
	
	public static void main(String[] args) {
		
	}
}
