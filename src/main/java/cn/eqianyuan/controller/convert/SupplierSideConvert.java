package cn.eqianyuan.controller.convert;

import cn.eqianyuan.bean.dto.SupplierByListSearchDTO;
import cn.eqianyuan.bean.dto.SupplierSideBasicInfoDTO;
import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.po.*;
import cn.eqianyuan.bean.vo.*;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.ICityDao;
import cn.eqianyuan.dao.ICountyDao;
import cn.eqianyuan.dao.IProvinceDao;
import cn.eqianyuan.listener.InitialData;
import cn.eqianyuan.util.CalendarUtil;
import cn.eqianyuan.util.yamlMapper.DataDictionaryConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 供应商PO转化VO对象处理类
 * Created by jason on 2016-08-11.
 */
@Component
public class SupplierSideConvert {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IProvinceDao provinceDao;

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private ICountyDao countyDao;

    /**
     * 将供应商对象PO转为VO对象
     *
     * @param supplierSidePO
     * @return
     */
    public SupplierSideVOByLogin supplierLogin(SupplierSidePO supplierSidePO) {
        SupplierSideVOByLogin supplierSideVOByLogin = new SupplierSideVOByLogin();
        BeanUtils.copyProperties(supplierSidePO, supplierSideVOByLogin);
        return supplierSideVOByLogin;
    }

    /**
     * 将供应商对象PO转为VO对象
     *
     * @param supplierSidePO
     * @return
     */
    public SupplierSideVOByBasicInfo getBasicInformation(SupplierSidePO supplierSidePO) {
        SupplierSideVOByBasicInfo supplierSideVOByBasicInfo = new SupplierSideVOByBasicInfo();
        BeanUtils.copyProperties(supplierSidePO, supplierSideVOByBasicInfo);
        return supplierSideVOByBasicInfo;
    }

