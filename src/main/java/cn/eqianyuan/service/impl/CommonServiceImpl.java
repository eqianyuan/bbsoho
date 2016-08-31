package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.listener.InitialData;
import cn.eqianyuan.service.ICommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by jason on 2016-08-29.
 */
@Service
public class CommonServiceImpl implements ICommonService {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 根据分组key获取数据字典数据
     *
     * @param groupKey
     * @return
     */
    public List<DataDictionaryPO> getDictionaryByGroupKey(String groupKey) {
        if (StringUtils.isEmpty(groupKey)) {
            logger.warn("getDictionaryByGroupKey fail , because group key is empty");
            return null;
        }

        //从数据字典缓存中获取group key集合
        List<DataDictionaryPO> dataDictionaryPOs = InitialData.dataDictionaryMap.get(groupKey);
        if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
            logger.warn("getDictionaryByGroupKey fail , because group key [" + groupKey + "] data not exists data dictionary");
            return null;
        }

        return dataDictionaryPOs;
    }
}
