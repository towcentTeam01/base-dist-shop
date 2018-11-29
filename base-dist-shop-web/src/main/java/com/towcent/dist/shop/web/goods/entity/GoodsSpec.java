package com.towcent.dist.shop.web.goods.entity;

import com.towcent.base.sc.web.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品规格Entity
 *
 * @author yxp
 * @version 2018-07-03
 */
public class GoodsSpec extends DataEntity<GoodsSpec> {

  private static final long serialVersionUID = 1L;
  private Integer goodsId; // 商品id
  private String goodsType; // 商品类型( 0:普通商品 1:批发商品 )
  private String name; // 规格名称
  private BigDecimal price; // 规格价格
  private Integer stock; // 规格库存
  private String unit; // 单位(件/套/...)
  private Integer merchantId; // 商户id

  private List<GoodsSku> skuList;

  public GoodsSpec() {
    super();
  }

  public GoodsSpec(String id) {
    super(id);
  }

  @Length(min = 0, max = 11, message = "商品id长度必须介于 0 和 11 之间")
  public Integer getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(Integer goodsId) {
    this.goodsId = goodsId;
  }

  @Length(min = 0, max = 2, message = "商品类型( 0:普通商品 1:批发商品 )长度必须介于 0 和 2 之间")
  public String getGoodsType() {
    return goodsType;
  }

  public void setGoodsType(String goodsType) {
    this.goodsType = goodsType;
  }

  @Length(min = 0, max = 100, message = "规格名称长度必须介于 0 和 100 之间")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  @Length(min = 0, max = 11, message = "规格库存长度必须介于 0 和 11 之间")
  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public Integer getMerchantId() {
    return merchantId;
  }

  public void setMerchantId(Integer merchantId) {
    this.merchantId = merchantId;
  }

  public List<GoodsSku> getSkuList() {
    return skuList;
  }

  public void setSkuList(List<GoodsSku> skuList) {
    this.skuList = skuList;
  }
}
