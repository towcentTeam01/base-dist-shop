package com.towcent.dist.shop.app.server.order.manager;

import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_0;
import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_1;
import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_2;
import static com.towcent.dist.shop.common.Constant.BIZ_TYPE_3;
import static com.towcent.dist.shop.common.Constant.ID;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.towcent.base.common.exception.RpcException;
import com.towcent.base.common.exception.ServiceException;
import com.towcent.base.common.utils.StringUtils;
import com.towcent.base.service.BaseService;
import com.towcent.dist.shop.app.client.mall.dto.OrderMain;
import com.towcent.dist.shop.app.client.mall.dto.OrderPayRecord;
import com.towcent.dist.shop.app.client.me.service.BrokerageApi;
import com.towcent.dist.shop.app.client.sys.service.SysFrontAccountApi;
import com.towcent.dist.shop.app.server.mall.service.OrderMainService;
import com.towcent.dist.shop.app.server.mall.service.OrderPayRecordService;
import com.towcent.dist.shop.app.server.order.pay.SdxPayAccountService;
import com.towcent.dist.shop.app.server.order.pay.SdxPayResponse;
import com.towcent.dist.shop.app.server.order.vo.PayCallVo;
import com.towcent.dist.shop.app.server.sys.service.SysFrontAccountService;
import com.towcent.dist.shop.common.Constant;

/**
 * Created by huangtao on 2017/6/29.
 */
@Service
public class SdxPayAccountApiImpl extends BaseService implements SdxPayAccountApi {

    @Autowired
    private SdxPayAccountService payAccountService;
    @Resource
    private SysFrontAccountService sysFrontAccountService;
    @Resource
    private SysFrontAccountApi sysFrontAccountApi;
    @Resource
    private OrderPayRecordService payRecordService;
    @Resource
    private OrderMainService orderMainService;
    @Resource
    private BrokerageApi brokerageApi;
    
    @Override
    public SdxPayResponse getPayResponse(Integer merchantId, Integer id) {
        return payAccountService.getPayResponse(merchantId, id);
    }
    
    @Override @Transactional
    public boolean finishPayCall(PayCallVo payCallVo) throws ServiceException, RpcException {
    	boolean result = false;
    	OrderPayRecord record = payRecordService.findByKeyValSingle("payRecordNo", payCallVo.getPayRecordNo());
    	if (null == record) return false;
    	record.setPayDate(payCallVo.getPayDate());
    	record.setThirdPaySn(payCallVo.getThirdPaySn());
    	record.setPayStatus(Constant.PAY_STATUS_1);
    	record.setUpdateDate(new Date());
    	if (payRecordService.modifyById(record) > 0) {
    		// 0:普通订单
    		if (StringUtils.equals(record.getBizType(), BIZ_TYPE_0)) {
    			OrderMain orderMainOld = orderMainService.findByKeyValSingle(ID, record.getOrderId());
    			OrderMain orderMainNew = new OrderMain();
    			orderMainNew.setId(orderMainOld.getId());
    			orderMainNew.setPayDate(record.getPayDate());
    			orderMainNew.setPayStatus(record.getPayStatus());
    			
    			result = orderMainService.modifyById(orderMainNew) > 0;
    			
    			// 如果余额支付部分, 则需要将冻结金额释放
    			sysFrontAccountService.discountAmount(record.getCreateBy(), record.getBalanceAmount(), record.getPayRecordNo(), true);
    			
    		} else if (StringUtils.equals(record.getBizType(), BIZ_TYPE_1)) {
    			// 1:充值
    			result = sysFrontAccountService.increaseAmount(record.getCreateBy(), record.getPayAmount(), record.getPayRecordNo());
    		} else if (StringUtils.equals(record.getBizType(), BIZ_TYPE_2)) {
    			// 1:购买铂金会员
    			result = sysFrontAccountService.upgradeMember(record.getCreateBy(), "3");
    			brokerageApi.calculateBkgeBuyMember(record);
    		} else if (StringUtils.equals(record.getBizType(), BIZ_TYPE_3)) {
    			// 1:购买钻石会员
    			result = sysFrontAccountService.upgradeMember(record.getCreateBy(), "4");
    			brokerageApi.calculateBkgeBuyMember(record);
    		}
    	}
    	return result;
    }
}
