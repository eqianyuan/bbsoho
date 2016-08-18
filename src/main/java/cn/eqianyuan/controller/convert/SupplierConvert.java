package cn.eqianyuan.controller.convert;

import cn.eqianyuan.bean.po.SupplierSidePO;
import cn.eqianyuan.bean.vo.DemandSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.SupplierSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * 供应商PO转化VO对象处理类
 * Created by jason on 2016-08-11.
 */
@Component
public class SupplierConvert {

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
     * 将需求商对象PO转为VO对象
     *
     * @param supplierSidePO
     * @return
     */
    public SupplierSideVOByBasicInfo getBasicInformation(SupplierSidePO supplierSidePO) {
        SupplierSideVOByBasicInfo supplierSideVOByBasicInfo = new SupplierSideVOByBasicInfo();
        BeanUtils.copyProperties(supplierSidePO, supplierSideVOByBasicInfo);
        return supplierSideVOByBasicInfo;
    }

}
