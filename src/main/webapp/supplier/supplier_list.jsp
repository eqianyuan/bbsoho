<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>人才库 - 百百SOHO</title>
    <c:import url="../common_inport.jsp"/>
</head>
<body class="pt97">
<!-- header -->
<c:import url="../head.jsp"/>
<!-- /header -->
<!-- lr-wrapper -->
<div class="lr-wrapper">
    <div class="lr-img"><img src="images/index/banner.png" alt="background-image"/></div>
</div>
<!-- /lr-wrapper -->
<!-- mainer -->
<div class="mainer pd30">
    <div class="container">
        <form class="search-form">
            <input type="hidden" name="countyId"/>                                   <!-- 地区 -->
            <input type="hidden" name="work"/>                                      <!-- 工种 -->
            <input type="hidden" name="workingYears"/>                              <!-- 工作经验 -->
        </form>
        <!-- talents-top -->
        <div class="talents-top">
            <dl class="city">
                <dt>期望工作城市：</dt>
                <dd>
                    <div class="tt-list">
                        <a href="javascript:;" data-target="city" data-value="320100000000" class="act">南京</a>
                    </div>
                </dd>
                <dd class="wd80">
                    <a href="javascript:;" class="more">更多</a>
                </dd>
            </dl>
            <dl class="work">
                <dt>期望工种：</dt>
                <dd class="wd80">
                    <a href="javascript:;" data-target="work" data-value="" class="allSelect act">不限</a>
                </dd>
                <dd>
                    <div class="tt-list">
                    </div>
                </dd>
                <dd class="wd80">
                    <a href="javascript:;" class="more">更多</a>
                </dd>
            </dl>
            <dl class="county">
                <dt>期望工作地区：</dt>
                <dd class="wd80">
                    <a href="javascript:;" data-target="countyId" data-value="" class="allSelect act">不限</a>
                </dd>
                <dd>
                    <div class="tt-list">
                    </div>
                </dd>
                <dd class="wd80">
                    <a href="javascript:;" class="more">更多</a>
                </dd>
            </dl>
            <dl class="workingYears">
                <dt>工作经验：</dt>
                <dd class="wd80">
                    <a href="javascript:;" data-target="workingYears" data-value="" class="allSelect act">不限</a>
                </dd>
                <dd>
                    <div class="tt-list">
                    </div>
                </dd>
                <dd class="wd80">
                    <a href="javascript:;" class="more">更多</a>
                </dd>
            </dl>
        </div>
        <!-- /talents-top -->
        <!-- talents-order -->
        <dl class="talents-order">
            <dt>排序：</dt>
            <dd>
                <a href="javascript:;" class="act" data-target="id" data-value="desc">默认顺序</a>
                <a href="javascript:;" data-target="working_years" data-value="desc">工作经验</a>
                <a href="javascript:;" data-target="expect_pay" data-value="desc">到岗时间</a>
            </dd>
        </dl>
        <!-- /talents-order -->
        <!-- talents-list -->
        <div class="talents-list">
        </div>
        <!-- /talents-list -->
        <!-- paging -->
        <div class="paging">
        </div>
        <!-- /paging -->
    </div>
