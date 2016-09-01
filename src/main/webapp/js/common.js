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

//select选框
function fixSelect(){
    var _selectStr = 'div.ipt-select';
    var _listStr = 'div.select-list';
    var _currentSelect = null;
    $(_selectStr).attr('data-flag', 0);
    //
    $(window).on('click', function(){
        if(null != _currentSelect && !parseInt(_currentSelect.data('flag'))){
            _currentSelect.find(_listStr).slideUp('fast');
        }
    });

    //状态
    $(document).on('mouseenter', _selectStr, function(){
        $(this).data('flag', 1);
        if($(this).find('li[data-value]').size()<= 0){
            $(this).find('a.ipt-select-okay').remove();
        }
    });
    $(document).on('mouseleave', _selectStr, function(){ $(this).data('flag', 0); });
    //修改选择状态
    $(document).on('click', _selectStr + ' li[data-value]', function(){
        $(_selectStr).find('ul li[data-value]').removeClass('active');
        $(this).addClass('active');
    });
    //显示选项列表
    $(document).on('click',  _selectStr + ' input[type=text]', function(){
        var _this = this;
        $(_selectStr).find(_listStr).hide();
        $(this).parents(_selectStr).find(_listStr).slideDown(function(){
            _currentSelect = $(_this).parents(_selectStr);
        });
    });

    //确定选择
    $(document).on('click',  _selectStr + ' a.ipt-select-okay', function(){
        var _parent = $(this).parents(_selectStr);
        var _value = _parent.find('ul li.active[data-value]').data('value');
        if('undefined' === typeof _value || '' == $.trim(_value)) return;

        _parent.find('input[type=text]').val(_value).trigger('blur');
        _parent.find(_listStr).slideUp();
    });
}

//radio
function selectRadioBox(){
    $(document).on("click", 'div.radio-box p.ipt-radio', function(){
        var _input = $(this).find('input[type=radio]');
        var _name = _input.attr('name');
        $('input[type=radio][name='+ _name +']').each(function(){
            $(this).prop('checked', false).parents('p.ipt-radio').removeClass('active');
        });

        _input.prop('checked', true);
        $(this).addClass('active');
    });
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
    //系统消息
    $('<div id=systemMsgWrap>').load('pop/systemMsg.html').appendTo('body');
    $('<div id=qrCodeWrap>').load('pop/payCode.html').appendTo('body');
});