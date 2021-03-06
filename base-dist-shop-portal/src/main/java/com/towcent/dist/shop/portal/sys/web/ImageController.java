package com.towcent.dist.shop.portal.sys.web;

import static com.towcent.base.common.constants.BaseConstant.E_001;
import static com.towcent.base.common.constants.BaseConstant.E_003;
import static com.towcent.base.common.constants.BaseConstant.JPG;
import static com.towcent.base.common.constants.BaseConstant.NO;
import static com.towcent.base.common.constants.BaseConstant.SJPG;
import static com.towcent.base.common.constants.BaseConstant.YES;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.model.SysImageConf;
import com.towcent.base.common.utils.IdGen;
import com.towcent.base.common.utils.ImageUtils;
import com.towcent.base.common.utils.SpringFTPUtil;
import com.towcent.base.common.utils.ThumbnailatorUtil;
import com.towcent.base.common.utils.WechatUtils;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.manager.BaseCommonApi;
import com.towcent.base.wx.service.WxInstanceFactory;
import com.towcent.dist.shop.common.ConfigUtil;
import com.towcent.dist.shop.portal.common.web.BaseImageController;
import com.towcent.dist.shop.portal.sys.vo.input.ImageVo;
import com.towcent.dist.shop.portal.sys.vo.output.ImageOut;

/**
 * 客户端图片上传接口
 * 
 * @author huangtao
 *
 */
@RequestMapping(value = "sys/image", method = RequestMethod.POST)
@RestController
public class ImageController extends BaseImageController {

	@Resource
	private MessageChannel ftpChannel;
	@Resource
	private BaseCommonApi commonApi;
	@Resource
	private WxInstanceFactory wxInstanceFactory;
	
	// 1.4.1 上传图片接口(过时)
	@RequestMapping(value = "upload")
	@Loggable
	public ResultVo upload(ImageVo paramVo, HttpServletRequest request) {
		ResultVo resultVo = new ResultVo();
		if (null == paramVo.getImageType()) {
			return assemblyVo(resultVo, E_003, "图片类型不能为空");
		}
		
		// 1. 获取图片类型配置
		SysImageConf imageConf = ImageUtils.getImageConfByType(paramVo.getMerchantId(), paramVo.getImageType());
		
		// 1.1. 获取本地临时目录
		String tempPath = this.getTempPath();
		String fileName = IdGen.randomString();
		// 1.2. 图片的相对目录(ftp服务器)
		String relativePath = ImageUtils.buildImageRelativePath(BASE_PATH, imageConf);
		try {
			boolean flag = false;
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Map<String, MultipartFile> mtFileMap = multipartRequest.getFileMap();
				for (String key : mtFileMap.keySet()) {
					MultipartFile fileItem = mtFileMap.get(key);
					File tempFile = new File(tempPath + fileName);
					FileCopyUtils.copy(fileItem.getBytes(), tempFile);
					flag = uploadOriginal(imageConf, tempFile, relativePath);

					// 生成缩略图
					generateThumb(imageConf, tempFile, tempPath + fileName, relativePath);
				}
			} else {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> fileList = upload.parseRequest(request);
				for (FileItem fileItem : fileList) {
					File tempFile = new File(tempPath + fileName);
					fileItem.write(tempFile);
					flag = uploadOriginal(imageConf, tempFile, relativePath);

					// 生成缩略图
					generateThumb(imageConf, tempFile, tempPath + fileName, relativePath);
				}
			}

			// 图片链接
			if (flag) {
				ImageOut out = new ImageOut();
				out.setData(StringUtils.substringBeforeLast(ConfigUtil.getUrlHeader(), "/") + relativePath + fileName);
				resultVo.setData(out);
				resultVo.setErrorMessage("上传成功");
			} else {
				assemblyVo(resultVo, E_001, "上传图片失败");
			}
		} catch (Exception e) {
			assemblyVo(resultVo, E_001, "上传图片失败");
			logger.error("上传图片异常.", e);
		} finally {
			FileUtils.deleteQuietly(new File(tempPath + fileName));
		}

