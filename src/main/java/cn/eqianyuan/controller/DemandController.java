package cn.eqianyuan.controller;

import cn.eqianyuan.bean.ServerResponse;
import cn.eqianyuan.bean.dto.DemandSideBasicInfoDTO;
import cn.eqianyuan.service.IDemandSideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 需求方需求控制器
 * Created by jason on 2016-08-10.
 */
@Controller
@RequestMapping("/demandSide")
public class DemandController extends BaseController {

    @Autowired
    private IDemandSideService demandSideService;

    /**
     * 需求发布
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse demandPublish(DemandSideBasicInfoDTO demandSideBasicInfoDTO) {
        System.out.println(demandSideBasicInfoDTO);
        return new ServerResponse();
    }

    /**
     * 需求变更
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse demandChange(DemandSideBasicInfoDTO demandSideBasicInfoDTO) {
        return new ServerResponse();
    }

    /**
     * 需求删除
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.DELETE)
    @ResponseBody
    public ServerResponse demandDelete(DemandSideBasicInfoDTO demandSideBasicInfoDTO) {
        return new ServerResponse();
    }

    /**
     * 需求详情
     *
     * @return
     */
    @RequestMapping(value = "/demand", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse demandInfo(DemandSideBasicInfoDTO demandSideBasicInfoDTO) {
        return new ServerResponse();
    }

}
