<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>需求大厅 - 百百SOHO</title>
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
            <input type="hidden" name="remuneration"/>                             <!-- 薪酬 -->
        </form>
        <!-- talents-top -->
        <div class="talents-top">
            <dl class="city">
                <dt>工作城市：</dt>
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
                <dt>需要工种：</dt>
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
                <dt>工作地区：</dt>
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
            <dl class="remuneration">
                <dt>薪资范畴：</dt>
                <dd class="wd80">
                    <a href="javascript:;" data-target="remuneration" data-value="" class="allSelect act">不限</a>
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
                <a href="javascript:;" class="act" data-target="publish_time" data-value="desc">默认顺序</a>
                <a href="javascript:;" data-target="working_years" data-value="desc">工作经验</a>
                <a href="javascript:;" data-target="remuneration" data-value="desc">薪资范畴</a>
            </dd>
        </dl>
        <!-- /talents-order -->
        <!-- talents-list -->
        <div class="talents-list">
        </div>
        <!-- /talents-list -->
        <!-- paging -->
        <div class="paging"></div>
        <!-- /paging -->
    </div>
</div>
<!-- /mainer -->
<!-- footer -->
<c:import url="../footer.jsp"/>
<!-- /footer -->
<script type="text/javascript" src="js/eqianyuan.page.js"></script>
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

    //获取期望薪资字典数据
    var remuneration = function (obj) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "expect_pay"},
            type: "GET",
            success: function (resp) {
                var remunerationHtml = '';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        remunerationHtml += '<a href="javascript:;" data-target="remuneration" data-value="' + this.groupValKey + '">' + this.groupValVal + '</a>';
                    });
                }
                $(obj).html(remunerationHtml);

                //搜索条件更多按钮事件
                var _dl = $(".remuneration");
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
            orderByColumn: 'publish_time',
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
                url: "/demandSide/search/demandList",
                data: $.extend({}, formDataJson, pagination.page),
                success: function (response) {
                    //设置分页
                    pagination.page.pageNo = response.pageNo;
                    pagination.page.pageCount = response.pageCount;
                    pagination.init();

                    if (response.totalCount > 0) {
                        var row = "";
                        $(response.list).each(function () {
                            row += '<div class="row">'
                                    + '<dl><dt><a href="/demand/demand_info.jsp?menuNavigation=demandHall&demandId=' + this.id + '" class="tit">' + this.name + '</a><!--<span class="grade">综合评分：<span class="orange">85分</span></span>--></dt></dl>'
                                    + '<dl>'
                                    + '<dd><p class="pd8">岗位需要</p><p class="ageLimit"><span class="orange large">' + this.work + '</span></p></dd>'
                                    + '<dd><p class="pd8">年限要求</p><p class="ageLimit"><span class="gray">' + this.workingYears + '</span></p></dd>'
                                    + '<dd><p class="pd8">薪资</p><p><span class="orange large">' + this.remuneration + '</span><span class="gray">元</span></p></dd>'
                                    + '<dd><p class="pd8">用人量</p><p class="ageLimit"><span class="gray">' + this.personsAmount + '</span></p></dd>'
                                    + '<dd><p>周期开始：<span class="gray">' + this.beginCycle + '</span>周期结束：<span class="gray">' + this.endCycle + '</span></p><p>工作地点：<span class="gray">' + this.countyName + '</span></p></dd>'
                                    + '<dd><a href="javascript:;" class="btn-buy signUp" data-id="' + this.id + '">报名</a></dd>'
                                    + '</dl>'
                                    + '</div>';
                        });

                        $(".talents-list").html(row);
                    } else {
                        $(".talents-list").html('<div class="row"><img src="images/demand/no_data.png"/></div>');
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
        //获取薪资查询条件
        remuneration($(".remuneration .tt-list"));
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

        //报名按钮点击事件
        $(document).on("click", ".signUp", function(){
            console.log();
            $.ajax({
                type: "POST",
                url: "/supplierSide/demand/signUp/"+$(this).data("id"),
                success: function (resp) {
                    if (resp.code == null) {
                        document.write(resp);
                        return;
                    }

                    if(resp.code == "200"){
                        $('#signUpTip').modal('show').find("h3").text("报名成功");
                    }else{
                        $('#signUpTip').modal('show').find("h3").text(resp.message);
                    }
                }
            });
        });

        /**
         * 弹窗关闭
         */
        $(document).on('click', 'button.btn-cancel', function(){
            $('#signUpTip').modal('hide');
        });
    })
</script>
</html>