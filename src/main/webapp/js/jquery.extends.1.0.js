/**
 * Created by Administrator on 2016/8/18.
 */
(function($){
    /**
     * 分组
     * @param options
     * @returns {jQuery}
     */
    $.fn.fnPageList = function(options){
        options = $.extend({
            newClassName : 'row',
            column : 4,
            child : 'dl'
        }, options);

        if('undefined' === typeof this) return undefined;
        var list = $(this);
        var listDl = $(this).find(options.child);

        if('undefined' != typeof $(this).data('column')) options.column = parseInt($(this).data('column'));
        for(var i = 0; i < listDl.length; i+= options.column){
            for(var j = i, newDiv = $('<div ' + (options.newClassName != '' ? 'class=' +options.newClassName : '') + ' />'); j < i + options.column && j < listDl.length; j++){
                newDiv.append(listDl[j]);

            }
            list.append(newDiv.append('<div class=clearfix></div>'));
        }
        return this;
    };

})(jQuery);