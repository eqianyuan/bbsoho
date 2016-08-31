package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.CityPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ICityDao {
    /**
     * 根据省编号查找数据集合
     *
     * @param provinceId 省编号
     * @return
     */
    List<CityPO> selectByList(@Param("province_id") String provinceId);

    /**
     * 根据地区编号查询数据
     *
     * @param provinceId 省编号
     * @param cityId     市编号
     * @return
     */
    CityPO selectById(@Param("province_id") String provinceId, @Param("city_id") String cityId);
}