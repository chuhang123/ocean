'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:workIsDone
 * @function
 * @description      强检备案表中是否办结，true则显示“已办结”，false则显示“未办结”
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.filter('workIsDone', function() {
		return function(input) {
			if (input === true) {
				return "是";
			} else if (input === false) {
				return "否";
			} else {
				return "未知状态";
			}
		};
	});