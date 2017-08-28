'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:MandatoryInstrumentStatus
 * @function
 * @description
 * 强检器具状态
 * 由于返回的信息中带有html，所以需要在调用该过滤器的地方配置ng-bind-html使用
 * # MandatoryInstrumentStatus
 * Filter in the webappApp.
 */
angular.module('webappApp')
.filter('MandatoryInstrumentStatus', function (instrumentService) {
	return function (input) {
		// 初始化
		input = parseInt(input);
		var style = '';
		var status = instrumentService.getStatus();
		var outputString = '';
		
		// 循环找状态
		angular.forEach(status, function (value) {
			if (input === value.id) {
				outputString = value.value;
				style = value.style;
			}
		});
		
		// 如果未找到，则返回未知
		if (outputString === '') {
			outputString = '未知';
			style = 'danger';
		}
		
		//返回正确的状态
		return '<span class="badge badge-' + style +'">' + outputString + '</span>';
	};
});
