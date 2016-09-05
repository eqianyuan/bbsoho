package cn.eqianyuan.bean.vo;

import cn.eqianyuan.bean.po.ProjectExperiencePO;
import cn.eqianyuan.bean.po.WorkExperiencePO;
import cn.eqianyuan.bean.po.WorkProficiencyPO;

import java.util.List;

public class SupplierSideVOByResume {
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

    private List<WorkProficiency> workProficiencies;

    private List<WorkExperience> workExperiences;

    private List<ProjectExperience> projectExperiences;

    public class WorkProficiency extends WorkProficiencyPO {
    }

    public class WorkExperience extends WorkExperiencePO{}

    public class ProjectExperience extends ProjectExperiencePO{}

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

    public List<WorkProficiency> getWorkProficiencies() {
        return workProficiencies;
    }

    public void setWorkProficiencies(List<WorkProficiency> workProficiencies) {
        this.workProficiencies = workProficiencies;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<ProjectExperience> getProjectExperiences() {
        return projectExperiences;
    }

    public void setProjectExperiences(List<ProjectExperience> projectExperiences) {
        this.projectExperiences = projectExperiences;
    }
}