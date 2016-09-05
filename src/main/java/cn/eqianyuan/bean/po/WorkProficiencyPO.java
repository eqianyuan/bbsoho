package cn.eqianyuan.bean.po;

public class WorkProficiencyPO {
    private Integer id;

    private String work;

    private Integer masterDegree;

    private String resumeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work == null ? null : work.trim();
    }

    public Integer getMasterDegree() {
        return masterDegree;
    }

    public void setMasterDegree(Integer masterDegree) {
        this.masterDegree = masterDegree;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId == null ? null : resumeId.trim();
    }
}