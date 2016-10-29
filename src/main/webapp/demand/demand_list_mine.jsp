<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>我发布的需求 - 百百SOHO</title>
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
        <c:import url="/demandSide/centerTab.jsp"/>
        <!-- /nav-tabs -->
        <!-- tab-content -->
        <div class="tab-content cd-list">
            <!-- 需求信息 START -->
            <div class="tab-pane fade in active">
                <!-- nav-tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#ongoing" data-toggle="tab" class="ongoing">进行中</a></li>
                    <li><a href="#complete" data-toggle="tab" class="complete">已结束</a></li>
                </ul>
                <!-- /nav-tabs -->
                <!-- tab-content -->
                <div class="tab-content">
                    <!-- tab-pane 已发布 start -->
                    <div class="tab-pane fade in active" id="ongoing">
                    </div>
                    <div class="paging ongoingPaging"></div>
                    <!-- tab-pane 已发布 end -->
                    <!-- tab-pane 已完成 start -->
                    <div class="tab-pane fade" id="complete">
                    </div>
                    <div class="paging completePaging"></div>
                    <!-- tab-pane 已完成 end -->
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
<!-- payWrap 待支付信息 -->
<div class="modal fade" id="payWrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- relieveDeal -->
                <form action="#" method="post" class="payInfo-form">
                    <h3>项目一</h3>
                    <div class="payInfoBox">
                        <p>需求人数3人。</p>
                        <p><span class="fr">(最低)</span>总价：<span class="price">27000</span>/月</p>
                        <p><span class="fr">(项目总价一周预付)</span>应支付：<span class="realityPay">6750</span></p>
                    </div>
                    <div class="btn-box">
                        <button type="submit" class="btn btn-pay">确定支付</button>
                        <button type="button" class="btn btn-cancel">取消</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- /payWrap -->
<!-- payWrap 支付方式 -->
<div class="modal fade" id="payTypeWrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- payType-form -->
                <form action="#" method="post" class="payType-form">
                    <h3>项目一</h3>
                    <div class="select-pay-away">
                        <!-- ipt-select -->
                        <div class="ipt-select2">
                            <input type="radio" name="payType" value="accountBalance" checked/>
                            <p>账户余额</p>
                        </div>
                        <div class="ipt-select2">
                            <input type="radio" name="payType" value="iPaySweep"/>
                            <p>支付宝扫码支付</p>
                        </div>
                        <!-- /ipt-select -->
                    </div>
                    <div class="error"></div>
                    <div class="btn-box">
                        <button type="submit" class="btn btn-pay">确定</button>
                        <button type="button" class="btn btn-cancel">取消</button>
                    </div>
                </form>
                <!-- /payType-form -->
            </div>
        </div>
    </div>
</div>
<!-- /payWrap -->
<!-- inputPwdWrap 输入支付密码 -->
<div class="modal fade" id="inputPwdWrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- payType-form -->
                <form action="#" method="post" class="inputPwd-form">
                    <h3>支付密码</h3>
                    <div class="pay-group">
                        <input type="password" name="pwd1" value="" tabindex="1"/>
                        <input type="password" name="pwd2" value="" tabindex="2"/>
                        <input type="password" name="pwd3" value="" tabindex="3"/>
                        <input type="password" name="pwd4" value="" tabindex="4"/>
                        <input type="password" name="pwd5" value="" tabindex="5"/>
                        <input type="password" name="pwd6" value="" tabindex="6"/>
                        <div class="clearfix"></div>
                    </div>
                    <div class="error"></div>
                    <div class="btn-box">
                        <button type="submit" class="btn btn-pay">确定</button>
                        <button type="button" class="btn btn-clear">清除</button>
                        <button type="button" class="btn btn-cancel">取消</button>
                    </div>
                </form>
                <!-- /payType-form -->
            </div>
        </div>
    </div>
