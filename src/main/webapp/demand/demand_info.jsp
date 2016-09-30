<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>需求信息 - 百百SOHO</title>
    <c:import url="../common_inport.jsp"/>
</head>
<body class="pt97">
<!-- header -->
<c:import url="../head.jsp"/>
<!-- /header -->
<!-- mainer -->
<div class="mainer personal">
    <div class="container">
        <!-- tab-content -->
        <div class="tab-content company-demand">
            <!-- 需求信息 START -->
            <div class="tab-pane fade  in active">
                <!-- job-detail -->
                <table class="job-detail bd-line">
                    <thead>
                    <th colspan="2"><h3>需求详情</h3></th>
                    </thead>
                    <tbody style="float: left;">
                    <tr>
                        <th>需求名称：</th>
                        <td class="name"></td>
                    </tr>
                    <tr>
                        <th>工作餐：</th>
                        <td class="workingLunch"></td>
                    </tr>
                    <tr>
                        <th>需求周期：</th>
                        <td class="cycle"></td>
                    </tr>
                    <tr>
                        <th>联系人：</th>
                        <td class="contact"></td>
                    </tr>
                    <tr>
                        <th>联系电话：</th>
                        <td class="mobileNumber"></td>
                    </tr>
                    <tr>
                        <th>工作内容：</th>
                        <td class="workContent"></td>
                    </tr>
                    <tr>
                        <th>工作地址：</th>
                        <td class="workArea"></td>
                    </tr>
                    <tr>
                        <th>详细地址：</th>
                        <td class="address"></td>
                    </tr>
                    </tbody>
                    <thead>
                    <th colspan="2"><h3>需求用工</h3></th>
                    </thead>
                    <tbody style="float: left;" class="demandEmployPersons">
                    </tbody>
                </table>
                <!-- /job-detail -->
                <!-- job-detail -->
                <%--<table class="job-detail project mt30">--%>
                <%--<thead>--%>
                <%--<th colspan="5"><h3>用工信息</h3></th>--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<tr>--%>
                <%--<th colspan="3"><a href="javascript:;" class="tit">王百百 Unity</a></th>--%>
                <%--<th rowspan="2">--%>
                <%--<div class="score">--%>
                <%--<p>个人平均评分</p>--%>
                <%--<p class="star star_04">80分</p>--%>
                <%--</div>--%>
                <%--<div class="btn-box">--%>
                <%--<a href="javascript:;" class="btn">发放薪水</a>--%>
                <%--<a href="javascript:;" class="btn">联系方式</a>--%>
                <%--<a href="javascript:;" class="btn" data-toggle="modal"--%>
                <%--data-target="#relieveDeal">协议解除</a>--%>
                <%--</div>--%>
                <%--</th>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td class="wd130"><a href="javascript:;" class="img"><img src="images/personal/logo.png"--%>
                <%--alt="1"/></a></td>--%>
                <%--<td class="wd130">--%>
                <%--<p>薪资总和</p><br/>--%>
                <%--<p><span class="price">27,000</span>元</p>--%>
                <%--</td>--%>
                <%--<td class="wd130">--%>
                <%--<p>周薪</p><br/>--%>
                <%--<p><span class="price">2,250</span>元</p>--%>
                <%--</td>--%>
                <%--</tr>--%>

                <%--<tr>--%>
                <%--<th colspan="3"><a href="javascript:;" class="tit">王百百 Unity</a></th>--%>
                <%--<th rowspan="2">--%>
                <%--<div class="score">--%>
                <%--<p>个人平均评分</p>--%>
                <%--<p class="star star_04">80分</p>--%>
                <%--</div>--%>
                <%--<div class="btn-box">--%>
                <%--<a href="javascript:;" class="btn">发放薪水</a>--%>
                <%--<a href="javascript:;" class="btn">联系方式</a>--%>
                <%--<a href="javascript:;" class="btn" data-toggle="modal"--%>
                <%--data-target="#relieveDeal">协议解除</a>--%>
                <%--</div>--%>
                <%--</th>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td class="wd130"><a href="javascript:;" class="img"><img src="images/personal/logo.png"--%>
                <%--alt="1"/></a></td>--%>
                <%--<td class="wd130">--%>
                <%--<p>薪资总和</p><br/>--%>
                <%--<p><span class="price">27,000</span>元</p>--%>
                <%--</td>--%>
                <%--<td class="wd130">--%>
                <%--<p>周薪</p><br/>--%>
                <%--<p><span class="price">2,250</span>元</p>--%>
                <%--</td>--%>
                <%--</tr>--%>

                <%--<tr>--%>
                <%--<th colspan="3"><a href="javascript:;" class="tit">王百百 Unity</a></th>--%>
                <%--<th rowspan="2">--%>
                <%--<div class="score">--%>
                <%--<p>个人平均评分</p>--%>
                <%--<p class="star star_04">80分</p>--%>
                <%--</div>--%>
                <%--<div class="btn-box">--%>
                <%--<a href="javascript:;" class="btn">发放薪水</a>--%>
                <%--<a href="javascript:;" class="btn">联系方式</a>--%>
                <%--<a href="javascript:;" class="btn" data-toggle="modal"--%>
                <%--data-target="#relieveDeal">协议解除</a>--%>
                <%--</div>--%>
                <%--</th>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                <%--<td class="wd130"><a href="javascript:;" class="img"><img src="images/personal/logo.png"--%>
                <%--alt="1"/></a></td>--%>
                <%--<td class="wd130">--%>
                <%--<p>薪资总和</p><br/>--%>
                <%--<p><span class="price">27,000</span>元</p>--%>
                <%--</td>--%>
                <%--<td class="wd130">--%>
                <%--<p>周薪</p><br/>--%>
                <%--<p><span class="price">2,250</span>元</p>--%>
                <%--</td>--%>
                <%--</tr>--%>
                <%--</tbody>--%>
                <%--</table>--%>
                <!-- /job-detail -->
            </div>
            <!-- 需求信息 END -->
        </div>
        <!-- /tab-content -->
    </div>
