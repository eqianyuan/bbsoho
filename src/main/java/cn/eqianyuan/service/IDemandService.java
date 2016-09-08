package cn.eqianyuan.service;

import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.core.exception.EqianyuanException;

/**
 * Created by jason on 2016-09-06.
 */
public interface IDemandService {

    /**
     * 根据主键查询需求信息
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    DemandVOByInfo demandInfo(String id) throws EqianyuanException;

    /**
     * 发布需求
     *
     * @param demandDTO
     * @throws EqianyuanException
     */
    void demandPublish(DemandDTO demandDTO) throws EqianyuanException;
}
