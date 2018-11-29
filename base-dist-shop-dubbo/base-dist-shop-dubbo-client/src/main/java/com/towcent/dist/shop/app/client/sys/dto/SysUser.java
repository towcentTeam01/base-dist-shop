package com.towcent.dist.shop.app.client.sys.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Generator
 * @date 2017-06-17 23:27:06
 * @version 1.0.0
 * @copyright facegarden.com
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号.
     */
    private Integer id;

    /**
     * 归属公司.
     */
    private Integer companyId;

    /**
     * 归属部门.
     */
    private Integer officeId;

    /**
     * 登录名.
     */
    private String loginName;

    /**
     * 密码.
     */
    private String password;

    /**
     * 工号.
     */
    private String no;

    /**
     * 姓名.
     */
    private String name;

    /**
     * 邮箱.
     */
    private String email;

    /**
     * 电话.
     */
    private String phone;

    /**
     * 手机.
     */
    private String mobile;

    /**
     * 用户类型.
     */
    private String userType;

    /**
     * 用户头像.
     */
    private String photo;

    /**
     * 最后登陆IP.
     */
    private String loginIp;

    /**
     * 最后登陆时间.
     */
    private Date loginDate;

    /**
     * 是否可登录.
     */
    private String loginFlag;

    /**
     * 创建者.
     */
    private String createBy;

    /**
     * 创建时间.
     */
    private Date createDate;

    /**
     * 更新者.
     */
    private String updateBy;

    /**
     * 更新时间.
     */
    private Date updateDate;

    /**
     * 备注信息.
     */
    private String remarks;

    /**
     * 删除标记.
     */
    private String delFlag;

    /**
     * 性别(0:女 1:男).
     */
    private String sex;

    /**
     * 职务.
     */
    private String job;

    /**
     * 入职时间.
     */
    private Date entryDate;

    /**
     * 生日.
     */
    private Date birthday;

    /**
     * 基础工资.
     */
    private BigDecimal basicSalary;

    /**
     * app是否可以登录(1:是 0:否).
     */
    private String appLoginFlag;

    /**
     * 操作状态1:正常 2:任务中3:休假中.
     */
    private Byte status;

    /**
     * 休假开始时间.
     */
    private Date vacationBeginTime;

    /**
     * 休假结束时间.
     */
    private Date vacationEndTime;

    /**
     * 
     * {@linkplain #id}
     *
     * @return the value of sys_user.id
     */
    public Integer getId() {
        return id;
    }

    /**
     * {@linkplain #id}
     * @param id the value for sys_user.id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * {@linkplain #companyId}
     *
     * @return the value of sys_user.company_id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * {@linkplain #companyId}
     * @param companyId the value for sys_user.company_id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 
     * {@linkplain #officeId}
     *
     * @return the value of sys_user.office_id
     */
    public Integer getOfficeId() {
        return officeId;
    }

    /**
     * {@linkplain #officeId}
     * @param officeId the value for sys_user.office_id
     */
    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    /**
     * 
     * {@linkplain #loginName}
     *
     * @return the value of sys_user.login_name
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * {@linkplain #loginName}
     * @param loginName the value for sys_user.login_name
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 
     * {@linkplain #password}
     *
     * @return the value of sys_user.password
     */
    public String getPassword() {
        return password;
    }

    /**
     * {@linkplain #password}
     * @param password the value for sys_user.password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 
     * {@linkplain #no}
     *
     * @return the value of sys_user.no
     */
    public String getNo() {
        return no;
    }

    /**
     * {@linkplain #no}
     * @param no the value for sys_user.no
     */
    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    /**
     * 
     * {@linkplain #name}
     *
     * @return the value of sys_user.name
     */
    public String getName() {
        return name;
    }

    /**
     * {@linkplain #name}
     * @param name the value for sys_user.name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 
     * {@linkplain #email}
     *
     * @return the value of sys_user.email
     */
    public String getEmail() {
        return email;
    }

    /**
     * {@linkplain #email}
     * @param email the value for sys_user.email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 
     * {@linkplain #phone}
     *
     * @return the value of sys_user.phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * {@linkplain #phone}
     * @param phone the value for sys_user.phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 
     * {@linkplain #mobile}
     *
     * @return the value of sys_user.mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * {@linkplain #mobile}
     * @param mobile the value for sys_user.mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 
     * {@linkplain #userType}
     *
     * @return the value of sys_user.user_type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * {@linkplain #userType}
     * @param userType the value for sys_user.user_type
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 
     * {@linkplain #photo}
     *
     * @return the value of sys_user.photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * {@linkplain #photo}
     * @param photo the value for sys_user.photo
     */
    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    /**
     * 
     * {@linkplain #loginIp}
     *
     * @return the value of sys_user.login_ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * {@linkplain #loginIp}
     * @param loginIp the value for sys_user.login_ip
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 
     * {@linkplain #loginDate}
     *
     * @return the value of sys_user.login_date
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * {@linkplain #loginDate}
     * @param loginDate the value for sys_user.login_date
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 
     * {@linkplain #loginFlag}
     *
     * @return the value of sys_user.login_flag
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * {@linkplain #loginFlag}
     * @param loginFlag the value for sys_user.login_flag
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag == null ? null : loginFlag.trim();
    }

    /**
     * 
     * {@linkplain #createBy}
     *
     * @return the value of sys_user.create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * {@linkplain #createBy}
     * @param createBy the value for sys_user.create_by
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 
     * {@linkplain #createDate}
     *
     * @return the value of sys_user.create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * {@linkplain #createDate}
     * @param createDate the value for sys_user.create_date
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 
     * {@linkplain #updateBy}
     *
     * @return the value of sys_user.update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * {@linkplain #updateBy}
     * @param updateBy the value for sys_user.update_by
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * 
     * {@linkplain #updateDate}
     *
     * @return the value of sys_user.update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * {@linkplain #updateDate}
     * @param updateDate the value for sys_user.update_date
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 
     * {@linkplain #remarks}
     *
     * @return the value of sys_user.remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * {@linkplain #remarks}
     * @param remarks the value for sys_user.remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * 
     * {@linkplain #delFlag}
     *
     * @return the value of sys_user.del_flag
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * {@linkplain #delFlag}
     * @param delFlag the value for sys_user.del_flag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

    /**
     * 
     * {@linkplain #sex}
     *
     * @return the value of sys_user.sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * {@linkplain #sex}
     * @param sex the value for sys_user.sex
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 
     * {@linkplain #job}
     *
     * @return the value of sys_user.job
     */
    public String getJob() {
        return job;
    }

    /**
     * {@linkplain #job}
     * @param job the value for sys_user.job
     */
    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    /**
     * 
     * {@linkplain #entryDate}
     *
     * @return the value of sys_user.entry_date
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     * {@linkplain #entryDate}
     * @param entryDate the value for sys_user.entry_date
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     * 
     * {@linkplain #birthday}
     *
     * @return the value of sys_user.birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * {@linkplain #birthday}
     * @param birthday the value for sys_user.birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 
     * {@linkplain #basicSalary}
     *
     * @return the value of sys_user.basic_salary
     */
    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    /**
     * {@linkplain #basicSalary}
     * @param basicSalary the value for sys_user.basic_salary
     */
    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    /**
     * 
     * {@linkplain #appLoginFlag}
     *
     * @return the value of sys_user.app_login_flag
     */
    public String getAppLoginFlag() {
        return appLoginFlag;
    }

    /**
     * {@linkplain #appLoginFlag}
     * @param appLoginFlag the value for sys_user.app_login_flag
     */
    public void setAppLoginFlag(String appLoginFlag) {
        this.appLoginFlag = appLoginFlag == null ? null : appLoginFlag.trim();
    }

    /**
     * 
     * {@linkplain #status}
     *
     * @return the value of sys_user.status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * {@linkplain #status}
     * @param status the value for sys_user.status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 
     * {@linkplain #vacationBeginTime}
     *
     * @return the value of sys_user.vacation_begin_time
     */
    public Date getVacationBeginTime() {
        return vacationBeginTime;
    }

    /**
     * {@linkplain #vacationBeginTime}
     * @param vacationBeginTime the value for sys_user.vacation_begin_time
     */
    public void setVacationBeginTime(Date vacationBeginTime) {
        this.vacationBeginTime = vacationBeginTime;
    }

    /**
     * 
     * {@linkplain #vacationEndTime}
     *
     * @return the value of sys_user.vacation_end_time
     */
    public Date getVacationEndTime() {
        return vacationEndTime;
    }

    /**
     * {@linkplain #vacationEndTime}
     * @param vacationEndTime the value for sys_user.vacation_end_time
     */
    public void setVacationEndTime(Date vacationEndTime) {
        this.vacationEndTime = vacationEndTime;
    }
}