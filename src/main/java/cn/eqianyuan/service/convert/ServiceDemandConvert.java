package cn.eqianyuan.service.convert;

import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.po.DemandEmployPersonsPO;
import cn.eqianyuan.bean.po.DemandPO;
import cn.eqianyuan.util.CalendarUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商PO转化DTO对象处理类
 * Created by jason on 2016-08-11.
 */
@Component
public class ServiceDemandConvert {

    /**
     * 将需求PO对象转为DTO对象
     *
     * @param demandPO
     * @return
     */
    public DemandDTO demandInfo(DemandPO demandPO) {
        DemandDTO demandDTO = new DemandDTO();
        BeanUtils.copyProperties(demandPO, demandDTO);

        demandDTO.setBeginCycle(String.valueOf(demandPO.getBeginCycle()));
        demandDTO.setEndCycle(String.valueOf(demandPO.getEndCycle()));

        return demandDTO;
    }

    /**
     * 将供应商需求用人要求对象PO转为DTO对象
     *
     * @param demandDTO
     * @param demandEmployPersonsPOs
     * @return
     */
    public void getEmployPersonsByDemandInfo(DemandDTO demandDTO, List<DemandEmployPersonsPO> demandEmployPersonsPOs) {
        if (!CollectionUtils.isEmpty(demandEmployPersonsPOs)) {
            List<DemandDTO.DemandEmployPersonsDTO> demandEmployPersonsDTOList = new ArrayList<DemandDTO.DemandEmployPersonsDTO>();
            for (DemandEmployPersonsPO demandEmployPersonsPO : demandEmployPersonsPOs) {
                DemandDTO.DemandEmployPersonsDTO demandEmployPersonsDTO = new DemandDTO().new DemandEmployPersonsDTO();
                BeanUtils.copyProperties(demandEmployPersonsPO, demandEmployPersonsDTO);
                demandEmployPersonsDTOList.add(demandEmployPersonsDTO);
            }
            demandDTO.setDemandEmployPersonsDTOList(demandEmployPersonsDTOList);
        }
    }
}
