define(['jquery',
    'bootstrap3',
    'jquery.validation.zh_CN',
    'bootstrap3.datetimepicker.lang-zh-CN'
], function(jQuery) {

 jQuery("[name='intentionName']").focus();
 jQuery("[name='intentionName']").blur();
    return {
        init: function() {
            this.initPopover();
            this.bindProgress();
            this.rulePanel();
		
        },
        totalAmount: function() {
            var leverageRatioVal = parseInt(jQuery('[name="leverageRatio"]').val(), 10) || 0;
            var investAmountVal = parseInt(jQuery('[name="investAmount"]').val(), 10) || 0;
            jQuery('[name="totalAmount"]').val(leverageRatioVal * investAmountVal + investAmountVal);
            jQuery('[name="totalAmount"]').trigger("change");
        },
        autoRulePanel: function() {
            var totalAmountVal = parseInt(jQuery('[name="totalAmount"]').val(), 10),
                investScopes = jQuery('[name="investScope"]:checked'),
                leverageRatio = jQuery('[name="leverageRatio"]'),
                investScopeVals = investScopes.val();
            if (totalAmountVal >= 50000 || (investScopes.length!==0 && (investScopes.length > 1 || investScopeVals !== "046f8f73-d95d-4746-9c02-1ec9402aa947"))) {
                jQuery('[data-target="#otherTPL"]').tab('show');
            } else {
                jQuery('[data-target="#standTPL"]').tab('show');
            }
            // 股票风控显示 1:1  1:2 1:3	
            if (investScopeVals !== "046f8f73-d95d-4746-9c02-1ec9402aa947" || investScopes.length > 1) {
                if (leverageRatio.find('option').length <= 3) {
                    jQuery.each([4, 5, 6, 7, 8, 9], function(idx, i) {
                        var opt = jQuery('<option value="' + i + '" data-warningLine="" data-openLine="">1:' + i + '</option>');
                        leverageRatio.append(opt);
                    });
                }
            } else {
                leverageRatio.find('option').each(function(idx, item) {
                    if (idx >= 3) {
                        jQuery(item).remove();
                    }
                });
            }
        },
        /**
         *  风控线自动填充
         *  function
         *
         */
        fullWarningLine: function(e) {
            var target = e.currentTarget,
                $target = jQuery(target),
				selectedIndex = jQuery('[name="leverageRatio"]')[0].selectedIndex || 0,
                investScopes = jQuery('[name="investScope"]:checked'),
                investScopeVals = investScopes.val(),
                openLine = "",
                warningLine = "",
                $currentOption = jQuery('[name="leverageRatio"]').find("option")[selectedIndex];
            if (investScopeVals == "046f8f73-d95d-4746-9c02-1ec9402aa947" && investScopes.length === 1) {
                warningLine = $currentOption.attributes['data-warningLine'].value || "";
                openLine = $currentOption.attributes['data-openLine'].value || "";
                jQuery('[name="warningLine"]').val(warningLine);
				jQuery('[name="warningLine"]').blur();
                jQuery('[name="openLine"]').val(openLine);
				jQuery('[name="openLine"]').blur();
            } else {
                jQuery('[name="warningLine"]').val("");
                jQuery('[name="openLine"]').val("");
            }
        },
        /**
         *	风控面板初始化事件绑定
         *	function
         *
         */
        rulePanel: function() {
            var self = this;
            jQuery('[name="investAmount"],[name="leverageRatio"]').on("change", function(e) {
                self.totalAmount();
            });
            jQuery('[name="totalAmount"],[name="investScope"]').on('change', function(e) {
                self.autoRulePanel();
            });
            jQuery('[name="leverageRatio"],[name="investScope"]').on('change', function(e) {
                self.fullWarningLine(e);
            });
        },
        /**
         *	风控条目面板
         *
         *
         */
        initPopover: function() {
			jQuery("input[readonly]").focus(function(){
				if(jQuery(this).attr("readonly")){
					jQuery(this).blur();
				}
			});
            var currentPopover = null;
            var popovers = jQuery('.label-rule').popover({
                html: true,
                animation: false,
                trigger: "manual",
                title: "修改",
                placement: "bottom"
            });

            jQuery('.label-rule>label').live('click', function(event) {
                var self = jQuery(event.currentTarget).parent();
                currentPopover && currentPopover.hide();
                currentPopover = self.data('bs.popover');
                currentPopover.show();
                currentPopover.hoverState = 'out';
            });

            jQuery(".label-rule .popover-close").live('click', function(event) {
                var $target = jQuery(event.currentTarget);
                var $self = $target.parents(".label-rule");
				var $popoverContent = $self.find(".popover-content");
				var input=$popoverContent.find("input");
			
				jQuery.each(input,function(idx,it){
					var $it=jQuery(it);
					$it.val($it.attr("val"));
					$it.removeClass("error");
				});
                event.preventDefault();
                event.stopPropagation();
                $self.data('bs.popover').hide();
            });
            jQuery(".label-rule .btn-edit").live('click', function(event) {
                var $target = jQuery(event.currentTarget);
                var $popoverContent = $target.parents(".label-rule").find(".popover-content");
			
				
                $target.addClass("hide");
                $target.next(".btn-save").removeClass("hide");
				$popoverContent.find("input").removeAttr("readonly");
                $popoverContent.removeClass("readonly-mode");
                event.preventDefault();
                event.stopPropagation();
            });

            jQuery(".label-rule .btn-save").live('click', function(event) {
                var $target = jQuery(event.currentTarget);
                var $self = $target.parents(".label-rule");
                var $popoverContent = $target.parents(".label-rule").find(".popover-content");
				var input=$popoverContent.find("input");
				if(input.valid()===true){;
					$target.addClass("hide");
					$target.prev(".btn-edit").removeClass("hide");
					input.attr("readonly","readonly");
					$self.data('bs.popover').hide();
					$popoverContent.addClass("readonly-mode");

					event.preventDefault();
					event.stopPropagation();
					$self.data('bs.popover').hide();
				}
            });
            jQuery(".label-rule .custom-btn-delete").live('click', function(event) {
                if (confirm("是否确认删除这条风控条目!")) {
                    var $target = jQuery(event.currentTarget);
                    var $self = $target.parents(".label-rule");
                    $self.data('bs.popover').hide();
                    $self.remove();
                }
                event.preventDefault();
                event.stopPropagation();
            });
            jQuery(".label-rule .custom-btn-save").live('click', function(event) {
                var $target = jQuery(event.currentTarget);
                var $self = $target.parents(".label-rule");
                var ruleContent = $self.find('[name="custom"]').val();
                if (ruleContent.trim() !== "") {
                    $self.data('bs.popover').hide();
                    if ($self.hasClass("label-new")) {
                        var clonePopover = $self.clone();
                        clonePopover.popover({
                            html: true,
                            animation: false,
                            trigger: "manual",
                            title: "修改",
                            placement: "bottom"
                        });
                        clonePopover.find('[name="custom"]').val("");
                        clonePopover.insertAfter($self);
                    }
                    ruleContent = $self.find('[name="custom"]').val();
                    $self.find(".custom-btn-delete").removeClass("hide");
                    $self.find(">label").html(ruleContent);
                    $self.removeClass("label-new");
                    $self.data('bs.popover').hide();
                    event.preventDefault();
                    event.stopPropagation();
                } else {
                    alert("条款内容不可为空");
                }
            });
        },
        bindProgress: function() {
            jQuery(".progress-toggle").on('click', function(e) {
                jQuery(".progress-cmp").toggleClass("progress-all");
            });
        },
        dustInit: function() {
            require(['www/module.common'], function(common) {
                common.initUpload();
                common.initDatePicker();
                common.scrollFixPage();
                common.togglePunish();
            });
        }
    };
});
