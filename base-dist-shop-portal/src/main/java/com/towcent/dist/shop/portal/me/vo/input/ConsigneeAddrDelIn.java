package com.towcent.dist.shop.portal.me.vo.input;


import com.towcent.dist.shop.portal.common.vo.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 3.1.3 删除收货地址管理
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class ConsigneeAddrDelIn extends BaseParam {

    private static final long serialVersionUID = 1L;


    @NotNull(message = "id不能为空.")
    private Integer id;        // 收货地址id

}