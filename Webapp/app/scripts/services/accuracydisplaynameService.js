'use strict';

/**
 * @ngdoc service
 * @name webappApp.accuracyDisplayName
 * @description
 * # 精度显示名称
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('accuracyDisplayNameService', function ($http) {
	var self = this;
	// 初始化数据
	self.init = function () {
		self.cache = {};
		self.cache.all = [];
	};
	
	self.init();
	
	// 获取所有数据
	self.all = function (callback) {
		if (angular.equals(self.cache.all, [])) {
			var url = '/AccuracyDisplayName/getAll';
			$http.get(url)
			.then(function success(response) {
				self.cache.all = response.data;
				callback(self.cache.all);
			}, function error(response) {
				alert("数据请求发生错误, 请求url:" + url);
				console.log(response);
			});
		} else {
			callback(self.cache.all);
		}
	};
	
	return {
		init: self.init,
		getAll: self.all
	};
});
