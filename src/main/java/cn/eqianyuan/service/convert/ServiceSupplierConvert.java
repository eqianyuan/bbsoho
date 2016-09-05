package cn.eqianyuan.service.convert;

import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.po.ProjectExperiencePO;
import cn.eqianyuan.bean.po.ResumePO;
import cn.eqianyuan.bean.po.WorkExperiencePO;
import cn.eqianyuan.bean.po.WorkProficiencyPO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 2016-09-02.
 */
@Component
public class ServiceSupplierConvert {

    /**
     * 将供应商简历对象PO转为DTO对象
     *
     * @param supplierSideResumeDTO 输出dto对象
     * @param resumePO              po对象
     * @return
     */
    public SupplierSideResumeDTO getResume(SupplierSideResumeDTO supplierSideResumeDTO, ResumePO resumePO) {
        BeanUtils.copyProperties(resumePO, supplierSideResumeDTO);
        return supplierSideResumeDTO;
    }

    /**
     * 将供应商工种熟练度对象PO转为DTO对象
     *
     * @param supplierSideResumeDTO
     * @param workProficiencyPOs
     * @return
     */
    public SupplierSideResumeDTO getWorkProficiencyByResume(SupplierSideResumeDTO supplierSideResumeDTO, List<WorkProficiencyPO> workProficiencyPOs) {
        if (!CollectionUtils.isEmpty(workProficiencyPOs)) {
            List<SupplierSideResumeDTO.WorkProficiencyDTO> workProficiencyDTOList = new ArrayList<SupplierSideResumeDTO.WorkProficiencyDTO>();
            for (WorkProficiencyPO workProficiencyPO : workProficiencyPOs) {
                SupplierSideResumeDTO.WorkProficiencyDTO workProficiencyDTO = new SupplierSideResumeDTO().new WorkProficiencyDTO();
                BeanUtils.copyProperties(workProficiencyPO, workProficiencyDTO);
                workProficiencyDTOList.add(workProficiencyDTO);
            }
            supplierSideResumeDTO.setWorkProficiencyDTOList(workProficiencyDTOList);
        }

        return supplierSideResumeDTO;
    }

    /**
     * 将供应商工作经历对象PO转为DTO对象
     *
     * @param supplierSideResumeDTO
     * @param workExperiencePOs
     * @return
     */
    public SupplierSideResumeDTO getWorkExperienceByResume(SupplierSideResumeDTO supplierSideResumeDTO, List<WorkExperiencePO> workExperiencePOs) {
        if (!CollectionUtils.isEmpty(workExperiencePOs)) {
            List<SupplierSideResumeDTO.WorkExperienceDTO> workExperienceDTOList = new ArrayList<SupplierSideResumeDTO.WorkExperienceDTO>();
            for (WorkExperiencePO workExperiencePO : workExperiencePOs) {
                SupplierSideResumeDTO.WorkExperienceDTO workExperienceDTO = new SupplierSideResumeDTO().new WorkExperienceDTO();
                BeanUtils.copyProperties(workExperiencePO, workExperienceDTO);
                workExperienceDTOList.add(workExperienceDTO);
            }
            supplierSideResumeDTO.setWorkExperienceDTOList(workExperienceDTOList);
        }

        return supplierSideResumeDTO;
    }

    /**
     * 将供应商项目经验对象PO转为DTO对象
     *
     * @param supplierSideResumeDTO
     * @param projectExperiencePOs
     * @return
     */
    public SupplierSideResumeDTO getProjectExperienceByResume(SupplierSideResumeDTO supplierSideResumeDTO, List<ProjectExperiencePO> projectExperiencePOs) {
        if (!CollectionUtils.isEmpty(projectExperiencePOs)) {
            List<SupplierSideResumeDTO.ProjectExperienceDTO> projectExperienceDTOList = new ArrayList<SupplierSideResumeDTO.ProjectExperienceDTO>();
            for (ProjectExperiencePO projectExperiencePO : projectExperiencePOs) {
                SupplierSideResumeDTO.ProjectExperienceDTO projectExperienceDTO = new SupplierSideResumeDTO().new ProjectExperienceDTO();
                BeanUtils.copyProperties(projectExperiencePO, projectExperienceDTO);
                projectExperienceDTOList.add(projectExperienceDTO);
            }
            supplierSideResumeDTO.setProjectExperienceDTOList(projectExperienceDTOList);
        }

        return supplierSideResumeDTO;
    }
}
