package cn.eqianyuan.bean.request;

/**
 * 聘用供应商请求对象
 * Created by jason on 2016-10-09.
 */
public class SupplierHireRequest {
    //需求编号
    private String demandId;
    //供应商编号
    private String supplierSideId;
    //报酬
    private String remuneration;
    //合同生效日期
    private String contractComesIntoEffectTime;
    //合同失效日期
    private String contractExpiresTime;
    //聘用状态：3:不聘用
    private String status;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getSupplierSideId() {
        return supplierSideId;
    }

    public void setSupplierSideId(String supplierSideId) {
        this.supplierSideId = supplierSideId;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
