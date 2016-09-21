<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>注册 - 百百SOHO</title>
    <%@ include file="common_inport.jsp"%>
</head>
<body>
<!-- header -->
<div class="header">
    <div class="container">
        <!-- lr-logo -->
        <div class="lr-logo">
            <a href="index.jsp" class="left-logo">
                <img src="images/logo.png" alt="LOGO"/>
            </a>
            <div class="right-tit">工作平台</div>
            <div class="clearfix"></div>
            <div class="bottom-tit">用系统的规划，审核，管理，实现异地工作旅行</div>
        </div>
        <!-- /lr-logo -->
    </div>
</div>
<!-- /header -->
<!-- lr-wrapper -->
<div class="lr-wrapper">
    <div class="lr-img"><img src="images/login/lr_bg.jpg" alt="background-image"/></div>
    <div class="container">
        <!-- lr-box -->
        <div class="lr-box">
            <!-- nav-tabs -->
            <ul class="nav nav-tabs">
                <li class="active"><a href="#supplier-area" data-toggle="tab">个人注册</a></li>
                <li><a href="#demand-area" data-toggle="tab">企业注册</a></li>
                <li><a href="login.jsp">登录</a></li>
            </ul>
            <!-- /nav-tabs -->
            <!-- tab-content -->
            <div class="tab-content">
                <div class="tab-pane fade in active" id="supplier-area">
                    <form action="javascript:;" method="post" class="forms personal-form">
                        <div class="error warn-error">
                            <p>
                                <label class="error" style="display: inline-block;"></label>
                            </p>
                        </div>
                        <p class="ipt-txt"><input type="text" name="mobile" placeholder="手机号"/></p>
                        <p class="ipt-txt">
                            <input type="text" name="imgVerifyCode" value="" class="wd190" placeholder="图片验证码"/>
                            <a href="javascript:;" class="imgCode"><img id="verifyCodeImage"
                                                                        src="supplierSide/verifyCodeByPage"
                                                                        title="点击重新获取验证码"/></a>
                        </p>
                        <p class="ipt-txt">
                            <input type="text" name="smsVerifyCode" value="" class="wd190" placeholder="短信验证码"/>
                            <a href="javascript:;" class="telCode getSMSVerificationCode">获取验证码</a>
                        </p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" value="" placeholder="密码"/></p>
                        <p class="ipt-txt"><input type="text" name="inviteCode" value="" placeholder="邀请码选填"/></p>
                        <div class="error reg-error"></div>
                        <p class="ipt-txt">
                            <input type="checkbox" name="clause" checked disabled/>
                            <label data-toggle="modal" data-target="#relieveApply">同意使用条款</label>
                        </p>
                        <button type="button" name="supplierSubmit" class="ipt-submit">注册</button>
                    </form>
                </div>
                <div class="tab-pane fade" id="demand-area">
                    <form action="javascript:;" method="post" class="forms company-form">
                        <div class="error warn-error">
                            <p>
                                <label class="error" style="display: inline-block;"></label>
                            </p>
                        </div>
                        <p class="ipt-txt"><input type="text" name="email" value="" placeholder="邮箱号码"/></p>
                        <p class="ipt-txt"><input type="password" name="loginPassword" value="" placeholder="密码"/></p>
                        <p class="ipt-txt"><input type="password" name="confirmPassword" value="" placeholder="确认密码"/></p>
                        <p class="ipt-txt"><input type="text" name="inviteCode" value="" placeholder="邀请码选填"/></p>
                        <div class="error reg-error"></div>
                        <p class="ipt-txt">
                            <input type="checkbox" name="clause" checked disabled />
                            <label data-toggle="modal" data-target="#relieveApply">同意使用条款</label>
                        </p>
                        <button type="submit" name="demandSubmit" class="ipt-submit">注册</button>
                    </form>
                </div>
            </div>
            <!-- /tab-content -->
        </div>
        <!-- /lr-box -->
    </div>
