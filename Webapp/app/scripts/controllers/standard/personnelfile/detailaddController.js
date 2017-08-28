'use strict';

/**
 * @ngdoc function
 * @name webappApp.controller:StandardPersonnelfileDetailaddCtrl
 * @description		人员资质--综合查询--资质新增/编辑
 * # StandardPersonnelfileDetailaddCtrl
 * @author chenyuanyuan
 */
angular.module('webappApp')
	.controller('StandardPersonnelfileDetailaddCtrl', ['$location', '$scope', 'StandardPersonnelFileDetailService', 'CommonService', '$timeout', '$stateParams', function($location, $scope, StandardPersonnelFileDetailService, CommonService, $timeout, $stateParams) {
		// 数据初始化
		var self = this;

		self.addInit = function() {
			$scope.data = {};
			$scope.data.name = ''; 							// 名称
			$scope.data.qualifierCertificateType = ''; // 资格证类型
			$scope.data.issueDate = ''; 					// 发证日期
			$scope.data.validityDate = ''; 				// 有效期至
			$scope.data.alertDate = ''; 					// 报警日期
			$scope.isAdd = false; 							//不显示“保存并新建”按钮
		};

		// 保存/更新
		self.save = function(callback) {
			if ($scope.isEdit) {
				StandardPersonnelFileDetailService.updateQualifierCertificateOfCurrentUser($scope.data.id, $scope.data, callback);
			} else {
				StandardPersonnelFileDetailService.addQualifierCertificateOfCurrentUser($scope.data, callback);
			}
		};

		// 保存并新建
		self.saveAndNew = function() {
			self.save(function() {
				CommonService.success();
			});
		};

		// 保存并关闭
		self.saveAndClose = function() {
			self.save(function() {
				CommonService.success();
			});
		};

		// 根据传入的参数获取当前用户选择类别
		self.type = $stateParams.type; // 类型：add:添加; edit:编辑
		// 按类型为add或edit分别进行数据的初始化
		if (angular.equals(self.type, 'add')) {
			self.addInit();
			$scope.isAdd = true;
			$scope.isEdit = false;
		} else {
			$scope.isAdd = false;
			$scope.isEdit = true;
			$scope.data = $stateParams.data;
		}

		$scope.saveAndClose = self.saveAndClose;
		$scope.saveAndNew = self.saveAndNew;

	}]);