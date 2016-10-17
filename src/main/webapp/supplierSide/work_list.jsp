<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>个人中心 - 我的工作 - 百百SOHO</title>
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
        <!-- nav-tabs -->
        <c:import url="centerTab.jsp"/>
        <!-- /nav-tabs -->
        <!-- tab-content -->
        <div class="tab-content cd-list p-work">
            <!-- 需求信息 START -->
            <div class="tab-pane fade in active">
                <!-- nav-tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#signUp" data-toggle="tab" class="signUp">已报名</a></li>
                    <li><a href="#signUpMeet" data-toggle="tab" class="signUpMeet">已约见</a></li>
                    <li><a href="#hire" data-toggle="tab" class="hire">已聘用</a></li>
                    <li><a href="#ongoing" data-toggle="tab" class="ongoing">进行中</a></li>
                    <li><a href="#complete" data-toggle="tab" class="complete">已完成</a></li>
                </ul>
                <!-- /nav-tabs -->
                <!-- tab-content -->
                <div class="tab-content">
                    <!-- tab-pane 已报名 start -->
                    <div class="tab-pane fade in active" id="signUp">
                        <div class="dataList"></div>
                        <div class="paging signUpPaging"></div>
                    </div>

                    <!-- tab-pane 已报名 end -->
                    <!-- tab-pane 约见 start -->
                    <div class="tab-pane fade" id="signUpMeet">
                        <div class="dataList"></div>
                        <div class="paging signUpMeetPaging"></div>
                    </div>
                    <!-- tab-pane 约见 end -->
                    <!-- tab-pane 聘用 start -->
                    <div class="tab-pane fade" id="hire">
                        <div class="dataList"></div>
                        <div class="paging hirePaging"></div>
                    </div>
                    <!-- tab-pane 聘用 end -->
                    <!-- tab-pane 进行中 start -->
                    <div class="tab-pane fade" id="ongoing">
                    </div>
                    <div class="paging ongoingPaging"></div>
                    <!-- tab-pane 进行中 end -->
                    <!-- tab-pane 进行中 start -->
                    <div class="tab-pane fade" id="complete">
                        <div class="p-left fl">
                            <dl class="tit">
                                <dt>
                                <h3>江苏省驿亁元科技有限公司</h3></dt>
                                <dd>
                                    <p>2016-08到2016-10</p>
                                </dd>
                            </dl>
                            <dl class="rDesc wd390 fl">
                                <dt>招聘岗位：</dt>
                                <dd>设计师、工程师、架构师</dd>
                            </dl>
                            <dl class="rDesc wd390 fl">
                                <dt>应征岗位：</dt>
                                <dd>设计师</dd>
                            </dl>
                            <dl class="rDesc">
                                <dt>招聘人数：</dt>
                                <dd>4人</dd>
                            </dl>
                            <dl class="rDesc">
                                <dt>招聘需求：</dt>
                                <dd>3年以上程序员、PHP语言、JAVA语言精通</dd>
                            </dl>
                        </div>
                        <div class="p-right fr">
                            <div class="complete">已完成</div>
                            <div class="clearfix"></div>
                            <div class="btn-box">
                                <a href="javascript:;" class="btn" data-toggle="modal" data-target="#meetMsg">详情</a>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="bottom-btn-box">
                            <a href="javascript:;" class="btn-apply">去找工作</a>
                        </div>
                    </div>
                    <div class="paging completePaging"></div>
                    <!-- tab-pane 进行中 end -->
                </div>
                <!-- /tab-content -->
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

<!-- meet 约见信息-->
<div class="modal fade" id="meetDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- relieveDeal -->
                <form action="#" method="post" class="cancelMeetMsg">
                    <h3>约见信息</h3>
                    <br>
                    <table>
                        <tr>
                            <th>约见时间：</th>
                            <td style="vertical-align: middle;" name="meetTime"></td>
                        </tr>
                        <tr>
                            <th>约见地址：</th>
                            <td style="vertical-align: middle;" name="address"></td>
                        </tr>
                        <tr>
                            <th>联系人：</th>
                            <td style="vertical-align: middle;" name="contact"></td>
                        </tr>
                        <tr>
                            <th>联系电话：</th>
                            <td style="vertical-align: middle;" name="mobileNumber"></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td style="vertical-align: middle;" name="telephoneNumber"></td>
                        </tr>
                    </table>
                    <br>
                    <div class="btn-box">
                        <button type="button" class="btn meetDisposeBtn" data-status="1">同意</button>
                        <button type="submit" class="btn meetDisposeBtn" data-status="2">拒绝</button>
                        <button type="button" class="btn btn-cancel">取消</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- meet 约见信息-->
