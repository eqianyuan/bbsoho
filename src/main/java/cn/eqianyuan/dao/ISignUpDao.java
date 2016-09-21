package cn.eqianyuan.dao;


import cn.eqianyuan.bean.po.SignUpPO;
import org.apache.ibatis.annotations.Param;

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
}