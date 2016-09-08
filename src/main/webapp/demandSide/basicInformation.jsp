<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>会员中心 - 百百SOHO</title>
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
            <!-- 企业信息 START -->
            <div class="tab-pane fade in active"
                 id="demandSideBasicInfo">
                <form class="personal enterprise-form modifyBasicInfoForm">
                    <div class="enterprise-top">
                        <div class="left">
                            <dl>
                                <dt data-toggle="modal" data-target="#uploadImage" id="viewHeadBox">
                                    <img name="logo" src="/images/head.png" />
                                </dt>
                                <dd>
                                    <h3 class="companyName"></h3>
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
                    <table class="demandInfo">
                        <tr>
                            <th>企业名称：</th>
                            <td><p class="ipt-txt wd415"><input type="text" name="companyName"/></p><i
                                    class="red">*</i></td>
                        </tr>
                        <tr>
                            <th>企业性质：</th>
                            <td>
                                <p class="ipt-txt wd415">
                                    <select name="enterpriseNature"></select>
                                </p><i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>企业规模：</th>
                            <td>
                                <p class="ipt-txt wd415">
                                    <select name="enterpriseScale"></select>
                                </p><i class="red">*</i>
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
                            <th>公司地区：</th>
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
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>详细地址：</th>
                            <td>
                                <p class="ipt-txt wd415"><input type="text" name="address"/></p>
                                <i class="red">*</i>
                            </td>
                        </tr>
                        <tr>
                            <th>&nbsp;</th>
                            <td>
                                <div class="error"></div>
                            </td>
                        </tr>
                    </table>
                    <button type="button" name="modifyBasicInfo" class="ipt-submit">修改信息完成</button>
                </form>
            </div>
            <!-- 企业信息 END -->
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
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/jquery.datepicker.zh.js"></script>
</body>