</div>

<div class="modal fade" id="relieveApply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog wd560">
        <div class="modal-content">
            <div class="modal-body">
                <!-- relieveDeal -->
                <form action="#" method="post" class="relieveApply">
                    <h3>使用条款</h3>
                    <div style="text-indent: 2em;margin-top: 20px;margin-bottom: 20px;height: 400px;overflow-y: scroll;">
                        <p>
                            <strong>
                                本网站是南京驿乾元软件科技有限公司负责运营的、向用户（以下简称“您或用户”）提供找项目或者招技术人员短期驻场开发的互联网平台，以下本网站均指网站及南京驿乾元软件科技有限公司。
                            </strong>
                        </p>
                        <p>
                            <strong>
                                为了保障您的权益，请于注册或使用本服务前，仔细阅读并完全理解本服务条款的全部内容。您必须在不加修改地无条件完全接受本服务协议所包含的条款、条件和本网站已经发布的或将来可能发布的各类规则，并且遵守有关互联网及/或本网站的相关法律、规定与规则的前提下，才能进入用户注册程序。您只有成功注册成为本网站用户后，才能使用本网站提供的用户服务。如您不同意本服务条款的任意内容，请勿注册或使用本网站用户服务。根据《中华人民共和国合同法》，如您通过进入注册程序并点击相应确认按钮，即表示您与本网站已达成协议，自愿接受本服务条款；此后，您不得以未阅读本服务条款内容作任何形式的抗辩，也不得以未签署书面协议为由否认本协议的效力。
                            </strong>
                        </p>

                        <br>
                        <strong>
                            第一条 用户的身份限制
                        </strong>
                        <p>
                            1.1 本网站只接受持有有效身份证件的18周岁以上的具有完全民事行为能力的自然人成为网站用户。如您不具备上述资格，您应立即停止在本网站的注册程序、停止使用本网站服务，本网站有权随时终止您的注册进程及本网站服务，您应对您的注册给本网站带来的损失承担全额赔偿责任，且您的监护人（如您为限制民事行为能力的自然人）应承担连带责任。
                        </p>
                        <p>
                            1.2 在注册时和使用本网站服务的所有期间，您应提供您自身的真实、最新、有效及完整的信息资料并保证自您注册之时起至您使用本网站服务的所有期间，其所提交的所有资料和信息（包括但不限于电子邮件地址、联系电话、联系地址、邮政编码、个人身份信息）有效性及安全性。
                        </p>
                        <br>
                        <strong>
                            第二条 服务内容
                        </strong>
                        <p>
                            2.1 本网站是为企业和技术人员提供招人才和找项目短期驻场开发的专业技术性服务平台。
                        </p>
                        <p>
                            2.2 本网站就向您提供的服务是否收取服务费及服务费的具体标准和规则由本网站与您另行签署其他协议，以及本网站公布的规则确定。
                        </p>
                        <br>
                        <strong>
                            第三条 使用须知
                        </strong>
                        <p>
                            3.1 您不得利用本网站或本网站服务从事任何不符合中国法律法规或侵犯他人权益的活动。本网站在发现您从事该等活动时，有权不经通知而立即停止您对本网站的全部或部分功能的使用。
                        </p>
                        <p>
                            3.2 在使用本网站提供的任何服务的过程中，您不得发送、公布或展示任何垃圾、广告推销邮件、信息或其他可能违反中国法律法规及本协议的内容。本网站在发现您从事该等活动或发布该等内容时，有权不经您同意而直接删除该等内容，并有权不经通知而立即暂停或停止您对本网站的全部或部分功能的使用。
                        </p>
                        <p>
                            3.3 您在注册时向本网站提交的手机号码、电子邮箱、用户名、密码，请妥善保管，切勿泄露给第三方。
                        </p>
                        <p>
                            3.4 您发现有他人冒用或盗用您的用户名及密码或任何其他未经合法授权之情形时，应立即以有效方式通知本网站，要求暂停相关服务。同时，您理解本网站对您的请求采取行动需要合理期限，而在本网站采取实际行动之前，您应对任何或所有由未经授权人士使用此服务或此服务被用作未经授权用途负责，本网站对已执行的指令及(或)所导致的您的损失不承担任何责任。
                        </p>
                        <p>
                            3.5 您保证合法使用本网站提供的资讯或服务，遵守所有与本网站服务有关的协议、规则和程序。未经本网站事先书面授权，不使用任何非法手段擅自进入本网站的未公开系统。
                        </p>
                        <br>
                        <strong>
                            第四条 用户信息的保护及披露
                        </strong>
                        <p>
                            4.1 您同意本网站在业务运营中收集和储存您的用户信息，包括但不限于您自行提供的资料和信息，以及本网站自行收集、取得的您在本网站使用信息等。本网站收集和储存您的用户信息的目的在于提高为您提供服务的效率和质量。
                        </p>
                        <p>
                            4.2 您同意本网站在业务运营中使用您的用户信息，包括但不限于(1)进行用户身份、信息核实；（2）出于提供服务的需要在本网站公示您的相关信息；(3)向本网站的合作机构（该合作机构仅限于本网站为了完成拟向您提供的服务而合作的机构）提供您的用户信息；(4)由人工或自动程序对您信息进行评估、分类、研究；(5)使用您的用户信息以改进本网站的推广；(6)使用您提供的联系方式与您联络并向您传递有关业务和管理方面的信息。（7）用于配合有权机关依职权调取证据材料。
                        </p>
                        <br>
                        <strong>
                            第五条 用户承诺和保证
                        </strong>
                        <p>
                            5.1 本网站用户应保证严格遵守中国现行法律、法规、政府规章及其他应该遵守的规范性文件，不在本网站从事危害国家安全、洗钱、套现、传销等任何违法活动或者其他有违社会公共利益或公共道德的行为。
                        </p>
                        <p>
                            5.2 您确认在签署本协议之前已阅读包括但不限于以下与本协议及相关协议的订立及履行有关的风险提示，并对该等风险有充分理解和预期，您自愿承担该等风险可能给带来的一切责任和损失和责任。不可抗力：由于本网站及相关第三方的设备、系统故障或缺陷、病毒、黑客攻击、网络故障、网络中断、地震、台风、水灾、海啸、雷电、火灾、瘟疫、流行病、战争、恐怖主义、敌对行为、暴动、罢工、交通中断、停止供应主要服务、电力中断、经济形势严重恶化或其它类似事件导致的风险。
                        </p>
                        <br>
                        <strong>
                            第六条 免责声明
                        </strong>
                        <p>
                            6.1 除非另有书面协议约定，本网站在任何情况下，对用户使用本网站服务而产生的任何形式的直接或间接损失均不承担法律责任，包括但不限于资金损失、收益损失等。
                        </p>
                        <p>
                            6.2 因不可抗力或本网站服务器死机、网络故障、数据库故障、软件升级等问题造成的服务中断和对用户个人数据及资料造成的损失，本网站不承担任何责任，亦不予赔偿，但将尽力减少因此而给用户造成的损失和影响。
                        </p>
                        <p>
                            6.3 因黑客、病毒或密码被盗、泄露等非本网站原因所造成损失概由您本人自行承担。
                        </p>
                        <p>
                            6.4您须对您本人在使用本网站所提供的服务时的一切行为、行动（不论是否故意）负全部责任。
                        </p>
                        <p>
                            6.5当司法机关、政府部门或其他监管机构根据有关法律、法规、规章及其他政府规范性文件的规定和程序，要求本网站提供用户信息资料，本网站对据此作出的任何披露，概不承担责任。
                        </p>
                        <br>
                        <strong>
                            第七条 用户注销、暂停、终止或限制访问
                        </strong>
                        <p>
                            7.1 如个人工作一个自然月出现三次或三次以上违约，本网站将冻结您的账号三十天。冻结期间您的账户将不能报名工作或提现账户余额。超过三十天后，您的账户将自动解冻。但会对您的信用产生影响。
                        </p>
                        <p>
                            7.2无论本网站是否收费，只要您利用本网站从事违法活动或者严重违反本协议的约定，本网站可在不发出任何通知的情况下立即使您的账户无效，账号终止后，本网站没有义务为您保留原账号中或与之相关的任何信息，或转发任何未曾阅读或发送的信息给您或第三方。此外，本网站亦不会就终止用户账户使用而对您或任何第三者承担任何责任。
                        </p>
                        <p>
                            7.4 用户账户的暂停、中断或终止不代表用户责任的终止，用户仍应对其使用本网站提供服务期间的行为承担可能的违约或损害赔偿责任，同时本网站仍可保有用户的相关信息。
                        </p>
                        <br>
                        <strong>
                            第八条 知识产权保护
                        </strong>
                        <p>
                            8.1 本网站所有内容和服务，包括但不限于网站的整体结构、网页设计、文字、图片、图表、软件、视频文件、音频文件、源代码、广告以及本网站为用户提供的其它信息或资料，其知识产权属本网站所有。未经本网站书面许可，任何人不得为了商业目的进行复制或者以其它方式进行非法使用。
                        </p>
                        <p>
                            8.2 任何未经授权许可而使用本网站内容的行为均属于违法行为, 本网站保留追究相关使用人法律责任的权利。
                        </p>
                        <br>
                        <strong>
                            第九条 赔偿
                        </strong>
                        <p>
                            9.1 用户因违反本协议或本协议项下的其他文件，或违反了任何法律、法规的规定，给本网站造成损失的，应予赔偿，赔偿范围包括但不限于本网站的直接损失、间接损失以及维权费用等。
                        </p>
                        <p>
                            9.2 用户因违反前款规定而侵害了第三方的权利，导致本网站遭受任何第三方提起的索赔、诉讼或行政责任，用户承诺无条件承担相应责任并使本网站免责。若因此给本网站造成损失的，应予赔偿，赔偿范围包括但不限于本网站的维权费用、名誉损失以及本网站向第三方支付的补偿金等。
                        </p>
                        <br>
                        <strong>
                            第十条 保密条款
                        </strong>
                        <p>
                            10.1 对于本网站用户在验证企业或个人身份时所获得的信息，包括但不限于本网站信息资料、业务运营情况、商业秘密等，不得向任何第三人披露。
                        </p>
                        <p>
                            10.2 除本协议另有约定外，本网站须根据中国法律的规定对本网站用户个人信息、资产情况和相关资料予以保密。
                        </p>
                        <br>
                        <strong>
                            第十一条 信息变更
                        </strong>
                        <p>
                            11.1 本网站用户如变更账户信息、通讯地址、电话等相关重要信息，须及时通知本网站。因您未及时通知而导致自身受到的一切损失，由您自行承担责任。
                        </p>
                        <br>
                        <strong>
                            第十二条 争议的处理
                        </strong>
                        <p>
                            12.1 企业有权对技术人员的工作态度和工作质量进行评估，如技术人员出现旷工、早退、迟到等情况。企业可视情况克扣技术人员薪水。如出现企业克扣薪水，技术人员会收到扣除钱数及扣除原因的消息通知，如技术人员没有异议，剩余薪水将在48小时之内支付到技术个人账户。如技术人员有异议，请在48小时之内提起申诉。申诉期间，剩余薪水暂时冻结。待申诉结束后，支付剩余或全额薪水。技术个人如在48小时内没有提起申诉，则视为同意。
                        </p>
                        <p>
                            12.2如遇不可抗拒因素（自然灾害、战争、动乱等）企业可在任何时间段取消，企业无需承担任何损失。
                        </p>
                        <br>
                        <strong>
                            第十三条 其他
                        </strong>
                        <p>
                            13.1 本协议中的任何条款或部分条款因违反中国法律而无效的，不影响本协议其他条款的效力。
                        </p>
                    </div>
                    <div class="btn-box">
                        <button type="button" class="btn btn-cancel">同意</button>
                    </div>
                </form>
                <!-- /relieveDeal -->
            </div>
        </div>
    </div>
