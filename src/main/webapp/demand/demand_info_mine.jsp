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
    <script type="text/javascript" src="js/eqianyuan.page.js"></script>
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
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#signUp" data-toggle="tab">已报名</a></li>
                    <li><a href="#signUpMeet" data-toggle="tab">已约见</a></li>
                    <li><a href="#hire" data-toggle="tab">已聘用</a></li>
                </ul>
                <!-- /nav-tabs -->
                <!-- tab-content -->
                <div class="tab-content applyHire">
                    <!-- tab-pane 已报名 start -->
                    <div class="tab-pane fade in active" id="signUp">
                        <!-- job-detail -->
                        <table class="job-detail project mb30">
                            <tbody>
                            </tbody>
                        </table>
                        <!-- /job-detail -->
                        <!-- paging -->
                        <div class="paging signUpPaging"></div>
                        <!-- /paging -->
                    </div>
                    <!-- tab-pane 已报名 end -->
                    <!-- tab-pane 已约见 start -->
                    <div class="tab-pane fade" id="signUpMeet">
                        <!-- job-detail -->
                        <table class="job-detail project mb30">
                            <tbody>
                            </tbody>
                        </table>
                        <!-- /job-detail -->
                        <!-- paging -->
                        <div class="paging signUpMeetPaging"></div>
                        <!-- /paging -->
                    </div>
                    <!-- tab-pane 已约见 end -->
                    <!-- tab-pane 已聘用 start -->
                    <div class="tab-pane fade" id="hire">
                        <!-- job-detail -->
                        <table class="job-detail project mb30">
                            <tbody>
                            </tbody>
                        </table>
                        <!-- /job-detail -->
                        <!-- paging -->
                        <div class="paging hirePaging"></div>
                        <!-- /paging -->
                    </div>
                    <!-- tab-pane 已聘用 end -->
                </div>
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

    //报名分页查询组件
    var signUpPagination = {
        initStatus: false,      //分页插件初始化状态-true:已经构建了分页插件、false:还未构建
        data: {},
        page: {
            pageNo: 1,
            pageSize: 10
        },
        init: function () {
            //初始化分页
            $(".signUpPaging").createPage({
                pageCount: signUpPagination.page.pageCount,
                current: signUpPagination.page.pageNo,
                initStatus: signUpPagination.initStatus,
                backFn: function (pageNo) {
                    signUpPagination.page.pageNo = pageNo;
                    signUpPagination.list();
                }
            });

            signUpPagination.initStatus = true;
        },
        list: function () {
            $("#signUp tbody").html("");

            $.ajax({
                type: "GET",
                url: "/demandSide/signUpByDemand/${param.demandId}",
                data: $.extend({}, signUpPagination.data, signUpPagination.page),
                success: function (resp) {
                    if (resp.pageNo == null) {
                        document.write(resp);
                        return;
                    }

                    //设置分页
                    signUpPagination.page.pageNo = resp.pageNo;
                    signUpPagination.page.pageCount = resp.pageCount;
                    signUpPagination.init();

                    var row = "";
                    if (resp.totalCount > 0) {
                        $(resp.list).each(function (i) {
                            var head = '/images/head.png';
                            if (this.headPortrait != undefined && this.headPortrait != "") {
                                head = this.headPortrait;
                            }

                            row += '<tr>'
                                    + '<th colspan="3"><a href="javascript:;" class="tit" data-id="' + this.id + '">' + this.nickName + "&nbsp;&nbsp;" + this.signUpWork + "&nbsp;&nbsp;" + this.signUpTime + '</a></th>'
                                    + '<th rowspan="2">'
                                    + '<div class="btn-box">'
                                    + '<a href="javascript:;" class="btn">约见</a>'
                                    + '</div>'
                                    + '</th>'
                                    + '</tr>'
                                    + '<tr>'
                                    + '<td class="wd130"><a href="javascript:;" class="img"><img src="' + head + '"/></a></td>'
                                    + '<td class="wd130">'
                                    + '<p>工作年限</p><br />'
                                    + '<p><span class="price">' + this.workingYears + '</span></p>'
                                    + '</td>'
                                    + '<td class="wd200">'
                                    + '<p>期望薪资</p><br />'
                                    + '<p><span class="price">' + this.expectPay + '</span>元</p>'
                                    + '</td>'
                                    + '</tr>';
                        });
                    }

                    $("#signUp tbody").html(row);
                }
            });
        }
    };

    //约见分页查询组件
    var signUpMeetPagination = {
        initStatus: false,      //分页插件初始化状态-true:已经构建了分页插件、false:还未构建
        data: {},
        page: {
            pageNo: 1,
            pageSize: 10
        },
        init: function () {
            //初始化分页
            $(".signUpMeetPaging").createPage({
                pageCount: signUpMeetPagination.page.pageCount,
                current: signUpMeetPagination.page.pageNo,
                initStatus: signUpMeetPagination.initStatus,
                backFn: function (pageNo) {
                    signUpMeetPagination.page.pageNo = pageNo;
                    signUpMeetPagination.list();
                }
            });

            signUpMeetPagination.initStatus = true;
        },
        list: function () {
            $("#signUpMeet tbody").html("");

            $.ajax({
                type: "GET",
                url: "/demandSide/signUpMeetByDemand/${param.demandId}",
                data: $.extend({}, signUpMeetPagination.data, signUpMeetPagination.page),
                success: function (resp) {
                    if (resp.pageNo == null) {
                        document.write(resp);
                        return;
                    }

                    //设置分页
                    signUpMeetPagination.page.pageNo = resp.pageNo;
                    signUpMeetPagination.page.pageCount = resp.pageCount;
                    signUpMeetPagination.init();

                    var row = "";
                    if (resp.totalCount > 0) {
                        $(resp.list).each(function (i) {
                            var head = '/images/head.png';
                            if (this.headPortrait != undefined && this.headPortrait != "") {
                                head = this.headPortrait;
                            }

                            var btn = '';
                            if (this.status == 1) {
                                btn = '<a href="javascript:;" class="btn">聘用</a>&nbsp;&nbsp;<a href="javascript:;" class="btn">不聘用</a>';
                            }

                            row += '<tr>'
                                    + '<th colspan="3"><a href="javascript:;" class="tit" data-id="' + this.id + '">' + this.nickName + "&nbsp;&nbsp;" + this.signUpWork + "&nbsp;&nbsp;" + this.meetTime + "&nbsp;&nbsp;" + (undefined != this.statusText ? this.statusText : "") + '</a></th>'
                                    + '<th rowspan="2">'
                                    + '<div class="btn-box">'
                                    + btn
                                    + '</div>'
                                    + '</th>'
                                    + '</tr>'
                                    + '<tr>'
                                    + '<td class="wd130"><a href="javascript:;" class="img"><img src="' + head + '"/></a></td>'
                                    + '<td class="wd130">'
                                    + '<p>工作年限</p><br />'
                                    + '<p><span class="price">' + this.workingYears + '</span></p>'
                                    + '</td>'
                                    + '<td class="wd200">'
                                    + '<p>期望薪资</p><br />'
                                    + '<p><span class="price">' + this.expectPay + '</span>元</p>'
                                    + '</td>'
                                    + '</tr>';
                        });
                    }

                    $("#signUpMeet tbody").html(row);
                }
            });
        }
    };

    //聘用分页查询组件
    var hirePagination = {
        initStatus: false,      //分页插件初始化状态-true:已经构建了分页插件、false:还未构建
        data: {},
        page: {
            pageNo: 1,
            pageSize: 10
        },
        init: function () {
            //初始化分页
            $(".signUpMeetPaging").createPage({
                pageCount: hirePagination.page.pageCount,
                current: hirePagination.page.pageNo,
                initStatus: hirePagination.initStatus,
                backFn: function (pageNo) {
                    hirePagination.page.pageNo = pageNo;
                    hirePagination.list();
                }
            });

            hirePagination.initStatus = true;
        },
        list: function () {
            $("#hire tbody").html("");

            $.ajax({
                type: "GET",
                url: "/demandSide/hireByDemand/${param.demandId}",
                data: $.extend({}, hirePagination.data, hirePagination.page),
                success: function (resp) {
                    if (resp.pageNo == null) {
                        document.write(resp);
                        return;
                    }

                    //设置分页
                    hirePagination.page.pageNo = resp.pageNo;
                    hirePagination.page.pageCount = resp.pageCount;
                    hirePagination.init();

                    var row = "";
                    if (resp.totalCount > 0) {
                        $(resp.list).each(function (i) {
                            var head = '/images/head.png';
                            if (this.headPortrait != undefined && this.headPortrait != "") {
                                head = this.headPortrait;
                            }

                            var btn = '';
                            if (this.status == 1) {
                                btn = '<a href="javascript:;" class="btn">支付薪水</a>';
                            }

                            row += '<tr>'
                                    + '<th colspan="4"><a href="javascript:;" class="tit" data-id="' + this.id + '">' + this.nickName + "&nbsp;&nbsp;" + this.work + "&nbsp;&nbsp;" + (undefined != this.statusText ? this.statusText : "") + '</a></th>'
                                    + '<th rowspan="2">'
                                    + '<div class="btn-box">'
                                    + btn
                                    + '</div>'
                                    + '</th>'
                                    + '</tr>'
                                    + '<tr>'
                                    + '<td class="wd130"><a href="javascript:;" class="img"><img src="' + head + '"/></a></td>'
                                    + '<td class="wd130">'
                                    + '<p>工作年限</p><br />'
                                    + '<p><span class="price">' + this.workingYears + '</span></p>'
                                    + '</td>'
                                    + '<td class="wd200">'
                                    + '<p>期望薪资</p><br />'
                                    + '<p><span class="price">' + this.expectPay + '</span>元</p>'
                                    + '</td>'
                                    + '<td class="wd200">'
                                    + '<p>合约薪水</p><br />'
                                    + '<p><span class="price">' + this.remuneration + '</span>元</p>'
                                    + '</td>'
                                    + '</tr>';
                        });
                    }

                    $("#hire tbody").html(row);
                }
            });
        }
    };

    $(function () {
        //获取需求基本信息
        getDemand();

        //获取已报名用户
        signUpPagination.list();

        //获取已约见用户
        signUpMeetPagination.list();

        //获取已聘用用户
        hirePagination.list();

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