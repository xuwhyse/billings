define(['jquery', 'jquery.fileupload-image','jquery.fileupload-process','jquery.fileupload-validate','jquery.validation.zh_CN','bootstrap3.datetimepicker'],function(jQuery){

	var moduleContext=jQuery('.module[data-module="module.discussmng"]'),
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
		jQuery('input.datepicker').datetimepicker({
			language:'zh-CN',
			weekStart:1,
			todayBtn:1,
			autoclose:1,
			todayHighlight:1,
		//	startView:2,
		//	minView:2,
			forceParse:0
		});
	jQuery(".show_meeting").live('click',function(e){
		var target=e.currentTarget,
		parent=jQuery(target).parents("tr");
		moreTr=parent.next();
		if(moreTr.hasClass('hide')){
			moreTr.removeClass("hide");
			jQuery(target).html("-");
		}else{
			moreTr.addClass("hide");
			jQuery(target).html("+");
		}
	})
});
