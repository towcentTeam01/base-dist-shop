package com.towcent.dist.shop.app.server.mall.service.impl;

import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.page.SimplePage;
import com.towcent.base.dal.db.CrudMapper;
import com.towcent.base.service.impl.BaseCrudServiceImpl;
import com.towcent.dist.shop.app.client.mall.dto.OrderDtl;
import com.towcent.dist.shop.app.server.mall.dao.OrderDtlMapper;
import com.towcent.dist.shop.app.server.mall.service.OrderDtlService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author huangtao
 * @date 2018-06-28 18:16:12
 * @version 1.0
 * @copyright facegarden.com
 */
@Service("orderDtlServiceImpl")
public class OrderDtlServiceImpl extends BaseCrudServiceImpl implements OrderDtlService {

  @Resource private OrderDtlMapper orderDtlMapper;

  @Override
  public CrudMapper init() {
    return orderDtlMapper;
  }

  @Override
  public Integer selectEvalCount(Map<String, Object> params) throws ServiceException{
    return orderDtlMapper.selectEvalCount(params);
  }

  @Override
  public List<OrderDtl> selectEvalByPage(SimplePage page, Map<String, Object> params) throws ServiceException {
    return orderDtlMapper.selectEvalByPage(page, params);
  }
}
