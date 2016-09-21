<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>人才简历 - 百百SOHO</title>
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
        <div class="tab-content">
            <!-- 需求信息 START -->
            <div class="tab-pane fade  in active" id="demandInfo">
                <!-- enterprise-top -->
                <div class="enterprise-top">
                    <div class="resume-top left">
                        <dl>
                            <dt><a class="head"><img src="images/head.png" class="headPortrait"/></a></dt>
                            <dd>
                                <h3 class="nickName"></h3>
                                <p class="status"></p>
                            </dd>
                        </dl>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <!-- /enterprise-top -->
                <table class="resume">
                    <thead>
                    <tr>
                        <th colspan="4">
                            <h3>个人信息</h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>姓名：</th>
                        <td class="gray nickName"></td>
                        <th>性别：</th>
                        <td class="gray sex"></td>
                    </tr>
                    <tr>
                        <th>年龄：</th>
                        <td class="gray age"></td>
                        <th>所处行业：</th>
                        <td class="gray industry"></td>
                    </tr>
                    <tr>
                        <th>工作年限：</th>
                        <td class="gray workingYears"></td>
                        <th>期望月薪：</th>
                        <td class="gray expectPay"></td>
                    </tr>
                    <tr>
                        <th>期望工作时间：</th>
                        <td class="gray expectWorkTime"></td>
                        <th>期望工作地点：</th>
                        <td class="gray expectWorkArea"></td>
                    </tr>
                    </tbody>
                    <thead>
                    <tr>
                        <th colspan="4">
                            <h3>其它信息</h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>毕业院校：</th>
                        <td class="gray schoolName"></td>
                        <th>学历：</th>
                        <td class="gray highestSchooling"></td>
                    </tr>
                    <tr>
                        <th>专业：</th>
                        <td class="gray professionalName"></td>
                        <th>特长/擅长：</th>
                        <td class="gray discribe"></td>
                    </tr>
                    <tr>
                        <th>外语：</th>
                        <td class="gray foreignLanguage"></td>
                        <th>外语能力：</th>
                        <td class="gray foreignLanguageAbility"></td>
                    </tr>
                    </tbody>
                    <thead>
                    <tr>
                        <th colspan="4">
                            <h3>技能掌握度</h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="4">
                            <ul class="progress-list workProficiency" id="selectLanguage">
                            </ul>
                        </td>
                    </tr>
                    </tbody>
                    <thead>
                    <tr>
                        <th colspan="4">
                            <h3>工作经历</h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody class="workExperience">
                    </tbody>
                    <thead>
                    <tr>
                        <th colspan="4">
                            <h3>项目经历</h3>
                        </th>
                    </tr>
                    </thead>
                    <tbody class="projectExperience">
                    </tbody>
                </table>
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
<script type="text/javascript">
    //获取需求信息并填充数据
    var getResume = function () {
        $.ajax({
            url: "/supplierSide/search/supplierResume/${param.supplierId}",
            type: "GET",
            success: function (resp) {
                //头像
                $(".headPortrait").prop("src", resp.headPortrait)
                //当前工作状态
                $(".status").text(resp.status);
                //昵称
                $(".nickName").text(resp.nickName);
                //性别
                $(".sex").text(resp.sex);
                //工龄
                $(".workingYears").text(resp.workingYears);
                //年龄
                $(".age").text(resp.age);
                //行业
                $(".industry").text(resp.industry + "->" + resp.workType + "->" + resp.work);
                //学历
                $(".highestSchooling").text(resp.highestSchooling);
                //毕业院校
                $(".schoolName").text(resp.schoolName);
                //专业
                $(".professionalName").text(resp.professionalName);
                //期望月薪
                $(".expectPay").text(resp.expectPay);
                //期望工作地
                $(".expectWorkArea").text(resp.expectWorkProvinceId + resp.expectWorkCityId + resp.expectWorkCountyId);
                //期望工作时间
                $(".expectWorkTime").text(resp.expectWorkTime);
                //擅长
                $(".discribe").text(resp.discribe);
                //外语
                $(".foreignLanguage").text(resp.foreignLanguage);
                //外语能力
                $(".foreignLanguageAbility").text(resp.foreignLanguageAbility);

                //技能掌握情况
                if (resp.workProficiencyVOs.length > 0) {
                    $(resp.workProficiencyVOs).each(function () {
                        var workProficiencyLi = '<li data-value="' + this.masterDegree + '" data-max="100" data-name="' + this.work + '">'
                                + '<div class="progress-tit fl">' + this.work + '</div>'
                                + '<div class="progress fl"></div>'
                                + '<div class="clearfix"></div>'
                                + '</li>';

                        $(".workProficiency").append(workProficiencyLi);
                    });

                    //可拖拽进度条
                    addProgressbar();
                }

                //工作经历
                if (resp.workExperienceVOs.length > 0) {
                    $(resp.workExperienceVOs).each(function () {
                        var workExperienceHtml = '<tr><th rowspan="2">' + this.beginYears + "-" + this.beginMonth + '~' + this.endYears + "-" + this.endMonth + ':</th><td colspan="3"><p>' + this.companyName + ' &nbsp;&nbsp; ' + this.positionName + '</p></td></tr>'
                                + '<tr><td colspan="3" class="desc gray"><p>' + this.workDiscribe + '</p></td></tr>';

                        $(".workExperience").append(workExperienceHtml);
                    });
                }

                //项目经验
                if (resp.projectExperienceVOs.length > 0) {
                    $(resp.projectExperienceVOs).each(function () {
                        var projectExperienceHtml = '<tr><th rowspan="2">' + this.beginYears + "-" + this.beginMonth + '~' + this.endYears + "-" + this.endMonth + ':</th><td colspan="3"><p>' + this.projectName + ' &nbsp;&nbsp; <a target="view_frame" href="' + this.visitAddress + '">' + this.visitAddress + '</a></p></td></tr>'
                                + '<tr><td colspan="3" class="desc gray"><p>' + this.projectDiscribe + '</p></td></tr>';

                        $(".projectExperience").append(projectExperienceHtml);
                    });
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
    }

    $(function () {
        getResume();
    });
</script>
</html>