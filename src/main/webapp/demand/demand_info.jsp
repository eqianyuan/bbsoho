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
                    <th colspan="2">
                        <h3>需求详情</h3>
                    </th>
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
                </table>
                <div class="close-apply-list">
                    <table class="job-detail">
                        <thead>
                        <th colspan="5"><h3>需求用工</h3></th>
                        </thead>
                        <tbody class="demandEmployPersons">
                        </tbody>
                    </table>
                </div>
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
<div class="modal fade" id="signUpTip" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body" style="padding-bottom:10px;">
                <!-- relieveDeal -->
                <form action="#" method="post" class="relieveApply">
                    <div>
                        <h3>
                        </h3>
                    </div>
                    <br>
                    <div class="btn-box">
                        <button type="button" class="btn btn-cancel">关闭</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- -->
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
                        var btn = '';
                        if ("${demandUserByLogin}" == "") {
                            btn = '<td><a href="javascript:;" class="btn-close-apply signUp" data-work="' + this.work + '">报名</a></td>';
                        }

                        var demandEmployPersonsHtml = '<tr>'
                                + '<td>' + this.workText + '</td>'
                                + '<td>经验：' + this.workingYears + '</td>'
                                + '<td>月薪酬：' + this.remuneration + '</td>'
                                + '<td>用人：' + this.personsAmount + '</td>'
                                + btn
                                + '</tr>';


                        $(".demandEmployPersons").append(demandEmployPersonsHtml);
                    });
                }
            }
        })
    }

    $(function () {
        getDemand();

        //报名按钮点击事件
        $(document).on("click", ".signUp", function () {
            $.ajax({
                type: "POST",
                url: "/supplierSide/demand/signUp/${param.demandId}/" + $(this).data("work"),
                success: function (resp) {
                    if (resp.code == null) {
                        var w = window.open('about:blank');
                        w.document.write(resp);
                        w.document.close();
                        return;
                    }

                    if (resp.code == "200") {
                        $('#signUpTip').modal('show').find("h3").text("报名成功");
                    } else {
                        $('#signUpTip').modal('show').find("h3").text(resp.message);
                    }
                }
            });
        });

        /**
         * 弹窗关闭
         */
        $(document).on('click', 'button.btn-cancel', function () {
            $('#signUpTip').modal('hide');
        });
    });
</script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/bootstrap/html5shiv.min.js"></script>
<script type="text/javascript" src="js/bootstrap/respond.min.js"></script>
<![endif]-->
</body>
</html>