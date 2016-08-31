package cn.eqianyuan.service.impl;

import cn.eqianyuan.bean.po.CityPO;
import cn.eqianyuan.bean.po.CountyPO;
import cn.eqianyuan.bean.po.ProvincePO;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.dao.ICityDao;
import cn.eqianyuan.dao.ICountyDao;
import cn.eqianyuan.dao.IProvinceDao;
import cn.eqianyuan.service.IAreaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 地区信息接口实现类
 */
@Service
public class AreaServiceImpl implements IAreaService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IProvinceDao provinceDao;

    @Autowired
    private ICityDao cityDao;

    @Autowired
    private ICountyDao countyDao;

    /**
     * 获取省数据集合
     *
     * @return
     */
    public List<ProvincePO> getProvince() throws EqianyuanException {
        List<ProvincePO> provinces = provinceDao.selectByList();
        if (CollectionUtils.isEmpty(provinces)) {
            logger.info("get province , result is null");
            return null;
        }

        return provinces;
    }

    /**
     * 根据省编号获取市数据集合
     *
     * @param provinceId 省编号
     * @return
     * @throws EqianyuanException
     */
    public List<CityPO> getCity(String provinceId) throws EqianyuanException {
        if (StringUtils.isEmpty(provinceId)) {
            logger.info("getCity fail , because query param provinceId is null");
            return null;
        }

        List<CityPO> cities = cityDao.selectByList(provinceId);
        if (CollectionUtils.isEmpty(cities)) {
            logger.info("get city by province id [" + provinceId + "] , result is empty");
            return null;
        }

        return cities;
    }

    /**
     * 根据市级编号获取下级区数据集合
     *
     * @param cityId 市编号
     * @return
     * @throws EqianyuanException
     */
    public List<CountyPO> getCounty(String cityId) throws EqianyuanException {
        if (StringUtils.isEmpty(cityId)) {
            logger.info("getCounty fail , because query param cityId is null");
            return null;
        }

        List<CountyPO> counties = countyDao.selectByList(cityId);
        if (CollectionUtils.isEmpty(counties)) {
            logger.info("get county by city id [" + cityId + "] , result is empty");
            return null;
        }

        return counties;
    }
}
