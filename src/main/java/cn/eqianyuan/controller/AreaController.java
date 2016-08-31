package cn.eqianyuan.controller;

import cn.eqianyuan.bean.po.CityPO;
import cn.eqianyuan.bean.po.CountyPO;
import cn.eqianyuan.bean.po.ProvincePO;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 地区控制器
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

    @Autowired
    private IAreaService areaService;

    /**
     * 获取省数据集合
     *
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/getProvince", method = RequestMethod.GET)
    @ResponseBody
    public List<ProvincePO> getProvince() throws EqianyuanException {
        return areaService.getProvince();
    }

    /**
     * 根据省信息获取下级市数据集合
     *
     * @param provinceId 省级编号
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    @ResponseBody
    public List<CityPO> getCity(String provinceId) throws EqianyuanException {
        return areaService.getCity(provinceId);
    }

    /**
     * 根据市信息获取下级区数据集合
     *
     * @param cityId 市级编号
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/getCounty", method = RequestMethod.GET)
    @ResponseBody
    public List<CountyPO> getCounty(String cityId) throws EqianyuanException {
        return areaService.getCounty(cityId);
    }
}
