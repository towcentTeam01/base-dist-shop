package com.towcent.dist.shop.app.client.mall.vo;

/** 订单创建参数VO */
import com.towcent.dist.shop.app.client.me.dto.ConsigneeAddr;
import com.towcent.dist.shop.app.client.sys.dto.SysFrontAccount;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class OrderParamVo implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer merchantId; // 商家Id
  private String goodsStr; // 商品字符串(商品Id:数量:规格)Id:qty:spec;Id:qty:spec;Id:qty:spec
  private BigDecimal totalAmount; // 总金额(商品总数量*单价+运费)
  private BigDecimal payAmount; // 实付总额(商品总数量*单价+运费-袋鼠币-红包优惠)
  private BigDecimal couponAmount; // 红包优惠金额
  private BigDecimal payInter; // 支付的袋鼠币
  private String payWay; // 付款方式(0:现金支付 1:线上支付)
  private String remark; // 买家留言
  private ConsigneeAddr consigneeAddr; // 用户收货地址

  private Integer couponId; // 优惠券Id
  private BigDecimal freightFee; // 运费

  private SysFrontAccount account;

  private List<GoodsSkuVo> skuList; // sku列表
}
