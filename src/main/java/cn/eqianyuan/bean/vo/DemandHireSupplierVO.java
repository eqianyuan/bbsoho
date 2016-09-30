package cn.eqianyuan.bean.vo;

/**
 * Created by jason on 2016-09-28.
 */
public class DemandHireSupplierVO {
    private String id;

    //供应商昵称
    private String nickName;

    //供应商头像
    private String headPortrait;

    //工龄
    private String workingYears;

    //期望薪资
    private String expectPay;

    //合同报酬
    private Integer remuneration;

    //工作岗位
    private String work;

    //聘用状态
    private Integer status;

    private String statusText;

    //合同生效时间
    private String contractComesIntoEffectTime;

    //合同到期时间
    private String contractExpiresTime;

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

    public Integer getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Integer remuneration) {
        this.remuneration = remuneration;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
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

    public String getContractComesIntoEffectTime() {
        return contractComesIntoEffectTime;
    }

    public void setContractComesIntoEffectTime(String contractComesIntoEffectTime) {
        this.contractComesIntoEffectTime = contractComesIntoEffectTime;
    }

    public String getContractExpiresTime() {
        return contractExpiresTime;
    }

    public void setContractExpiresTime(String contractExpiresTime) {
        this.contractExpiresTime = contractExpiresTime;
    }
}
