package cn.eqianyuan.dao;


import cn.eqianyuan.bean.po.DemandEmployPersonsPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDemandEmployPersonsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DemandEmployPersonsPO record);

    int insertSelective(DemandEmployPersonsPO record);

    DemandEmployPersonsPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DemandEmployPersonsPO record);

    int updateByPrimaryKey(DemandEmployPersonsPO record);

    /**
     * 根据需求编号删除用人集合
     *
     * @param demandId
     * @return
     */
    int deleteByDemandId(@Param("demand_id") String demandId);

    /**
     * 根据需求编号查询需求用人集合
     *
     * @param demandId
     * @return
     */
    List<DemandEmployPersonsPO> selectByDemandId(@Param("demand_id") String demandId);

    /**
     * 批量增加
     *
     * @param demandEmployPersonsPOs
     * @return
     */
    int insertByList(@Param("demandEmployPersonsPOList") List<DemandEmployPersonsPO> demandEmployPersonsPOs);
}