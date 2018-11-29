package com.towcent.dist.shop.app.server.common.util;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.towcent.base.common.utils.PictureUtils;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.dist.shop.app.client.mall.dto.GoodsPicDto;
import com.towcent.dist.shop.common.ConfigUtil;
import com.towcent.dist.shop.common.Constant;

/**
 * 图片处理工具类
 * 
 * @author huangtao
 * @date 2015年7月27日 下午4:10:47
 * @version 0.1.0 
 * @copyright zuojian.com
 */
@Component
public final class GoodsImageUtils implements InitializingBean {
	
	/**
	 * 处理像素规格Map<type, List<pixel>>
	 */
	public static Map<String, List<String>> pixels = Maps.newHashMap();
	
	/**
	 * 列表图规格
	 */
	public static List<String> listPixels = Lists.newArrayList();
	
	@Value("${image.pixel.0}")
	private String pixel_0;
	
	@Value("${image.pixel.1}")
	private String pixel_1;
	
	@Value("${image.pixel.list}")
	private String pixel_list;

	@Override
	public void afterPropertiesSet() throws Exception {
		pixels.put("0", split(pixel_0, ","));
		pixels.put("1", split(pixel_1, ","));
		listPixels.addAll(split(pixel_list, ","));
	}
	
	/**
	 * 获取需要的像素列表
	 * @param type 图片类型 0:窗口图  1:详情图 
	 * @param seq 图片的顺序
	 * @return 
	 */
	public static List<String> getPixelsBytype(String type, Integer seq) {
		List<String> list = pixels.get(type);
		if ("0".equals(type) && 1 == seq) {
			// list.addAll(listPixels);
			listPixels.addAll(list);
			return listPixels;
		}
		return list;
	}
	
	public static List<GoodsPicDto> buildGoodsPicList(String goodsNo,
			String relativePath, Map<String, File> uploadFileMap) {
		List<GoodsPicDto> goodsPics = Lists.newArrayList();
		if (!CollectionUtils.isEmpty(uploadFileMap)) {
			GoodsPicDto picDto = null;
			for (String fileName : uploadFileMap.keySet()) {
				if (fileName.endsWith(PictureUtils.SJPG) || 
						fileName.endsWith(".zip")) {
					continue;
				}
				picDto = new GoodsPicDto();
				picDto.setGoodsNo(goodsNo);
				picDto.setPicName(fileName);
				picDto.setPicSize(PictureUtils.getFileSizes(uploadFileMap.get(fileName)));
				picDto.setPicType(StringUtils.substring(fileName, 12, 13));
				picDto.setPicUrl(relativePath + fileName);
				picDto.setSort(Integer.valueOf(StringUtils.substring(fileName, 9, 11)));
				picDto.setPicStatus("1");
				picDto.setDelFlag(Constant.DEL_FLAG_0);
				picDto.setCreateBy("auto");
				picDto.setCreateDate(new Date());
				goodsPics.add(picDto);
			}
		}
		return goodsPics;
	}
	
	public static List<String> buildGoodsPicList(String relativePath, Map<String, File> uploadFileMap) {
		return buildGoodsPicList(relativePath, uploadFileMap, "_0.jpg");
	}
	
	public static List<String> buildGoodsPicList(String relativePath, Map<String, File> uploadFileMap, String suffix) {
		List<String> picUrls = Lists.newArrayList();
		if (!CollectionUtils.isEmpty(uploadFileMap)) {
			for (String fileName : uploadFileMap.keySet()) {
				if (fileName.endsWith(suffix)) {
					picUrls.add(ConfigUtil.getUrlHeader() + relativePath.substring(1) + fileName);
				}
			}
			// 排序
			Collections.sort(picUrls, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				};
			});
		}
		return picUrls;
	}
	
	/**
	 * 格式化业务编码 流水号不足，则前面补零
	 * @param prefix
	 * @param serial
	 * @param length
	 * @return
	 */
	public static String formatCode(String prefix, long serial, int length) {
		prefix = null == prefix ? "" : prefix;
		StringBuilder sb = new StringBuilder(prefix);
		String ser = serial + "";
		//不够位,则补零
		for (int i = 0; i < length - ser.length(); i++) {
			sb.append("0");			
		}
		sb.append(ser);
		return sb.toString();
	}
	
	private List<String> split(String str, String regex) {
		return Arrays.asList(StringUtils.split(str, ","));
	}
	
	/**
	 * 在url后增加一个随机数
	 * @param url
	 * @return
	 */
	public static String replaceRandomUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return url;
		}
		String urlHeader = ConfigUtil.getUrlHeader();
		// 如果有配置本地局域网的url头部，则需要替换
		String localUrlHeader = ConfigUtil.getLocalUrlHeader();
		if (StringUtils.isNotBlank(localUrlHeader)) {
			url = url.replaceAll(urlHeader, localUrlHeader);
		}
		if (url.contains("?")) {
			return url;
		}
		return url + "?v=" + getRandom(100);
	}
	
	/**
	 * 从指定的数值中获取随机数
	 * @param n 比如 n=100
	 * @return 则获取100内的随机数
	 */
	public static int getRandom(int n) {
		Random r = new Random();
		int rom = r.nextInt(n);
		return rom;
	}
	
	public static void main(String[] args) {
		String picName = "10000002_01_1.jpg";
		String type = StringUtils.substring(picName, 12, 13);
		System.out.println(type);
		String sort = StringUtils.substring(picName, 9, 11);
		System.out.println(sort);
		//System.out.println(getPercent(new File("C:\\nginx-1.7.4\\html\\goodsPic\\201507\\00000004\\00000004_01_6.jpg")));
	}
}
