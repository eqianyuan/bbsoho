package cn.eqianyuan.interceptor;

import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.util.SessionUtil;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 供应商用户拦截器
 * Created by jason on 2016-08-15.
 */
public class SupplierInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //检查是否有登录
        SupplierSideVOByLogin supplierSideVOByLogin = (SupplierSideVOByLogin) SessionUtil.getAttribute(SystemConf.SUPPLIER_USER_BY_LOGIN.toString());
        if (ObjectUtils.isEmpty(supplierSideVOByLogin)) {
            return interceptorByUnLogin(httpServletRequest, httpServletResponse);
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
        response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + SystemConf.SUPPLIER_USER_LOGIN_PAGE.toString());
        return false;
    }

}
