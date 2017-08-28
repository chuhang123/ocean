'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:DepartmentIndexCtrl
 * @description 部门(对应前台四种用户管理)管理
 * # DepartmentIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('DepartmentIndexCtrl', ['$scope', 'departmentService', '$location', 'CommonService', '$stateParams', 'config', 'UserServer', function ($scope, departmentService, $location, CommonService, $stateParams, config, UserServer) {
	var self = this;
	$scope.type = self.type = $stateParams.type;
	self.page = $stateParams.page ? parseInt($stateParams.page) - 1 : 1;
	self.size = $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize;
	self.init = function () {
		$scope.data = {
			content: [],
			totalPages: 0,
			totalElements: 0,
			first: true,
			last: true,
			size: self.size,
			number: self.page,
			numberOfElements: 0,
			sort: null
		};
	};
	self.init();

	// 定义获取数据方法
	self.showData = function () {
		var params = {
			page: $scope.data.number,
			size: $scope.data.size
		};
		// 获取后台数据
		departmentService.pageByDepartmentTypePinyinOfCurrentUserManageDistricts($scope.type, params, function (data) {
			$scope.data = data;
		});
	};

	// 执行获取数据
	self.showData();

	// 删除功能
    self.delete = function (index, id) {
        //提示用户
        CommonService.warning(function (success, error) {
            departmentService.delete(id, function (response) {
                if (204 === response.status) {
                    // 删除此条数据，更新视图
                    $scope.data.content.splice(index, 1);
                    success();
                } else {
                    // 未删除关联实体
                    error('error', '请先删除与其相关联的其它记录', '');
                }
            });
        });
    };

	self.showQuxian = UserServer.showQuxian;

	self.showShi = UserServer.showShi;

	$scope.showQuxian = self.showQuxian;
	$scope.showShi = self.showShi;
	$scope.console = console;
	$scope.delete = self.delete;
}]);
