package com.towcent.dist.shop.web.sys.entity;

import com.towcent.base.sc.web.modules.sys.entity.User;

import com.towcent.base.sc.web.common.persistence.DataEntity;

/**
 * 系统用户商户关系Entity
 *
 * @author yxp
 * @version 2018-07-19
 */
public class SysUserMerchantRel extends DataEntity<SysUserMerchantRel> {

    private static final long serialVersionUID = 1L;
    private User user;        // 系统用户
    private SysMerchantInfo merchant;        // 商户

    public SysUserMerchantRel() {
        super();
    }

    public SysUserMerchantRel(String id) {
        super(id);
    }

    public SysMerchantInfo getMerchant() {
        return merchant;
    }

    public void setMerchant(SysMerchantInfo merchant) {
        this.merchant = merchant;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
}