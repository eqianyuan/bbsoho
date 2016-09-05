package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.listener.InitialData;
import cn.eqianyuan.service.ICommonService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    /**
     * 根据分组key及val_key前模糊获取递归字典数据
     *
     * @param groupKey
     * @param group_val_key
     * @return
     */
    public List<DataDictionaryPO> getDictionaryByValKeyFuzzy(String groupKey, String group_val_key) {
        if (StringUtils.isEmpty(group_val_key)) {
            logger.warn("getDictionaryByGroupKey fail , because group_val_key is empty");
            return null;
        }

        List<DataDictionaryPO> dataDictionaryPOs = getDictionaryByGroupKey(groupKey);

        if (CollectionUtils.isEmpty(dataDictionaryPOs)) {
            logger.warn("getDictionaryByGroupKey fail , because group key [" + groupKey + "] data not exists data dictionary");
            return null;
        }

        List<DataDictionaryPO> result = new ArrayList<DataDictionaryPO>();
        Pattern pattern = Pattern.compile(group_val_key + "+\\w+");
        for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
            if (pattern.matcher(dataDictionaryPO.getGroupValKey()).matches()) {
                result.add(dataDictionaryPO);
            }
        }
        return result;
    }
}
