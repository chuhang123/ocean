'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:MandatorycalibraterecordCtrl
 * @description    强制检定备案
 * # MandatorycalibraterecordCtrl
 * Controller of the webappApp
 * @author chenyuanyuan
 */
angular.module('webappApp')
.controller('MandatorycalibraterecordCtrl', ['$location', '$scope', 'MandatoryCalibrateRecordService', 'UserServer', function($location, $scope, MandatoryCalibrateRecordService, UserServer) {
	var self = this;
	$scope.data = [];
	$scope.console = console;
	// 定义获取数据方法
	self.showData = function() {
		// 获取后台数据
		MandatoryCalibrateRecordService.getCurrentUserMandatoryCalibrateRecordArray(function(data) {
			$scope.data = data;
		});
	};

	// 执行获取数据
	self.showData();


	self.showAudit = function(object) {
		// 没有办结，并且当前的工作对应的审核部门与申请部门相同
		if (!object.done && object.apply.auditingDepartment.id === object.auditDepartment.id) {
			return true;
		} else {
			return false;
		}
	};

	self.showFinish = function(object) {
		// 没有办结，并且当前的工作对应的审核部门与申请部门相同
		if (!object.apply.done && object.apply.auditingDepartment.id === object.auditDepartment.id) {
			return true;
		} else {
			return false;
		}
	};

	//根据当前登录的用户类型判断是否显示新增按钮
    UserServer.getCurrentLoginUser(function (currentUser) {
        if (currentUser.department.departmentType.name === '器具用户') {
            $scope.showAddButton = true;
        } else {
            $scope.showAddButton = false;
        }
    });

	// 方法统一暴露
	$scope.delete = self.delete;
	$scope.showAudit = self.showAudit;
	$scope.showFinish = self.showFinish;

}]);
