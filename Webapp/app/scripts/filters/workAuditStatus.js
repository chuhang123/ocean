'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:workAuditStatus
 * @function
 * @description           
 * # workAuditStatus    用户当前工作状态
 * @ author chenyuanyuan
 */
angular.module('webappApp')
	.filter('workAuditStatus', function() {
		return function(input) {
			if (input.todo === true) {
				return "待办";
			} else if (input.doing === true) {
				return "在办";
			} else if (input.done === true) {
				return "已办";
			} else {
				return "未知状态";
			}
		};
	});