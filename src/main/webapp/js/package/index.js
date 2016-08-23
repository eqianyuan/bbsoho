/**
 * Created by jiqiang on 2016/8/21.
 */
$(function(){
    //分列重构
    $('#advantage').fnPageList({ column : 3 });
    $('#flowIndex').fnPageList({ column : 3 });
    $('#fLink').fnPageList({ column : 5, child : 'a' });
});