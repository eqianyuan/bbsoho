package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.ResumePO;

public interface IResumeDao {
    int deleteByPrimaryKey(String id);

    int insert(ResumePO record);

    int insertSelective(ResumePO record);

    ResumePO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ResumePO record);

    int updateByPrimaryKey(ResumePO record);

    /**
     * 根据会员编号查询会员简历
     *
     * @param id
     * @return
     */
    ResumePO selectBySupplierSideId(String id);
}