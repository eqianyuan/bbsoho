package cn.eqianyuan.interceptor;

import cn.eqianyuan.bean.vo.SupplierSideVOByLogin;
import cn.eqianyuan.service.ISupplierResumeService;
import cn.eqianyuan.service.ISupplierSideService;
import cn.eqianyuan.util.UserUtils;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jason on 2016-09-21.
 */
public class SupplierResumeInterceptor implements HandlerInterceptor {

    @Autowired
    private ISupplierSideService supplierSideService;

    @Autowired
    private ISupplierResumeService supplierResumeService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        SupplierSideVOByLogin supplierSideVOByLogin = UserUtils.getSupplierSideUserBySession();

        //检查供应商基本信息是否完善
        if (!supplierSideService.isIntegrity(String.valueOf(supplierSideVOByLogin.getMobileNumber()))) {
            return unIntegrityByBasicInformation(httpServletRequest, httpServletResponse);
        }

        //检查供应商简历信息是否完善
        if (!supplierResumeService.isIntegrity(String.valueOf(supplierSideVOByLogin.getMobileNumber()))) {
            return unIntegrityByResume(httpServletRequest, httpServletResponse);
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 基本信息资料未完善，跳转到资料完善页面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean unIntegrityByBasicInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + SystemConf.SUPPLIER_USER_UN_INTEGRITY_PAGE.toString());
        return false;
    }

    /**
     * 简历信息资料未完善，跳转到简历完善页面
     *
     * @param request
     * @param response
     * @return
     */
    private boolean unIntegrityByResume(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + SystemConf.SUPPLIER_USER_UN_INTEGRITY_BY_RESUME_PAGE.toString());
        return false;
    }
}
