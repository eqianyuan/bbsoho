package cn.eqianyuan.bean.vo;

public class SupplierHireDemandVO {
    private String id;

    private String name;

    private String beginCycle;

    private String endCycle;

    private String work;

    private Integer personsAmount;

    private String demandDiscribe;

    private String signUpWork;

    private Integer status;

    private String statusText;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}