package com.towcent.dist.shop.web.goods.entity;

import com.towcent.dist.shop.web.member.entity.SysFrontAccount;
import com.towcent.dist.shop.web.order.entity.OrderMain;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 商品评价Entity
 *
 * @author yxp
 * @version 2018-07-09
 */
public class GoodsEva extends DataEntity<GoodsEva> {

	private static final long serialVersionUID = 1L;
	private Goods goods; // 商品id
	private OrderMain order; // 订单id
	private String orderDtlId; // 订单详情id
	private String evaContent; // 评价内容
	private SysFrontAccount user; // 会员id
	private String evaUrls; // 评价图片
	private Integer evaStar; // 评价星级
	private String replyContent; // 商家回复
	private Date replyDate; // 商家回复时间
	private String isHideName; // 是否匿名
	private Integer merchantId; // 商户id

	private String goodsSmallPic; // 商品小列表图

	public GoodsEva() {
		super();
	}

	public GoodsEva(String id) {
		super(id);
	}

	@Length(min = 0, max = 11, message = "订单详情id长度必须介于 0 和 11 之间")
	public String getOrderDtlId() {
		return orderDtlId;
	}

	public void setOrderDtlId(String orderDtlId) {
		this.orderDtlId = orderDtlId;
	}

	@Length(min = 0, max = 500, message = "评价内容长度必须介于 0 和 500 之间")
	public String getEvaContent() {
		return evaContent;
	}

	public void setEvaContent(String evaContent) {
		this.evaContent = evaContent;
	}

	public void setEvaUrls(String evaUrls) {
		this.evaUrls = evaUrls;
	}

	@Length(min = 0, max = 1, message = "评价星级长度必须介于 0 和 1 之间")
	public Integer getEvaStar() {
		return evaStar;
	}

	public void setEvaStar(Integer evaStar) {
		this.evaStar = evaStar;
	}

	@Length(min = 0, max = 500, message = "商家回复长度必须介于 0 和 500 之间")
	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}

	@Length(min = 0, max = 2, message = "是否匿名长度必须介于 0 和 2 之间")
	public String getIsHideName() {
		return isHideName;
	}

	public void setIsHideName(String isHideName) {
		this.isHideName = isHideName;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public OrderMain getOrder() {
		return order;
	}

	public void setOrder(OrderMain order) {
		this.order = order;
	}

	public void setUser(SysFrontAccount user) {
		this.user = user;
	}

	public String getEvaUrls() {
		return evaUrls;
	}

	public SysFrontAccount getUser() {
		return user;
	}

	public String getGoodsSmallPic() {
		return goodsSmallPic;
	}

	public void setGoodsSmallPic(String goodsSmallPic) {
		this.goodsSmallPic = goodsSmallPic;
	}
}
