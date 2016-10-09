package cn.eqianyuan.bean.vo;

/**
 * 供应商报名需求对象
 * Created by jason on 2016-09-26.
 */
public class SupplierSignUpDemandVO {

    private String id;

    private String name;

    private String beginCycle;

    private String endCycle;

    private String work;

    private String workText;

    private Integer personsAmount;

    private String demandDiscribe;

    private String signUpWork;

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

    public String getBeginCycle() {
        return beginCycle;
    }

    public void setBeginCycle(String beginCycle) {
        this.beginCycle = beginCycle;
    }

    public String getEndCycle() {
        return endCycle;
    }

    public void setEndCycle(String endCycle) {
        this.endCycle = endCycle;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getWorkText() {
        return workText;
    }

    public void setWorkText(String workText) {
        this.workText = workText;
    }

    public Integer getPersonsAmount() {
        return personsAmount;
    }

    public void setPersonsAmount(Integer personsAmount) {
        this.personsAmount = personsAmount;
    }

    public String getDemandDiscribe() {
        return demandDiscribe;
    }

    public void setDemandDiscribe(String demandDiscribe) {
        this.demandDiscribe = demandDiscribe;
    }

    public String getSignUpWork() {
        return signUpWork;
    }

    public void setSignUpWork(String signUpWork) {
        this.signUpWork = signUpWork;
    }
}
