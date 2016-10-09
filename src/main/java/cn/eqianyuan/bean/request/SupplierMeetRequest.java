package cn.eqianyuan.bean.request;

/**
 * 供应商约见请求对象
 * Created by jason on 2016-09-30.
 */
public class SupplierMeetRequest {
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
    private Integer respectfulName;
    //电话号码-区号
    private String phoneAreaCode;
    //电话号码
    private String telephoneNumber;
    //电话号码-分机号
    private String extensionNumber;
    //移动号码
    private String mobileNumber;

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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
