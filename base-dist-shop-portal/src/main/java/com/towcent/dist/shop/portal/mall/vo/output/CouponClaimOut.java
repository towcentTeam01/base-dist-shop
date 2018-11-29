package com.towcent.dist.shop.portal.mall.vo.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 2.2.1 优惠券领取列表
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class CouponClaimOut implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id; // 优惠券id
  private String actName; // 活动名称
  private String type; // 类型(1:现金券 2:折扣券 3:满减券 )
  private BigDecimal limitAmount; // 满减金额
  private BigDecimal amount; // 优惠券金额/折扣金额
  private Integer actId; // 活动id
}
