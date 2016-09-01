<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>个人中心 - 个人信息编辑 - 百百SOHO</title>
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
        <ul class="nav nav-tabs">
            <li><a href="javascript:;">我的工作</a></li>
            <li><a href="javascript:;">账户信息</a></li>
            <li class="<c:if test="${param.tabWidget eq 'basicInformation'}">active</c:if>"><a
                    href="supplierSide/basicInformation.jsp?tabWidget=basicInformation">个人信息</a></li>
            <li><a href="javascript:;">过往评价</a></li>
        </ul>
        <!-- /nav-tabs -->
        <!-- tab-content -->
        <div class="tab-content">
            <!-- 需求信息 START -->
            <div class="tab-pane fade  in active" id="demandInfo">
                <!-- enterprise-top -->
                <div class="enterprise-top">
                    <div class="left">
                        <dl>
                            <dt data-toggle="modal" data-target="#uploadImage" id="viewHeadBox">
                                <img name="headPortrait" src="images/head.png"/>
                            </dt>
                            <dd>
                                <h3 class="realName"></h3>
                                <%--<div class="radio-box">--%>
                                <%--<p class="ipt-radio active"><input type="radio" name="iPay" value="支付宝" checked />绑定支付宝</p>--%>
                                <%--</div>--%>
                            </dd>
                        </dl>
                    </div>
                    <%--<div class="right">--%>
                    <%--<dl>--%>
                    <%--<dt>企业平均评分：</dt>--%>
                    <%--<dd>--%>
                    <%--<p class="star star_03">80分</p>--%>
                    <%--</dd>--%>
                    <%--</dl>--%>
                    <%--</div>--%>
                    <div class="clearfix"></div>
                </div>
                <!-- /enterprise-top -->
                <!-- personal-form -->
                <form class="personal personal-form modifyBasicInfoForm">
                    <table class="enterprise-base-info">
                        <thead>
                        <tr>
                            <th colspan="2">
                                <h2>基本信息</h2>
                            </th>
                        </tr>
                        </thead>
                        <!-- 基本信息 -->
                        <tbody>
                        <tr class="pd-none">
                            <th></th>
                            <td style="padding-top: 20px;">
                                <div class="error warn-error wd420"></div>
                            </td>
                        </tr>
                        <tr>
                            <th>姓名：</th>
                            <td>
                                <p class="ipt-txt wd420 fl"><input type="text" name="realName" placeholder="请输入真实姓名"/>
                                </p>
                                <i class="warn-star fl">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>昵称：</th>
                            <td>
                                <p class="ipt-txt wd420 fl"><input type="text" name="nickName"/></p>
                                <i class="warn-star fl">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>性别：</th>
                            <td>
                                <div class="radio-box radioSex"></div>
                            </td>
                        </tr>
                        <tr>
                            <th>出生日期：</th>
                            <td>
                                <p class="ipt-txt wd200">
                                    <select name="birthdayYear">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd100">
                                    <select name="birthdayMonth">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd100">
                                    <select name="birthdayDay">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>电子邮箱：</th>
                            <td>
                                <p class="ipt-txt wd420 fl"><input type="text" name="email"/></p>
                                <i class="warn-star fl">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>最高学历：</th>
                            <td>
                                <p class="ipt-txt wd420">
                                    <select name="highestSchooling">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>学校名称：</th>
                            <td>
                                <p class="ipt-txt wd420 fl"><input type="text" name="schoolName"/></p>
                            </td>
                        </tr>
                        <tr>
                            <th>专业：</th>
                            <td>
                                <p class="ipt-txt wd420 fl"><input type="text" name="professionalName"/></p>
                            </td>
                        </tr>
                        <tr>
                            <th>工作年限：</th>
                            <td>
                                <p class="ipt-txt wd420">
                                    <select name="workingYears">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>籍贯：</th>
                            <td>
                                <p class="ipt-txt wd200">
                                    <select name="nativePlaceProvinceId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="nativePlaceCityId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="nativePlaceCountyId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                            </td>
                        </tr>
                        <tr>
                            <th>现居住地：</th>
                            <td>
                                <p class="ipt-txt wd200">
                                    <select name="liveAddressProvinceId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="liveAddressCityId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                                <p class="ipt-txt wd200">
                                    <select name="liveAddressCountyId">
                                        <option value="">请选择</option>
                                    </select>
                                </p>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="pl-btn-box">
                        <button type="button" name="modifyBasicInfo" class="btn">修改信息完成</button>
                        <button type="button" name="perfect" class="btn">完善简历</button>
                        <p class="desc">完善简历可以获得更多工作哦！</p>
                    </div>
                </form>
                <!-- /personal-form -->
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
<!-- uploadImage -->
<div class="modal fade" id="uploadImage" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <iframe src="../uploadImage.jsp" id="uploadImageFrame" scrolling="no" frameborder="0"></iframe>
            </div>
        </div>
    </div>
