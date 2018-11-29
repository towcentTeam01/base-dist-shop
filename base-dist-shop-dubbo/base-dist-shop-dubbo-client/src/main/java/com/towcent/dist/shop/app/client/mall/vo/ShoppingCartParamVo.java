package com.towcent.dist.shop.app.client.mall.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/** Created by ShippYoung on 2018/6/23. */
@Setter
@Getter
public class ShoppingCartParamVo implements Serializable {

  private static final long serialVersionUID = 1L;

  /** 用户id */
  private Integer userId;

  /** 商品id */
  private Integer goodsId;

  /** 规格 */
  private Integer spec;

  /** 数量 */
  private Integer qty = 0;

  /** 商户id. */
  private Integer merchantId;

  /**
   * 商品id_规格id(goodsId_spec)，多个以；分割（1_3;2_1）
   */
  private String keys;

}
