package cn.eqianyuan.service;

import cn.eqianyuan.bean.vo.SupplierResumeVOBySearchInfo;
import cn.eqianyuan.core.exception.EqianyuanException;

/**
 * Created by jason on 2016-09-18.
 */
public interface ISupplierResumeService {


    /**
     * 根据需求编号查询简历详细信息，且字典数据已经转码
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    SupplierResumeVOBySearchInfo supplierResumeInfoBySearch(String id) throws EqianyuanException;
    
}
