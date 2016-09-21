package cn.eqianyuan.service;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.dto.SupplierSideBasicInfoDTO;
import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.request.SupplierSearchListByRequest;
import cn.eqianyuan.bean.vo.SupplierSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.bean.vo.SupplierSideVOByResume;
import cn.eqianyuan.core.exception.EqianyuanException;

/**
 * Created by jason on 2016-08-10.
 */
public interface ISupplierSideService {

    /**
     * 添加供应商用户
     *
     * @param supplierSideBasicInfoDTO 数据对象
     * @param verifyCodeBySMS          短信验证码
     * @throws EqianyuanException
     */
    void add(SupplierSideBasicInfoDTO supplierSideBasicInfoDTO, String verifyCodeBySMS) throws EqianyuanException;

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

    /**
     * 供应商用户登出
     */
    void supplierLogout();

    /**
     * 获取供应商基本信息
     *
     * @return
     * @throws EqianyuanException
     */
    SupplierSideVOByBasicInfo getBasicInformation() throws EqianyuanException;

    /**
     * 供应商基本信息编辑
     *
     * @param supplierSideBasicInfoDTO
     * @throws EqianyuanException
     */
    void modifyBasicInformation(SupplierSideBasicInfoDTO supplierSideBasicInfoDTO) throws EqianyuanException;

    /**
     * 获取供应商简历信息
     *
     * @return
     * @throws EqianyuanException
     */
    SupplierSideVOByResume getResume() throws EqianyuanException;

    /**
     * 供应商简历信息编辑
     *
     * @param supplierSideResumeDTO
     * @throws EqianyuanException
     */
    void modifyResume(SupplierSideResumeDTO supplierSideResumeDTO) throws EqianyuanException;

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @param supplierSearchListByRequest
     * @param page
     * @return
     * @throws EqianyuanException
     */
    PageResponse supplierList(SupplierSearchListByRequest supplierSearchListByRequest, Page page) throws EqianyuanException;

}
