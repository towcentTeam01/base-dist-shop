package com.towcent.dist.shop.common;

import com.towcent.base.common.constants.BaseConstant;

/**
 * 常量类
 *
 * @author huangtao
 * @version 0.1.0
 * @date 2015年6月24日 下午4:23:06
 */
public final class Constant extends BaseConstant {

    /**
     * 图片上传URL
     */
    public static final String UPLOAD_URL = "/api/sys/image/upload";
    /**
     * 图片上传URL-base64
     */
    public static final String UPLOAD_URL_WX = "/api/sys/image/wxupload";

    /**
     * APP类型(0:客户)
     */
    public static final byte APP_TYPE_0 = 0;

    /**
     * 设备类型(1:IOS 2:Android 3:pad 4:H5 5:微信小程序)
     */
    public static final byte SYS_TYPE_1 = 1;
    /**
     * 设备类型(1:IOS 2:Android 3:pad 4:H5 5:微信小程序)
     */
    public static final byte SYS_TYPE_2 = 2;
    /**
     * 设备类型(1:IOS 2:Android 3:pad 4:H5 5:微信小程序)
     */
    public static final byte SYS_TYPE_3 = 3;
    /**
     * 设备类型(1:IOS 2:Android 3:pad 4:H5 5:微信小程序)
     */
    public static final byte SYS_TYPE_4 = 4;
    /**
     * 设备类型(1:IOS 2:Android 3:pad 4:H5 5:微信小程序)
     */
    public static final byte SYS_TYPE_5 = 5;

    /**
     * 用户注册
     */
    public static final byte SMS_TYPE_1 = 1;
    /**
     * 用户登录
     */
    public static final byte SMS_TYPE_2 = 2;
    /**
     * 用户修改密码
     */
    public static final byte SMS_TYPE_3 = 3;