</div>
<!-- /uploadImage -->
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

    //生日数据对象
    var birthday = {
        yearScope: "1970-2020",
        getYear: function (selectedVal) {
            var year_start = parseInt(this.yearScope.split("-")[0]);
            var year_end = parseInt(this.yearScope.split("-")[1]);

            var yearsOption = '<option value="">请选择</option>';

            for (year_start; year_start <= year_end; year_start++) {
                var isSelected = false;
                if (selectedVal != null) {
                    if (year_start == selectedVal) {
                        isSelected = true;
                    }
                }

                yearsOption += '<option value="' + year_start + '" ' + (isSelected ? 'selected' : '') + '>' + year_start + '</option>';
            }

            $("select[name='birthdayYear']").html(yearsOption);
        },
        getMonth: function (selectedVal) {
            var monthOption = '<option value="">请选择</option>';

            for (var month = 1; month <= 12; month++) {
                var isSelected = false;
                if (selectedVal != null) {
                    if (month == selectedVal) {
                        isSelected = true;
                    }
                }
                monthOption += '<option value="' + month + '" ' + (isSelected ? 'selected' : '') + '>' + month + '</option>';
            }

            $("select[name='birthdayMonth']").html(monthOption);
        },
        getDay: function (selectedVal) {
            var dayOption = '<option value="">请选择</option>';

            var day;
            if ([1, 3, 5, 7, 8, 10, 12].indexOf(parseInt($("select[name='birtydayMonth']").val())) != -1) {
                day = 31;
            } else if ([4, 6, 9, 11].indexOf(parseInt($("select[name='birtydayMonth']").val())) != -1) {
                day = 30;
            } else {
                day = 29;
            }

            for (var i = 1; i <= day; i++) {
                var isSelected = false;
                if (selectedVal != null) {
                    if (i == selectedVal) {
                        isSelected = true;
                    }
                }
                dayOption += '<option value="' + i + '" ' + (isSelected ? 'selected' : '') + '>' + i + '</option>';
            }

            $("select[name='birthdayDay']").html(dayOption);
        }
    };

    //获取性别字典数据
    var getSex = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "sex"},
            type: "GET",
            success: function (resp) {
                var sexRadio = '';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isChecked = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isChecked = true;
                            }
                        }

                        sexRadio += '<p class="ipt-radio mr20 ' + (isChecked ? 'active' : '') + '"><input type="radio" name="sex" value="' + this.groupValKey + '" ' + (isChecked ? 'checked' : '') + '/>' + this.groupValVal + '</p>';
                    });
                }

                $(".radioSex").html(sexRadio);
            }
        })
    }

    //获取教育学历字典数据
    var getSchooling = function (selectedVal) {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "schooling"},
            type: "GET",
            success: function (resp) {
                var schoolingOptions = '<option value="">请选择</option>';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }

                        schoolingOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }
                $("select[name='highestSchooling']").html(schoolingOptions);
            }
        })
    }

    //获取工作年限字典数据
    var getWorkingYears = function (selectedVal) {
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
                $("select[name='workingYears']").html(workingYearsOptions);
            }
        })
    }

    //获取个人基本资料信息并填充数据
    var getBasicInformation = function () {
        $.ajax({
            url: "/supplierSide/basicInformation",
            type: "GET",
            success: function (resp) {
                if (resp.code == null) {
                    document.write(resp);
                    return;
                }

                if (resp.code == "200") {
                    if (resp.data.headPortrait != "" && resp.data.headPortrait != null) {
                        //个人头像
                        $("img[name='headPortrait']").prop("src", resp.data.headPortrait);
                    }

                    //真实姓名
                    $("input[name='realName']").val(resp.data.realName);
                    //昵称
                    $("input[name='nickName']").val(resp.data.nickName);
                    //性别
                    getSex(resp.data.sex)
                    //生日-年
                    birthday.getYear(resp.data.birthdayYear)
                    //生日-月
                    birthday.getMonth(resp.data.birthdayMonth)
                    //生日-日
                    birthday.getDay(resp.data.birthdayDay)
                    //电子邮箱
                    $("input[name='email']").val(resp.data.email);
                    //最高学历
                    getSchooling(resp.data.highestSchooling);
                    //学校名称
                    $("input[name='schoolName']").val(resp.data.schoolName);
                    //专业
                    $("input[name='professionalName']").val(resp.data.professionalName);
                    //工作年限
                    getWorkingYears(resp.data.workingYears);
                    //籍贯省
                    getArea.getProvince($("select[name='nativePlaceProvinceId']"), resp.data.nativePlaceProvinceId);
                    //籍贯市
                    getArea.getCity($("select[name='nativePlaceCityId']"), resp.data.nativePlaceCityId, resp.data.nativePlaceProvinceId);
                    //籍贯区
                    getArea.getCounty($("select[name='nativePlaceCountyId']"), resp.data.nativePlaceCountyId, resp.data.nativePlaceCityId);
                    //现居地省
                    getArea.getProvince($("select[name='liveAddressProvinceId']"), resp.data.liveAddressProvinceId);
                    //现居地市
                    getArea.getCity($("select[name='liveAddressCityId']"), resp.data.liveAddressCityId, resp.data.liveAddressProvinceId);
                    //现居地区
                    getArea.getCounty($("select[name='liveAddressCountyId']"), resp.data.liveAddressCountyId, resp.data.liveAddressCityId);
                } else {
                    alert(resp.message);
                }
            }
        })
    }

    $(function () {
        //获取并加载企业基本信息
        getBasicInformation();

        //radio点击选中释放选中切换
        selectRadioBox();

        /**
         * 生日月数据切换，联动日数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='birtydayMonth']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='birtydayDay']").html(options);

            birthday.getDay();
        });

        /**
         * 籍贯省份数据切换，联动市区数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='nativePlaceProvinceId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='nativePlaceCityId']").html(options);
            $("select[name='nativePlaceCountyId']").html(options);

            getArea.getCity($("select[name='nativePlaceCityId']"), "", $(this).val());
        });

        /**
         * 籍贯市数据切换，联动区数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='nativePlaceCityId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='nativePlaceCountyId']").html(options);
            getArea.getCounty($("select[name='nativePlaceCountyId']"), "", $(this).val());
        });

        /**
         * 现居地省份数据切换，联动市区数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='liveAddressProvinceId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='liveAddressCityId']").html(options);
            $("select[name='liveAddressCountyId']").html(options);

            getArea.getCity($("select[name='liveAddressCityId']"), "", $(this).val());
        });

        /**
         * 现居地市数据切换，联动区数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='liveAddressCityId']", function () {
            var options = '<option value="">请选择</option>';
            $("select[name='liveAddressCountyId']").html(options);
            getArea.getCounty($("select[name='liveAddressCountyId']"), "", $(this).val());
        });

        //修改基本信息
        $("button[name='modifyBasicInfo']").click(function () {
            var _this = $(this);
            var formDataJson = {};

            if ($("canvas").size() > 0) {
                formDataJson["headPortrait"] = $("canvas").get(0).toDataURL()
            }

            _this.attr('disabled', "true")

            var formData = $(".modifyBasicInfoForm").serializeArray();

            $(formData).each(function () {
                formDataJson[this.name] = this.value;
            });

            //异步提交表单
            $.ajax({
                url: "/supplierSide/basicInformation",
                data: $.extend({_method: "put"}, formDataJson),
                type: "post",
                success: function (resp) {
                    alert(resp.message);
                    _this.removeAttr("disabled");
                }
            })
        });
    })
</script>
</html>