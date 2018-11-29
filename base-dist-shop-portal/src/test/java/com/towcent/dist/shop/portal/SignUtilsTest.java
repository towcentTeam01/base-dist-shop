package com.towcent.dist.shop.portal;

import java.io.IOException;
import java.util.Map;

import com.towcent.dist.shop.portal.common.utils.SignUtils;


/**
 * TODO: 增加描述
 * 
 * @author huangtao
 * @date 2015年6月29日 下午2:42:27
 * @version 0.1.0 
 * @copyright zuojian.com
 */
public class SignUtilsTest {
	
	/** 
	 * appKey = 889988 
	 */
	static String secret = "ec0d4dcff92d78e672167eaabcd32a90";
	
	public static String getSign(String json) throws IOException {
		Map<String, String> map = SignUtils.toJsonObject(json);
		return SignUtils.getSignature(map, secret);
	}
	
}