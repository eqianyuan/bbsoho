/**
 * Created by Administrator on 2016/8/18.
 */
$(function(){
    //IE placeholder 修正
    $('textarea, input').placeholder();
    //WAP导航
    $('li.btn-menu').click(function(){
        $('div.normal-head ul.nav').slideToggle();
    });
});