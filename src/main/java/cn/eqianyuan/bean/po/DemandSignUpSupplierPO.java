package cn.eqianyuan.bean.po;

/**
 * 需求报名供应商信息对象
 * Created by jason on 2016-09-27.
 */
public class DemandSignUpSupplierPO {
    //供应商编号
    private String supplierSideId;

    //需求编号
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

    //报名时间
    private Integer signUpTime;

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

    public Integer getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(Integer signUpTime) {
        this.signUpTime = signUpTime;
    }
}
