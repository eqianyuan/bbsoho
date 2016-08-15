package cn.eqianyuan.service;

import cn.eqianyuan.core.exception.EqianyuanException;

/**
 * Created by jason on 2016-08-12.
 */
public interface IDemandSideService {

    /**
     * 添加需求商用户
     *
     * @param email           手机号码
     * @param loginPassword   登录密码
     * @param confirmPassword 确认密码
     * @throws EqianyuanException
     */
    void add(String email, String loginPassword, String confirmPassword) throws EqianyuanException;

    /**
     * 需求商账号激活
     *
     * @param email          邮箱号码
     * @param activationCode 激活码
     * @throws EqianyuanException
     */
    void accountActivation(String email, String activationCode) throws EqianyuanException;

    /**
     * 发送激活邮件
     *
     * @param email 邮箱号码
     * @throws EqianyuanException
     */
    void sendActivationMail(String email) throws EqianyuanException;
}
