package cn.eqianyuan.bean.vo;

public class SupplierSideVOByLogin {
    //昵称
    private String nickName;

    //手机号码
    private Long mobileNumber;

    //头像
    private String headPortrait;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }
}