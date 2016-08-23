package cn.eqianyuan.controller;

import cn.eqianyuan.bean.ServerResponse;
import cn.eqianyuan.bean.dto.DemandSideBasicInfoDTO;
import cn.eqianyuan.bean.vo.DemandSideVOByBasicInfo;
import cn.eqianyuan.bean.vo.DemandSideVOByLogin;
import cn.eqianyuan.core.exception.EqianyuanException;
import cn.eqianyuan.service.IDemandSideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 需求方会员控制器
 * Created by jason on 2016-08-10.
 */
@Controller
@RequestMapping("/demandSide")
public class DemandSideController extends BaseController {

    @Autowired
    private IDemandSideService demandSideService;

    /**
     * 会员（需求方）用户注册
     *
     * @param email           邮箱号码
     * @param loginPassword   登录密码
     * @param confirmPassword 确认密码
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse demandRegister(String email, String loginPassword, String confirmPassword) throws EqianyuanException {
        demandSideService.add(email, loginPassword, confirmPassword);
        return new ServerResponse();
    }

    /**
     * 会员（需求商）账号激活
     *
     * @param email          邮箱号码
     * @param activationCode 激活码
     * @return
     */
    @RequestMapping(value = "/account_activation", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse accountActivation(String email, String activationCode) throws EqianyuanException {
        demandSideService.accountActivation(email, activationCode);
        return new ServerResponse();
    }

    /**
     * 会员（需求商）发送激活邮件
     *
     * @param email
     * @return
     */
    @RequestMapping(value = "/send_activaton_mail", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse sendActivationMail(String email) throws EqianyuanException {
        demandSideService.sendActivationMail(email);
        return new ServerResponse();
    }

    /**
     * 会员（需求商）用户登录
     *
     * @param email         邮箱号码
     * @param loginPassword 登录密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse demandLogin(String email, String loginPassword) throws EqianyuanException {
        DemandSideVOByLogin demandSideVOByLogin = demandSideService.demandLogin(email, loginPassword);
        return new ServerResponse.ResponseBuilder().data(demandSideVOByLogin).build();
    }

    /**
     * 会员（需求商）用户登出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse demandLogout() throws EqianyuanException {
        demandSideService.demandLogout();
        return new ServerResponse();
    }

    /**
     * 需求商基本信息
     *
     * @return
     */
    @RequestMapping(value = "/basicInformation", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse basicInformation() throws EqianyuanException {
        DemandSideVOByBasicInfo demandSideVOByBasicInfo = demandSideService.getBasicInformation();
        return new ServerResponse.ResponseBuilder().data(demandSideVOByBasicInfo).build();
    }

    /**
     * 需求商基本信息编辑
     *
     * @param demandSideBasicInfoDTO
     * @return
     */
    @RequestMapping(value = "/basicInformation", method = RequestMethod.PUT)
    @ResponseBody
    public ServerResponse basicInformationEdit(DemandSideBasicInfoDTO demandSideBasicInfoDTO) throws EqianyuanException {
        demandSideService.modifyBasicInformation(demandSideBasicInfoDTO);
        return new ServerResponse();
    }
}
