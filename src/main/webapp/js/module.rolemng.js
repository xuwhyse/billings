define(['jquery','jquery.validation.zh_CN'],function(jQuery){
	var moduleContext=jQuery('.module[data-module="module.rolemng"]'),
		form=moduleContext.find('.validateForm');
		jQuery(form).validate({
			rules:{
				rolename:{
					remote:{
 						url: '/manager/rolemng/checkRolename.do',
 						type: "post",
 						cache:false,              
					    dataType: "json",   
					    data: {
					        roleid: $('input[name="roleid"]').val(),
							
					   }
					}
				}
			},
			messages:{
				rolename:'角色名称已存在'
			}

		});
	
});