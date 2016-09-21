package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.dto.SupplierSideBasicInfoDTO;
import cn.eqianyuan.bean.dto.SupplierSideResumeDTO;
import cn.eqianyuan.bean.po.*;
import cn.eqianyuan.bean.vo.SupplierResumeVOBySearchInfo;
import cn.eqianyuan.controller.convert.SupplierSideConvert;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.dao.*;
import cn.eqianyuan.service.ISupplierResumeService;
import cn.eqianyuan.service.convert.ServiceSupplierSideConvert;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Created by jason on 2016-09-18.
 */
@Service
public class SupplierResumeServiceImpl implements ISupplierResumeService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ISupplierSideDao supplierSideDao;

    @Autowired
    private IResumeDao resumeDao;

    @Autowired
    private IWorkProficiencyDao workProficiencyDao;

    @Autowired
    private IWorkExperienceDao workExperienceDao;

    @Autowired
    private IProjectExperienceDao projectExperienceDao;

    @Autowired
    private ServiceSupplierSideConvert serviceSupplierConvert;

    @Autowired
    private SupplierSideConvert supplierConvert;


    /**
     * 根据需求编号查询简历详细信息，且字典数据已经转码
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public SupplierResumeVOBySearchInfo supplierResumeInfoBySearch(String id) throws EqianyuanException {
        if (StringUtils.isEmpty(id)) {
            logger.warn("supplierResumeInfoBySearch fail , because id is null");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_QUERY_FAIL);
        }

        SupplierSidePO supplierSidePO = supplierSideDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(supplierSidePO) ||
                StringUtils.isEmpty(supplierSidePO.getId())) {
            logger.warn("supplierResumeInfoBySearch fail , because query data by id [" + id + "] not exists");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_QUERY_FAIL);
        }

        //获取简历
        ResumePO resumePO = resumeDao.selectBySupplierSideId(supplierSidePO.getId());

        if (ObjectUtils.isEmpty(resumePO) ||
                StringUtils.isEmpty(resumePO.getId())) {
            logger.warn("supplierResumeInfoBySearch fail , because query resume data by supplier side id [" + supplierSidePO.getId() + "] not exists");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_RESUME_BY_QUERY_FAIL);
        }

        //获取工种及熟练度
        List<WorkProficiencyPO> workProficiencyPOs = workProficiencyDao.selectByResumeId(resumePO.getId());
        //获取工作经历
        List<WorkExperiencePO> workExperiencePOs = workExperienceDao.selectByResumeId(resumePO.getId());
        //获取项目经验
        List<ProjectExperiencePO> projectExperiencePOs = projectExperienceDao.selectByResumeId(resumePO.getId());

        //将PO转为DTO
        SupplierSideBasicInfoDTO supplierSideBasicInfoDTO = new SupplierSideBasicInfoDTO();
        SupplierSideResumeDTO supplierSideResumeDTO = new SupplierSideResumeDTO();
        supplierSideBasicInfoDTO = serviceSupplierConvert.getSupplierSideBasicInfo(supplierSideBasicInfoDTO, supplierSidePO);
        supplierSideResumeDTO = serviceSupplierConvert.getResume(supplierSideResumeDTO, resumePO);
        supplierSideResumeDTO = serviceSupplierConvert.getWorkProficiencyByResume(supplierSideResumeDTO, workProficiencyPOs);
        supplierSideResumeDTO = serviceSupplierConvert.getWorkExperienceByResume(supplierSideResumeDTO, workExperiencePOs);
        supplierSideResumeDTO = serviceSupplierConvert.getProjectExperienceByResume(supplierSideResumeDTO, projectExperiencePOs);

        //将dto转为vo
        SupplierResumeVOBySearchInfo supplierResumeVOBySearchInfo = new SupplierResumeVOBySearchInfo();
        supplierResumeVOBySearchInfo = supplierConvert.supplierResumeBySearchInfo(supplierSideBasicInfoDTO, supplierSideResumeDTO);
        return supplierResumeVOBySearchInfo;
    }

    /**
     * 供应商简历信息是否已经完善
     *
     * @param mobile
     * @return
     * @throws EqianyuanException
     */
    public boolean isIntegrity(String mobile) throws EqianyuanException {
        if (StringUtils.isEmpty(mobile)) {
            logger.warn("supplier isIntegrity fail , because mobile , value is empty");
            throw new EqianyuanException(ExceptionMsgConstant.SUPPLIER_USER_REGISTER_BY_MOBILE_IS_FAIL);
        }

        SupplierSidePO supplierSidePO = supplierSideDao.selectByMobile(mobile);
        ResumePO resumePO = resumeDao.selectBySupplierSideId(supplierSidePO.getId());
        if (StringUtils.isEmpty(resumePO.getWorkType())) {
            return false;
        }
        return true;
    }
}