<!-- meet 聘用信息-->
<div class="modal fade" id="hireDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- relieveDeal -->
                <form action="#" method="post" class="cancelMeetMsg">
                    <h3>约见信息</h3>
                    <br>
                    <table>
                        <tr>
                            <th>工作岗位：</th>
                            <td style="vertical-align: middle;" name="work"></td>
                        </tr>
                        <tr>
                            <th>合同期限：</th>
                            <td style="vertical-align: middle;" name="contractPeriod"></td>
                        </tr>
                        <tr>
                            <th>合约薪水（月薪）：</th>
                            <td style="vertical-align: middle;" name="remuneration"></td>
                        </tr>
                    </table>
                    <br>
                    <div class="btn-box">
                        <button type="button" class="btn hireDisposeBtn" data-status="1">同意</button>
                        <button type="submit" class="btn hireDisposeBtn" data-status="2">拒绝</button>
                        <button type="button" class="btn btn-cancel">取消</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- meet 聘用信息-->
<!-- error msg 错误信息弹窗 -->
<div class="modal fade" id="errorMsgDialog" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- relieveDeal -->
                <form action="#" method="post" class="meetMsg">
                    <div class="errorMsg" style="font-size: 2em;padding-bottom: 20px;text-align: center;"></div>
                    <div class="btn-box">
                        <button type="button" class="btn btn-cancel">确定</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- /rror msg 错误信息弹窗 -->
