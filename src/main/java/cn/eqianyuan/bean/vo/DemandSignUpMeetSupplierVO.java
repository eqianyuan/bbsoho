package cn.eqianyuan.bean.vo;

/**
 * 需求约见对象
 * Created by jason on 2016-09-27.
 */
public class DemandSignUpMeetSupplierVO {
    private String supplierSideId;

    private String demandId;

    //供应商昵称
    private String nickName;

    //供应商头像
    private String headPortrait;

    //工龄
    private String workingYears;

    //期望薪资
    private String expectPay;

    //报名岗位
    private String signUpWork;

    //约见时间
    private String meetTime;

    //约见状态
    private Integer status;

    private String statusText;

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

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    public String getExpectPay() {
        return expectPay;
    }

    public void setExpectPay(String expectPay) {
        this.expectPay = expectPay;
    }

    public String getSignUpWork() {
        return signUpWork;
    }

    public void setSignUpWork(String signUpWork) {
        this.signUpWork = signUpWork;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
