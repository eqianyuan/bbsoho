package cn.eqianyuan.bean.vo;

import java.util.List;

/**
 * Created by jason on 2016-09-18.
 */
public class SupplierResumeVOBySearchInfo {

    private String id;

    private String headPortrait;

    private String nickName;

    private String sex;

    private String age;

    private String highestSchooling;

    private String schoolName;

    private String professionalName;

    private String workingYears;

    private String status;

    private String expectWorkProvinceId;

    private String expectWorkCityId;

    private String expectWorkCountyId;

    private String industry;

    private String workType;

    private String work;

    private String expectPay;

    private String expectWorkTime;

    private String foreignLanguage;

    private String foreignLanguageAbility;

    private String discribe;

    private List<WorkProficiencyVO> workProficiencyVOs;
    private List<WorkExperienceVO> workExperienceVOs;
    private List<ProjectExperienceVO> projectExperienceVOs;


    public class WorkProficiencyVO {
        private String work;

        private Integer masterDegree;

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public Integer getMasterDegree() {
            return masterDegree;
        }

        public void setMasterDegree(Integer masterDegree) {
            this.masterDegree = masterDegree;
        }
    }

    public class WorkExperienceVO {
        private Integer beginYears;

        private Integer beginMonth;

        private Integer endYears;

        private Integer endMonth;

        private String companyName;

        private String positionName;

        private String workDiscribe;

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
            this.companyName = companyName;
        }

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public String getWorkDiscribe() {
            return workDiscribe;
        }

        public void setWorkDiscribe(String workDiscribe) {
            this.workDiscribe = workDiscribe;
        }
    }

    public class ProjectExperienceVO {
        private Integer beginYears;

        private Integer beginMonth;

        private Integer endYears;

        private Integer endMonth;

        private String projectName;

        private String visitAddress;

        private String projectDiscribe;

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
            this.projectName = projectName;
        }

        public String getVisitAddress() {
            return visitAddress;
        }

        public void setVisitAddress(String visitAddress) {
            this.visitAddress = visitAddress;
        }

        public String getProjectDiscribe() {
            return projectDiscribe;
        }

        public void setProjectDiscribe(String projectDiscribe) {
            this.projectDiscribe = projectDiscribe;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHighestSchooling() {
        return highestSchooling;
    }

    public void setHighestSchooling(String highestSchooling) {
        this.highestSchooling = highestSchooling;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(String workingYears) {
        this.workingYears = workingYears;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpectWorkProvinceId() {
        return expectWorkProvinceId;
    }

    public void setExpectWorkProvinceId(String expectWorkProvinceId) {
        this.expectWorkProvinceId = expectWorkProvinceId;
    }

    public String getExpectWorkCityId() {
        return expectWorkCityId;
    }

    public void setExpectWorkCityId(String expectWorkCityId) {
        this.expectWorkCityId = expectWorkCityId;
    }

    public String getExpectWorkCountyId() {
        return expectWorkCountyId;
    }

    public void setExpectWorkCountyId(String expectWorkCountyId) {
        this.expectWorkCountyId = expectWorkCountyId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getExpectPay() {
        return expectPay;
    }

    public void setExpectPay(String expectPay) {
        this.expectPay = expectPay;
    }

    public String getExpectWorkTime() {
        return expectWorkTime;
    }

    public void setExpectWorkTime(String expectWorkTime) {
        this.expectWorkTime = expectWorkTime;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public String getForeignLanguageAbility() {
        return foreignLanguageAbility;
    }

    public void setForeignLanguageAbility(String foreignLanguageAbility) {
        this.foreignLanguageAbility = foreignLanguageAbility;
    }

    public String getDiscribe() {
        return discribe;
    }

    public void setDiscribe(String discribe) {
        this.discribe = discribe;
    }

    public List<WorkProficiencyVO> getWorkProficiencyVOs() {
        return workProficiencyVOs;
    }

    public void setWorkProficiencyVOs(List<WorkProficiencyVO> workProficiencyVOs) {
        this.workProficiencyVOs = workProficiencyVOs;
    }

    public List<WorkExperienceVO> getWorkExperienceVOs() {
        return workExperienceVOs;
    }

    public void setWorkExperienceVOs(List<WorkExperienceVO> workExperienceVOs) {
        this.workExperienceVOs = workExperienceVOs;
    }

    public List<ProjectExperienceVO> getProjectExperienceVOs() {
        return projectExperienceVOs;
    }

    public void setProjectExperienceVOs(List<ProjectExperienceVO> projectExperienceVOs) {
        this.projectExperienceVOs = projectExperienceVOs;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
