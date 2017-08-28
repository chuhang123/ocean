'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiAccess
 * @description 拥有权限，原样输出；没有权限，输出空字符串
 * # 权限过滤
 */
angular.module('webappApp')
.directive('yunzhiAccess', function (WebAppMenuService) {
	var self = {};
	
	/**
	 * 动态链接函数。在controller后面执行。原则上，如果我们的指令不与其它模块交互的话，函数直接写在link即可
	 * @param scope
	 * @param element 元素信息 https://docs.angularjs.org/api/ng/function/angular.element
	 * @author panjie@yunzhiclub.com
	 */
	self.link = function(scope, element) {
		// 有权限，不做任何改变，无权限，输出空字符串
		self.checkCurrentUserAccessByRoute(scope.route, function(state){
			if (!state) {
				element.text('');
			}
		});
	};
	
	/**
	 * 检测当前用户是否拥有权限
	 * @param route String 例：system.user system
	 * @param callback
	 * @author panjie
	 */
	self.checkCurrentUserAccessByRoute = function(route, callback) {
		WebAppMenuService.checkCurrentUserIsAllowedByRoute(route, function(state){
			if(callback){callback(state);}
		});
	};
	
	return {
		scope: {
			route: '@'
		},
		restrict: 'AE',
		link: self.link
	};
});
