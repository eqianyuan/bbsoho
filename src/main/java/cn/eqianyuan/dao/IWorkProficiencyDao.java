package cn.eqianyuan.dao;


import cn.eqianyuan.bean.po.WorkProficiencyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWorkProficiencyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkProficiencyPO record);

    int insertSelective(WorkProficiencyPO record);

    WorkProficiencyPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkProficiencyPO record);

    int updateByPrimaryKey(WorkProficiencyPO record);

    /**
     * 根据简历编号查询工种技能及熟练度
     *
     * @param resumeId
     * @return
     */
    List<WorkProficiencyPO> selectByResumeId(@Param("resume_id") String resumeId);

    /**
     * 批量增加
     *
     * @param workProficiencyPOs
     * @return
     */
    int insertByList(@Param("workProficiencyPOList") List<WorkProficiencyPO> workProficiencyPOs);

    /**
     * 根据简历编号删除工种技能及熟练度
     *
     * @param resumeId
     * @return
     */
    int deleteByResumeId(@Param("resume_id") String resumeId);
}