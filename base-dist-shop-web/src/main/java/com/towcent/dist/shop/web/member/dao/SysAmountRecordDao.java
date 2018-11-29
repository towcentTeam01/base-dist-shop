package com.towcent.dist.shop.web.member.dao;

import com.towcent.base.sc.web.common.persistence.CrudDao;
import com.towcent.base.common.annotation.MyBatisDao;
import com.towcent.dist.shop.web.member.entity.SysAmountRecord;

/**
 * 钱包明细DAO接口
 * @author huangtao
 * @version 2018-07-08
 */
@MyBatisDao
public interface SysAmountRecordDao extends CrudDao<SysAmountRecord> {
	
}