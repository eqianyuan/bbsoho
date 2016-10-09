package cn.eqianyuan.bean.vo;

/**
 * 供应商约见请求对象
 * Created by jason on 2016-09-30.
 */
public class DemandMeetInfoVO {
    //需求编号
    private String demandId;
    //供应商编号
    private String supplierSideId;
    //约见时间
    private String meetTime;
    //约见地址
    private String address;
    //联系人
    private String contact;
    //联系人尊称
    private String respectfulName;
    //电话号码-区号
    private String phoneAreaCode;
    //电话号码
    private Integer telephoneNumber;
    //电话号码-分机号
    private String extensionNumber;
    //移动号码
    private Long mobileNumber;

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getSupplierSideId() {
        return supplierSideId;
    }

    public void setSupplierSideId(String supplierSideId) {
        this.supplierSideId = supplierSideId;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRespectfulName() {
        return respectfulName;
    }

    public void setRespectfulName(String respectfulName) {
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
}
