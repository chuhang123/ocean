'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:UserUserfileIndexCtrl
 * @description 用户管理Indexcontroller
 * # UserUserfileIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('UserUserfileIndexCtrl', ['$scope', 'UserServer', '$location', 'CommonService', '$stateParams', 'config', '$state', function ($scope, UserServer, $location, CommonService, $stateParams, config, $state) {
	var self = this;

	// 获取ui-route传入的参考
	$scope.params = self.params = {
		page: $stateParams.page ? parseInt($stateParams.page) - 1 : 1,
		size: $stateParams.pageSize ? parseInt($stateParams.pageSize) : config.pageSize,
		district: {id: parseInt($stateParams.districtId)},                  // 区域
		departmentType: {id: parseInt($stateParams.departmentTypeId)},      // 部门类型
		status: {key: parseInt($stateParams.status)},                              // 用户类型
		departmentName: $stateParams.departmentName                         // 部门名称
	};


	// 分页数据初始化
	CommonService.initPageData($scope);

	//获取所有数据
	self.getAll = function () {
		// 整理传入的参数信息。我们之所以在params的属性中只使用了基本类型，是为了以后将查询条件放到url中做准备
		var params = {
			page: self.params.page,
			size: self.params.size,
			districtId: self.params.district.id ? self.params.district.id : undefined,
			departmentTypeId: self.params.departmentType.id ? self.params.departmentType.id : undefined,
			status: (self.params.status.key !== '-2' && self.params.status.key !== -2) ? self.params.status.key : undefined,
			departmentName: self.params.departmentName ? self.params.departmentName : undefined                                   //
		};

		UserServer.pageAllBySpecification(params, function (data) {
			$scope.data = data;
		});
	};

	// 执行获取数据


	// 提交数据
	self.submit = function () {
		$stateParams.districtId = self.params.district.id;
		$stateParams.departmentTypeId = self.params.departmentType.id;
		$stateParams.status = self.params.status.key;
		$stateParams.departmentName = self.params.departmentName;
		$state.go($state.current, $stateParams, {reload: true});
	};

	// 增加方法
	self.add = function () {
		$location.path('/system/UserfileAdd');
	};

	// 编辑方法
	self.edit = function (data) {
		UserServer.setCurrentOperateObject(data);
		$location.path('/system/UserfileEdit');
	};

	// 详情方法
	self.detail = function () {
		$location.path('/system/UserfileDetail');
	};

	// 重置密码
	self.resetPassword = function (user) {
		CommonService.warning(function (success, error) {
			UserServer.resetPassword(user.id, function(status){
			    if (204 === status) {
                    success();
                } else {
                    error('error', '系统或网络异常', '');
                }
			});
		});
	};

	// 删除
	self.delete = function (index, user) {
        CommonService.warning(function (success, error) {
            UserServer.delete(user, function(status){
                if (204 === status) {
                    // 从视图中删除该数据
                    $scope.data.content.splice(index, 1);

                    success();
                } else {
                    error('error', '系统或网络异常', '');
                }
            });
        });
    }

	// 方法统一暴露
	$scope.add = self.add;
	$scope.edit = self.edit;
	$scope.detail = self.detail;
	$scope.resetPassword = self.resetPassword;
	$scope.submit = self.submit;
	$scope.delete = self.delete;
}]);