<script>
    //获取省市区字典数据
    var getArea = {
        getProvince: function () {
            $.ajax({
                url: "/area/getProvince",
                type: "GET",
                success: function (resp) {
                    var provinceOptions = '<option value="">请选择</option>';

                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            var selectedVal = $(".modifyBasicInfoForm select[name='provinceId']").attr("selectedVal");
                            if (selectedVal != null) {
                                if (this.provinceId == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            provinceOptions += '<option value="' + this.provinceId + '" ' + (isSelected ? 'selected' : '') + '>' + this.provinceName + '</option>';
                        });
                    }
                    $(".modifyBasicInfoForm select[name='provinceId']").html(provinceOptions);
                }
            })
        },
        getCity: function (provinceId) {
            $.ajax({
                url: "/area/getCity",
                type: "GET",
                data: {provinceId: provinceId},
                success: function (resp) {
                    var cityOptions = '<option value="">请选择</option>';
                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            var selectedVal = $(".modifyBasicInfoForm select[name='cityId']").attr("selectedVal");
                            if (selectedVal != null) {
                                if (this.cityId == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            cityOptions += '<option value="' + this.cityId + '" ' + (isSelected ? 'selected' : '') + '>' + this.cityName + '</option>';
                        });
                    }
                    $(".modifyBasicInfoForm select[name='cityId']").html(cityOptions);
                }
            })
        },
        getCounty: function (cityId) {
            $.ajax({
                url: "/area/getCounty",
                type: "GET",
                data: {cityId: cityId},
                success: function (resp) {
                    var countyOptions = '<option value="">请选择</option>';
                    if (resp != null && resp.length > 0) {
                        $(resp).each(function () {
                            var isSelected = false;
                            var selectedVal = $(".modifyBasicInfoForm select[name='countyId']").attr("selectedVal");
                            if (selectedVal != null) {
                                if (this.countyId == selectedVal) {
                                    isSelected = true;
                                }
                            }
                            countyOptions += '<option value="' + this.countyId + '" ' + (isSelected ? 'selected' : '') + '>' + this.countyName + '</option>';
                        });
                    }
                    $(".modifyBasicInfoForm select[name='countyId']").html(countyOptions);
                }
            })
        }
    };

    //获取企业性质字典数据
    var getEnterpriseNature = function () {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "enterprise_nature"},
            type: "GET",
            success: function (resp) {
                var enterpriseNatureOptions = '<option value="">请选择</option>';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        var selectedVal = $("select[name='enterpriseNature']").attr("selectedVal");
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }
                        enterpriseNatureOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }
                $(".modifyBasicInfoForm select[name='enterpriseNature']").html(enterpriseNatureOptions);
            }
        })
    }

    //获取企业规模字典数据
    var getEnterpriseScale = function () {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "enterprise_scale"},
            type: "GET",
            success: function (resp) {
                var enterpriseScaleOptions = '<option value="">请选择</option>';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        var selectedVal = $("select[name='enterpriseScale']").attr("selectedVal");
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }

                        enterpriseScaleOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }
                $(".modifyBasicInfoForm select[name='enterpriseScale']").html(enterpriseScaleOptions);
            }
        })
    }

    //获取用户尊称字典数据
    var getRespectfulName = function () {
        $.ajax({
            url: "/common/getDictionaryByGroupKey",
            data: {groupKey: "respectful_name"},
            type: "GET",
            success: function (resp) {
                var respectfulNameOptions = '';
                if (resp != null && resp.length > 0) {
                    $(resp).each(function () {
                        var isSelected = false;
                        var selectedVal = $("select[name='respectfulName']").attr("selectedVal");
                        if (selectedVal != null) {
                            if (this.groupValKey == selectedVal) {
                                isSelected = true;
                            }
                        }

                        respectfulNameOptions += '<option value="' + this.groupValKey + '" ' + (isSelected ? 'selected' : '') + '>' + this.groupValVal + '</option>';
                    });
                }
                $(".modifyBasicInfoForm select[name='respectfulName']").html(respectfulNameOptions);
            }
        })
    }

    //获取企业基本资料信息并填充数据
    var getBasicInformation = function () {
        $.ajax({
            url: "/demandSide/basicInformation",
            type: "GET",
            success: function (resp) {
                if (resp.code == null) {
                    document.write(resp);
                    return;
                }

                if (resp.code == "200") {
                    if (resp.data.logo != "" && resp.data.logo != null) {
                        //企业logo
                        $(".modifyBasicInfoForm img[name='logo']").prop("src", resp.data.logo);
                    }

                    //企业名称
                    $(".modifyBasicInfoForm .companyName").text(resp.data.companyName);
                    $(".modifyBasicInfoForm input[name='companyName']").val(resp.data.companyName);
                    //企业性质
                    $(".modifyBasicInfoForm select[name='enterpriseNature']").attr("selectedVal", resp.data.enterpriseNature);
                    $(".modifyBasicInfoForm select[name='enterpriseNature']").val(resp.data.enterpriseNature);
                    //企业规模
                    $(".modifyBasicInfoForm select[name='enterpriseScale']").attr("selectedVal", resp.data.enterpriseScale);
                    $(".modifyBasicInfoForm select[name='enterpriseScale']").val(resp.data.enterpriseScale);
                    //企业联系人
                    $(".modifyBasicInfoForm input[name='contact']").val(resp.data.contact);
                    //企业联系人尊称
                    $(".modifyBasicInfoForm select[name='respectfulName']").attr("selectedVal", resp.data.respectfulName);
                    $(".modifyBasicInfoForm select[name='respectfulName']").val(resp.data.respectfulName);
                    //企业联系移动号码
                    $(".modifyBasicInfoForm input[name='mobileNumber']").val(resp.data.mobileNumber);
                    //企业联系固话号码
                    $(".modifyBasicInfoForm input[name='phoneAreaCode']").val(resp.data.phoneAreaCode);
                    $(".modifyBasicInfoForm input[name='telephoneNumber']").val(resp.data.telephoneNumber);
                    $(".modifyBasicInfoForm input[name='extensionNumber']").val(resp.data.extensionNumber);
                    //企业省
                    $(".modifyBasicInfoForm select[name='provinceId']").attr("selectedVal", resp.data.provinceId);
                    $(".modifyBasicInfoForm select[name='provinceId']").val(resp.data.provinceId);
                    //企业市
                    $(".modifyBasicInfoForm select[name='cityId']").attr("selectedVal", resp.data.cityId);
                    getArea.getCity(resp.data.provinceId);
                    //企业区
                    $(".modifyBasicInfoForm select[name='countyId']").attr("selectedVal", resp.data.countyId);
                    getArea.getCounty(resp.data.cityId);
                    //企业详细地址
                    $(".modifyBasicInfoForm input[name='address']").val(resp.data.address);
                } else {
                    alert(resp.message);
                }
            }
        })
    }

    $(function () {
        //获取并加载企业基本信息
        getBasicInformation();
        //获取并加载企业性质
        getEnterpriseNature();
        //获取并加载企业规模
        getEnterpriseScale();
        //获取并加载企业联系人尊称
        getRespectfulName();
        //获取并加载省市区
        getArea.getProvince();

        //radio点击选中释放选中切换
        $('div.radio-box p.ipt-radio').click(function () {
            var _input = $(this).find('input[type=radio]');
            $('div.radio-box p.ipt-radio').removeClass('active');
            if (_input.is(':checked')) {
                _input.prop('checked', false);
                $(this).removeClass('active');
            } else {
                _input.prop('checked', true);
                $(this).addClass('active');
            }
        }).each(function () {
            var _input = $(this).find('input[type=radio]');
            if (_input.is(':checked')) {
                _input.prop('checked', false);
                $(this).removeClass('active');
            } else {
                _input.prop('checked', true);
                $(this).addClass('active');
            }
        });

        /**
         * 省份数据切换，联动市区数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='provinceId']", function () {
            var options = '<option value="">请选择</option>';
            $(".modifyBasicInfoForm select[name='cityId']").html(options);
            $(".modifyBasicInfoForm select[name='countyId']").html(options);

            getArea.getCity($(this).val());
        });

        /**
         * 市数据切换，联动区数据
         */
        $(".modifyBasicInfoForm").on("change", "select[name='cityId']", function () {
            var options = '<option value="">请选择</option>';
            $(".modifyBasicInfoForm select[name='countyId']").html(options);
            getArea.getCounty($(this).val());
        });

        //修改基本信息
        $(".modifyBasicInfoForm button[name='modifyBasicInfo']").click(function () {
            var _this = $(this);
            var formDataJson = {};

            if ($(".modifyBasicInfoForm canvas").size() > 0) {
                formDataJson["logo"] = $(".modifyBasicInfoForm canvas").get(0).toDataURL()
            }

            _this.attr('disabled', "true")

            var formData = $(".modifyBasicInfoForm").serializeArray();

            $(formData).each(function () {
                formDataJson[this.name] = this.value;
            });

            //异步提交表单
            $.ajax({
                url: "/demandSide/basicInformation",
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
