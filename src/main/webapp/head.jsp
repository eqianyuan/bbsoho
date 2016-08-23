<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.method.js"></script>
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<script type="text/javascript" src="js/jquery.extends.1.0.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/bootstrap/html5shiv.min.js"></script>
<script type="text/javascript" src="js/bootstrap/respond.min.js"></script>
<![endif]-->

<div class="header normal">
    <div class="container">
        <!-- normal-head -->
        <div class="normal-head">
            <a href="index.jsp" class="logo">
                <img src="images/logo.png" alt="LOGO"/>
            </a>
            <ul class="lr">
                <li class="btn-menu"><a href="javascript:;">导航</a></li>
                <c:if test="${empty supplierUserByLogin}">
                    <li><a href="login.jsp" class="login">登录</a></li>
                    <li><a href="register.jsp">注册</a></li>
                </c:if>
                <c:if test="${!empty supplierUserByLogin}">
                    <li>
                        <dl class="head">
                            <dt><a href="personal.html"><img src="${supplierUserByLogin.headPortrait}" alt="head"/><span></span></a></dt>
                            <dd>
                                <a href="personal.html">${supplierUserByLogin.nickName}</a>
                                <a href="javascript:;" class="exit">退出</a>
                            </dd>
                        </dl>
                    </li>
                </c:if>
            </ul>
            <ul class="nav">
                <li class="active"><a href="index.jsp">首页</a></li>
                <li><a href="search_job.html">找工作</a></li>
                <li><a href="talents_list.html">招人才</a></li>
                <li><a href="help.html">新手帮助</a></li>
                <li><a href="complain.html">投诉建议</a></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <!-- /normal-head -->
    </div>
</div>

<script>
    $(function () {
        /**
         * 退出
         */
        $(".exit").click(function () {
            $.ajax({
                type: "GET",
                url: "/supplier/logout",
                data: null,
                success: function (resp) {
                    window.location.reload();
                }
            });
        });
    })
</script>