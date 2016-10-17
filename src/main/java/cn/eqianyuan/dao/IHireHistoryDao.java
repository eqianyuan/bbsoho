package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.HireHistoryPO;
import cn.eqianyuan.bean.po.HirePO;

public interface IHireHistoryDao {
    int deleteByPrimaryKey(String id);

    int insert(HireHistoryPO record);

    int insertSelective(HireHistoryPO record);

    HireHistoryPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HireHistoryPO record);

    int updateByPrimaryKey(HireHistoryPO record);

    /**
     * 将聘用表数据复制到聘用历史表
     *
     * @param record
     * @return
     */
    int copyInsertHistory(HirePO record);
}