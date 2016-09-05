package cn.eqianyuan.bean.po;

public class WorkExperiencePO {
    private Integer id;

    private Integer beginYears;

    private Integer beginMonth;

    private Integer endYears;

    private Integer endMonth;

    private String companyName;

    private String positionName;

    private String workDiscribe;

    private String resumeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBeginYears() {
        return beginYears;
    }

    public void setBeginYears(Integer beginYears) {
        this.beginYears = beginYears;
    }

    public Integer getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(Integer beginMonth) {
        this.beginMonth = beginMonth;
    }

    public Integer getEndYears() {
        return endYears;
    }

    public void setEndYears(Integer endYears) {
        this.endYears = endYears;
    }

    public Integer getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Integer endMonth) {
        this.endMonth = endMonth;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName == null ? null : positionName.trim();
    }

    public String getWorkDiscribe() {
        return workDiscribe;
    }

    public void setWorkDiscribe(String workDiscribe) {
        this.workDiscribe = workDiscribe;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId == null ? null : resumeId.trim();
    }
}