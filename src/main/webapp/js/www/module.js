/*
 *   UI framework module AMD
 *
 *
 */

require.config({
    baseUrl: '/js',
    urlArgs:"ver="+document.documentElement.attributes['ver'].value,
    shim: {
        'jquery.validation.additional-methods': ['jquery.validation'],
        'jquery.validation.zh_CN': ['jquery', 'jquery.validation', 'jquery.validation.additional-methods'],
        'jquery.validation': ['jquery'],
        'bootstrap3': ['jquery'],
        'bootstrap3.datetimepicker.lang-zh-CN': ['bootstrap3', 'bootstrap3.datetimepicker', 'css!bootstrap3.datetimepicker-css'],
        'bootstrap3.treeview': ['bootstrap3', 'css!bootstrap3.treeview-css'],
        'bootstrap3.slider': ['bootstrap3', 'css!bootstrap3.slider-css'],
        'scojs.modal': ['bootstrap3', 'css!scojs-css'],
        'scojs.confirm': ['bootstrap3', 'css!scojs-css', 'scojs.modal'],
        'scojs.message': ['bootstrap3', 'css!scojs-css'],
        'jquery.validation.password': ['jquery.validation', 'css!jquery.validation.password'],
        'jquery.ui.widget': ['jquery'],
        'jquery.fileupload': ['jquery.ui.widget', 'css!jquery.fileupload-css', 'jquery.iframe-transport'],
        'jquery.fileupload-process': ['jquery', 'jquery.fileupload'],
        'load-image-meta': ['load-image'],
        'jquery.fileupload-validate': ['jquery', 'jquery.fileupload-process'],
        'jquery.fileupload-image': ['load-image-meta', 'canvas-to-blob', 'jquery.fileupload-process'],
        'jquery.treeview': ['jquery', 'css!jquery.treeview-css'],
        'jquery.placeholder': ['jquery'],
        'highcharts': ['jquery'],
        'bootstrap-highcharts': ['jquery'],
        'bootstrap-lightbox':['bootstrap3','css!bootstrap-lightbox-css']
    },
    paths: {
		'common':'./common',
        'css': '../thirdparty/require-css/css',
        'domReady': '../thirdparty/requirejs/domReady',
        'jquery': '../thirdparty/jquery/jquery-1.8.3',
        'ui.framework': './ui.framework',
		'main':'./main',
        'bootstrap3': '../thirdparty/bootstrap-3.0.3/js/bootstrap',
        'bootstrap3-css': '../thirdparty/bootstrap-3.0.3/css/bootstrap',
        'bootstrap3-theme': '../thirdparty/bootstrap-3.0.3/css/bootstrap-theme',
        'bootstrap3.treeview-css': '../thirdparty/bootstrap-treeview/src/css/bootstrap-treeview',
		'bootstrap3.treeview':'../thirdparty/bootstrap-treeview/src/js/bootstrap-treeview',
        'scojs.message': '../thirdparty/scojs/js/sco.message',
        'scojs.modal': '../thirdparty/scojs/js/sco.modal',
        'scojs.confirm': '../thirdparty/scojs/js/sco.confirm',
        'scojs-css': '../thirdparty/scojs/css/scojs',
        'bootstrap3.slider-css': '../thirdparty/bootstrap-slider/css/bootstrap-slider',
        'bootstrap3.datetimepicker': '../thirdparty/bootstrap-datetimepicker/js/bootstrap-datetimepicker',
        'bootstrap3.datetimepicker-css': '../thirdparty/bootstrap-datetimepicker/css/bootstrap-datetimepicker',
        'bootstrap3.datetimepicker.lang-zh-CN': '../thirdparty/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN',

        'bootstrap3.slider': '../thirdparty/bootstrap-slider/js/bootstrap-slider',
        'jquery.validation.zh_CN': '../thirdparty/jquery-validation/src/localization/messages_zh',
        'jquery.validation': '../thirdparty/jquery-validation/dist/jquery.validate',
        'jquery.validation.additional-methods': '../thirdparty/jquery-validation/dist/additional-methods',

        'jquery.validation.password': '../thirdparty/jquery-validate.password/jquery.validate.password',
        'jquery.validation.password-css': '../thirdparty/jquery-validate.password/jquery.validate.password',
        'highlight': '../thirdparty/highlight/highlight.pack',

        'jquery.form': '../thirdparty/jquery-form/jquery.form',
        'jquery.placeholder': '../thirdparty/jquery-html5-placeholder-shim/jquery.html5-placeholder-shim',

        'jquery.fileupload': '../thirdparty/jquery-file-upload/js/jquery.fileupload',
        'jquery.fileupload-css': '../thirdparty/jquery-file-upload/css/jquery.fileupload',
        'jquery.fileupload-process': '../thirdparty/jquery-file-upload/js/jquery.fileupload-process',
        'canvas-to-blob': '../thirdparty/jquery-file-upload/js/vendor/canvas-to-blob.min',
        'load-image': '../thirdparty/jquery-file-upload/js/vendor/load-image',
        'load-image-ios': '../thirdparty/jquery-file-upload/js/vendor/load-image-ios',
        'load-image-meta': '../thirdparty/jquery-file-upload/js/vendor/load-image-meta',
        'load-image-exif': '../thirdparty/jquery-file-upload/js/vendor/load-image-exif',
        'jquery.fileupload-image': '../thirdparty/jquery-file-upload/js/jquery.fileupload-image',
        'jquery.fileupload-angular': '../thirdparty/jquery-file-upload/js/jquery.fileupload-angular',
        'jquery.fileupload-audio': '../thirdparty/jquery-file-upload/js/jquery.fileupload-audio',
        'jquery.fileupload-video': '../thirdparty/jquery-file-upload/js/jquery.fileupload-video',
        'jquery.fileupload-validate': '../thirdparty/jquery-file-upload/js/jquery.fileupload-validate',
        'jquery.iframe-transport': '../thirdparty/jquery-file-upload/js/jquery.iframe-transport',
        'angular': '../thirdparty/jquery-file-upload/js/vendor/angular.min',
        'jquery.ui.widget': '../thirdparty/jquery-file-upload/js/vendor/jquery.ui.widget',
        'jquery.tmpl': '../thirdparty/jquery-tmpl/jquery.tmpl',
        'jquery.treeview-css': '../thirdparty/zTree_v3/css/zTreeStyle/zTreeStyle',
        'jquery.treeview': '../thirdparty/zTree_v3/js/jquery.ztree.all-3.5',
        'highcharts': '../thirdparty/Highcharts-4/js/highcharts',
        'highcharts-data': '../thirdparty/Highcharts-4/js/modules/data',
        'highcharts-drilldown': '../thirdparty/Highcharts-4/js/modules/drilldown',
        'bootstrap-highcharts': '../thirdparty/bootstrap-highcharts/bootstrap-highcharts',
        'bootstrap-lightbox':'../thirdparty/bootstrap-lightbox/bootstrap-lightbox',
        'bootstrap-lightbox-css':'../thirdparty/bootstrap-lightbox/bootstrap-lightbox',
    },
	callback:function(e){
		require(['main'],function(e){
		});
	}
});


