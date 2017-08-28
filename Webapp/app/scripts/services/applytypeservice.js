'use strict';

/**
 * @ngdoc service
 * @name webappApp.ApplyTypeService
 * @description
 * # ApplyTypeService
 * 申请类型
 */
angular.module('webappApp')
.service('ApplyTypeService', function ($http, WebAppMenuService) {
	var self = this;
	// 获取前台菜单ID对应的申请类型
	self.getOneByWebAppMenuId = function(webAppMenuId, callback) {
		var url = '/ApplyType/getOneByWebAppMenuId/' + webAppMenuId;
		$http.get(url)
		.then(function success(response){
			if (callback) {callback(response.data);}
		}, function error(response){
			alert('ApplyTypeService.getOneByWebAppMenuId -> ' + url + ': ' + response.status);
		});
	};
	
	// 获取当前菜单对应的申请类型
	self.getOneOfCurrentMenu = function(callback) {
		WebAppMenuService.getCurrentMenu(function(webAppMenu) {
			self.getOneByWebAppMenuId(webAppMenu.id, function(data) {
				if (callback) {callback(data);}
			});
		});
	};
	
	return {
		getOneByWebAppMenuId: self.getOneByWebAppMenuId,
		getOneOfCurrentMenu: self.getOneOfCurrentMenu
	};
	// AngularJS will instantiate a singleton by calling "new" on this function
});
