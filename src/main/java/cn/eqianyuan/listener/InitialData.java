package cn.eqianyuan.listener;

import cn.eqianyuan.bean.po.DataDictionaryPO;
import cn.eqianyuan.dao.IDataDictionaryDao;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统启动成功后，初始加载系统级数据
 * Created by jason on 2016-08-15.
 */
public class InitialData implements ServletContextListener {

    //数据字典数据集合对象
    public static List<DataDictionaryPO> dataDictionary = new ArrayList<DataDictionaryPO>();

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContext appctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());

        /**
         * 加载数据字典数据
         */
        {
            IDataDictionaryDao dataDictionaryDao = (IDataDictionaryDao) appctx.getBean("IDataDictionaryDao");
            //获取字典数据集合
            dataDictionary = dataDictionaryDao.selectByList();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