		return resultVo;
	}

	// 1.4.2 上传图片接口
	@RequestMapping(value = "wxupload")
	@Loggable
	public ResultVo wxupload(@RequestBody ImageVo paramVo, HttpServletRequest request) {
		ResultVo resultVo = new ResultVo();
		if (null == paramVo.getImageType()) {
			return assemblyVo(resultVo, E_003, "图片类型不能为空");
		}
		if (StringUtils.isBlank(paramVo.getImageData())) {
			return assemblyVo(resultVo, E_001, "图片为空");
		}
		
		// 1. 获取图片类型配置
		SysImageConf imageConf = ImageUtils.getImageConfByType(paramVo.getMerchantId(), paramVo.getImageType());
		
		String imageData = paramVo.getImageData();
		// 1.1. 获取本地临时目录
		String tempPath = this.getTempPath();
		String fileName = IdGen.randomString();
		// 1.2. 图片的相对目录(ftp服务器)
		String relativePath = ImageUtils.buildImageRelativePath(BASE_PATH, imageConf);
		try {
			File tempFile = new File(tempPath + fileName);
			if (paramVo.getIsWeixin() == 1) {
				InputStream fis = WechatUtils.getInputStream(
						wxInstanceFactory.getInstance(paramVo.getMerchantId()).getAccessToken(), imageData);
				byte[] bytes = new byte[2048];
				int len = fis.read(bytes);
				OutputStream out = new FileOutputStream(tempFile);
				while (len != -1) {
					out.write(bytes, 0, len);
					out.flush();
					len = fis.read(bytes);
				}
				fis.close();
				out.close();
			} else {
				if (imageData.indexOf("base64,") >= 0) {
					imageData = imageData.split("base64,")[1];
				}
				byte[] b = Base64.decodeBase64(imageData);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {	// 调整异常数据
						b[i] += 256;
					}
				}
				OutputStream out = new FileOutputStream(tempFile);
				out.write(b);
				out.flush();
				out.close();
			}

			boolean flag = false;
			flag = uploadOriginal(imageConf, tempFile, relativePath);

			// 生成缩略图
			this.generateThumb(imageConf, tempFile, tempPath + fileName, relativePath);

			// 图片链接
			if (flag) {
				ImageOut out = new ImageOut();
				out.setData(StringUtils.substringBeforeLast(ConfigUtil.getUrlHeader(), "/") + relativePath + fileName);
				resultVo.setData(out);
				resultVo.setErrorMessage("上传成功");
			} else {
				assemblyVo(resultVo, E_001, "上传图片失败");
			}
		} catch (Exception e) {
			assemblyVo(resultVo, E_001, "上传图片失败");
			logger.error("上传图片异常.", e);
		} finally {
			FileUtils.deleteQuietly(new File(tempPath + fileName));
		}

		return resultVo;
	}

	/**
	 * 上传原图到Ftp服务器.
	 * @Title uploadOriginal
	 * @param imageConf    图片类型配置
	 * @param srcFile      原图文件
	 * @param relativePath Ftp服务器相对路径
	 * @return
	 * @throws IOException
	 */
	private boolean uploadOriginal(SysImageConf imageConf, File srcFile, String relativePath) throws IOException {
		// 1. 判断原图需要压缩
		if (null != imageConf && StringUtils.equals(YES, imageConf.getIsOriginalCompress())) {
			String dest = srcFile.getAbsolutePath().replaceAll(JPG, "_t.jpg");
			FileUtils.moveFile(srcFile, new File(dest));
			// PictureUtils.scale(imageConf.getOriginalSize(), dest, srcFile.getAbsolutePath());
			ThumbnailatorUtil.thumbnail(imageConf.getOriginalSize(), dest, srcFile.getAbsolutePath());
		}
		
		return SpringFTPUtil.ftpUpload(ftpChannel, new File(srcFile.getAbsolutePath()), relativePath);
	}

	/**
	 * 生成缩略图
	 * @param imageConf 图片类型配置
	 * @param srcFile   原图文件
	 * @param srcPath   原图文件路径
	 * @param relativePath 相对目录
	 * @throws UnsupportedEncodingException
	 */
	private void generateThumb(SysImageConf imageConf, File srcFile, String srcPath, String relativePath)
			throws UnsupportedEncodingException {
		if (null == imageConf) {
			return;
		}
		// 判断是否需要缩略图
		if (StringUtils.equals(NO, imageConf.getIsThumb())) {
			return;
		}

		String sFilePath = srcPath.replaceAll(JPG, SJPG);
		// PictureUtils.scale(imageConf.getThumbSize(), srcFile, sFilePath);
		ThumbnailatorUtil.thumbnail(imageConf.getThumbSize(), srcFile, sFilePath);
		SpringFTPUtil.ftpUpload(ftpChannel, new File(sFilePath), relativePath);
	}
}