<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>需求编辑 - 百百SOHO</title>
    <c:import url="../common_inport.jsp"/>
    <script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="pt97">
<!-- header -->
<c:import url="../head.jsp"/>
<!-- /header -->
<!-- mainer -->
<div class="mainer personal">
    <div class="container">
        <!-- tab-content -->
        <div class="tab-content">
            <!-- 需求信息 START -->
            <div class="tab-pane fade  in active" id="demandInfo">
                <form class="personal demandInfo-form editDemandForm">
                    <input type="text" name="id" hidden value="${param.demandId}"/>
                    <table class="demandInfo enterprise-base-info">
                        <thead>
                        <tr>
                            <th colspan="2">
                                <h2>需求信息</h2>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="pd-none">
                            <th></th>
                            <td style="padding-top: 20px;">
                                <div class="error warn-error wd420 ">
                                    <p>
                                        <label class="error" style="display: inline-block;"></label>
                                    </p>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th>需求名称：</th>
                            <td><p class="ipt-txt wd415"><input type="text" name="name"/></p><i
                                    class="red">*</i></td>
                        </tr>
                        <tr>
                            <th>工作餐：</th>
                            <td>
                                <p class="ipt-txt wd415">
                                    <select name="workingLunch"></select>
                                </p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>需求周期：</th>
                            <td>
                                <p class="ipt-txt wd200"><input type="text" name="beginCycle" class="workTime"
                                                                placeholder="开始日期"
                                                                onclick="WdatePicker()"/>
                                </p>
                                <p class="ipt-txt wd200"><input type="text" name="endCycle" class="workTime"
                                                                placeholder="结束日期"
                                                                onclick="WdatePicker()"/></p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>联系人：</th>
                            <td>
                                <p class="ipt-txt wd300">
                                    <input type="text" name="contact"/>
                                </p>
                                <p class="ipt-txt wd100">
                                    <select name="respectfulName"></select>
                                </p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>联系电话：</th>
                            <td>
                                <p class="ipt-txt wd415"><input type="text" name="mobileNumber" placeholder="手机号码"/></p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <p class="ipt-txt wd100"><input type="text" name="phoneAreaCode" placeholder="区号"/></p>
                                <p class="ipt-txt wd200"><input type="text" name="telephoneNumber" placeholder="固话号码"/>
                                </p>
                                <p class="ipt-txt wd100"><input type="text" name="extensionNumber" placeholder="分机号码"/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th>工作内容：</th>
                            <td><p class="ipt-txt wd415"><textarea class="workContent" name="demandDiscribe"
                                                                   placeholder="请描述任务需求主要职责，需要掌握哪些技能。"></textarea></p>
                            </td>
                        </tr>
                        <tr>
                            <th>工作地址：</th>
                            <td>
                                <p class="ipt-txt wd200">
                                    <select name="provinceId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="cityId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="countyId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th>详细地址：</th>
                            <td><p class="ipt-txt wd415"><input type="text" name="address"/></p></td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th colspan="2">
                                <h2>需求用人</h2>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="demandEmployPersons">
                        </tbody>
                        <tbody>
                        <tr class="pd-none">
                            <th>&nbsp;</th>
                            <td>
                                <p class="ipt-txt wd670 ttr">
                                    <button type="button" id="btn-demandEmployPersons" class="btn btn-add">添加需求用人
                                    </button>
                                </p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <button type="button" class="ipt-submit" name="editDemand">发布需求</button>
                </form>
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
</body>
<script>
    //获取工作餐字典数据
    var workingLunch = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "working_lunch"},
            type: "GET",
            success: function (resp) {
                var workingLunchOptions = '';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        workingLunchOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }

                $("select[name='workingLunch']").html(workingLunchOptions);
            }
        })
    }

    //获取用户尊称字典数据
    var getRespectfulName = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "respectful_name"},
            type: "GET",
            success: function (resp) {
                var respectfulNameOptions = '';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        respectfulNameOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }

                $("select[name='respectfulName']").html(respectfulNameOptions);
            }
        })
    }

    //获取省市区字典数据
    var getArea = {
        getProvince: function (obj, selectedVal) {
            $.ajax({
                url: "/area/getProvince",
                type: "GET",
                success: function (resp) {
                    var provinceOptions = '<option value="">请选择</option>';

                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            if (selectedVal != null) {
                                if (this.provinceId == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            provinceOptions += '<option value="' + this.provinceId + '" ' + (isSelected ? 'selected' : '') + '>' + this.provinceName + '</option>';
                        });
                    }
                    $(obj).html(provinceOptions);
                }
            })
        },
        getCity: function (obj, selectedVal, provinceId) {
            $.ajax({
                url: "/area/getCity",
                type: "GET",
                data: {provinceId: provinceId},
                success: function (resp) {
                    var cityOptions = '<option value="">请选择</option>';
                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            if (selectedVal != null) {
                                if (this.cityId == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            cityOptions += '<option value="' + this.cityId + '" ' + (isSelected ? 'selected' : '') + '>' + this.cityName + '</option>';
                        });
                    }
                    $(obj).html(cityOptions);
                }
            })
        },
        getCounty: function (obj, selectedVal, cityId) {
            $.ajax({
                url: "/area/getCounty",
                type: "GET",
                data: {cityId: cityId},
                success: function (resp) {
                    var countyOptions = '<option value="">请选择</option>';
                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            if (selectedVal != null) {
                                if (this.countyId == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            countyOptions += '<option value="' + this.countyId + '" ' + (isSelected ? 'selected' : '') + '>' + this.countyName + '</option>';
                        });
                    }
                    $(obj).html(countyOptions);
                }
            })
        }
    };

    //获取行业字典数据
    var industry = {
        getIndustry: function (obj, selectedVal) {
            $.ajax({
                url: "/common/getDictionaryByGroupKey",
                data: {groupKey: "industry"},
                type: "GET",
                success: function (resp) {
                    var industryOptions = '<option value="">请选择</option>';

                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            if (selectedVal != null) {
                                if (this.groupValKey == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            industryOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                        });
                    }
                    $(obj).html(industryOptions);
                }
            })
        },
        getWorkType: function (obj, selectedVal, industry) {
            $.ajax({
                url: "/common/getDictionaryByValKeyFuzzy",
                data: {groupKey: "work_type", group_val_key: industry},
                type: "GET",
                success: function (resp) {
                    var workTypeOptions = '<option value="">请选择</option>';

                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            if (selectedVal != null) {
                                if (this.groupValKey == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            workTypeOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                        });
                    }
                    $(obj).html(workTypeOptions);
                }
            })
        },
        getWork: function (obj, selectedVal, workType) {
            $.ajax({
                url: "/common/getDictionaryByValKeyFuzzy",
                data: {groupKey: "work", group_val_key: workType},
                type: "GET",
                success: function (resp) {
                    var workOptions = '<option value="">请选择</option>';
                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            if (selectedVal != null) {
                                if (this.groupValKey == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            workOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                        });
                    }
                    $(obj).html(workOptions);
                }
            })
        }
    };

    //获取工作年限字典数据
    var getWorkingYears = function (obj, selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "working_years"},
            type: "GET",
            success: function (resp) {
                var workingYearsOptions = '<option value="">请选择</option>';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }

                        workingYearsOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }
                $(obj).html(workingYearsOptions);
            }
        })
    }

    //获取期望薪资字典数据
    var remuneration = function (obj, selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "expect_pay"},
            type: "GET",
            success: function (resp) {
                var expectPayOptions = '<option value="">请选择</option>';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        expectPayOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }

                $(obj).html(expectPayOptions);
            }
        })
    }

    //添加需求用人
    function addDemandEmployPersons() {
        var tpl_first = '<tr>'
                + '<th>所处行业：</th>'
                + '<td>'
                + '<p class="ipt-txt wd200 fl mr10">'
                + '<select key="industry">'
                + '<option value="">请选择</option>'
                + '</select>'
                + '</p>'
                + '<p class="ipt-txt wd200 fl mr10">'
                + '<select key="workType">'
                + '<option value="">请选择</option>'
                + '</select>'
                + '</p>'
                + '<p class="ipt-txt wd200 fl mr10">'
                + '<select key="work">'
                + '<option value="">请选择</option>'
                + '</select>'
                + '</p>'
                + '<button type="button" class="btn btn-delete" data-target="0">删除</button>'
                + '</td>'
                + '</tr>';

        var tpl_second = '<tr data-flag="0">' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>用人数：</th>' +
                '<td><p class="ipt-txt wd420 fl"><input type="text" key="personsAmount"/></p></td>' +
                '</tr>' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>工作年限：</th>' +
                '<td><p class="ipt-txt wd420 fl"><select key="workingYears"><option value="">请选择</option></select></p></td>' +
                '</tr>' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>月薪报酬：</th>' +
                '<td><p class="ipt-txt wd420 fl"><select key="remuneration"><option value="">请选择</option></select></p></td>' +
                '</tr>';

        var _obj = $('#demandEmployPersons');
        //添加事件
        $(document).on('click', 'button#btn-demandEmployPersons', function () {
            var _index = Math.floor(_obj.find('tr').size() / 4);
            if ('undefined' !== typeof _obj[0]) {
                $(tpl_first).attr('data-flag', _index).find('button.btn-delete').attr('data-target', _index).end().appendTo(_obj);
                $(tpl_second).attr('data-flag', _index).appendTo(_obj);
            }

            //初始化行业
            industry.getIndustry($("tr[data-flag=" + _index + "] select[key='industry']"), null);
            //初始化工作年限
            getWorkingYears($("tr[data-flag=" + _index + "] select[key='workingYears']"), null);
            //初始化月薪报酬
            remuneration($("tr[data-flag=" + _index + "] select[key='remuneration']"), null);
        });
        //删除事件
        $(document).on('click', '#demandEmployPersons button.btn-delete', function () {
            var _target = $(this).data('target');
            _obj.find('tr[data-flag=' + _target + ']').remove();
        });
    }

    //编辑数据，初始化需求用人
    function initDemandEmployPersons(data) {
        if (data != null && data.length > 0) {
            $(data).each(function () {
                var tpl_first = '<tr>'
                        + '<th>所处行业：</th>'
                        + '<td>'
                        + '<p class="ipt-txt wd200 fl mr10">'
                        + '<select key="industry">'
                        + '<option value="">请选择</option>'
                        + '</select>'
                        + '</p>'
                        + '<p class="ipt-txt wd200 fl mr10">'
                        + '<select key="workType">'
                        + '<option value="">请选择</option>'
                        + '</select>'
                        + '</p>'
                        + '<p class="ipt-txt wd200 fl mr10">'
                        + '<select key="work">'
                        + '<option value="">请选择</option>'
                        + '</select>'
                        + '</p>'
                        + '<button type="button" class="btn btn-delete" data-target="0">删除</button>'
                        + '</td>'
                        + '</tr>';

                var tpl_second = '<tr data-flag="0">' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>用人数：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><input type="text" key="personsAmount" value="' + this.personsAmount + '"/></p></td>' +
                        '</tr>' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>工作年限：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><select key="workingYears"><option value="">请选择</option></select></p></td>' +
                        '</tr>' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>月薪报酬：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><select key="remuneration"><option value="">请选择</option></select></p></td>' +
                        '</tr>';

                var _index = Math.floor($('#demandEmployPersons').find('tr').size() / 4);
                if ('undefined' !== typeof $('#demandEmployPersons')[0]) {
                    $(tpl_first).attr('data-flag', _index).find('button.btn-delete').attr('data-target', _index).end().appendTo($('#demandEmployPersons'));
                    $(tpl_second).attr('data-flag', _index).appendTo($('#demandEmployPersons'));
                }

                //获取并加载行业信息
                industry.getIndustry($("tr[data-flag=" + _index + "] select[key='industry']"), this.industry)
                //获取并加载行业工种类别信息
                industry.getWorkType($("tr[data-flag=" + _index + "] select[key='workType']"), this.workType, this.industry);
                //获取并加载行业工种信息
                industry.getWork($("tr[data-flag=" + _index + "] select[key='work']"), this.work, this.workType);
                //初始化工作年限
                getWorkingYears($("tr[data-flag=" + _index + "] select[key='workingYears']"), this.workingYears);
                //初始化月薪报酬
                remuneration($("tr[data-flag=" + _index + "] select[key='remuneration']"), this.remuneration);
            });
        }
    }

    var initPublish = function () {
        //初始化工作餐数据
        workingLunch(null);
        //初始化尊称数据
        getRespectfulName(null);
        //初始化工作地省数据
        getArea.getProvince($("select[name='provinceId']"), null);
    }
    //获取需求信息并填充数据
    var getDemand = function () {
        $.ajax({
            url: "/demandSide/demand",
            type: "GET",
            data: {"id": $("input[name='id']").val()},
            success: function (resp) {
                if (resp.code == null) {
                    document.write(resp);
                    return;
                }

                if (resp.code == "200") {
                    //需求名称
                    $("input[name='name']").val(resp.data.name);
                    //需求周期-开始日期
                    $("input[name='beginCycle']").val(resp.data.beginCycle);
                    //需求周期-结束日期
                    $("input[name='endCycle']").val(resp.data.endCycle);
                    //联系人
                    $("input[name='contact']").val(resp.data.contact);
                    //联系人手机号码
                    $("input[name='mobileNumber']").val(resp.data.mobileNumber);
                    //联系人固话区号
                    $("input[name='phoneAreaCode']").val(resp.data.phoneAreaCode);
                    //联系人固话号码
                    $("input[name='telephoneNumber']").val(resp.data.telephoneNumber);
                    //联系人固话分机号
                    $("input[name='extensionNumber']").val(resp.data.extensionNumber);
                    //工作内容描述
                    $("textarea[name='demandDiscribe']").html(resp.data.demandDiscribe);
                    //工作详细地址
                    $("input[name='address']").val(resp.data.address);
                    //初始化工作餐数据
                    workingLunch(resp.data.workingLunch);
                    //初始化尊称数据
                    getRespectfulName(resp.data.respectfulName);
                    //初始化工作地省数据
                    getArea.getProvince($("select[name='provinceId']"), resp.data.provinceId);
                    //工作地市
                    getArea.getCity($("select[name='cityId']"), resp.data.cityId, resp.data.provinceId);
                    //工作地区
                    getArea.getCounty($("select[name='countyId']"), resp.data.countyId, resp.data.cityId);

                    //获取并加载用人数据
                    initDemandEmployPersons(resp.data.demandEmployPersonsList);
                } else {
                    $(".warn-error").show().find("label").text(resp.message);
                }
            }
        })
    }

    $(function () {
        //追加需求用人
        addDemandEmployPersons();

        //获取url参数，是否为发布，如果发布，则不需要查询数据
        if ("${param.isPublish}" == "true") {
            initPublish();
        } else {
            getDemand();
        }

        /**
         * 工作地省份数据切换，联动市区数据
         */
        $(document).on("change", "select[name='provinceId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='cityId']").html(options);
            $("select[name='countyId']").html(options);

            getArea.getCity($("select[name='cityId']"), "", $(this).val());
        });

        /**
         * 工作地市数据切换，联动区数据
         */
        $(document).on("change", "select[name='cityId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='countyId']").html(options);
            getArea.getCounty($("select[name='countyId']"), "", $(this).val());
        });

        /**
         * 行业数据切换，联动工种类型数据
         */
        $(document).on("change", "select[key='industry']", function () {

            var options = '<option value="">请选择</option>';
            $("tr[data-flag=" + $(this).parents("tr[data-flag]").data("flag") + "] select[key='workType']").html(options);
            $("tr[data-flag=" + $(this).parents("tr[data-flag]").data("flag") + "] select[key='work']").html(options);

            industry.getWorkType($("tr[data-flag=" + $(this).parents("tr[data-flag]").data("flag") + "] select[key='workType']"), null, $(this).val());
        });

        /**
         * 工种类型数据切换，联动工种数据
         */
        $(document).on("change", "select[key='workType']", function () {
            var options = '<option value="">请选择</option>';
            $("tr[data-flag=" + $(this).parents("tr[data-flag]").data("flag") + "] select[key='work']").html(options);

            industry.getWork($("tr[data-flag=" + $(this).parents("tr[data-flag]").data("flag") + "] select[key='work']"), null, $(this).val());
        });

        //编辑需求信息
        $("button[name='editDemand']").click(function () {
            var demandEmployPersons = new Map();

            //封装用人信息集合
            $('#demandEmployPersons tr[data-flag]').each(function () {
                var _this = this;
                if (!demandEmployPersons.containsKey($(_this).data("flag"))) {
                    demandEmployPersons.put($(_this).data("flag"), new Map());
                }

                $(_this).find("input[key], select[key]").each(function () {
                    demandEmployPersons.get($(_this).data("flag")).put($(this).attr("key"), $(this).val());
                });
            });

            var _this = $(this);
            var formDataJson = {};

            _this.attr('disabled', "true")

            var formData = $(".editDemandForm").serializeArray();

            $(formData).each(function () {
                formDataJson[this.name] = this.value;
            });

            //解析获取用人数据
            var demandEmployPersonses = new Array();
            if (!demandEmployPersons.isEmpty()) {
                for (var i = 0; i < demandEmployPersons.size(); i++) {
                    var demandEmployPersonsJSON = {};
                    var demandEmployPersonsData = demandEmployPersons.element(i).value;
                    $(demandEmployPersonsData.keys()).each(function () {
                        demandEmployPersonsJSON[this] = demandEmployPersonsData.get(this);
                    });
                    demandEmployPersonses.push(demandEmployPersonsJSON);
                }
            }

            formDataJson.demandEmployPersonsDTOList = demandEmployPersonses;

            //异步提交表单
            $.ajax({
                url: "/demandSide/demand",
                data: JSON.stringify(formDataJson),
                type: "post",
                contentType: "application/json",
                success: function (resp) {
                    if (resp.code == null) {
                        document.write(resp);
                        return;
                    }

                    if(resp.code == "200"){
                        window.location.href = "demand/demand_list.jsp?menuNavigation=demandHall";
                    }

                    $(".warn-error").show().find("label").text(resp.message);
                    _this.removeAttr("disabled");
                }
            })
        });
    })
</script>
</html>