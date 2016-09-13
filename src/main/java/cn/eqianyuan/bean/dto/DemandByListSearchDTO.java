package cn.eqianyuan.bean.dto;

/**
 * 需求大厅查找对象
 * Created by jason on 2016-09-09.
 */
public class DemandByListSearchDTO {

    //市
    private String cityId;

    //区
    private String countyId;

    //工种
    private String work;

    //工龄经验
    private Integer workingYears;

    //薪酬
    private Integer remuneration;

    private String id;

    private String name;

    private Integer workingLunch;

    private Integer beginCycle;

    private Integer endCycle;

    private Integer publishTime;

    private Integer personsAmount;

    private String countyName;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Integer getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(Integer workingYears) {
        this.workingYears = workingYears;
    }

    public Integer getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Integer remuneration) {
        this.remuneration = remuneration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWorkingLunch() {
        return workingLunch;
    }

    public void setWorkingLunch(Integer workingLunch) {
        this.workingLunch = workingLunch;
    }

    public Integer getBeginCycle() {
        return beginCycle;
    }

    public void setBeginCycle(Integer beginCycle) {
        this.beginCycle = beginCycle;
    }

    public Integer getEndCycle() {
        return endCycle;
    }

    public void setEndCycle(Integer endCycle) {
        this.endCycle = endCycle;
    }

    public Integer getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getPersonsAmount() {
        return personsAmount;
    }

    public void setPersonsAmount(Integer personsAmount) {
        this.personsAmount = personsAmount;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
