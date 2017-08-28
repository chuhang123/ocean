'use strict';

/**
 * @ngdoc service
 * @name webappApp.PurposeService
 * @description 用途的M层（service）。用来获取后台返回数据。
 * # PurposeService
 * Service in the webappApp.
 */
angular.module('webappApp')
.service('PurposeService', ['$http', function ($http) {
	var self = this;
	//获取后台数据
	self.all = function (callback) {
		$http.get('/Purpose/getAll').then(function success(response) {
			callback(response.data);
		});
	};
	
	return {
		getPurposeArray: self.all,
		all: self.all
	};
}]);
