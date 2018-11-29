package com.towcent.dist.shop.portal.me.vo.input;


import com.towcent.dist.shop.portal.common.vo.BaseParam;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 3.3.1 钱包/积分明细
 * @author huangtao
 * @version 0.0.1
 */
@Data
public class WalletIncomeListIn extends BaseParam {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@NotBlank(message = "tabFlag不能为空.")
	private String tabFlag;		// Tab(1:余额 2:积分) 默认1全部
	
	
	
}