package cn.eqianyuan.dao;

import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.po.DemandSignUpMeetSupplierPO;
import cn.eqianyuan.bean.po.SignUpMeetPO;
import cn.eqianyuan.bean.po.SupplierSignUpMeetDemandPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISignUpMeetDao {
    int deleteByPrimaryKey(String id);

    int insert(SignUpMeetPO record);

    int insertSelective(SignUpMeetPO record);

    SignUpMeetPO selectByPrimaryKey(String id);

    /**
     * 根据需求编号和供应商编号查询约见信息
     *
     * @param demandId
     * @param supplierSideId
     * @return
     */
    SignUpMeetPO selectMeetInfo(@Param("demandId") String demandId, @Param("supplierSideId") String supplierSideId);

    int updateByPrimaryKeySelective(SignUpMeetPO record);

    int updateByPrimaryKey(SignUpMeetPO record);

    /**
     * 查询供应商已约见需求总数
     *
     * @param supplierId
     * @return
     */
    int countBySupplierId(@Param("supplierId") String supplierId);

    /**
     * 根据对象及分页条件获取供应商约见需求分页数据集合
     *
     * @return
     */
    List<SupplierSignUpMeetDemandPO> selectBySupplierPagination(@Param("supplierId") String supplierId, @Param("page") Page page);

    /**
     * 根据需求编号查询约见用户总数
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @return
     */
    int countByDemandId(@Param("demandSideId") String demandSideId, @Param("demandId") String demandId);

    /**
     * 根据需求编号和分页对象查询约见数据分页集合
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     */
    List<DemandSignUpMeetSupplierPO> selectByDemandPagination(@Param("demandSideId") String demandSideId, @Param("demandId") String demandId, @Param("page") Page page);

    /**
     * 将约见表数据复制到约见历史表
     *
     * @param record
     * @return
     */
    int copyInsertHistory(SignUpMeetPO record);
}