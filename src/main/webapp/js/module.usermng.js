define(['jquery','jquery.validation.zh_CN','typeahead'],function(jQuery){
	var moduleContext=jQuery('.module[data-module="module.usermng"]'),
		form=moduleContext.find('.validateForm');
		jQuery(form).validate({
			rules:{
				username:{
					alphanumeric:true,
					remote:{
						cache:false,
 						url:'/manager/usermng/checkUesrname',
 						type: "post",
					    dataType: "json",
					    data: {
					        userid: $('input[name="userid"]').val()
						}
					}
				}
			},
			messages:{
				username:'用户名称已存在，请使用其他名称！'
			}
		});
});