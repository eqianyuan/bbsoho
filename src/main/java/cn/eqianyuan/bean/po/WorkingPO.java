package cn.eqianyuan.bean.po;

public class WorkingPO {
    private String id;

    private String supplierSideId;

    private String demandId;

    private Integer remuneration;

    private String work;

    private Integer contractComesIntoEffectTime;

    private Integer contractExpiresTime;

    private Integer createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSupplierSideId() {
        return supplierSideId;
    }

    public void setSupplierSideId(String supplierSideId) {
        this.supplierSideId = supplierSideId == null ? null : supplierSideId.trim();
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId == null ? null : demandId.trim();
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
        this.work = work == null ? null : work.trim();
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

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}