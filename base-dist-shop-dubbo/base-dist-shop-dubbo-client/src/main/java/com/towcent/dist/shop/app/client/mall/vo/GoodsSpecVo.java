package com.towcent.dist.shop.app.client.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.towcent.dist.shop.app.client.mall.dto.GoodsSku;

import lombok.Getter;
import lombok.Setter;

/** Created by ShippYoung on 2018/6/23. */
@Setter
@Getter
public class GoodsSpecVo implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 主键id. */
  private Integer id;

  /** 商品id. */
  private Integer goodsId;

  /** 商品类型( 0:普通商品 1:批发商品 ). */
  private String goodsType;

  /** 规格名称. */
  private String name;

  /** 规格价格. */
  private BigDecimal price;

  /** 规格库存. */
  private Integer stock;

  /** 单位(件/套/...). */
  private String unit;

  /** 创建者. */
  private Integer createBy;

  /** 创建时间. */
  private Date createDate;

  /** 更新者. */
  private Integer updateBy;

  /** 更新时间. */
  private Date updateDate;

  /** 删除标记(0:正常1:删除). */
  private String delFlag;

  /** 商户id. */
  private Integer merchantId;

  private List<GoodsSku> skuList = Lists.newArrayList();

}
