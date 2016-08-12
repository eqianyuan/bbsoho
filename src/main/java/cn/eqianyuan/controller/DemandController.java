package cn.eqianyuan.controller;

import cn.eqianyuan.bean.dto.SupplierSideDTO;
import cn.eqianyuan.bean.ServerResponse;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.service.ISupplierSideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 系统会员注册入口
 * Created by jason on 2016-08-10.
 */
@Controller
@RequestMapping("/demand")
public class DemandController extends BaseController {

    /**
     * 会员（需求方）用户注册
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ServerResponse demandRegister() {

        return new ServerResponse();
    }

}
