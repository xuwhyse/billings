define(['jquery','typeahead','jquery.validation.zh_CN','bootstrap-lightbox'],function(jQuery){
    jQuery('#product-intention .typeahead').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
    }, {
        name: 'auto-list',//dropdown的css类
        displayKey: 'intention_name',//显示字段
        source: function (query, process) {
                var status=jQuery('#product-intention .typeahead').data('param-status');
                $.get('/manager/filtertext/intention.do', { searchname: query,status:status}, function (data) {
                    process(data);
                });
        },
        templates: {
            empty: [
                '<div class="empty-message">',
                '没有找到匹配产品意向',
                '</div>'
            ].join('\n'),
            suggestion: Handlebars.compile('<p>{{intention_name}}</p>')
        },
    });

    jQuery('#capital-user .typeahead').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
    }, {
        name: 'auto-list',
        displayKey: 'user_real_name',
        source: function (query, process) {
                var status=jQuery('#capital-user .typeahead').data('param-status');
                $.get('/manager/filtertext/userext.do', { searchname: query,status:status }, function (data) {
                    process(data);
                });
        },
        templates: {
            empty: [
                '<div class="empty-message">',
                '没有找到匹配资方代表',
                '</div>'
            ].join('\n'),
            suggestion: Handlebars.compile('<p>{{user_real_name}}-{{org_name}}</p>')
        },
    });

    jQuery('#adviser-user .typeahead').typeahead({
        hint: true,
        highlight: true,
        minLength: 1
    }, {
        name: 'auto-list',
        displayKey: 'user_real_name',
        source: function (query, process) {
                var status=jQuery('#adviser-user .typeahead').data('param-status');
                $.get('/manager/filtertext/userext.do', { searchname: query,status:status }, function (data) {
                    process(data);
                });
        },
        templates: {
            empty: [
                '<div class="empty-message">',
                '没有找到匹配投顾方代表',
                '</div>'
            ].join('\n'),
            suggestion: Handlebars.compile('<p>{{user_real_name}}-{{org_name}}</p>')
        },
    });

    jQuery('#product-intention .typeahead').on('typeahead:selected',function(event,item){
            jQuery('#product-intention .pid').val(item.intention_id);
    });
    jQuery('#capital-user .typeahead').on('typeahead:selected',function(event,item){
            jQuery('#capital-user .pid').val(item.user_id);
    });
    jQuery('#adviser-user .typeahead').on('typeahead:selected',function(event,item){
            jQuery('#adviser-user .pid').val(item.user_id);
    });

    jQuery.extend(jQuery.validator.messages, {
        integer: "只能输入整数数字！"
    });
    jQuery(".validateForm").each(function(idx, item) {
        console.log('test');
        jQuery(this).validate({
            errorPlacement: function(error, element) {
                var fieldRow = element.parents(".form-group");
                if (fieldRow.find(".help-block").length > 0) {
                    error.appendTo(fieldRow.find(".help-block"));
                } else {
                    error.appendTo(fieldRow);
                }
            }
        });
    });
    jQuery(document).on('click','.checkbox-panel',function(e){
                //todo:内部label与chechbox点击时均触发，应取消冒泡
        var oThis = $(this);
        var cor_flag = oThis.parent('.img-list-item').find('.icons-correct');
       // console.log(oThis.find('.hideDiv').attr('checked')=='checked');
        jQuery('#errDiv').hide();
        if(oThis.find('.hideDiv').attr('checked')=='checked')
            cor_flag.show();
        else
            cor_flag.hide();
        jQuery('.showDiv').each(function(index, item){
            //console.log(item);
            if(jQuery(item).attr("checked")=='checked'){
                jQuery('#errDiv').show();
                return;
            }
        });
    });
});
