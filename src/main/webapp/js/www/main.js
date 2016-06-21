/**
 *
 *
 *
 *
 * */

define(['jquery','bootstrap3'], function(jQuery) {
    var moduleStr = jQuery("body").data("module"),
    module=moduleStr.split("#");
    if (module[0] !=='') {
        require(['www/' + module[0]], function(mod) {
        	if(module[1]){
        		 mod[module[1]]();
        	}else{
        		 mod.init();
        	}
		});
    }
});

