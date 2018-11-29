package com.towcent.dist.shop.portal.me.vo.output;

import lombok.Data;

import java.io.Serializable;

/**
 * 3.1.1 收货地址管理
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ConsigneeAddrListOut implements Serializable {

  private static final long serialVersionUID = 1L;

  private Integer id; // id
  private String consigneeName; // 收货人姓名
  private String mobilePhone; // 手机号码
  private String telephone; // 电号码
  private Integer province; // 省
  private Integer city; // 市
  private Integer district; // 区
  private String detailAddr; // 详细地址
  private String address; // 全地址
  private String remarks; // 标签（如：家，公司） 数据字典 （consignee_label）
  private String defaultFlag; // 是否默认(1:是 0:否)
}
