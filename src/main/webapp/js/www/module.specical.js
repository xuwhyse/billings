define(['jquery',
    'bootstrap3',
    'bootstrap3.datetimepicker.lang-zh-CN'
], function(jQuery) {
    return {
        init: function() {
            this.specicalValiation();
        },
		/**
		 * 特殊验证规则 每次只对最上方一个对象验证一次
		 *
		 *
		 *
		 * */
        specicalValiation: function() {
            jQuery(".specical_form").each(function(idx, item) {
                jQuery(this).validate({
                    focusInvalid: false,
                    onfocusout: null,
                    onkeyup: null,
                    highlight: function(element, errorClass, validClass) {
                        var errorList = this.errorList;
                        if (element == errorList[0].element) {
                            $(element).addClass(errorClass).removeClass(validClass);
                        }else{
							 $(element).removeClass(errorClass).removeClass(validClass);
						}

                    },
                    errorPlacement: function(error, element) {
                        error.appendTo(jQuery(".error_box"));
                    }
                });
            });
        }
    };
});
