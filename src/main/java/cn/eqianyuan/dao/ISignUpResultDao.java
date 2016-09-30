package cn.eqianyuan.dao;

import cn.eqianyuan.bean.po.SignUpResultPO;

public interface ISignUpResultDao {
    int deleteByPrimaryKey(String id);

    int insert(SignUpResultPO record);

    int insertSelective(SignUpResultPO record);

    SignUpResultPO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SignUpResultPO record);

    int updateByPrimaryKey(SignUpResultPO record);
}