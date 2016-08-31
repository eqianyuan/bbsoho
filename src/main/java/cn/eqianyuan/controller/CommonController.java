package cn.eqianyuan.controller;

import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jason on 2016-08-29.
 */
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private ICommonService commonService;

    /**
     * 根据分组key获取数据字典数据
     *
     * @param groupKey
     * @return
     */
    @RequestMapping(value = "getDictionaryByGroupKey", method = RequestMethod.GET)
    @ResponseBody
    public List<DataDictionaryPO> getDictionaryByGroupKey(String groupKey) {
        return commonService.getDictionaryByGroupKey(groupKey);
    }
}
