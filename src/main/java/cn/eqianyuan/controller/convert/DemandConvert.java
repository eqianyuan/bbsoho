package cn.eqianyuan.controller.convert;

import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.util.CalendarUtil;
import org.springframework.beans.BeanUtils;
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
}
