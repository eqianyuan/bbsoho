package cn.eqianyuan.controller.convert;

import cn.eqianyuan.bean.po.DemandSidePO;
import cn.eqianyuan.bean.vo.DemandSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 供应商PO转化VO对象处理类
 * Created by jason on 2016-08-11.
 */
@Component
public class DemandSideConvert {

    /**
     * 将需求商对象PO转为VO对象
     *
     * @param demandSidePO
     * @return
     */
    public DemandSideVOByLogin demandLogin(DemandSidePO demandSidePO) {
        DemandSideVOByLogin demandSideVOByLogin = new DemandSideVOByLogin();
        BeanUtils.copyProperties(demandSidePO, demandSideVOByLogin);
        return demandSideVOByLogin;
    }

    /**
     * 将需求商对象PO转为VO对象
     *
     * @param demandSidePO
     * @return
     */
    public DemandSideVOByBasicInfo getBasicInformation(DemandSidePO demandSidePO) {
        DemandSideVOByBasicInfo demandSideVOByBasicInfo = new DemandSideVOByBasicInfo();
        BeanUtils.copyProperties(demandSidePO, demandSideVOByBasicInfo);
        return demandSideVOByBasicInfo;
    }

}
