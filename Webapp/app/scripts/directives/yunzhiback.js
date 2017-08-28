'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiBack
 * @description 后退按钮
 * # yunzhiBack
 */
angular.module('webappApp')
.directive('yunzhiBack', function (CommonService) {
	return {
		replace: true,
		restrict: 'AE',
		// 模板
		templateUrl: 'views/directive/yunzhiBack.html',
		link: function postLink(scope) {
			scope.back = CommonService.back;
			// 当路由状态的个数大于1时，表示可以后退。否则隐藏后退按钮
			scope.showBack = function() {
				if (CommonService.getStates().length > 1) {
					return true;
				} else {
					return false;
				}
			};
		}
	};
});
