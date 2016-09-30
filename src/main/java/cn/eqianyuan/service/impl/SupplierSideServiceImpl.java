package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.dto.SupplierByListSearchDTO;
import cn.eqianyuan.bean.dto.SupplierSideBasicInfoDTO;
import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.po.*;
import cn.eqianyuan.bean.request.SupplierSearchListByRequest;
import cn.eqianyuan.bean.vo.SupplierSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.bean.vo.SupplierSideVOByResume;
import cn.eqianyuan.controller.convert.SupplierSideConvert;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.*;
import cn.eqianyuan.listener.InitialData;
import cn.eqianyuan.service.ISupplierSideService;
import cn.eqianyuan.service.convert.ServiceSupplierSideConvert;
import cn.eqianyuan.util.*;
import cn.eqianyuan.util.yamlMapper.ClientConf;
import cn.eqianyuan.util.yamlMapper.DataDictionaryConf;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jason on 2016-08-10.
 */
@Service
public class SupplierSideServiceImpl implements ISupplierSideService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ISupplierSideDao supplierSideDao;

    @Autowired
    private IResumeDao resumeDao;

    @Autowired
    private IWorkProficiencyDao workProficiencyDao;

    @Autowired
    private IWorkExperienceDao workExperienceDao;

    @Autowired
    private IProjectExperienceDao projectExperienceDao;

    @Autowired
    private SupplierSideConvert supplierConvert;

    @Autowired
    private ServiceSupplierSideConvert serviceSupplierConvert;

    @Autowired
    private ISignUpDao signUpDao;

    @Autowired
    private ISignUpMeetDao signUpMeetDao;

    @Autowired
    private IHireDao hireDao;

    //真实姓名DB许可字节长度
    private static final int REAL_NAME_MAX_BYTES_BY_DB = 20;
    //昵称DB许可字节长度
    private static final int NICK_NAME_MAX_BYTES_BY_DB = 30;
    //邮箱DB许可字节长度
    private static final int EMAIL_MAX_BYTES_BY_DB = 300;
    //学校名称DB许可字节长度
    private static final int SCHOOL_NAME_MAX_BYTES_BY_DB = 100;
    //专业名称DB许可字节长度
    private static final int PROFESSIONAL_NAME_MAX_BYTES_BY_DB = 100;
    //擅长方向DB许可字节长度
    private static final int DISCRIBE_MAX_BYTES_BY_DB = 3000;

    /**
     * 添加供应商用户信息
     *
     * @param supplierSideBasicInfoDTO 数据对象
     * @param verifyCodeBySMS          短信验证码
     * @throws EqianyuanException
     */
    public void add(SupplierSideBasicInfoDTO supplierSideBasicInfoDTO, String verifyCodeBySMS) throws EqianyuanException {

        //检查供应商用户输入手机号码是否正确
        if (!RegexUtils.isMobile(String.valueOf(supplierSideBasicInfoDTO.getMobileNumber()))) {
            logger.warn("supplier user add fail , because user input mobile number is not right mobile number");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        //检查供应商用户输入登录密码是否为空
        if (StringUtils.isEmpty(supplierSideBasicInfoDTO.getLoginPassword())) {
            logger.warn("supplier user add fail , because user input login password , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_IS_EMPTY);
        }

        //检查供应商用户输入短信验证码是否为空
        if (StringUtils.isEmpty(verifyCodeBySMS)) {
            logger.warn("supplier user add fail , because user input verifyCodeBySMS , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_BY_SMS_IS_EMPTY);
        }

        //从session中获取短信验证码数据
        String verifyCodeBySession = (String) SessionUtil.getAttribute(SystemConf.USER_REGISTER_VERIFY_CODE_BY_SMS.toString());
        //检查session是否存在验证码数据
        if (StringUtils.isEmpty(verifyCodeBySession)) {
            logger.warn("supplier user add fail, because there is no verification code in the session attribute.");
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_VALIDATION_ERROR);
        }

        //比较用户提交验证码与session中验证码是否一致
        if (!StringUtils.equalsIgnoreCase(verifyCodeBySMS, verifyCodeBySession)) {
            logger.warn("supplier user add fail, because session attribute verification code and request param code not consistent.");
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_VALIDATION_ERROR);
        }

        //检查供应商用户输入登录密码是否含有空格
        if (RegexUtils.hasBlankCharacters(supplierSideBasicInfoDTO.getLoginPassword())) {
            logger.warn("supplier user add fail , because user input login password , value contains invalid characters");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_CONTAINS_INVALID_CHARACTERS);
        }

        //检查供应商用户输入密码是否在长度范围中
        if (supplierSideBasicInfoDTO.getLoginPassword().length() < 6 || supplierSideBasicInfoDTO.getLoginPassword().length() > 20) {
            logger.warn("supplier user add fail , because user input login password , value length is beyond the scope of [6 ~ 20]");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_COMPLEXITY_IS_NOT_ENOUGH);
        }

        /**
         * 检查供应商用户输入密码复杂度
         */
        {
            //声明复杂性
            int complexity = 0;
            //是否含有数字
            if (RegexUtils.hasDigital(supplierSideBasicInfoDTO.getLoginPassword())) {
                complexity++;
            }
            //是否含有字母及下划线
            if (RegexUtils.hasAlphabet(supplierSideBasicInfoDTO.getLoginPassword())) {
                complexity++;
            }
            //是否含有特殊字符
            if (RegexUtils.hasSpecialCharacters(supplierSideBasicInfoDTO.getLoginPassword())) {
                complexity++;
            }

            //复杂度最低2
            if (complexity < 2) {
                logger.warn("supplier user add fail , because user input login password , value complexity is not enough");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_COMPLEXITY_IS_NOT_ENOUGH);
            }
        }

        //检查手机号是否已经注册过
        int memberCountByMobile = supplierSideDao.selectCountByMobile(supplierSideBasicInfoDTO.getMobileNumber());
        //当结果数量大于0，说明已经被注册
        if (memberCountByMobile > 0) {
            logger.warn("supplier user add fail , because mobile is already register.");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_ALREADY_REGISTER);
        }

        //密码MD5加密处理
        String encryptionPwd = Md5Util.MD5By32(StringUtils.lowerCase(supplierSideBasicInfoDTO.getLoginPassword()));
        //获取当前系统月份英文描述
        String monthEN = CalendarUtil.getCurrentMonthEN();
        //截取月份英文描述首字母
        String firstCharacter = StringUtils.substring(monthEN, 0, 1);
        //将首字母转为大写
        String firstCharacterUpper = StringUtils.upperCase(firstCharacter);
        //获取当前系统秒数
        int systemSeconds = CalendarUtil.getSystemSeconds();

        //创建PO对象并设置内容
        SupplierSidePO supplierSidePO = new SupplierSidePO();
        supplierSidePO.setMobileNumber(supplierSideBasicInfoDTO.getMobileNumber());
        supplierSidePO.setLoginPassword(encryptionPwd);
        //注册期，给与默认用户名，用户名生成规则：当月月份英文大写首字母+当前系统秒数时间
        supplierSidePO.setNickName(firstCharacterUpper + systemSeconds);
        supplierSidePO.setCreateTime(systemSeconds);
        //持久化PO对象
        supplierSideDao.insertSelective(supplierSidePO);
        //移除短信验证码
        SessionUtil.removeAttribute(SystemConf.USER_REGISTER_VERIFY_CODE_BY_SMS.toString());
    }

    /**
     * 从session中获取短信发送集合
     *
     * @return
     */
    private Map<String, Map<String, String>> getSendSMSVerificationCodeByRegister() throws EqianyuanException {
        Object sendSMSByBatchSend2 = SessionUtil.getAttribute(SystemConf.SEND_SMS_VERIFICATION_CODE_BY_REGISTER.toString());
        if (ObjectUtils.isEmpty(sendSMSByBatchSend2)) {
            return new HashMap<String, Map<String, String>>();
        }

        return (Map<String, Map<String, String>>) sendSMSByBatchSend2;
    }

    /**
     * 供应商会员注册，发送手机短信验证码
     *
     * @param mobile           手机号码
     * @param verifyCodeLength 验证码内容长度
     */
    public void sendSMSVerificationCodeByRegister(String mobile, String verifyCodeByPage, Integer verifyCodeLength) throws EqianyuanException {

        //检查供应商用户输入手机号码是否为空
        if (StringUtils.isEmpty(mobile)) {
            logger.warn("supplier sendSMSVerificationCode fail  because mobile value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        //检查供应商用户输入手机号码是否正确
        if (!RegexUtils.isMobile(mobile)) {
            logger.warn("supplier sendSMSVerificationCode fail , because user input mobile number is not right mobile number");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        //检查供应商用户输入验证码是否为空
        if (StringUtils.isEmpty(verifyCodeByPage)) {
            logger.warn("supplier sendSMSVerificationCode fail  because verifyCodeByPage value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_IS_EMPTY);
        }

        //从session中获取页面验证码数据
        String verifyCodeBySession = (String) SessionUtil.getAttribute(SystemConf.USER_REGISTER_VERIFY_CODE_BY_PAGE.toString());
        //检查session是否存在验证码数据
        if (StringUtils.isEmpty(verifyCodeBySession)) {
            logger.warn("supplier sendSMSVerificationCode fail, because there is no verification code in the session attribute.");
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_VALIDATION_ERROR);
        }

        //比较用户提交验证码与session中验证码是否一致
        if (!StringUtils.equalsIgnoreCase(verifyCodeByPage, verifyCodeBySession)) {
            logger.warn("supplier sendSMSVerificationCode fail, because session attribute verification code and request param code not consistent.");
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_VALIDATION_ERROR);
        }

        //检查手机号是否已经注册过
        int memberCountByMobile = supplierSideDao.selectCountByMobile(Long.parseLong(mobile));
        //当结果数量大于0，说明已经被注册
        if (memberCountByMobile > 0) {
            logger.warn("supplier user add fail , because mobile is already register.");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_ALREADY_REGISTER);
        }

        synchronized (this) {
            //从session中获取最后发送成功短信时间及手机号码
            Map<String, Map<String, String>> sendMap = getSendSMSVerificationCodeByRegister();

            //判断是否已经存在成功发送请求session
            if (!CollectionUtils.isEmpty(sendMap)) {
                for (String mobileKey : sendMap.keySet()) {
                    //判断当前手机号码是否已经在session中有记录
                    if (!StringUtils.equals(mobileKey, mobile)) {
                        continue;
                    }

                    //检查session中记录时间是否超过60S
                    if (CalendarUtil.getSystemSeconds() - Integer.parseInt(sendMap.get(mobileKey).get("send_time")) < 60) {
                        logger.warn("supplier sendSMSVerificationCodeByRegister fail , because mobile [" + mobile + "] send interval less than 60s");
                        throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_INTERVAL_INSUFFICIENT);
                    }

                    break;
                }
            }

            //获取消息内容模板
            String message = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.SMS.SMS.toString(), ClientConf.SMS.BatchSend2_message.toString());
            //检查消息模板内容是否为空
            if (StringUtils.isEmpty(message)) {
                logger.warn("supplier sendSMSVerificationCodeByRegister fail ,because BatchSend2_message not exists the client-conf.yaml");
                throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
            }

            //获取数字验证码
            String verifyCode = VerifyCodeUtils.random(verifyCodeLength, VerifyCodeUtils.SEEDS_BY_NUMBER);

            //替换消息模板内容
            message = StringTemplateReplaceUtil.getStr(message, "\\?", verifyCode);

            //将验证码内容写入session
            SessionUtil.setAttribute(SystemConf.USER_REGISTER_VERIFY_CODE_BY_SMS.toString(), verifyCode);
            //发送短信
            SMSUtils.batchSend2(mobile, message);

            //当前手机及发送情况
            Map<String, String> sendInfo = new HashMap<String, String>();
            sendInfo.put("mobile", mobile);
            sendInfo.put("send_time", String.valueOf(CalendarUtil.getSystemSeconds()));
            sendMap.put(mobile, sendInfo);

            //发送短信成功，保存发送时间
            SessionUtil.setAttribute(SystemConf.SEND_SMS_VERIFICATION_CODE_BY_REGISTER.toString(), sendMap);
            //从session中移除页面验证码数据
            SessionUtil.removeAttribute(SystemConf.USER_REGISTER_VERIFY_CODE_BY_PAGE.toString());
        }
    }

    /**
     * 供应商会员登录
     *
     * @param mobileNumber  手机号码
     * @param loginPassword 登录密码
     * @throws EqianyuanException
     */
    public SupplierSideVOByLogin supplierLogin(String mobileNumber, String loginPassword) throws EqianyuanException {
        //检查供应商用户输入手机号码是否为空
        if (StringUtils.isEmpty(mobileNumber)) {
            logger.warn("supplier supplierLogin fail  because mobile value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        //检查供应商用户输入手机号码是否正确
        if (!RegexUtils.isMobile(mobileNumber)) {
            logger.warn("supplier supplierLogin fail , because user input mobile number is not right mobile number");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        //检查供应商用户输入登录密码是否为空
        if (StringUtils.isEmpty(loginPassword)) {
            logger.warn("supplier supplierLogin fail , because user input login password , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_LOGIN_PASSWORD_IS_EMPTY);
        }

        //密码MD5加密处理
        String encryptionPwd = Md5Util.MD5By32(StringUtils.lowerCase(loginPassword));
        //根据手机号码及加密密码查询供应商用户信息
        SupplierSidePO supplierSidePO = supplierSideDao.selectByLogin(mobileNumber, encryptionPwd);
        if (ObjectUtils.isEmpty(supplierSidePO) ||
                ObjectUtils.isEmpty(supplierSidePO.getId())) {
            logger.warn("supplier supplierLogin fail , mobileNumber [" + mobileNumber +
                    "] , loginPassword [" + loginPassword + "] , encryptionPwd [" + encryptionPwd + "]");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_LOGIN_BY_ACCOUNT_ERROR);
        }

        //将PO转为VO
        SupplierSideVOByLogin supplierSideVOByLogin = supplierConvert.supplierLogin(supplierSidePO);
        //将登录VO写入session
        SessionUtil.setAttribute(SystemConf.SUPPLIER_USER_BY_LOGIN.toString(), supplierSideVOByLogin);
        return supplierSideVOByLogin;
    }

    /**
     * 供应商用户登出
     */
    public void supplierLogout() {
        SessionUtil.removeAttribute(SystemConf.SUPPLIER_USER_BY_LOGIN.toString());
    }

    /**
     * 获取供应商基本信息
     *
     * @return
     * @throws EqianyuanException
     */
    public SupplierSideVOByBasicInfo getBasicInformation() throws EqianyuanException {
        //获取session用户
        SupplierSideVOByLogin supplierSideVOByLogin = UserUtils.getSupplierSideUserBySession();

        //获取用户手机号码，并且根据手机号码获取供应商基本信息
        SupplierSidePO supplierSidePO = supplierSideDao.selectByMobile(String.valueOf(supplierSideVOByLogin.getMobileNumber()));
        //将PO转为VO
        SupplierSideVOByBasicInfo supplierSideVOByBasicInfo = supplierConvert.getBasicInformation(supplierSidePO);
        return supplierSideVOByBasicInfo;
    }

    /**
     * 供应商基本信息编辑
     *
     * @param supplierSideBasicInfoDTO
     * @throws EqianyuanException
     */
    public void modifyBasicInformation(SupplierSideBasicInfoDTO supplierSideBasicInfoDTO) throws EqianyuanException {
        //检查供应商用户输入真实姓名是否为空
        if (StringUtils.isEmpty(supplierSideBasicInfoDTO.getRealName())) {
            logger.warn("modifyBasicInformation fail , because user input real name , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_REAL_NAME_IS_EMPTY);
        }

        //检查供应商用户输入昵称是否为空
        if (StringUtils.isEmpty(supplierSideBasicInfoDTO.getNickName())) {
            logger.warn("modifyBasicInformation fail , because user input nick name , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_NICK_NAME_IS_EMPTY);
        }

        //检查供应商用户输入性别是否为空
        if (ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getSex())) {
            logger.warn("modifyBasicInformation fail , because user input sex , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_SEX_IS_EMPTY);
        }

        //检查供应商用户生日
        {
            //检查供应商用户输入生日-年是否为空
            if (ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getBirthdayYear())) {
                logger.warn("modifyBasicInformation fail , because user input birthday year , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL);
            }

            //检查供应商用户输入生日-月是否为空
            if (ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getBirthdayMonth())) {
                logger.warn("modifyBasicInformation fail , because user input month , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL);
            }

            //检查供应商用户输入生日-日是否为空
            if (ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getBirthdayDay())) {
                logger.warn("modifyBasicInformation fail , because user input day , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL);
            }

            //检查供应商用户输入生日-年是否为数字
            if (!RegexUtils.isDigital(String.valueOf(supplierSideBasicInfoDTO.getBirthdayYear()))) {
                logger.warn("modifyBasicInformation fail , because user input birthday year , value is not number");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL);
            }

            //检查供应商用户输入生日-月是否为数字
            if (!RegexUtils.isDigital(String.valueOf(supplierSideBasicInfoDTO.getBirthdayMonth()))) {
                logger.warn("modifyBasicInformation fail , because user input birthday month , value is not number");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL);
            }

            //检查供应商用户输入生日-日是否为数字
            if (!RegexUtils.isDigital(String.valueOf(supplierSideBasicInfoDTO.getBirthdayDay()))) {
                logger.warn("modifyBasicInformation fail , because user input birthday day , value is not number");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_BIRTHDAY_IS_FAIL);
            }
        }

        //检查供应商用户输入邮箱是否为空
        if (StringUtils.isEmpty(supplierSideBasicInfoDTO.getEmail())) {
            logger.warn("modifyBasicInformation fail , because user input email , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_EMAIL_IS_EMPTY);
        }

        //检查供应商用户输入邮箱是否为邮箱
        if (!RegexUtils.isEmail(supplierSideBasicInfoDTO.getEmail())) {
            logger.warn("modifyBasicInformation fail , because user input email , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_EMAIL_IS_FAIL);
        }

        //检查供应商用户输入学历是否为空
        if (ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getHighestSchooling())) {
            logger.warn("modifyBasicInformation fail , because user input highest schooling , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_SCHOOLING_IS_FAIL);
        }

        //检查供应商用户输入工作年限是否为空
        if (ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getWorkingYears())) {
            logger.warn("modifyBasicInformation fail , because user input working years , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_WORKING_YEARS_IS_EMPTY);
        }

        //检查真实姓名内容长度是否超出DB许可长度
        try {
            if (supplierSideBasicInfoDTO.getRealName().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > REAL_NAME_MAX_BYTES_BY_DB) {
                logger.info("modifyBasicInformation fail , because real name [" + supplierSideBasicInfoDTO.getRealName() + "] bytes greater than"
                        + REAL_NAME_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_REAL_NAME_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("modifyBasicInformation fail , because real name [" + supplierSideBasicInfoDTO.getRealName() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        //检查昵称内容长度是否超出DB许可长度
        try {
            if (supplierSideBasicInfoDTO.getNickName().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > NICK_NAME_MAX_BYTES_BY_DB) {
                logger.info("modifyBasicInformation fail , because nick name [" + supplierSideBasicInfoDTO.getNickName() + "] bytes greater than"
                        + NICK_NAME_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_NICK_NAME_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("modifyBasicInformation fail , because nick name [" + supplierSideBasicInfoDTO.getNickName() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        List<DataDictionaryPO> dataDictionaryPOs;

        /**
         * 检查性别正确性
         */
        {
            //从数据字典缓存中获取性别集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.SEX.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.SEX.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //性别是否存在字典数据中
            boolean sexInDictionary = false;

            //检查性别是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideBasicInfoDTO.getSex()))) {
                    sexInDictionary = true;
                    break;
                }
            }

            //当企业性质值不存在字典数据中时，抛出错误信息
            if (!sexInDictionary) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.SEX.toString() + "] " +
                        " [" + supplierSideBasicInfoDTO.getSex() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        //检查邮箱内容长度是否超出DB许可长度
        try {
            if (supplierSideBasicInfoDTO.getEmail().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > EMAIL_MAX_BYTES_BY_DB) {
                logger.info("modifyBasicInformation fail , because email [" + supplierSideBasicInfoDTO.getEmail() + "] bytes greater than"
                        + EMAIL_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_EMAIL_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("modifyBasicInformation fail , because email [" + supplierSideBasicInfoDTO.getEmail() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        /**
         * 检查学历正确性
         */
        {
            //从数据字典缓存中获取学历集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.SCHOOLING.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.SCHOOLING.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //学历是否存在字典数据中
            boolean schoolingInDictionary = false;

            //检查学历是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideBasicInfoDTO.getHighestSchooling()))) {
                    schoolingInDictionary = true;
                    break;
                }
            }

            //当学历值不存在字典数据中时，抛出错误信息
            if (!schoolingInDictionary) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.SCHOOLING.toString() + "] " +
                        " [" + supplierSideBasicInfoDTO.getHighestSchooling() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        /**
         * 检查工作年限正确性
         */
        {
            //从数据字典缓存中获取工作年限集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //工作年限是否存在字典数据中
            boolean workingYearsInDictionary = false;

            //检查工作年限是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideBasicInfoDTO.getWorkingYears()))) {
                    workingYearsInDictionary = true;
                    break;
                }
            }

            //当工作年限值不存在字典数据中时，抛出错误信息
            if (!workingYearsInDictionary) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] " +
                        " [" + supplierSideBasicInfoDTO.getWorkingYears() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        //获取session用户
        SupplierSideVOByLogin supplierSideVOByLogin = UserUtils.getSupplierSideUserBySession();
        //获取用户手机号码，并且根据手机号码获取供应商基本信息
        SupplierSidePO supplierSidePO = supplierSideDao.selectByMobile(String.valueOf(supplierSideVOByLogin.getMobileNumber()));

        //检查学校名称内容长度是否超出DB许可长度
        if (!StringUtils.isEmpty(supplierSideBasicInfoDTO.getSchoolName())) {
            //检查学校名称内容长度是否超出DB许可长度
            try {
                if (supplierSideBasicInfoDTO.getSchoolName().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > SCHOOL_NAME_MAX_BYTES_BY_DB) {
                    logger.info("modifyBasicInformation fail , because school name [" + supplierSideBasicInfoDTO.getSchoolName() + "] bytes greater than"
                            + SCHOOL_NAME_MAX_BYTES_BY_DB);
                    throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_SCHOOL_NAME_TO_LONG);
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("modifyBasicInformation fail , because school name [" + supplierSideBasicInfoDTO.getSchoolName() + "] getBytes("
                        + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
            }

            supplierSidePO.setSchoolName(supplierSideBasicInfoDTO.getSchoolName());
        }

        //检查专业名称内容长度是否超出DB许可长度
        if (!StringUtils.isEmpty(supplierSideBasicInfoDTO.getProfessionalName())) {
            //检查专业名称内容长度是否超出DB许可长度
            try {
                if (supplierSideBasicInfoDTO.getProfessionalName().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > PROFESSIONAL_NAME_MAX_BYTES_BY_DB) {
                    logger.info("modifyBasicInformation fail , because professional name [" + supplierSideBasicInfoDTO.getProfessionalName() + "] bytes greater than"
                            + PROFESSIONAL_NAME_MAX_BYTES_BY_DB);
                    throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_BASIC_INFORMATION_BY_PROFESSIONAL_NAME_TO_LONG);
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("modifyBasicInformation fail , because professional name [" + supplierSideBasicInfoDTO.getProfessionalName() + "] getBytes("
                        + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
            }

            supplierSidePO.setProfessionalName(supplierSideBasicInfoDTO.getProfessionalName());
        }

        //检查供应商用户选择籍贯地区-省是否为空
        if (!ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getNativePlaceProvinceId())) {
            supplierSidePO.setNativePlaceProvinceId(supplierSideBasicInfoDTO.getNativePlaceProvinceId());
        }

        //检查供应商用户选择籍贯地区-市是否为空
        if (!ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getNativePlaceCityId())) {
            supplierSidePO.setNativePlaceCityId(supplierSideBasicInfoDTO.getNativePlaceCityId());
        }

        //检查供应商用户选择籍贯地区-区是否为空
        if (!ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getNativePlaceCountyId())) {
            supplierSidePO.setNativePlaceCountyId(supplierSideBasicInfoDTO.getNativePlaceCountyId());
        }

        //检查供应商用户选择现居地区-省是否为空
        if (!ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getLiveAddressProvinceId())) {
            supplierSidePO.setLiveAddressProvinceId(supplierSideBasicInfoDTO.getLiveAddressProvinceId());
        }

        //检查供应商用户选择现居地区-市是否为空
        if (!ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getLiveAddressCityId())) {
            supplierSidePO.setLiveAddressCityId(supplierSideBasicInfoDTO.getLiveAddressCityId());
        }

        //检查供应商用户选择现居地区-区是否为空
        if (!ObjectUtils.isEmpty(supplierSideBasicInfoDTO.getLiveAddressCountyId())) {
            supplierSidePO.setLiveAddressCountyId(supplierSideBasicInfoDTO.getLiveAddressCountyId());
        }

        supplierSidePO.setRealName(supplierSideBasicInfoDTO.getRealName());
        supplierSidePO.setNickName(supplierSideBasicInfoDTO.getNickName());
        supplierSidePO.setSex(supplierSideBasicInfoDTO.getSex());
        supplierSidePO.setBirthdayYear(supplierSideBasicInfoDTO.getBirthdayYear());
        supplierSidePO.setBirthdayMonth(supplierSideBasicInfoDTO.getBirthdayMonth());
        supplierSidePO.setBirthdayDay(supplierSideBasicInfoDTO.getBirthdayDay());
        supplierSidePO.setEmail(supplierSideBasicInfoDTO.getEmail());
        supplierSidePO.setHighestSchooling(supplierSideBasicInfoDTO.getHighestSchooling());
        supplierSidePO.setWorkingYears(supplierSideBasicInfoDTO.getWorkingYears());

        if (!StringUtils.isEmpty(supplierSideBasicInfoDTO.getHeadPortrait())) {
            String headPortraitSuffix = ".png";
            String headPortraitFileName = supplierSideBasicInfoDTO.getNickName() + headPortraitSuffix;
            //获取headPortrait数据，因为是canvas数据，所以需要截取掉前22个无用字符
            String headPortrait = supplierSideBasicInfoDTO.getHeadPortrait().substring(22);
            //headPortrait写入文件
            //canvas数据是base64加密数据，所以使用对应解密
            byte[] headPortraitBytes = Base64Utils.decode(headPortrait);
            FileUtilHandle.writeFile(headPortraitBytes, SystemConf.SUPPLIER_USER_HEAD_PORTRAIT_FILE_UPLOAD_PATH.toString() + File.separator, headPortraitFileName);
            supplierSidePO.setHeadPortrait(SystemConf.SUPPLIER_USER_HEAD_PORTRAIT_FILE_UPLOAD_PATH.toString() + File.separator + headPortraitFileName);
        }

        //持久化基本信息
        supplierSideDao.updateByPrimaryKeySelective(supplierSidePO);

        //将PO转为VO
        supplierSideVOByLogin = supplierConvert.supplierLogin(supplierSidePO);
        //将会员（供应商）编辑后的新数据重新写入session
        SessionUtil.setAttribute(SystemConf.SUPPLIER_USER_BY_LOGIN.toString(), supplierSideVOByLogin);
    }

    /**
     * 获取供应商简历信息
     *
     * @return
     * @throws EqianyuanException
     */
    public SupplierSideVOByResume getResume() throws EqianyuanException {
        SupplierSideResumeDTO supplierSideResumeDTO = new SupplierSideResumeDTO();
        //获取session用户
        SupplierSideVOByLogin supplierSideVOByLogin = UserUtils.getSupplierSideUserBySession();

        //获取用户手机号码，并且根据手机号码获取供应商基本信息
        ResumePO resumePO = resumeDao.selectBySupplierSideId(supplierSideVOByLogin.getId());

        if (ObjectUtils.isEmpty(resumePO) ||
                ObjectUtils.isEmpty(resumePO.getId())) {
            logger.warn("supplier getResume fail , supplier side id [" + supplierSideVOByLogin.getId() + "] ");
            return new SupplierSideVOByResume();
        }

        //获取工种及熟练度
        List<WorkProficiencyPO> workProficiencyPOs = workProficiencyDao.selectByResumeId(resumePO.getId());
        //获取工作经历
        List<WorkExperiencePO> workExperiencePOs = workExperienceDao.selectByResumeId(resumePO.getId());
        //获取项目经验
        List<ProjectExperiencePO> projectExperiencePOs = projectExperienceDao.selectByResumeId(resumePO.getId());

        //将PO转为DTO
        supplierSideResumeDTO = serviceSupplierConvert.getResume(supplierSideResumeDTO, resumePO);
        supplierSideResumeDTO = serviceSupplierConvert.getWorkProficiencyByResume(supplierSideResumeDTO, workProficiencyPOs);
        supplierSideResumeDTO = serviceSupplierConvert.getWorkExperienceByResume(supplierSideResumeDTO, workExperiencePOs);
        supplierSideResumeDTO = serviceSupplierConvert.getProjectExperienceByResume(supplierSideResumeDTO, projectExperiencePOs);

        SupplierSideVOByResume supplierSideVOByResume = supplierConvert.getResume(supplierSideResumeDTO);
        return supplierSideVOByResume;
    }

    /**
     * 供应商简历信息编辑
     *
     * @param supplierSideResumeDTO
     * @throws EqianyuanException
     */
    @Transactional(rollbackFor = Exception.class)
    public void modifyResume(SupplierSideResumeDTO supplierSideResumeDTO) throws EqianyuanException {
        /**
         * 检查行业正确性
         */
        {
            //检查供应商用户输入所属行业是否为空
            if (ObjectUtils.isEmpty(supplierSideResumeDTO.getIndustry())) {
                logger.warn("modifyResume fail , because user input industry , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_INDUSTRY_IS_EMPTY);
            }

            List<DataDictionaryPO> dataDictionaryPOs;

            //从数据字典缓存中获取行业集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.INDUSTRY.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.INDUSTRY.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //行业是否存在字典数据中
            boolean industryInDictionary = false;

            //检查行业是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideResumeDTO.getIndustry()))) {
                    industryInDictionary = true;
                    break;
                }
            }

            //当行业值不存在字典数据中时，抛出错误信息
            if (!industryInDictionary) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.INDUSTRY.toString() + "] " +
                        " [" + supplierSideResumeDTO.getIndustry() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        /**
         * 检查行业工种类别正确性
         */
        {
            //检查供应商用户输入所属行业工种类别是否为空
            if (ObjectUtils.isEmpty(supplierSideResumeDTO.getWorkType())) {
                logger.warn("modifyResume fail , because user input work type , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_WORK_TYPE_IS_EMPTY);
            }

            List<DataDictionaryPO> dataDictionaryPOs;

            //从数据字典缓存中获取行业工种类别集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK_TYPE.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.WORK_TYPE.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //行业工种类别是否存在字典数据中
            boolean workTypeDictionary = false;

            //检查行业工种类别是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideResumeDTO.getWorkType()))) {
                    workTypeDictionary = true;
                    break;
                }
            }

            //当行业工种类别值不存在字典数据中时，抛出错误信息
            if (!workTypeDictionary) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.WORK_TYPE.toString() + "] " +
                        " [" + supplierSideResumeDTO.getWorkType() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        /**
         * 检查行业工种正确性
         */
        List<SupplierSideResumeDTO.WorkProficiencyDTO> workProficiencyDTOList = new ArrayList<SupplierSideResumeDTO.WorkProficiencyDTO>();
        {
            //检查供应商用户输入所属行业工种是否为空
            if (CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkProficiencyDTOList())) {
                logger.warn("modifyResume fail , because user input work , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_WORK_IS_FAIL);
            }

            List<DataDictionaryPO> dataDictionaryPOs;

            //从数据字典缓存中获取行业工种集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //行业工种是否存在字典数据中
            boolean workDictionary = false;

            for (SupplierSideResumeDTO.WorkProficiencyDTO workProficiencyDTO : supplierSideResumeDTO.getWorkProficiencyDTOList()) {
                if (StringUtils.isEmpty(workProficiencyDTO.getWork())) {
                    continue;
                }

                //检查行业工种是否存在或正确
                for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                    if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(workProficiencyDTO.getWork()))) {
                        workProficiencyDTOList.add(workProficiencyDTO);
                        workDictionary = true;
                        break;
                    }
                }
            }

            //当行业工种值不存在字典数据中时，抛出错误信息
            if (!workDictionary) {
                logger.warn("modifyResume fail , because work data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_WORK_IS_FAIL);
            }

            //获取一个职务下允许选择的岗位数量
            String workCount = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.SUPPLIERSIDE.SUPPLIER_SIDE.toString(), ClientConf.SUPPLIERSIDE.WORK_COUNT.toString());
            //检查会员多选工种条数，是否超出系统许可选择条数
            if (workProficiencyDTOList.size() > Integer.parseInt(workCount)) {
                logger.warn("modifyResume fail , because work data county [" + workProficiencyDTOList.size() + "] , but system just can choose " + workCount);
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_WORK_CHOOSE_TOO_MANY);
            }
        }

        //检查擅长方向是否为空
        if (!StringUtils.isEmpty(supplierSideResumeDTO.getDiscribe())) {
            //检查擅长方向内容长度是否超出DB许可长度
            try {
                if (supplierSideResumeDTO.getDiscribe().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DISCRIBE_MAX_BYTES_BY_DB) {
                    logger.info("modifyResume fail , because discribe [" + supplierSideResumeDTO.getDiscribe() + "] bytes greater than"
                            + DISCRIBE_MAX_BYTES_BY_DB);
                    throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_DISCRIBE_TOO_MANY);
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("modifyResume fail , because discribe [" + supplierSideResumeDTO.getDiscribe() + "] getBytes("
                        + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
            }
        }

        /**
         * 检查工作时间正确性
         */
        {
            //检查供应商用户输入工作时间是否为空
            if (ObjectUtils.isEmpty(supplierSideResumeDTO.getExpectWorkTime())) {
                logger.warn("modifyResume fail , because user input expect work time , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_EXPECT_WORK_TIME_IS_EMPTY);
            }

            List<DataDictionaryPO> dataDictionaryPOs;

            //从数据字典缓存中获取工作时间集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_WORK_TIME.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.EXPECT_WORK_TIME.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //工作时间是否存在字典数据中
            boolean expectWorkTimeDictionary = false;

            //检查工作时间是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideResumeDTO.getExpectWorkTime()))) {
                    expectWorkTimeDictionary = true;
                    break;
                }
            }

            //当工作时间值不存在字典数据中时，抛出错误信息
            if (!expectWorkTimeDictionary) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.EXPECT_WORK_TIME.toString() + "] " +
                        " [" + supplierSideResumeDTO.getExpectWorkTime() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        /**
         * 检查薪资待遇正确性
         */
        {
            //检查供应商用户输入薪资待遇是否为空
            if (ObjectUtils.isEmpty(supplierSideResumeDTO.getExpectPay())) {
                logger.warn("modifyResume fail , because user input expect pay , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_EXPECT_PAY_IS_EMPTY);
            }

            List<DataDictionaryPO> dataDictionaryPOs;

            //从数据字典缓存中获取薪资待遇集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //薪资待遇是否存在字典数据中
            boolean expectPayDictionary = false;

            //检查薪资待遇是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideResumeDTO.getExpectPay()))) {
                    expectPayDictionary = true;
                    break;
                }
            }

            //当薪资待遇值不存在字典数据中时，抛出错误信息
            if (!expectPayDictionary) {
                logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] " +
                        " [" + supplierSideResumeDTO.getExpectPay() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        //获取session用户
        SupplierSideVOByLogin supplierSideVOByLogin = UserUtils.getSupplierSideUserBySession();
        //获取用户手机号码，并且根据手机号码获取供应商基本信息
        ResumePO resumePO = resumeDao.selectBySupplierSideId(supplierSideVOByLogin.getId());

        if (ObjectUtils.isEmpty(resumePO)) {
            resumePO = new ResumePO();
            resumePO.setSupplierSideId(supplierSideVOByLogin.getId());
        }

        resumePO.setIndustry(supplierSideResumeDTO.getIndustry());
        resumePO.setWorkType(supplierSideResumeDTO.getWorkType());
        resumePO.setExpectPay(supplierSideResumeDTO.getExpectPay());
        resumePO.setExpectWorkTime(supplierSideResumeDTO.getExpectWorkTime());
        resumePO.setExpectPay(supplierSideResumeDTO.getExpectPay());
        resumePO.setDiscribe(supplierSideResumeDTO.getDiscribe());

        /**
         * 检查外语语种正确性
         */
        {
            //检查供应商用户输入外语语种是否为空
            if (!ObjectUtils.isEmpty(supplierSideResumeDTO.getForeignLanguage())) {
                List<DataDictionaryPO> dataDictionaryPOs;

                //从数据字典缓存中获取外语语种集合
                dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.FOREIGN_LANGUAGE.toString());
                if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                    logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.FOREIGN_LANGUAGE.toString() + "] data not exists data dictionary");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
                }

                //外语语种是否存在字典数据中
                boolean foreignLanguageDictionary = false;

                //检查外语语种是否存在或正确
                for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                    if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideResumeDTO.getForeignLanguage()))) {
                        foreignLanguageDictionary = true;
                        break;
                    }
                }

                //当外语语种值不存在字典数据中时，抛出错误信息
                if (!foreignLanguageDictionary) {
                    logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.FOREIGN_LANGUAGE.toString() + "] " +
                            " [" + supplierSideResumeDTO.getForeignLanguage() + "] data not exists data dictionary");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
                }

                resumePO.setForeignLanguage(supplierSideResumeDTO.getForeignLanguage());
            }
        }

        /**
         * 检查外语熟练度正确性
         */
        {
            //检查供应商用户输入外语熟练度是否为空
            if (!ObjectUtils.isEmpty(supplierSideResumeDTO.getForeignLanguageAbility())) {
                List<DataDictionaryPO> dataDictionaryPOs;

                //从数据字典缓存中获取外语熟练度集合
                dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.FOREIGN_LANGUAGE_ABILITY.toString());
                if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                    logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.FOREIGN_LANGUAGE_ABILITY.toString() + "] data not exists data dictionary");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
                }

                //外语熟练度是否存在字典数据中
                boolean foreignLanguageAbilityDictionary = false;

                //检查外语熟练度是否存在或正确
                for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                    if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(supplierSideResumeDTO.getForeignLanguageAbility()))) {
                        foreignLanguageAbilityDictionary = true;
                        break;
                    }
                }

                //当外语熟练度值不存在字典数据中时，抛出错误信息
                if (!foreignLanguageAbilityDictionary) {
                    logger.warn("modifyResume fail , because group key [" + DataDictionaryConf.FOREIGN_LANGUAGE_ABILITY.toString() + "] " +
                            " [" + supplierSideResumeDTO.getForeignLanguageAbility() + "] data not exists data dictionary");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
                }

                resumePO.setForeignLanguageAbility(supplierSideResumeDTO.getForeignLanguageAbility());
            }
        }

        //检查供应商用户选择期望工作地区-省是否为空
        if (!ObjectUtils.isEmpty(supplierSideResumeDTO.getExpectWorkProvinceId())) {
            resumePO.setExpectWorkProvinceId(supplierSideResumeDTO.getExpectWorkProvinceId());
        }

        //检查供应商用户选择期望工作地区-市是否为空
        if (!ObjectUtils.isEmpty(supplierSideResumeDTO.getExpectWorkCityId())) {
            resumePO.setExpectWorkCityId(supplierSideResumeDTO.getExpectWorkCityId());
        }

        //检查供应商用户选择期望工作地区-区是否为空
        if (!ObjectUtils.isEmpty(supplierSideResumeDTO.getExpectWorkCountyId())) {
            resumePO.setExpectWorkCountyId(supplierSideResumeDTO.getExpectWorkCountyId());
        }

        //持久化个人简历信息
        if (StringUtils.isEmpty(resumePO.getId())) {
            resumeDao.insertSelective(resumePO);
        } else {
            resumeDao.updateByPrimaryKeySelective(resumePO);
        }

        //先删除工种熟练度数据
        workProficiencyDao.deleteByResumeId(resumePO.getId());
        if (!CollectionUtils.isEmpty(workProficiencyDTOList)) {
            List<WorkProficiencyPO> workProficiencyPOs = new ArrayList<WorkProficiencyPO>();
            for (SupplierSideResumeDTO.WorkProficiencyDTO workProficiencyDTO : workProficiencyDTOList) {
                WorkProficiencyPO workProficiencyPO = new WorkProficiencyPO();
                BeanUtils.copyProperties(workProficiencyDTO, workProficiencyPO);
                workProficiencyPO.setResumeId(resumePO.getId());
                workProficiencyPOs.add(workProficiencyPO);
            }
            //持久化工种熟练度
            workProficiencyDao.insertByList(workProficiencyPOs);
        }

        //先删除工作经历数据
        workExperienceDao.deleteByResumeId(resumePO.getId());
        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkExperienceDTOList())) {
            List<WorkExperiencePO> workExperiencePOs = new ArrayList<WorkExperiencePO>();
            for (SupplierSideResumeDTO.WorkExperienceDTO workExperienceDTO : supplierSideResumeDTO.getWorkExperienceDTOList()) {
                WorkExperiencePO workExperiencePO = new WorkExperiencePO();
                BeanUtils.copyProperties(workExperienceDTO, workExperiencePO);
                workExperiencePO.setResumeId(resumePO.getId());

                if (!StringUtils.isEmpty(workExperienceDTO.getWorkCycleBegin())
                        && workExperienceDTO.getWorkCycleBegin().indexOf("年") != -1) {
                    String[] begin = workExperienceDTO.getWorkCycleBegin().replace("月", StringUtils.EMPTY).split("年");
                    if (RegexUtils.isDigital(begin[0])
                            && RegexUtils.isDigital(begin[1])) {
                        workExperiencePO.setBeginYears(Integer.parseInt(begin[0]));
                        workExperiencePO.setBeginMonth(Integer.parseInt(begin[1]));
                    }
                }

                if (!StringUtils.isEmpty(workExperienceDTO.getWorkCycleEnd())
                        && workExperienceDTO.getWorkCycleEnd().indexOf("年") != -1) {
                    String[] end = workExperienceDTO.getWorkCycleEnd().replace("月", StringUtils.EMPTY).split("年");
                    if (RegexUtils.isDigital(end[0])
                            && RegexUtils.isDigital(end[1])) {
                        workExperiencePO.setEndYears(Integer.parseInt(end[0]));
                        workExperiencePO.setEndMonth(Integer.parseInt(end[1]));
                    }
                }

                workExperiencePOs.add(workExperiencePO);
            }
            //持久化工作经历
            workExperienceDao.insertByList(workExperiencePOs);
        }

        //先删除项目经验数据
        projectExperienceDao.deleteByResumeId(resumePO.getId());
        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getProjectExperienceDTOList())) {
            List<ProjectExperiencePO> projectExperiencePOs = new ArrayList<ProjectExperiencePO>();
            for (SupplierSideResumeDTO.ProjectExperienceDTO projectExperienceDTO : supplierSideResumeDTO.getProjectExperienceDTOList()) {
                ProjectExperiencePO projectExperiencePO = new ProjectExperiencePO();
                BeanUtils.copyProperties(projectExperienceDTO, projectExperiencePO);
                projectExperiencePO.setResumeId(resumePO.getId());

                if (!StringUtils.isEmpty(projectExperienceDTO.getProjectCycleBegin())
                        && projectExperienceDTO.getProjectCycleBegin().indexOf("年") != -1) {
                    String[] begin = projectExperienceDTO.getProjectCycleBegin().replace("月", StringUtils.EMPTY).split("年");
                    if (RegexUtils.isDigital(begin[0])
                            && RegexUtils.isDigital(begin[1])) {
                        projectExperiencePO.setBeginYears(Integer.parseInt(begin[0]));
                        projectExperiencePO.setBeginMonth(Integer.parseInt(begin[1]));
                    }
                }

                if (!StringUtils.isEmpty(projectExperienceDTO.getProjectCycleEnd())
                        && projectExperienceDTO.getProjectCycleEnd().indexOf("年") != -1) {
                    String[] end = projectExperienceDTO.getProjectCycleEnd().replace("月", StringUtils.EMPTY).split("年");
                    if (RegexUtils.isDigital(end[0])
                            && RegexUtils.isDigital(end[1])) {
                        projectExperiencePO.setEndYears(Integer.parseInt(end[0]));
                        projectExperiencePO.setEndMonth(Integer.parseInt(end[1]));
                    }
                }

                projectExperiencePOs.add(projectExperiencePO);
            }
            //持久化项目经验
            projectExperienceDao.insertByList(projectExperiencePOs);
        }
    }

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @param supplierSearchListByRequest
     * @param page
     * @return
     * @throws EqianyuanException
     */
    public PageResponse supplierList(SupplierSearchListByRequest supplierSearchListByRequest, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = supplierSideDao.countByPagination(supplierSearchListByRequest);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<SupplierPOBySearchList> supplierPOBySearchLists = supplierSideDao.selectByPagination(page, supplierSearchListByRequest);
        if (CollectionUtils.isEmpty(supplierPOBySearchLists)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        List<SupplierByListSearchDTO> supplierByListSearchDTOs = new ArrayList<SupplierByListSearchDTO>();
        for (SupplierPOBySearchList supplierPOBySearchList : supplierPOBySearchLists) {
            SupplierByListSearchDTO supplierByListSearchDTO = new SupplierByListSearchDTO();
            BeanUtils.copyProperties(supplierPOBySearchList, supplierByListSearchDTO);
            supplierByListSearchDTOs.add(supplierByListSearchDTO);
        }

        return new PageResponse(page, supplierConvert.supplierList(supplierByListSearchDTOs));
    }

    /**
     * 供应商用户信息是否已经完善
     *
     * @param mobile
     * @return
     * @throws EqianyuanException
     */
    public boolean isIntegrity(String mobile) throws EqianyuanException {
        if (StringUtils.isEmpty(mobile)) {
            logger.warn("supplier isIntegrity fail , because mobile , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        SupplierSidePO supplierSidePO = supplierSideDao.selectByMobile(mobile);
        if (StringUtils.isEmpty(supplierSidePO.getRealName())
                && StringUtils.isEmpty(supplierSidePO.getEmail())) {
            return false;
        }
        return true;
    }

    /**
     * 需求报名
     *
     * @param demandId   需求编号
     * @param supplierId 供应方用户编号
     * @throws EqianyuanException
     */
    public void signUp(String demandId, String supplierId, String work) throws EqianyuanException {
        if (StringUtils.isEmpty(demandId)) {
            logger.warn("signUp fail , because demand id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_IS_EMPTY);
        }

        if (StringUtils.isEmpty(work)) {
            logger.warn("signUp fail , because work is null");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        /**
         * 检查行业工种正确性
         */
        List<SupplierSideResumeDTO.WorkProficiencyDTO> workProficiencyDTOList = new ArrayList<SupplierSideResumeDTO.WorkProficiencyDTO>();
        {
            List<DataDictionaryPO> dataDictionaryPOs;

            //从数据字典缓存中获取行业工种集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("signUp fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //行业工种是否存在字典数据中
            boolean workDictionary = false;

            //检查行业工种是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), work)) {
                    workDictionary = true;
                    break;
                }
            }

            //当行业工种值不存在字典数据中时，抛出错误信息
            if (!workDictionary) {
                logger.warn("signUp fail , because work data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        //先根据用户编号和需求编号查询数据，如已存在报名信息，则提示已经报过名
        int signUpCnt = signUpDao.countBySigup(demandId, supplierId);

        if (signUpCnt > 0) {
            logger.warn("signUp fail , because demand id [" + demandId + "], supplier id [" + supplierId + "] query data is exists");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HAS_SIGN_UP);
        }

        //插入报名数据
        SignUpPO signUpPO = new SignUpPO();
        signUpPO.setDemandId(demandId);
        signUpPO.setSupplierSideId(supplierId);
        signUpPO.setCreateTime(CalendarUtil.getSystemSeconds());
        signUpPO.setWork(work);
        signUpDao.insertSelective(signUpPO);
    }

    /**
     * 查询供应商报名需求分页
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     * @throws EqianyuanException
     */
    public PageResponse signUpDemand(String supplierId, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = signUpDao.countBySupplierId(supplierId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<SupplierSignUpDemandPO> supplierSignUpDemandPOs = signUpDao.selectBySupplierPagination(supplierId, page);

        if (CollectionUtils.isEmpty(supplierSignUpDemandPOs)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        return new PageResponse(page, supplierConvert.supplierSignUpDemand(supplierSignUpDemandPOs));
    }

    /**
     * 查询供应商约见需求分页
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     * @throws EqianyuanException
     */
    public PageResponse signUpMeetDemand(String supplierId, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = signUpMeetDao.countBySupplierId(supplierId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<SupplierSignUpMeetDemandPO> supplierSignUpMeetDemandPOs = signUpMeetDao.selectBySupplierPagination(supplierId, page);

        if (CollectionUtils.isEmpty(supplierSignUpMeetDemandPOs)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        return new PageResponse(page, supplierConvert.supplierSignUpMeetDemand(supplierSignUpMeetDemandPOs));
    }

    /**
     * 查询供应商聘用需求分页
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     * @throws EqianyuanException
     */
    public PageResponse hireDemand(String supplierId, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = hireDao.countBySupplierId(supplierId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<SupplierHireDemandPO> supplierHireDemandPOs = hireDao.selectBySupplierPagination(supplierId, page);

        if (CollectionUtils.isEmpty(supplierHireDemandPOs)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        return new PageResponse(page, supplierConvert.supplierHireDemand(supplierHireDemandPOs));
    }
}
