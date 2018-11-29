package com.towcent.dist.shop.app.client.me.dto;

import com.towcent.dist.shop.app.client.mall.dto.Goods;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 18:41:27
 * @version 1.0
 * @copyright facegarden.com
 */
@Setter @Getter
public class ConcernGoods implements Serializable {

    private static final long serialVersionUID = 1L;
	
	/**
     * 主键id.
     */
	private Integer id;
	
	/**
     * 商品id.
     */
	private Integer goodsId;
	
	/**
     * 注备.
     */
	private String remarks;
	
	/**
     * 创建者.
     */
	private Integer createBy;
	
	/**
     * 创建时间.
     */
	private Date createDate;
	
	/**
     * 更新者.
     */
	private Integer updateBy;
	
	/**
     * 更新时间.
     */
	private Date updateDate;
	
	/**
     * 删除标记(0:正常1:删除).
     */
	private String delFlag;
	
	/**
     * 商户id.
     */
	private Integer merchantId;

	private Goods goods;
	
	
}