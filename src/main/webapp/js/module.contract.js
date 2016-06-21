define(['jquery',
	'jquery.validation.zh_CN',
	'jquery.fileupload-image',
    'jquery.fileupload-process',
    'jquery.fileupload-validate'
	],function(jQuery){
	var moduleContext=jQuery('.module[data-module="module.contract"]'),
		form=moduleContext.find('.validateForm');

	jQuery(form).validate();
	jQuery('input[type="file"]').fileupload({ 
        autoUpload: false,
		replaceFileInput:false,
    }).on('fileuploadadd', function (e, data) {
		var self=$(this);
        data.context = $('<div/>').appendTo('#fileBox');
        $.each(data.files, function (index, file) {
            var node = $('<p/>').append($('<span/>').text(file.name));
            node.appendTo(data.context);
        });
		self.clone(true).insertAfter(self);
		data.context.append(self.addClass("hide"));
    });
	jQuery("#intentionId").live('change',function(event){
	    var target=event.currentTarget;
        $target=jQuery(target);
		jQuery.ajax({
			url: "getIntention.do",
			cache:false,
			type: 'POST',
			data:{
				id:$target.attr("value")
			},
			dataType: 'json',
			success: function(data, textStatus, xhr) {
				if(!data.exception){
					jQuery("#prodName").attr("value",data.data.intentionName);
					jQuery("#leverageRatio").val(data.data.leverageRatio);
					jQuery("#privateInvestAmount").attr("value",data.data.investAmount);
					jQuery("#investStrategy").val(data.data.investStrategy);
					
					jQuery("[name='investScope']").each(function(){
						jQuery(this).removeAttr("checked");
					});
					
					
					for(var index in data.scopeList){
						jQuery("#" + data.scopeList[index].investScopeDict).attr("checked",'true');
					};
					
					jQuery("#clearanceLine").attr("value",data.risk.clearanceLine);
					jQuery("#shareholdingRatio").attr("value",data.risk.shareHoldingRatio);
					jQuery("#investmentRestriction").attr("value",data.risk.investmentRestriction);
					jQuery("#capitalCost").attr("value",data.data.hopeFinancingCost);
					jQuery("#financingCycle").val(data.data.tolerateFinancingCycle);
					jQuery("#investType").val(data.data.investType);
				}
			}
		});
	});
});