    /**
     * 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
     */
    public static final String PAY_STATUS_0 = "0";
    /**
     * 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
     */
    public static final String PAY_STATUS_1 = "1";
    /**
     * 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
     */
    public static final String PAY_STATUS_2 = "2";
    /**
     * 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
     */
    public static final String PAY_STATUS_3 = "3";
    /**
     * 支付状态(0:未支付 1:已支付 2:退款中 3:已退款 4:失败)
     */
    public static final String PAY_STATUS_4 = "4";

    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_1 = "1";
    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_2 = "2";
    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_3 = "3";
    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_4 = "4";
    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_5 = "5";
    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_6 = "6";
    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成)
     */
    public static final String ORDER_STATUS_7 = "7";

    /**
     * 业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员)
     */
    public static final String BIZ_TYPE_0 = "0";
    /**
     * 业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员)
     */
    public static final String BIZ_TYPE_1 = "1";
    /**
     * 业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员)
     */
    public static final String BIZ_TYPE_2 = "2";
    /**
     * 业务类型(0:订单 1:余额充值 2:购买铂金会员 3:购买钻石会员)
     */
    public static final String BIZ_TYPE_3 = "3";

    /**
     * 支付类型(0:余额支付 1:微信支付_公众号 2:支付宝支付 3:微信APP支付)
     */
    public static final String PAY_TYPE_0 = "0";
    /**
     * 支付类型(0:余额支付 1:微信支付_公众号 2:支付宝支付 3:微信APP支付)
     */
    public static final String PAY_TYPE_1 = "1";
    /**
     * 支付类型(0:余额支付 1:微信支付_公众号 2:支付宝支付 3:微信APP支付)
     */
    public static final String PAY_TYPE_2 = "2";
    /**
     * 支付类型(0:余额支付 1:微信支付_公众号 2:支付宝支付 3:微信APP支付)
     */
    public static final String PAY_TYPE_3 = "3";

    /**
     * 1:收入 0:支出
     */
    public static final String INCOME_TYPE_0 = "0";
    /**
     * 1:收入 0:支出
     */
    public static final String INCOME_TYPE_1 = "1";

    /**
     * 提现状态(0:已申请 1:处理中 2:已处理)
     */
    public static final String WITHDRAW_APPLY_0 = "0";
    /**
     * 提现状态(0:已申请 1:处理中 2:已处理)
     */
    public static final String WITHDRAW_APPLY_1 = "1";
    /**
     * 提现状态(0:已申请 1:处理中 2:已处理)
     */
    public static final String WITHDRAW_APPLY_2 = "2";

    /**
     * 常量：id
     */
    public static final String ID = "id";
    /**
     * 常量：userId
     */
    public static final String USER_ID = "userId";
    /**
     * 常量：payRecordNo
     */
    public static final String PAY_RECORD_NO = "payRecordNo";

    /**
     * 优惠券活动状态 0:未开始 1:进行中
     */
    public static final String ACTIVITY_STATUS_UNSTARTED = "0";
    /**
     * 优惠券活动状态 0:未开始 1:进行中
     */
    public static final String ACTIVITY_STATUS_STARTING = "1";

    /**
     * 优惠券状态 0:未使用 1:已使用 2:已过期
     */
    public static final String COUPON_STATUS_UNUSED = "0";
    /**
     * 优惠券状态 0:未使用 1:已使用 2:已过期
     */
    public static final String COUPON_STATUS_USED = "1";
    /**
     * 优惠券状态 0:未使用 1:已使用 2:已过期
     */
    public static final String COUPON_STATUS_OVER = "2";

    /**
     * 1:余额
     */
    public static final String ACCOUNT_RECORD_AMOUNT = "1";
    /**
     * 2:积分
     */
    public static final String ACCOUNT_RECORD_INTEGRAL = "2";

    /**
     * 优惠券类型 现金
     */
    public static final String COUPON_TYPE_CASH = "1";
    /**
     * 优惠券类型 折扣
     */
    public static final String COUPON_TYPE_DISCOUNT = "2";
    /**
     * 优惠券类型 满减
     */
    public static final String COUPON_TYPE_FULLSUB = "3";

    /**
     * 编码规则
     */
    public static final String RULE_GOODS_NO = "goods_no";
    /**
     * 编码规则
     */
    public static final String RULE_PAY_RECORD_NO = "pay_record_no";
    /**
     * 编码规则
     */
    public static final String RULE_ORDER_NO = "order_no";
    /**
     * 编码规则
     */
    public static final String RULE_JOB_NO = "job_no";
    /**
     * 编码规则
     */
    public static final String RULE_GOODS_CATEGORY_NO = "goods_category_no";

    /**
     * 商品类型 0:普通商品 1:批发商品
     */
    public static final String GOODS_TYPE_0 = "0";
    /**
     * 商品类型 0:普通商品 1:批发商品
     */
    public static final String GOODS_TYPE_1 = "1";

    /**
     * 分销开关 不开启
     */
    public static final String DIST_SWITCH_0 = "0";
    /**
     * 分销开关 开启
     */
    public static final String DIST_SWITCH_1 = "1";

    /**
     * 微信公众号模板消息回跳地址
     */
    public static final String WX_TEMPLATE_BACK_URL = "wx_template_back_url";

    /**
     * 普通订单的直推佣金提醒标题
     */
    public static final String BKGE_FIRST_TITLE_1 = "您的客户{0}购买了{1}，您获得业绩奖励{2}元！已到账，请查收！";
    /**
     * 普通订单的下级佣金提醒标题
     */
    public static final String BKGE_FIRST_TITLE_2 = "您的下级代理商的客户{0}购买了{1}，您获得下级提成奖励{2}元！已到账，请查收！";
    /**
     * 普通订单的平级奖励提醒标题
     */
    public static final String BKGE_FIRST_TITLE_3 = "您的下级代理商的客户{0}购买了{1}，您获得平级奖励{2}元！已到账，请查收！";
    /**
     * 提现成功通知标题
     */
    public static final String WITHDRAW_APPLY_FIRST = "您申请的提现已成功处理，请关注\"微信支付\"入账消息，或到微信零钱中查收。";
    /**
     * 新会员加入通知标题
     */
    public static final String MEMBER_JOIN_FIRST = "您的朋友{0}光顾了您的商城，请注意维护好客户关系，做好服务工作，如有疑问可直接公众号中咨询系统客服！";

    /**
     * 数字10
     */
    public static final int INTEGER_NUMBER_10 = 10;

    /**
     * 商品状态 1:未发布 2:上架 3:下架 4:图片处理中
     */
    public static final String GOODS_STATUS_1 = "1";
    /**
     * 商品状态 1:未发布 2:上架 3:下架 4:图片处理中
     */
    public static final String GOODS_STATUS_2 = "2";
    /**
     * 商品状态 1:未发布 2:上架 3:下架 4:图片处理中
     */
    public static final String GOODS_STATUS_3 = "3";
    /**
     * 商品状态 1:未发布 2:上架 3:下架 4:图片处理中
     */
    public static final String GOODS_STATUS_4 = "4";

    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_0 = 0;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_1 = 1;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_2 = 2;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_3 = 3;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_4 = 4;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_5 = 5;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_6 = 6;
    /**
     * 图片类型(0:会员头像 1:轮播图 2:商品主图 3:商品详情图 4:商品分类图 5:商品频道图 6:商家Logo 7:广告图)
     */
    public static final int IMAGE_TYPE_7 = 7;

    /**
     * 商品图片类型(0:商品主图 1:商品详情图)
     */
    public static final String GOODS_IMAGE_TYPE_0 = "0";
    /**
     * 商品图片类型(0:商品主图 1:商品详情图)
     */
    public static final String GOODS_IMAGE_TYPE_1 = "1";

    /**
     * 数据0
     */
    public static final int INTEGER_NUM_0 = 0;

    /**
     * 数字1
     */
    public static final int INTEGER_NUM_1 = 1;

    /**
     * 数字100
     */
    public static final int INTEGER_NUM_100 = 100;
    
    /**
     * 快递100 物流查询url
     */
    public static final String KUAI_DI_100_URL = "https://m.kuaidi100.com/query?";
    
    /**
     * 快递100 HOST
     */
    public static final String KUAI_DI_100_HOST = "m.kuaidi100.com:443";
}
