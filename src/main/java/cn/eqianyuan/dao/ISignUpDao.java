package cn.eqianyuan.dao;


import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.po.SupplierSignUpDemandPO;
import cn.eqianyuan.bean.po.DemandSignUpSupplierPO;
import cn.eqianyuan.bean.po.SignUpPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISignUpDao {
    int deleteByPrimaryKey(String id);

    int insert(SignUpPO record);

    int insertSelective(SignUpPO record);

    SignUpPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SignUpPO record);

    int updateByPrimaryKey(SignUpPO record);

    /**
     * 根据需求编号和供应商用户编号查询报名数据总数
     *
     * @param demandId
     * @param supplierId
     * @return
     */
    int countBySigup(@Param("demandId") String demandId, @Param("supplierId") String supplierId);

    /**
     * 查询供应商已报名需求总数
     *
     * @param supplierId
     * @return
     */
    int countBySupplierId(@Param("supplierId") String supplierId);

    /**
     * 根据对象及分页条件获取我的报名需求分页数据集合
     *
     * @return
     */
    List<SupplierSignUpDemandPO> selectBySupplierPagination(@Param("supplierId") String supplierId, @Param("page") Page page);

    /**
     * 根据需求编号查询报名用户总数
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @return
     */
    int countByDemandId(@Param("demandSideId") String demandSideId, @Param("demandId") String demandId);

    /**
     * 根据需求编号和分页对象查询报名数据分页集合
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     */
    List<DemandSignUpSupplierPO> selectByDemandPagination(@Param("demandSideId") String demandSideId, @Param("demandId") String demandId, @Param("page") Page page);

}