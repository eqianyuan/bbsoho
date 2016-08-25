<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>注册 - 百百SOHO</title>
    <%@ include file="common_inport.jsp"%>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <!-- lr-logo -->
        <div class="lr-logo">
            <a href="index.jsp" class="left-logo">
                <img src="images/logo.png" alt="LOGO"/>
            </a>
            <div class="right-tit">工作平台</div>
            <div class="clearfix"></div>
            <div class="bottom-tit">用系统的规划，审核，管理，实现异地工作旅行</div>
        </div>
        <!-- /lr-logo -->
    </div>
</div>
<!-- /header -->
<!-- lr-wrapper -->
<div class="lr-wrapper">
    <div class="lr-img"><img src="images/login/lr_bg.jpg" alt="background-image"/></div>
    <div class="container">
        <!-- lr-box -->
        <div class="lr-box">
            <!-- nav-tabs -->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#supplier-area" data-toggle="tab">个人注册</a></li>
                <li><a href="#demand-area" data-toggle="tab">企业注册</a></li>
                <li><a href="login.jsp">登录</a></li>
            </ul>
            <!-- /nav-tabs -->
            <!-- tab-content -->
            <div class="tab-content">
                <div class="tab-pane fade in active" id="supplier-area">
                    <form action="javascript:;" method="post" class="forms personal-form">
                        <p class="ipt-txt"><input type="text" name="mobile" placeholder="手机号"/></p>
                        <p class="ipt-txt">
                            <input type="text" name="imgVerifyCode" value="" class="wd190" placeholder="图片验证码"/>
                            <a href="javascript:;" class="imgCode"><img id="verifyCodeImage"
                                                                        src="supplierSide/verifyCodeByPage"
                                                                        title="点击重新获取验证码"/></a>
                        </p>
                        <p class="ipt-txt">
                            <input type="text" name="smsVerifyCode" value="" class="wd190" placeholder="短信验证码"/>
                            <a href="javascript:;" class="telCode getSMSVerificationCode">获取验证码</a>
                        </p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" value="" placeholder="密码"/></p>
                        <p class="ipt-txt"><input type="text" name="inviteCode" value="" placeholder="邀请码选填"/></p>
                        <div class="error reg-error"></div>
                        <p class="ipt-txt">
                            <input type="checkbox" name="clause" checked disabled/>
                            <label >同意使用条款</label>
                        </p>
                        <button type="button" name="supplierSubmit" class="ipt-submit">注册</button>
                    </form>
                </div>
                <div class="tab-pane fade" id="demand-area">
                    <form action="javascript:;" method="post" class="forms company-form">
                        <p class="ipt-txt"><input type="text" name="email" value="" placeholder="邮箱号码"/></p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" value="" placeholder="密码"/></p>
                        <p class="ipt-txt"><input type="password" name="confirmPassword" value="" placeholder="确认密码"/></p>
                        <p class="ipt-txt"><input type="text" name="inviteCode" value="" placeholder="邀请码选填"/></p>
                        <div class="error reg-error"></div>
                        <p class="ipt-txt">
                            <input type="checkbox" name="clause" checked disabled/>
                            <label >同意使用条款</label>
                        </p>
                        <button type="submit" name="demandSubmit" class="ipt-submit">注册</button>
                    </form>
                </div>
            </div>
            <!-- /tab-content -->
        </div>
        <!-- /lr-box -->
    </div>
