package cn.eqianyuan.util;

import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.util.yamlMapper.SystemConf;

/**
 * Created by jason on 2016-08-24.
 */
public class UserUtils {

    /**
     * 从session 中获取会员（需求商）登录对象
     *
     * @return
     */
    public static DemandSideVOByLogin getDemandSideUserBySession() {
        return (DemandSideVOByLogin) SessionUtil.getAttribute(SystemConf.DEMAND_USER_BY_LOGIN.toString());
    }

    /**
     * 从session 中获取会员（供应商）登录对象
     *
     * @return
     */
    public static SupplierSideVOByLogin getSupplierSideUserBySession() {
        return (SupplierSideVOByLogin) SessionUtil.getAttribute(SystemConf.SUPPLIER_USER_BY_LOGIN.toString());
    }

}
