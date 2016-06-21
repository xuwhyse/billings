define(['jquery',
	'bootstrap3',
	'jquery.validation.zh_CN',
	'jquery.tmpl',
	'bootstrap3.datetimepicker.lang-zh-CN'], function($) {

	var initPage = function(){
		//shareholder，team页面初始化化
		//$('#msgModel').modal('show');//调试model框用
		$('#neworgsh').on('click', function(event) {
			$('#newOrgSH').hasClass('hide')?$('#newOrgSH').removeClass('hide'):$('#newOrgSH').addClass('hide');
			$('#newNaturalSH').hasClass('hide') || $('#newNaturalSH').addClass('hide');
		});
		$('#newnaturalsh').on('click', function(event) {
			$('#newNaturalSH').hasClass('hide')?$('#newNaturalSH').removeClass('hide'):$('#newNaturalSH').addClass('hide');
			$('#newOrgSH').hasClass('hide') || $('#newOrgSH').addClass('hide');
		});
		if(document.getElementById("noShMsg") != null){
			initNoShMsg();
		}
		initComponet();
		initForm();
	}

	//没有消息时的提示信息
	function initNoShMsg(){
		$(document).on('click.hideMsg',function(e){
			if(!$('#newOrgSH').hasClass('hide') || ($('#newNaturalSH')[0] && !$('#newNaturalSH').hasClass('hide'))
				|| $('.readonly')[0])
			{
				$('#noShMsg').addClass('hide');
				$('.readonly')[0] && $(document).off('click.hideMsg');
			}else if($(e.target||e.srcElement).hasClass('edit')){
			    $(document).off('click.hideMsg');
			}else{
				$("#noShMsg").removeClass('hide');
			}
		});
	}

	function initComponet(){
		require(['www/module.common'], function(common) {
            common.initUpload();
			common.initDatePicker();
        });
	}
	//初始化页面上的form
	function initForm(){
		$(document).on('click', '.edit', function(event) {
			var oForm = $(this).parents('form');//取本form
			oForm.find('textarea').removeAttr('readonly');
			oForm.find('textarea').attr('rows',5);
			oForm.find('input[type=text]').removeAttr('disabled');
			oForm.removeClass('readonly');
			oForm.hasClass('natrualRead')&&oForm.removeClass('natrualRead');
			oForm.find('.save').removeClass('hide');
			$(this).addClass('hide');
		});
		//class 'save':submit its own form
		$(document).on('click','.save',function(event){
			var oForm = $(this).parents('.editForm');
			if(oForm.find('input[type=hidden]').val() == ''){
				//新增
				oForm.trigger('submit',[true]);
			}else{
				//编辑
				oForm.trigger('submit',[false]);
			}
		});
		$(document).on('click','.del',function(event){
			event.preventDefault();
			var oThis = $(this);
			if(confirm('确定删除?')){
				sendAjax(oThis.attr('href'),'',function(res){
					oThis.parents('.editForm').remove();
					//当全部删除时，显示没有内容提示信息
					if($('.readonly')[0] == null){
						initNoShMsg();
						if(!$('#newOrgSH').hasClass('hide') || ($('#newNaturalSH')[0] && !$('#newNaturalSH').hasClass('hide')))
							{
								$('#noShMsg').addClass('hide');
							}else{
								$('#noShMsg').removeClass('hide');
							}
					}
					//console.log(res.entity);
				});
			}
		});
		$(document).on('submit','.editForm', function(event,isNew) {
			//console.log(isNew);
			event.preventDefault();
			var oThis = $(this);
			var args = oThis.serializeArray();
			sendAjax(oThis.attr('action'),args,function(res){
				resHandler(res,oThis,isNew);
			});
		});
		//}
	}

	//几秒钟关闭模态框
	function showHint(sec,msg,callback){
		$('#msgModel').modal('show');
		$('#countDown').html(sec);
		$('#msgContent').html(msg);
		//返回上一页
		$('.returnToLast').one('click',function(event){
			$('#msgModel').modal('hide');
			callback();
			clearInterval(count);
			sec = 0;
		});
		var count = setInterval(function(){
			if(sec-- === 1){
				$('#msgModel').modal('hide');
				callback();
				clearInterval(count);
			}else{
				$('#countDown').html(sec);
			}
		},1000);
	}
    /*处理Ajax返回Json
     *@para:{
     *      	execption:false,//是否出错
     *       	msg:"success",//模态框提示框
     *       	entity:{//返回项目
     *       	}
     *       }
     *      isNew:true新增 false更新
     */
	function resHandler(res,obj,isNew){
		if(res === undefined){
			//console.log('---ERROR:没有返回值---');
			return;
		}
		if(res.execption){
			//异常。。。
			showHint(2,res.msg);
		}else{
			showHint(5,res.msg,function(){
				//如果添加成功，新建一个form
				if(res.entity.holderRealName === undefined){
					//其它情况均跳到这里
					$('#tmplCommon').tmpl(res.entity).insertAfter('#listHook');
				}else{
					$('#tmplOrgSh').tmpl(res.entity).insertAfter('#listHook');
				}
				//console.log(obj);
				//判断是更新还是新增，采取不同相应
				isNew?obj[0].reset():obj.remove();
			});
		}
	}

	/*发起Ajax请求
	 *@para:  oform:所提交表单对象（应为jquery对象）
	 *        callback:成功后调用函数
	 */
	function sendAjax(ajaxUrl,args,callback){
		if(ajaxUrl !== undefined){
			$.ajax({
  				url: ajaxUrl,
  				data: args,
  				type: 'post',
  				cache:false
			}).done(function(res){
				callback(res);
			}).fail(function(err){

			});
		}else{
			//console.log("---ERROR:url为空，ajax无法发送！---")
		}
	}

	return {
		init:function(){
			initPage();
		},
		initComponet:initComponet,
	};
});
