package cn.eqianyuan.bean.po;

public class DemandPO {
    private String id;

    private String name;

    private Integer workingLunch;

    private String contact;

    private Integer respectfulName;

    private String phoneAreaCode;

    private Integer telephoneNumber;

    private String extensionNumber;

    private Long mobileNumber;

    private String demandDiscribe;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String address;

    private Integer beginCycle;

    private Integer endCycle;

    private String demandSideId;

    private Integer publishTime;

    private Integer endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getWorkingLunch() {
        return workingLunch;
    }

    public void setWorkingLunch(Integer workingLunch) {
        this.workingLunch = workingLunch;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public Integer getRespectfulName() {
        return respectfulName;
    }

    public void setRespectfulName(Integer respectfulName) {
        this.respectfulName = respectfulName;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode == null ? null : phoneAreaCode.trim();
    }

    public Integer getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(Integer telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber == null ? null : extensionNumber.trim();
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDemandDiscribe() {
        return demandDiscribe;
    }

    public void setDemandDiscribe(String demandDiscribe) {
        this.demandDiscribe = demandDiscribe == null ? null : demandDiscribe.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCountyId() {
        return countyId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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

    public String getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(String demandSideId) {
        this.demandSideId = demandSideId == null ? null : demandSideId.trim();
    }

    public Integer getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }
}