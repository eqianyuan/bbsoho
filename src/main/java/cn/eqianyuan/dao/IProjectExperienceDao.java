package cn.eqianyuan.dao;


import cn.eqianyuan.bean.po.ProjectExperiencePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProjectExperienceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IProjectExperienceDao record);

    int insertSelective(IProjectExperienceDao record);

    IProjectExperienceDao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IProjectExperienceDao record);

    int updateByPrimaryKey(IProjectExperienceDao record);

    /**
     * 根据简历编号查询数据
     *
     * @param resumeId
     * @return
     */
    List<ProjectExperiencePO> selectByResumeId(@Param("resume_id") String resumeId);

    /**
     * 根据简历编号删除数据
     *
     * @param resumeId
     * @return
     */
    int deleteByResumeId(@Param("resume_id") String resumeId);

    /**
     * 批量增加
     *
     * @param projectExperiencePOs
     * @return
     */
    int insertByList(@Param("projectExperiencePOList") List<ProjectExperiencePO> projectExperiencePOs);
}