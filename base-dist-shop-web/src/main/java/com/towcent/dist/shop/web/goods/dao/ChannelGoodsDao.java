/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights
 * reserved.
 */
package com.towcent.dist.shop.web.goods.dao;

import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.goods.entity.ChannelGoods;

import java.util.List;

/**
 * 商品频道DAO接口
 *
 * @author alice
 * @version 2017-03-01
 */
@MyBatisDao
public interface ChannelGoodsDao {

  /**
   * 查询频道商品列表
   *
   * @param channelGoods
   * @return
   */
  List<ChannelGoods> findChannelGoodsPage(ChannelGoods channelGoods);
}
