/**
 * Created by jiqiang on 2016/8/21.
 */
//点击事件
function talentsTopLoad(){
    $('a[data-target][data-value]').click(function(){
        var _target = $(this).data('target');
        var _value = $(this).data('value');

        if('undefined' !== typeof $('#' + _target)[0]){
            $('a[data-target=' + _target + '][data-value]').removeClass('act');
            $('#' + _target).val(_value);
            $(this).addClass('act');

            loadList();
        }
    });
}

//ajax加载数据
function loadList(){
    //获取查询参数
    console.log($('form.search-form input').serialize());
    //ajax do something
    //...
}


$(function(){
    talentsTopLoad();           //分类加载

    //更多
    $('div.talents-top a.more').each(function(){
        var _dl = $(this).parents('dl');
        var _list = _dl.find('div.tt-list').height('auto');
        var _baseH = 40;
        if(_list.outerHeight(true) > _baseH){
            $(this).show().data('flag', 0).on('click', function(){
                if(!parseInt($(this).data('flag'))){
                    $(this).data('flag', 1).text('收缩');
                    _list.height('auto');
                }else{
                    $(this).data('flag', 0).text('更多');
                    _list.height(_baseH);
                }
            });
            _list.height(_baseH);
        }
    });
});