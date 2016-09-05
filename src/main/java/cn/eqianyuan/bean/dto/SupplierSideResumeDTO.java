package cn.eqianyuan.bean.dto;

import cn.eqianyuan.bean.po.ProjectExperiencePO;
import cn.eqianyuan.bean.po.WorkExperiencePO;
import cn.eqianyuan.bean.po.WorkProficiencyPO;

import java.util.List;

public class SupplierSideResumeDTO {
    private String id;

    private String expectWorkProvinceId;

    private String expectWorkCityId;

    private String expectWorkCountyId;

    private Integer industry;

    private String workType;

    private Integer expectPay;

    private Integer expectWorkTime;

    private Integer foreignLanguage;

    private Integer foreignLanguageAbility;

    private String discribe;

    private String supplierSideId;

    private List<WorkProficiencyDTO> workProficiencyDTOList;
    private List<WorkExperienceDTO> workExperienceDTOList;
    private List<ProjectExperienceDTO> projectExperienceDTOList;

    public class WorkProficiencyDTO extends WorkProficiencyPO {
    }

    public class WorkExperienceDTO extends WorkExperiencePO {
        private String workCycleBegin;
        private String workCycleEnd;

        public String getWorkCycleBegin() {
            return workCycleBegin;
        }

        public void setWorkCycleBegin(String workCycleBegin) {
            this.workCycleBegin = workCycleBegin;
        }

        public String getWorkCycleEnd() {
            return workCycleEnd;
        }

        public void setWorkCycleEnd(String workCycleEnd) {
            this.workCycleEnd = workCycleEnd;
        }
    }

    public class ProjectExperienceDTO extends ProjectExperiencePO {
        private String projectCycleBegin;
        private String projectCycleEnd;

        public String getProjectCycleBegin() {
            return projectCycleBegin;
        }

        public void setProjectCycleBegin(String projectCycleBegin) {
            this.projectCycleBegin = projectCycleBegin;
        }

        public String getProjectCycleEnd() {
            return projectCycleEnd;
        }

        public void setProjectCycleEnd(String projectCycleEnd) {
            this.projectCycleEnd = projectCycleEnd;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExpectWorkProvinceId() {
        return expectWorkProvinceId;
    }

    public void setExpectWorkProvinceId(String expectWorkProvinceId) {
        this.expectWorkProvinceId = expectWorkProvinceId == null ? null : expectWorkProvinceId.trim();
    }

    public String getExpectWorkCityId() {
        return expectWorkCityId;
    }

    public void setExpectWorkCityId(String expectWorkCityId) {
        this.expectWorkCityId = expectWorkCityId == null ? null : expectWorkCityId.trim();
    }

    public String getExpectWorkCountyId() {
        return expectWorkCountyId;
    }

    public void setExpectWorkCountyId(String expectWorkCountyId) {
        this.expectWorkCountyId = expectWorkCountyId == null ? null : expectWorkCountyId.trim();
    }

    public Integer getIndustry() {
        return industry;
    }

    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getExpectPay() {
        return expectPay;
    }

    public void setExpectPay(Integer expectPay) {
        this.expectPay = expectPay;
    }

    public Integer getExpectWorkTime() {
        return expectWorkTime;
    }

    public void setExpectWorkTime(Integer expectWorkTime) {
        this.expectWorkTime = expectWorkTime;
    }

    public Integer getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(Integer foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public Integer getForeignLanguageAbility() {
        return foreignLanguageAbility;
    }

    public void setForeignLanguageAbility(Integer foreignLanguageAbility) {
        this.foreignLanguageAbility = foreignLanguageAbility;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe == null ? null : discribe.trim();
    }

    public String getSupplierSideId() {
        return supplierSideId;
    }

    public void setSupplierSideId(String supplierSideId) {
        this.supplierSideId = supplierSideId == null ? null : supplierSideId.trim();
    }

    public List<WorkProficiencyDTO> getWorkProficiencyDTOList() {
        return workProficiencyDTOList;
    }

    public void setWorkProficiencyDTOList(List<WorkProficiencyDTO> workProficiencyDTOList) {
        this.workProficiencyDTOList = workProficiencyDTOList;
    }

    public List<WorkExperienceDTO> getWorkExperienceDTOList() {
        return workExperienceDTOList;
    }

    public void setWorkExperienceDTOList(List<WorkExperienceDTO> workExperienceDTOList) {
        this.workExperienceDTOList = workExperienceDTOList;
    }

    public List<ProjectExperienceDTO> getProjectExperienceDTOList() {
        return projectExperienceDTOList;
    }

    public void setProjectExperienceDTOList(List<ProjectExperienceDTO> projectExperienceDTOList) {
        this.projectExperienceDTOList = projectExperienceDTOList;
    }
}