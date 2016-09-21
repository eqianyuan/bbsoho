<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>个人中心 - 个人简历 - 百百SOHO</title>
    <c:import url="../common_inport.jsp"/>
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
        <div class="tab-content">
            <!-- 需求信息 START -->
            <div class="tab-pane fade  in active">
                <!-- bottom-form  -->
                <form class="personal bottom-form modifyResumeForm">
                    <table class="enterprise-base-info">
                        <thead>
                        <tr>
                            <th colspan="2">
                                <h2>简历信息</h2>
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
                            <th>所处行业：</th>
                            <td>
                                <p class="ipt-txt wd200 fl mr10">
                                    <select name="industry">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200 fl mr10">
                                    <select name="workType">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <div class="ipt-select wd200 fl workProficiency">
                                    <input type="text" name="work" readonly/>
                                    <div class="select-list">
                                        <ul></ul>
                                        <div class="btn-box">
                                            <a href="javascript:;" class="ipt-select-okay">确定</a>
                                        </div>
                                    </div>
                                </div>
                                <i class="warn-star">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>擅长方向：</th>
                            <td>
                                <p class="ipt-txt wd420 fl"><input type="text" name="discribe" placeholder="请输入擅长关键词，多个关键词以空格隔开"/>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th>工作时间：</th>
                            <td>
                                <p class="ipt-txt wd420">
                                    <select name="expectWorkTime">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>薪资待遇：</th>
                            <td>
                                <p class="ipt-txt wd420 fl">
                                    <select name="expectPay">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <i class="warn-star">*</i>
                                <p class="unit fl">月</p>
                            </td>
                        </tr>
                        <tr>
                            <th>期望工作地区：</th>
                            <td>
                                <p class="ipt-txt wd200">
                                    <select name="expectWorkProvinceId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="expectWorkCityId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="expectWorkCountyId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th>外语语种：</th>
                            <td>
                                <p class="ipt-txt wd420 fl">
                                    <select name="foreignLanguage">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th>外语能力：</th>
                            <td>
                                <p class="ipt-txt wd420 fl">
                                    <select name="foreignLanguageAbility">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                            </td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th colspan="2">
                                <h2>工作经历</h2>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="workExperience">
                        </tbody>
                        <tbody>
                        <tr class="pd-none">
                            <th>&nbsp;</th>
                            <td>
                                <p class="ipt-txt wd670 ttr">
                                    <button type="button" id="btn-workExperience" class="btn btn-add">添加工作经历</button>
                                </p>
                            </td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th colspan="2">
                                <h2>项目经验</h2>
                            </th>
                        </tr>
                        </thead>
                        <tbody id="projectExperience">
                        </tbody>
                        <tbody>
                        <tr class="pd-none">
                            <th>&nbsp;</th>
                            <td>
                                <p class="ipt-txt wd670 ttr">
                                    <button type="button" id="btn-projectExperience" class="btn btn-add">添加项目经验</button>
                                </p>
                            </td>
                        </tr>
                        </tbody>
                        <tbody>
                        <tr>
                            <th>擅长语言：</th>
                            <td>
                                <ul class="progress-list" id="workProficiency"></ul>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pl-btn-box">
                        <button type="button" name="modifyResume" class="btn">保存编辑</button>
                    </div>
                </form>
                <!-- /bottom-form  -->
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
<script language="javascript" type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
</body>
<script>
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
        getWork: function (obj, selectedVals, workType) {
            $.ajax({
                url: "/common/getDictionaryByValKeyFuzzy",
                data: {groupKey: "work", group_val_key: workType},
                type: "GET",
                success: function (resp) {
                    var workOptions = '';
                    //构建熟练度插件
                    var workProficiency = '';
                    if (resp != null && resp.length > 0) {
                        if (selectedVals != null && selectedVals.length > 0) {
                            var _values = new Array();
                            $(resp).each(function () {
                                var _this = this;
                                var isSelected = false;
                                $(selectedVals).each(function () {
                                    if (this.work == _this.groupValKey) {
                                        isSelected = true;
                                        _values.push(_this.groupValVal);

                                        workProficiency += '<li data-value="' + this.masterDegree + '" data-max="100" data-name="' + _this.groupValVal + '" data-work="' + _this.groupValKey + '">'
                                                + '<div class="progress-tit fl">' + _this.groupValVal + '</div>'
                                                + '<div class="progress fl"></div>'
                                                + '<div class="clearfix"></div>'
                                                + '</li>';

                                        return false;
                                    }
                                });

                                workOptions += '<li data-value="' + _this.groupValKey + '" class="' + (isSelected ? 'active' : '') + '">' + _this.groupValVal + '</li>';
                            });

                            $(obj).parents(".ipt-select").find('input[type=text]').val(_values.join());
                        } else {
                            $(resp).each(function () {
                                workOptions += '<li data-value="' + this.groupValKey + '">' + this.groupValVal + '</li>';
                            });
                        }
                    }
                    $(obj).html(workOptions);
                    $("#workProficiency").html(workProficiency);
                    //可拖拽进度条
                    addProgressbar();
                }
            })
        }
    };

    //获取期望工作时间字典数据
    var expectWorkTime = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "expect_work_time"},
            type: "GET",
            success: function (resp) {
                var expectWorkTimeOptions = '<option value="">请选择</option>';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        expectWorkTimeOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }

                $("select[name='expectWorkTime']").html(expectWorkTimeOptions);
            }
        })
    }

    //获取期望薪资字典数据
    var expectPay = function (selectedVal) {
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

                $("select[name='expectPay']").html(expectPayOptions);
            }
        })
    }

    //获取外语语种字典数据
    var foreignLanguage = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "foreign_language"},
            type: "GET",
            success: function (resp) {
                var foreignLanguageOptions = '<option value="">请选择</option>';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        foreignLanguageOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }

                $("select[name='foreignLanguage']").html(foreignLanguageOptions);
            }
        })
    }

    //获取外语语种熟练度字典数据
    var foreignLanguageAbility = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "foreign_language_ability"},
            type: "GET",
            success: function (resp) {
                var foreignLanguageAbilityOptions = '<option value="">请选择</option>';

                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        foreignLanguageAbilityOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }

                $("select[name='foreignLanguageAbility']").html(foreignLanguageAbilityOptions);
            }
        })
    }

    //根据工作经历数据填充页面
    var workExperience = function (data) {
        if (data != null && data.length > 0) {
            $(data).each(function () {
                var tpl_first = '<tr data-flag="0"> ' +
                        '<th>公司名称：</th>' +
                        '<td>' +
                        '<p class="ipt-txt wd420 fl"><input type="text" key="companyName" value="' + this.companyName + '"/></p>' +
                        '<button type="button" class="btn btn-delete" data-target="0">删除</button>' +
                        '</td>' +
                        '</tr>';
                var tpl_second = '<tr data-flag="0">' +
                        '<th>工作周期：</th>' +
                        '<td>' +
                        '<p class="ipt-txt wd200 fl mr10"><input type="text" key="workCycleBegin" class="calendar" value="' + this.beginYears + '年' + this.beginMonth + '月" readonly/></p>' +
                        '<p class="ipt-txt wd200 fl"><input type="text" key="workCycleEnd" class="calendar" value="' + this.endYears + '年' + this.endMonth + '月"  readonly/></p>' +
                        '</td>' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>职位：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><input type="text" key="positionName" value="' + this.positionName + '"/></p></td>' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>负责岗位：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><textarea key="workDiscribe" placeholder="请描述在公司的岗位职责。">' + this.workDiscribe + '</textarea></p></td>' +
                        '</tr>';

                var _index = Math.floor($('#workExperience').find('tr').size() / 4);
                if ('undefined' !== typeof $('#workExperience')[0]) {
                    $(tpl_first).attr('data-flag', _index).find('button.btn-delete').attr('data-target', _index).end().appendTo($('#workExperience'));
                    $(tpl_second).attr('data-flag', _index).appendTo($('#workExperience'));
                }
            });
        }
    }

    //根据项目经验数据填充页面
    var projectExperience = function (data) {
        if (data != null && data.length > 0) {
            $(data).each(function () {
                var tpl_first = '<tr data-flag="0"> ' +
                        '<th>项目名称：</th>' +
                        '<td>' +
                        '<p class="ipt-txt wd420 fl"><input type="text" key="projectName" value="' + this.projectName + '"/></p>' +
                        '<button type="button" class="btn btn-delete" data-target="0">删除</button>' +
                        '</td>' +
                        '</tr>';
                var tpl_second = '<tr data-flag="0">' +
                        '<th>项目访问地址：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><input type="text" key="visitAddress" value="' + this.visitAddress + '"/></p>' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>项目周期：</th>' +
                        '<td>' +
                        '<p class="ipt-txt wd200 fl mr10"><input type="text" key="projectCycleBegin" class="calendar" value="' + this.beginYears + '年' + this.beginMonth + '月" readonly/></p>' +
                        '<p class="ipt-txt wd200 fl"><input type="text" key="projectCycleEnd" class="calendar" value="' + this.endYears + '年' + this.endMonth + '月" readonly/></p>' +
                        '</td>' +
                        '</tr>' +
                        '<tr data-flag="0">' +
                        '<th>责任描述：</th>' +
                        '<td><p class="ipt-txt wd420 fl"><textarea key="projectDiscribe" placeholder="请描述在公司的岗位职责。">' + this.projectDiscribe + '</textarea></p></td>' +
                        '</tr>';

                var _index = Math.floor($('#projectExperience').find('tr').size() / 4);
                if ('undefined' !== typeof $('#projectExperience')[0]) {
                    $(tpl_first).attr('data-flag', _index).find('button.btn-delete').attr('data-target', _index).end().appendTo($('#projectExperience'));
                    $(tpl_second).attr('data-flag', _index).appendTo($('#projectExperience'));
                }
            });
        }
    }

    //获取个人基本资料信息并填充数据
    var getResume = function () {
        $.ajax({
            url: "/supplierSide/resume",
            type: "GET",
            success: function (resp) {
                if (resp.code == null) {
                    document.write(resp);
                    return;
                }

                if (resp.code == "200") {
                    //获取并加载行业信息
                    industry.getIndustry($("select[name='industry']"), resp.data.industry)
                    //获取并加载行业工种类别信息
                    industry.getWorkType($("select[name='workType']"), resp.data.workType, resp.data.industry);
                    //获取并加载行业工种信息
                    industry.getWork($("input[name='work'] + .select-list ul"), resp.data.workProficiencies, resp.data.workType);
                    $("input[name='discribe']").val(resp.data.discribe);
                    //获取并加载期望工作时间
                    expectWorkTime(resp.data.expectWorkTime);
                    //获取并加载期望薪资
                    expectPay(resp.data.expectPay);
                    //获取并加载外语语种
                    foreignLanguage(resp.data.foreignLanguage);
                    //获取并加载外语语种能力
                    foreignLanguageAbility(resp.data.foreignLanguageAbility);
                    //期望工作地省
                    getArea.getProvince($("select[name='expectWorkProvinceId']"), resp.data.expectWorkProvinceId);
                    //期望工作地市
                    getArea.getCity($("select[name='expectWorkCityId']"), resp.data.expectWorkCityId, resp.data.expectWorkProvinceId);
                    //期望工作地区
                    getArea.getCounty($("select[name='expectWorkCountyId']"), resp.data.expectWorkCountyId, resp.data.expectWorkCityId);
                    //获取并加载工作经历
                    workExperience(resp.data.workExperiences);
                    //获取并加载项目经验
                    projectExperience(resp.data.projectExperiences);
                } else {
                    $(".warn-error").show().find("label").text(resp.message);
                }
            }
        })
    }

    //添加进度条
    var addProgressbar = function () {
        var tpl = '<div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 30%;">' +
                '<span class="sr-only"></span>' +
                '<input type="hidden"/>' +
                '</div>';
        var _width = 360;
        $('ul.progress-list li[data-value]').each(function () {
            var progress = $(this).find('div.progress');
            var _length = $(this).data('value');
            var _max = $(this).data('max');
            var _name = $(this).data('name');
            var _left = (_length * _width) / _max;

            $(tpl).css('width', _left).attr('aria-valuemax', _max).find('input[type=hidden]').attr('name', _name).val(_length).end().appendTo(progress);
        });
        $(document).on('click', 'div.progress', function (e) {
            var _bar = $(this).find('.progress-bar');
            var _startPos = $(this).offset().left;
            var _max = parseInt(_bar.attr('aria-valuemax'));
            var _left = e.clientX - _startPos;
            var _value = parseInt(_left * _max / _width);
            $(this).find('input[type=hidden]').val(_value);
            _bar.width(_left);
        });
    }

    //添加工作经历
    function addWorkExperience() {
        var tpl_first = '<tr data-flag="0"> ' +
                '<th>公司名称：</th>' +
                '<td>' +
                '<p class="ipt-txt wd420 fl"><input type="text" key="companyName"/></p>' +
                '<button type="button" class="btn btn-delete" data-target="0">删除</button>' +
                '</td>' +
                '</tr>';
        var tpl_second = '<tr data-flag="0">' +
                '<th>工作周期：</th>' +
                '<td>' +
                '<p class="ipt-txt wd200 fl mr10"><input type="text" key="workCycleBegin" class="calendar" readonly/></p>' +
                '<p class="ipt-txt wd200 fl"><input type="text" key="workCycleEnd" class="calendar" readonly/></p>' +
                '</td>' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>职位：</th>' +
                '<td><p class="ipt-txt wd420 fl"><input type="text" key="positionName"/></p></td>' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>负责岗位：</th>' +
                '<td><p class="ipt-txt wd420 fl"><textarea key="workDiscribe" placeholder="请描述在公司的岗位职责。"></textarea></p></td>' +
                '</tr>';

        var _obj = $('#workExperience');
        //添加事件
        $(document).on('click', 'button#btn-workExperience', function () {
            var _index = Math.floor(_obj.find('tr').size() / 4);
            if ('undefined' !== typeof _obj[0]) {
                $(tpl_first).attr('data-flag', _index).find('button.btn-delete').attr('data-target', _index).end().appendTo(_obj);
                $(tpl_second).attr('data-flag', _index).appendTo(_obj);
            }
        });
        //删除事件
        $(document).on('click', '#workExperience button.btn-delete', function () {
            var _target = $(this).data('target');
            _obj.find('tr[data-flag=' + _target + ']').remove();
        });
    }

    //添加项目经验
    function addProjectExperience() {
        var tpl_first = '<tr data-flag="0"> ' +
                '<th>项目名称：</th>' +
                '<td>' +
                '<p class="ipt-txt wd420 fl"><input type="text" key="projectName"/></p>' +
                '<button type="button" class="btn btn-delete" data-target="0">删除</button>' +
                '</td>' +
                '</tr>';
        var tpl_second = '<tr data-flag="0">' +
                '<th>项目访问地址：</th>' +
                '<td><p class="ipt-txt wd420 fl"><input type="text" key="visitAddress"/></p>' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>项目周期：</th>' +
                '<td>' +
                '<p class="ipt-txt wd200 fl mr10"><input type="text" key="projectCycleBegin" class="calendar" readonly/></p>' +
                '<p class="ipt-txt wd200 fl"><input type="text" key="projectCycleEnd" class="calendar" readonly/></p>' +
                '</td>' +
                '</tr>' +
                '<tr data-flag="0">' +
                '<th>责任描述：</th>' +
                '<td><p class="ipt-txt wd420 fl"><textarea key="projectDiscribe" placeholder="请描述在公司的岗位职责。"></textarea></p></td>' +
                '</tr>';

        var _obj = $('#projectExperience');
        //添加事件
        $(document).on('click', 'button#btn-projectExperience', function () {
            var _index = Math.floor(_obj.find('tr').size() / 4);
            if ('undefined' !== typeof _obj[0]) {
                $(tpl_first).attr('data-flag', _index).find('button.btn-delete').attr('data-target', _index).end().appendTo(_obj);
                $(tpl_second).attr('data-flag', _index).appendTo(_obj);
            }
        });
        //删除事件
        $(document).on('click', '#projectExperience button.btn-delete', function () {
            var _target = $(this).data('target');
            _obj.find('tr[data-flag=' + _target + ']').remove();
        });
    }

    $(function () {
        //获取并加载简历信息
        getResume();
        //追加工作经历输入框
        addWorkExperience();
        //追加项目经历输入框
        addProjectExperience();
        //复选下拉
        fixSelect();

        //日历时间组件
        $(document).on("click", ".calendar", function () {
            //年最大为今年
            WdatePicker({dateFmt: 'yyyy年M月', minDate: '1980-1', maxDate: new Date().getFullYear() + '-12'})
        })

        //行业工种选择确认
        $(document).on('click', '.workProficiency a.ipt-select-okay', function () {
            var _parent = $(this).parents(".workProficiency");
            //构建熟练度插件
            var workProficiency = '';
            $(_parent.find('ul li.active')).each(function () {
                workProficiency += '<li data-value="0" data-max="100" data-name="' + $(this).text() + '" data-work="' + $(this).attr("data-value") + '">'
                        + '<div class="progress-tit fl">' + $(this).text() + '</div>'
                        + '<div class="progress fl"></div>'
                        + '<div class="clearfix"></div>'
                        + '</li>';
            });

            $("#workProficiency").html(workProficiency);
            addProgressbar();
        });

        /**
         * 期望工作地省份数据切换，联动市区数据
         */
        $(document).on("change", "select[name='expectWorkProvinceId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='expectWorkCityId']").html(options);
            $("select[name='expectWorkCountyId']").html(options);

            getArea.getCity($("select[name='expectWorkCityId']"), "", $(this).val());
        });

        /**
         * 期望工作地市数据切换，联动区数据
         */
        $(document).on("change", "select[name='expectWorkCityId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='expectWorkCountyId']").html(options);
            getArea.getCounty($("select[name='expectWorkCountyId']"), "", $(this).val());
        });

        /**
         * 行业数据切换，联动工种类型数据
         */
        $(document).on("change", "select[name='industry']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='workType']").html(options);
            $("input[name='work']").val("");
            $("input[name='work'] + .select-list ul").html("");

            industry.getWorkType($("select[name='workType']"), null, $("select[name='industry']").val());
        });

        /**
         * 工种类型数据切换，联动工种数据
         */
        $(document).on("change", "select[name='workType']", function () {
            $("input[name='work']").val("");
            $("input[name='work'] + .select-list ul").html("");

            industry.getWork($("input[name='work'] + .select-list ul"), null, $("select[name='workType']").val());
        });

        //修改简历信息
        $("button[name='modifyResume']").click(function () {
            var workExperience = new Map();
            var projectExperience = new Map();

            //封装工作经历集合
            $('#workExperience tr[data-flag]').each(function () {
                var _this = this;
                if (!workExperience.containsKey($(_this).data("flag"))) {
                    workExperience.put($(_this).data("flag"), new Map());
                }

                $(_this).find("input[key], textarea[key]").each(function () {
                    workExperience.get($(_this).data("flag")).put($(this).attr("key"), $(this).val());
                });
            });

            //封装项目经验集合
            $('#projectExperience tr[data-flag]').each(function () {
                var _this = this;
                if (!projectExperience.containsKey($(_this).data("flag"))) {
                    projectExperience.put($(_this).data("flag"), new Map());
                }

                $(_this).find("input[key], textarea[key]").each(function () {
                    projectExperience.get($(_this).data("flag")).put($(this).attr("key"), $(this).val());
                });
            });

            var _this = $(this);
            var formDataJson = {};

            _this.attr('disabled', "true")

            var formData = $(".modifyResumeForm").serializeArray();

            $(formData).each(function () {
                formDataJson[this.name] = this.value;
            });

            //解析获取工作经历数据
            var workExperiences = new Array();
            if (!workExperience.isEmpty()) {
                for (var i = 0; i < workExperience.size(); i++) {
                    var workExperienceJSON = {};
                    var workExperienceData = workExperience.element(i).value;
                    $(workExperienceData.keys()).each(function () {
                        workExperienceJSON[this] = workExperienceData.get(this);
                    });
                    workExperiences.push(workExperienceJSON);
                }
            }

            //解析获取项目经验数据
            var projectExperiences = new Array();
            if (!projectExperience.isEmpty()) {
                for (var i = 0; i < projectExperience.size(); i++) {
                    var projectExperienceJSON = {};
                    var projectExperienceData = projectExperience.element(i).value;
                    $(projectExperienceData.keys()).each(function () {
                        projectExperienceJSON[this] = projectExperienceData.get(this);
                    });
                    projectExperiences.push(projectExperienceJSON);
                }
            }

            //解析获取工种数据
            var workProficiency = new Array();
            $("#workProficiency li[data-value]").each(function () {
                workProficiency.push({"work": $(this).data("work"), "masterDegree": $(this).find("input").val()});
                delete formDataJson[$(this).data("name")];
            });

            formDataJson.workExperienceDTOList = workExperiences;
            formDataJson.projectExperienceDTOList = projectExperiences;
            formDataJson.workProficiencyDTOList = workProficiency;

            //异步提交表单
            $.ajax({
                url: "/supplierSide/resume",
                data: JSON.stringify(formDataJson),
                type: "post",
                contentType: "application/json",
                success: function (resp) {

                    if(resp.code == "200"){
                        window.location.href = "supplier/supplier_list.jsp?menuNavigation=talentPool";
                    }

                    $(".warn-error").show().find("label").text(resp.message);
                    _this.removeAttr("disabled");
                }
            })
        });
    })
</script>
</html>