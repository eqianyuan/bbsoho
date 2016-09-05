<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<ul class="nav nav-tabs">
    <li><a href="javascript:;">我的工作</a></li>
    <li><a href="javascript:;">账户信息</a></li>
    <li class="<c:if test="${param.tabWidget eq 'basicInformation'}">active</c:if>">
        <a href="supplierSide/basicInformation.jsp?tabWidget=basicInformation">个人信息</a>
    </li>
    <li><a href="javascript:;">过往评价</a></li>
</ul>