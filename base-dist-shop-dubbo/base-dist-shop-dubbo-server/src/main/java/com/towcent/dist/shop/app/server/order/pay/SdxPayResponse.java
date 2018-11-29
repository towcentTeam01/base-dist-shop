package com.towcent.dist.shop.app.server.order.pay;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import com.egzosn.pay.common.api.PayConfigStorage;
import com.egzosn.pay.common.api.PayMessageHandler;
import com.egzosn.pay.common.api.PayMessageRouter;
import com.egzosn.pay.common.api.PayService;
import com.egzosn.pay.common.bean.MsgType;
import com.egzosn.pay.common.http.HttpConfigStorage;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.pay.entity.PayAccount;
import com.towcent.base.pay.entity.PayType;
import com.towcent.base.pay.service.interceptor.AliPayMessageInterceptor;
import com.towcent.dist.shop.app.server.order.pay.handler.SdxAliPayMessageHandler;
import com.towcent.dist.shop.app.server.order.pay.handler.SdxWxPayMessageHandler;
import com.towcent.dist.shop.common.ConfigUtil;

/**
 * 支付响应对象
 */
public class SdxPayResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
    private AutowireCapableBeanFactory spring;

    private PayConfigStorage storage;

    private PayService service;

    private PayMessageRouter router;

    public SdxPayResponse() {

    }

    /**
     * 初始化支付配置
     * @param apyAccount 账户信息
     * @see ApyAccount 对应表结构详情--》 /pay-java-demo/resources/apy_account.sql
     */
    public void init(PayAccount payAccount) {
        //根据不同的账户类型 初始化支付配置
        this.service = payAccount.getPayType().getPayService(payAccount);
        this.storage = service.getPayConfigStorage();
        if (StringUtils.equals("wxPay", this.storage.getPayType())) {
        	this.service.setRequestTemplateConfigStorage(getHttpConfigStorage(payAccount.getPayId()));
        }
        //这里设置代理配置
        // service.setRequestTemplateConfigStorage(getHttpConfigStorage());
        buildRouter(payAccount.getPayId());
    }

    /**
     * 获取http配置，如果配置为null则为默认配置，无代理。
     * 此处非必需
     * @return
     */
    public HttpConfigStorage getHttpConfigStorage(Integer payId){
        HttpConfigStorage httpConfigStorage = new HttpConfigStorage();
        //http代理地址
        // httpConfigStorage.setHttpProxyHost("192.168.1.69");
        //代理端口
        // httpConfigStorage.setHttpProxyPort(3308);
        //代理用户名
        // httpConfigStorage.setHttpProxyUsername("user");
        //代理密码
        // httpConfigStorage.setHttpProxyPassword("password");
        String payWxCertPath = ConfigUtil.getPayWxCertPath();
        if (StringUtils.isNotBlank(payWxCertPath)) {
        	payWxCertPath = MessageFormat.format(payWxCertPath, payId);
        }
        if (StringUtils.equals("wxPay", this.storage.getPayType()) && StringUtils.isNotBlank(payWxCertPath)) {
	        httpConfigStorage.setKeystore(payWxCertPath);
	        httpConfigStorage.setStorePassword(this.getStorage().getPid());
        }
        return httpConfigStorage;
    }


    /**
     * 配置路由
     * @param payId 指定账户id，用户多微信支付多支付宝支付
     */
    private void buildRouter(Integer payId) {
        router = new PayMessageRouter(this.service);
        router
                .rule()
                .async(false)
                .msgType(MsgType.text.name()) //消息类型
                .payType(PayType.aliPay.name()) //支付账户事件类型
                .interceptor(new AliPayMessageInterceptor()) //拦截器
                .handler(autowire(new SdxAliPayMessageHandler(payId))) //处理器
                .end()
                .rule()
                .async(false)
                .msgType(MsgType.xml.name())
                .payType(PayType.wxPay.name())
                .handler(autowire(new SdxWxPayMessageHandler(payId)))
                .end()
        ;
    }


    private PayMessageHandler autowire(PayMessageHandler handler) {
        spring.autowireBean(handler);
        return handler;
    }

    public PayConfigStorage getStorage() {
        return storage;
    }

    public PayService getService() {
        return service;
    }

    public PayMessageRouter getRouter() {
        return router;
    }
}
