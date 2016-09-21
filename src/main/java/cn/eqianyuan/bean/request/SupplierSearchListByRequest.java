package cn.eqianyuan.bean.request;

/**
 * 人才库查找对象
 * Created by jason on 2016-09-09.
 */
public class SupplierSearchListByRequest {

    //市
    private String cityId;

    //区
    private String countyId;

    //工种
    private String work;

    //工龄经验
    private Integer workingYears;

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

}
