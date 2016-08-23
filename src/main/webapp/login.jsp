<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>登录 - 百百SOHO</title>
    <link rel="stylesheet" type="text/css" href="css/common.css" />
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="css/ie8.css" />
    <![endif]-->
</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <!-- lr-logo -->
        <div class="lr-logo">
            <a href="index.jsp" class="left-logo">
                <img src="images/logo.png" alt="LOGO" />
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
    <div class="lr-img"><img src="images/login/lr_bg.jpg" alt="background-image" /></div>
    <div class="container">
        <!-- lr-box -->
        <div class="lr-box">
            <!-- nav-tabs -->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#supplier-area" data-toggle="tab">个人登录</a></li>
                <li><a href="#demand-area" data-toggle="tab">企业登录</a></li>
                <li><a href="register.jsp">注册</a></li>
            </ul>
            <!-- /nav-tabs -->
            <!-- tab-content -->
            <div class="tab-content">
                <div class="tab-pane fade in active" id="supplier-area">
                    <form action="#" method="post" class="forms login-from">
                        <p class="ipt-txt"><input type="text" name="mobile" placeholder="手机号码" /></p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" placeholder="密码" /></p>
                        <p class="ipt-txt ttr"><a href="resetLR.html" class="link-forget">忘记密码</a></p>
                        <div class="error"></div>
                        <div class="btn-area">
                            <button type="button" name="supplierSubmit" class="ipt-submit">登录</button>
                            <a href="javascript:;">其它登录方式</a>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="demand-area">
                    <form action="#" method="post" class="forms login-from">
                        <p class="ipt-txt"><input type="text" name="email" placeholder="邮箱号码" /></p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" placeholder="密码" /></p>
                        <p class="ipt-txt ttr"><a href="resetLR.html" class="link-forget">忘记密码</a></p>
                        <div class="error"></div>
                        <div class="btn-area">
                            <button type="button" name="demandSubmit" class="ipt-submit">登录</button>
                            <a href="javascript:;">其它登录方式</a>
                        </div>
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
                ©百百Soho.com  南京驿乾元信息技术有限公司  京ICP证110507号  京ICP备10046444号
            </div>
            <div class="f-link">
                <a href="javascript:;"><img src="images/link_01.png" alt="1" /></a>
                <a href="javascript:;"><img src="images/link_02.png" alt="2" /></a>
            </div>
        </div>
        <!-- /f-left -->
        <!-- f-right -->
        <div class="f-right">
            <div class="bottom-table">
                <div class="cell">
                    <div class="QR-code">
                        <img src="images/code.png" alt="二维码" />
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
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.method.js"></script>
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<script type="text/javascript" src="js/jquery.extends.1.0.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/package/login.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/bootstrap/html5shiv.min.js"></script>
<script type="text/javascript" src="js/bootstrap/respond.min.js"></script>
<![endif]-->
</body>
<script>
    $(function () {
        /**
         * 个人登录按钮点击提交事件
         */
        $("button[name='supplierSubmit']").click(function(){
            $.ajax({
                type: "GET",
                url: "/supplier/login",
                data: {
                    mobileNumber: $("input[name='mobile']").val(),
                    loginPassword: $("#supplier-area input[name='loginPassword']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        window.location.href = "index.jsp";
                    }else{
                        alert(resp.message);
                    }
                }
            });
        });

        /**
         * 企业登录按钮点击提交事件
         */
        $("button[name='demandSubmit']").click(function(){
            $.ajax({
                type: "GET",
                url: "/demandSide/login",
                data: {
                    email: $("input[name='email']").val(),
                    loginPassword: $("#demand-area input[name='loginPassword']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        window.location.href = "index.jsp";
                    }else{
                        alert(resp.message);
                    }
                }
            });
        });
    })
</script>
</html>