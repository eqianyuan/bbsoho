package cn.eqianyuan.service;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.dto.SupplierSideBasicInfoDTO;
import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.request.SupplierSearchListByRequest;
import cn.eqianyuan.bean.vo.*;
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

    /**
     * 供应商账户基本信息是否已经完善
     *
     * @param mobile
     * @return
     */
    boolean isIntegrity(String mobile) throws EqianyuanException;

    /**
     * 需求报名
     *
     * @param demandId   需求编号
     * @param supplierId 供应方用户编号
     * @param work       报名岗位
     * @throws EqianyuanException
     */
    void signUp(String demandId, String supplierId, String work) throws EqianyuanException;

    /**
     * 查询供应商报名需求分页
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     * @throws EqianyuanException
     */
    PageResponse signUpDemand(String supplierId, Page page) throws EqianyuanException;

    /**
     * 查询供应商约见需求分页
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     * @throws EqianyuanException
     */
    PageResponse signUpMeetDemand(String supplierId, Page page) throws EqianyuanException;

    /**
     * 查询供应商聘用需求分页
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     * @throws EqianyuanException
     */
    PageResponse hireDemand(String supplierId, Page page) throws EqianyuanException;

    /**
     * 查询需求商需求约见信息
     *
     * @param demandId       需求编号
     * @param supplierSideId 供应商编号
     * @throws EqianyuanException
     */
    DemandMeetInfoVO demandMeetInfo(String demandId, String supplierSideId) throws EqianyuanException;

    /**
     * 约见需求处理（同意约见、拒绝约见）
     *
     * @param demandId       需求编号
     * @param supplierSideId 供应商编号
     * @param status         处理状态
     * @throws EqianyuanException
     */
    void demandMeetDispose(String demandId, String supplierSideId, Integer status) throws EqianyuanException;

    /**
     * 需求聘用处理（同意聘用、拒绝聘用）
     *
     * @param demandId       需求编号
     * @param supplierSideId 供应商编号
     * @param status         处理状态
     * @throws EqianyuanException
     */
    void demandHireDispose(String demandId, String supplierSideId, Integer status) throws EqianyuanException;

    /**
     * 需求聘用合同信息查询
     *
     * @param demandId       需求编号
     * @param supplierSideId 供应商编号
     * @return
     * @throws EqianyuanException
     */
    SupplierContractInfoVO demandContractInfo(String demandId, String supplierSideId) throws EqianyuanException;
}
