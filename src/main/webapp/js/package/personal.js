/**
 * Created by jiqiang on 2016/8/21.
 */
//需求信息校验
function demandInfoVerify(){
    $('form.demandInfo-form').validate({
        errorLabelContainer: $("form.demandInfo-form div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler:function(form){
            alert("表单事件!");
            form.submit();
        },
        rules : {
            "demandJob" : { required : true }
        },
        messages : {
            "demandJob" : { required : "请输入需求岗位" }
        }
    });
}

//企业信息校验
function enterpriseInfoVerify(){
    $('form.enterprise-form').validate({
        errorLabelContainer: $("form.enterprise-form div.error"),
        wrapper: "p",
        ignore: ".ignore",
        submitHandler:function(form){
            alert("表单事件!");
            form.submit();
        },
        rules : {
            "companyName" : { required : true }
        },
        messages : {
            "companyName" : { required : "请输入企业名称" }
        }
    });
}

$(function(){
    demandInfoVerify();         //调用需求信息校验函数
    enterpriseInfoVerify();    //调用企业信息校验函数

    $('div.radio-box p.ipt-radio').click(function(){
        var _input = $(this).find('input[type=radio]');
        $('div.radio-box p.ipt-radio').removeClass('active');
        console.log();
        if(_input.is(':checked')){
            _input.prop('checked', false);
            $(this).removeClass('active');
        }else{
            _input.prop('checked', true);
            $(this).addClass('active');
        }
    }).each(function(){
        var _input = $(this).find('input[type=radio]');
        if(_input.is(':checked')){
            _input.prop('checked', false);
            $(this).removeClass('active');
        }else{
            _input.prop('checked', true);
            $(this).addClass('active');
        }
    });
});