</div>
<!-- /mainer -->
<!-- footer -->
<c:import url="../footer.jsp"/>
<!-- /footer -->
<script type="text/javascript" src="js/eqianyuan.page.js"></script>
</body>
<script>
    //获得工种查询条件
    var getWork = function (obj) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "work"},
            type: "GET",
            success: function (resp) {
                var workHtml = '';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        workHtml += '<a href="javascript:;" data-target="work" data-value="' + this.groupValKey + '">' + this.groupValVal + '</a>';
                    });
                }
                $(obj).html(workHtml);

                //搜索条件更多按钮事件
                var _dl = $(".work");
                var _list = _dl.find('div.tt-list').height('auto');
                var _more = _dl.find(".more");
                var _baseH = 40;
                if (_list.outerHeight(true) > _baseH) {
                    _more.show().data('flag', 0).on('click', function () {
                        if (!parseInt($(this).data('flag'))) {
                            $(this).data('flag', 1).text('收缩');
                            _list.height('auto');
                        } else {
                            $(this).data('flag', 0).text('更多');
                            _list.height(_baseH);
                        }
                    });
                    _list.height(_baseH);
                }
            }
        })
    }

    //获取地区查询条件
    var getCounty = function (obj) {
        $.ajax({
            url: "/area/getCounty",
            type: "GET",
            data: {cityId: 320100000000},
            success: function (resp) {
                var countyHtml = '';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        countyHtml += '<a href="javascript:;" data-target="countyId" data-value="' + this.countyId + '">' + this.countyName + '</a>';
                    });
                }
                $(obj).html(countyHtml);

                //搜索条件更多按钮事件
                var _dl = $(".county");
                var _list = _dl.find('div.tt-list').height('auto');
                var _more = _dl.find(".more");
                var _baseH = 40;
                if (_list.outerHeight(true) > _baseH) {
                    _more.show().data('flag', 0).on('click', function () {
                        if (!parseInt($(this).data('flag'))) {
                            $(this).data('flag', 1).text('收缩');
                            _list.height('auto');
                        } else {
                            $(this).data('flag', 0).text('更多');
                            _list.height(_baseH);
                        }
                    });
                    _list.height(_baseH);
                }
            }
        })
    }

    //获取工作年限字典数据
    var getWorkingYears = function (obj) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "working_years"},
            type: "GET",
            success: function (resp) {
                var workingYearsHtml = '';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        workingYearsHtml += '<a href="javascript:;" data-target="workingYears" data-value="' + this.groupValKey + '">' + this.groupValVal + '</a>';
                    });
                }
                $(obj).html(workingYearsHtml);

                //搜索条件更多按钮事件
                var _dl = $(".workingYears");
                var _list = _dl.find('div.tt-list').height('auto');
                var _more = _dl.find(".more");
                var _baseH = 40;
                if (_list.outerHeight(true) > _baseH) {
                    _more.show().data('flag', 0).on('click', function () {
                        if (!parseInt($(this).data('flag'))) {
                            $(this).data('flag', 1).text('收缩');
                            _list.height('auto');
                        } else {
                            $(this).data('flag', 0).text('更多');
                            _list.height(_baseH);
                        }
                    });
                    _list.height(_baseH);
                }
            }
        })
    }

    //分页查询组件
    var pagination = {
        initStatus: false,      //分页插件初始化状态-true:已经构建了分页插件、false:还未构建
        page: {
            pageNo: 1,
            pageSize: 10,
            orderByColumn: 'id',
            orderByType: 'desc'
        },
        init: function () {
            //初始化分页
            $(".paging").createPage({
                pageCount: pagination.page.pageCount,
                current: pagination.page.pageNo,
                initStatus: pagination.initStatus,
                backFn: function (pageNo) {
                    pagination.page.pageNo = pageNo;
                    pagination.list();
                }
            });

            pagination.initStatus = true;
        },
        list: function () {
            $(".talents-list").html("");

            var formDataJson = {};
            $($(".search-form").serializeArray()).each(function () {
                formDataJson[this.name] = this.value;
            });

            $.ajax({
                type: "GET",
                url: "/supplierSide/search/supplierList",
                data: $.extend({}, formDataJson, pagination.page),
                success: function (response) {
                    //设置分页
                    pagination.page.pageNo = response.pageNo;
                    pagination.page.pageCount = response.pageCount;
                    pagination.init();

                    if (response.totalCount > 0) {
                        var row = "";

                        $(response.list).each(function () {
                            var workIcon = '';
                            if (undefined != this.work && this.work != "") {
                                var work = this.work.split(",");
                                for (var i = 0; i < work.length; i++) {
                                    workIcon += '<span class="sw-icon"><img src="images/supplier/icon_' + work[i] + '.png" title="' + work[i] + '"/></span>';
                                }
                            }

                            var _discribe = this.discribe;
                            if (_discribe != null && _discribe != "") {
                                if (_discribe.length > 30) {
                                    _discribe = _discribe.substring(0, 30) + " ...";
                                }
                            }

                            var btn = '';
                            if ("${supplierUserByLogin}" == "") {
                                btn = '<dd><a href="javascript:;" class="btn-buy">约见</a></dd>';
                            }

                            row += '<div class="row">'
                                    + '<dl>'
                                    + '<dt>'
                                    + '<a href="/supplier/supplier_info.jsp?menuNavigation=talentPool&supplierId=' + this.id + '" class="tit">' + this.nickName + '</a>'
                                    + '<!--<span class="grade">综合评分：<span class="orange">80分</span></span>-->'
                                    + '</dt>'
                                    + '</dl>'
                                    + '<dl>'
                                    + '<dd class="wd">'
                                    + '<p class="pd8">熟悉软件</p>'
                                    + '<p>'
                                    + workIcon
                                    + '</p>'
                                    + '</dd>'
                                    + '<dd>'
                                    + '<p class="pd8">工作年限</p>'
                                    + '<p class="ageLimit"><span class="orange large">' + this.workingYears + '</span></p>'
                                    + '</dd>'
                                    + '<dd>'
                                    + '<p class="pd8">期望薪资</p>'
                                    + '<p><span class="orange large">' + this.expectPay + '</span><span class="gray">元</span></p>'
                                    + '</dd>'
                                    + '<dd>'
                                    + '<p>擅长方向：'
                                    + '<span class="gray pdt5">' + _discribe + '</span>'
                                    + '</p>'
                                    + '</dd>'
                                    + '<dd>'
                                    + '<p>工作地点：<span class="gray">' + this.countyName + '</span></p>'
                                    + '<p>工作时间：<span class="gray">' + this.expectWorkTime + '</span></p>'
                                    + '</dd>'
                                    + btn
                                    + '</dl>'
                                    + '</div>';
                        });

                        $(".talents-list").html(row);
                    } else {
                        $(".talents-list").html('<div class="row"><img src="images/supplier/no_data.png"/></div>');
                    }
                }
            });
        }
    };

    //搜索条件点击事件
    function talentsTopLoad() {
        $("div.talents-top").on("click", "a[data-target][data-value]", function () {
            var _target = $(this).data('target');
            var _value = $(this).data('value');

            $('a[data-target=' + _target + '][data-value]').removeClass('act');
            $(this).addClass('act');

            $("input[name='" + _target + "'").val(_value);

            pagination.page.pageNo = 1;
            //获取列表数据
            pagination.list();
        });
    }

    $(function () {
        //获取列表数据
        pagination.list();

        //获取工种查询条件
        getWork($(".work .tt-list"))
        //获取工作地区查询条件
        getCounty($(".county .tt-list"));
        //获取工作年限查询条件
        getWorkingYears($(".workingYears .tt-list"));
        //搜索条件点击事件
        talentsTopLoad();

        //排序点击事件
        $(".talents-order a[data-target]").click(function () {
            $(".talents-order a[data-target]").removeClass("act");
            $(this).addClass('act');

            var _target = $(this).data('target');
            var _value = $(this).data('value');

            if (_value == "desc") {
                $(this).data('value', "asc").addClass("asc").removeClass("desc");
            } else {
                $(this).data('value', "desc").addClass("desc").removeClass("asc");
            }

            pagination.page.pageNo = 1;
            pagination.page.orderByColumn = _target;
            pagination.page.orderByType = _value;

            //获取列表数据
            pagination.list();
        });
    })
</script>
</html>