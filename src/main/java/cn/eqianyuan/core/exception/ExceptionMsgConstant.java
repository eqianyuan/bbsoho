package cn.eqianyuan.core.exception;

/**
 * 自定义异常错误消息静态常量类
 * Created by jason on 2016-05-19.
 */
public class ExceptionMsgConstant {

/********** 系统级异常配置 **********/
    //系统运行时错误
    public static final String SYSTEM_RUNTIME_EXCEPTION = "1001001";
    //验证码是空
    public static final String VALIDATA_CODE_IS_EMPTY = "1001002";
    //验证码内容超长
    public static final String VALIDATA_CODE_CONTENT_LENGTH_TO0_LONG = "1001003";
    //验证码校验错误
    public static final String VALIDATA_CODE_VALIDATION_ERROR = "1001004";
    //验证码发送时间间隔小于60s
    public static final String VALIDATA_CODE_INTERVAL_INSUFFICIENT = "1001005";
    //短信验证码发送失败
    public static final String VALIDATA_CODE_SEND_ERROR = "1001006";
    //获取配置文件信息错误
    public static final String GET_CONFIGURATION_ERROR = "1001007";
    //短信验证码为空
    public static final String VALIDATA_CODE_BY_SMS_IS_EMPTY = "1001008";
/********** 系统级异常配置 **********/

/********** 客户端系统异常配置 **********/
    //会员（供应商）用户注册，手机号码不正确
    public static final String SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL = "2001001";
    //会员（供应商）用户注册，登录密码为空
    public static final String SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_IS_EMPTY = "2001002";
    //会员（供应商）用户注册，登录密码包含无效字符
    public static final String SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_CONTAINS_INVALID_CHARACTERS = "2001003";
    //会员（供应商）用户注册，登录密码复杂度不够
    public static final String SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_COMPLEXITY_IS_NOT_ENOUGH = "2001004";
    //会员（供应商）用户注册，手机号码已经注册
    public static final String SUPPLIER_USER_REGISTER_BY_MOBILE_IS_ALREADY_REGISTER = "2001005";
    //会员（供应商）用户登录，账号或密码错误
    public static final String SUPPLIER_USER_LOGIN_BY_ACCOUNT_ERROR = "2001006";
/********** 客户端系统异常配置 **********/
}
