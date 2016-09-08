package cn.eqianyuan.controller.convert;

import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.po.ProjectExperiencePO;
import cn.eqianyuan.bean.po.SupplierSidePO;
import cn.eqianyuan.bean.po.WorkExperiencePO;
import cn.eqianyuan.bean.po.WorkProficiencyPO;
import cn.eqianyuan.bean.vo.SupplierSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.bean.vo.SupplierSideVOByResume;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商PO转化VO对象处理类
 * Created by jason on 2016-08-11.
 */
@Component
public class SupplierSideConvert {

    /**
     * 将供应商对象PO转为VO对象
     *
     * @param supplierSidePO
     * @return
     */
    public SupplierSideVOByLogin supplierLogin(SupplierSidePO supplierSidePO) {
        SupplierSideVOByLogin supplierSideVOByLogin = new SupplierSideVOByLogin();
        BeanUtils.copyProperties(supplierSidePO, supplierSideVOByLogin);
        return supplierSideVOByLogin;
    }

    /**
     * 将供应商对象PO转为VO对象
     *
     * @param supplierSidePO
     * @return
     */
    public SupplierSideVOByBasicInfo getBasicInformation(SupplierSidePO supplierSidePO) {
        SupplierSideVOByBasicInfo supplierSideVOByBasicInfo = new SupplierSideVOByBasicInfo();
        BeanUtils.copyProperties(supplierSidePO, supplierSideVOByBasicInfo);
        return supplierSideVOByBasicInfo;
    }

    /**
     * 将供应商简历DTO转为VO对象
     *
     * @param supplierSideResumeDTO
     * @return
     */
    public SupplierSideVOByResume getResume(SupplierSideResumeDTO supplierSideResumeDTO) {
        SupplierSideVOByResume supplierSideVOByResume = new SupplierSideVOByResume();
        BeanUtils.copyProperties(supplierSideResumeDTO, supplierSideVOByResume);

        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkProficiencyDTOList())) {
            List<SupplierSideVOByResume.WorkProficiency> workProficiencies = new ArrayList<SupplierSideVOByResume.WorkProficiency>();
            for (WorkProficiencyPO workProficiencyPO : supplierSideResumeDTO.getWorkProficiencyDTOList()) {
                SupplierSideVOByResume.WorkProficiency workProficiency = new SupplierSideVOByResume().new WorkProficiency();
                BeanUtils.copyProperties(workProficiencyPO, workProficiency);
                workProficiencies.add(workProficiency);
            }
            supplierSideVOByResume.setWorkProficiencies(workProficiencies);
        }

        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getWorkExperienceDTOList())) {
            List<SupplierSideVOByResume.WorkExperience> workExperiences = new ArrayList<SupplierSideVOByResume.WorkExperience>();
            for (WorkExperiencePO workExperiencePO : supplierSideResumeDTO.getWorkExperienceDTOList()) {
                SupplierSideVOByResume.WorkExperience workExperience = new SupplierSideVOByResume().new WorkExperience();
                BeanUtils.copyProperties(workExperiencePO, workExperience);
                workExperiences.add(workExperience);
            }
            supplierSideVOByResume.setWorkExperiences(workExperiences);
        }

        if (!CollectionUtils.isEmpty(supplierSideResumeDTO.getProjectExperienceDTOList())) {
            List<SupplierSideVOByResume.ProjectExperience> projectExperiences = new ArrayList<SupplierSideVOByResume.ProjectExperience>();
            for (ProjectExperiencePO projectExperiencePO : supplierSideResumeDTO.getProjectExperienceDTOList()) {
                SupplierSideVOByResume.ProjectExperience projectExperience = new SupplierSideVOByResume().new ProjectExperience();
                BeanUtils.copyProperties(projectExperiencePO, projectExperience);
                projectExperiences.add(projectExperience);
            }
            supplierSideVOByResume.setProjectExperiences(projectExperiences);
        }

        return supplierSideVOByResume;
    }
}
