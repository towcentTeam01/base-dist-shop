package com.towcent.dist.shop.portal.wx.vo.output;


import lombok.Data;

import java.io.Serializable;

/**
 * 3.0.1 我的信息汇总接口
 *
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WeixinConfigOut implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信appId
     */
    private String appid;

}