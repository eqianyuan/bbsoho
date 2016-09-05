package cn.eqianyuan.core.exception;

/**
 * 自定义异常错误消息静态常量类
 * Created by jason on 2016-05-19.
 */
public class ExceptionMsgConstant {

    /**********
     * 系统级异常配置
     **********/
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
    //注册激活邮件发送失败
    public static final String REGISTRATION_ACTIVATION_EMAIL_SEND_ERROR = "1001009";
    //获取字符串字节错误
    public static final String SYSTEM_GET_BYTE_FAIL = "1001010";
    //附件不存在
    public static final String FILE_NO_EXISTS = "1001011";
    //附件上传失败
    public static final String FILE_UPDATE_ERROR = "1001012";
/********** 系统级异常配置 **********/

    /**********
     * 客户端系统异常配置
     **********/
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
    //会员（供应商）用户基本信息，真实姓名为空
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_REAL_NAME_IS_EMPTY = "2001007";
    //会员（供应商）用户基本信息，性别为空
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_SEX_IS_EMPTY = "2001008";
    //会员（供应商）用户基本信息，真实姓名太长
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_REAL_NAME_TO_LONG = "2001009";
    //会员（供应商）用户基本信息，昵称为空
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_NICK_NAME_IS_EMPTY = "2001010";
    //会员（供应商）用户基本信息，生日不正确
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL = "2001011";
    //会员（供应商）用户基本信息，邮箱为空
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_EMAIL_IS_EMPTY = "2001012";
    //会员（供应商）用户基本信息，邮箱不正确
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_EMAIL_IS_FAIL = "2001013";
    //会员（供应商）用户基本信息，工作年限为空
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_WORKING_YEARS_IS_EMPTY = "2001014";
    //会员（供应商）用户基本信息，学历不正确
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_SCHOOLING_IS_FAIL = "2001015";
    //会员（供应商）用户基本信息，昵称太长
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_NICK_NAME_TO_LONG = "2001016";
    //会员（供应商）用户基本信息，邮箱太长
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_EMAIL_TO_LONG = "2001017";
    //会员（供应商）用户基本信息，学校名称太长
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_SCHOOL_NAME_TO_LONG = "2001018";
    //会员（供应商）用户基本信息，专业太长
    public static final String SUPPLIER_USER_BASIC_INFORMATION_BY_PROFESSIONAL_NAME_TO_LONG = "2001019";
    //会员（供应商）用户简历信息，行业为空
    public static final String SUPPLIER_USER_RESUME_BY_INDUSTRY_IS_EMPTY = "2001020";
    //会员（供应商）用户简历信息，行业工种类别为空
    public static final String SUPPLIER_USER_RESUME_BY_WORK_TYPE_IS_EMPTY = "2001021";
    //会员（供应商）用户简历信息，行业工种为空
    public static final String SUPPLIER_USER_RESUME_BY_WORK_IS_FAIL = "2001022";
    //会员（供应商）用户简历信息，工作时间为空
    public static final String SUPPLIER_USER_RESUME_BY_EXPECT_WORK_TIME_IS_EMPTY = "2001023";
    //会员（供应商）用户简历信息，期望薪资为空
    public static final String SUPPLIER_USER_RESUME_BY_EXPECT_PAY_IS_EMPTY = "2001024";
    //会员（供应商）用户简历信息，行业工种选择太多项了
    public static final String SUPPLIER_USER_RESUME_BY_WORK_CHOOSE_TOO_MANY = "2001025";

