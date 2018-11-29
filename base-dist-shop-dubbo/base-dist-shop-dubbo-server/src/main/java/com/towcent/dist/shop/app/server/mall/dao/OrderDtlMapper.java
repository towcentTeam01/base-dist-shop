package com.towcent.dist.shop.app.server.mall.dao;

import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.dal.db.CrudMapper;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * order_dtl 数据库操作接口
 *
 * @author huangtao
 * @date 2018-06-28 18:16:12
 * @version 1.0
 * @copyright facegarden.com
 */
@MyBatisDao
public interface OrderDtlMapper extends CrudMapper {

  /**
   * 查询评价订单商品
   *
   * @param params
   * @return
   */
  Integer selectEvalCount(@Param("params") Map<String, Object> params);

  /**
   * 查询评价订单商品
   *
   * @param params
   * @return
   */
  List<OrderDtl> selectEvalByPage(
      @Param("page") SimplePage page, @Param("params") Map<String, Object> params);
}
