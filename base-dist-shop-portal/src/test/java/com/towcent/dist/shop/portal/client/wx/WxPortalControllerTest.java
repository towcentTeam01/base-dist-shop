package com.towcent.dist.shop.portal.client.wx;

import com.towcent.dist.shop.portal.client.BaseAppTest;
import com.towcent.dist.shop.portal.wx.vo.input.WeixinSignIn;
import org.junit.Test;

import java.io.IOException;

public class WxPortalControllerTest extends BaseAppTest {

    static {
        descMap.put("wechat/portal/getWXJsSign", "获取微信jssdk参数");
    }

    @Test
    public void getWXJsSign() throws IOException {
        String path = "wechat/portal/getWXJsSign";
        WeixinSignIn paramVo = new WeixinSignIn();
        this.setCommonParam(paramVo);
        this.setLoginFlag(paramVo);
        paramVo.setUrlStr("http://wx2.51jll.cn/index.html#/home");
        String content = sendHttp(paramVo, path);
        System.out.println(content);
    }
}
