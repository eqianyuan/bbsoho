package cn.eqianyuan.bean.po;

public class SupplierSignUpMeetDemandPO {
    private String id;

    private String name;

    private Integer beginCycle;

    private Integer endCycle;

    private String work;

    private Integer personsAmount;

    private String demandDiscribe;

    private String signUpWork;

    private Integer status;

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

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public Integer getPersonsAmount() {
        return personsAmount;
    }

    public void setPersonsAmount(Integer personsAmount) {
        this.personsAmount = personsAmount;
    }

    public String getSignUpWork() {
        return signUpWork;
    }

    public void setSignUpWork(String signUpWork) {
        this.signUpWork = signUpWork;
    }

    public String getDemandDiscribe() {
        return demandDiscribe;
    }

    public void setDemandDiscribe(String demandDiscribe) {
        this.demandDiscribe = demandDiscribe;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}