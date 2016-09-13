package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.po.DemandEmployPersonsPO;
import cn.eqianyuan.bean.po.DemandPO;
import cn.eqianyuan.bean.po.DemandPOBySearchList;
import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.controller.convert.DemandConvert;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.IDemandDao;
import cn.eqianyuan.dao.IDemandEmployPersonsDao;
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
    private IDemandDao demandDao;

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

//            if (ObjectUtils.isEmpty(demandPO.getTelephoneNumber())) {
//                demandPO.setTelephoneNumber(0);
//            }

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
}
