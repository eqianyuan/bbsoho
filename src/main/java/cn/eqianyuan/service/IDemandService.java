package cn.eqianyuan.service;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.request.SupplierMeetRequest;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.bean.vo.DemandVOBySearchInfo;
import cn.eqianyuan.core.exception.EqianyuanException;

/**
 * Created by jason on 2016-09-06.
 */
public interface IDemandService {

    /**
     * 根据主键查询需求信息
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    DemandVOByInfo demandInfo(String id) throws EqianyuanException;

    /**
     * 发布需求
     *
     * @param demandDTO
     * @throws EqianyuanException
     */
    void demandPublish(DemandDTO demandDTO) throws EqianyuanException;

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @param page
     * @param demandByListSearchDTO
     * @return
     */
    PageResponse demandList(Page page, DemandByListSearchDTO demandByListSearchDTO) throws EqianyuanException;

    /**
     * 根据需求编号查询需求详细信息，且字典数据已经转码
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    DemandVOBySearchInfo demandInfoBySearch(String id) throws EqianyuanException;

    /**
     * 根据需求状态及分页条件获取分页数据集合
     *
     * @param page
     * @param isEnd
     * @return
     */
    PageResponse demandListByMine(Page page, String isEnd) throws EqianyuanException;

    /**
     * 查询需求中已报名的人员信息
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     * @throws EqianyuanException
     */
    PageResponse signUpByDemand(String demandSideId, String demandId, Page page) throws EqianyuanException;

    /**
     * 查询需求中已约见的人员信息
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     * @throws EqianyuanException
     */
    PageResponse signUpMeetByDemand(String demandSideId, String demandId, Page page) throws EqianyuanException;

    /**
     * 查询需求中已聘用的人员信息
     *
     * @param demandSideId 需求商编号
     * @param demandId     需求编号
     * @param page         分页对象
     * @return
     * @throws EqianyuanException
     */
    PageResponse hireByDemand(String demandSideId, String demandId, Page page) throws EqianyuanException;

    /**
     * 需求报名供应商约见
     *
     * @param supplierMeetRequest
     * @throws EqianyuanException
     */
    void signUpMeet(SupplierMeetRequest supplierMeetRequest) throws EqianyuanException;
}
