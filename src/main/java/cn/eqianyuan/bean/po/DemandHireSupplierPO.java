package cn.eqianyuan.bean.po;

/**
 * Created by jason on 2016-09-28.
 */
public class DemandHireSupplierPO {
    private String id;

    //供应商昵称
    private String nickName;

    //供应商头像
    private String headPortrait;

    //工龄
    private Integer workingYears;

    //期望薪资
    private Integer expectPay;

    //合同报酬
    private Integer remuneration;

    //工作岗位
    private String work;

    //聘用状态
    private Integer status;

    //合同生效时间
    private Integer contractComesIntoEffectTime;

    //合同到期时间
    private Integer contractExpiresTime;

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

    public Integer getContractComesIntoEffectTime() {
        return contractComesIntoEffectTime;
    }

    public void setContractComesIntoEffectTime(Integer contractComesIntoEffectTime) {
        this.contractComesIntoEffectTime = contractComesIntoEffectTime;
    }

    public Integer getContractExpiresTime() {
        return contractExpiresTime;
    }

    public void setContractExpiresTime(Integer contractExpiresTime) {
        this.contractExpiresTime = contractExpiresTime;
    }
}
