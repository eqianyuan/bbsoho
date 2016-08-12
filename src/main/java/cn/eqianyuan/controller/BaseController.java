package cn.eqianyuan.controller;

import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.util.SessionUtil;
import cn.eqianyuan.util.VerifyCodeUtils;
import cn.eqianyuan.util.YamlForMapHandleUtil;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import cn.eqianyuan.util.yamlMapper.SystemErr;
import cn.eqianyuan.bean.ServerResponse;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jason on 2016-05-22.
 */
public class BaseController {

    Logger logger = Logger.getLogger(this.getClass());

    /**
     * 用于统一处理异常信息
     *
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ServerResponse exception(Exception ex) {
        String messageCode = ExceptionMsgConstant.SYSTEM_RUNTIME_EXCEPTION;
        if (ex instanceof EqianyuanException) {
            logger.warn("BaseController catch exception info is :" +
                    YamlForMapHandleUtil.getValueBykey(SystemErr.getMap()
                            , ex.getMessage()
                            , SystemErr.Key.CN.toString()));
            messageCode = ex.getMessage();
        } else {
            logger.warn("BaseController catch exception info is :", ex);
        }

        Map<String, Object> systemErrMap = (Map<String, Object>) YamlForMapHandleUtil.getMapByKey(SystemErr.getMap(), messageCode);

        return new ServerResponse.ResponseBuilder()
                .code(systemErrMap.get(SystemErr.Key.CODE.toString()).toString())
                .message(systemErrMap.get(SystemErr.Key.CN.toString()).toString())
                .data(null).build();
    }

    /**
     * 生成验证码图片流
     *
     * @param verifyCode 验证码内容
     * @param response   http响应
     * @param height     图片高度
     * @throws EqianyuanException
     * @throws IOException
     */
    public void verifyCode(String verifyCode,
                           HttpServletResponse response,
                           int height) throws EqianyuanException, IOException {
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        VerifyCodeUtils.render(verifyCode, sos, verifyCode.length() * 30, height);
        sos.close();
    }
}