</body>
<script>
    //新窗口打开对象
    var winOpen = null;
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
            $("#signUp .dataList").html("");

            $.ajax({
                type: "GET",
                url: "/supplierSide/demand/signUpDemand",
                data: $.extend({}, signUpPagination.data, signUpPagination.page),
                success: function (resp) {
                    if (resp.pageNo == null) {
                        if (winOpen == null) {
                            winOpen = window.open('about:blank');
                            winOpen.document.write(resp);
                            winOpen.document.close();
                        }
                        return;
                    }

                    //设置分页
                    signUpPagination.page.pageNo = resp.pageNo;
                    signUpPagination.page.pageCount = resp.pageCount;
                    signUpPagination.init();

                    var row = "";
                    if (resp.totalCount > 0) {
                        $(resp.list).each(function (i) {
                            row += '<p class="meet fr"></p>'
                                    + '<div class="p-left fl">'
                                    + '<dl class="tit"><dt data-id="' + this.id + '"><h3>' + this.name + '</h3></dt><dd><p>' + this.beginCycle + '到' + this.endCycle + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + '</p></dd></dl>'
                                    + '<dl class="rDesc wd390 fl"><dt>招聘岗位：</dt><dd>' + this.work + '</dd></dl>'
                                    + '<dl class="rDesc wd390 fl"><dt>应征岗位：</dt><dd>' + this.signUpWork + '</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘人数：</dt><dd>' + this.personsAmount + '人</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘需求：</dt><dd>' + this.demandDiscribe + '</dd></dl>'
                                    + '</div>'
                                    + '<div class="clearfix"></div>';

                            if (resp.list.length - 1 > i) {
                                row += '<div class="bottom-btn-box"></div>';
                            }
                        });
                    }

                    $("#signUp .dataList").html(row + '<div class="bottom-btn-box"><a href="/demand/demand_list.jsp?menuNavigation=demandHall" class="btn-apply">去找工作</a></div>');
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
            $("#signUpMeet .dataList").html("");

            $.ajax({
                type: "GET",
                url: "/supplierSide/demand/signUpMeetDemand",
                data: $.extend({}, signUpMeetPagination.data, signUpMeetPagination.page),
                success: function (resp) {
                    if (resp.pageNo == null) {
                        if (winOpen == null) {
                            winOpen = window.open('about:blank');
                            winOpen.document.write(resp);
                            winOpen.document.close();
                        }
                        return;
                    }

                    //设置分页
                    signUpMeetPagination.page.pageNo = resp.pageNo;
                    signUpMeetPagination.page.pageCount = resp.pageCount;
                    signUpMeetPagination.init();

                    var row = "";
                    if (resp.totalCount > 0) {
                        $(resp.list).each(function (i) {
                            row += '<p class="meet fr"></p>'
                                    + '<div class="p-left fl">'
                                    + '<dl class="tit"><dt data-id="' + this.id + '"><h3>' + this.name + '</h3></dt><dd><p>' + this.beginCycle + '到' + this.endCycle + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + (undefined == this.statusText ? "" : this.statusText) + '</p></dd></dl>'
                                    + '<dl class="rDesc wd390 fl"><dt>招聘岗位：</dt><dd>' + this.work + '</dd></dl>'
                                    + '<dl class="rDesc wd390 fl"><dt>应征岗位：</dt><dd>' + this.signUpWork + '</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘人数：</dt><dd>' + this.personsAmount + '人</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘需求：</dt><dd>' + this.demandDiscribe + '</dd></dl>'
                                    + '</div>'
                                    + '<div class="p-right fr">'
                                    + '<div class="clearfix"></div>'
                                    + '<div class="btn-box">'
                                    + '<a href="javascript:;" class="btn meetInfoBtn" data-toggle="modal" data-target="#meetDialog" data-demandId="' + this.id + '" data-status="' + this.status + '">约见信息</a>'
                                    + '</div>'
                                    + '</div>'
                                    + '<div class="clearfix"></div>';

                            if (resp.list.length - 1 > i) {
                                row += '<div class="bottom-btn-box"></div>';
                            }
                        });
                    }

                    $("#signUpMeet .dataList").html(row + '<div class="bottom-btn-box"><a href="/demand/demand_list.jsp?menuNavigation=demandHall" class="btn-apply">去找工作</a></div>');
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
            $(".hirePaging").createPage({
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
            $("#hire .dataList").html("");

            $.ajax({
                type: "GET",
                url: "/supplierSide/demand/hireDemand",
                data: $.extend({}, hirePagination.data, hirePagination.page),
                success: function (resp) {
                    if (resp.pageNo == null) {
                        if (winOpen == null) {
                            winOpen = window.open('about:blank');
                            winOpen.document.write(resp);
                            winOpen.document.close();
                        }
                        return;
                    }

                    //设置分页
                    hirePagination.page.pageNo = resp.pageNo;
                    hirePagination.page.pageCount = resp.pageCount;
                    hirePagination.init();

                    var row = "";
                    if (resp.totalCount > 0) {
                        $(resp.list).each(function (i) {
                            row += '<p class="meet fr"></p>'
                                    + '<div class="p-left fl">'
                                    + '<dl class="tit"><dt data-id="' + this.id + '"><h3>' + this.name + '</h3></dt><dd><p>' + this.beginCycle + '到' + this.endCycle + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + (undefined == this.statusText ? "" : this.statusText) + '</p></dd></dl>'
                                    + '<dl class="rDesc wd390 fl"><dt>招聘岗位：</dt><dd>' + this.work + '</dd></dl>'
                                    + '<dl class="rDesc wd390 fl"><dt>应征岗位：</dt><dd>' + this.signUpWork + '</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘人数：</dt><dd>' + this.personsAmount + '人</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘需求：</dt><dd>' + this.demandDiscribe + '</dd></dl>'
                                    + '</div>'
                                    + '<div class="p-right fr">'
                                    + '<div class="clearfix"></div>'
                                    + '<div class="btn-box">'
                                    + '<a href="javascript:;" class="btn hireInfoBtn" data-toggle="modal" data-target="#hireDialog" data-demandId="' + this.id + '" data-status="' + this.status + '">合约信息</a>'
                                    + '</div>'
                                    + '</div>'
                                    + '<div class="clearfix"></div>';

                            if (resp.list.length - 1 > i) {
                                row += '<div class="bottom-btn-box"></div>';
                            }
                        });
                    }

                    $("#hire .dataList").html(row + '<div class="bottom-btn-box"><a href="/demand/demand_list.jsp?menuNavigation=demandHall" class="btn-apply">去找工作</a></div>');
                }
            });
        }
    };

    $(function () {
        //获取报名列表数据
        signUpPagination.list();

        //获取约见列表数据
        signUpMeetPagination.list();

        //获取聘用列表数据
        hirePagination.list();

        //点击约见信息按钮，异步查询约见信息并填充到弹窗
        $(document).on("click", ".meetInfoBtn", function () {
            if ($(this).data("status") != undefined && $(this).data("status") != "") {
                $("#meetDialog .meetDisposeBtn").hide();
            } else {
                $("#meetDialog .meetDisposeBtn").attr("data-demandId", $(this).attr("data-demandId"));
                $("#meetDialog .meetDisposeBtn").show();
            }

            var _this = this;
            $.ajax({
                url: "/supplierSide/demand/demandMeetInfo/" + $(_this).attr("data-demandId"),
                type: "GET",
                success: function (resp) {
                    $("#meetDialog td[name='meetTime']").text(resp.meetTime);
                    $("#meetDialog td[name='address']").text(resp.address);
                    $("#meetDialog td[name='contact']").text(resp.contact + resp.respectfulName);
                    $("#meetDialog td[name='mobileNumber']").text(resp.mobileNumber);

                    var telephoneNumber = '';
                    if (resp.phoneAreaCode != undefined) {
                        telephoneNumber += resp.phoneAreaCode;
                    }
                    if (resp.telephoneNumber != undefined) {
                        telephoneNumber += " " + resp.telephoneNumber;
                    }
                    if (resp.extensionNumber != undefined) {
                        telephoneNumber += " " + resp.extensionNumber;
                    }
                    $("#meetDialog td[name='telephoneNumber']").text(telephoneNumber);
                }
            })
        });

        //点击合同信息按钮，异步查询聘用合同信息并填充到弹窗
        $(document).on("click", ".hireInfoBtn", function () {
            if ($(this).data("status") != undefined && $(this).data("status") != "") {
                $("#hireDialog .hireDisposeBtn").hide();
            } else {
                $("#hireDialog .hireDisposeBtn").attr("data-demandId", $(this).attr("data-demandId"));
                $("#hireDialog .hireDisposeBtn").show();
            }

            var _this = this;
            $.ajax({
                url: "/supplierSide/demand/demandContractInfo/" + $(_this).attr("data-demandId"),
                type: "GET",
                success: function (resp) {
                    $("#hireDialog td[name='contractPeriod']").html(resp.contractComesIntoEffectTime + "<br> " + resp.contractExpiresTime);
                    $("#hireDialog td[name='remuneration']").text(resp.remuneration);
                    $("#hireDialog td[name='work']").text(resp.workText);
                }
            })
        });

        //约见处理按钮点击事件
        $(document).on("click", ".meetDisposeBtn", function () {
            var _this = this;
            $.ajax({
                url: "/supplierSide/demand/demandMeetDispose/" + $(_this).attr("data-demandId"),
                data: {"status": $(_this).data("status")},
                type: "POST",
                success: function (resp) {
                    if (resp.code != "200") {
                        $("#errorMsgDialog").modal('show').find(".errorMsg").text(resp.message);
                    } else {
                        document.location.reload();
                    }
                }
            })
        });

        //聘用处理按钮点击事件
        $(document).on("click", ".hireDisposeBtn", function () {
            var _this = this;
            $.ajax({
                url: "/supplierSide/demand/demandHireDispose/" + $(_this).attr("data-demandId"),
                data: {"status": $(_this).data("status")},
                type: "POST",
                success: function (resp) {
                    if (resp.code != "200") {
                        $("#errorMsgDialog").modal('show').find(".errorMsg").text(resp.message);
                    } else {
                        document.location.reload();
                    }
                }
            })
        });

        //弹窗关闭
        $(document).on('click', '.modal .btn-cancel', function () {
            $(this).parents(".modal").modal('hide');
        });

        //点击标题前往详情
        $(document).on("click", ".tit dt[data-id]", function () {
            document.location.href = 'demand/demand_info.jsp?menuNavigation=demandHall&demandId=' + $(this).data("id");
        })
    })
</script>
</html>