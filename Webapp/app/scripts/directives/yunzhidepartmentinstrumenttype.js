'use strict';

/**
 * @ngdoc directive
 * @name webappApp.directive:yunzhiDepartmentInstrumentType
 * @description 获取某个部门下拥有的器具类别
 * # 部门器具类别
 */
angular.module('webappApp')
.directive('yunzhiDepartmentInstrumentType', function ($http, ApplianceService) {
	return {
		//独立scope，使各个指令之间互不影响
		scope: {
			ngModel: '=',        //将使用此指令中的appliance属性，双向绑定到$scope.appliance
			measureUser: '=',    // 双向绑定data-measure-user
			discipline: '='    //双向绑定data-discipline
			// hide: '@'             //单项绑定data-hide
		},
		//模板
		templateUrl: 'views/directive/yunzhiDepartmentInstrumentType.html',
		restrict: 'EA', //指令类型，多为E（元素），A（属性）
		controller: function ($scope) {
			// 初始化是否隐藏本元素
			if ($scope.hide) {
				$scope.hide = false;
			} else {
				$scope.hide = true;
			}
			
			$scope.instrumentTypes = [];                      //初始化所有器具名称
			$scope.appliance = {};                       //初始化器具名称
			$scope.appliance.selected = $scope.ngModel; //传值
			
			// 当用户进行选择时，更新ngModel。
			$scope.updateModel = function (newValue) {
				$scope.ngModel = newValue;
			};
		},
		link: function postLink(scope) {
			// 监听传入的data-measure-user是否发生了变化，如果发生了变化，则重新获取器具列表
			scope.$watch('measureUser', function (newValue) {
				if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
					scope.hide = false;
					// 获取用户可见的器具列表
					ApplianceService.getArrayByMeasurementUserId(newValue.id, function (datas) {
						scope.instrumentTypes = datas;
						// 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个器具给当前器具(或者刚传入器具用户id，则初始化器具)
						if (datas.length > 0) {
							scope.appliance.selected = datas[0];
							scope.ngModel = scope.appliance.selected;
						}
					});
				} else {
					scope.hide = true;
					//隐藏器具，则将 选中的器具 初始化，并传给V层，供下级关联使用。
					var data = {};
					scope.appliance.selected = data;
					scope.ngModel = scope.appliance.selected;
				}
			}, true);
			
			// 监听传入的data-discipline是否发生了变化，如果发生了变化，则重新获取器具列表
			scope.$watch('discipline', function (newValue) {
				if (newValue && !angular.equals(newValue, {}) && (newValue.id !== 0)) {
					scope.hide = false;
					// 获取用户可见的器具列表
					ApplianceService.getArrayByDisciplineId(newValue.id, function (datas) {
						scope.instrumentTypes = datas;
						// 如果大小不为0，而且用户并没有传入ngModel实体，则将第一个器具给当前器具(或者刚传入器具用户id，则初始化器具)
						if (datas.length > 0) {
							scope.appliance.selected = datas[0];
							scope.ngModel = scope.appliance.selected;
						}
					});
				} else {
					scope.hide = true;
					//隐藏器具，则将 选中的器具 初始化，并传给V层，供下级关联使用。
					var data = {};
					scope.appliance.selected = data;
					scope.ngModel = scope.appliance.selected;
				}
			}, true);
		}
	};
});
