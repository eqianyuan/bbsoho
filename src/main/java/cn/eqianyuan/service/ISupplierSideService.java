package cn.eqianyuan.service;

import cn.eqianyuan.bean.dto.SupplierSideDTO;
import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.core.exception.EqianyuanException;

/**
 * Created by jason on 2016-08-10.
 */
public interface ISupplierSideService {

    /**
     * 添加供应商用户
     *
     * @param supplierSideDTO 数据对象
     * @param verifyCodeBySMS 短信验证码
     * @throws EqianyuanException
     */
    void add(SupplierSideDTO supplierSideDTO, String verifyCodeBySMS) throws EqianyuanException;

    /**
     * 供应商会员注册，发送手机短信验证码
     *
     * @param mobile           手机号码
     * @param verifyCodeByPage 页面验证码
     * @param verifyCodeLength 短信验证码内容长度
     * @throws EqianyuanException
     */
    void sendSMSVerificationCodeByRegister(String mobile, String verifyCodeByPage, Integer verifyCodeLength) throws EqianyuanException;

    /**
     * 供应商会员登录
     *
     * @param mobileNumber  手机号码
     * @param loginPassword 登录密码
     * @throws EqianyuanException
     */
    SupplierSideVOByLogin supplierLogin(String mobileNumber, String loginPassword) throws EqianyuanException;
}
