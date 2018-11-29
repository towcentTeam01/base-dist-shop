package com.towcent.dist.shop.portal.mall.vo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.towcent.dist.shop.app.client.mall.vo.ShoppingCartVo;

import lombok.Data;

/**
 * 2.1.6 去结算页面
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ShoppingCartConfirmOut implements Serializable {

  private static final long serialVersionUID = 1L;

  private Object consigneeAddr; // 收获地址
  private List<ShoppingCartVo> goodsList = Lists.newArrayList(); // 商品列表
  private MemberAccountOut memberAccount; // 账户信息
  private BigDecimal totalPrice; // 商品总价格
  private BigDecimal payAmount; // 订单总额(商品总金额+运费)
  private Integer totalQty; // 总数量
  private Integer integral; // 总积分
  private BigDecimal freightFee; // 运费
  private List<CouponClaimOut> couponList; // 优惠券列表
  private Integer couponCount; // 优惠券可用数量
  private String servicePhone; // 客服电话
}