    //会员（需求商）用户注册，邮箱为空
    public static final String DEMAND_USER_REGISTER_BY_EMAIL_IS_EMPTY = "2002001";
    //会员（需求商）用户注册，邮箱不正确
    public static final String DEMAND_USER_REGISTER_BY_EMAIL_IS_FAIL = "2002002";
    //会员（需求商）用户注册，登录密码为空
    public static final String DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_IS_EMPTY = "2002003";
    //会员（需求商）用户注册，确认密码为空
    public static final String DEMAND_USER_REGISTER_BY_LOGIN_CONFIRM_PASSWORD_IS_EMPTY = "2002004";
    //会员（需求商）用户注册，两次密码不一致
    public static final String DEMAND_USER_REGISTER_BY_LOGIN_TWO_PASSWORD_NOT_CONSISTENT = "2002005";
    //会员（需求商）用户注册，登录密码包含无效字符
    public static final String DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_CONTAINS_INVALID_CHARACTERS = "2002006";
    //会员（需求商）用户注册，登录密码复杂度不够
    public static final String DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_COMPLEXITY_IS_NOT_ENOUGH = "2002007";
    //会员（需求商）用户注册，邮箱号码已经注册
    public static final String DEMAND_USER_REGISTER_BY_EMAIL_IS_ALREADY_REGISTER = "2002008";
    //会员（需求商）账号激活，激活失败
    public static final String DEMAND_USER_ACCOUNT_ACTIVATION_FAIL = "2002009";
    //会员（需求商）账号不存在
    public static final String DEMAND_USER_ACCOUNT_IS_NOT_EXISTS = "2002010";
    //会员（需求商）用户登录，账号或密码错误
    public static final String DEMAND_USER_LOGIN_BY_ACCOUNT_ERROR = "2001006";
    //会员（需求商）用户基本信息，企业名称为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_NAME_IS_EMPTY = "2002011";
    //会员（需求商）用户基本信息，企业性质为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_ENTERPRISE_NATURE_IS_EMPTY = "2002012";
    //会员（需求商）用户基本信息，企业规模为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_ENTERPRISE_SCALE_IS_EMPTY = "2002013";
    //会员（需求商）用户基本信息，企业名称太长
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_NAME_TO_LONG = "2002014";
    //会员（需求商）用户基本信息，企业联系人为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_CONTACT_IS_EMPTY = "2002015";
    //会员（需求商）用户基本信息，企业联系人尊称为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_RESPECTFUL_NAME_IS_EMPTY = "2002016";
    //会员（需求商）用户基本信息，企业联系电话-移动号码不能为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_MOBILE_NUMBER_IS_EMPTY = "2002017";
    //会员（需求商）用户基本信息，企业联系电话-移动号码不正确
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_MOBILE_NUMBER_IS_FAIL = "2002018";
    //会员（需求商）用户基本信息，企业地区-省不能为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_PROVINCE_IS_FAIL = "2002019";
    //会员（需求商）用户基本信息，企业地区-市不能为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_CITY_IS_FAIL = "2002020";
    //会员（需求商）用户基本信息，企业地区-区不能为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_COUNTY_IS_FAIL = "2002021";
    //会员（需求商）用户基本信息，企业详细地址能为空
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_ADDRESS_IS_FAIL = "2002022";
    //会员（需求商）用户基本信息，企业联系人太长
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_CONTACT_TO_LONG = "2002023";
    //会员（需求商）用户基本信息，企业联系固话-区号不是数字
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_PHONE_AREA_CODE_IS_FAIL = "2002024";
    //会员（需求商）用户基本信息，企业联系固话-区号太长
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_PHONE_AREA_CODE_TO_LONG = "2002025";
    //会员（需求商）用户基本信息，企业联系固话号码不是数字
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_TELEPHONE_NUMBER_IS_FAIL = "2002026";
    //会员（需求商）用户基本信息，企业联系固话-分机号不是数字
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_EXTENSION_NUMBER_IS_FAIL = "2002027";
    //会员（需求商）用户基本信息，企业联系固话-分机号太长
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_EXTENSION_NUMBER_TO_LONG = "2002028";
    //会员（需求商）用户基本信息，企业详细地址太长
    public static final String DEMAND_USER_BASIC_INFORMATION_BY_ADDRESS_TO_LONG = "2002029";
/********** 客户端系统异常配置 **********/
}
