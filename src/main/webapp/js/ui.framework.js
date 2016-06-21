/**
 *	UI框架代码
 *
 *
 */
define(['jquery',
	   'jquery.validation.zh_CN',
	   'bootstrap3.datetimepicker',
	   'bootstrap3.treeview',
	   'scojs.confirm',
	   'jquery.placeholder'],function(jquery){
	var UIFramework=function(config){
		this.init.call(this,config);
	}
	UIFramework.prototype={
		init:function(config){
			var self=this;
			if(config.datepicker){
				self.bindDatetimePicker();
			}
			if(config.autoModule){
				self.autoLoadModule();
			}
			if(config.sync){
				self.bindSync();
			}
			if(config.deleteConfirm){
				self.bindDeleteComfirm()
			}
			if(config.placeHolder){
				self.bindFixedPlaceholder();
			}
			self.bindComfirm();
		},
		loadModule:function(){
			require([moduleName]);
		},
		autoLoadModule:function(){
			jquery('.module[data-module]').each(function(i,moduleEle){
				var moduleName=jquery(moduleEle).data("module");
				require([moduleName]);
			});
		},
		bindDatetimePicker:function(){
			jquery('input[type="datetime"]').datetimepicker({
				language:'zh-CN',
				weekStart:1,
				todayBtn:1,
				autoclose:1,
				todayHighlight:1,
				startView:2,
				minView:2,
				forceParse:0
			});
		},
		bindSync:function(){
			jquery('.btn-add,.btn-edit').on('click',function(event){
				jquery(this).scojs_modal({
					title:"添加数据",
					target:"#editModal"
				});

				event.preventDefault();
				event.stopPropagation();
			});
		},
		bindDeleteComfirm:function(){
			jquery('.btn-delete').on('click',function(event){
				var self=jquery(this);
				var confirm=jquery.scojs_confirm({
					content:"是否确认删除这条记录!",
					title:"删除告警！",
					target:"#delConfirm"
				}).show();
				confirm.scomodal.$modal.find('[data-action]').attr('href',self.attr("href"))
				event.preventDefault();
				event.stopPropagation();
			});
		},
		bindComfirm:function(){
			jquery('.btn-confirm').on('click',function(event){
				var self=jquery(this);
					target=event.currentTarget,
					$target=jquery(target);
					console.log($target);
				var confirm=jquery.scojs_confirm({
					title:"确认框",
					content:$target.data('message'),
					target:"#Confirm"
				}).show();
				confirm.scomodal.$modal.find('[data-action]').attr('href',self.attr("href"))
				event.preventDefault();
				event.stopPropagation();
			});
		},bindFixedPlaceholder:function(){
			jQuery.placeholder.shim();
		}
	}
	return UIFramework;
});


