package cn.eqianyuan.dao;


import cn.eqianyuan.bean.po.SupplierSidePO;
import org.apache.ibatis.annotations.Param;

public interface ISupplierSideDao {
    int deleteByPrimaryKey(String id);

    int insert(SupplierSidePO record);

    int insertSelective(SupplierSidePO record);

    SupplierSidePO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierSidePO record);

    int updateByPrimaryKey(SupplierSidePO record);

    /**
     * 根据手机号码查询注册用户数据条数
     *
     * @param mobileNumber
     * @return
     */
    int selectCountByMobile(@Param("mobile_number") long mobileNumber);

    /**
     * 根据登录信息查询数据对象
     *
     * @param mobileNumber
     * @param loginPassword
     * @return
     */
    SupplierSidePO selectByLogin(@Param("mobile_number") String mobileNumber, @Param("login_password") String loginPassword);

    /**
     * 根据手机号码查询用户信息
     *
     * @param mobileNumber
     * @return
     */
    SupplierSidePO selectByMobile(@Param("mobile_number") String mobileNumber);
}