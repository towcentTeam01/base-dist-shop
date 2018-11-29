/*
 * All rights Reserved, Designed By www.songywang.com
 * @Project : base-dist-shop-portal
 * @Title : MemberAccountLoginForWxIn.java
 * @Package : com.towcent.dist.shop.portal.sys.vo.input
 * @date : 2018年6月27日下午12:46:35
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
package com.towcent.dist.shop.portal.sys.vo.input;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: MemberAccountLoginForWxIn 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 *
 * @author huangtao
 * @date 2018年6月27日 下午12:46:35
 * @version 1.0.0
 * @Copyright: 2018 www.songywang.com Inc. All rights reserved. 
 * 注意：本内容仅限于深圳市众旺网络科技有限公司内部传阅，禁止外泄以及用于其他的商业项目
 */
@Setter @Getter
public class MemberAccountLoginForWxIn extends BaseParam {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "code不能为空.")
	private String code;
	
	private String jobNo; //邀请工号
}

	