package cn.eqianyuan.dao;

import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.po.DemandPO;
import cn.eqianyuan.bean.po.DemandPOBySearchList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IDemandDao {
    int deleteByPrimaryKey(String id);

    int insert(DemandPO record);

    int insertSelective(DemandPO record);

    DemandPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemandPO record);

    int updateByPrimaryKey(DemandPO record);

    /**
     * 获取数据总条数
     *
     * @return
     */
    Integer countByPagination(@Param("demand") DemandByListSearchDTO demandByListSearchDTO);

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @return
     */
    List<DemandPOBySearchList> selectByPagination(@Param("page") Page page, @Param("demand") DemandByListSearchDTO demandByListSearchDTO);

    /**
     * 获取数据总条数
     *
     * @return
     */
    Integer countByMinePagination(@Param("isEnd") String isEnd);

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @return
     */
    List<DemandPOBySearchList> selectByMinePagination(@Param("page") Page page, @Param("isEnd") String isEnd);
}