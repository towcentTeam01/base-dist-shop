package com.towcent.dist.shop.portal.mall.vo.input;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Getter;
import lombok.Setter;

/**
 * 2.1.6 去结算页面
 *
 * @author huangtao
 * @version 0.0.1
 */
@Setter @Getter
public class ShoppingCartConfirmIn extends BaseParam {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "goodsStr不能为空.")
  private String goodsStr;  // 商品Id:数量:规格(Id:qty:spec,Id:qty:spec)  (多个商品使用,分割) 例如(1:1:蓝色,2:1:长方桌)

  private Integer couponId; // 优惠券Id
  
}
