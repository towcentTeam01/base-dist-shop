/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-portal
 * @Title : ImageSynthesisUtils.java
 * @Package : com.towcent.dist.shop.portal.common.utils
 * @date : 2018年8月1日下午7:46:17
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.app.server.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;

/**
 * @ClassName: ImageSynthesisUtils 
 * @Description: 海报合成工具类
 *
 * @author huangtao
 * @date 2018年8月1日 下午7:46:17
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
public class ImageSynthesisUtils {
	
	/**
	 * 合成海报.<br>
	 * <code>
	 * g.drawImage(Image img, int x, int y, int width, int height, ImageObserver observer); <br>
	 * x:左边距  <br>
	 * y:上边距  <br>
	 * width:素材宽度  <br>
	 * height:素材高度 <br>
	 * </code>
	 * @param account   会员账号信息
	 * @param bgFile    模板背景图
	 * @param outFile   合成后的海报文件
	 * @throws IOException
	 */
	public static void compositeImage(SysFrontAccount account, BufferedImage bgFile, File outFile) throws IOException {
		Graphics2D g = bgFile.createGraphics();
		// 1. 合并推广二维码
		BufferedImage image = ImageIO.read(new URL(account.getPosterUrl()));    
		g.drawImage(image, 500, 1040, 150, 150, null);
		
		// 2. 合并用户头像
		image = ImageIO.read(new URL(account.getPortrait()));    
		g.drawImage(image, 70, 1040, 80, 80, null);
		
		// 3. 添加用户昵称
		// 设置文本样式
		Font font = new Font("微软雅黑", Font.PLAIN, 30);// 添加字体的属性设置
		g.setFont(font);
		g.setColor(Color.BLACK);
		// 添加用户名称
		g.drawString(account.getNickName(), 165, 1060);
		
		// 完成模板修改
		g.dispose();
		bgFile.flush();
		image.flush();
		ImageIO.write(bgFile, "png", outFile);
	}

	
	public static void main(String[] args) throws MalformedURLException, IOException {
		SysFrontAccount account = new SysFrontAccount();
		account.setNickName("Andy");
		account.setPosterUrl("http://119.23.75.16:81/0/shop/info201808/1808012200532149.jpg");
		account.setPortrait("http://thirdwx.qlogo.cn/mmopen/ankGqIf6uCgxhA8dLgCgaQIIU0ewds4qY8LDjaT5DRVricCibvfeXSnkb0yIuC57wkqDFJwrRcNkOgfUUvqicNwtaM3HYc8fD5z/132");
		
		BufferedImage bgImage = ImageIO.read(new URL("http://119.23.75.16:81/0/shop/info201808/1808012047307263.jpg")); 
		
		compositeImage(account, bgImage, new File("d:\\12_t.jpg"));
		
		System.out.println("完毕");
	}
}

	