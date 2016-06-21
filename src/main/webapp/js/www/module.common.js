define(['jquery',
    'bootstrap3',
    'jquery.validation.zh_CN',
    'jquery.fileupload-image',
    'jquery.fileupload-process',
    'jquery.fileupload-validate',
    'bootstrap-lightbox',
    'bootstrap3.datetimepicker.lang-zh-CN'
], function($) {

    var bindProgress = function() {
        $(".progress-toggle").on('click', function(e) {
            $(".progress-cmp").toggleClass("progress-all");
        });
    }
    var initDatePicker_date=function(){
        $('.datepicker').datetimepicker({
            format: "yyyy-mm-dd",
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left",
            minView: 2,
            startView: 2,
            language:"zh-CN"
        });
    }
    var initHover = function(){
        $('.con-dl-item').on('mouseover',function(){
            $(this).find('.col-dl-hint').show();
        })
         $('.con-dl-item').on('mouseout',function(){
            $(this).find('.col-dl-hint').hide();
        })
    }
    //动态绑定lightbox,对应示例在zhangyang.html里面
    var initAsynLightBox = function(){
        $(document).on('click.lightbox.data-api', '[data-param-toggle*="lightbox"]', function(e) {
            var $this = $(this);
            var href = $this.attr('href');
            var $target = $($this.attr('data-target') || (href && href.replace(/.*(?=#[^\s]+$)/, ''))); //strip for ie7
            //如果有这个属性，则动态加载
            if($this.data('param-src'))
                replaceLightBox($this,$target);

            e.preventDefault();
            var option = {
                backdrop: true,
                keyboard: true,
                show: true
            };
            $target
                .lightbox(option);
        });
        /*动态绑定*/
        function replaceLightBox(obj,target){
            var oSrc = obj.data('param-src'),
                oMsg = obj.data('param-msg'),
                oContent = target.find('.lightbox-content'),
                oOldImg = oContent.find('img'),
                oMsgDiv = oContent.find('p');

            oOldImg&&oOldImg.remove();

            var oImg = $('<img />');
            oImg.attr('src',oSrc);
            oImg.appendTo(oContent);

            oMsgDiv.html('')&&oMsgDiv.html(oMsg);
        }
    }
    return {

        init: function() {
        },
        chooseUserType: function(){
            //first_type页面
            $('#f_accountType').on('click','label',function(){
                var oThis = $(this);
                oThis.addClass('active');
                var oSibling = oThis.parent('span').siblings('span');
                otherLabel = oSibling.find('label');
                otherLabel.hasClass('active')&&otherLabel.removeClass('active');
                $('#f_submit').attr('disabled')&& $('#f_submit').removeAttr('disabled');
            });
            $(".progress-toggle").on('click', function(e) {
                $(".progress-cmp").toggleClass("progress-all");
            });
        },
        //duty页面，选择有惩罚展开输入框
        togglePunish: function() {
            ($('#dPunish2').attr('checked') == 'checked') && $('.dPunishCnt').removeClass('hide');
            $('input[type=radio]').on('click', function() {
                ($('#dPunish2').attr('checked') == 'checked') && $('.dPunishCnt').removeClass('hide');
                ($('#dPunish1').attr('checked') == 'checked') && $('.dPunishCnt').addClass('hide');
            });
        },
        initUpload: function() {
            $('.upLoad').fileupload({
                dataType: 'json',
                autoUpload: false,
                replaceFileInput: false,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxNumberOfFiles : 1,
                maxFileSize: 5000000, // 5 MB
                disableImageResize: /Android(?!.*Chrome)|Opera/.test(window.navigator.userAgent),
                previewMaxWidth: 120,
                previewMaxHeight: 150,
                previewCanvas: true,
                previewCrop: true,
                formData: function (form) {
                    return {
                        aab:12,
                        bb2:14
                    };
                },
            }).on('fileuploadadd', function(e, data) {

                var target = e.currentTarget;
                preview = $(target).parents(".uploadPanel").find(".uploadMsg");
                preview.html("");
                data.context = $('<div/>').appendTo(preview);
                data.data={
                    aab:12,
                    bb2:14
                };

                $.each(data.files, function(index, file) {
                    var node = $('<span/>').addClass("fileName").text(file.name);
                    node.appendTo(data.context);
                });
            })
        },
        initAsynUpload:function(){
             'use strict';
            $('.upLoad').fileupload({
                url: '/member/uploadpif.do',
                dataType: 'json',
                autoUpload: true,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxFileSize: 5000000, // 5 MB
                // Enable image resizing, except for Android and Opera,
                // which actually support image resizing, but fail to
                // send Blob objects via XHR requests:
                disableImageResize: /Android(?!.*Chrome)|Opera/
                    .test(window.navigator.userAgent),
                previewMaxWidth: 120,
                previewMaxHeight: 70,
                previewCrop: true,
                done: function (e, data) {
                    var res = data.result;
                    if(!res.exception){
                        var target = $(this).parents('.uploadPanel').find('.files');
                        target.html('');
                        var img = $('<img/>').attr('src',res.attchPath);
                        img.appendTo(target);
                        $('.picData').find('input[name="picf'+res.attchType+'"]').val(res.attchId);
                    }else{
                        var target = $(this).parents('.uploadPanel').find('.uploadMsg');
                        target.html(res.msg);
                    }
                }
            }).on('fileuploadadd', function (e, data) {
                var target = e.currentTarget;
                var type_id = $(target).data("param-type");
                $('#attchType').val(type_id);
            });
            bindProgress();
            initDatePicker_date();
            $(".picData").each(function(idx, item) {
       // console.log(jQuery(this));
                $(this).validate({
                    ignore:"",
                    errorPlacement: function(error, element) {
                        var fieldRow = element.parents(".picData");
                        if (fieldRow.find(".help-block").length > 0) {
                            error.appendTo(fieldRow.find(".help-block"));
                        } else {
                            error.appendTo(fieldRow);
                        }
                    }
                });
            });
        },
        initContract:function(){
            bindProgress();
            initDatePicker_date();
            initHover();
           // initAsynLightBox();
        },
        //产品详情页浏览页，部分input可以编辑，初始化其不可编辑
        initProductDetail:function(){
            jQuery('#product-detail').find('input').attr({
                readonly: 'readonly'
            });
        },
		initDatePicker:function(){
		  jQuery('.datepicker').datetimepicker({
				format: "yyyy-mm-dd",
				autoclose: true,
				todayBtn: true,
				pickerPosition: "bottom-left",
				minView: 2,
				startView: 4,
				language:"zh-CN"
			});
		},
        initCheckbox:function(){
            $(document).on('click','.checkbox-panel',function(e){
                //todo:内部label与chechbox点击时均触发，应取消冒泡
                var oThis = $(this);
                var cor_flag = oThis.parent('.img-list-item').find('.icons-correct');
               // console.log(oThis.find('.hideDiv').attr('checked')=='checked');
                $('#errDiv').hide();
                if(oThis.find('.hideDiv').attr('checked')=='checked')
                    cor_flag.show();
                else
                    cor_flag.hide();
                $('.showDiv').each(function(index, item){
                    //console.log(item);
                    if($(item).attr("checked")=='checked'){
                        $('#errDiv').show();
                        return;
                    }
                });
            });
        },
        /**
         * 滚动条监听，duty页面
         */
        scrollFixPage: function() {
            $(window).on('scroll', function() {
                var pageTop = document.documentElement.scrollTop || document.body.scrollTop;
                var hkHeight = [];
                var pageTag = $('.pageTag>li').find('ul');
                pageTag.find('li').removeClass('active');
                $('.pageHook').each(function(index) {
                    /* iterate through array or object */
                    hkHeight[index] = $(this).offset().top;
                });
                for (var i = 0; i < hkHeight.length - 1; i++) {
                    hkHeight[i + 1] = (hkHeight[i + 1] - hkHeight[i]) / 2 + hkHeight[i];
                }
                //console.log(pageTop);
                if (pageTop < hkHeight[1]) {
                    pageTag.find('li:eq(0)').addClass('active');
                } else if (hkHeight[1] <= pageTop && pageTop < hkHeight[2]) {
                    pageTag.find('li:eq(1)').addClass('active');
                } else if (hkHeight[2] <= pageTop && pageTop < hkHeight[3]) {
                    pageTag.find('li:eq(2)').addClass('active');
                } else if (pageTop >= hkHeight[3]) {
                    pageTag.find('li:eq(3)').addClass('active');
                }
            })
        }

    }

});
