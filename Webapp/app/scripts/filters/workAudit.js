'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:workAudit
 * @function
 * @description
 * # workAudit      根据用户当前工作状态判断当前操作为“查看”还是“审核”
 * Filter in the webappApp.
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.filter('workAudit', ['$sce', function($sce) {
		return function(input) {
			if (input.done) {
				return "查看";
			} else {
				return $sce.trustAsHtml('');
			}
		};
	}]);