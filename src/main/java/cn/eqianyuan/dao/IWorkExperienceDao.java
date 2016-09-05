package cn.eqianyuan.dao;


import cn.eqianyuan.bean.po.WorkExperiencePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IWorkExperienceDao {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkExperiencePO record);

    int insertSelective(WorkExperiencePO record);

    WorkExperiencePO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkExperiencePO record);

    int updateByPrimaryKey(WorkExperiencePO record);

    /**
     * 根据简历编号查询数据
     *
     * @param resumeId
     * @return
     */
    List<WorkExperiencePO> selectByResumeId(@Param("resume_id") String resumeId);

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
     * @param workExperiencePOs
     * @return
     */
    int insertByList(@Param("workExperiencePOList") List<WorkExperiencePO> workExperiencePOs);
}