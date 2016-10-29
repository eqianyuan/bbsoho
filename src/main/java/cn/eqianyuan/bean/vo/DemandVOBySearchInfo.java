package cn.eqianyuan.bean.vo;

import cn.eqianyuan.bean.po.DemandEmployPersonsPO;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by jason on 2016-09-14.
 */
public class DemandVOBySearchInfo {

    private String id;

    private String name;

    private String workingLunch;

    private String contact;

    private String respectfulName;

    private String phoneAreaCode;

    private Integer telephoneNumber;

    private String extensionNumber;

    private Long mobileNumber;

    private String demandDiscribe;

    private String province;

    private String city;

    private String county;

    private String address;

    private String beginCycle;

    private String endCycle;

    private String demandSideId;

    private String publishTime;

    private List<DemandEmployPersons> demandEmployPersonsList;

    public class DemandEmployPersons{

        private Integer id;

        private String industry;

        private String workType;

        private String work;

        private String workText;

        private String personsAmount;

        private String workingYears;

        private String remuneration;

        private Integer channelWhetherClose;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getPersonsAmount() {
            return personsAmount;
        }

        public void setPersonsAmount(String personsAmount) {
            this.personsAmount = personsAmount;
        }

        public String getWorkingYears() {
            return workingYears;
        }

        public void setWorkingYears(String workingYears) {
            this.workingYears = workingYears;
        }

        public String getRemuneration() {
            return remuneration;
        }

        public void setRemuneration(String remuneration) {
            this.remuneration = remuneration;
        }

        public Integer getChannelWhetherClose() {
            return channelWhetherClose;
        }

        public void setChannelWhetherClose(Integer channelWhetherClose) {
            this.channelWhetherClose = channelWhetherClose;
        }
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

    public String getWorkingLunch() {
        return workingLunch;
    }

    public void setWorkingLunch(String workingLunch) {
        this.workingLunch = workingLunch;
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

    public String getDemandDiscribe() {
        return demandDiscribe;
    }

    public void setDemandDiscribe(String demandDiscribe) {
        this.demandDiscribe = demandDiscribe;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDemandSideId() {
        return demandSideId;
    }

    public void setDemandSideId(String demandSideId) {
        this.demandSideId = demandSideId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public List<DemandEmployPersons> getDemandEmployPersonsList() {
        return demandEmployPersonsList;
    }

    public void setDemandEmployPersonsList(List<DemandEmployPersons> demandEmployPersonsList) {
        this.demandEmployPersonsList = demandEmployPersonsList;
    }
}