    /**
     * 将供应商简历DTO转为VO对象
     *
     * @param supplierSideResumeDTO
     * @return
     */
    public SupplierSideVOByResume getResume(SupplierSideResumeDTO supplierSideResumeDTO) {
        SupplierSideVOByResume supplierSideVOByResume = new SupplierSideVOByResume();
        BeanUtils.copyProperties(supplierSideResumeDTO, supplierSideVOByResume);

        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkProficiencyDTOList())) {
            List<SupplierSideVOByResume.WorkProficiency> workProficiencies = new ArrayList<SupplierSideVOByResume.WorkProficiency>();
            for (WorkProficiencyPO workProficiencyPO : supplierSideResumeDTO.getWorkProficiencyDTOList()) {
                SupplierSideVOByResume.WorkProficiency workProficiency = new SupplierSideVOByResume().new WorkProficiency();
                BeanUtils.copyProperties(workProficiencyPO, workProficiency);
                workProficiencies.add(workProficiency);
            }
            supplierSideVOByResume.setWorkProficiencies(workProficiencies);
        }

        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkExperienceDTOList())) {
            List<SupplierSideVOByResume.WorkExperience> workExperiences = new ArrayList<SupplierSideVOByResume.WorkExperience>();
            for (WorkExperiencePO workExperiencePO : supplierSideResumeDTO.getWorkExperienceDTOList()) {
                SupplierSideVOByResume.WorkExperience workExperience = new SupplierSideVOByResume().new WorkExperience();
                BeanUtils.copyProperties(workExperiencePO, workExperience);
                workExperiences.add(workExperience);
            }
            supplierSideVOByResume.setWorkExperiences(workExperiences);
        }

        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getProjectExperienceDTOList())) {
            List<SupplierSideVOByResume.ProjectExperience> projectExperiences = new ArrayList<SupplierSideVOByResume.ProjectExperience>();
            for (ProjectExperiencePO projectExperiencePO : supplierSideResumeDTO.getProjectExperienceDTOList()) {
                SupplierSideVOByResume.ProjectExperience projectExperience = new SupplierSideVOByResume().new ProjectExperience();
                BeanUtils.copyProperties(projectExperiencePO, projectExperience);
                projectExperiences.add(projectExperience);
            }
            supplierSideVOByResume.setProjectExperiences(projectExperiences);
        }

        return supplierSideVOByResume;
    }

    /**
     * 将人才库DTO对象转为VO对象
     *
     * @return
     */
    public List<SupplierVOByListSearch> supplierList(List<SupplierByListSearchDTO> supplierByListSearchDTOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(supplierByListSearchDTOs)) {
            return null;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("supplierList fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工龄集合
        List<DataDictionaryPO> workingYearsDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
        if (CollectionUtils.isEmpty(workingYearsDictionary)) {
            logger.warn("supplierList fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取薪资报酬集合
        List<DataDictionaryPO> remunerationDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(remunerationDictionary)) {
            logger.warn("supplierList fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工作时间段集合
        List<DataDictionaryPO> expectWorkTimeDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_WORK_TIME.toString());
        if (CollectionUtils.isEmpty(expectWorkTimeDictionary)) {
            logger.warn("supplierList fail , because group key [" + DataDictionaryConf.EXPECT_WORK_TIME.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<SupplierVOByListSearch> supplierVOByListSearches = new ArrayList<SupplierVOByListSearch>();

        for (SupplierByListSearchDTO supplierByListSearchDTO : supplierByListSearchDTOs) {
            SupplierVOByListSearch supplierVOByListSearch = new SupplierVOByListSearch();
            BeanUtils.copyProperties(supplierByListSearchDTO, supplierVOByListSearch);

            if (!StringUtils.isEmpty(supplierByListSearchDTO.getWork())) {
                List<String> workList = new ArrayList<String>();
                for (String workKey : supplierByListSearchDTO.getWork().split(",")) {
                    //转换工种数据
                    for (DataDictionaryPO work : workDictionary) {
                        if (StringUtils.equals(workKey, work.getGroupValKey())) {
                            workList.add(work.getGroupValVal());
                            break;
                        }
                    }
                }
                supplierVOByListSearch.setWork(StringUtils.join(workList, ","));
            }

            //转换工龄数据
            for (DataDictionaryPO workingYears : workingYearsDictionary) {
                if (StringUtils.equals(String.valueOf(supplierByListSearchDTO.getWorkingYears()), workingYears.getGroupValKey())) {
                    supplierVOByListSearch.setWorkingYears(workingYears.getGroupValVal());
                    break;
                }
            }
            //转换薪资报酬数据
            for (DataDictionaryPO remuneration : remunerationDictionary) {
                if (StringUtils.equals(String.valueOf(supplierByListSearchDTO.getExpectPay()), remuneration.getGroupValKey())) {
                    supplierVOByListSearch.setExpectPay(remuneration.getGroupValVal());
                    break;
                }
            }
            //转换工作时间段数据
            for (DataDictionaryPO workingLunch : expectWorkTimeDictionary) {
                if (StringUtils.equals(String.valueOf(supplierByListSearchDTO.getExpectWorkTime()), workingLunch.getGroupValKey())) {
                    supplierVOByListSearch.setExpectWorkTime(workingLunch.getGroupValVal());
                    break;
                }
            }

            supplierVOByListSearches.add(supplierVOByListSearch);
        }

        return supplierVOByListSearches;
    }

    /**
     * 将人才信息DTO/简历DTO转为VO对象
     *
     * @param supplierSideBasicInfoDTO
     * @param supplierSideResumeDTO
     * @return
     */
    public SupplierResumeVOBySearchInfo supplierResumeBySearchInfo(SupplierSideBasicInfoDTO supplierSideBasicInfoDTO, SupplierSideResumeDTO supplierSideResumeDTO) throws EqianyuanException {
        SupplierResumeVOBySearchInfo supplierResumeVOBySearchInfo = new SupplierResumeVOBySearchInfo();
        BeanUtils.copyProperties(supplierSideBasicInfoDTO, supplierResumeVOBySearchInfo);
        BeanUtils.copyProperties(supplierSideResumeDTO, supplierResumeVOBySearchInfo);
        supplierResumeVOBySearchInfo.setAge(String.valueOf(CalendarUtil.getCurrentYear() - supplierSideBasicInfoDTO.getBirthdayYear()));

        //从数据字典缓存中获取性别集合
        List<DataDictionaryPO> sexDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.SEX.toString());
        if (CollectionUtils.isEmpty(sexDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.SEX.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取学历集合
        List<DataDictionaryPO> schoolingDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.SCHOOLING.toString());
        if (CollectionUtils.isEmpty(schoolingDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.SCHOOLING.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取行业集合
        List<DataDictionaryPO> industryDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.INDUSTRY.toString());
        if (CollectionUtils.isEmpty(industryDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.INDUSTRY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取行业工种类别集合
        List<DataDictionaryPO> workTypeDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK_TYPE.toString());
        if (CollectionUtils.isEmpty(workTypeDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.WORK_TYPE.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取期望薪资集合
        List<DataDictionaryPO> expectPayDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(expectPayDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工龄集合
        List<DataDictionaryPO> workingYearsDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
        if (CollectionUtils.isEmpty(workingYearsDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取薪资报酬集合
        List<DataDictionaryPO> remunerationDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(remunerationDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工作时间段集合
        List<DataDictionaryPO> expectWorkTimeDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_WORK_TIME.toString());
        if (CollectionUtils.isEmpty(expectWorkTimeDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.EXPECT_WORK_TIME.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工作状况段集合
        List<DataDictionaryPO> workingStatusDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_STATUS.toString());
        if (CollectionUtils.isEmpty(workingStatusDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.WORKING_STATUS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取外语语种集合
        List<DataDictionaryPO> foreignLanguageDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.FOREIGN_LANGUAGE.toString());
        if (CollectionUtils.isEmpty(foreignLanguageDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.FOREIGN_LANGUAGE.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取外语熟练度集合
        List<DataDictionaryPO> foreignLanguageAbilityDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.FOREIGN_LANGUAGE_ABILITY.toString());
        if (CollectionUtils.isEmpty(foreignLanguageAbilityDictionary)) {
            logger.warn("supplierResumeBySearchInfo fail , because group key [" + DataDictionaryConf.FOREIGN_LANGUAGE_ABILITY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        {
            //转换基本信息
            //转换性别数据
            for (DataDictionaryPO sex : sexDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideBasicInfoDTO.getSex()), sex.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setSex(sex.getGroupValVal());
                    break;
                }
            }
            //转换学历数据
            for (DataDictionaryPO schooling : schoolingDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideBasicInfoDTO.getHighestSchooling()), schooling.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setHighestSchooling(schooling.getGroupValVal());
                    break;
                }
            }
            //转换工龄数据
            for (DataDictionaryPO workingYears : workingYearsDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideBasicInfoDTO.getWorkingYears()), workingYears.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setWorkingYears(workingYears.getGroupValVal());
                    break;
                }
            }
            //转换状态数据
            for (DataDictionaryPO workingStatus : workingStatusDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideBasicInfoDTO.getStatus()), workingStatus.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setStatus(workingStatus.getGroupValVal());
                    break;
                }
            }
        }

        {
            //转换简历信息
            if (!ObjectUtils.isEmpty(supplierSideResumeDTO.getIndustry())) {
                //转换行业数据
                for (DataDictionaryPO industry : industryDictionary) {
                    if (StringUtils.equals(String.valueOf(supplierSideResumeDTO.getIndustry()), industry.getGroupValKey())) {
                        supplierResumeVOBySearchInfo.setIndustry(industry.getGroupValVal());
                        break;
                    }
                }
            }

            if (!StringUtils.isEmpty(supplierSideResumeDTO.getWorkType())) {
                //转换工种类别数据
                for (DataDictionaryPO workType : workTypeDictionary) {
                    if (StringUtils.equals(String.valueOf(supplierSideResumeDTO.getWorkType()), workType.getGroupValKey())) {
                        supplierResumeVOBySearchInfo.setWorkType(workType.getGroupValVal());
                        break;
                    }
                }
            }

            //转换工种数据
            if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkProficiencyDTOList())) {
                List<String> workList = new ArrayList<String>();
                for (SupplierSideResumeDTO.WorkProficiencyDTO workProficiencyDTO : supplierSideResumeDTO.getWorkProficiencyDTOList()) {
                    for (DataDictionaryPO work : workDictionary) {
                        if (StringUtils.equals(workProficiencyDTO.getWork(), work.getGroupValKey())) {
                            workList.add(work.getGroupValVal());
                            break;
                        }
                    }
                }

                supplierResumeVOBySearchInfo.setWork(StringUtils.join(workList, ","));
            }

            //转换期望薪资数据
            for (DataDictionaryPO expectPay : expectPayDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideResumeDTO.getExpectPay()), expectPay.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setExpectPay(expectPay.getGroupValVal());
                    break;
                }
            }
            //转换期望工作时间数据
            for (DataDictionaryPO expectWorkTime : expectWorkTimeDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideResumeDTO.getExpectWorkTime()), expectWorkTime.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setExpectWorkTime(expectWorkTime.getGroupValVal());
                    break;
                }
            }
            //转换外语语种数据
            for (DataDictionaryPO foreignLanguage : foreignLanguageDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideResumeDTO.getForeignLanguage()), foreignLanguage.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setForeignLanguage(foreignLanguage.getGroupValVal());
                    break;
                }
            }
            //转换外语语种熟练度数据
            for (DataDictionaryPO foreignLanguageAbility : foreignLanguageAbilityDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSideResumeDTO.getForeignLanguageAbility()), foreignLanguageAbility.getGroupValKey())) {
                    supplierResumeVOBySearchInfo.setForeignLanguageAbility(foreignLanguageAbility.getGroupValVal());
                    break;
                }
            }
        }

        {
            //转换简历附属信息
            if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkProficiencyDTOList())) {
                List<SupplierResumeVOBySearchInfo.WorkProficiencyVO> workProficiencies = new ArrayList<SupplierResumeVOBySearchInfo.WorkProficiencyVO>();
                for (WorkProficiencyPO workProficiencyPO : supplierSideResumeDTO.getWorkProficiencyDTOList()) {
                    SupplierResumeVOBySearchInfo.WorkProficiencyVO workProficiency = new SupplierResumeVOBySearchInfo().new WorkProficiencyVO();
                    BeanUtils.copyProperties(workProficiencyPO, workProficiency);

                    //转换工种数据
                    for (DataDictionaryPO work : workDictionary) {
                        if (StringUtils.equals(workProficiency.getWork(), work.getGroupValKey())) {
                            workProficiency.setWork(work.getGroupValVal());
                            break;
                        }
                    }

                    workProficiencies.add(workProficiency);
                }
                supplierResumeVOBySearchInfo.setWorkProficiencyVOs(workProficiencies);
            }

            if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkExperienceDTOList())) {
                List<SupplierResumeVOBySearchInfo.WorkExperienceVO> workExperiences = new ArrayList<SupplierResumeVOBySearchInfo.WorkExperienceVO>();
                for (WorkExperiencePO workExperiencePO : supplierSideResumeDTO.getWorkExperienceDTOList()) {
                    SupplierResumeVOBySearchInfo.WorkExperienceVO workExperience = new SupplierResumeVOBySearchInfo().new WorkExperienceVO();
                    BeanUtils.copyProperties(workExperiencePO, workExperience);
                    workExperiences.add(workExperience);
                }
                supplierResumeVOBySearchInfo.setWorkExperienceVOs(workExperiences);
            }

            if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getProjectExperienceDTOList())) {
                List<SupplierResumeVOBySearchInfo.ProjectExperienceVO> projectExperiences = new ArrayList<SupplierResumeVOBySearchInfo.ProjectExperienceVO>();
                for (ProjectExperiencePO projectExperiencePO : supplierSideResumeDTO.getProjectExperienceDTOList()) {
                    SupplierResumeVOBySearchInfo.ProjectExperienceVO projectExperience = new SupplierResumeVOBySearchInfo().new ProjectExperienceVO();
                    BeanUtils.copyProperties(projectExperiencePO, projectExperience);
                    projectExperiences.add(projectExperience);
                }
                supplierResumeVOBySearchInfo.setProjectExperienceVOs(projectExperiences);
            }
        }

        //根据地区编号获取地区名
        {
            if (!StringUtils.isEmpty(supplierSideResumeDTO.getExpectWorkProvinceId())) {
                ProvincePO provincePO = provinceDao.selectById(supplierSideResumeDTO.getExpectWorkProvinceId());
                if (!ObjectUtils.isEmpty(provincePO) && !ObjectUtils.isEmpty(provincePO.getId())) {
                    supplierResumeVOBySearchInfo.setExpectWorkProvinceId(provincePO.getProvinceName());
                }
            }

            if (!StringUtils.isEmpty(supplierSideResumeDTO.getExpectWorkCityId())) {
                CityPO cityPO = cityDao.selectById(supplierSideResumeDTO.getExpectWorkProvinceId(), supplierSideResumeDTO.getExpectWorkCityId());
                if (!ObjectUtils.isEmpty(cityPO) && !ObjectUtils.isEmpty(cityPO.getId())) {
                    supplierResumeVOBySearchInfo.setExpectWorkCityId(cityPO.getCityName());
                }
            }

            if (!StringUtils.isEmpty(supplierSideResumeDTO.getExpectWorkCountyId())) {
                CountyPO countyPO = countyDao.selectById(supplierSideResumeDTO.getExpectWorkCityId(), supplierSideResumeDTO.getExpectWorkCountyId());
                if (!ObjectUtils.isEmpty(countyPO) && !ObjectUtils.isEmpty(countyPO.getId())) {
                    supplierResumeVOBySearchInfo.setExpectWorkCountyId(countyPO.getCountyName());
                }
            }
        }

        return supplierResumeVOBySearchInfo;
    }

    /**
     * 将需求报名供应商PO转为VO
     *
     * @param demandSignUpSupplierPOs
     * @return
     * @throws EqianyuanException
     */
    public List<DemandSignUpSupplierVO> demandSignUpSupplier(List<DemandSignUpSupplierPO> demandSignUpSupplierPOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(demandSignUpSupplierPOs)) {
            return Collections.EMPTY_LIST;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("demandSignUpSupplier fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取期望薪资集合
        List<DataDictionaryPO> expectPayDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(expectPayDictionary)) {
            logger.warn("demandSignUpSupplier fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工龄集合
        List<DataDictionaryPO> workingYearsDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
        if (CollectionUtils.isEmpty(workingYearsDictionary)) {
            logger.warn("demandSignUpSupplier fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<DemandSignUpSupplierVO> demandSignUpSupplierVOs = new ArrayList<DemandSignUpSupplierVO>();
        for (DemandSignUpSupplierPO demandSignUpSupplierPO : demandSignUpSupplierPOs) {
            DemandSignUpSupplierVO demandSignUpSupplierVO = new DemandSignUpSupplierVO();
            BeanUtils.copyProperties(demandSignUpSupplierPO, demandSignUpSupplierVO);
            demandSignUpSupplierVO.setSignUpTime(CalendarUtil.secondsTimeToDateTimeString(demandSignUpSupplierPO.getSignUpTime(), CalendarUtil.Format_Date));

            //转换报名岗位工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(demandSignUpSupplierPO.getSignUpWork(), work.getGroupValKey())) {
                    demandSignUpSupplierVO.setSignUpWork(work.getGroupValVal());
                    break;
                }
            }

            //转换期望薪资数据
            for (DataDictionaryPO expectPay : expectPayDictionary) {
                if (StringUtils.equals(String.valueOf(demandSignUpSupplierPO.getExpectPay()), expectPay.getGroupValKey())) {
                    demandSignUpSupplierVO.setExpectPay(expectPay.getGroupValVal());
                    break;
                }
            }

            //转换工龄数据
            for (DataDictionaryPO workingYears : workingYearsDictionary) {
                if (StringUtils.equals(String.valueOf(demandSignUpSupplierPO.getWorkingYears()), workingYears.getGroupValKey())) {
                    demandSignUpSupplierVO.setWorkingYears(workingYears.getGroupValVal());
                    break;
                }
            }

            demandSignUpSupplierVOs.add(demandSignUpSupplierVO);
        }

        return demandSignUpSupplierVOs;
    }

    /**
     * 将需求约见供应商PO转为VO
     *
     * @param demandSignUpMeetSupplierPOs
     * @return
     * @throws EqianyuanException
     */
    public List<DemandSignUpMeetSupplierVO> demandSignUpMeetSupplier(List<DemandSignUpMeetSupplierPO> demandSignUpMeetSupplierPOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(demandSignUpMeetSupplierPOs)) {
            return Collections.EMPTY_LIST;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("demandSignUpMeetSupplier fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取期望薪资集合
        List<DataDictionaryPO> expectPayDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(expectPayDictionary)) {
            logger.warn("demandSignUpMeetSupplier fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工龄集合
        List<DataDictionaryPO> workingYearsDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
        if (CollectionUtils.isEmpty(workingYearsDictionary)) {
            logger.warn("demandSignUpMeetSupplier fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取约见状态集合
        List<DataDictionaryPO> signUpMeetDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.SIGN_UP_MEET_STATUS.toString());
        if (CollectionUtils.isEmpty(signUpMeetDictionary)) {
            logger.warn("demandSignUpMeetSupplier fail , because group key [" + DataDictionaryConf.SIGN_UP_MEET_STATUS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<DemandSignUpMeetSupplierVO> demandSignUpMeetSupplierVOs = new ArrayList<DemandSignUpMeetSupplierVO>();
        for (DemandSignUpMeetSupplierPO demandSignUpMeetSupplierPO : demandSignUpMeetSupplierPOs) {
            DemandSignUpMeetSupplierVO demandSignUpMeetSupplierVO = new DemandSignUpMeetSupplierVO();
            BeanUtils.copyProperties(demandSignUpMeetSupplierPO, demandSignUpMeetSupplierVO);
            demandSignUpMeetSupplierVO.setMeetTime(CalendarUtil.secondsTimeToDateTimeString(demandSignUpMeetSupplierPO.getMeetTime(), CalendarUtil.Format_Date));

            //转换报名岗位工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(demandSignUpMeetSupplierPO.getSignUpWork(), work.getGroupValKey())) {
                    demandSignUpMeetSupplierVO.setSignUpWork(work.getGroupValVal());
                    break;
                }
            }

            //转换期望薪资数据
            for (DataDictionaryPO expectPay : expectPayDictionary) {
                if (StringUtils.equals(String.valueOf(demandSignUpMeetSupplierPO.getExpectPay()), expectPay.getGroupValKey())) {
                    demandSignUpMeetSupplierVO.setExpectPay(expectPay.getGroupValVal());
                    break;
                }
            }

            //转换工龄数据
            for (DataDictionaryPO workingYears : workingYearsDictionary) {
                if (StringUtils.equals(String.valueOf(demandSignUpMeetSupplierPO.getWorkingYears()), workingYears.getGroupValKey())) {
                    demandSignUpMeetSupplierVO.setWorkingYears(workingYears.getGroupValVal());
                    break;
                }
            }

            //转换约见数据
            for (DataDictionaryPO signUpMeet : signUpMeetDictionary) {
                if (StringUtils.equals(String.valueOf(demandSignUpMeetSupplierPO.getStatus()), signUpMeet.getGroupValKey())) {
                    demandSignUpMeetSupplierVO.setStatusText(signUpMeet.getGroupValVal());
                    break;
                }
            }

            demandSignUpMeetSupplierVOs.add(demandSignUpMeetSupplierVO);
        }

        return demandSignUpMeetSupplierVOs;
    }

    /**
     * 将需求聘用供应商PO转为VO
     *
     * @param demandHireSupplierPOs
     * @return
     * @throws EqianyuanException
     */
    public List<DemandHireSupplierVO> demandHireSupplier(List<DemandHireSupplierPO> demandHireSupplierPOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(demandHireSupplierPOs)) {
            return Collections.EMPTY_LIST;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("demandHireSupplier fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取期望薪资集合
        List<DataDictionaryPO> expectPayDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(expectPayDictionary)) {
            logger.warn("demandHireSupplier fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工龄集合
        List<DataDictionaryPO> workingYearsDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
        if (CollectionUtils.isEmpty(workingYearsDictionary)) {
            logger.warn("demandHireSupplier fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取聘用状态集合
        List<DataDictionaryPO> hiredDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.HIRE_STATUS.toString());
        if (CollectionUtils.isEmpty(hiredDictionary)) {
            logger.warn("demandHireSupplier fail , because group key [" + DataDictionaryConf.HIRE_STATUS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<DemandHireSupplierVO> demandHireSupplierVOs = new ArrayList<DemandHireSupplierVO>();
        for (DemandHireSupplierPO demandHireSupplierPO : demandHireSupplierPOs) {
            DemandHireSupplierVO demandHireSupplierVO = new DemandHireSupplierVO();
            BeanUtils.copyProperties(demandHireSupplierPO, demandHireSupplierVO);
            demandHireSupplierVO.setContractComesIntoEffectTime(CalendarUtil.secondsTimeToDateTimeString(demandHireSupplierPO.getContractComesIntoEffectTime(), CalendarUtil.Format_DateMinute));
            demandHireSupplierVO.setContractExpiresTime(CalendarUtil.secondsTimeToDateTimeString(demandHireSupplierPO.getContractExpiresTime(), CalendarUtil.Format_DateMinute));

            //转换报名岗位工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(demandHireSupplierPO.getWork(), work.getGroupValKey())) {
                    demandHireSupplierVO.setWork(work.getGroupValVal());
                    break;
                }
            }

            //转换期望薪资数据
            for (DataDictionaryPO expectPay : expectPayDictionary) {
                if (StringUtils.equals(String.valueOf(demandHireSupplierPO.getExpectPay()), expectPay.getGroupValKey())) {
                    demandHireSupplierVO.setExpectPay(expectPay.getGroupValVal());
                    break;
                }
            }

            //转换工龄数据
            for (DataDictionaryPO workingYears : workingYearsDictionary) {
                if (StringUtils.equals(String.valueOf(demandHireSupplierPO.getWorkingYears()), workingYears.getGroupValKey())) {
                    demandHireSupplierVO.setWorkingYears(workingYears.getGroupValVal());
                    break;
                }
            }

            //转换聘用数据
            for (DataDictionaryPO hire : hiredDictionary) {
                if (StringUtils.equals(String.valueOf(demandHireSupplierPO.getStatus()), hire.getGroupValKey())) {
                    demandHireSupplierVO.setStatusText(hire.getGroupValVal());
                    break;
                }
            }

            demandHireSupplierVOs.add(demandHireSupplierVO);
        }

        return demandHireSupplierVOs;
    }

    /**
     * 将供应商报名需求PO转为VO
     *
     * @param supplierSignUpDemandPOs
     * @return
     * @throws EqianyuanException
     */
    public List<SupplierSignUpDemandVO> supplierSignUpDemand(List<SupplierSignUpDemandPO> supplierSignUpDemandPOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(supplierSignUpDemandPOs)) {
            return Collections.EMPTY_LIST;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("supplierSignUpDemand fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<SupplierSignUpDemandVO> supplierSignUpDemandVOs = new ArrayList<SupplierSignUpDemandVO>();
        for (SupplierSignUpDemandPO supplierSignUpDemandPO : supplierSignUpDemandPOs) {
            SupplierSignUpDemandVO supplierSignUpDemandVO = new SupplierSignUpDemandVO();
            BeanUtils.copyProperties(supplierSignUpDemandPO, supplierSignUpDemandVO);
            supplierSignUpDemandVO.setBeginCycle(CalendarUtil.secondsTimeToDateTimeString(supplierSignUpDemandPO.getBeginCycle(), CalendarUtil.Format_Date));
            supplierSignUpDemandVO.setEndCycle(CalendarUtil.secondsTimeToDateTimeString(supplierSignUpDemandPO.getEndCycle(), CalendarUtil.Format_Date));

            //转换需求工种数据
            if (!StringUtils.isEmpty(supplierSignUpDemandPO.getWork())) {
                List<String> workList = new ArrayList<String>();
                for (String workKey : supplierSignUpDemandPO.getWork().split(",")) {
                    for (DataDictionaryPO work : workDictionary) {
                        if (StringUtils.equals(workKey, work.getGroupValKey())) {
                            workList.add(work.getGroupValVal());
                            break;
                        }
                    }
                }
                supplierSignUpDemandVO.setWork(StringUtils.join(workList, ","));
            }


            //转换报名岗位工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(supplierSignUpDemandPO.getSignUpWork(), work.getGroupValKey())) {
                    supplierSignUpDemandVO.setSignUpWork(work.getGroupValVal());
                    break;
                }
            }

            supplierSignUpDemandVOs.add(supplierSignUpDemandVO);
        }

        return supplierSignUpDemandVOs;
    }

    /**
     * 将供应商约见需求PO转为VO
     *
     * @param supplierSignUpMeetDemandPOs
     * @return
     * @throws EqianyuanException
     */
    public List<SupplierSignUpMeetDemandVO> supplierSignUpMeetDemand(List<SupplierSignUpMeetDemandPO> supplierSignUpMeetDemandPOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(supplierSignUpMeetDemandPOs)) {
            return Collections.EMPTY_LIST;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("supplierSignUpMeetDemand fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取约见状态集合
        List<DataDictionaryPO> signUpMeetDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.SIGN_UP_MEET_STATUS.toString());
        if (CollectionUtils.isEmpty(signUpMeetDictionary)) {
            logger.warn("supplierSignUpMeetDemand fail , because group key [" + DataDictionaryConf.SIGN_UP_MEET_STATUS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<SupplierSignUpMeetDemandVO> supplierSignUpMeetDemandVOs = new ArrayList<SupplierSignUpMeetDemandVO>();
        for (SupplierSignUpMeetDemandPO supplierSignUpMeetDemandPO : supplierSignUpMeetDemandPOs) {
            SupplierSignUpMeetDemandVO supplierSignUpMeetDemandVO = new SupplierSignUpMeetDemandVO();
            BeanUtils.copyProperties(supplierSignUpMeetDemandPO, supplierSignUpMeetDemandVO);
            supplierSignUpMeetDemandVO.setBeginCycle(CalendarUtil.secondsTimeToDateTimeString(supplierSignUpMeetDemandPO.getBeginCycle(), CalendarUtil.Format_Date));
            supplierSignUpMeetDemandVO.setEndCycle(CalendarUtil.secondsTimeToDateTimeString(supplierSignUpMeetDemandPO.getEndCycle(), CalendarUtil.Format_Date));

            //转换需求工种数据
            if (!StringUtils.isEmpty(supplierSignUpMeetDemandPO.getWork())) {
                List<String> workList = new ArrayList<String>();
                for (String workKey : supplierSignUpMeetDemandPO.getWork().split(",")) {
                    for (DataDictionaryPO work : workDictionary) {
                        if (StringUtils.equals(workKey, work.getGroupValKey())) {
                            workList.add(work.getGroupValVal());
                            break;
                        }
                    }
                }
                supplierSignUpMeetDemandVO.setWork(StringUtils.join(workList, ","));
            }


            //转换报名岗位工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(supplierSignUpMeetDemandPO.getSignUpWork(), work.getGroupValKey())) {
                    supplierSignUpMeetDemandVO.setSignUpWork(work.getGroupValVal());
                    break;
                }
            }

            //转换约见状态数据
            for (DataDictionaryPO signUpMeet : signUpMeetDictionary) {
                if (StringUtils.equals(String.valueOf(supplierSignUpMeetDemandPO.getStatus()), signUpMeet.getGroupValKey())) {
                    supplierSignUpMeetDemandVO.setStatusText(signUpMeet.getGroupValVal());
                    break;
                }
            }

            supplierSignUpMeetDemandVOs.add(supplierSignUpMeetDemandVO);
        }

        return supplierSignUpMeetDemandVOs;
    }

    /**
     * 将供应商聘用需求PO转为VO
     *
     * @param supplierHireDemandPOs
     * @return
     * @throws EqianyuanException
     */
    public List<SupplierHireDemandVO> supplierHireDemand(List<SupplierHireDemandPO> supplierHireDemandPOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(supplierHireDemandPOs)) {
            return Collections.EMPTY_LIST;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("supplierHireDemand fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取聘用状态集合
        List<DataDictionaryPO> hireDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.HIRE_STATUS.toString());
        if (CollectionUtils.isEmpty(hireDictionary)) {
            logger.warn("supplierHireDemand fail , because group key [" + DataDictionaryConf.HIRE_STATUS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<SupplierHireDemandVO> supplierHireDemandVOs = new ArrayList<SupplierHireDemandVO>();
        for (SupplierHireDemandPO supplierHireDemandPO : supplierHireDemandPOs) {
            SupplierHireDemandVO supplierHireDemandVO = new SupplierHireDemandVO();
            BeanUtils.copyProperties(supplierHireDemandPO, supplierHireDemandVO);
            supplierHireDemandVO.setBeginCycle(CalendarUtil.secondsTimeToDateTimeString(supplierHireDemandPO.getBeginCycle(), CalendarUtil.Format_Date));
            supplierHireDemandVO.setEndCycle(CalendarUtil.secondsTimeToDateTimeString(supplierHireDemandPO.getEndCycle(), CalendarUtil.Format_Date));

            //转换需求工种数据
            if (!StringUtils.isEmpty(supplierHireDemandPO.getWork())) {
                List<String> workList = new ArrayList<String>();
                for (String workKey : supplierHireDemandPO.getWork().split(",")) {
                    for (DataDictionaryPO work : workDictionary) {
                        if (StringUtils.equals(workKey, work.getGroupValKey())) {
                            workList.add(work.getGroupValVal());
                            break;
                        }
                    }
                }
                supplierHireDemandVO.setWork(StringUtils.join(workList, ","));
            }


            //转换报名岗位工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(supplierHireDemandPO.getSignUpWork(), work.getGroupValKey())) {
                    supplierHireDemandVO.setSignUpWork(work.getGroupValVal());
                    break;
                }
            }

            //转换聘用状态数据
            for (DataDictionaryPO hire : hireDictionary) {
                if (StringUtils.equals(String.valueOf(supplierHireDemandPO.getStatus()), hire.getGroupValKey())) {
                    supplierHireDemandVO.setStatusText(hire.getGroupValVal());
                    break;
                }
            }

            supplierHireDemandVOs.add(supplierHireDemandVO);
        }

        return supplierHireDemandVOs;
    }

    /**
     * 将供应商需求约见PO对象转为VO对象
     *
     * @param signUpMeetPO
     * @return
     * @throws EqianyuanException
     */
    public DemandMeetInfoVO demandMeetInfo(SignUpMeetPO signUpMeetPO) throws EqianyuanException {
        DemandMeetInfoVO demandMeetInfoVO = new DemandMeetInfoVO();
        BeanUtils.copyProperties(signUpMeetPO, demandMeetInfoVO);
        demandMeetInfoVO.setMeetTime(CalendarUtil.secondsTimeToDateTimeString(signUpMeetPO.getMeetTime(), CalendarUtil.Format_DateTime));

        //从数据字典缓存中获取联系人尊称集合
        List<DataDictionaryPO> dataDictionaryPOs = InitialData.dataDictionaryMap.get(DataDictionaryConf.RESPECTFUL_NAME.toString());
        if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
            logger.warn("demandMeetInfo fail , because group key [" + DataDictionaryConf.RESPECTFUL_NAME.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //转换联系人尊称数据
        for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
            if (StringUtils.equals(String.valueOf(signUpMeetPO.getRespectfulName()), dataDictionaryPO.getGroupValKey())) {
                demandMeetInfoVO.setRespectfulName(dataDictionaryPO.getGroupValVal());
                break;
            }
        }

        return demandMeetInfoVO;
    }

    /**
     * 将供应商需求聘用合同PO对象转为VO对象
     *
     * @param hirePO
     * @return
     * @throws EqianyuanException
     */
    public SupplierContractInfoVO demandContractInfo(HirePO hirePO) throws EqianyuanException {
        SupplierContractInfoVO supplierContractInfoVO = new SupplierContractInfoVO();
        BeanUtils.copyProperties(hirePO, supplierContractInfoVO);
        supplierContractInfoVO.setContractComesIntoEffectTime(CalendarUtil.secondsTimeToDateTimeString(hirePO.getContractComesIntoEffectTime(), CalendarUtil.Format_DateMinute));
        supplierContractInfoVO.setContractExpiresTime(CalendarUtil.secondsTimeToDateTimeString(hirePO.getContractExpiresTime(), CalendarUtil.Format_DateMinute));

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("demandContractInfo fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //转换报名岗位工种数据
        for (DataDictionaryPO work : workDictionary) {
            if (StringUtils.equals(hirePO.getWork(), work.getGroupValKey())) {
                supplierContractInfoVO.setWorkText(work.getGroupValVal());
                break;
            }
        }

        return supplierContractInfoVO;
    }
}
