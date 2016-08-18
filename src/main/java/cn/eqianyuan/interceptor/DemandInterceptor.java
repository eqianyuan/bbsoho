package cn.eqianyuan.interceptor;

import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.service.IDemandSideService;
import cn.eqianyuan.util.SessionUtil;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需求商用户拦截器
 * Created by jason on 2016-08-15.
 */
public class DemandInterceptor implements HandlerInterceptor {

    @Autowired
    private IDemandSideService demandSideService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //检查是否有登录
        DemandSideVOByLogin demandSideVOByLogin = (DemandSideVOByLogin) SessionUtil.getAttribute(SystemConf.DEMAND_USER_BY_LOGIN.toString());
        if (ObjectUtils.isEmpty(demandSideVOByLogin)) {
            return interceptorByUnLogin(httpServletRequest, httpServletResponse);
        }

        //检查账号是否已经激活
        if (!demandSideService.isActivation(demandSideVOByLogin.getActivationStatus())) {
            return interceptorByUnActivation(httpServletRequest, httpServletResponse);
        }

        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 未登录拦截结果响应，跳转到登录界面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean interceptorByUnLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + SystemConf.DEMAND_USER_LOGIN_PAGE.toString());
        return false;
    }

    /**
     * 未激活拦截结果响应，跳转到未激活提醒+激活邮件发送页面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean interceptorByUnActivation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + SystemConf.DEMAND_USER_UN_ACTIVATION_PAGE.toString());
        return false;
    }
}
