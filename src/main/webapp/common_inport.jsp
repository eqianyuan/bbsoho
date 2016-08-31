<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<base href="<%=basePath%>">

<link rel="stylesheet" type="text/css" href="css/common.css" />
<!--[if lt IE 9]>
<link rel="stylesheet" type="text/css" href="css/ie8.css" />
<![endif]-->

<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.placeholder.min.js"></script>
<script type="text/javascript" src="js/jquery.form.min.js"></script>
<script type="text/javascript" src="js/jquery.extends.1.0.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/bootstrap/html5shiv.min.js"></script>
<script type="text/javascript" src="js/bootstrap/respond.min.js"></script>
<![endif]-->