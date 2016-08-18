package cn.eqianyuan.util;

import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.util.yamlMapper.ClientConf;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ObjectUtils;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * 邮箱工具类
 * Created by jason on 2016-08-12.
 */
public class EmailUtils {
    private static Logger logger = Logger.getLogger(EmailUtils.class);

    /**
     * 发送邮箱信息
     *
     * @param email
     * @param subject
     * @param content
     * @return
     */
    public static boolean sendEmail(String email, String subject, String content) throws EqianyuanException {
        String host = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.HOST.toString());
        String fromAccountNumber = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.ACCOUNT_NUMBER.toString());
        String fromAccountName = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.ACCOUNT_NAME.toString());
        String fromAuthorizationCode = YamlForMapHandleUtil.getValueBykey(ClientConf.getMap(), ClientConf.MAIL.MAIL.toString(), ClientConf.MAIL.AUTHORIZATION_CODE.toString());

        if (ObjectUtils.isEmpty(host)) {
            logger.warn("sendEmail fail , because host not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        if (ObjectUtils.isEmpty(fromAccountNumber)) {
            logger.warn("sendEmail fail , because account number not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        if (ObjectUtils.isEmpty(fromAccountName)) {
            logger.warn("sendEmail fail , because account name  not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        if (ObjectUtils.isEmpty(fromAccountName)) {
            logger.warn("sendEmail fail , because authorization code not exists the client-conf.yaml");
            throw new EqianyuanException(ExceptionMsgConstant.GET_CONFIGURATION_ERROR);
        }

        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.auth", "true");            // 请求认证，参数名称与具体实现有关
        props.setProperty("mail.smtp.host", host);              // 发件人的邮箱的 SMTP 服务器地址

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance(props);
        session.setDebug(false);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 1. 创建一封邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        // 2. From: 发件人
        InternetAddress fromInternetAddress, toInternatAddress;
        try {
            fromInternetAddress = new InternetAddress(fromAccountNumber, fromAccountName, SystemConf.PLATFORM_CHARSET.toString());
            toInternatAddress = new InternetAddress(email, StringUtils.EMPTY, SystemConf.PLATFORM_CHARSET.toString());
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
            throw new EqianyuanException(ExceptionMsgConstant.REGISTRATION_ACTIVATION_EMAIL_SEND_ERROR);
        }

        // 4. 根据 Session 获取邮件传输对象
        Transport transport;
        try {
            transport = session.getTransport();
        } catch (NoSuchProviderException e) {
            logger.error(e);
            throw new EqianyuanException(ExceptionMsgConstant.REGISTRATION_ACTIVATION_EMAIL_SEND_ERROR);
        }

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        try {
            mimeMessage.setFrom(fromInternetAddress);
            mimeMessage.setRecipient(MimeMessage.RecipientType.TO, toInternatAddress);
            // 4. Subject: 邮件主题
            mimeMessage.setSubject(subject);
            // 5. Content: 邮件正文（可以使用html标签）
            mimeMessage.setContent(content, "text/html;charset=UTF-8");
            // 6. 设置发件时间
            mimeMessage.setSentDate(new Date());
            // 7. 保存设置
            mimeMessage.saveChanges();
            // 5. 使用 邮箱账号 和 密码 连接邮件服务器
            //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
            transport.connect(fromAccountNumber, fromAuthorizationCode);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        } catch (MessagingException e) {
            logger.error(e);
            throw new EqianyuanException(ExceptionMsgConstant.REGISTRATION_ACTIVATION_EMAIL_SEND_ERROR);
        } finally {
            // 7. 关闭连接
            try {
                transport.close();
            } catch (MessagingException e) {
                logger.error(e);
                throw new EqianyuanException(ExceptionMsgConstant.REGISTRATION_ACTIVATION_EMAIL_SEND_ERROR);
            }
        }

        return true;
    }
}
