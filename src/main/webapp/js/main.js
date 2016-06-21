/**
 * 全局模块加载
 *
 **/

define(['jquery', 'bootstrap3', 'jquery.validation.zh_CN', 'common','jquery.placeholder'], function(jQuery) {
    // 全局验证js 注册
    jQuery.placeholder.shim();
	jQuery("#carousel-index").carousel();
    jQuery.extend(jQuery.validator.messages, {
        integer: "只能输入整数数字！"
    });
    jQuery(".validatorForm").each(function(idx, item) {
       // console.log(jQuery(this));
        jQuery(this).validate({
            errorPlacement: function(error, element) {
                var fieldRow = element.parents(".form-group");
                if (fieldRow.find(".help-block").length > 0) {
                    error.appendTo(fieldRow.find(".help-block"));
                } else {
                    error.appendTo(fieldRow);
                }
            }
        });
    });
    var moduleStr = jQuery("body").data("module"),
        module = moduleStr.split("#");
    if (module[0] !== '') {
        require(['www/' + module[0]], function(mod) {
            if (module[1]) {
                mod[module[1]]();
            } else {
                mod.init();
            }
        });
    }
});
