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
            $(this).find('a.ipt-select-okay').hide();
        }else{
            $(this).find('a.ipt-select-okay').show();
        }
    });
    $(document).on('mouseleave', _selectStr, function(){ $(this).data('flag', 0); });
    //修改选择状态
    $(document).on('click', _selectStr + ' li[data-value]', function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
        }else{
            $(this).addClass('active');
        }
    });
    //显示选项列表
    $(document).on('click',  _selectStr + ' input[type=text]', function(){
        var _this = this;
        
        if($(_selectStr).find(_listStr).is(":visible")){
            $(this).parents(_selectStr).find(_listStr).slideUp();
        }else{
            $(this).parents(_selectStr).find(_listStr).slideDown(function(){
                _currentSelect = $(_this).parents(_selectStr);
            });
        }
    });

    //确定选择
    $(document).on('click',  _selectStr + ' a.ipt-select-okay', function(){
        var _parent = $(this).parents(_selectStr);
        var _values = new Array();
        $(_parent.find('ul li.active')).each(function(){
            _values.push($(this).text());
        });

        _parent.find('input[type=text]').val(_values).trigger('blur');
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

//MAP
function Map() {
    this.elements = new Array();
    //获取MAP元素个数
    this.size = function() {
        return this.elements.length;
    };
    //判断MAP是否为空
    this.isEmpty = function() {
        return (this.elements.length < 1);
    };
    //删除MAP所有元素
    this.clear = function() {
        this.elements = new Array();
    };
    //向MAP中增加元素（key, value)
    this.put = function(_key, _value) {
        this.elements.push( {
            key : _key,
            value : _value
        });
    };
    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function(_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    };
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function(_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    };
    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function(_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function(_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    };
    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    };
    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function() {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    };
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