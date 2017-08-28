'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplyService
 * @description
 * # ApplyService panjie
 * 申请
 */
angular.module('webappApp')
.service('ApplyService', function ($http) {
	var self = this;
	self.update = function(apply, callback) {
		var url = '/Apply/' + apply.id;
		$http.put(url, apply)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('ApplyService.update -> ' + url + ': ' + response.status);
		});
	};
	// 获取申请对应的所有工作信息
	
	
	return {
		update: self.update
	};
});
