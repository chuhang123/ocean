'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardFiledeviceinstrumentIndexCtrl
 * @description
 * # StandardFiledeviceinstrumentIndexCtrl
 * Controller of the webappApp
 */
angular.module('webappApp')
.controller('StandardFiledeviceinstrumentIndexCtrl', function ($scope, DeviceInstrumentService, $stateParams, config, CommonService) {
	var self = this;
	// 初始化分页信息
	$scope.page = self.page = parseInt($stateParams.page) ? parseInt($stateParams.page) - 1 : 0;
	$scope.pageSize = self.pageSize = parseInt($stateParams.pageSize) ? parseInt($stateParams.pageSize) : config.pageSize;
	$scope.deviceSet = {id: parseInt($stateParams.deviceSetId)};
	// 初始化数据
	self.init = function () {
		$scope.data = {
			content: [],
			totalPages: 0,
			totalElements: 0,
			first: true,
			last: true,
			size: self.pageSize,
			number: self.page,
			numberOfElements: 0,
			sort: null
		};
	};
	self.init();

	// 当标准装置变化时，进行数据重新请求
	self.deviceSetChange = function(newValue) {
		if (newValue && typeof (newValue.id) !== 'undefined') {
			var params = {
				page: $scope.data.number,
				size: $scope.data.size
			};
			DeviceInstrumentService.pageByDeviceSetIdOfCurrentUser(newValue.id, params, function(data){
				$scope.data = data;
			});
		}
	};

	// 是否显示多个 标准装置
	self.showManyDeviceSet = function() {
		if ($scope.deviceSet.id !== 0) {
			return false;
		} else {
			return true;
		}
	};

	// 是否显示添加按钮
	self.showAdd = function () {
		return !self.showManyDeviceSet();
	};

	// 删除功能实现
    self.delete = function (index, deviceSetId, measureScaleId, accuracyId) {
	    // 提示用户是否确定删除
        CommonService.warning(function (success, error) {
            //请求后台，删除数据
            DeviceInstrumentService.delete(deviceSetId, measureScaleId,
                accuracyId, function (status) {
                if (204 === status) {
                    // 把该记录从视图的数组中移除
                    $scope.data.content.splice(index, 1);
                    // 提示用户删除成功
                    success();
                } else {
                    // 中间表无关联删除的异常，如删除失败，应是网络问题
                    error('error', '系统或网络异常', '');
                }
            });
        });
    };

	// 是否显示删除按钮
	self.showDelete = self.showAdd;

	$scope.$watch('deviceSet', self.deviceSetChange);
	$scope.showManyDeviceSet = self.showManyDeviceSet;
	$scope.showAdd = self.showAdd;
	$scope.showDelete = self.showDelete;
	$scope.delete = self.delete;
});
