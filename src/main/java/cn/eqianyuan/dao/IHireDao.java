package cn.eqianyuan.dao;

import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.po.DemandHireSupplierPO;
import cn.eqianyuan.bean.po.HirePO;
import cn.eqianyuan.bean.po.SupplierHireDemandPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IHireDao {
    int deleteByPrimaryKey(String id);

    int insert(HirePO record);

    int insertSelective(HirePO record);

    HirePO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HirePO record);

    int updateByPrimaryKey(HirePO record);

    /**
     * 根据需求编号查询聘用用户总数
     *
     * @param supplierId 供应商编号
     * @return
     */
    int countBySupplierId(@Param("supplierId") String supplierId);

    /**
     * 根据需求编号和分页对象查询聘用数据分页集合
     *
     * @param supplierId 供应商编号
     * @param page       分页对象
     * @return
     */
    List<SupplierHireDemandPO> selectBySupplierPagination(@Param("supplierId") String supplierId, @Param("page") Page page);

    /**
     * 根据需求编号查询聘用用户总数
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @return
     */
    int countByDemandId(@Param("demandSideId") String demandSideId, @Param("demandId") String demandId);

    /**
     * 根据需求编号和分页对象查询聘用数据分页集合
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     */
    List<DemandHireSupplierPO> selectByDemandPagination(@Param("demandSideId") String demandSideId, @Param("demandId") String demandId, @Param("page") Page page);
}