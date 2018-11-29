package com.towcent.dist.shop.portal.wx.vo.input;

import com.towcent.dist.shop.portal.common.vo.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WeixinConfigIn extends BaseParam {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "merchantId不能为空.")
    private Integer merchantId;

}
