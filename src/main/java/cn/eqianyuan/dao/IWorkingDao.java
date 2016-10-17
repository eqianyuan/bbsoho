package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.HirePO;
import cn.eqianyuan.bean.po.WorkingPO;

public interface IWorkingDao {
    int deleteByPrimaryKey(String id);

    int insert(WorkingPO record);

    int insertSelective(WorkingPO record);

    WorkingPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WorkingPO record);

    int updateByPrimaryKey(WorkingPO record);

    /**
     * 将聘用表数据复制到工作表
     *
     * @param record
     * @return
     */
    int copyInsertHistory(HirePO record);
}