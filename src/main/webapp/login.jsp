<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>登录 - 百百SOHO</title>
    <c:import url="common_inport.jsp"/>
</head>
<body>
<c:choose>
    <c:when test="${!empty supplierUserByLogin || !empty demandUserByLogin}">
        <script>
            window.location.href = "index.jsp";
        </script>
    </c:when>
</c:choose>
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
                <li class="active"><a href="#supplier-area" data-toggle="tab">个人登录</a></li>
                <li><a href="#demand-area" data-toggle="tab">企业登录</a></li>
                <li><a href="register.jsp">注册</a></li>
            </ul>
            <!-- /nav-tabs -->
            <!-- tab-content -->
            <div class="tab-content">
                <div class="tab-pane fade in active" id="supplier-area">
                    <form action="#" method="post" class="forms login-from">
                        <div class="error warn-error">
                            <p>
                                <label class="error" style="display: inline-block;"></label>
                            </p>
                        </div>
                        <p class="ipt-txt"><input type="text" name="mobile" placeholder="手机号码"/></p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" placeholder="密码"/></p>
                        <%--<p class="ipt-txt ttr"><a href="resetLR.html" class="link-forget">忘记密码</a></p>--%>
                        <div class="error"></div>
                        <div class="btn-area">
                            <button type="button" name="supplierSubmit" class="ipt-submit">登录</button>
                            <%--<a href="javascript:;">其它登录方式</a>--%>
                        </div>
                    </form>
                </div>
                <div class="tab-pane fade" id="demand-area">
                    <form action="#" method="post" class="forms login-from">
                        <div class="error warn-error">
                            <p>
                                <label class="error" style="display: inline-block;"></label>
                            </p>
                        </div>
                        <p class="ipt-txt"><input type="text" name="email" placeholder="邮箱号码"/></p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" placeholder="密码"/></p>
                        <%--<p class="ipt-txt ttr"><a href="resetLR.html" class="link-forget">忘记密码</a></p>--%>
                        <div class="error"></div>
                        <div class="btn-area">
                            <button type="button" name="demandSubmit" class="ipt-submit">登录</button>
                            <%--<a href="javascript:;">其它登录方式</a>--%>
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
<c:import url="footer.jsp"/>
<!-- /footer -->
</body>
<script>
    /**
     * 个人登录按钮点击提交事件
     */
    $("button[name='supplierSubmit']").click(function () {
        $.ajax({
            type: "GET",
            url: "/supplierSide/login",
            data: {
                mobileNumber: $("input[name='mobile']").val(),
                loginPassword: $("#supplier-area input[name='loginPassword']").val()
            },
            success: function (resp) {
                if (resp.code == "200") {
                    window.location.href = "index.jsp";
                } else {
                    $("#supplier-area .warn-error").show().find("label").text(resp.message);
                }
            }
        });
    });

    /**
     * 企业登录按钮点击提交事件
     */
    $("button[name='demandSubmit']").click(function () {
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
                } else {
                    $("#demand-area .warn-error").show().find("label").text(resp.message);
                }
            }
        });
    });
</script>
</html>