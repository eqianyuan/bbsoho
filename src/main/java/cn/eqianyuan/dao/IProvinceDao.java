package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.ProvincePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IProvinceDao {
    /**
     * 查找数据集合
     *
     * @return
     */
    List<ProvincePO> selectByList();

    /**
     * 根据地区编号查询数据
     *
     * @param provinceId
     * @return
     */
    ProvincePO selectById(@Param("province_id") String provinceId);
}