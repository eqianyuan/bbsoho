package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.CountyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ICountyDao {
    /**
     * 根据市编号查找区数据集合
     *
     * @param cityId 市编号
     * @return
     */
    List<CountyPO> selectByList(@Param("city_id") String cityId);

    /**
     * 根据地区编号查询数据
     *
     * @param cityId   市编号
     * @param countyId 区编号
     * @return
     */
    CountyPO selectById(@Param("city_id") String cityId, @Param("county_id") String countyId);
}