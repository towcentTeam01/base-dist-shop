package com.towcent.dist.shop.portal.mall.vo.output;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import lombok.Data;

/**
 * 2.0.6 商品评价列表
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class GoodsEvalListOut implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;		// 评价id	
	private String nickName;		// 用户昵称	
	private String evaContent;		// 评价内容	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;		// 评论时间	
	private String evaUrls;		// 评价图片	
	private Integer evaStar;		// 评价星级	
	private String replyContent;		// 回复内容	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date replyDate;		// 回复时间	
	private String isHideName;		// 是否匿名	
	
}