</div>
<!-- /lr-wrapper -->
<!-- footer -->
<%@ include file="footer.jsp"%>
<!-- /footer -->

<script>
    $(function () {
        $(document).on('click', 'button.btn-cancel', function(){
            $('#relieveApply').modal('hide');
        });

        //个人注册，点击验证码图片更换图片
        $("#verifyCodeImage").click(function () {
            var getVerifyCodeByPageUrl = $(this).prop("src");
            if (getVerifyCodeByPageUrl.indexOf("?") != -1) {
                getVerifyCodeByPageUrl = getVerifyCodeByPageUrl.substr(0, getVerifyCodeByPageUrl.indexOf("?"));
            }
            getVerifyCodeByPageUrl += "?" + new Date().valueOf();
            $(this).prop("src", getVerifyCodeByPageUrl);
        });

        //个人注册，点击获取短信验证码事件处理
        $(".getSMSVerificationCode").click(function () {
            //判断间隔时间是否归0
            if (timerByGetSMSVeerifyCodeInterval > 0 && timerByGetSMSVeerifyCodeInterval < 60) {
                return false;
            }

            $.ajax({
                type: "GET",
                url: "/supplierSide/sendSMSVerificationCodeByRegister",
                data: {
                    mobile: $("input[name='mobile']").val(),
                    verifyCodeByPage: $("input[name='imgVerifyCode']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        getSMSVeerifyCode();
                    }else{
                        $("#supplier-area .warn-error").show().find("label").text(resp.message);
                    }
                }
            });
        });

        //定义获取短信验证码按钮文字
        var getSMSVerifyicationCodeText ;
        //定义发送短信验证码间隔时间
        var timerByGetSMSVeerifyCodeInterval = 60;
        var getSMSVeerifyCode = function () {
            getSMSVerifyicationCodeText = timerByGetSMSVeerifyCodeInterval + "S";

            if (--timerByGetSMSVeerifyCodeInterval < 0) {
                timerByGetSMSVeerifyCodeInterval = 60;
                getSMSVerifyicationCodeText = "获取验证码";
            }else{
                setTimeout(getSMSVeerifyCode, 1000);
            }

            $(".getSMSVerificationCode").text(getSMSVerifyicationCodeText);
        }

        /**
         * 个人注册按钮点击提交事件
         */
        $("button[name='supplierSubmit']").click(function(){
            $.ajax({
                type: "POST",
                url: "/supplierSide/register",
                data: {
                    mobileNumber: $("input[name='mobile']").val(),
                    verifyCodeBySMS: $("input[name='smsVerifyCode']").val(),
                    loginPassword: $("#supplier-area input[name='loginPassword']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        window.location.href = "/login.jsp";
                    }else{
                        $("#supplier-area .warn-error").show().find("label").text(resp.message);
                    }
                }
            });
        });

        /**
         * 企业注册按钮点击提交事件
         */
        $("button[name='demandSubmit']").click(function(){
            $.ajax({
                type: "POST",
                url: "/demandSide/register",
                data: {
                    email: $("input[name='email']").val(),
                    loginPassword: $("#demand-area input[name='loginPassword']").val(),
                    confirmPassword: $("#demand-area input[name='confirmPassword']").val()
                },
                success: function (resp) {
                    if (resp.code == "200") {
                        window.location.href = "/login.jsp";
                    }else{
                        $("#demand-area .warn-error").show().find("label").text(resp.message);
                    }
                }
            });
        });

    })
</script>
</body>
</html>