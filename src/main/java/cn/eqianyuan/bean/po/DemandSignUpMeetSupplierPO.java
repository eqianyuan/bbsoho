package cn.eqianyuan.bean.po;

/**
 * 需求约见对象
 * Created by jason on 2016-09-27.
 */
public class DemandSignUpMeetSupplierPO {
    private String supplierSideId;

    private String demandId;

    //供应商昵称
    private String nickName;

    //供应商头像
    private String headPortrait;

    //工龄
    private Integer workingYears;

    //期望薪资
    private Integer expectPay;

    //报名岗位
    private String signUpWork;

    //约见时间
    private Integer meetTime;

    //约见状态
    private Integer status;

    public String getSupplierSideId() {
        return supplierSideId;
    }

    public void setSupplierSideId(String supplierSideId) {
        this.supplierSideId = supplierSideId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(Integer workingYears) {
        this.workingYears = workingYears;
    }

    public Integer getExpectPay() {
        return expectPay;
    }

    public void setExpectPay(Integer expectPay) {
        this.expectPay = expectPay;
    }

    public String getSignUpWork() {
        return signUpWork;
    }

    public void setSignUpWork(String signUpWork) {
        this.signUpWork = signUpWork;
    }

    public Integer getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(Integer meetTime) {
        this.meetTime = meetTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
