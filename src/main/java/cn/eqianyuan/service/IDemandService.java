package cn.eqianyuan.service;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.dto.Page;
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

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @param page
     * @param demandByListSearchDTO
     * @return
     */
    PageResponse demandList(Page page, DemandByListSearchDTO demandByListSearchDTO) throws EqianyuanException;
}
