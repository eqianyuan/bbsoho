<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>账号激活 - 百百SOHO</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="css/ie8.css"/>
    <![endif]-->
</head>
<body class="pt97">
<!-- header -->
<!-- isEnter = 是否进入过当前页面，避免引用head.jsp，然后出现死循环引用-->
<c:import url="../head.jsp?isEnter=true"/>
<!-- /header -->
<!-- lr-wrapper -->
<div class="lr-wrapper">
    <div class="lr-img"><img src="images/index/banner.png" alt="background-image"/></div>
    <div class="container">
        <!-- index-top -->
        <div class="index-top">
            <h2>尊敬的会员，您好：</h2>
            <h2>您的账号尚未激活，为了保障您的账户安全，请先</h2>
            <h2>前往邮箱进行账户激活，点击<a href="javascript:;" class="sendActivatonMail">发送激活邮件</a></h2>
        </div>
        <!-- /index-top -->
    </div>
</div>
<!-- /lr-wrapper -->
<!-- footer -->
<c:import url="../footer.jsp"/>
<!-- /footer -->
</body>
<script>
    $(".sendActivatonMail").click(function(){
        $.ajax({
            type: "POST",
            url: "/demandSide/send_activaton_mail",
            data: {
                email: "${demandUserByLogin.email}"
            },
            success: function (resp) {
                alert(resp.message);
            }
        });
    });
</script>
</html>