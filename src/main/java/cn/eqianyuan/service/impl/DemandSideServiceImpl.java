package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.po.DemandSidePO;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.IDemandSideDao;
import cn.eqianyuan.service.IDemandSideService;
import cn.eqianyuan.util.*;
import cn.eqianyuan.util.yamlMapper.ClientConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Created by jason on 2016-08-12.
 */
@Service
public class DemandSideServiceImpl implements IDemandSideService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IDemandSideDao demandSideDao;

    //账号激活-已激活状态
    private static final int ACTIVATION_STATUS_BY_ACTIVATED = 1;

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

        //创建PO对象并设置内容
        DemandSidePO demandSidePO = new DemandSidePO();
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
}
