package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.dto.DemandSideBasicInfoDTO;
import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.bean.po.DemandSidePO;
import cn.eqianyuan.bean.vo.DemandSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.controller.convert.DemandConvert;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.IDemandSideDao;
import cn.eqianyuan.listener.InitialData;
import cn.eqianyuan.service.IDemandSideService;
import cn.eqianyuan.util.*;
import cn.eqianyuan.util.yamlMapper.ClientConf;
import cn.eqianyuan.util.yamlMapper.DataDictionaryConf;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by jason on 2016-08-12.
 */
@Service
public class DemandSideServiceImpl implements IDemandSideService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IDemandSideDao demandSideDao;

    @Autowired
    private DemandConvert demandConvert;

    //账号激活-已激活状态
    private static final int ACTIVATION_STATUS_BY_ACTIVATED = 1;
    //企业名称DB许可字节长度
    private static final int COMPANY_NAME_MAX_BYTES_BY_DB = 192;

    /**
     * 添加需求商用户
     *
     * @param email           手机号码
     * @param loginPassword   登录密码
     * @param confirmPassword 确认密码
     * @throws EqianyuanException
     */
    public void add(String email, String loginPassword, String confirmPassword) throws EqianyuanException {
        //检查需求商用户输入邮箱是否为空
        if (StringUtils.isEmpty(email)) {
            logger.warn("demand user add fail , because user input email , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_EMAIL_IS_EMPTY);
        }

        //检查需求商用户输入邮箱是否正确
        if (!RegexUtils.isEmail(email)) {
            logger.warn("demand user add fail , because user input email is not right email");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_EMAIL_IS_FAIL);
        }

        //检查需求商用户输入登录密码是否为空
        if (StringUtils.isEmpty(loginPassword)) {
            logger.warn("demand user add fail , because user input login password , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_IS_EMPTY);
        }

        //检查需求商用户输入确认密码是否为空
        if (StringUtils.isEmpty(confirmPassword)) {
            logger.warn("demand user add fail , because user input confirmPassword , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_CONFIRM_PASSWORD_IS_EMPTY);
        }

        //检查需求商用户两次输入密码是否一致
        if (!StringUtils.equals(loginPassword, confirmPassword)) {
            logger.warn("demand user add fail , because two input password is not consistent");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_TWO_PASSWORD_NOT_CONSISTENT);
        }

        //检查需求商用户输入登录密码是否含有空格
        if (RegexUtils.hasBlankCharacters(loginPassword)) {
            logger.warn("demand user add fail , because user input login password , value contains invalid characters");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_CONTAINS_INVALID_CHARACTERS);
        }

        //检查需求商用户输入密码是否在长度范围中
        if (loginPassword.length() < 6 || loginPassword.length() > 20) {
            logger.warn("demand user add fail , because user input login password , value length is beyond the scope of [6 ~ 20]");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_COMPLEXITY_IS_NOT_ENOUGH);
        }

        /**
         * 检查需求商用户输入密码复杂度
         */
        {
            //声明复杂性
            int complexity = 0;
            //是否含有数字
            if (RegexUtils.hasDigital(loginPassword)) {
                complexity++;
            }
            //是否含有字母及下划线
            if (RegexUtils.hasAlphabet(loginPassword)) {
                complexity++;
            }
            //是否含有特殊字符
            if (RegexUtils.hasSpecialCharacters(loginPassword)) {
                complexity++;
            }

            //复杂度最低2
            if (complexity < 2) {
                logger.warn("demand user add fail , because user input login password , value complexity is not enough");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_COMPLEXITY_IS_NOT_ENOUGH);
            }
        }

        //检查邮箱是否已经注册过
        int memberCountByEmail = demandSideDao.selectCountByEmail(email);
        //当结果数量大于0，说明已经被注册
        if (memberCountByEmail > 0) {
            logger.warn("demand user add fail , because email is already register.");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_EMAIL_IS_ALREADY_REGISTER);
        }

        //密码MD5加密处理
        String encryptionPwd = Md5Util.MD5By32(StringUtils.lowerCase(loginPassword));
        //获取当前系统秒数
        int systemSeconds = CalendarUtil.getSystemSeconds();
        //获取随机验证码，作为账号激活码
        String activationCode = VerifyCodeUtils.random(6, VerifyCodeUtils.SEEDS_BY_CHARACTER);
        //MD5加密激活码
        String encryptionActivationCode = Md5Util.MD5By32(activationCode);
        //获取当前系统月份英文描述
        String monthEN = CalendarUtil.getCurrentMonthEN();
        //截取月份英文描述首字母
        String firstCharacter = StringUtils.substring(monthEN, 0, 1);
        //将首字母转为大写
        String firstCharacterUpper = StringUtils.upperCase(firstCharacter);

        //创建PO对象并设置内容
        DemandSidePO demandSidePO = new DemandSidePO();
        //注册期，给与默认用户名，用户名生成规则：当月月份英文大写首字母+当前系统秒数时间
        demandSidePO.setCompanyName(firstCharacterUpper + systemSeconds);
        demandSidePO.setEmail(email);
        demandSidePO.setLoginPassword(encryptionPwd);
        demandSidePO.setCreateTime(systemSeconds);
        demandSidePO.setActivationCode(encryptionActivationCode);
        //持久化PO对象
        demandSideDao.insertSelective(demandSidePO);

        //获取邮件主题
        String subject = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.SUBJECT.toString());
        //获取邮件内容
        String message = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.CONTENT.toString());
        //获取账号激活地址
        String address = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.ADDRESS.toString());

        //检查邮件主题是否为空
        if (StringUtils.isEmpty(subject)) {
            logger.warn("demand add fail ,because subject not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        //检查邮件内容是否为空
        if (StringUtils.isEmpty(message)) {
            logger.warn("demand add fail ,because message not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        //检查账号激活地址是否为空
        if (StringUtils.isEmpty(address)) {
            logger.warn("demand add fail ,because address not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        //激活地址加入参数内容
        address += "?email=" + email + "&activationCode=" + encryptionActivationCode;

        //替换消息模板内容
        message = StringTemplateReplaceUtil.getStr(message, "\\{\\?\\}", email, address, address, CalendarUtil.getCurrentDateAsString());
        //发送账号激活邮件
        EmailUtils.sendEmail(email, subject, message);
    }

    /**
     * 需求商账号激活
     *
     * @param email          邮箱号码
     * @param activationCode 激活码
     * @throws EqianyuanException
     */
    public void accountActivation(String email, String activationCode) throws EqianyuanException {
        //检查激活邮箱账号是否为空
        if (StringUtils.isEmpty(email)) {
            logger.warn("demand accountActivation fail ,because email is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_ACCOUNT_ACTIVATION_FAIL);
        }

        //检查激活码是否为空
        if (StringUtils.isEmpty(activationCode)) {
            logger.warn("demand accountActivation fail ,because activationCode is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_ACCOUNT_ACTIVATION_FAIL);
        }

        //根据邮箱及激活码查询账号数据
        DemandSidePO demandSidePo = demandSideDao.selectByActivation(email, activationCode);

        if (ObjectUtils.isEmpty(demandSidePo) || StringUtils.isEmpty(demandSidePo.getId())) {
            logger.warn("demand accountActivation fail , because email or activation code is error.");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_ACCOUNT_ACTIVATION_FAIL);
        }

        demandSidePo.setActivationStatus(ACTIVATION_STATUS_BY_ACTIVATED);
        //激活账号
        demandSideDao.updateByPrimaryKeySelective(demandSidePo);

        DemandSideVOByLogin demandSideVOByLogin = UserUtils.getDemandSideUserBySession();
        if (!ObjectUtils.isEmpty(demandSideVOByLogin) && !StringUtils.isEmpty(demandSideVOByLogin.getEmail())) {
            demandSideVOByLogin.setActivationStatus(ACTIVATION_STATUS_BY_ACTIVATED);
            //将登录VO写入session
            SessionUtil.setAttribute(SystemConf.DEMAND_USER_BY_LOGIN.toString(), demandSideVOByLogin);
        }
    }

    /**
     * 发送激活邮件
     *
     * @param email 邮箱号码
     * @throws EqianyuanException
     */
    public void sendActivationMail(String email) throws EqianyuanException {
        //检查激活邮箱账号是否为空
        if (StringUtils.isEmpty(email)) {
            logger.warn("demand sendActivationMail fail ,because email is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_EMAIL_IS_EMPTY);
        }

        //根据邮箱查询账号数据
        DemandSidePO demandSidePo = demandSideDao.selectByEmail(email);

        if (ObjectUtils.isEmpty(demandSidePo) || StringUtils.isEmpty(demandSidePo.getId())) {
            logger.warn("demand sendActivationMail fail , because select account by email[" + email + "] is null.");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_ACCOUNT_IS_NOT_EXISTS);
        }

        //获取邮件主题
        String subject = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.SUBJECT.toString());
        //获取邮件内容
        String message = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.CONTENT.toString());
        //获取账号激活地址
        String address = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.ADDRESS.toString());

        //检查邮件主题是否为空
        if (StringUtils.isEmpty(subject)) {
            logger.warn("demand sendActivationMail fail ,because subject not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        //检查邮件内容是否为空
        if (StringUtils.isEmpty(message)) {
            logger.warn("demand sendActivationMail fail ,because message not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        //检查账号激活地址是否为空
        if (StringUtils.isEmpty(address)) {
            logger.warn("demand sendActivationMail fail ,because address not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        //激活地址加入参数内容
        address += "?email=" + email + "&activationCode=" + demandSidePo.getActivationCode();

        //替换消息模板内容
        message = StringTemplateReplaceUtil.getStr(message, "\\{\\?\\}", email, address, address, CalendarUtil.getCurrentDateAsString());
        //发送账号激活邮件
        EmailUtils.sendEmail(email, subject, message);
    }

    /**
     * 需求商用户登录
     *
     * @param email
     * @param loginPassword
     * @return
     * @throws EqianyuanException
     */
    public DemandSideVOByLogin demandLogin(String email, String loginPassword) throws EqianyuanException {
        //检查需求商用户输入邮箱是否为空
        if (StringUtils.isEmpty(email)) {
            logger.warn("demand login fail , because user input email , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_EMAIL_IS_EMPTY);
        }

        //检查需求商用户输入登录密码是否为空
        if (StringUtils.isEmpty(loginPassword)) {
            logger.warn("demand login fail , because user input login password , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_LOGIN_PASSWORD_IS_EMPTY);
        }

        //密码MD5加密处理
        String encryptionPwd = Md5Util.MD5By32(StringUtils.lowerCase(loginPassword));
        //根据手机号码及加密密码查询供应商用户信息
        DemandSidePO demandSidePO = demandSideDao.selectByLogin(email, encryptionPwd);
        if (ObjectUtils.isEmpty(demandSidePO) ||
                ObjectUtils.isEmpty(demandSidePO.getId())) {
            logger.warn("demand login fail , email [" + email +
                    "] , loginPassword [" + loginPassword + "] , encryptionPwd [" + encryptionPwd + "]");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_LOGIN_BY_ACCOUNT_ERROR);
        }

        //将PO转为VO
        DemandSideVOByLogin demandSideVOByLogin = demandConvert.demandLogin(demandSidePO);
        //将登录VO写入session
        SessionUtil.setAttribute(SystemConf.DEMAND_USER_BY_LOGIN.toString(), demandSideVOByLogin);
        return demandSideVOByLogin;
    }

    /**
     * 需求商用户登出
     */
    public void demandLogout() {
        SessionUtil.removeAttribute(SystemConf.DEMAND_USER_BY_LOGIN.toString());
    }

    /**
     * 账号是否已激活
     *
     * @param activationStatus 激活状态
     * @return
     */
    public boolean isActivation(Integer activationStatus) {
        if (!ObjectUtils.isEmpty(activationStatus) &&
                activationStatus == ACTIVATION_STATUS_BY_ACTIVATED) {
            return true;
        }
        return false;
    }

    /**
     * 需求商账户基本信息是否完善
     *
     * @param email
     * @return
     */
    public boolean isIntegrity(String email) throws EqianyuanException {
        //检查需求商用户输入邮箱是否为空
        if (StringUtils.isEmpty(email)) {
            logger.warn("demand isIntegrity fail , because email , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_REGISTER_BY_EMAIL_IS_EMPTY);
        }

        DemandSidePO demandSidePO = demandSideDao.selectByEmail(email);
        //检查需求商账户信息中基本资料必填项是否有值，有值则认定为资料已经完善
        if (StringUtils.isEmpty(demandSidePO.getCompanyName())) {
            return false;
        }
        return true;
    }

    /**
     * 获取需求商基本信息
     *
     * @return
     * @throws EqianyuanException
     */
    public DemandSideVOByBasicInfo getBasicInformation() throws EqianyuanException {
        //获取session用户
        DemandSideVOByLogin demandSideVOByLogin = UserUtils.getDemandSideUserBySession();

        //获取用户邮箱，并且根据邮箱号获取需求商基本信息
        DemandSidePO demandSidePO = demandSideDao.selectByEmail(demandSideVOByLogin.getEmail());
        //将PO转为VO
        DemandSideVOByBasicInfo demandSideBasicInfoVO = demandConvert.getBasicInformation(demandSidePO);
        return demandSideBasicInfoVO;
    }

    /**
     * 需求商基本信息编辑
     *
     * @param demandSideBasicInfoDTO
     * @throws EqianyuanException
     */
    public void modifyBasicInformation(DemandSideBasicInfoDTO demandSideBasicInfoDTO) throws EqianyuanException {
        //检查需求商用户输入企业名称是否为空
        if (StringUtils.isEmpty(demandSideBasicInfoDTO.getCompanyName())) {
            logger.warn("modifyBasicInformation fail , because user input company name , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_NAME_IS_EMPTY);
        }

        //检查需求商用户输入企业性质是否为空
        if (ObjectUtils.isEmpty(demandSideBasicInfoDTO.getEnterpriseNature())) {
            logger.warn("modifyBasicInformation fail , because user input enterprise nature , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_ENTERPRISE_NATURE_IS_EMPTY);
        }

        //检查需求商用户输入企业规模是否为空
        if (ObjectUtils.isEmpty(demandSideBasicInfoDTO.getEnterpriseScale())) {
            logger.warn("modifyBasicInformation fail , because user input enterprise scale , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_ENTERPRISE_SCALE_IS_EMPTY);
        }

        //检查企业名称内容长度是否超出DB许可长度
        try {
            if (demandSideBasicInfoDTO.getCompanyName().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > COMPANY_NAME_MAX_BYTES_BY_DB) {
                logger.info("modifyBasicInformation fail , because company name [" + demandSideBasicInfoDTO.getCompanyName() + "] bytes greater than"
                        + COMPANY_NAME_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_NAME_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("modifyBasicInformation fail , because company name [" + demandSideBasicInfoDTO.getCompanyName() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        List<DataDictionaryPO> dataDictionaryPOs;

        /**
         * 检查企业性质正确性
         */
        {
            //从数据字典缓存中获取企业性质集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.ENTERPRISE_NATURE.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.ENTERPRISE_NATURE.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //企业性质是否存在字典数据中
            boolean natureInDictionary = false;

            //检查企业性质是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(demandSideBasicInfoDTO.getEnterpriseNature()))) {
                    natureInDictionary = true;
                    break;
                }
            }

            //当企业性质值不存在字典数据中时，抛出错误信息
            if (!natureInDictionary) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.ENTERPRISE_NATURE.toString() + "] , " +
                        "enterprise nature [" + demandSideBasicInfoDTO.getEnterpriseNature() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        /**
         * 检查企业规模正确性
         */
        {
            //从数据字典缓存中获取企业规模集合
            dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.ENTERPRISE_SCALE.toString());
            if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.ENTERPRISE_SCALE.toString() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }

            //企业规模是否存在字典数据中
            boolean scaleDictionary = false;

            //检查企业规模是否存在或正确
            for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                if (StringUtils.equalsIgnoreCase(dataDictionaryPO.getGroupValKey(), String.valueOf(demandSideBasicInfoDTO.getEnterpriseScale()))) {
                    scaleDictionary = true;
                    break;
                }
            }

            //当企业规模值不存在字典数据中时，抛出错误信息
            if (!scaleDictionary) {
                logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.ENTERPRISE_SCALE.toString() + "] , " +
                        "enterprise nature [" + demandSideBasicInfoDTO.getEnterpriseScale() + "] data not exists data dictionary");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
            }
        }

        //检查需求商用户输入联系电话是否为空
        //检查需求商用户输入企业归属地是否为空
        //检查需求商用户输入企业地址是否为空

        //获取session用户
        DemandSideVOByLogin demandSideVOByLogin = UserUtils.getDemandSideUserBySession();
        //获取用户邮箱，并且根据邮箱号获取需求商基本信息
        DemandSidePO demandSidePO = demandSideDao.selectByEmail(demandSideVOByLogin.getEmail());
        demandSidePO.setCompanyName(demandSideBasicInfoDTO.getCompanyName());
        demandSidePO.setEnterpriseNature(demandSideBasicInfoDTO.getEnterpriseNature());
        demandSidePO.setEnterpriseScale(demandSideBasicInfoDTO.getEnterpriseScale());

        //持久化基本信息
        demandSideDao.updateByPrimaryKeySelective(demandSidePO);
    }

    /**
     * 需求发布
     *
     * @param demandSideBasicInfoDTO
     * @throws EqianyuanException
     */
    public void demandPublish(DemandSideBasicInfoDTO demandSideBasicInfoDTO) throws EqianyuanException {

    }

}
