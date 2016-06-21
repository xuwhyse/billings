/**
 * 通用模块
 * @param  {[type]} jQuery [description]
 * @return {[type]}        [description]
 */
require(['jquery','jquery.validation'],function(jQuery){

	// Accept a value from a file input based on a required mimetype
	jQuery.validator.addMethod("afterdate", function(value, element, param) {
		try{
			var target=$(param);
			if(target.val()==""){
				return true;
			}else{
			
			var valueDate=new Date(value.replace(/-/g, "/"));


			var targetDate=new Date(target.val().replace(/-/g, "/"));
			if (this.settings.onfocusout ) {
				target.unbind(".validate-equalTo").bind("blur.validate-equalTo", function() {
					$(element).valid();
				});
			}

			return valueDate.valueOf()-targetDate.valueOf()>0
			}
		}catch(e){
			return false;
		}
		
	}, jQuery.format("结束时间应该大于开始时间！"));
});

require(['jquery','jquery.validation'],function(jQuery){

	/**
	 * 用户名格式英文或数字或下划线“_”
	 * 长度为6~16位
	 * @param  {[type]} value   [description]
	 * @param  {[type]} element [description]
	 * @param  {[type]} param   [description]
	 * @return {[type]}         [description]
	 */
	jQuery.validator.addMethod("username", function(value, element, param) {
		return /^([a-zA-Z0-9_@.]{6,30})$/i.test(value);
	}, jQuery.format("用户名格式不正确！"));
});

require(['jquery','jquery.validation'],function(jQuery){

	/**
	 * 国内手机格式验证
	 * 长度为11位 例：1383838438
	 * @param  {[type]} value   [description]
	 * @param  {[type]} element [description]
	 * @param  {[type]} param   [description]
	 * @return {[type]}         [description]
	 */
	jQuery.validator.addMethod("mobile", function(value, element, param) {
		return /^(1[3,5,8]{1}\d{9})?$/.test(value);
	}, jQuery.format("手机格式不正确！"));
});

require(['jquery','jquery.validation'],function(jQuery){

	// Accept a value from a file input based on a required mimetype
	jQuery.validator.addMethod("after", function(value, element, param) {
		
		var target=$(param);
		console.log(this.errorsFor(target.get(0)));
		return (value-target.val())>=0
		
		
	}, jQuery.format("最大值应该大于或者等于最小值！"));
});

require(['jquery','jquery.validation'],function(jQuery){

	// Accept a value from a file input based on a required mimetype
	jQuery.validator.addMethod("before", function(value, element, param) {
		
		var target=$(param);
		console.log(this.errorsFor(target.get(0)));
		return (value-target.val())<=0
		
		
	}, jQuery.format("最大值应该大于或者等于最小值！"));
});

require(['jquery','jquery.validation'],function(jQuery){

	// 身份证 15或者18位验证
	jQuery.validator.addMethod("idcard", function(value, element, param) {
		return  /^(\d{14}(\d{3})?[\w|\d])?$/.test(value);
	}, jQuery.format("身份证号码格式不正确！"));
});

require(['jquery','jquery.validation'],function(jQuery){
	jQuery.validator.addMethod("emailto", function(value, element, param) {
		var bool=/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
		if(!bool){
			$(param).attr("disabled","disabled");
		}else{
			$(param).removeAttr("disabled");
		}
		return bool;
	}, jQuery.format("请输入有效电子邮箱"));
});

require(['jquery','jquery.validation'],function(jQuery){
	jQuery.validator.addMethod("mobileto", function(value, element, param) {
		var bool=/^(1[3,5,8]{1}\d{9})?$/.test(value);
		if(!bool){
			$(param).attr("disabled","disabled");
		}else{
			$(param).removeAttr("disabled");
		}
		return bool;
	}, jQuery.format("请输入有效手机号码"));
});
