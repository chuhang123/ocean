'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:RoleRolefileEditCtrl
 * @description
 * # RoleRolefileEditCtrl
 * Controller of the webappApp
 * 角色管理 编辑
 */
angular.module('webappApp')
.controller('RoleRolefileEditCtrl', ['$scope', 'RoleRolefileService', '$location', 'WebAppMenuService', 'CommonService', '$stateParams', function ($scope, RoleRolefileService, $location, WebAppMenuService, CommonService, $stateParams) {
	var self = this;
	$scope.id = CommonService.getUniqueId();
	
	// 初始化信息
	self.init = function () {
		$scope.edit = true;
		$scope.data = $stateParams.data;
		WebAppMenuService.getMenuTree(function (datas) {
			// 对前台菜单添加权限
			RoleRolefileService.addCheckedInfo(datas, $scope.data.webAppMenus);
			$scope.datas = datas;
		});
	};
	
	self.init();
	
	/**
	 * 提交数据
	 * @author panjie
	 */
	self.saveAndClose = function () {
		RoleRolefileService.save($scope, function(){
			$location.path('/system/role');
		});
	};
	
	// 统一暴露
	$scope.saveAndClose = self.saveAndClose;
	$scope.parentMenuClick = RoleRolefileService.parentMenuClick;
}
]);