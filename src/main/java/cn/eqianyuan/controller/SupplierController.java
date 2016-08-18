package cn.eqianyuan.controller;

import cn.eqianyuan.bean.ServerResponse;
import cn.eqianyuan.bean.dto.SupplierSideBasicInfoDTO;
import cn.eqianyuan.bean.dto.SupplierSideDTO;
import cn.eqianyuan.bean.vo.SupplierSideVOByBasicInfo;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.core.exception.ExceptionMsgConstant;
import cn.eqianyuan.service.ISupplierSideService;
import cn.eqianyuan.util.SessionUtil;
import cn.eqianyuan.util.VerifyCodeUtils;
import cn.eqianyuan.util.yamlMapper.SystemConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 供应商入口
 * Created by jason on 2016-08-10.
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private ISupplierSideService supplierSideService;

    /**
     * 获取供应商用户注册页面验证码
     *
     * @param verifyCodeLength 验证码内容字符长度
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/verifyCodeByPage", method = RequestMethod.GET)
    public void verifyCodeByPage(@RequestParam(name = "verify_code_length", required = false, defaultValue = "4") Integer verifyCodeLength,
                                 HttpServletResponse response) throws EqianyuanException, IOException {
        //控制验证码生成数量，避免构建图片时出现内存不足问题
        if (verifyCodeLength > 10) {
            throw new EqianyuanException(ExceptionMsgConstant.VALIDATA_CODE_CONTENT_LENGTH_TO0_LONG);
        }

        //获取验证码内容
        String verifyCode = VerifyCodeUtils.random(verifyCodeLength, VerifyCodeUtils.SEEDS_BY_NUMBER);
        //将验证码内容写入session
        SessionUtil.setAttribute(SystemConf.USER_REGISTER_VERIFY_CODE_BY_PAGE.toString(), verifyCode);
        //根据验证码内容生成图片并通过response对象输出
        verifyCode(verifyCode, response, 30);
    }

    /**
     * 会员（供应商）注册发送手机短信验证码
     *
     * @param mobile           手机号码
     * @param verifyCodeByPage 页面验证码
     * @param verifyCodeLength 短信验证码内容长度
     * @return
     * @throws EqianyuanException
     * @throws IOException
     */
    @RequestMapping(value = "/sendSMSVerificationCodeByRegister", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse sendSMSVerificationCodeByRegister(String mobile,
                                                            String verifyCodeByPage,
                                                            @RequestParam(name = "verify_code_length", required = false, defaultValue = "4") Integer verifyCodeLength)
            throws EqianyuanException {
        supplierSideService.sendSMSVerificationCodeByRegister(mobile, verifyCodeByPage, verifyCodeLength);
        return new ServerResponse();
    }

    /**
     * 会员（服务方）用户注册
     *
     * @param supplierSideDTO 数据对象
     * @param verifyCodeBySMS 短信验证码
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse supplierRegister(SupplierSideDTO supplierSideDTO,
                                           String verifyCodeBySMS) throws EqianyuanException {
        supplierSideService.add(supplierSideDTO, verifyCodeBySMS);
        return new ServerResponse();
    }

    /**
     * 会员（供应商）用户登录
     *
     * @param mobileNumber  手机号码
     * @param loginPassword 登录密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse supplierLogin(String mobileNumber, String loginPassword) throws EqianyuanException {
        supplierSideService.supplierLogin(mobileNumber, loginPassword);
        return new ServerResponse();
    }

    /**
     * 会员（供应商）用户登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse supplierLogout() throws EqianyuanException {
        supplierSideService.supplierLogout();
        return new ServerResponse();
    }

    /**
     * 供应商商基本信息
     *
     * @return
     */
    @RequestMapping(value = "/basicInformation", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse basicInformation() throws EqianyuanException {
        SupplierSideVOByBasicInfo supplierSideVOByBasicInfo = supplierSideService.getBasicInformation();
        return new ServerResponse.ResponseBuilder().data(supplierSideVOByBasicInfo).build();
    }

    /**
     * 供应商基本信息编辑
     *
     * @param supplierSideBasicInfoDTO
     * @return
     */
    @RequestMapping(value = "/basicInformation", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse basicInformationEdit(SupplierSideBasicInfoDTO supplierSideBasicInfoDTO) throws EqianyuanException {
        supplierSideService.modifyBasicInformation(supplierSideBasicInfoDTO);
        return new ServerResponse();
    }
}
