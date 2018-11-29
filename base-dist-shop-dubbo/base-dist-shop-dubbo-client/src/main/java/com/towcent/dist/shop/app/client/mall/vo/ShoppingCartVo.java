package com.towcent.dist.shop.app.client.mall.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.towcent.base.common.utils.Md5Utils;

import lombok.Getter;
import lombok.Setter;

/** Created by ShippYoung on 2018/6/23. */
@Setter
@Getter
public class ShoppingCartVo implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 购物车商品项id */
  private String id;

  /** 商品id */
  private Integer goodsId;

  /** 商品名称. */
  private String goodsName;

  /** 商品类型(0:普通商品 1:批发商品). */
  private String goodsType;

  /** 商品价格. */
  private BigDecimal price;

  /** 兑换积分. */
  private Integer integral;

  /** 数量. */
  private Integer qty;

  /** 规格id. */
  private Integer spec;

  /** 规格名称. */
  private String specName;

  /** 图片. */
  private String picUrl;

  /** 商户id. */
  private Integer merchantId;

  /** 用户id. */
  private Integer userId;

  /** 用户id. */
  private Integer stock = 0;

  public String getId() {
    return Md5Utils.encryption(merchantId + "_" + userId + "_" + goodsId + "_" + spec);
  }
}
