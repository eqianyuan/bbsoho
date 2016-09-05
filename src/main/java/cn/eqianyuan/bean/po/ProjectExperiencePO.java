package cn.eqianyuan.bean.po;

public class ProjectExperiencePO
{
    private Integer id;

    private Integer beginYears;

    private Integer beginMonth;

    private Integer endYears;

    private Integer endMonth;

    private String projectName;

    private String visitAddress;

    private String projectDiscribe;

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getVisitAddress() {
        return visitAddress;
    }

    public void setVisitAddress(String visitAddress) {
        this.visitAddress = visitAddress == null ? null : visitAddress.trim();
    }

    public String getProjectDiscribe() {
        return projectDiscribe;
    }

    public void setProjectDiscribe(String projectDiscribe) {
        this.projectDiscribe = projectDiscribe;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId == null ? null : resumeId.trim();
    }
}