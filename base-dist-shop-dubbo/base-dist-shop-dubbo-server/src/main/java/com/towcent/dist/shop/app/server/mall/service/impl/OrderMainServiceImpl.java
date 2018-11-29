package com.towcent.dist.shop.app.server.mall.service.impl;

import com.google.common.collect.Maps;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.server.mall.dao.OrderMainMapper;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import static com.towcent.base.common.constants.BaseConstant.DEL_FLAG_0;

/**
 * 
 * @author huangtao
 * @date 2018-06-22 15:44:09
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("orderMainServiceImpl")
public class OrderMainServiceImpl extends BaseCrudServiceImpl implements OrderMainService {

    @Resource
    private OrderMainMapper orderMainMapper;

    @Override
    public CrudMapper init() {
        return orderMainMapper;
    }

    @Override
    public Map<String, Object> queryOrderNum(Map<String, Object> params) throws ServiceException
    {
        Map<String, Object> resultMap = Maps.newHashMap();
        List<Map<String, Object>> list = orderMainMapper.queryOrderNum(params);
        if (!CollectionUtils.isEmpty(list))
        {
            for (Map<String, Object> map : list)
            {
                resultMap.put(String.valueOf(map.get("type")),map.get("num"));
            }
        }
        return resultMap;
    }

}