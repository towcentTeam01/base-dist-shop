package com.towcent.dist.shop.portal.me.vo.input;


import com.towcent.dist.shop.portal.common.vo.BaseParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 3.2.2 删除收藏商品
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class CollectGoodsDelIn extends BaseParam {

    private static final long serialVersionUID = 1L;


    @NotNull(message = "collectId不能为空.")
    private Integer collectId;        // 收藏id

}