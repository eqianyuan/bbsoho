<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="header normal">
    <div class="container">
        <!-- normal-head -->
        <div class="normal-head">
            <a href="index.jsp" class="logo">
                <img src="images/logo.png" alt="LOGO"/>
            </a>
            <ul class="lr">
                <li class="btn-menu"><a href="javascript:;">导航</a></li>
                <c:choose>
                    <c:when test="${!empty supplierUserByLogin}">
                        <li>
                            <dl class="head">
                                <dt><a href="javascript:;"><img src="${empty supplierUserByLogin.headPortrait ? 'images/head.png' : supplierUserByLogin.headPortrait}" alt="head" /><span></span></a></dt>
                                <dd>
                                    <a href="javascript:;">${supplierUserByLogin.nickName}</a>
                                    <ul class="top-menu">
                                        <li><a href="javascript:;">我的工作</a></li>
                                        <li><a href="supplierSide/basicInformation.jsp?menuNavigation=talentPool&tabWidget=basicInformation">个人信息</a></li>
                                        <li><a href="supplierSide/resume.jsp?menuNavigation=talentPool&tabWidget=basicInformation">个人简历</a></li>
                                        <li><a href="javascript:;" data-toggle="modal" data-target="#systemMsg">系统消息</a></li>
                                        <li><a href="javascript:;" class="exit supplierLogout">退出</a></li>
                                    </ul>
                                </dd>
                            </dl>
                        </li>
                    </c:when>
                    <c:when test="${!empty demandUserByLogin}">
                        <li>
                            <dl class="head">
                                <dt><a href="javascript:;"><img src="${empty demandUserByLogin.logo ? 'images/head.png' : demandUserByLogin.logo}" alt="head" /><span></span></a></dt>
                                <dd>
                                    <a href="javascript:;">${demandUserByLogin.companyName}</a>
                                    <ul class="top-menu">
                                        <li><a href="demand/demand_list_mine.jsp?menuNavigation=demandHall&demandId&tabWidget=mineDemand">我的需求</a></li>
                                        <li><a href="demandSide/basicInformation.jsp?menuNavigation=demandHall&demandId&tabWidget=basicInformation">企业信息</a></li>
                                        <li><a href="demandSide/demandEdit.jsp?menuNavigation=demandHall&demandId&isPublish=true">发布需求</a></li>
                                        <li><a href="javascript:;" data-toggle="modal" data-target="#systemMsg">系统消息</a></li>
                                        <li><a href="javascript:;" class="exit demandLogout">退出</a></li>
                                    </ul>
                                </dd>
                            </dl>
                        </li>

                        <script>
                            if(${demandUserByLogin.activationStatus} == 2 && ("" == "${param.isEnter}")){
                                $.ajax({
                                    type: "GET",
                                    url: "demandSide/unActivation.jsp",
                                    data: null,
                                    dataType: "html",
                                    success: function (resp) {
                                        $("html").html(resp)
                                    }
                                });
                            }
                        </script>
                    </c:when>
                    <c:otherwise>
                        <li><a href="login.jsp" class="login">登录</a></li>
                        <li><a href="register.jsp">注册</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <ul class="nav">
                <li class="<c:if test="${empty param.menuNavigation}">active</c:if>"><a href="index.jsp">首页</a></li>
                <li class="<c:if test="${param.menuNavigation eq 'demandHall'}">active</c:if>"><a href="demand/demand_list.jsp?menuNavigation=demandHall">需求大厅</a></li>
                <li class="<c:if test="${param.menuNavigation eq 'talentPool'}">active</c:if>"><a href="supplier/supplier_list.jsp?menuNavigation=talentPool">人才库</a></li>
                <li class="<c:if test="${param.menuNavigation eq 'help'}">active</c:if>"><a href="help.jsp?menuNavigation=help">新手帮助</a></li>
                <%--<li class="<c:if test="${empty param.menuNavigation}">active</c:if>"><a href="javascript:;">投诉建议</a></li>--%>
            </ul>
            <div class="clearfix"></div>
        </div>
        <!-- /normal-head -->
    </div>
</div>

<script>
    $(function () {
        /**
         * 个人退出
         */
        $(".supplierLogout").click(function () {
            $.ajax({
                type: "GET",
                url: "/supplierSide/logout",
                data: null,
                success: function (resp) {
                    window.location.reload();
                }
            });
        });

        /**
         * 企业退出
         */
        $(".demandLogout").click(function () {
            $.ajax({
                type: "GET",
                url: "/demandSide/logout",
                data: null,
                success: function (resp) {
                    window.location.reload();
                }
            });
        });
    })
</script>