package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.DemandSidePO;
import org.apache.ibatis.annotations.Param;

public interface IDemandSideDao {
    int deleteByPrimaryKey(String id);

    int insert(DemandSidePO record);

    int insertSelective(DemandSidePO record);

    DemandSidePO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DemandSidePO record);

    int updateByPrimaryKey(DemandSidePO record);

    /**
     * 根据邮箱号码查询注册用户数据条数
     *
     * @param email
     * @return
     */
    int selectCountByEmail(String email);

    /**
     * 根据邮箱号码查询注册用户数据
     *
     * @param email
     * @return
     */
    DemandSidePO selectByEmail(String email);

    /**
     * 根据邮箱号码和激活码查询注册用户数据
     *
     * @param email
     * @param activationCode
     * @return
     */
    DemandSidePO selectByActivation(@Param("email") String email, @Param("activation_code") String activationCode);

    /**
     * 根据登录信息查询数据对象
     *
     * @param email
     * @param loginPassword
     * @return
     */
    DemandSidePO selectByLogin(@Param("email") String email, @Param("login_password") String loginPassword);
}