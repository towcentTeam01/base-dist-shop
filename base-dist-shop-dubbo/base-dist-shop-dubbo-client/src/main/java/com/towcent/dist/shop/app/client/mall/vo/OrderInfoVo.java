package com.towcent.dist.shop.app.client.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 *
 * @author yxp
 */
@Setter
@Getter
public class OrderInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id.
     */
    private Integer id;

    /**
     * 订单类型(0:普通订单).
     */
    private String orderType;

    /**
     * 订单号.
     */
    private String orderNo;

    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成).
     */
    private String orderStatus;

    /**
     * 订单状态(1:已下单 2:已发货 3:已签收 4:已完成 5:已取消 6:已申请售后 7:售后完成).
     */
    private String orderStatusDesc;

    /**
     * 支付状态(0:未支付 1:已支付).
     */
    private String payStatus;

    /**
     * 支付状态描述(0:未支付 1:已支付).
     */
    private String payStatusDesc;

    /**
     * 付款方式描述(1:在线支付 2:线下付款).
     */
    private String payWay;

    /**
     * 付款方式(1:在线支付 2:线下付款).
     */
    private String payWayDesc;

    /**
     * 总金额(商品总金额-优惠金额+运费).
     */
    private BigDecimal totalAmount;

    /**
     * 余额支付金额.
     */
    private BigDecimal amount;

    /**
     * 线上实付金额(总).
     */
    private BigDecimal payAmount;

    /**
     * 积分支付数额.
     */
    private BigDecimal payInter;

    /**
     * 优惠金额.
     */
    private BigDecimal couponAmount;

    /**
     * 收货人姓名.
     */
    private String consigneeName;

    /**
     * 收货人联系方式(手机或者电话至少填一项).
     */
    private String mobilePhone;

    /**
     * 收货详细地址(收货地址).
     */
    private String consigneeAddr;

    /**
     * 商品总数量.
     */
    private Integer totalQty;

    /**
     * 运费.
     */
    private BigDecimal freightFee;

    /**
     * 运单号.
     */
    private String freightNumber;

    /**
     * 物流公司id.
     */
    private String logisticsNo;

    /**
     * 物流公司名称.
     */
    private String logisticsName;

    /**
     * 是否评论(1:是0:否).
     */
    private String isEval;

    /**
     * 售后备注.
     */
    private String saleAfterRemarks;

    /**
     * 售后申请时间.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date saleAfterDate;

    /**
     * 注备.
     */
    private String remarks;

    /**
     * 创建者.
     */
    private Integer createBy;

    /**
     * 创建时间.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 更新者.
     */
    private Integer updateBy;

    /**
     * 更新时间.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    /**
     * 删除标记(0:正常1:删除).
     */
    private String delFlag;

    /**
     * 支付时间.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payDate;

    /**
     * 发货时间.
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;

    /**
     * 商户id.
     */
    private Integer merchantId;

    /**
     * 订单明细列表
     */
    private List<OrderDtlItemVo> orderDtlList = Lists.newArrayList();
}
