define(['jquery','jquery.validation.zh_CN'],function(jQuery){
	var moduleContext=jQuery('.module[data-module="module.verifymng"]');

	jQuery(".verify-detail .btn").on('click',function(event){
		var target=event.currentTarget;

		var verifyState=jQuery(target).data('value');
		jQuery("#verifyState").val(verifyState);

		jQuery("#verifyForm").submit();
	});
});