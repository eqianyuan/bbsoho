package cn.eqianyuan.listener;

import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.dao.IDataDictionaryDao;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统启动成功后，初始加载系统级数据
 * Created by jason on 2016-08-15.
 */
public class InitialData implements ServletContextListener {

    //数据字典数据集合对象
    public static Map<String, List<DataDictionaryPO>> dataDictionaryMap = new HashMap<String, List<DataDictionaryPO>>();

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());

        /**
         * 加载数据字典数据
         */
        {
            IDataDictionaryDao dataDictionaryDao = (IDataDictionaryDao) appctx.getBean("IDataDictionaryDao");
            //获取字典数据集合
            List<DataDictionaryPO> dataDictionaryPOs = dataDictionaryDao.selectByList();
            if (!CollectionUtils.isEmpty(dataDictionaryPOs)) {
                //根据字典分组进行重组
                for (DataDictionaryPO dataDictionaryPO : dataDictionaryPOs) {
                    //从字典数据集合中获取当前分组集合
                    List<DataDictionaryPO> groupList = dataDictionaryMap.get(dataDictionaryPO.getGroupKey());

                    //如果分组集合为空，则实例化一个分组集合对象
                    if (CollectionUtils.isEmpty(groupList)) {
                        groupList = new ArrayList<DataDictionaryPO>();
                    }

                    //将当前字典加入到分组集合中
                    groupList.add(dataDictionaryPO);
                    //覆盖字典数据集合中的分集合
                    dataDictionaryMap.put(dataDictionaryPO.getGroupKey(), groupList);
                }
            }
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
