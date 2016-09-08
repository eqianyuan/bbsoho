package cn.eqianyuan.util.yamlMapper;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 系统静态配置信息枚举类
 * <p>
 * Created by jason on 2016-06-02.
 */
public enum SystemConf {

    /**
     * 系统维护的线程池
     * 计算公式：单个CPU处理的线程数 * 设备可用有效CPU个数
     */
    POOL_BY_THREAD_SIZE(Executors.newFixedThreadPool(10 * Runtime.getRuntime().availableProcessors())),
    /**
     * 平台字符集
     */
    PLATFORM_CHARSET("UTF-8"),
    /**
     * 会员注册页面验证码session key
     */
    USER_REGISTER_VERIFY_CODE_BY_PAGE("userRegisterVerifyCodeByPage"),
    /**
     * 会员注册短信验证码session key
     */
    USER_REGISTER_VERIFY_CODE_BY_SMS("userRegisterVerifyCodeBySMS"),
    /**
     * 会员注册手机号码短信验证码session key
     */
    SEND_SMS_VERIFICATION_CODE_BY_REGISTER("sendSMSVerificationCodeByRegister"),
    /**
     * 供应商会员用户登录对象 session key
     */
    SUPPLIER_USER_BY_LOGIN("supplierUserByLogin"),
    /**
     * 需求商会员用户登录对象 session key
     */
    DEMAND_USER_BY_LOGIN("demandUserByLogin"),
    /**
     * 会员用户登录页面位置
     */
    USER_LOGIN_PAGE("/login.jsp"),
    /**
     * 需求商会员用户账号未激活提醒页面位置
     */
    DEMAND_USER_UN_ACTIVATION_PAGE("/demandSide/unActivation.jsp"),
    /**
     * 需求商会员用户账号激活成功提醒页面位置
     */
    DEMAND_USER_ACTIVATION_SUCCESS_PAGE("/login"),
    /**
     * 需求商会员用户账号基本信息未完善提醒页面位置
     */
    DEMAND_USER_UN_INTEGRITY_PAGE("/demandSide/basicInformation.jsp?tabWidget=basicInformation"),
    /**
     * 供应商会员用户账号基本信息未完善提醒页面位置
     */
    SUPPLIER_USER_UN_INTEGRITY_PAGE("/supplierSide/basicInformation.jsp?tabWidget=basicInformation"),
    /**
     * 需求商会员LOGO图片存放路径
     */
    DEMAND_USER_LOGO_FILE_UPLOAD_PATH(File.separator + "images" + File.separator + "demand" + File.separator + "logo"),
    /**
     * 供应商会员headPortrait图片存放路径
     */
    SUPPLIER_USER_HEAD_PORTRAIT_FILE_UPLOAD_PATH(File.separator + "images" + File.separator + "supplier" + File.separator + "head protrait");

    private Object value;

    SystemConf(Object value) {
        this.value = value;
    }

    /**
     * 获取系统维护的线程池对象
     *
     * @return
     */
    public static ExecutorService getPoolByThreadSize() {
        return (ExecutorService) SystemConf.POOL_BY_THREAD_SIZE.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
