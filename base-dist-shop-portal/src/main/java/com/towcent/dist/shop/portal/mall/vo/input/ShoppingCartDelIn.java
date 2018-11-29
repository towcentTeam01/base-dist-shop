package com.towcent.dist.shop.portal.mall.vo.input;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 2.1.2 删除购物车商品接口
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ShoppingCartDelIn extends BaseParam {

  private static final long serialVersionUID = 1L;

  /** 商品id_规格id(goodsId_spec)，多个以；分割（1_3;2_1） */
  @NotBlank(message = "keys不能为空.")
  private String keys;
}
