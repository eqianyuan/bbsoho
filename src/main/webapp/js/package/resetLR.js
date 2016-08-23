/**
 * Created by jiqiang on 2016/8/20.
 */
$(function(){
    //个人注册校验
    $('form.personal-form').validate({
        errorLabelContainer: $("form.personal-form div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler:function(form){
            alert("重置提交事件!");
            form.submit();
        },
        rules : {
            "telPhone" : { required : true, isMobile : true },
            "imgVerifyCode" : { required : true },
            "telVerifyCode" : { required : true },
            "userPwd" : { required : true },
            "againPwd" : { required : true, equalTo : "#pUserPwd" }
        },
        messages : {
            "telPhone" : { required : "请输入手机号", isMobile : "请输入正确手机格式" },
            "imgVerifyCode" : { required : "请输入图片验证码" },
            "telVerifyCode" : { required : "请输入手机验证码" },
            "userPwd" : { required : "请输入密码" },
            "againPwd" : { required : "请输入确认密码", equalTo : "两次密码不一致" }
        }
    });

    //企业注册校验
    $('form.company-form').validate({
        errorLabelContainer: $("form.company-form div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler:function(form){
            alert("重置提交事件!");
            form.submit();
        },
        rules : {
            "telPhone" : { required : true, isMobile : true },
            "imgVerifyCode" : { required : true },
            "telVerifyCode" : { required : true },
            "userPwd" : { required : true },
            "againPwd" : { required : true, equalTo : "#cUserPwd" }
        },
        messages : {
            "telPhone" : { required : "请输入手机号", isMobile : "请输入正确手机格式" },
            "imgVerifyCode" : { required : "请输入图片验证码" },
            "telVerifyCode" : { required : "请输入手机验证码" },
            "userPwd" : { required : "请输入密码" },
            "againPwd" : { required : "请输入确认密码", equalTo : "两次密码不一致" }
        }
    });
});