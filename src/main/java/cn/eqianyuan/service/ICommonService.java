package cn.eqianyuan.service;

import cn.eqianyuan.bean.po.DataDictionaryPO;

import java.util.List;

/**
 * Created by jason on 2016-08-29.
 */
public interface ICommonService {
    /**
     * 根据分组key获取数据字典数据
     *
     * @param groupKey
     * @return
     */
    public List<DataDictionaryPO> getDictionaryByGroupKey(String groupKey);

    /**
     * 根据分组key及val_key前模糊获取递归字典数据
     *
     * @param groupKey
     * @param group_val_key
     * @return
     */
    List<DataDictionaryPO> getDictionaryByValKeyFuzzy(String groupKey, String group_val_key);
}