</div>
<!-- /lr-wrapper -->
<!-- footer -->
<div class="footer">
    <div class="container">
        <!-- f-left -->
        <div class="f-left">
            <div class="bottom-link">
                <a href="javascript:;">新手引导</a>
                <a href="javascript:;">安全条款</a>
                <a href="javascript:;">服务协议</a>
                <a href="javascript:;">加入我们</a>
                <a href="javascript:;">投资合作</a>
            </div>
            <div class="copy-right">
                ©百百Soho.com 南京驿乾元信息技术有限公司 京ICP证110507号 京ICP备10046444号
            </div>
            <div class="f-link">
                <a href="javascript:;"><img src="images/link_01.png" alt="1"/></a>
                <a href="javascript:;"><img src="images/link_02.png" alt="2"/></a>
            </div>
        </div>
        <!-- /f-left -->
        <!-- f-right -->
        <div class="f-right">
            <div class="bottom-table">
                <div class="cell">
                    <div class="QR-code">
                        <img src="images/code.png" alt="二维码"/>
                    </div>
                </div>
                <div class="cell">
                    <h2>客服热线：025-84242551</h2>
                    <p>周一至周六 9:00-20:30</p>
                    <p>（仅收市话费）</p>
                    <p class="email">投诉邮箱：Liuchao2016617@163.com</p>
                </div>
            </div>
        </div>
        <!-- /f-right -->
        <div class="clearfix"></div>
    </div>
</div>
<!-- /footer -->

<script>
    $(function () {
        //个人注册，点击验证码图片更换图片
        $("#verifyCodeImage").click(function () {
            var getVerifyCodeByPageUrl = $(this).prop("src");
            if (getVerifyCodeByPageUrl.indexOf("?") != -1) {
                getVerifyCodeByPageUrl = getVerifyCodeByPageUrl.substr(0, getVerifyCodeByPageUrl.indexOf("?"));
            }
            getVerifyCodeByPageUrl += "?" + new Date().valueOf();
            $(this).prop("src", getVerifyCodeByPageUrl);
        });

        //个人注册，点击获取短信验证码事件处理
        $(".getSMSVerificationCode").click(function () {
            //判断间隔时间是否归0
            if (timerByGetSMSVeerifyCodeInterval > 0 && timerByGetSMSVeerifyCodeInterval < 60) {
                return false;
            }

            $.ajax({
                type: "GET",
                url: "/supplierSide/sendSMSVerificationCodeByRegister",
                data: {
                    mobile: $("input[name='mobile']").val(),
                    verifyCodeByPage: $("input[name='imgVerifyCode']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        getSMSVeerifyCode();
                    }else{
                        alert(resp.message);
                    }
                }
            });
        });

        //定义获取短信验证码按钮文字
        var getSMSVerifyicationCodeText ;
        //定义发送短信验证码间隔时间
        var timerByGetSMSVeerifyCodeInterval = 60;
        var getSMSVeerifyCode = function () {
            getSMSVerifyicationCodeText = timerByGetSMSVeerifyCodeInterval + "S";

            if (--timerByGetSMSVeerifyCodeInterval < 0) {
                timerByGetSMSVeerifyCodeInterval = 60;
                getSMSVerifyicationCodeText = "获取验证码";
            }else{
                setTimeout(getSMSVeerifyCode, 1000);
            }

            $(".getSMSVerificationCode").text(getSMSVerifyicationCodeText);
        }

        /**
         * 个人注册按钮点击提交事件
         */
        $("button[name='supplierSubmit']").click(function(){
            $.ajax({
                type: "POST",
                url: "/supplierSide/register",
                data: {
                    mobileNumber: $("input[name='mobile']").val(),
                    verifyCodeBySMS: $("input[name='smsVerifyCode']").val(),
                    loginPassword: $("#supplier-area input[name='loginPassword']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        window.location.href = "/login.jsp";
                    }else{
                        alert(resp.message);
                    }
                }
            });
        });

        /**
         * 企业注册按钮点击提交事件
         */
        $("button[name='demandSubmit']").click(function(){
            $.ajax({
                type: "POST",
                url: "/demandSide/register",
                data: {
                    email: $("input[name='email']").val(),
                    loginPassword: $("#demand-area input[name='loginPassword']").val(),
                    confirmPassword: $("#demand-area input[name='confirmPassword']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        window.location.href = "/login.jsp";
                    }else{
                        alert(resp.message);
                    }
                }
            });
        });

    })
</script>
</body>
</html>