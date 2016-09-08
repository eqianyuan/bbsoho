package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.DemandPO;

public interface IDemandDao {
    int deleteByPrimaryKey(String id);

    int insert(DemandPO record);

    int insertSelective(DemandPO record);

    DemandPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemandPO record);

    int updateByPrimaryKey(DemandPO record);
}