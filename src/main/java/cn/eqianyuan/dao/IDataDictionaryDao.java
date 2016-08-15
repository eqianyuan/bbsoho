package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.DataDictionaryPO;

import java.util.List;

public interface IDataDictionaryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDictionaryPO record);

    int insertSelective(DataDictionaryPO record);

    DataDictionaryPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDictionaryPO record);

    int updateByPrimaryKey(DataDictionaryPO record);

    /**
     * 获取全部数据集合
     *
     * @return
     */
    List<DataDictionaryPO> selectByList();
}