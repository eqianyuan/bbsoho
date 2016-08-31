/**
 * Created by Administrator on 2016/8/18.
 */
//裁剪图片
function hideCutImageDialog(canvas, serviceUrl){
    $('#viewHeadBox').html(canvas);
    $('#headImageUrl').val(serviceUrl);

    closeCutImageDialog();
}

//关闭剪裁弹框
function closeCutImageDialog(){
    $('#uploadImage').modal('hide');
}

$(function(){
    $(this).on('hidden.bs.modal', function () {
        if('undefined' !== typeof document.getElementById('uploadImageFrame')) document.getElementById('uploadImageFrame').contentWindow.location.reload(true);
    });

    //IE placeholder 修正
    $('textarea, input').placeholder();
    //WAP导航
    $('li.btn-menu').click(function(){
        $('div.normal-head ul.nav').slideToggle();
    });
    $('<div id=systemMsgWrap>').load('systemMsg.html').appendTo('body');
});