package cn.eqianyuan.controller.convert;

import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.bean.vo.DemandVOBySearchList;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.ICountyDao;
import cn.eqianyuan.listener.InitialData;
import cn.eqianyuan.util.CalendarUtil;
import cn.eqianyuan.util.yamlMapper.DataDictionaryConf;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商DTO转化VO对象处理类
 * Created by jason on 2016-08-11.
 */
@Component
public class DemandConvert {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ICountyDao countyDao;

    /**
     * 将需求DTO对象转为VO对象
     *
     * @param demandDTO
     * @return
     */
    public DemandVOByInfo demandInfo(DemandDTO demandDTO) {
        DemandVOByInfo demandVOByInfo = new DemandVOByInfo();
        BeanUtils.copyProperties(demandDTO, demandVOByInfo);

        demandVOByInfo.setBeginCycle(CalendarUtil.secondsTimeToDateTimeString(Integer.parseInt(demandDTO.getBeginCycle()), CalendarUtil.Format_Date));
        demandVOByInfo.setEndCycle(CalendarUtil.secondsTimeToDateTimeString(Integer.parseInt(demandDTO.getEndCycle()), CalendarUtil.Format_Date));

        if (!CollectionUtils.isEmpty(demandDTO.getDemandEmployPersonsDTOList())) {
            List<DemandVOByInfo.DemandEmployPersons> demandEmployPersonses = new ArrayList<DemandVOByInfo.DemandEmployPersons>();

            for (DemandDTO.DemandEmployPersonsDTO demandEmployPersonsDTO : demandDTO.getDemandEmployPersonsDTOList()) {
                DemandVOByInfo.DemandEmployPersons demandEmployPersons = new DemandVOByInfo().new DemandEmployPersons();
                BeanUtils.copyProperties(demandEmployPersonsDTO, demandEmployPersons);
                demandEmployPersonses.add(demandEmployPersons);
            }
            demandVOByInfo.setDemandEmployPersonsList(demandEmployPersonses);
        }

        return demandVOByInfo;
    }

    /**
     * 将需求大厅DTO对象转为VO对象
     *
     * @return
     */
    public List<DemandVOBySearchList> demandList(List<DemandByListSearchDTO> demandByListSearchDTOs) throws EqianyuanException {
        if (CollectionUtils.isEmpty(demandByListSearchDTOs)) {
            return null;
        }

        //从数据字典缓存中获取工种集合
        List<DataDictionaryPO> workDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORK.toString());
        if (CollectionUtils.isEmpty(workDictionary)) {
            logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.WORK.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工龄集合
        List<DataDictionaryPO> workingYearsDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_YEARS.toString());
        if (CollectionUtils.isEmpty(workingYearsDictionary)) {
            logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.WORKING_YEARS.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取薪资报酬集合
        List<DataDictionaryPO> remunerationDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.EXPECT_PAY.toString());
        if (CollectionUtils.isEmpty(remunerationDictionary)) {
            logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.EXPECT_PAY.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        //从数据字典缓存中获取工作餐集合
        List<DataDictionaryPO> workingLunchDictionary = InitialData.dataDictionaryMap.get(DataDictionaryConf.WORKING_LUNCH.toString());
        if (CollectionUtils.isEmpty(workingLunchDictionary)) {
            logger.warn("modifyBasicInformation fail , because group key [" + DataDictionaryConf.WORKING_LUNCH.toString() + "] data not exists data dictionary");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION);
        }

        List<DemandVOBySearchList> demandVOBySearchLists = new ArrayList<DemandVOBySearchList>();

        for (DemandByListSearchDTO demandByListSearchDTO : demandByListSearchDTOs) {
            DemandVOBySearchList demandVOBySearchList = new DemandVOBySearchList();
            BeanUtils.copyProperties(demandByListSearchDTO, demandVOBySearchList);
            demandVOBySearchList.setBeginCycle(CalendarUtil.secondsTimeToDateTimeString(demandByListSearchDTO.getBeginCycle(), CalendarUtil.Format_Date));
            demandVOBySearchList.setEndCycle(CalendarUtil.secondsTimeToDateTimeString(demandByListSearchDTO.getEndCycle(), CalendarUtil.Format_Date));
            demandVOBySearchList.setPublishTime(CalendarUtil.secondsTimeToDateTimeString(demandByListSearchDTO.getEndCycle(), CalendarUtil.Format_Date));
            demandVOBySearchList.setPersonsAmount(String.valueOf(demandByListSearchDTO.getPersonsAmount()));

            //转换工种数据
            for (DataDictionaryPO work : workDictionary) {
                if (StringUtils.equals(demandByListSearchDTO.getWork(), work.getGroupValKey())) {
                    demandVOBySearchList.setWork(work.getGroupValVal());
                    break;
                }
            }
            //转换工龄数据
            for (DataDictionaryPO workingYears : workingYearsDictionary) {
                if (StringUtils.equals(String.valueOf(demandByListSearchDTO.getWorkingYears()), workingYears.getGroupValKey())) {
                    demandVOBySearchList.setWorkingYears(workingYears.getGroupValVal());
                    break;
                }
            }
            //转换薪资报酬数据
            for (DataDictionaryPO remuneration : remunerationDictionary) {
                if (StringUtils.equals(String.valueOf(demandByListSearchDTO.getRemuneration()), remuneration.getGroupValKey())) {
                    demandVOBySearchList.setRemuneration(remuneration.getGroupValVal());
                    break;
                }
            }
            //转换工作餐数据
            for (DataDictionaryPO workingLunch : workingLunchDictionary) {
                if (StringUtils.equals(String.valueOf(demandByListSearchDTO.getWorkingLunch()), workingLunch.getGroupValKey())) {
                    demandVOBySearchList.setWorkingLunch(workingLunch.getGroupValVal());
                    break;
                }
            }

            demandVOBySearchLists.add(demandVOBySearchList);
        }

        return demandVOBySearchLists;
    }
}
