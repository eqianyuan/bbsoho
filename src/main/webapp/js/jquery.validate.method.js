/**
 * Created by jiqiang on 2016/8/1.
 */
$.validator.addMethod("isMobile", function(value, element) {
    var tel = /^1\d{10}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确手机号");