</div>
<!-- /mainer -->
<!-- footer -->
<c:import url="../footer.jsp"/>
<!-- /footer -->
<!-- -->
<!-- relieveDeal -->
<div class="modal fade" id="relieveDeal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- relieveDeal -->
                <form action="#" method="post" class="relieveDeal">
                    <h3>协议解除</h3>
                    <textarea class="tta" name="message" placeholder="请填写协议解除原因，并与您的自由员工联系。"></textarea>
                    <div class="error"></div>
                    <div class="btn-box">
                        <button type="button" class="btn">联系我们</button>
                        <button type="submit" class="btn">确定</button>
                        <button type="button" class="btn btn-cancel">取消</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- /relieveDeal -->
<script type="text/javascript">
    //获取需求信息并填充数据
    var getDemand = function () {
        $.ajax({
            url: "/demandSide/search/demand/${param.demandId}",
            type: "GET",
            success: function (resp) {
                //需求名称
                $(".name").text(resp.name);
                //工作餐
                $(".workingLunch").text(resp.workingLunch);
                //需求周期-开始日期
                $(".cycle").text(resp.beginCycle + " - " + resp.endCycle);
                //联系人
                $(".contact").text(resp.contact + resp.respectfulName);
                //联系人手机号码
                $(".mobileNumber").html(resp.mobileNumber + "</br>" + ("" != resp.phoneAreaCode ? resp.phoneAreaCode + "-" : "") + (undefined != resp.telephoneNumber ? resp.telephoneNumber : "") + ("" != resp.extensionNumber ? "-" + resp.extensionNumber : ""));
                //工作内容描述
                $(".workContent").text(resp.demandDiscribe);
                //工作地区
                $(".workArea").text(resp.province + resp.city + resp.county);
                //工作详细地址
                $(".address").text(resp.address);

                if (resp.demandEmployPersonsList.length > 0) {
                    $(resp.demandEmployPersonsList).each(function () {
                        var demandEmployPersonsHtml = '<tr><th>用人数：</th><td>' + this.personsAmount + '</td>'
                                + '<th>工种：</th><td>' + this.workText + '</td>'
                                + '<th>工作经验：</th><td>' + this.workingYears + '</td>'
                                + '<th>薪酬：</th><td>' + this.remuneration + '</td></tr>';

                        $(".demandEmployPersons").append(demandEmployPersonsHtml);
                    });
                }
            }
        })
    }

    $(function () {
        getDemand();

        //取消
//        $(document).on('click', 'form.relieveDeal button.btn-cancel', function(){
//            $('#relieveDeal').modal('hide');
//        });

        //解除协议校验
//        $('form.relieveDeal').validate({
//            errorLabelContainer: $("form.company-form div.error"),
//            wrapper: "p",
//            ignore: ".ignore",
//            submitHandler:function(form){
//                alert("注册提交事件!");
//                form.submit();
//            },
//            rules : {
//                "message" : { required : true }
//            },
//            messages : {
//                "message" : { required : "请输入原因" }
//            }
//        });
    });
</script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/bootstrap/html5shiv.min.js"></script>
<script type="text/javascript" src="js/bootstrap/respond.min.js"></script>
<![endif]-->
</body>
</html>