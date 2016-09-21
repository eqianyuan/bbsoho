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
}
