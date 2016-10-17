package cn.eqianyuan.bean.vo;

/**
 * 供应商合同信息对象
 * Created by jason on 2016-10-12.
 */
public class SupplierContractInfoVO {

    //合同开始日期
    private String contractComesIntoEffectTime;

    //合同结束日期
    private String contractExpiresTime;

    //合同薪水（月薪）
    private Integer remuneration;

    //工作岗位
    private String workText;

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

    public Integer getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Integer remuneration) {
        this.remuneration = remuneration;
    }

    public String getWorkText() {
        return workText;
    }

    public void setWorkText(String workText) {
        this.workText = workText;
    }
}
