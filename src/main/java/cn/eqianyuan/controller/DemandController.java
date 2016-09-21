package cn.eqianyuan.controller;

import cn.eqianyuan.bean.PageResponse;
import cn.eqianyuan.bean.ServerResponse;
import cn.eqianyuan.bean.dto.DemandByListSearchDTO;
import cn.eqianyuan.bean.dto.DemandDTO;
import cn.eqianyuan.bean.dto.Page;
import cn.eqianyuan.bean.vo.DemandVOByInfo;
import cn.eqianyuan.bean.vo.DemandVOBySearchInfo;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.service.IDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 需求方需求控制器
 * Created by jason on 2016-08-10.
 */
@Controller
@RequestMapping("/demandSide")
public class DemandController extends BaseController {

    @Autowired
    private IDemandService demandService;

    /**
     * 需求编辑
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse demandPublish(@RequestBody DemandDTO demandDTO) throws EqianyuanException {
        demandService.demandPublish(demandDTO);
        return new ServerResponse();
    }

    /**
     * 需求删除
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.DELETE)
    @ResponseBody
    public ServerResponse demandDelete(DemandDTO demandDTO) {
        return new ServerResponse();
    }

    /**
     * 需求详情
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse demandInfo(String id) throws EqianyuanException {
        DemandVOByInfo demandVOByInfo = demandService.demandInfo(id);
        return new ServerResponse.ResponseBuilder().data(demandVOByInfo).build();
    }

    /**
     * 需求大厅分页列表
     *
     * @return
     */
    @RequestMapping(value = "/search/demandList", method = RequestMethod.GET)
    @ResponseBody
    public PageResponse demandList(DemandByListSearchDTO demandByListSearchDTO,
                                   Page page) throws EqianyuanException {
        return demandService.demandList(page, demandByListSearchDTO);
    }

    /**
     * 需求详情
     *
     * @return
     */
    @RequestMapping(value = "/search/demand/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DemandVOBySearchInfo demandInfoBySearch(@PathVariable("id") String id) throws EqianyuanException {
        DemandVOBySearchInfo demandVOByInfo = demandService.demandInfoBySearch(id);
        return demandVOByInfo;
    }

    /**
     * 查询自己发布的需求分页集合
     *
     * @param isEnd 需求是否已经结束，如果内容为空，则查询未结束的需求
     * @param page
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/mine/demandList", method = RequestMethod.GET)
    @ResponseBody
    public PageResponse mineDemandList(String isEnd,
                                       Page page) throws EqianyuanException {
        return demandService.demandListByMine(page, isEnd);
    }

}
