package cn.eqianyuan.bean.vo;

/**
 * 需求商账户基本信息
 */
public class DemandSideVOByBasicInfo {
    private String id;

    private String companyName;

    private String provinceId;

    private String cityId;

    private String countyId;

    private String address;

    private String contact;

    private Integer respectfulName;

    private String phoneAreaCode;

    private Integer telephoneNumber;

    private String extensionNumber;

    private Long mobileNumber;

    private String logo;

    private Integer enterpriseNature;

    private Integer enterpriseScale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
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
        this.phoneAreaCode = phoneAreaCode;
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
        this.extensionNumber = extensionNumber;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Integer getEnterpriseNature() {
        return enterpriseNature;
    }

    public void setEnterpriseNature(Integer enterpriseNature) {
        this.enterpriseNature = enterpriseNature;
    }

    public Integer getEnterpriseScale() {
        return enterpriseScale;
    }

    public void setEnterpriseScale(Integer enterpriseScale) {
        this.enterpriseScale = enterpriseScale;
    }
}