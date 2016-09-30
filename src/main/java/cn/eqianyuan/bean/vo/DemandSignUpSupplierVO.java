package cn.eqianyuan.bean.vo;

/**
 * 需求报名供应商信息对象
 * Created by jason on 2016-09-27.
 */
public class DemandSignUpSupplierVO {
    //供应商信息主键
    private String id;

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

    //报名时间
    private String signUpTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSignUpTime() {
        return signUpTime;
    }

    public void setSignUpTime(String signUpTime) {
        this.signUpTime = signUpTime;
    }
}
