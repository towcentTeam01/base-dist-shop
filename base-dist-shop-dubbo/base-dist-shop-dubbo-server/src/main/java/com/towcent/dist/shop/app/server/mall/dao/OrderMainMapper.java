package com.towcent.dist.shop.app.server.mall.dao;

import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.base.dal.db.CrudMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * order_main 数据库操作接口
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
@MyBatisDao
public interface OrderMainMapper extends CrudMapper {

    /**
     * 统计个人中心订单
     * @param params
     * @return
     */
    List<Map<String, Object>> queryOrderNum(@Param("params") Map<String, Object> params);

}