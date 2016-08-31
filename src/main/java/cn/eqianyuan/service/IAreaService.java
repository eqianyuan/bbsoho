package cn.eqianyuan.service;


import cn.eqianyuan.bean.po.CityPO;
import cn.eqianyuan.bean.po.CountyPO;
import cn.eqianyuan.bean.po.ProvincePO;
import cn.eqianyuan.core.exception.EqianyuanException;

import java.util.List;

/**
 * 地区信息接口
 */
public interface IAreaService {

    /**
     * 获取省数据集合
     *
     * @return
     */
    List<ProvincePO> getProvince() throws EqianyuanException;

    /**
     * 根据省编号获取对应市数据集合
     *
     * @param provinceId 省编号
     * @return
     */
    List<CityPO> getCity(String provinceId) throws EqianyuanException;

    /**
     * 根据省编号获取对应市数据集合
     *
     * @param cityId 市编号
     * @return
     */
    List<CountyPO> getCounty(String cityId) throws EqianyuanException;
}
