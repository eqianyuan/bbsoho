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
    //会员（供应商）用户简历信息，擅长方向内容太多了
    public static final String SUPPLIER_USER_RESUME_BY_DISCRIBE_TOO_MANY = "2001026";
    //会员（供应商）简历信息，简历查询失败
    public static final String SUPPLIER_USER_RESUME_BY_QUERY_FAIL = "2001027";

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
    //会员（需求商）需求信息，需求查询失败
    public static final String DEMAND_USER_DEMAND_BY_QUERY_FAIL = "2002030";
    //会员（需求商）需求信息，需求名称为空
    public static final String DEMAND_USER_DEMAND_BY_NAME_IS_EMPTY = "2002031";
    //会员（需求商）需求信息，需求周期开始时间为空
    public static final String DEMAND_USER_DEMAND_BY_BEGIN_CYCLE_IS_EMPTY = "2002032";
    //会员（需求商）需求信息，需求周期结束时间为空
    public static final String DEMAND_USER_DEMAND_BY_END_CYCLE_IS_EMPTY = "2002033";
    //会员（需求商）需求信息，需求周期开始时间格式不正确
    public static final String DEMAND_USER_DEMAND_BY_BEGIN_CYCLE_FORMAT_IS_FAIL = "2002034";
    //会员（需求商）需求信息，需求周期结束时间格式不正确
    public static final String DEMAND_USER_DEMAND_BY_END_CYCLE_FORMAT_IS_FAIL = "2002035";
    //会员（需求商）需求信息，需求联系人为空
    public static final String DEMAND_USER_DEMAND_BY_COMPANY_CONTACT_IS_EMPTY = "2002036";
    //会员（需求商）需求信息，需求联系人尊称为空
    public static final String DEMAND_USER_DEMAND_BY_COMPANY_RESPECTFUL_NAME_IS_EMPTY = "2002037";
    //会员（需求商）需求信息，需求联系电话-移动号码不能为空
    public static final String DEMAND_USER_DEMAND_BY_COMPANY_MOBILE_NUMBER_IS_EMPTY = "2002038";
    //会员（需求商）需求信息，需求联系电话-移动号码不正确
    public static final String DEMAND_USER_DEMAND_BY_COMPANY_MOBILE_NUMBER_IS_FAIL = "2002039";
    //会员（需求商）需求信息，需求名称太长
    public static final String DEMAND_USER_DEMAND_BY_NAME_TO_LONG = "2002040";
    //会员（需求商）需求信息，联系固话-区号不是数字
    public static final String DEMAND_USER_DEMAND_BY_PHONE_AREA_CODE_IS_FAIL = "2002041";
    //会员（需求商）需求信息，联系固话-区号太长
    public static final String DEMAND_USER_DEMAND_BY_PHONE_AREA_CODE_TO_LONG = "2002042";
    //会员（需求商）需求信息，联系固话号码不是数字
    public static final String DEMAND_USER_DEMAND_BY_TELEPHONE_NUMBER_IS_FAIL = "2002043";
    //会员（需求商）需求信息，联系固话-分机号不是数字
    public static final String DEMAND_USER_DEMAND_BY_EXTENSION_NUMBER_IS_FAIL = "2002044";
    //会员（需求商）需求信息，联系固话-分机号太长
    public static final String DEMAND_USER_DEMAND_BY_EXTENSION_NUMBER_TO_LONG = "2002045";
    //会员（需求商）需求信息，详细地址太长
    public static final String DEMAND_USER_DEMAND_BY_ADDRESS_TO_LONG = "2002046";
    //会员（需求商）需求信息，工作内容太长
    public static final String DEMAND_USER_DEMAND_BY_DISCRIBE_TO_LONG = "2002047";
    //会员（需求商）需求信息，需求用人为空
    public static final String DEMAND_USER_DEMAND_BY_EMPLOY_PERSONS_IS_EMPTY = "2002048";

    //需求操作,找不到需求
    public static final String DEMAND_IS_EMPTY = "2003001";
    //需求操作，已报过名
    public static final String DEMAND_HAS_SIGN_UP = "2003002";
    //需求操作，约见失败
    public static final String DEMAND_MEET_FAIL = "2003003";
    //需求操作，约见表单-约见时间为空
    public static final String DEMAND_MEET_TIME_IS_EMPTY = "2003004";
    //需求操作，约见表单-约见地址为空
    public static final String DEMAND_MEET_ADDRESS_IS_EMPTY = "2003005";
    //需求操作，约见表单-约见联系人为空
    public static final String DEMAND_MEET_CONTACT_IS_EMPTY = "2003006";
    //需求操作，约见表单-约见联系人尊称为空
    public static final String DEMAND_MEET_RESPECTFULNAME_IS_EMPTY = "2003007";
    //需求操作，约见表单-约见联系电话-移动号码为空
    public static final String DEMAND_MEET_MOBILE_IS_EMPTY = "2003008";
    //需求操作，约见表单-约见联系电话-固话-区号为空
    public static final String DEMAND_MEET_PHONEAREACODE_IS_EMPTY= "2003009";
    //需求操作，约见表单-约见联系电话-固话-号码为空
    public static final String DEMAND_MEET_TELEPHONENUMBER_IS_EMPTY = "2003010";
    //需求操作，约见表单-约见联系电话-固话-分机号码为空
    public static final String DEMAND_MEET_EXTENSIONNUMBER_IS_EMPTY = "2003011";
    //需求操作，约见表单-约见联系电话-移动号码不正确
    public static final String DEMAND_MEET_BY_MOBILE_NUMBER_IS_FAIL = "2003012";
    //需求操作，约见表单-联系人太长
    public static final String DEMAND_MEET_BY_COMPANY_CONTACT_TO_LONG = "2003013";
    //需求操作，约见表单-约见联系固话-区号不是数字
    public static final String DEMAND_MEET_BY_COMPANY_PHONE_AREA_CODE_IS_FAIL = "2003014";
    //需求操作，约见表单-约见联系固话-区号太长
    public static final String DEMAND_MEET_BY_COMPANY_PHONE_AREA_CODE_TO_LONG = "2003015";
    //需求操作，约见表单-约见联系固话号码不是数字
    public static final String DEMAND_MEET_BY_COMPANY_TELEPHONE_NUMBER_IS_FAIL = "2003016";
    //需求操作，约见表单-约见联系固话-分机号不是数字
    public static final String DEMAND_MEET_BY_COMPANY_EXTENSION_NUMBER_IS_FAIL = "2003017";
    //需求操作，约见表单-约见联系固话-分机号太长
    public static final String DEMAND_MEET_BY_COMPANY_EXTENSION_NUMBER_TO_LONG = "2003018";
    //需求操作，约见表单-约见详细地址太长
    public static final String DEMAND_MEET_BY_ADDRESS_TO_LONG = "2003019";
    //需求操作，约见信息查询失败
    public static final String DEMAND_MEET_BY_QUERY_FAIL = "2003020";
    //需求操作，聘用失败
    public static final String DEMAND_HIRE_FAIL = "2003021";
    //需求操作，聘用表单-合同生效时间为空
    public static final String DEMAND_HIRE_CONTRACT_COMES_INTO_EFFECT_TIME_IS_EMPTY = "2003022";
    //需求操作，聘用表单-合同失效时间为空
    public static final String DEMAND_MEET_CONTRACT_EXPIRES_TIME_IS_EMPTY = "2003023";
    //需求操作，聘用表单-薪资报酬为空
    public static final String DEMAND_MEET_REMUNERATION_IS_EMPTY = "2003024";
    //需求操作，聘用表单-薪资报酬不是正确金额
    public static final String DEMAND_MEET_REMUNERATION_IS_NOT_MONEY = "2003025";
    //需求操作，聘用信息查询失败
    public static final String DEMAND_HIRE_BY_QUERY_FAIL = "2003026";
    //需求操作，关闭报名通道失败
    public static final String DEMAND_SIGN_UP_BY_WORK_CLOSE_FAIL = "2003027";
/********** 客户端系统异常配置 **********/
}
