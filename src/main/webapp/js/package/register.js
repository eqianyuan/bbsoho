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
            alert("注册提交事件!");
            form.submit();
        },
        rules : {
            "telPhone" : { required : true, isMobile : true  },
            "imgVerifyCode" : { required : true },
            "telVerifyCode" : { required : true },
            "userPwd" : { required : true },
            "inviteCode" : { required : true },
            "clause" : { required : true }
        },
        messages : {
            "telPhone" : { required : "请输入手机号", isMobile : "请输入正确手机格式"  },
            "imgVerifyCode" : { required : "请输入图片验证码" },
            "telVerifyCode" : { required : "请输入手机验证码" },
            "userPwd" : { required : "请输入密码" },
            "inviteCode" : { required : "请输入邀请码" },
            "clause" : { required : "请选择条款" }
        }
    });

    //企业注册校验
    $('form.company-form').validate({
        errorLabelContainer: $("form.company-form div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler:function(form){
            alert("注册提交事件!");
            form.submit();
        },
        rules : {
            "telPhone" : { required : true, isMobile : true  },
            "imgVerifyCode" : { required : true },
            "telVerifyCode" : { required : true },
            "userPwd" : { required : true },
            "inviteCode" : { required : true },
            "clause" : { required : true }
        },
        messages : {
            "telPhone" : { required : "请输入手机号", isMobile : "请输入正确手机格式"  },
            "imgVerifyCode" : { required : "请输入图片验证码" },
            "telVerifyCode" : { required : "请输入手机验证码" },
            "userPwd" : { required : "请输入密码" },
            "inviteCode" : { required : "请输入邀请码" },
            "clause" : { required : "请选择条款" }
        }
    });
});