</div>
<!-- /inputPwdWrap -->
<!-- inputPwdWrap 二维码支付 -->
<div class="modal fade" id="QRCodeWrap" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd380">
        <div class="modal-content">
            <div class="modal-body">
                <!-- payType-form -->
                <form action="#" method="post" class="qr-code-form">
                    <h3>支付宝扫码支付</h3>
                    <p><img src="images/pay_code.png"></p>
                </form>
                <!-- /payType-form -->
            </div>
        </div>
    </div>
</div>
<!-- /inputPwdWrap -->
</body>
<script>
    //分页查询组件
    var pagination = {
        initStatus: false,      //分页插件初始化状态-true:已经构建了分页插件、false:还未构建
        data: {},
        page: {
            pageNo: 1,
            pageSize: 10,
            orderByColumn: 'publish_time',
            orderByType: 'desc'
        },
        init: function (obj, pagingObj) {
            //初始化分页
            $(pagingObj).createPage({
                pageCount: pagination.page.pageCount,
                current: pagination.page.pageNo,
                initStatus: pagination.initStatus,
                backFn: function (pageNo) {
                    pagination.page.pageNo = pageNo;
                    pagination.list(obj, pagingObj);
                }
            });

            pagination.initStatus = true;
        },
        list: function (obj, pagingObj) {
            $(".talents-list").html("");

            $.ajax({
                type: "GET",
                url: "/demandSide/mine/demandList",
                data: $.extend({}, pagination.data, pagination.page),
                success: function (response) {
                    if (response.pageNo == null) {
                        var w=window.open('about:blank');
                        w.document.write(response);
                        w.document.close();
                        return;
                    }

                    //设置分页
                    pagination.page.pageNo = response.pageNo;
                    pagination.page.pageCount = response.pageCount;
                    pagination.init(obj, pagingObj);

                    var row = "";
                    if (response.totalCount > 0) {
                        $(response.list).each(function (i) {

                            row += '<dl class="tit"><dt data-id="' + this.id + '"><h3>' + this.name + '</h3></dt><dd>' + this.beginCycle + '到' + this.endCycle + '</dd></dl>'
                                    + '<div class="btn-box"><!--<a href="javascript:;" class="btn" data-toggle="modal" data-target="#payWrap">待支付</a><a href="javascript:;" class="btn">详情</a>--></div>'
                                    + '<dl class="rDesc"><dt>招聘岗位：</dt><dd>' + this.workText + '</dd></dl>'
                                    + '<dl class="rDesc"><dt>招聘人数：</dt><dd>' + this.personsAmount + '人</dd></dl>';

                            if (response.list.length - 1 > i) {
                                row += '<div class="bottom-btn-box"></div>';
                            }
                        });
                    }

                    $(obj).html(row + '<div class="bottom-btn-box"><a href="demandSide/demandEdit.jsp?isPublish=true" class="btn-apply">发布需求</a></div>');
                }
            });
        }
    };

    $(function () {
        //获取列表数据
        pagination.list($("#ongoing"), $(".ongoingPaging"));

        //tab切换卡点击事件
        $(".ongoing, .complete").click(function () {
            pagination.page.pageNo = 1;
            if ($(this).hasClass("ongoing")) {
                $("#ongoing").show();
                $("#complete").hide();
                $(".ongoingPaging").show();
                $(".completePaging").hide();
                pagination.data.isEnd = "";
                pagination.list($("#ongoing"), $(".ongoingPaging"));
            } else {
                $("#complete").show();
                $("#ongoing").hide();
                $(".completePaging").show();
                $(".ongoingPaging").hide();
                pagination.data.isEnd = "true";
                pagination.list($("#complete"), $(".completePaging"));
            }
        });

        //点击标题前往详情
        $(document).on("click", ".tit dt[data-id]", function () {
            document.location.href = 'demand/demand_info_mine.jsp?menuNavigation=demandHall&demandId=' + $(this).data("id");
        })
    })
</script>
</html>