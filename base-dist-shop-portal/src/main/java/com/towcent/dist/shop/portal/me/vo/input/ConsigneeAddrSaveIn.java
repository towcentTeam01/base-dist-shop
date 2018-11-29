package com.towcent.dist.shop.portal.me.vo.input;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.towcent.dist.shop.portal.common.vo.BaseParam;

import lombok.Data;

/**
 * 3.1.2 新增/修改收货地址管理
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ConsigneeAddrSaveIn extends BaseParam {

  private static final long serialVersionUID = 1L;

  private Integer id; // 收货地址id 修改时id必送

  @NotBlank(message = "consigneeName 不能为空.")
  private String consigneeName; // 收货人姓名

  @NotBlank(message = "mobilePhone不能为空.")
  private String mobilePhone; // 手机号码

  private String telephone; // 电号码

  @NotNull(message = "province不能为空.")
  private Integer province; // 省

  @NotNull(message = "city不能为空.")
  private Integer city; // 市

  @NotNull(message = "district不能为空.")
  private Integer district; // 区

  @NotBlank(message = "detailAddr不能为空.")
  private String detailAddr; // 详细地址

  @NotNull(message = "address不能为空.")
  private String address; // 全地址

  private String remarks; // 标签（如：家，公司） 数据字典 （consignee_label）

  @NotBlank(message = "defaultFlag不能为空.")
  private String defaultFlag; // 是否默认(1:是 0:否)
}
