package cn.eqianyuan.service;

import cn.eqianyuan.bean.dto.DemandSideBasicInfoDTO;
import cn.eqianyuan.bean.vo.DemandSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
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

    /**
     * 需求商用户登录
     *
     * @param email
     * @param loginPassword
     * @return
     * @throws EqianyuanException
     */
    DemandSideVOByLogin demandLogin(String email, String loginPassword) throws EqianyuanException;

    /**
     * 需求商用户登出
     */
    void demandLogout();

    /**
     * 账户是否已经激活
     *
     * @param activationStatus
     * @return
     */
    boolean isActivation(Integer activationStatus);

    /**
     * 需求商账户基本信息是否已经完善
     *
     * @param email
     * @return
     */
    boolean isIntegrity(String email) throws EqianyuanException;

    /**
     * 获取需求商基本信息
     *
     * @return
     * @throws EqianyuanException
     */
    DemandSideVOByBasicInfo getBasicInformation() throws EqianyuanException;

    /**
     * 需求商基本信息编辑
     *
     * @param demandSideBasicInfoDTO
     * @throws EqianyuanException
     */
    void modifyBasicInformation(DemandSideBasicInfoDTO demandSideBasicInfoDTO) throws EqianyuanException;

    /**
     * 需求发布
     *
     * @param demandSideBasicInfoDTO
     * @throws EqianyuanException
     */
    void demandPublish(DemandSideBasicInfoDTO demandSideBasicInfoDTO) throws EqianyuanException;
}
