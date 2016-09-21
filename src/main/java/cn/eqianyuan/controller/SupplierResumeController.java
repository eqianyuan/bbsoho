package cn.eqianyuan.controller;

import cn.eqianyuan.bean.vo.SupplierResumeVOBySearchInfo;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.service.ISupplierResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jason on 2016-09-18.
 */
@Controller
@RequestMapping("/supplierSide")
public class SupplierResumeController extends BaseController {

    @Autowired
    private ISupplierResumeService supplierResumeService;

    /**
     * 人才简历详情
     *
     * @return
     */
    @RequestMapping(value = "/search/supplierResume/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SupplierResumeVOBySearchInfo demandInfoBySearch(@PathVariable("id") String id) throws EqianyuanException {
        SupplierResumeVOBySearchInfo supplierResumeVOBySearchInfo = supplierResumeService.supplierResumeInfoBySearch(id);
        return supplierResumeVOBySearchInfo;
    }

}
