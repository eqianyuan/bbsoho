<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav nav-tabs">
    <li class="<c:if test="${param.tabWidget eq '...'}">active</c:if>"><a href="#demandInfo" data-toggle="tab">需求信息</a>
    </li>
    <li class="<c:if test="${param.tabWidget eq '...'}">active</c:if>"><a href="#accountInfo" data-toggle="tab">账户信息</a>
    </li>
    <li class="<c:if test="${param.tabWidget eq 'basicInformation'}">active</c:if> demandSideBasicInfoTab">
        <a href="demandSide/basicInformation.jsp?tabWidget=basicInformation">企业信息</a>
    </li>
    <li class="<c:if test="${param.tabWidget eq '...'}">active</c:if>"><a href="#accountAuth" data-toggle="tab">账号认证</a>
    </li>
</ul>