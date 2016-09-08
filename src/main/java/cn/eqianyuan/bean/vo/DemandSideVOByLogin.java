package cn.eqianyuan.bean.vo;

public class DemandSideVOByLogin {
    private String id;

    private String companyName;

    private String logo;

    private String email;

    private Integer activationStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(Integer activationStatus) {
        this.activationStatus = activationStatus;
    }
}