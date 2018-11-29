package com.towcent.dist.shop.portal.wx.web;

import com.towcent.base.common.annotation.Loggable;
import com.towcent.base.common.vo.ResultVo;
import com.towcent.base.common.web.BaseController;
import com.towcent.base.wx.service.WxInstanceFactory;
import com.towcent.dist.shop.portal.wx.vo.input.WeixinConfigIn;
import com.towcent.dist.shop.portal.wx.vo.input.WeixinSignIn;
import com.towcent.dist.shop.portal.wx.vo.output.WeixinConfigOut;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "wechat/portal", method = RequestMethod.POST)
public class WxPortalController extends BaseController {

    @Resource
    private WxInstanceFactory wxInstanceFactory;

    @RequestMapping(value = "getWXJsSign")
    @Loggable
    public ResultVo getWXJsSign(@RequestBody WeixinSignIn paramIn) throws Exception {
        ResultVo resultVo = new ResultVo();
        WxJsapiSignature signature = wxInstanceFactory.getInstance(paramIn.getMerchantId())
                .createJsapiSignature(new String(paramIn.getUrlStr().getBytes("ISO8859-1"), "UTF-8"));
        resultVo.setData(signature);
        return resultVo;
    }

    @RequestMapping(value = "getWXConfig")
    @Loggable
    public ResultVo getWXConfig(@RequestBody WeixinConfigIn paramIn) throws Exception {
        ResultVo resultVo = new ResultVo();
        WeixinConfigOut out = new WeixinConfigOut();
        WxMpConfigStorage wxMpConfigStorage = wxInstanceFactory.getInstance(paramIn.getMerchantId()).getWxMpConfigStorage();
        out.setAppid(wxMpConfigStorage.getAppId());
        resultVo.setData(out);
        return resultVo;
    }
}