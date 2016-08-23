package cn.eqianyuan.interceptor;

import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.service.IDemandSideService;
import cn.eqianyuan.util.SessionUtil;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需求商发布需求拦截器
 * Created by jason on 2016-08-15.
 */
public class DemandPublishInterceptor implements HandlerInterceptor {

    @Autowired
    private IDemandSideService demandSideService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        DemandSideVOByLogin demandSideVOByLogin = (DemandSideVOByLogin) SessionUtil.getAttribute(SystemConf.DEMAND_USER_BY_LOGIN.toString());

        //检查需求商信息资料是否完善
        if (!demandSideService.isIntegrity(demandSideVOByLogin.getEmail())) {
            return interceptorByUnIntegrity(httpServletRequest, httpServletResponse);
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 未激活拦截结果响应，跳转到未激活提醒+激活邮件发送页面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean interceptorByUnIntegrity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + SystemConf.DEMAND_USER_UN_INTEGRITY_PAGE.toString());
        return false;
    }
}
