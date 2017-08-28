'use strict';

/**
 * @ngdoc filter
 * @name webappApp.filter:status
 * @function
 * @description
 * # status   用户当前的状态
 * Filter in the webappApp.
 * @author chenyuanyuan
 */
angular.module('webappApp')
// 第一个参数是过滤器名，第二个参数是功能函数
.filter('userStatus', ['$sce', 'UserServer', function ($sce, UserServer) {
	//input为模板传入的变量
	return function (input) {
		var self = {};
		self.result = '';
		self.statuses = [];
		UserServer.getStatuses(function(statuses){
			angular.forEach(statuses, function(value) {
				if (value.key === input) {
					self.result = value.value;
				}
			});
		});
		
		// 引入$sce返回可信任的html标记
		return $sce.trustAsHtml(self.result);
	};
}]);
