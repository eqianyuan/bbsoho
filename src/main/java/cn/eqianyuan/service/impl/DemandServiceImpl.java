package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.po.*;
import cn.eqianyuan.bean.request.SupplierHireRequest;
import cn.eqianyuan.bean.request.SupplierMeetRequest;
import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.bean.vo.DemandVOBySearchInfo;
import cn.eqianyuan.controller.convert.DemandConvert;
import cn.eqianyuan.controller.convert.SupplierSideConvert;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.*;
import cn.eqianyuan.service.IDemandService;
import cn.eqianyuan.service.convert.ServiceDemandConvert;
import cn.eqianyuan.util.CalendarUtil;
import cn.eqianyuan.util.RegexUtils;
import cn.eqianyuan.util.UserUtils;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016-09-06.
 */
@Service
public class DemandServiceImpl implements IDemandService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ServiceDemandConvert serviceDemandConvert;

    @Autowired
    private DemandConvert demandConvert;

    @Autowired
    private SupplierSideConvert supplierSideConvert;

    @Autowired
    private IDemandDao demandDao;

    @Autowired
    private ISignUpDao signUpDao;

    @Autowired
    private ISignUpMeetDao signUpMeetDao;

    @Autowired
    private IHireDao hireDao;

    @Autowired
    private IDemandEmployPersonsDao demandEmployPersonsDao;

    //需求名称DB许可字节长度
    private static final int DEMAND_NAME_MAX_BYTES_BY_DB = 100;
    //需求联系人DB许可字节长度
    private static final int DEMAND_CONTACT_MAX_BYTES_BY_DB = 20;
    //联系固话-区号DB许可字节长度
    private static final int DEMAND_PHONE_AREA_CODE_MAX_BYTES_BY_DB = 20;
    //联系固话-分机号DB许可字节长度
    private static final int DEMAND_EXTENSION_NUMBER_CODE_MAX_BYTES_BY_DB = 20;
    //详细地址DB许可字节长度
    private static final int DEMAND_ADDRESS_MAX_BYTES_BY_DB = 100;
    //工作描述DB许可字节长度
    private static final int DEMAND_DISCRIBE_MAX_BYTES_BY_DB = 6000;
    //企业详细地址DB许可字节长度
    private static final int SIGN_UP_MEET_ADDRESS_MAX_BYTES_BY_DB = 100;

    /**
     * 根据主键获取需求信息
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public DemandVOByInfo demandInfo(String id) throws EqianyuanException {
        if (StringUtils.isEmpty(id)) {
            logger.warn("demandInfo fail , because id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_QUERY_FAIL);
        }

        DemandPO demandPO = demandDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(demandPO) ||
                ObjectUtils.isEmpty(demandPO.getId())) {
            logger.warn("demandInfo fail , because query data by id [" + id + "] not exists");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_QUERY_FAIL);
        }

        //获取工作经历
        List<DemandEmployPersonsPO> demandEmployPersonsPOs = demandEmployPersonsDao.selectByDemandId(demandPO.getId());

        //将po转为dto
        DemandDTO demandDTO = serviceDemandConvert.demandInfo(demandPO);
        serviceDemandConvert.getEmployPersonsByDemandInfo(demandDTO, demandEmployPersonsPOs);

        DemandVOByInfo demandVOByInfo = demandConvert.demandInfo(demandDTO);
        return demandVOByInfo;
    }

    /**
     * 发布需求
     *
     * @param demandDTO
     * @throws EqianyuanException
     */
    @Transactional(rollbackFor = Exception.class)
    public void demandPublish(DemandDTO demandDTO) throws EqianyuanException {
        //校验需求名是否为空
        if (StringUtils.isEmpty(demandDTO.getName())) {
            logger.warn("demandPublish fail , because input name , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_NAME_IS_EMPTY);
        }

        //校验需求周期开始日期是否为空
        if (ObjectUtils.isEmpty(demandDTO.getBeginCycle())) {
            logger.warn("demandPublish fail , because input begin cycle , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_BEGIN_CYCLE_IS_EMPTY);
        }

        //校验需求周期结束日期是否为空
        if (ObjectUtils.isEmpty(demandDTO.getEndCycle())) {
            logger.warn("demandPublish fail , because input end cycle , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_END_CYCLE_IS_EMPTY);
        }

        //将周期开始日期转为秒数
        try {
            demandDTO.setBeginCycle(String.valueOf(CalendarUtil.getSecondsByDate(demandDTO.getBeginCycle())));
        } catch (Exception e) {
            logger.warn("demandPublish fail , because input begin cycle , the value format is not yyyy-MM-dd");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_BEGIN_CYCLE_FORMAT_IS_FAIL);
        }

        //将周期结束日期转为秒数
        try {
            demandDTO.setEndCycle(String.valueOf(CalendarUtil.getSecondsByDate(demandDTO.getEndCycle())));
        } catch (Exception e) {
            logger.warn("demandPublish fail , because input end cycle , the value format is not yyyy-MM-dd");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_END_CYCLE_FORMAT_IS_FAIL);
        }

        //检查需求商用户输入联系人是否为空
        if (StringUtils.isEmpty(demandDTO.getContact())) {
            logger.warn("demandPublish fail , because user input contact , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_COMPANY_CONTACT_IS_EMPTY);
        }

        //检查需求商用户输入联系人尊称是否为空
        if (ObjectUtils.isEmpty(demandDTO.getRespectfulName())) {
            logger.warn("demandPublish fail , because user input respectful name , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_COMPANY_RESPECTFUL_NAME_IS_EMPTY);
        }

        //检查需求商用户输入联电话-移动号码是否为空
        if (ObjectUtils.isEmpty(demandDTO.getMobileNumber())) {
            logger.warn("demandPublish fail , because user input mobile number , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_COMPANY_MOBILE_NUMBER_IS_EMPTY);
        }

        //检查需求商用户输入联系电话-移动号码是否正确
        if (!RegexUtils.isMobile(String.valueOf(demandDTO.getMobileNumber()))) {
            logger.warn("demandPublish fail , because user input mobile number is not right mobile number");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_COMPANY_MOBILE_NUMBER_IS_FAIL);
        }

        //检查需求名称内容长度是否超出DB许可长度
        try {
            if (demandDTO.getName().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_NAME_MAX_BYTES_BY_DB) {
                logger.info("demandPublish fail , because name [" + demandDTO.getName() + "] bytes greater than"
                        + DEMAND_NAME_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_NAME_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("demandPublish fail , because name [" + demandDTO.getName() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        //检查联系人内容长度是否超出DB许可长度
        try {
            if (demandDTO.getContact().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_CONTACT_MAX_BYTES_BY_DB) {
                logger.info("demandPublish fail , because contact [" + demandDTO.getContact() + "] bytes greater than"
                        + DEMAND_CONTACT_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_BASIC_INFORMATION_BY_COMPANY_CONTACT_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("demandPublish fail , because contact [" + demandDTO.getContact() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        /**
         * 检查联系电话-固话
         */
        {
            //检查固话区号是否正确
            if (!StringUtils.isEmpty(demandDTO.getPhoneAreaCode())) {
                //检查输入联系电话-固话号码-区号是否正确
                if (!RegexUtils.isDigital(demandDTO.getPhoneAreaCode())) {
                    logger.warn("demandPublish fail , because user input phone area code is not right number");
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_PHONE_AREA_CODE_IS_FAIL);
                }

                //检查联系电话-固话-区号内容长度是否超出DB许可长度
                try {
                    if (demandDTO.getPhoneAreaCode().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_PHONE_AREA_CODE_MAX_BYTES_BY_DB) {
                        logger.info("demandPublish fail , because phone area code [" + demandDTO.getPhoneAreaCode() + "] bytes greater than"
                                + DEMAND_PHONE_AREA_CODE_MAX_BYTES_BY_DB);
                        throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_PHONE_AREA_CODE_TO_LONG);
                    }
                } catch (UnsupportedEncodingException e) {
                    logger.info("demandPublish fail , because phone area code [" + demandDTO.getPhoneAreaCode() + "] getBytes("
                            + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
                }
            }

            //检查固话号码是否正确
            if (!ObjectUtils.isEmpty(demandDTO.getTelephoneNumber())) {
                //检查输入联系电话-固话号码是否正确
                if (!RegexUtils.isDigital(String.valueOf(demandDTO.getTelephoneNumber()))) {
                    logger.warn("demandPublish fail , because telephone number is not right number");
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_TELEPHONE_NUMBER_IS_FAIL);
                }
            }

            //检查固话分机号是否正确
            if (!StringUtils.isEmpty(demandDTO.getExtensionNumber())) {
                //检查输入联系电话-固话号码-分机号是否正确
                if (!RegexUtils.isDigital(demandDTO.getExtensionNumber())) {
                    logger.warn("demandPublish fail , because user input extension number is not right number");
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_EXTENSION_NUMBER_IS_FAIL);
                }

                //检查固话分机号内容长度是否超出DB许可长度
                try {
                    if (demandDTO.getExtensionNumber().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_EXTENSION_NUMBER_CODE_MAX_BYTES_BY_DB) {
                        logger.info("demandPublish fail , because extension number [" + demandDTO.getExtensionNumber() + "] bytes greater than"
                                + DEMAND_EXTENSION_NUMBER_CODE_MAX_BYTES_BY_DB);
                        throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_EXTENSION_NUMBER_TO_LONG);
                    }
                } catch (UnsupportedEncodingException e) {
                    logger.info("demandPublish fail , because extension number [" + demandDTO.getExtensionNumber() + "] getBytes("
                            + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
                }
            }
        }

        //检查用户输入工作内容是否为空
        if (!StringUtils.isEmpty(demandDTO.getDemandDiscribe())) {
            //检查工作内容长度是否超出DB许可长度
            try {
                if (demandDTO.getDemandDiscribe().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_DISCRIBE_MAX_BYTES_BY_DB) {
                    logger.info("demandPublish fail , because demand discribe [" + demandDTO.getDemandDiscribe() + "] bytes greater than"
                            + DEMAND_DISCRIBE_MAX_BYTES_BY_DB);
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_DISCRIBE_TO_LONG);
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("demandPublish fail , because demand discribe [" + demandDTO.getDemandDiscribe() + "] getBytes("
                        + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
            }
        }

        //检查用户输入详细地址是否为空
        if (!StringUtils.isEmpty(demandDTO.getAddress())) {
            //检查地址内容长度是否超出DB许可长度
            try {
                if (demandDTO.getAddress().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_ADDRESS_MAX_BYTES_BY_DB) {
                    logger.info("demandPublish fail , because address name [" + demandDTO.getAddress() + "] bytes greater than"
                            + DEMAND_ADDRESS_MAX_BYTES_BY_DB);
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_ADDRESS_TO_LONG);
                }
            } catch (UnsupportedEncodingException e) {
                logger.info("demandPublish fail , because address [" + demandDTO.getAddress() + "] getBytes("
                        + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
            }
        }

        //检查需求是否有用人
        if (CollectionUtils.isEmpty(demandDTO.getDemandEmployPersonsDTOList())) {
            logger.info("demandPublish fail , because demand employ persons is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_EMPLOY_PERSONS_IS_EMPTY);
        }

        DemandPO demandPO = new DemandPO();
        //获取session用户
        DemandSideVOByLogin demandSideVOByLogin = UserUtils.getDemandSideUserBySession();

        if (StringUtils.isEmpty(demandDTO.getId())) {
            //将dto转为po
            BeanUtils.copyProperties(demandDTO, demandPO);

            demandPO.setDemandSideId(demandSideVOByLogin.getId());
            demandPO.setPublishTime(CalendarUtil.getSystemSeconds());
            demandPO.setBeginCycle(Integer.parseInt(demandDTO.getBeginCycle()));
            demandPO.setEndCycle(Integer.parseInt(demandDTO.getEndCycle()));
            demandDao.insertSelective(demandPO);
        } else {
            demandPO = demandDao.selectByPrimaryKey(demandDTO.getId());

            if (ObjectUtils.isEmpty(demandPO) ||
                    ObjectUtils.isEmpty(demandPO.getId())) {
                logger.warn("demandPublish fail , because id [" + demandPO.getId() + "] query data is not exists");
                throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_LOGIN_BY_ACCOUNT_ERROR);
            }

            //将dto转为po
            BeanUtils.copyProperties(demandDTO, demandPO);
            demandPO.setDemandSideId(demandSideVOByLogin.getId());

            demandDao.updateByPrimaryKeySelective(demandPO);
            //先删除需求用人要求数据
            demandEmployPersonsDao.deleteByDemandId(demandDTO.getId());
        }

        if (!CollectionUtils.isEmpty(demandDTO.getDemandEmployPersonsDTOList())) {
            List<DemandEmployPersonsPO> demandEmployPersonsPOs = new ArrayList<DemandEmployPersonsPO>();
            for (DemandDTO.DemandEmployPersonsDTO demandEmployPersonsDTO : demandDTO.getDemandEmployPersonsDTOList()) {
                if (!ObjectUtils.isEmpty(demandEmployPersonsDTO.getPersonsAmount())) {
                    if (!RegexUtils.isDigital(String.valueOf(demandEmployPersonsDTO.getPersonsAmount()))) {
                        continue;
                    }
                }

                DemandEmployPersonsPO demandEmployPersonsPO = new DemandEmployPersonsPO();
                BeanUtils.copyProperties(demandEmployPersonsDTO, demandEmployPersonsPO);
                demandEmployPersonsPO.setDemandId(demandPO.getId());

                demandEmployPersonsPOs.add(demandEmployPersonsPO);
            }
            //持久化用人需要
            demandEmployPersonsDao.insertByList(demandEmployPersonsPOs);
        }
    }

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @param page
     * @param demandByListSearchDTO
     * @return
     */
    public PageResponse demandList(Page page, DemandByListSearchDTO demandByListSearchDTO) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = demandDao.countByPagination(demandByListSearchDTO);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<DemandPOBySearchList> demandPOBySearchLists = demandDao.selectByPagination(page, demandByListSearchDTO);
        if (CollectionUtils.isEmpty(demandPOBySearchLists)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        List<DemandByListSearchDTO> demandDTOs = new ArrayList<DemandByListSearchDTO>();
        for (DemandPOBySearchList demandPOBySearchList : demandPOBySearchLists) {
            demandByListSearchDTO = new DemandByListSearchDTO();
            BeanUtils.copyProperties(demandPOBySearchList, demandByListSearchDTO);
            demandDTOs.add(demandByListSearchDTO);
        }

        return new PageResponse(page, demandConvert.demandList(demandDTOs));
    }

    /**
     * 根据需求主键查询详细数据，且字典数据已转码
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public DemandVOBySearchInfo demandInfoBySearch(String id) throws EqianyuanException {
        if (StringUtils.isEmpty(id)) {
            logger.warn("demandInfoBySearch fail , because id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_QUERY_FAIL);
        }

        DemandPO demandPO = demandDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(demandPO) ||
                ObjectUtils.isEmpty(demandPO.getId())) {
            logger.warn("demandInfoBySearch fail , because query data by id [" + id + "] not exists");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_USER_DEMAND_BY_QUERY_FAIL);
        }

        //获取用人需求
        List<DemandEmployPersonsPO> demandEmployPersonsPOs = demandEmployPersonsDao.selectByDemandId(demandPO.getId());

        //将po转为dto
        DemandDTO demandDTO = serviceDemandConvert.demandInfo(demandPO);
        serviceDemandConvert.getEmployPersonsByDemandInfo(demandDTO, demandEmployPersonsPOs);

        DemandVOBySearchInfo demandVOBySearchInfo = demandConvert.demandInfoBySearch(demandDTO);
        return demandVOBySearchInfo;
    }

    /**
     * 根据需求状态及分页条件获取分页数据集合
     *
     * @param page
     * @param isEnd
     * @return
     * @throws EqianyuanException
     */
    public PageResponse demandListByMine(Page page, String isEnd, String demandSideId) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = demandDao.countByMinePagination(isEnd, demandSideId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<DemandPOBySearchList> demandPOBySearchLists = demandDao.selectByMinePagination(page, isEnd, demandSideId);
        if (CollectionUtils.isEmpty(demandPOBySearchLists)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        List<DemandByListSearchDTO> demandDTOs = new ArrayList<DemandByListSearchDTO>();
        for (DemandPOBySearchList demandPOBySearchList : demandPOBySearchLists) {
            DemandByListSearchDTO demandByListSearchDTO = new DemandByListSearchDTO();
            BeanUtils.copyProperties(demandPOBySearchList, demandByListSearchDTO);
            demandDTOs.add(demandByListSearchDTO);
        }

        return new PageResponse(page, demandConvert.demandListByMine(demandDTOs));
    }

    /**
     * 查询需求中已报名的人员信息
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     * @throws EqianyuanException
     */
    public PageResponse signUpByDemand(String demandSideId, String demandId, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = signUpDao.countByDemandId(demandSideId, demandId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<DemandSignUpSupplierPO> demandPOBySearchLists = signUpDao.selectByDemandPagination(demandSideId, demandId, page);

        if (CollectionUtils.isEmpty(demandPOBySearchLists)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        return new PageResponse(page, supplierSideConvert.demandSignUpSupplier(demandPOBySearchLists));
    }

    /**
     * 查询需求中已约见的人员信息
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     * @throws EqianyuanException
     */
    public PageResponse signUpMeetByDemand(String demandSideId, String demandId, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = signUpMeetDao.countByDemandId(demandSideId, demandId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<DemandSignUpMeetSupplierPO> demandSignUpMeetSupplierPOs = signUpMeetDao.selectByDemandPagination(demandSideId, demandId, page);

        if (CollectionUtils.isEmpty(demandSignUpMeetSupplierPOs)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        return new PageResponse(page, supplierSideConvert.demandSignUpMeetSupplier(demandSignUpMeetSupplierPOs));
    }

    /**
     * 查询需求中已聘用的人员信息
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     * @throws EqianyuanException
     */
    public PageResponse hireByDemand(String demandSideId, String demandId, Page page) throws EqianyuanException {
        //根据条件查询数据条数
        Integer rowCount = hireDao.countByDemandId(demandSideId, demandId);

        page.setTotalRow(rowCount);
        if (ObjectUtils.isEmpty(rowCount) || rowCount == 0) {
            logger.info("get total count is null");
            return new PageResponse(page, null);
        }

        List<DemandHireSupplierPO> demandHireSupplierPOs = hireDao.selectByDemandPagination(demandSideId, demandId, page);

        if (CollectionUtils.isEmpty(demandHireSupplierPOs)) {
            logger.info("pageNo [" + page.getPageNo() + "], pageSize [" + page.getPageSize() + "], get list is null");
            return new PageResponse(page, null);
        }

        return new PageResponse(page, supplierSideConvert.demandHireSupplier(demandHireSupplierPOs));
    }

    /**
     * 需求报名供应商约见
     *
     * @param supplierMeetRequest
     * @throws EqianyuanException
     */
    @Transactional(rollbackFor = Exception.class)
    public void signUpMeet(SupplierMeetRequest supplierMeetRequest) throws EqianyuanException {
        if (StringUtils.isEmpty(supplierMeetRequest.getDemandId())) {
            logger.warn("signUpMeet fail , because demand id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_FAIL);
        }

        if (StringUtils.isEmpty(supplierMeetRequest.getSupplierSideId())) {
            logger.warn("signUpMeet fail , because supplier side id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_FAIL);
        }

        //检查户输入约见时间是否为空
        if (StringUtils.isEmpty(supplierMeetRequest.getMeetTime())) {
            logger.warn("signUpMeet fail , because user input meet time , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_TIME_IS_EMPTY);
        }

        //检查约见时间是否为yyyy-MM-dd HH:mm:ss格式字符串
        try {
            supplierMeetRequest.setMeetTime(String.valueOf(CalendarUtil.parseDate(supplierMeetRequest.getMeetTime(), CalendarUtil.Format_DateTime).getTime() / 1000));
        } catch (Exception e) {
            logger.warn("signUpMeet fail , because user input meet time , value is not dateTime format");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_FAIL);
        }

        //检查需求商用户输入公司详细地址是否为空
        if (StringUtils.isEmpty(supplierMeetRequest.getAddress())) {
            logger.warn("signUpMeet fail , because user input address , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_ADDRESS_IS_EMPTY);
        }

        //检查需求商用户输入联系人是否为空
        if (StringUtils.isEmpty(supplierMeetRequest.getContact())) {
            logger.warn("signUpMeet fail , because user input contact , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_CONTACT_IS_EMPTY);
        }

        //检查需求商用户输入联系人尊称是否为空
        if (ObjectUtils.isEmpty(supplierMeetRequest.getRespectfulName())) {
            logger.warn("signUpMeet fail , because user input respectful name , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_RESPECTFULNAME_IS_EMPTY);
        }

        //检查需求商用户输入联电话-移动号码是否为空
        if (ObjectUtils.isEmpty(supplierMeetRequest.getMobileNumber())) {
            logger.warn("signUpMeet fail , because user input mobile number , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_MOBILE_IS_EMPTY);
        }

        //检查需求商用户输入联系电话-移动号码是否正确
        if (!RegexUtils.isMobile(String.valueOf(supplierMeetRequest.getMobileNumber()))) {
            logger.warn("signUpMeet fail , because user input mobile number is not right mobile number");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_MOBILE_NUMBER_IS_FAIL);
        }

        //构建报名用户约见数据对象
        SignUpMeetPO signUpMeetPO = new SignUpMeetPO();

        /**
         * 检查联系电话-固话
         */
        {
            //检查固话区号是否正确
            if (!StringUtils.isEmpty(supplierMeetRequest.getPhoneAreaCode())) {
                //检查需求商用户输入联系电话-固话号码-区号是否正确
                if (!RegexUtils.isDigital(supplierMeetRequest.getPhoneAreaCode())) {
                    logger.warn("signUpMeet fail , because user input phone area code is not right number");
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_COMPANY_PHONE_AREA_CODE_IS_FAIL);
                }

                //检查企业联系电话-固话-区号内容长度是否超出DB许可长度
                try {
                    if (supplierMeetRequest.getPhoneAreaCode().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_PHONE_AREA_CODE_MAX_BYTES_BY_DB) {
                        logger.info("signUpMeet fail , because phone area code [" + supplierMeetRequest.getPhoneAreaCode() + "] bytes greater than"
                                + DEMAND_PHONE_AREA_CODE_MAX_BYTES_BY_DB);
                        throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_COMPANY_PHONE_AREA_CODE_TO_LONG);
                    }
                } catch (UnsupportedEncodingException e) {
                    logger.info("signUpMeet fail , because phone area code [" + supplierMeetRequest.getPhoneAreaCode() + "] getBytes("
                            + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
                }
                signUpMeetPO.setPhoneAreaCode(supplierMeetRequest.getPhoneAreaCode());
            }

            //检查固话号码是否正确
            if (!ObjectUtils.isEmpty(supplierMeetRequest.getTelephoneNumber())) {
                //检查需求商用户输入联系电话-固话号码是否正确
                if (!RegexUtils.isDigital(String.valueOf(supplierMeetRequest.getTelephoneNumber()))) {
                    logger.warn("signUpMeet fail , because user input telephone number is not right number");
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_COMPANY_TELEPHONE_NUMBER_IS_FAIL);
                }
                signUpMeetPO.setTelephoneNumber(Integer.parseInt(supplierMeetRequest.getTelephoneNumber()));
            }

            //检查固话分机号是否正确
            if (!StringUtils.isEmpty(supplierMeetRequest.getExtensionNumber())) {
                //检查需求商用户输入联系电话-固话号码-分机号是否正确
                if (!RegexUtils.isDigital(supplierMeetRequest.getExtensionNumber())) {
                    logger.warn("signUpMeet fail , because user input extension number is not right number");
                    throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_COMPANY_EXTENSION_NUMBER_IS_FAIL);
                }

                //检查企业固话分机号内容长度是否超出DB许可长度
                try {
                    if (supplierMeetRequest.getExtensionNumber().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_EXTENSION_NUMBER_CODE_MAX_BYTES_BY_DB) {
                        logger.info("signUpMeet fail , because extension number [" + supplierMeetRequest.getExtensionNumber() + "] bytes greater than"
                                + DEMAND_EXTENSION_NUMBER_CODE_MAX_BYTES_BY_DB);
                        throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_COMPANY_EXTENSION_NUMBER_TO_LONG);
                    }
                } catch (UnsupportedEncodingException e) {
                    logger.info("signUpMeet fail , because extension number [" + supplierMeetRequest.getExtensionNumber() + "] getBytes("
                            + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
                    throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
                }

                signUpMeetPO.setExtensionNumber(supplierMeetRequest.getExtensionNumber());
            }
        }

        //检查联系人内容长度是否超出DB许可长度
        try {
            if (supplierMeetRequest.getContact().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > DEMAND_CONTACT_MAX_BYTES_BY_DB) {
                logger.info("signUpMeet fail , because contact [" + supplierMeetRequest.getContact() + "] bytes greater than"
                        + DEMAND_CONTACT_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_COMPANY_CONTACT_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("signUpMeet fail , because contact [" + supplierMeetRequest.getContact() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        //检查企业地址内容长度是否超出DB许可长度
        try {
            if (supplierMeetRequest.getAddress().getBytes(SystemConf.PLATFORM_CHARSET.toString()).length > SIGN_UP_MEET_ADDRESS_MAX_BYTES_BY_DB) {
                logger.info("signUpMeet fail , because address name [" + supplierMeetRequest.getAddress() + "] bytes greater than"
                        + SIGN_UP_MEET_ADDRESS_MAX_BYTES_BY_DB);
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_BY_ADDRESS_TO_LONG);
            }
        } catch (UnsupportedEncodingException e) {
            logger.info("signUpMeet fail , because address [" + supplierMeetRequest.getAddress() + "] getBytes("
                    + SystemConf.PLATFORM_CHARSET.toString() + ") fail");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_GET_BYTE_FAIL);
        }

        //根据需求编号查询需求数据
        DemandPO demandPO = demandDao.selectByPrimaryKey(supplierMeetRequest.getDemandId());
        if (ObjectUtils.isEmpty(demandPO) ||
                ObjectUtils.isEmpty(demandPO.getId())) {
            logger.warn("signUpMeet fail , because demand id [" + supplierMeetRequest.getDemandId() + "] query data is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_FAIL);
        }

        //根据供应商编号查询供应商报名数据
        SignUpPO signUpPO = signUpDao.selectBySupplierSideId(supplierMeetRequest.getDemandId(), supplierMeetRequest.getSupplierSideId());
        if (ObjectUtils.isEmpty(signUpPO) ||
                ObjectUtils.isEmpty(signUpPO.getId())) {
            logger.warn("signUpMeet fail , because supplier side id [" + supplierMeetRequest.getSupplierSideId() + "] query data by table [sign_up] is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_FAIL);
        }

        signUpMeetPO.setDemandId(supplierMeetRequest.getDemandId());
        signUpMeetPO.setSupplierSideId(supplierMeetRequest.getSupplierSideId());
        signUpMeetPO.setWork(signUpPO.getWork());
        signUpMeetPO.setMeetTime(Integer.parseInt(supplierMeetRequest.getMeetTime()));
        signUpMeetPO.setAddress(supplierMeetRequest.getAddress());
        signUpMeetPO.setContact(supplierMeetRequest.getContact());
        signUpMeetPO.setRespectfulName(supplierMeetRequest.getRespectfulName());
        signUpMeetPO.setMobileNumber(Long.parseLong(supplierMeetRequest.getMobileNumber()));
        signUpMeetPO.setCreateTime(CalendarUtil.getSystemSeconds());
        //添加新约见数据
        signUpMeetDao.insertSelective(signUpMeetPO);

        //将报名数据复制到报名历史表中
        signUpDao.copyInsertHistory(signUpPO);

        //删除报名数据
        signUpDao.deleteByPrimaryKey(signUpPO.getId());
    }

    /**
     * 聘用供应商
     *
     * @param supplierHireRequest
     * @throws EqianyuanException
     */
    @Transactional(rollbackFor = Exception.class)
    public void hire(SupplierHireRequest supplierHireRequest) throws EqianyuanException {
        if (StringUtils.isEmpty(supplierHireRequest.getDemandId())) {
            logger.warn("hire fail , because demand id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_FAIL);
        }

        if (StringUtils.isEmpty(supplierHireRequest.getSupplierSideId())) {
            logger.warn("hire fail , because supplier side id is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_FAIL);
        }

        //根据供应商编号查询供应商约见数据
        SignUpMeetPO signUpMeetPO = signUpMeetDao.selectMeetInfo(supplierHireRequest.getDemandId(), supplierHireRequest.getSupplierSideId());
        if (ObjectUtils.isEmpty(signUpMeetPO) ||
                ObjectUtils.isEmpty(signUpMeetPO.getId())) {
            logger.warn("hire fail , because demand id [" + supplierHireRequest.getDemandId() + "], supplier side id [" + supplierHireRequest.getSupplierSideId() + "] query data by table [sign_up_meet] is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_FAIL);
        }

        //判断聘用状态是否有值，没有值，则认为是聘用操作
        if (StringUtils.isEmpty(supplierHireRequest.getStatus())) {
            //检查合同生效时间是否为空
            if (StringUtils.isEmpty(supplierHireRequest.getContractComesIntoEffectTime())) {
                logger.warn("hire fail , because user input contract comes into effect time , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_CONTRACT_COMES_INTO_EFFECT_TIME_IS_EMPTY);
            }

            //检查合同失效时间是否为空
            if (StringUtils.isEmpty(supplierHireRequest.getContractExpiresTime())) {
                logger.warn("hire fail , because user input contract expires time , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_CONTRACT_EXPIRES_TIME_IS_EMPTY);
            }

            //检查合同生效时间是否为yyyy-MM-dd HH:mm:ss格式字符串
            try {
                supplierHireRequest.setContractComesIntoEffectTime(String.valueOf(CalendarUtil.parseDate(supplierHireRequest.getContractComesIntoEffectTime(), CalendarUtil.Format_DateTime).getTime() / 1000));
            } catch (Exception e) {
                logger.warn("hire fail , because user input contract comes into effect time , value is not dateTime format");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_FAIL);
            }

            //检查合同生效时间是否为yyyy-MM-dd HH:mm:ss格式字符串
            try {
                supplierHireRequest.setContractExpiresTime(String.valueOf(CalendarUtil.parseDate(supplierHireRequest.getContractExpiresTime(), CalendarUtil.Format_DateTime).getTime() / 1000));
            } catch (Exception e) {
                logger.warn("hire fail , because user input contract expires time , value is not dateTime format");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_FAIL);
            }

            //检查薪资报酬是否为空
            if (ObjectUtils.isEmpty(supplierHireRequest.getRemuneration())) {
                logger.warn("hire fail , because user input remuneration , value is empty");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_REMUNERATION_IS_EMPTY);
            }

            //检查薪资报酬是否为正确数字
            if (!RegexUtils.isDigital(supplierHireRequest.getRemuneration())) {
                logger.warn("hire fail , because user input remuneration , value is not right number");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_MEET_REMUNERATION_IS_NOT_MONEY);
            }

            //根据需求编号查询需求数据
            DemandPO demandPO = demandDao.selectByPrimaryKey(supplierHireRequest.getDemandId());
            if (ObjectUtils.isEmpty(demandPO) ||
                    ObjectUtils.isEmpty(demandPO.getId())) {
                logger.warn("hire fail , because demand id [" + supplierHireRequest.getDemandId() + "] query data is null");
                throw new EqianyuanException(ExceptionMsgConstant.DEMAND_HIRE_FAIL);
            }

            //构建供应商聘用数据对象
            HirePO hirePO = new HirePO();
            hirePO.setDemandId(supplierHireRequest.getDemandId());
            hirePO.setSupplierSideId(supplierHireRequest.getSupplierSideId());
            hirePO.setWork(signUpMeetPO.getWork());
            hirePO.setContractComesIntoEffectTime(Integer.parseInt(supplierHireRequest.getContractComesIntoEffectTime()));
            hirePO.setContractExpiresTime(Integer.parseInt(supplierHireRequest.getContractExpiresTime()));
            hirePO.setRemuneration(Integer.parseInt(supplierHireRequest.getRemuneration()));
            hirePO.setCreateTime(CalendarUtil.getSystemSeconds());
            //添加新聘用数据
            hireDao.insertSelective(hirePO);

            //将约见数据复制到约见历史表中
            signUpMeetDao.copyInsertHistory(signUpMeetPO);

            //删除约见数据
            signUpMeetDao.deleteByPrimaryKey(signUpMeetPO.getId());
        } else {
            //修改约见数据状态为不聘用：3
            signUpMeetPO.setStatus(3);
            signUpMeetDao.updateByPrimaryKeySelective(signUpMeetPO);

            //将约见数据复制到约见历史表中
            signUpMeetDao.copyInsertHistory(signUpMeetPO);

            //删除约见数据
            signUpMeetDao.deleteByPrimaryKey(signUpMeetPO.getId());
        }
    }

    /**
     * 需求岗位报名通道关闭或开启
     *
     * @param channelId 通道编号
     * @param operator  1：开启、2：关闭
     * @throws EqianyuanException
     */
    public void demandSignUpChannel(String channelId, String operator) throws EqianyuanException {
        if (StringUtils.isEmpty(channelId)) {
            logger.warn("closeSignUp fail , because channelId is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_SIGN_UP_BY_WORK_CLOSE_FAIL);
        }

        //根据需求用人岗位编号查询数据
        DemandEmployPersonsPO demandEmployPersonsPO = demandEmployPersonsDao.selectByPrimaryKey(Integer.parseInt(channelId));
        if (ObjectUtils.isEmpty(demandEmployPersonsPO) ||
                ObjectUtils.isEmpty(demandEmployPersonsPO.getId())) {
            logger.warn("closeSignUp fail , because channelId [" + channelId + "] query data by table [demand_employ_persons] is null");
            throw new EqianyuanException(ExceptionMsgConstant.DEMAND_SIGN_UP_BY_WORK_CLOSE_FAIL);
        }

        //关闭报名数据：更改是否关闭报名字段数据
        demandEmployPersonsPO.setChannelWhetherClose(Integer.parseInt(operator));
        demandEmployPersonsDao.updateByPrimaryKeySelective(demandEmployPersonsPO);
    }

}
