/**
 * Created by jiqiang on 2016/8/20.
 */
$(function(){
    //登录校验
    $('form.login-from').validate({
        errorLabelContainer: $("form.login-from div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler:function(form){
            alert("登录提交事件!");
            form.submit();
        },
        rules : {
            "accountName" : { required : true },
            "userPwd" : { required : true }
        },
        messages : {
            "accountName" : { required : "请输入账号" },
            "userPwd" : { required : "请输入密码" }
        }
    });
});