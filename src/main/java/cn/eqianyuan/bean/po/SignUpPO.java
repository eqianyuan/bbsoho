package cn.eqianyuan.bean.po;

public class SignUpPO {
    private String id;

    private String supplierSideId;

    private String demandId;

    private String work;

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
        this.demandId = demandId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
}